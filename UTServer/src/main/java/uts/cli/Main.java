package uts.cli;

import org.glassfish.grizzly.http.server.HttpServer;
import uts.config.BootConfig;
import uts.config.BootConfigLoader;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    private final static Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) throws Exception {

        runEmbeddedServer(args);
    }

    public static HttpServer runEmbeddedServer(String[] args) throws Exception {

        System.out.println("Starting Service....");

        String fileLocation = getFileLocFromArg(args[0]);
        HttpServer srv = null;
        try {
            BootConfig config = BootConfigLoader.getBootstrapConfig(fileLocation);
            srv = EmbeddedServer.getInstance().run(config);
        } catch (IOException e) {
            logger.log(Level.FINE, "Exception", e);
            throw e;
        } catch (InterruptedException e) {
            logger.log(Level.FINE, "Exception", e);
            throw e;
        }

        System.out.println("Service Started");
        logger.log(Level.FINE, "Service Started");

        return srv;

    }

    private static String getFileLocFromArg(String arg) {

        String[] loc = arg.split("=");

        if (loc != null && loc.length == 2 && loc[0].trim().equals("bootConfig")) {
            return loc[1];
        } else {
            throw new IllegalArgumentException(" unknown args  passed: " + arg
                    + "  , need the boot config location in format bootConfig=<absolute file location> ");
        }

    }

}
