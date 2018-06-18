package cli;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    private final static Logger logger = Logger.getLogger(Main.class.getName());
    public static void main(String[] args) {

        System.out.println("Starting Service....");

        try {
            EmbeddedServer.getInstance().run();
        } catch (IOException e) {
            logger.log(Level.FINE, "Exception", e);
        } catch (InterruptedException e) {
            logger.log(Level.FINE, "Exception", e);
        }

        System.out.println("Service Started");
        logger.log(Level.FINE, "Service Started");


    }


}
