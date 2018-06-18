package uts.cli;


import org.glassfish.grizzly.http.server.Request;
import org.glassfish.hk2.api.ServiceLocator;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Singleton
@Path("/uts/test")
public class Test {

    private ServiceLocator sl;


    public static final String CONTENT_TYPE_JSON_UTF8 = MediaType.APPLICATION_JSON + "; charset=utf-8";
    @Inject
    public Test(ServiceLocator sl){
        super();
        this.sl = sl;

    }

    @GET
    @Consumes("application/x-www-form-urlencoded")
    @Produces(CONTENT_TYPE_JSON_UTF8)
    public Response get(@Context HttpHeaders httpHeaders, @Context Request httpReq) {

       return Response.ok(" This is the Url Threat Service Test").build();

    }


}
