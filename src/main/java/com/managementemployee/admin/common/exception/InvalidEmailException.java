package com.managementemployee.admin.common.exception;

public class InvalidEmailException extends  Exception{
    private String invalidEmail;

    public InvalidEmailException() {

    }
    public InvalidEmailException(String message, String invalidEmail){
        super(message);
        this.invalidEmail = invalidEmail;
    }

    public String getInvalidEmail() {
        return invalidEmail;
    }

}
