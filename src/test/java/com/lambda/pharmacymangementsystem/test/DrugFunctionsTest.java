package com.lambda.pharmacymangementsystem.test;

import com.lambda.pharmacymangementsystem.model.entities.DrugEntity;
import com.lambda.pharmacymangementsystem.model.entities.DrugViewEntity;
import com.lambda.pharmacymangementsystem.model.functions.DrugFunctions;
import com.lambda.pharmacymangementsystem.utils.Drug;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class DrugFunctionsTest {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Choose a test function to run:");
            System.out.println("1: Add one drug");
            System.out.println("2: Get one drug");
            System.out.println("3: Update one drug");
            System.out.println("4: Delete one drug");
            System.out.println("5: Get all drugs");
            System.out.println("6: Get recent drug code");
            System.out.println("7: Search drugs");
            System.out.println("8: Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    testAddOneDrug();
                    break;
                case 2:
                    System.out.print("Enter drug ID: ");
                    int getId = scanner.nextInt();
                    testGetOneDrug(getId);
                    break;
                case 3:
                    System.out.print("Enter drug ID: ");
                    int updateId = scanner.nextInt();
                    testUpdateOneDrug(updateId);
                    break;
                case 4:
                    System.out.print("Enter drug ID: ");
                    int deleteId = scanner.nextInt();
                    testDeleteOneDrug(deleteId);
                    break;
                case 5:
                    testGetAllDrugs();
                    break;
                case 6:
                    testGetRecentDrugCode();
                    break;
                case 7:
                    testSearchDrugs();
                    break;
                case 8:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void testAddOneDrug() {
        try {
//            get recent drug code and add find the next code
            String recentDrugCode = DrugFunctions.getRecentDrugCode();
            String nextDrugCode = Drug.generateNextDrugCode(recentDrugCode);
            DrugEntity drug = new DrugEntity("Test Drug", nextDrugCode, 10, 25.0, 1);
            DrugFunctions.addOneDrug(drug);
            System.out.println("Drug added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void testGetOneDrug(int id) {
        try {
            DrugViewEntity drug = DrugFunctions.getOneDrug(id);
            if (drug != null) {
                System.out.println("Drug found: \n" + drug.toString());
            } else {
                System.out.println("Drug not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void testUpdateOneDrug(int id) {
        try {
            DrugEntity drug = new DrugEntity("Updated Drug", "UPD001", 20, 30.0, 1);
            DrugFunctions.updateOneDrug(id, drug);
            System.out.println("Drug updated successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void testDeleteOneDrug(int id) {
        try {
            DrugFunctions.deleteOneDrug(id);
            System.out.println("Drug deleted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void testGetRecentDrugCode() {
        try {
            String recentDrugCode = DrugFunctions.getRecentDrugCode();
            System.out.println("Recent drug code: " + recentDrugCode);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void testSearchDrugs() {
        try {
            // Simulating search parameters (can be adjusted based on actual use case)
            Map<String, Object> params = new HashMap<>();
            params.put("drug_code", "01");

            List<DrugViewEntity> drugs = DrugFunctions.searchDrugs(params);

            if (!drugs.isEmpty()) {
                System.out.println("Drugs found:");
                for (DrugViewEntity drug : drugs) {
                    System.out.println(drug.toString());
                }
            } else {
                System.out.println("No drugs found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void testGetAllDrugs() {
        try {
            List<DrugViewEntity> drugs = DrugFunctions.getAllDrugs();
            if (!drugs.isEmpty()) {
                System.out.println("All drugs:");
                for (DrugViewEntity drug : drugs) {
                    System.out.println("Drugs: \n" + drug.toString());
                }
            } else {
                System.out.println("No drugs found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
