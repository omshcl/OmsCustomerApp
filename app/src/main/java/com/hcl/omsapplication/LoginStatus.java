package com.hcl.omsapplication;

public class LoginStatus {
    public boolean isValid;

    public boolean isAdmin;

    public LoginStatus(Boolean isValid, Boolean isAdmin){
        this.isValid = isValid;
        this.isAdmin = isAdmin;
    }
}
