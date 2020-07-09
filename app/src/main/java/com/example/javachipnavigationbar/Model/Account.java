package com.example.javachipnavigationbar.Model;

public class Account {
    private String username;
    private String email;
    private String password;
    private String cofipassword;

    public Account() {
    }

    public Account(String username, String email, String password, String cofipassword) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.cofipassword = cofipassword;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCofipassword() {
        return cofipassword;
    }

    public void setCofipassword(String cofipassword) {
        this.cofipassword = cofipassword;
    }
}


