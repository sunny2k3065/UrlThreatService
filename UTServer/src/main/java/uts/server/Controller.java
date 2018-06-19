package uts.server;

import org.glassfish.hk2.api.ServiceLocator;
import org.jvnet.hk2.annotations.Service;
import uts.server.handler.ThreatHandler;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import java.io.IOException;

import static uts.cli.Test.CONTENT_TYPE_JSON_UTF8;

@Singleton
@Path("/uts/{version}")
public class Controller {

    private ServiceLocator sl;

    @Inject
    public Controller(ServiceLocator sl) {
        super();
        this.sl = sl;

    }

    @GET
    @Path("/{host_port}/{path}")
    @Consumes("application/x-www-form-urlencoded")
    @Produces(CONTENT_TYPE_JSON_UTF8)
    public ThreatResponse get(
            @PathParam("host_port") String hostPort, @PathParam("path") String path,
            @PathParam("version") String version, @Context UriInfo uriInfo) throws IOException {

        version = "V" + version;

        String query = uriInfo.getRequestUri().getRawQuery();
        String pathQuery = (query!= null && query.length() >0)? new StringBuffer(path).append("?").append(query).toString(): path;


        ThreatHandler th = sl.getService(ThreatHandler.class, version);


        return th.handleGet(hostPort, pathQuery);

    }

    @POST
    @Path("/threaturl")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(CONTENT_TYPE_JSON_UTF8)
    public ThreatResponse post(ThreatRequest request,
            @PathParam("version") String version) throws IOException {


        version = "V" + version;

        return sl.getService(ThreatHandler.class, version).handlePost(request, request.getHostPort(), request.getPathQuery());
    }

}
