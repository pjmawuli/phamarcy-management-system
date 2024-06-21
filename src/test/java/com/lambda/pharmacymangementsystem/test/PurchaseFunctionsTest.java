package com.lambda.pharmacymangementsystem.test;

import com.lambda.pharmacymangementsystem.model.entities.PurchaseEntity;
import com.lambda.pharmacymangementsystem.model.entities.PurchaseViewEntity;
import com.lambda.pharmacymangementsystem.model.functions.PurchaseFunctions;
import com.lambda.pharmacymangementsystem.utils.Purchase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PurchaseFunctionsTest {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Choose a test function to run:");
            System.out.println("1: Add one purchase");
            System.out.println("2: Add many purchases");
            System.out.println("3: Get one purchase");
            System.out.println("4: Get all purchases");
            System.out.println("5: Delete one purchase");
            System.out.println("6: Get recent purchase code");
            System.out.println("7: Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    testAddOnePurchase();
                    break;
                case 2:
                    testAddManyPurchases();
                    break;
                case 3:
                    System.out.print("Enter purchase ID: ");
                    int getId = scanner.nextInt();
                    testGetOnePurchase(getId);
                    break;
                case 4:
                    testGetAllPurchases();
                    break;
                case 5:
                    System.out.print("Enter purchase ID: ");
                    int deleteId = scanner.nextInt();
                    testDeleteOnePurchase(deleteId);
                    break;
                case 6:
                    testGetRecentPurchaseCode();
                    break;
                case 7:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void testAddOnePurchase() {
        try {
            // Generate a new purchase code based on the recent one
            String recentPurchaseCode = PurchaseFunctions.getRecentPurchaseCode();
            String nextPurchaseCode = Purchase.generateNextPurchaseCode(recentPurchaseCode);

            // Create a new PurchaseEntity
            PurchaseEntity purchase = new PurchaseEntity(nextPurchaseCode, 10, 100.0, "Test Customer", 6);
            PurchaseFunctions.addOnePurchase(purchase);
            System.out.println("Purchase added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void testAddManyPurchases() {
        try {
            // Generate a list of PurchaseEntity objects
            List<PurchaseEntity> purchases = new ArrayList<>();

            // Get recent purchase code and generate the next few purchase code
            String recentPurchaseCode = PurchaseFunctions.getRecentPurchaseCode();
            String nextPurchaseCode = Purchase.generateNextPurchaseCode(recentPurchaseCode);

            for (int i = 0; i < 5; i++) {
                purchases.add(new PurchaseEntity(nextPurchaseCode, 10 + i, 100.0 + (i * 10), "Test Customer", 10 + i));
            }

            // Add purchases
            PurchaseFunctions.addManyPurchases(purchases);
            System.out.println("Purchases added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    private static void testGetOnePurchase(int id) {
        try {
            PurchaseViewEntity purchase = PurchaseFunctions.getOnePurchase(id);
            if (purchase != null) {
                System.out.println("Purchase found: \n" + purchase);
            } else {
                System.out.println("Purchase not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void testGetAllPurchases() {
        try {
            List<PurchaseViewEntity> purchases = PurchaseFunctions.getAllPurchases();
            if (!purchases.isEmpty()) {
                System.out.println("All purchases:");
                for (PurchaseViewEntity purchase : purchases) {
                    System.out.println("Purchase: \n" + purchase.toString());
                }
            } else {
                System.out.println("No purchases found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void testDeleteOnePurchase(int id) {
        try {
            PurchaseFunctions.deleteOnePurchase(id);
            System.out.println("Purchase deleted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void testGetRecentPurchaseCode() {
        try {
            String recentPurchaseCode = PurchaseFunctions.getRecentPurchaseCode();
            System.out.println("Recent purchase code: " + recentPurchaseCode);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
