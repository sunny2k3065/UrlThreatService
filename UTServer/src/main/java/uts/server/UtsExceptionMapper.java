package uts.server;

import org.glassfish.hk2.api.ServiceLocator;

import javax.inject.Inject;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class UtsExceptionMapper  implements ExceptionMapper<java.lang.Exception> {

    private ServiceLocator sl;

    @Inject
    public UtsExceptionMapper(ServiceLocator sl) {
        super();
        this.sl = sl;

    }

    private static final String DEFAULT_MESSAGE = "{\"error\": \"internal_server_error\"}";

    public Response toResponse(Exception ex) {

     ex.printStackTrace();
     return prepareResponse(Response.Status.INTERNAL_SERVER_ERROR, ex.toString());
    }


    private Response prepareResponse(Response.Status status, String entity) {
        return Response.status(status)
                .entity(entity)
                .type(MediaType.TEXT_PLAIN)
                .header("Cache-Control", "no-cache, no-store, must-revalidate")
                .header("Pragma", "no-cache")
                .build();
    }


}

