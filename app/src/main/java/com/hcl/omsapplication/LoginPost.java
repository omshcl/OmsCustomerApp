package com.hcl.omsapplication;

public class LoginPost {

    private String username;

    private String password;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public LoginPost(String username, String password){
        this.username = username;
        this.password = password;
    }
}
