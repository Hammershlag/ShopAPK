package com.example.shopapk.Classes;

public class UserInfo {

    private String username, name, surname, phone_number;
    private int _id;

    public UserInfo() {}
    public UserInfo(int id, String username, String name, String surname, String phone_number)
    {
        this.username = username;
        this._id = id;
        this.name = name;
        this.surname = surname;
        this.phone_number = phone_number;
    }

    public UserInfo(String username, String name)
    {
        this.username = username;

    }

    public String getUsername() {
        return this.username;
    }

    public int getId() {
        return this._id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setId(int id) {
        this._id = id;
    }

    public String getName()
    {
        return this.name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getSurname()
    {
        return this.surname;
    }

    public void setSurname(String surname)
    {
        this.surname = surname;
    }

    public String getPhone_number()
    {
        return this.phone_number;
    }

    public void setPhone_number(String phone_number)
    {
        this.phone_number = phone_number;
    }
}
