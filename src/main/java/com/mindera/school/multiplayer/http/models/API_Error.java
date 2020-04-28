package com.mindera.school.multiplayer.http.models;

public class API_Error {

    private String message;
    private String error;


    public API_Error(String error, String message) {
        this.error = error;
        this.message = message;
    }
    public String getMessage() {
        return message;
    }
    public String getError() {
        return error;
    }


}