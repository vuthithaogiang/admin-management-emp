package com.managementemployee.admin.employee.model;

import com.managementemployee.admin.common.exception.InvalidEmailException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmployeeEmailOnly {

    private String email;

    public EmployeeEmailOnly() {

    }

    public String getEmail() { return  email;}

    public void setEmail(String email) throws InvalidEmailException {
        var regex = "^[a-z]+[\\w._]*@gmail.com$";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        if(matcher.matches()){
            this.email = email.toLowerCase();
        }
        else{
            this.email = null;
            var msg = "Email is invalid!";
            throw new InvalidEmailException(msg, email);
        }
    }
}
