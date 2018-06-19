package uts.server;

import org.glassfish.hk2.api.ServiceLocator;
import uts.server.handler.ThreatHandler;

import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

@Singleton @Path("/uts/{version}/{host_port}/{path_query}") public class Controller {

    private ServiceLocator sl;

    public Controller(ServiceLocator sl) {

        this.sl = sl;

    }

    @GET @Consumes("application/x-www-form-urlencoded") @Produces(
            MediaType.APPLICATION_JSON + "; charset=utf-8") public ThreatResponse get(
            @PathParam("host_port") String hostPort, @PathParam("path_query") String pathQuery,
            @PathParam("version") String version) throws IOException {

        version = "V" + version;

        return sl.getService(ThreatHandler.class, version).handleGet(hostPort, pathQuery);

    }

    @POST @Consumes(MediaType.APPLICATION_JSON) @Produces(
            MediaType.APPLICATION_JSON + "; charset=utf-8") public ThreatResponse post(ThreatRequest request,
            @PathParam("host_port") String hostPort, @PathParam("path_query") String pathQuery,
            @PathParam("version") String version) throws IOException {

        version = "V" + version;

        return sl.getService(ThreatHandler.class, version).handlePost(request, hostPort, pathQuery);
    }

}
