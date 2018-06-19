package uts.cli;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.Test;

import java.io.File;
import java.net.URL;

public class MainTest {
    @Test
    public void main() throws Exception {
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
        }

    }

}
