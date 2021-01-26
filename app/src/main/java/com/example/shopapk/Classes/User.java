package com.example.shopapk.Classes;

public class User {

    private String username, password;
    private int _id;

    public User() {}
    public User(int id, String username, String password)
    {
        this.username = username;
        this._id = id;
        this.password = password;
    }

    public User(String username, String password)
    {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return this.username;
    }

    public int getId() {
        return this._id;
    }

    public String getPassword() {
        return this.password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setId(int id) {
        this._id = id;
    }

    public void setPassword(String description) {
        this.password = description;
    }

}
