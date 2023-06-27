package com.managementemployee.admin.common.exception;

import java.time.LocalTime;

public class InvalidTimeInException extends Exception{
    private LocalTime invalidTimeIn;

    public InvalidTimeInException(){

    }

    public InvalidTimeInException(LocalTime invalidTimeIn){
        this.invalidTimeIn = invalidTimeIn;
    }

    public InvalidTimeInException(String message, LocalTime invalidTimeIn){
        super(message);
        this.invalidTimeIn = invalidTimeIn;
    }

    public LocalTime getInvalidTimeIn() {
        return invalidTimeIn;
    }
}
