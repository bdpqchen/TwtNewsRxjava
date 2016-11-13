package com.example.cdc.twtnewsrxjava.bean;

import java.io.Serializable;

/**
 * Created by cdc on 16-11-13.
 */

public class ApiResponse<T> {

    private int error_code;
    private String message;
    private T data;

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }



}
