package com.lambda.pharmacymangementsystem.model.entities;

import java.time.LocalDateTime;

public class PurchaseViewEntity {
    public static String toString;
    private int id;
    private String purchase_code;
    private int quantity;
    private double total_price;
    private String customer_name;
    private LocalDateTime created_at;
    private int drug_id;
    private String drug_name;
    private String drug_code;
    private int drug_quantity;
    private double drug_price;
    private int drug_supplier_id;
    private LocalDateTime drug_created_at;
    private LocalDateTime drug_updated_at;

    //    constructor
    public PurchaseViewEntity(
            int id,
            String purchase_code,
            int quantity,
            double total_price,
            String customer_name,
            LocalDateTime created_at,
            int drug_id,
            String drug_name,
            String drug_code,
            int drug_quantity,
            double drug_price,
            int drug_supplier_id,
            LocalDateTime drug_created_at,
            LocalDateTime drug_updated_at
    ) {

        this.id = id;
        this.purchase_code = purchase_code;
        this.quantity = quantity;
        this.total_price = total_price;
        this.customer_name = customer_name;
        this.created_at = created_at;
        this.drug_id = drug_id;
        this.drug_name = drug_name;
        this.drug_code = drug_code;
        this.drug_quantity = drug_quantity;
        this.drug_price = drug_price;
        this.drug_supplier_id = drug_supplier_id;
        this.drug_created_at = drug_created_at;
        this.drug_updated_at = drug_updated_at;
    }


    public int getId() {
        return id;
    }

    //    define getters and setters
    public void setId(int id) {
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

    public LocalDateTime getCreatedAt() {
        return created_at;
    }

    public void setCreatedAt(LocalDateTime created_at) {
        this.created_at = created_at;
    }


    //    drug reference

    public int getDrugId() {
        return drug_id;
    }

    public void setDrugId(int drug_id) {
        this.drug_id = drug_id;
    }


    public String getDrugName() {
        return drug_name;
    }

    public void setDrugName(String drug_name) {
        this.drug_name = drug_name;
    }

    public String getDrugCode() {
        return drug_code;
    }

    public void setDrugCode(String drug_code) {
        this.drug_code = drug_code;
    }

    public int getDrugQuantity() {
        return drug_quantity;
    }

    public void setDrugQuantity(int drug_quantity) {
        this.drug_quantity = drug_quantity;
    }

    public double getDrugPrice() {
        return drug_price;
    }

    public void setDrugPrice(double drug_price) {
        this.drug_price = drug_price;
    }

    public int getDrugSupplierId() {
        return drug_supplier_id;
    }

    public void setDrugSupplierId(int drug_supplier_id) {
        this.drug_supplier_id = drug_supplier_id;
    }

    public LocalDateTime getDrugCreatedAt() {
        return drug_created_at;
    }

    public void setDrugCreatedAt(LocalDateTime drug_created_at) {
        this.drug_created_at = drug_created_at;
    }

    public LocalDateTime getDrugUpdatedAt() {
        return drug_updated_at;
    }

    public void setDrugUpdatedAt(LocalDateTime drug_updated_at) {
        this.drug_updated_at = drug_updated_at;
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
                "\n  purchase_code=" + purchase_code +
                "\n  quantity=" + quantity +
                "\n  total_price=" + total_price +
                ",\n  customer_name='" + customer_name + '\'' +
                ",\n  created_at='" + created_at + '\'' +
                ",\n  drug_code='" + drug_code + '\'' +
                ",\n  drug_quantity=" + drug_quantity +
                ",\n  drug_price=" + drug_price +
                ",\n  drug_created_at=" + drug_created_at +
                ",\n  drug_updated_at=" + drug_updated_at +
                ",\n  drug_supplier_id=" + drug_supplier_id +
                "\n}";
    }
}
