package com.lambda.pharmacymangementsystem.model.entities;


public class SupplierEntities {
    public int id;
    public String name;
    public String contact;
    public String location;

//    define constructor
    public SupplierEntities(String name, String contact, String location) {
        this.name = name;
        this.contact = contact;
        this.location = location;
    }
    
}