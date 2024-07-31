package com.lambda.pharmacymangementsystem.test;

import com.lambda.pharmacymangementsystem.model.entities.SupplierEntity;
import com.lambda.pharmacymangementsystem.model.functions.SupplierFunctions;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class SupplierFunctionsTest {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Choose a test function to run:");
            System.out.println("1: Add one supplier");
            System.out.println("2: Get one supplier");
            System.out.println("3: Update one supplier");
            System.out.println("4: Delete one supplier");
            System.out.println("5: Get all suppliers");
            System.out.println("6: Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    testAddOneSupplier();
                    break;
                case 2:
                    System.out.print("Enter supplier ID: ");
                    int getId = scanner.nextInt();
                    testGetOneSupplier(getId);
                    break;
                case 3:
                    System.out.print("Enter supplier ID: ");
                    int updateId = scanner.nextInt();
                    testUpdateOneSupplier(updateId);
                    break;
                case 4:
                    System.out.print("Enter supplier ID: ");
                    int deleteId = scanner.nextInt();
                    testDeleteOneSupplier(deleteId);
                    break;
                case 5:
                    testGetAllSuppliers();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void testAddOneSupplier() {
        try {
            SupplierEntity supplier = new SupplierEntity("Test Supplier", "0200000000", "Test Location");
            SupplierFunctions.addOneSupplier(supplier);
            System.out.println("Supplier added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void testGetOneSupplier(int id) {
        try {
            SupplierEntity supplier = SupplierFunctions.getOneSupplier(id);
            if (supplier != null) {
                System.out.println("Supplier found: " + supplier);
            } else {
                System.out.println("Supplier not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void testUpdateOneSupplier(int id) {
        try {
            SupplierEntity supplier = new SupplierEntity("Updated Supplier", "02000000098", "Updated Location");
            SupplierFunctions.updateOneSupplier(id, supplier);
            System.out.println("Supplier updated successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void testDeleteOneSupplier(int id) {
        try {
            SupplierFunctions.deleteOneSupplier(id);
            System.out.println("Supplier deleted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void testGetAllSuppliers() {
        try {
            List<SupplierEntity> suppliers = SupplierFunctions.getAllSuppliers();
            if (!suppliers.isEmpty()) {
                System.out.println("All suppliers:");
                for (SupplierEntity supplier : suppliers) {
                    System.out.println(supplier.getName());
                }
            } else {
                System.out.println("No suppliers found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
