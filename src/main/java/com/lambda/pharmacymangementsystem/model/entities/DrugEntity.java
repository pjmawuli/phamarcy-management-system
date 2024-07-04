package com.lambda.pharmacymangementsystem.model.entities;

import java.time.LocalDateTime;

public class DrugEntity {
    private int id;
    private String name;
    private String drug_code;
    private int quantity;
    private double price;
    private int supplier_id;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    //    default constructor with id
    public DrugEntity(int id, String name, String drug_code, int quantity, double price, int supplier_id, LocalDateTime created_at, LocalDateTime updated_at) {
        this(name, drug_code, quantity, price, supplier_id);
        this.id = id;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    //    define constructor
    public DrugEntity(String name, String drug_code, int quantity, double price, int supplier_id) {
        this.name = name;
        this.drug_code = drug_code;
        this.quantity = quantity;
        this.price = price;
        this.supplier_id = supplier_id;
    }

    // define constructor for update
    public DrugEntity(String name, int quantity, double price, int supplier_id) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.supplier_id = supplier_id;
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

    public int getSupplierId() {
        return supplier_id;
    }

    public void setSupplierId(int supplier_id) {
        this.supplier_id = supplier_id;
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
