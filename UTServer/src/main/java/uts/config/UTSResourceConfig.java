package uts.config;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import uts.cli.Test;
import org.glassfish.jersey.server.ResourceConfig;
import uts.dataprovider.DataProvider;
import uts.dataprovider.impl.RedisDataProvider;
import uts.dataprovider.uts.dataprovider.connection.RedisPool;
import uts.server.Controller;
import uts.server.UtsExceptionMapper;
import uts.server.handler.ThreatHandler;
import uts.server.handler.impl.ThreatHandlerImplV1;

import javax.inject.Singleton;

public class UTSResourceConfig extends ResourceConfig {


    public UTSResourceConfig(){


        register(Test.class);
        register(Controller.class);
        register(UtsExceptionMapper.class);


        register(new AbstractBinder() {
            @Override
            protected void configure() {
                bind(RedisDataProvider.class).to(DataProvider.class).in(Singleton.class);

            }
        });
        register(new AbstractBinder() {
            @Override
            protected void configure() {

                bind(ThreatHandlerImplV1.class).named("V1").to(ThreatHandler.class).in(Singleton.class);

            }
        });



    }



}
