package com.lambda.pharmacymangementsystem.model.entities;

import java.time.LocalDateTime;

public class DrugViewEntity {
    public String toString;
    private int id;
    private String name;
    private String drug_code;
    private int quantity;
    private double price;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
    private int supplier_id;
    private String supplier_name;
    private String supplier_contact;
    private String supplier_location;
    private LocalDateTime supplier_created_at;
    private LocalDateTime supplier_updated_at;


    //    constructor
    public DrugViewEntity(int id, String name, String drug_code, int quantity, double price, LocalDateTime created_at, LocalDateTime updated_at, int supplier_id, String supplier_name, String supplier_contact, String supplier_location, LocalDateTime supplier_created_at, LocalDateTime supplier_updated_at) {
        this.id = id;
        this.name = name;
        this.drug_code = drug_code;
        this.quantity = quantity;
        this.price = price;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.supplier_id = supplier_id;
        this.supplier_name = supplier_name;
        this.supplier_contact = supplier_contact;
        this.supplier_location = supplier_location;
        this.supplier_created_at = supplier_created_at;
        this.supplier_updated_at = supplier_updated_at;
    }

    public int getId() {
        return id;
    }

    //    define getters and setters
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDrugCode() {
        return drug_code;
    }

    public void setDrugCode(String drug_code) {
        this.drug_code = drug_code;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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

    public int getSupplierId() {
        return supplier_id;
    }

    public void setSupplierId(int supplier_id) {
        this.supplier_id = supplier_id;
    }

    public String getSupplierName() {
        return supplier_name;
    }

    public void setSupplierName(String supplier_name) {
        this.supplier_name = supplier_name;
    }

    public String getSupplierContact() {
        return supplier_contact;
    }

    public void setSupplierContact(String supplier_contact) {
        this.supplier_contact = supplier_contact;
    }

    public String getSupplierLocation() {
        return supplier_location;
    }

    public void setSupplierLocation(String supplier_location) {
        this.supplier_location = supplier_location;
    }

    public LocalDateTime getSupplierCreatedAt() {
        return supplier_created_at;
    }

    public void setSupplierCreatedAt(LocalDateTime supplier_created_at) {
        this.supplier_created_at = supplier_created_at;
    }

    public LocalDateTime getSupplierUpdatedAt() {
        return supplier_updated_at;
    }

    public void setSupplierUpdatedAt(LocalDateTime supplier_updated_at) {
        this.supplier_updated_at = supplier_updated_at;
    }

    /**
     * Hint: Use this method to nicely display the object properties in the log statement
     * Only use this is the test functions
     *
     * @return - the neatly formatted drug object
     */

    @Override
    public String toString() {
        return "Drug {" +
                "\n  id=" + id +
                ",\n  name='" + name + '\'' +
                ",\n  drug_code='" + drug_code + '\'' +
                ",\n  quantity=" + quantity +
                ",\n  price=" + price +
                ",\n  created_at=" + created_at +
                ",\n  updated_at=" + updated_at +
                ",\n  supplier_id=" + supplier_id +
                ",\n  supplier_name='" + supplier_name + '\'' +
                ",\n  supplier_contact='" + supplier_contact + '\'' +
                ",\n  supplier_location='" + supplier_location + '\'' +
                ",\n  supplier_created_at=" + supplier_created_at +
                ",\n  supplier_updated_at=" + supplier_updated_at +
                "\n}";
    }
}
