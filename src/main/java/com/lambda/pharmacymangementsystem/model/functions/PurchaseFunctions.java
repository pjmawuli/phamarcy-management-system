package com.lambda.pharmacymangementsystem.model.functions;

import com.lambda.pharmacymangementsystem.model.Database;
import com.lambda.pharmacymangementsystem.model.entities.PurchaseEntity;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @hidden st: the sql statement to be executed
 * @hidden rs: the result set returned from the query
 */
public class PurchaseFunctions {
    static Database db = new Database();

    // add one purchase
    public static void addOnePurchase(PurchaseEntity purchase) throws SQLException {

//        use `try with resources` to automatically release the resources when done
        try
                (
                        Connection conn = db.connectDatabase();
                        PreparedStatement st = conn.prepareStatement("INSERT INTO purchases (purchase_code, quantity, total_price, customer_name, drug_id) VALUES (?, ?, ?, ?, ?)")
                ) {

//            bind inputs
            st.setString(1, purchase.getPurchaseCode());
            st.setInt(2, purchase.getQuantity());
            st.setDouble(4, purchase.getTotalPrice());
            st.setString(3, purchase.getCustomerName());
            st.setInt(5, purchase.getDrugId());

//            execute the query
            st.executeUpdate();
        } catch (Exception e) {
//            TODO: handle errors properly
            System.out.println("Could not add purchase");
            e.printStackTrace();
            throw e;
        }
    }

    //    get one purchase
    public static PurchaseEntity getOnePurchase(int id) throws SQLException {
//        use `try with resources` to automatically release the resources when done
        try
                (
                        Connection conn = db.connectDatabase();
                        PreparedStatement st = conn.prepareStatement("SELECT * FROM purchases WHERE id = ?")
                ) {

//            bind inputs
            st.setInt(1, id);

//            execute the query
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new PurchaseEntity(
                        rs.getInt("id"),
                        rs.getString("purchase_code"),
                        rs.getInt("quantity"),
                        rs.getDouble("total_price"),
                        rs.getString("customer_name"),
                        rs.getInt("drug_id"),
                        rs.getObject("created_at", LocalDateTime.class)
                );
            }
        } catch (Exception e) {
//            TODO: handle errors properly
            System.out.println("Could not retrieve purchase");
            e.printStackTrace();
            throw e;
        }
        return null;
    }


    //    get all purchases
    public static List<PurchaseEntity> getAllPurchases() throws SQLException {
        List<PurchaseEntity> purchases = new ArrayList<PurchaseEntity>();
//        use `try with resources` to automatically release the resources when done
        try
                (
                        Connection conn = db.connectDatabase();
                        Statement st = conn.createStatement()
                ) {

//            execute the query
            ResultSet rs = st.executeQuery("SELECT * FROM purchases");
            while (rs.next()) {
                PurchaseEntity newPurchase = new PurchaseEntity(
                        rs.getInt("id"),
                        rs.getString("purchase_code"),
                        rs.getInt("quantity"),
                        rs.getDouble("total_price"),
                        rs.getString("customer_name"),
                        rs.getInt("drug_id"),
                        rs.getObject("created_at", LocalDateTime.class));
                purchases.add(newPurchase);
            }
            return purchases;
        } catch (Exception e) {
//            TODO: handle errors properly
            System.out.println("Could not retrieve purchases");
            e.printStackTrace();
            throw e;
        }
    }


    //    delete one purchase
    public static void deleteOnePurchase(int id) throws SQLException {
//        PreparedStatement st = null;

//        use `try with resources` to automatically release the resources when done
        try
                (
                        Connection conn = db.connectDatabase();
                        PreparedStatement st = conn.prepareStatement("DELETE FROM purchases WHERE id = ?")
                ) {

//            bind inputs
            st.setInt(1, id);

//            execute the query
            st.executeUpdate();
        } catch (Exception e) {
//            TODO: handle errors properly
            System.out.println("Could not delete purchase");
            e.printStackTrace();
            throw e;
        }
    }

    //    get recent purchase code
    public static String getRecentPurchaseCode() throws SQLException {
//        use `try with resources` to automatically release the resources when done
        try
                (
                        Connection conn = db.connectDatabase();
                        PreparedStatement st = conn.prepareStatement("SELECT purchase_code FROM purchases ORDER BY purchase_code DESC LIMIT 1")
                ) {

//            execute the query
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getString("purchase_code");
            }
        } catch (Exception e) {
//            TODO: handle errors properly
            System.out.println("Could not retrieve recent purchase code");
            e.printStackTrace();
            throw e;
        }
        return "INV-00000";
    }


}
