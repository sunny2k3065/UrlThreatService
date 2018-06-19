package uts.server;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY) public class ThreatResponse extends ThreatRequest{

    public Boolean getWriteSuccess() {
        return writeSuccess;
    }

    public void setWriteSuccess(Boolean writeSuccess) {
        this.writeSuccess = writeSuccess;
    }

    private Boolean writeSuccess;

}
