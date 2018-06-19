package uts.cli;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import uts.dataprovider.uts.dataprovider.connection.RedisPool;
import uts.dataprovider.uts.dataprovider.connection.RedispoolFactory;

import java.io.File;
import java.net.URL;

import static org.junit.Assert.assertEquals;

public class MainTest {
    @Test
    public void serverStartTest() throws Exception {
        System.setProperty("servicePort", "8990");
        URL url = getClass().getClassLoader().getResource("TestBootConfig.json");
        System.out.println("url path :  "+url.getPath());
        File file = new File(url.getPath());
        HttpServer srvr =  Main.runEmbeddedServer(new String[]{"bootConfig="+file.getPath()});

        System.out.println(" wait 2 secs after srv strt ");

        Thread.sleep(2000);

        System.out.println(" stop srvr");

        if(srvr != null){

            srvr.shutdown();
            //Thread.currentThread().join();
        }

    }

    @Test
    public void redisPoolTest() throws Exception {

        RedisPool  pool = RedispoolFactory.getInstance().getPool();

        try (Jedis jedis =  pool.getJedisPool().getResource()){
            // do operations with jedis resource
            System.out.println(" ### trying hget");
            jedis.set("sunnyTestKey", "sunnyTestValue");

            String ret = jedis.get("sunnyTestKey");

            System.out.println(" ### trying hget return : "+ret);
            System.out.println(" get response : "+ret);

            assertEquals(ret, "sunnyTestValue");


        }catch (Exception e){
            System.out.println("Exception: "+e.getLocalizedMessage());
            e.printStackTrace();
        }
    }

}
