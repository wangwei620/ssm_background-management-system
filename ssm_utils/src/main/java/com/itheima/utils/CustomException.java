package com.itheima.utils;

public class CustomException extends Exception {

    private String errorCode ;
    private String errorMsg;

    public CustomException(String errorCode,String errorMs){
        this.errorCode=errorCode;
        this.errorMsg=errorMs;
    }

    @Override
    public String getMessage() {
        return this.errorMsg;
    }
}
