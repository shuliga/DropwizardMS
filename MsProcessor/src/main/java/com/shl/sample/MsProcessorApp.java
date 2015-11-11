package com.shl.sample;

import com.shl.sample.exception.IIOExceptionInterceptor;
import com.shl.sample.resources.MsProcessorBinder;
import com.shl.sample.resources.ProcessorResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

/**
 * Created by yshuliga on 06.11.2015.
 */
public class MsProcessorApp extends Application<MsProcessorConfiguration> {

    public static void main(String[] args) throws Exception {
        new MsProcessorApp().run(args);
    }

    @Override
    public void run(MsProcessorConfiguration configuration, Environment environment) throws Exception {
        final ProcessorResource resource = new ProcessorResource(
                configuration.getProcessorRoot(),
                configuration.getImagePrefix()
        );
        environment.jersey().register(resource);
        environment.jersey().register(IIOExceptionInterceptor.class);
        environment.jersey().getResourceConfig().register(new MsProcessorBinder());
    }

    @Override
    public void initialize(Bootstrap<MsProcessorConfiguration> bootstrap) {
    }

}
