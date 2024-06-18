package com.lambda.pharmacymangementsystem.model.entities;


public class SupplierEntity {
    private int id;
    private String name;
    private int contact;
    private String location;
    private String created_at;
    private String updated_at;

    //    default constructor with id
    public SupplierEntity(int id, String name, int contact, String location, String created_at, String updated_at) {
        this(name, contact, location);
        this.id = id;
        this.created_at = created_at;
        this.updated_at = updated_at;
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

    public String getCreatedAt() {
        return created_at;
    }

    public void setCreatedAt() {
        this.created_at = created_at;
    }

    public String getUpdatedAt() {
        return updated_at;
    }

    public void setUpdatedAt() {
        this.updated_at = updated_at;
    }
}