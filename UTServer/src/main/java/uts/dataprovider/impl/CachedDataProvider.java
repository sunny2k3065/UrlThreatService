package uts.dataprovider.impl;

import org.glassfish.hk2.api.ServiceLocator;
import uts.dataprovider.DataProvider;

import java.util.HashMap;

public class CachedDataProvider extends DataProvider{

    DataProvider dp;
    ServiceLocator sl;

    private HashMap<String,HashMap<String,String>> elasticCache = new HashMap<String,HashMap<String,String>>();

    // TODO: externalize to config or calculate based on heap size
    private static int MAX_CACHE_SIZE = 2000;

    public CachedDataProvider(DataProvider dp, ServiceLocator sl) {

        this.sl = sl;
        this.dp = dp;
    }

    @Override public String getProviderThreatData(String hostPort, String pathQuery) {

        String json;
        HashMap<String,String> domainMap = elasticCache.get(hostPort);
        if(domainMap == null){
            domainMap = new HashMap<String,String>();
            elasticCache.put(hostPort, domainMap);
        }

            json  = domainMap.get(pathQuery);
            if(json == null){

                json = dp.getProviderThreatData(hostPort,pathQuery);

                if(json != null || !json.isEmpty()){

                    domainMap.put(pathQuery, json);
                }


            }

            return json;


    }



    @Override public boolean writeProviderThreatData(String hostPort, String pathQuery, String json) {
        return dp.writeProviderThreatData(hostPort, pathQuery, json);
    }
}
