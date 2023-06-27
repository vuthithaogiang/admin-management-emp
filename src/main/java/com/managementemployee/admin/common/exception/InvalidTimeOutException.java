package com.managementemployee.admin.common.exception;

import java.time.LocalTime;

public class InvalidTimeOutException extends  Exception{
    private LocalTime invalidTimeOut;

    public InvalidTimeOutException() {

    }

    public InvalidTimeOutException(LocalTime invalidTimeOut){
        this.invalidTimeOut = invalidTimeOut;
    }

    public InvalidTimeOutException(String message, LocalTime invalidTimeOut){
        super(message);
        this.invalidTimeOut = invalidTimeOut;
    }

    public LocalTime getInvalidTimeOut(){
        return invalidTimeOut;
    }
}
