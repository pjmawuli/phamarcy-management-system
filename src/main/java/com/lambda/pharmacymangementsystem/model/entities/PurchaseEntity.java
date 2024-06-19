package com.lambda.pharmacymangementsystem.model.entities;

import java.time.LocalDateTime;

public class PurchaseEntity {
    private int id;
    private String purchase_code;
    private int quantity;
    private double total_price;
    private String customer_name;
    private int drug_id;
    private LocalDateTime created_at;

    //    default constructor with id
    public PurchaseEntity(int id, String purchase_code, int quantity, double total_price, String customer_name, int drug_id, LocalDateTime created_at) {
        this(purchase_code, quantity, total_price, customer_name, drug_id);
        this.id = id;
        this.created_at = created_at;
    }

    //    define constructor
    public PurchaseEntity(String purchase_code, int quantity, double total_price, String customer_name, int drug_id) {
        this.purchase_code = purchase_code;
        this.quantity = quantity;
        this.total_price = total_price;
        this.customer_name = customer_name;
        this.drug_id = drug_id;
    }

    public int getId() {
        return id;
    }

    //    define getters and setters
    public void setId(int Id) {
        this.id = id;
    }

    public String getPurchaseCode() {
        return purchase_code;
    }

    public void setPurchaseCode(String purchase_code) {
        this.purchase_code = purchase_code;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return total_price;
    }

    public void setTotalPrice(double total_price) {
        this.total_price = total_price;
    }

    public String getCustomerName() {
        return customer_name;
    }

    public void setCustomerName(String customer_name) {
        this.customer_name = customer_name;
    }

    public int getDrugId() {
        return drug_id;
    }

    public void setDrugId(int drug_id) {
        this.drug_id = drug_id;
    }

    public LocalDateTime getCreatedAt() {
        return created_at;
    }

    public void setCreatedAt(LocalDateTime created_at) {
        this.created_at = created_at;
    }

}
