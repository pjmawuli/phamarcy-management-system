package com.lambda.pharmacymangementsystem.model;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * contains the connection details of the application and its underlying database (postgres) using JDBC
 * the postgres Java Database Connectivity (pgJDBC) driver is used to ensure smooth communication between the application and its data store
 * reference: https://jdbc.postgresql.org/
 */
public class Database {
    private static final String databaseUrl = "jdbc:postgresql://localhost/lambda_pharmacy_db?user=postgres&password=postgres";
    public static Connection conn;

    // create connection
    public static Connection connectDatabase() {
        try {
            conn = DriverManager.getConnection(databaseUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
}
