package uts.server.handler.impl;

import org.glassfish.hk2.api.ServiceLocator;
import org.jvnet.hk2.annotations.Service;
import uts.dataprovider.DataProvider;
import uts.dataprovider.impl.CachedDataProvider;
import uts.dataprovider.impl.RedisDataProvider;
import uts.server.ThreatRequest;
import uts.server.ThreatResponse;
import uts.server.handler.ThreatHandler;
import uts.server.json.PayloadHandler;

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

    public ThreatHandlerImplV1(ServiceLocator sl){

        this.sl = sl;
        this.dp = sl.getService(RedisDataProvider.class);
        cdp = new CachedDataProvider(dp,sl);
        noDataResponse = getNoDataThreatResponse();

    }

    private ThreatResponse getNoDataThreatResponse(){

        ThreatResponse tr = new ThreatResponse();
        tr.setThreatIndex(0);
        return tr;
    }



    @Override public ThreatResponse handleGet(String hostPort, String pathQuery) throws IOException {
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

        String json = provider.getProviderThreatData(hostPort, pathQuery);

        if(json == null || json.isEmpty()){
            return noDataResponse;
        }
        return (ThreatResponse) PayloadHandler.parseJson(json);

    }



    public  boolean writeThreatData(String hostPort, String pathQuery, ThreatRequest request, DataProvider provider ) throws IOException {

        return provider. writeProviderThreatData(hostPort, pathQuery, PayloadHandler.generateJson(request));

    }


}
