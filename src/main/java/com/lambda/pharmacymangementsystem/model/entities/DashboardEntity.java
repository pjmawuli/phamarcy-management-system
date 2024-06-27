package com.lambda.pharmacymangementsystem.model.entities;

public class DashboardEntity {
    private int total_drugs;
    private int total_suppliers;
    private int total_purchases;
    private int total_low_in_stock_drugs;
    private int total_today_purchases;

    public DashboardEntity() {
    }

    public DashboardEntity(int total_drugs, int total_suppliers, int total_purchases, int total_low_in_stock_drugs, int total_today_purchases) {
        this.total_drugs = total_drugs;
        this.total_suppliers = total_suppliers;
        this.total_purchases = total_purchases;
        this.total_low_in_stock_drugs = total_low_in_stock_drugs;
        this.total_today_purchases = total_today_purchases;
    }

    public int getTotalDrugs() {
        return total_drugs;
    }

    public void setTotalDrugs(int total_drugs) {
        this.total_drugs = total_drugs;
    }

    public int getTotalSuppliers() {
        return total_suppliers;
    }

    public void setTotalSuppliers(int total_suppliers) {
        this.total_suppliers = total_suppliers;
    }

    public int getTotalPurchases() {
        return total_purchases;
    }

    public void setTotalPurchases(int total_purchases) {
        this.total_purchases = total_purchases;
    }

    public int getTotalLowInStockDrugs() {
        return total_low_in_stock_drugs;
    }

    public void setTotalLowInStockDrugs(int total_low_in_stock_drugs) {
        this.total_low_in_stock_drugs = total_low_in_stock_drugs;
    }

    public int getTotalTodayPurchases() {
        return total_today_purchases;
    }

    public void setTotalTodayPurchases(int total_today_purchases) {
        this.total_today_purchases = total_today_purchases;
    }
}
