package com.shl.sample.resources;

import com.codahale.metrics.annotation.Timed;
import com.shl.sample.SampleProcessor;
import com.shl.sample.qualifiers.Prefix;
import com.shl.sample.qualifiers.Root;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

/**
 * Created by yshuliga on 06.11.2015.
 */

@Path("/processor")
public class ProcessorResource {

    @Inject
    SampleProcessor sampleProcessor;

    public static final String TYPE_EXTENSION = ".jpg";
    private String rootPath;
    private String prefix;

    public ProcessorResource() {
    }

    public ProcessorResource(String rootPath, String prefix) {
        this.rootPath = rootPath;
        this.prefix = prefix;
    }

    @POST
    @Timed
    @Path("/process/{index}")
    public Response process(@PathParam("index") Integer index) throws IOException {
        sampleProcessor.process(getFile("in", index), getFile("out", index));
        return Response.ok().build();
    }

    private File getFile(String folder, int index) {
        return Paths.get(rootPath, folder, getFileName(index)).toFile();
    }

    private String getFileName(int index) {
        return prefix + index + ".jpg";
    }
}
