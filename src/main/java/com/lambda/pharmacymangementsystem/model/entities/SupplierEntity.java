package com.lambda.pharmacymangementsystem.model.entities;


public class SupplierEntity {
    private int id;
    private String name;
    private int contact;
    private String location;

    //    default constructor with id
    public SupplierEntity(int id, String name, int contact, String location) {
        this(name, contact, location);
        this.id = id;
    }

    //    define constructor
    public SupplierEntity(String name, int contact, String location) {
        this.name = name;
        this.contact = contact;
        this.location = location;
    }

    public int getId() {
        return id;
    }

    //    define getters and setters
    public void setId(int Id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getContact() {
        return contact;
    }

    public void setContact(int contact) {
        this.contact = contact;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}