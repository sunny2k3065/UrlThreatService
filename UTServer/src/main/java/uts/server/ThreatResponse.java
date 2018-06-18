package uts.server;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY) public class ThreatResponse {

    private Boolean isThreat;

    private Boolean isHttpAllowed;

    private Boolean isGetAllowed;

    private Boolean isPostAllowed;

}
