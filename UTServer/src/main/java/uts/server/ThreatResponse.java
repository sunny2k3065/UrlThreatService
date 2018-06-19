package uts.server;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ThreatResponse extends ThreatRequest{

    public ThreatResponse(ThreatRequest request) {
        super();

        this.setThreatIndex(request.getThreatIndex());
        this.setHttpAllowed(request.getHttpAllowed());
        this.setGetAllowed(request.getGetAllowed());
        this.setPostAllowed(request.getPostAllowed());

    }

    public ThreatResponse() {
        super();
    }

    public Boolean getWriteSuccess() {
        return writeSuccess;
    }

    public void setWriteSuccess(Boolean writeSuccess) {
        this.writeSuccess = writeSuccess;
    }

    private Boolean writeSuccess;

}
