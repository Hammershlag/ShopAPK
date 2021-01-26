package com.example.shopapk.Classes;

public class User {

    private String username, password;

    public User (String username, String password)
    {
        this.username = username;
        this.password = password;
    }

    public String getUsername()
    {
        return this.username;
    }

    public String getPassword()
    {
        return this.password;
    }

    public void setPassword(String newPass){
        this.password = newPass;
    }

}
