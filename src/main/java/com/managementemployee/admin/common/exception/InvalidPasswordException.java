package com.managementemployee.admin.common.exception;

public class InvalidPasswordException extends  Exception{
    private String invalidPassword;

    public InvalidPasswordException() {

    }

    public InvalidPasswordException(String invalidPassword){
        this.invalidPassword = invalidPassword;
    }

    public InvalidPasswordException(String message, String invalidPassword){
        super(message);
        this.invalidPassword = invalidPassword;
    }

    public String getInvalidPassword() {
        return invalidPassword;
    }
}
