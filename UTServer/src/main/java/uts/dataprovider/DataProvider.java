package uts.dataprovider;

import org.glassfish.jersey.spi.Contract;
import uts.server.ThreatRequest;
import uts.server.ThreatResponse;
import uts.server.json.PayloadHandler;

import java.io.IOException;

@Contract
public abstract class DataProvider {




    public abstract String getProviderThreatData(String hostPort, String pathQuery);




    public abstract boolean writeProviderThreatData(String hostPort, String pathQuery, String json);



}
