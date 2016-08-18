package com.systems.network_manager.data_model;

import java.io.Serializable;

/**
 * @author Attiq ur Rehman on 04/07/2016.
 */
public class Response implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 3396475472528600424L;

    public int getStatusCode() {
        return StatusCode;
    }

    public void setStatusCode(int statusCode) {
        StatusCode = statusCode;
    }

    public String getStatusMessage() {
        return StatusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        StatusMessage = statusMessage;
    }

    public Object getData() {
        return Data;
    }

    public void setData(Object data) {
        Data = data;
    }

    public void setCode(Resp code) {
        this.code = code;
    }

    public Resp getCode() {
        return code;
    }

    Resp code = Resp.FAILURE;
    int StatusCode;
    String StatusMessage = "Unable to process the request";
    Object Data;

    public enum Resp {
        SUCCESS, FAILURE;
    }
}
