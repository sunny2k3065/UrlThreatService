package uts.dataprovider.impl;

import org.glassfish.hk2.api.ServiceLocator;
import org.jvnet.hk2.annotations.Service;
import redis.clients.jedis.Jedis;
import uts.dataprovider.DataProvider;
import uts.dataprovider.uts.dataprovider.connection.RedisPool;
import uts.dataprovider.uts.dataprovider.connection.RedispoolFactory;

import javax.inject.Inject;
import javax.inject.Singleton;

@Service
@Singleton
public class RedisDataProvider extends DataProvider {

    ServiceLocator sl;
    @Inject
    public RedisDataProvider(ServiceLocator sl){
        this.sl = sl;
    }

    @Override public String getProviderThreatData(String hostPort, String pathQuery) {

        RedisPool pool = RedispoolFactory.getInstance().getPool();

        try (Jedis jedis =  pool.getJedisPool().getResource()){

            String ret = jedis.hget(hostPort, pathQuery);


            return ret;

        }catch (Exception e){
            System.out.println("Exception: "+e.getLocalizedMessage());
            e.printStackTrace();
        }
        return null;

    }

    @Override public boolean writeProviderThreatData(String hostPort, String pathQuery, String json) {

        RedisPool pool = RedispoolFactory.getInstance().getPool();
        try (Jedis jedis =  pool.getJedisPool().getResource()){
            // do operations with jedis resource

            jedis.hset(hostPort, pathQuery, json);
             return true;

        }

    }
}
