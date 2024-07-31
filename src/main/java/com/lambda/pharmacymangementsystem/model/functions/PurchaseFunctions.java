package com.lambda.pharmacymangementsystem.model.functions;

import com.lambda.pharmacymangementsystem.model.Database;
import com.lambda.pharmacymangementsystem.model.entities.PurchaseEntity;
import com.lambda.pharmacymangementsystem.model.entities.PurchaseViewEntity;
import com.lambda.pharmacymangementsystem.utils.Purchase;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @hidden st: the sql statement to be executed
 * @hidden rs: the result set returned from the query
 */
public class PurchaseFunctions {

    // add one purchase
    public static void addOnePurchase(PurchaseEntity purchase) throws SQLException, Exception {

        Connection conn = Database.connectDatabase();
        try {
            // disable the default autocommit behavior of the pgdbc driver for transactions
            conn.setAutoCommit(false);

            // Retireve the most rececnt purchase code and add generate the next purchase
            // code

            String lastPurchaseCode = getRecentPurchaseCode();
            purchase.setPurchaseCode(Purchase.generateNextPurchaseCode(lastPurchaseCode));

            PreparedStatement st = conn.prepareStatement(
                    "INSERT INTO purchases (purchase_code, quantity, total_price, customer_name, drug_id) VALUES (?, ?, ?, ?, ?)");

            // bind inputs
            st.setString(1, purchase.getPurchaseCode());
            st.setInt(2, purchase.getQuantity());
            st.setDouble(3, purchase.getTotalPrice());
            st.setString(4, purchase.getCustomerName());
            st.setInt(5, purchase.getDrugId());

            // execute the query
            st.executeUpdate();

            // update quantity
            DrugFunctions.updateOneDrugStock(purchase.getDrugId(), purchase.getQuantity());

            conn.commit();
        } catch (Exception e) {
            // rollback transaction in case of error
            conn.rollback();
            // TODO: handle errors properly
            System.out.println("Could not add purchase");
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * This method takes a list of purchases and insert them as a unit (transaction)
     * Thus, the purchases inserted all have the same purchase code which allows
     * them to be easily aggregated
     *
     * @param `List<purchase>` - a list of the bulk purchases to be inserted
     * @throws SQLException
     */
    public static void addManyPurchases(List<PurchaseEntity> purchases) throws SQLException, Exception {

        Connection conn = Database.connectDatabase();
        try {
            // disable the default autocommit behavior of the pgdbc driver for transactions
            conn.setAutoCommit(false);

            PreparedStatement st = conn.prepareStatement(
                    "INSERT INTO purchases (purchase_code, quantity, total_price, customer_name, drug_id) VALUES (?, ?, ?, ?, ?)");

            // add purchases in batch and bind inputs
            for (PurchaseEntity purchase : purchases) {
                st.setString(1, purchase.getPurchaseCode());
                st.setInt(2, purchase.getQuantity());
                st.setDouble(3, purchase.getTotalPrice());
                st.setString(4, purchase.getCustomerName());
                st.setInt(5, purchase.getDrugId());

                // register as a batch statement
                st.addBatch();

                // update quantity
                DrugFunctions.updateOneDrugStock(purchase.getDrugId(), purchase.getQuantity());
            }

            // execute the batch insert
            st.executeBatch();

            // subsequently update stock in shop
            // PreparedStatement stockst = conn.prepareStatement("UPDATE drugs SET quantity
            // = ? WHERE id = ?");
            //
            // for (PurchaseEntity purchase : purchases) {
            // stockst.setInt(1, purchase.getDrugId());
            // stockst.setInt(2, purchase.getQuantity());
            //
            //// register as a batch statement
            // st.addBatch();
            // }

            // commit transaction
            conn.commit();
            st.close();
        } catch (Exception e) {
            // rollback transaction in case of error
            conn.rollback();
            // TODO: handle errors properly
            System.out.println("Could not add purchase");
            e.printStackTrace();
            throw e;
        } finally {
            conn.close();
        }
    }

    // get one purchase
    public static PurchaseViewEntity getOnePurchase(int id) throws SQLException {
        // use `try with resources` to automatically release the resources when done
        try (
                Connection conn = Database.connectDatabase();
                PreparedStatement st = conn.prepareStatement("SELECT * FROM purchases_view WHERE id = ?")) {

            // bind inputs
            st.setInt(1, id);

            // execute the query
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new PurchaseViewEntity(
                        rs.getInt("id"),
                        rs.getString("purchase_code"),
                        rs.getInt("quantity"),
                        rs.getDouble("total_price"),
                        rs.getString("customer_name"),
                        rs.getObject("created_at", LocalDateTime.class),
                        rs.getInt("drug_id"),
                        rs.getString("drug_name"),
                        rs.getString("drug_code"),
                        rs.getInt("drug_quantity"),
                        rs.getDouble("drug_price"),
                        rs.getInt("drug_supplier_id"),
                        rs.getObject("drug_created_at", LocalDateTime.class),
                        rs.getObject("drug_updated_at", LocalDateTime.class));
            }
        } catch (Exception e) {
            // TODO: handle errors properly
            System.out.println("Could not retrieve purchase");
            e.printStackTrace();
            throw e;
        }
        return null;
    }

    // get all purchases count
    public static int getAllPurchasesCount() throws SQLException {
        // use `try with resources` to automatically release the resources when done
        try (
                Connection conn = Database.connectDatabase();
                Statement st = conn.createStatement()) {

            // execute the query
            ResultSet rs = st.executeQuery("SELECT COUNT( DISTINCT(purchase_code) ) AS total_purchases FROM purchases");
            if (rs.next())
                return rs.getInt("total_purchases");
        } catch (Exception e) {
            // TODO: handle errors properly
            System.out.println("Could not retrieve purchases");
            e.printStackTrace();
            throw e;
        }
        return 0;
    }

    // get all purchases made today count
    public static int getAllTodayPurchasesCount() throws SQLException {
        LocalDateTime startDate = LocalDateTime.now().with(LocalTime.MIN);
        LocalDateTime endDate = LocalDateTime.now().with(LocalTime.MAX);

        // use `try with resources` to automatically release the resources when done
        try (
                Connection conn = Database.connectDatabase();
                PreparedStatement st = conn.prepareStatement(
                        "SELECT COUNT( DISTINCT(purchase_code) ) AS total_today_purchases FROM purchases WHERE created_at >= ? and created_at <= ?")) {

            st.setDate(1, Date.valueOf(startDate.toLocalDate()));
            st.setDate(2, Date.valueOf(endDate.toLocalDate()));
            // execute the query
            ResultSet rs = st.executeQuery();
            if (rs.next())
                return rs.getInt("total_today_purchases");
        } catch (SQLException e) {
            // TODO: handle errors properly
            System.out.println("Could not retrieve purchases");
            e.printStackTrace();
            throw e;
        }
        return 0;
    }

    // get all purchases
    public static List<PurchaseViewEntity> getAllPurchases() throws SQLException {
        List<PurchaseViewEntity> purchases = new ArrayList<PurchaseViewEntity>();
        // use `try with resources` to automatically release the resources when done
        try (
                Connection conn = Database.connectDatabase();
                Statement st = conn.createStatement()) {

            // execute the query
            ResultSet rs = st.executeQuery("SELECT * FROM purchases_view ORDER BY created_at, drug_code DESC");
            while (rs.next()) {
                PurchaseViewEntity newPurchase = new PurchaseViewEntity(
                        rs.getInt("id"),
                        rs.getString("purchase_code"),
                        rs.getInt("quantity"),
                        rs.getDouble("total_price"),
                        rs.getString("customer_name"),
                        rs.getObject("created_at", LocalDateTime.class),
                        rs.getInt("drug_id"),
                        rs.getString("drug_name"),
                        rs.getString("drug_code"),
                        rs.getInt("drug_quantity"),
                        rs.getDouble("drug_price"),
                        rs.getInt("drug_supplier_id"),
                        rs.getObject("drug_created_at", LocalDateTime.class),
                        rs.getObject("drug_updated_at", LocalDateTime.class));
                purchases.add(newPurchase);
            }
            return purchases;
        } catch (SQLException e) {
            // TODO: handle errors properly
            System.out.println("Could not retrieve purchases");
            e.printStackTrace();
            throw e;
        }
    }

    // delete one purchase
    public static void deleteOnePurchase(int id) throws SQLException {
        // PreparedStatement st = null;

        // use `try with resources` to automatically release the resources when done
        try (
                Connection conn = Database.connectDatabase();
                PreparedStatement st = conn.prepareStatement("DELETE FROM purchases WHERE id = ?")) {

            // bind inputs
            st.setInt(1, id);

            // execute the query
            st.executeUpdate();
        } catch (SQLException e) {
            // TODO: handle errors properly
            System.out.println("Could not delete purchase");
            e.printStackTrace();
            throw e;
        }
    }

    // get recent purchase code
    public static String getRecentPurchaseCode() throws SQLException {
        // use `try with resources` to automatically release the resources when done
        try (
                Connection conn = Database.connectDatabase();
                PreparedStatement st = conn
                        .prepareStatement("SELECT purchase_code FROM purchases ORDER BY purchase_code DESC LIMIT 1")) {

            // execute the query
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getString("purchase_code");
            }
        } catch (SQLException e) {
            // TODO: handle errors properly
            System.out.println("Could not retrieve recent purchase code");
            e.printStackTrace();
            throw e;
        }
        return "INV-00000";
    }

}
