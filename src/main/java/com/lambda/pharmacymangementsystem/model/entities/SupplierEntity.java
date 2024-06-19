package com.lambda.pharmacymangementsystem.model.entities;


import java.time.LocalDateTime;

public class SupplierEntity {
    private int id;
    private String name;
    private int contact;
    private String location;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    //    default constructor with id
    public SupplierEntity(int id, String name, int contact, String location, LocalDateTime created_at, LocalDateTime updated_at) {
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

    public LocalDateTime getCreatedAt() {
        return created_at;
    }

    public void setCreatedAt(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public LocalDateTime getUpdatedAt() {
        return updated_at;
    }

    public void setUpdatedAt(LocalDateTime updated_at) {
        this.updated_at = updated_at;
    }
}