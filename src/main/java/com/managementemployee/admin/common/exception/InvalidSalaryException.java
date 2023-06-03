package com.managementemployee.admin.common.exception;



public class InvalidSalaryException extends Exception {
    private float invalidSalary;

    public InvalidSalaryException() {

    }
    public InvalidSalaryException(String msg, float invalidSalary){
        super(msg);
        this.invalidSalary = invalidSalary;
    }

    public float getInvalidSalary() {
        return invalidSalary;
    }
}
