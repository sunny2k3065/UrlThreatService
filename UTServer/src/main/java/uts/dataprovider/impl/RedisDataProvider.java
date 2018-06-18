package uts.dataprovider.impl;

import org.glassfish.hk2.api.ServiceLocator;
import org.jvnet.hk2.annotations.Service;
import redis.clients.jedis.Jedis;
import uts.dataprovider.DataProvider;
import uts.dataprovider.uts.dataprovider.connection.RedisPool;

@Service
public class RedisDataProvider extends DataProvider {

    ServiceLocator sl;

    public RedisDataProvider(ServiceLocator sl){
        this.sl = sl;
    }

    @Override public String getProviderThreatData(String hostPort, String pathQuery) {

        RedisPool pool = sl.getService(RedisPool.class);
        try (Jedis jedis =  pool.getJedisPool().getResource()){
            // do operations with jedis resource

            return jedis.hget(hostPort, pathQuery);

        }


    }

    @Override public boolean writeProviderThreatData(String hostPort, String pathQuery, String json) {

        RedisPool pool = sl.getService(RedisPool.class);
        try (Jedis jedis =  pool.getJedisPool().getResource()){
            // do operations with jedis resource

             jedis.hset(hostPort, pathQuery, json);
             return true;

        }

    }
}
