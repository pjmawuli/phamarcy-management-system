package com.lambda.pharmacymangementsystem.model.functions;

import com.lambda.pharmacymangementsystem.model.Database;
import com.lambda.pharmacymangementsystem.model.entities.DrugEntity;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @hidden st: the sql statement to be executed
 * @hidden rs: the result set returned from the query
 */
public class DrugFunctions {
    static Database db = new Database();

    // add one drug
    public static void addOneDrug(DrugEntity drug) throws SQLException {

//        `use try with resources` to automatically release the resources when done
        try
                (
                        Connection conn = db.connectDatabase();
                        PreparedStatement st = conn.prepareStatement("INSERT INTO drugs (name, drug_code, quantity, price, supplier_id) VALUES (?, ?, ?, ?, ?)")
                ) {

//            bind inputs
            st.setString(1, drug.getName());
            st.setString(2, drug.getDrugCode());
            st.setInt(3, drug.getQuantity());
            st.setDouble(4, drug.getPrice());
            st.setInt(5, drug.getSupplierId());

//            execute the query
            st.executeUpdate();
        } catch (Exception e) {
//            TODO: handle errors properly
            System.out.println("Could not add drug");
            e.printStackTrace();
            throw e;
        }
    }

    //    get one drug
    public static DrugEntity getOneDrug(int id) throws SQLException {
//        use `try with resources` to automatically release the resources when done
        try
                (
                        Connection conn = db.connectDatabase();
                        PreparedStatement st = conn.prepareStatement("SELECT * FROM drugs WHERE id = ?")
                ) {

//            bind inputs
            st.setInt(1, id);

//            execute the query
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new DrugEntity(rs.getInt("id"), rs.getString("name"),
                        rs.getString("drug_code"),
                        rs.getInt("quantity"),
                        rs.getDouble("price"),
                        rs.getInt("supplier_id"),
                        rs.getObject("created_at", LocalDateTime.class),
                        rs.getObject("updated_at", LocalDateTime.class));
            }
        } catch (Exception e) {
//            TODO: handle errors properly
            System.out.println("Could not retrieve drug");
            e.printStackTrace();
            throw e;
        }
        return null;
    }


    //    get all drugs
    public static List<DrugEntity> getAllDrugs() throws SQLException {
        List<DrugEntity> drugs = new ArrayList<DrugEntity>();
//        use `try with resources` to automatically release the resources when done
        try
                (
                        Connection conn = db.connectDatabase();
                        Statement st = conn.createStatement()
                ) {

//            execute the query
            ResultSet rs = st.executeQuery("SELECT * FROM drugs");
            while (rs.next()) {
                DrugEntity newDrug = new DrugEntity(rs.getInt("id"), rs.getString("name"),
                        rs.getString("drug_code"),
                        rs.getInt("quantity"),
                        rs.getDouble("price"),
                        rs.getInt("supplier_id"),
                        rs.getObject("created_at", LocalDateTime.class),
                        rs.getObject("updated_at", LocalDateTime.class));
                drugs.add(newDrug);
            }
            return drugs;
        } catch (Exception e) {
//            TODO: handle errors properly
            System.out.println("Could not retrieve drugs");
            e.printStackTrace();
            throw e;
        }
    }


    //    update one drug
    public static void updateOneDrug(int id, DrugEntity drug) throws SQLException {

//        use `try with resources` to automatically release the resources when done
        try
                (
                        Connection conn = db.connectDatabase();
                        PreparedStatement st = conn.prepareStatement("UPDATE drugs SET name = ?, drug_code = ?, quantity = ?, price = ?, supplier_id = ? WHERE id = ?")
                ) {

//            bind inputs
            st.setString(1, drug.getName());
            st.setString(2, drug.getDrugCode());
            st.setInt(3, drug.getQuantity());
            st.setDouble(4, drug.getPrice());
            st.setInt(5, drug.getSupplierId());
            st.setInt(6, id);

//            execute the query
            st.executeUpdate();
        } catch (Exception e) {
//            TODO: handle errors properly
            System.out.println("Could not update drug");
            e.printStackTrace();
            throw e;
        }
    }


    //    delete one drug
    public static void deleteOneDrug(int id) throws SQLException {
//        PreparedStatement st = null;

//        use `try with resources` to automatically release the resources when done
        try
                (
                        Connection conn = db.connectDatabase();
                        PreparedStatement st = conn.prepareStatement("DELETE FROM drugs WHERE id = ?")
                ) {

//            bind inputs
            st.setInt(1, id);

//            execute the query
            st.executeUpdate();
        } catch (Exception e) {
//            TODO: handle errors properly
            System.out.println("Could not delete drug");
            e.printStackTrace();
            throw e;
        }
    }


    //    get recent drug code
    public static String getRecentDrugCode() throws SQLException {
//        use `try with resources` to automatically release the resources when done
        try
                (
                        Connection conn = db.connectDatabase();
                        PreparedStatement st = conn.prepareStatement("SELECT drug_code FROM drugs ORDER BY drug_code DESC LIMIT 1")
                ) {

//            execute the query
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getString("drug_code");
            }
        } catch (Exception e) {
//            TODO: handle errors properly
            System.out.println("Could not retrieve recent drug code");
            e.printStackTrace();
            throw e;
        }
        return "MED-00000";
    }

}
