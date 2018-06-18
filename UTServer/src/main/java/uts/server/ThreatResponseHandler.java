package uts.server;

import org.glassfish.hk2.api.ServiceLocator;
import uts.dataprovider.DataProvider;
import uts.dataprovider.impl.CachedDataProvider;
import uts.dataprovider.impl.RedisDataProvider;
import uts.server.json.PayloadHandler;

import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;

@Singleton
@Path("/uts/{version}/{host_port}/{path_query}")
public class ThreatResponseHandler {


    private ServiceLocator sl;
    private DataProvider dp;
    private CachedDataProvider cdp;

    public ThreatResponseHandler(ServiceLocator sl){

        this.sl = sl;
        this.dp = sl.getService(RedisDataProvider.class);
        cdp = new CachedDataProvider(dp,sl);

    }


    @GET
    @Consumes("application/x-www-form-urlencoded")
    @Produces(MediaType.APPLICATION_JSON + "; charset=utf-8")
    public ThreatResponse get(@PathParam("host_port") String hostPort,@PathParam("path_query") String pathQuery)
            throws IOException {


            return getThreatData(hostPort,pathQuery,cdp);


    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON + "; charset=utf-8")
    public ThreatResponse post(ThreatRequest request, @PathParam("host_port") String hostPort,@PathParam("path_query") String pathQuery)
            throws IOException {

        return writeThreatData(hostPort,pathQuery,request,cdp);
    }


    public  ThreatResponse getThreatData(String hostPort, String pathQuery, DataProvider provider ) throws IOException {

        return PayloadHandler.parseResponseJson(provider.getProviderThreatData(hostPort, pathQuery));

    }


    public  boolean writeThreatData(String hostPort, String pathQuery, ThreatRequest request, DataProvider provider ) throws IOException {

        return provider. writeProviderThreatData(hostPort, pathQuery, PayloadHandler.generateResponseJson(request));

    }



}
