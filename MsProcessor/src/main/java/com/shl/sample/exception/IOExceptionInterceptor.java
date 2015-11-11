package com.shl.sample.exception;

import javax.imageio.IIOException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

/**
 * Created by yshuliga on 07.11.2015.
 */
@Provider
public class IOExceptionInterceptor implements ExceptionMapper<IOException> {

    public Response toResponse(IOException e) {
        return Response.status(Response.Status.NOT_FOUND).entity(e.getLocalizedMessage()).build();
    }
}
