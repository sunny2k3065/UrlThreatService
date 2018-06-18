package uts.config;

import uts.cli.Test;
import org.glassfish.jersey.server.ResourceConfig;

public class UTSResourceConfig extends ResourceConfig {


    public UTSResourceConfig(){


        register(Test.class);

    }



}
