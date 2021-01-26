package com.example.shopapk.Classes;

public class Product {

    private String _name, _description;
    private int _id;

    public Product() {}
    public Product(int id, String name, String description)
    {
        this._name = name;
        this._id = id;
        this._description = description;
    }

    public Product(String name, String description)
    {
        this._name = name;
        this._description = description;
    }

    public String getName() {
        return this._name;
    }

    public int getId() {
        return this._id;
    }

    public String getDescription() {
        return this._description;
    }

    public void setName(String name) {
        this._name = name;
    }

    public void setId(int id) {
        this._id = id;
    }

    public void setDescription(String description) {
        this._description = description;
    }
}
