package uts.server;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ThreatRequest {


    public Integer getThreatIndex() {
        return threatIndex;
    }

    public void setThreatIndex(int threatIndex) {
        this.threatIndex = threatIndex;
    }

    public Boolean getHttpAllowed() {
        return isHttpAllowed;
    }

    public void setHttpAllowed(Boolean httpAllowed) {
        isHttpAllowed = httpAllowed;
    }

    public Boolean getGetAllowed() {
        return isGetAllowed;
    }

    public void setGetAllowed(Boolean getAllowed) {
        isGetAllowed = getAllowed;
    }

    public Boolean getPostAllowed() {
        return isPostAllowed;
    }

    public void setPostAllowed(Boolean postAllowed) {
        isPostAllowed = postAllowed;
    }

    private Integer threatIndex; // 1 : is a threat, -1: not a threat, 0: no threat data at server

    private Boolean isHttpAllowed;

    private Boolean isGetAllowed;

    private Boolean isPostAllowed;


    private String hostPort;

    public String getHostPort() {
        return hostPort;
    }

    public void setHostPort(String hostPort) {
        this.hostPort = hostPort;
    }

    public String getPathQuery() {
        return pathQuery;
    }

    public void setPathQuery(String pathQuery) {
        this.pathQuery = pathQuery;
    }

    private String pathQuery;


}
