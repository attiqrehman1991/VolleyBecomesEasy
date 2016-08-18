package com.systems.network_manager.data_model;

/**
 * @author Attiq ur Rehman on 04/07/2016.
 */
public class Resp {

    String status;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCurrent_state() {
        return current_state;
    }

    public void setCurrent_state(String current_state) {
        this.current_state = current_state;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    String message;
    String current_state;
}
