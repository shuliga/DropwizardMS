import io.dropwizard.client.HttpClientBuilder;
import io.dropwizard.client.HttpClientConfiguration;
import org.apache.http.client.HttpClient;
import org.joda.time.DateTime;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by yshuliga on 06.11.2015.
 */
public class ProcessorRestTest {
    public static final String RESOURCE_URL = "http://localhost:8099/processor/process/";
    private static ExecutorService parallelExecutor = Executors.newFixedThreadPool(8);
    private static ExecutorService serialExecutor = Executors.newSingleThreadExecutor();

    public static void main(String[] args) {
        DateTime time = new DateTime();
        run(serialExecutor);
        System.out.println("Serial processing took: " + getTimeElapsed(time) + " ms");
        time = new DateTime();
        run(parallelExecutor);
        System.out.println("Parallel processing took: " + getTimeElapsed(time) + " ms");
        parallelExecutor.shutdown();
        serialExecutor.shutdown();
    }

    private static long getTimeElapsed(DateTime time) {
        return new DateTime().minus(time.getMillis()).toDate().getTime();
    }

    private static void run(ExecutorService executorService) {
        Collection<CompletableFuture<Integer>> cfl = new ArrayList<>();
        for (int i = 1; i <= 8; i++)
            cfl.add(processRemote(executorService, i));
        CompletableFuture.allOf(cfl.toArray(new CompletableFuture[cfl.size()])).join();
    }

    private static CompletableFuture<Integer> processRemote(ExecutorService executorService, final int i) {
        return CompletableFuture.supplyAsync(() -> doRequest(i), executorService);
    }

    private static Integer doRequest(int i) {
        int code = -1;
        try {
            URL msUrl = new URL(RESOURCE_URL + i);
            HttpURLConnection connection = (HttpURLConnection) msUrl.openConnection();
            connection.setRequestMethod("POST");
            connection.connect();
            code = connection.getResponseCode();
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
        return code;
    }
}
