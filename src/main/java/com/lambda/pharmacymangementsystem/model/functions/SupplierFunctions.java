package com.lambda.pharmacymangementsystem.model.functions;

import com.lambda.pharmacymangementsystem.model.Database;
import com.lambda.pharmacymangementsystem.model.entities.SupplierEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @hidden st: the sql statement to be executed
 * @hidden rs: the result set returned from the query
 */
public class SupplierFunctions {
    Database db = new Database();

    // add one supplier
    public void addOneSupplier(SupplierEntity supplier) throws SQLException {

//        `use try with resources` to automatically release the resources when done
        try
                (
                        Connection conn = db.connectDatabase();
                        PreparedStatement st = conn.prepareStatement("INSERT INTO suppliers (name, contact, location) VALUES (?, ?, ?)");
                ) {

//            bind inputs
            st.setString(1, supplier.getName());
            st.setInt(2, supplier.getContact());
            st.setString(3, supplier.getLocation());

//            execute the query
            st.executeUpdate();
        } catch (Exception e) {
//            TODO: handle errors properly
            System.out.println("Could not add supplier");
            e.printStackTrace();
        }
    }

    //    get one supplier
    public SupplierEntity getOneSupplier(int id) throws SQLException {
//        use `try with resources` to automatically release the resources when done
        try
                (
                        Connection conn = db.connectDatabase();
                        PreparedStatement st = conn.prepareStatement("SELECT * FROM suppliers WHERE id = ?");
                ) {

//            bind inputs
            st.setInt(1, id);

//            execute the query
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new SupplierEntity(rs.getInt("id"), rs.getString("name"), rs.getInt("contact"), rs.getString("location"));
            }
        } catch (Exception e) {
//            TODO: handle errors properly
            System.out.println("Could not update supplier");
            e.printStackTrace();
        }
        return null;
    }


    //    get all suppliers
    public List<SupplierEntity> getAllSuppliers() throws SQLException {
        List<SupplierEntity> suppliers = new ArrayList<SupplierEntity>();
//        use `try with resources` to automatically release the resources when done
        try
                (
                        Connection conn = db.connectDatabase();
                        Statement st = conn.createStatement();
                ) {

//            execute the query
            ResultSet rs = st.executeQuery("SELECT * FROM suppliers");
            while (rs.next()) {
                SupplierEntity newSupplier = new SupplierEntity(rs.getInt("id"), rs.getString("name"), rs.getInt("contact"), rs.getString("location"));
                suppliers.add(newSupplier);
            }
            return suppliers;
        } catch (Exception e) {
//            TODO: handle errors properly
            System.out.println("Could not update supplier");
            e.printStackTrace();
        }
        return null;
    }


    //    update one supplier
    public void updateOneSupplier(int id, SupplierEntity supplier) throws SQLException {

//        use `try with resources` to automatically release the resources when done
        try
                (
                        Connection conn = db.connectDatabase();
                        PreparedStatement st = conn.prepareStatement("UPDATE suppliers SET name = ?, contact = ?, location = ? WHERE id = ?");
                ) {

//            bind inputs
            st.setString(1, supplier.getName());
            st.setInt(2, supplier.getContact());
            st.setString(3, supplier.getLocation());
            st.setInt(4, id);

//            execute the query
            st.executeUpdate();
        } catch (Exception e) {
//            TODO: handle errors properly
            System.out.println("Could not update supplier");
            e.printStackTrace();
        }
    }


    //    delete one supplier
    public void deleteOneSupplier(int id) throws SQLException {
//        PreparedStatement st = null;

//        use `try with resources` to automatically release the resources when done
        try
                (
                        Connection conn = db.connectDatabase();
                        PreparedStatement st = conn.prepareStatement("DELETE FROM suppliers WHERE id = ?");
                ) {

//            bind inputs
            st.setInt(1, id);

//            execute the query
            st.executeUpdate();
        } catch (Exception e) {
//            TODO: handle errors properly
            System.out.println("Could not update supplier");
            e.printStackTrace();
        }
    }


}
