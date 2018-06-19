package uts.server.handler;

import org.jvnet.hk2.annotations.Contract;
import uts.server.ThreatRequest;
import uts.server.ThreatResponse;

import java.io.IOException;

@Contract
public interface ThreatHandler {

    public ThreatResponse handleGet(String hostPort,String pathQuery) throws IOException;

    public ThreatResponse handlePost(ThreatRequest request, String hostPort,String pathQuery) throws IOException;

}
