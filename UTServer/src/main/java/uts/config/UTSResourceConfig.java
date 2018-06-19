package uts.config;

import uts.cli.Test;
import org.glassfish.jersey.server.ResourceConfig;
import uts.server.Controller;

public class UTSResourceConfig extends ResourceConfig {


    public UTSResourceConfig(){


        register(Test.class);
        register(Controller.class);

    }



}
