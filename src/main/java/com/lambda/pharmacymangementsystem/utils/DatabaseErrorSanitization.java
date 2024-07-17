package com.lambda.pharmacymangementsystem.utils;

import org.postgresql.util.PSQLException;
import org.postgresql.util.ServerErrorMessage;

import java.sql.SQLException;

public class DatabaseErrorSanitization {

    public static String getErrorMessage(SQLException e) {
        if (e instanceof PSQLException psqlException) {
            ServerErrorMessage serverError = psqlException.getServerErrorMessage();

            if (serverError != null) {
                String sqlState = serverError.getSQLState();
                String detail = serverError.getDetail();

                switch (sqlState) {
                    case "23505": // unique_violation
                        return getUniqueViolationErrorMessage(detail);
                    case "23503": // foreign_key_violation
                        return getForeignKeyViolationErrorMessage(detail);
                    default:
                        return "Something went wrong: " + serverError.getMessage();
                }
            }
        }

        return "Something went wrong: " + e.getMessage();
    }

    private static String getUniqueViolationErrorMessage(String detail) {
        if (detail != null && detail.contains("already exists")) {
            String[] parts = detail.split("\\)=\\(");
            if (parts.length == 2) {
                String column = parts[0].substring(parts[0].indexOf("(") + 1);
                String value = parts[1].substring(0, parts[1].indexOf(")"));
                return String.format("The %s '%s' already exists. Please use a different value.", column, value);
            }
        }
        return "This record already exists";
    }

    private static String getForeignKeyViolationErrorMessage(String detail) {
        if (detail != null && detail.contains("is not present in table")) {
            String[] parts = detail.split("\\)=\\(");
            if (parts.length == 2) {
                String column = parts[0].substring(parts[0].indexOf("(") + 1);
                String value = parts[1].substring(0, parts[1].indexOf(")"));
                String table = detail.substring(detail.lastIndexOf("\"") + 1, detail.length() - 1);
                return String.format("The %s '%s' does not exist in the %s", column, value, table);
            }
        }
        return "This record depends on other records which does not exit.";
    }
}