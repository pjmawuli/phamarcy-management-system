package com.lambda.pharmacymangementsystem.model.entities;

public class DrugEntity {
    private int id;
    private String name;
    private String drug_code;
    private int quantity;
    private double price;
    private int supplier_id;
    private String created_at;
    private String updated_at;

    //    default constructor with id
    public DrugEntity(int id, String name, String drug_code, int quantity, double price, int supplier_id, String created_at, String updated_at) {
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

    public String getCreatedAt() {
        return created_at;
    }

    public void setCreatedAt(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdatedAt() {
        return updated_at;
    }

    public void setUpdatedAt(String updated_at) {
        this.updated_at = updated_at;
    }
}
