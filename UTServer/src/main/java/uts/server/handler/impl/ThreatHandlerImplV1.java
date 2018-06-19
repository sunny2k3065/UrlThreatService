package uts.server.handler.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.glassfish.hk2.api.ServiceLocator;
import org.jvnet.hk2.annotations.Service;
import uts.dataprovider.DataProvider;
import uts.dataprovider.impl.CachedDataProvider;
import uts.dataprovider.impl.RedisDataProvider;
import uts.server.ThreatRequest;
import uts.server.ThreatResponse;
import uts.server.handler.ThreatHandler;
import uts.server.json.PayloadHandler;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import java.io.IOException;

@Service
@Singleton
@Named("V1")
public class ThreatHandlerImplV1 implements ThreatHandler {


    private ServiceLocator sl;
    private DataProvider dp;
    private CachedDataProvider cdp;

    private ThreatResponse noDataResponse;

    @Inject
    public ThreatHandlerImplV1(ServiceLocator sl){

        this.sl = sl;
        this.dp = sl.getService(DataProvider.class);
        cdp = new CachedDataProvider(dp,sl);
        noDataResponse = getNoDataThreatResponse();
        System.out.println(" ######### threathandler v1 construction");

    }

    private ThreatResponse getNoDataThreatResponse(){

        ThreatResponse tr = new ThreatResponse();
        tr.setThreatIndex(0);
        return tr;
    }



    @Override public ThreatResponse handleGet(String hostPort, String pathQuery) throws IOException {
        System.out.println(" ####rchd here hostport: "+hostPort + " pathquery: "+pathQuery);
        return getThreatData(hostPort,pathQuery,cdp);
    }

    @Override public ThreatResponse handlePost(ThreatRequest request, String hostPort, String pathQuery)
            throws IOException {
        boolean success = writeThreatData(hostPort,pathQuery,request,cdp);


        ThreatResponse tr = new ThreatResponse();
        tr.setWriteSuccess(success);
        return tr;
    }


    public  ThreatResponse getThreatData(String hostPort, String pathQuery, DataProvider provider ) throws IOException {

        String json = null;
        try {
            json = provider.getProviderThreatData(hostPort, pathQuery);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(" json string read :"+json);
        if(json == null || json.isEmpty()){
            return noDataResponse;
        }
        return new ThreatResponse(PayloadHandler.parseJson(json));

    }



    public  boolean writeThreatData(String hostPort, String pathQuery, ThreatRequest request, DataProvider provider ) throws IOException {
        System.out.println(" ######### In handle post!!");
        boolean bool = false;
        try {
            String json  = PayloadHandler.generateJson(request);

            bool = provider. writeProviderThreatData(hostPort, pathQuery, json);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return bool;

    }


}
