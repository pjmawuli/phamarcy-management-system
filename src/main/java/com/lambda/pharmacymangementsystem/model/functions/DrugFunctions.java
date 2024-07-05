package com.lambda.pharmacymangementsystem.model.functions;

import com.lambda.pharmacymangementsystem.model.Database;
import com.lambda.pharmacymangementsystem.model.entities.DrugEntity;
import com.lambda.pharmacymangementsystem.model.entities.DrugViewEntity;
import com.lambda.pharmacymangementsystem.utils.Drug;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
            // get the drug code
            String recentDrugCode = getRecentDrugCode();

            // generate next drug code
            String nextDrugCode = Drug.generateNextDrugCode(recentDrugCode);

//            bind inputs
            st.setString(1, drug.getName());
            st.setString(2, nextDrugCode);
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
    public static DrugViewEntity getOneDrug(int id) throws SQLException {
//        use `try with resources` to automatically release the resources when done
        try
                (
                        Connection conn = db.connectDatabase();
                        PreparedStatement st = conn.prepareStatement("SELECT * FROM drugs_view WHERE id = ?")
                ) {

//            bind inputs
            st.setInt(1, id);

//            execute the query
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new DrugViewEntity(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("drug_code"),
                        rs.getInt("quantity"),
                        rs.getDouble("price"),
                        rs.getObject("created_at", LocalDateTime.class),
                        rs.getObject("updated_at", LocalDateTime.class),
                        rs.getInt("supplier_id"),
                        rs.getString("supplier_name"),
                        rs.getString("supplier_contact"),
                        rs.getString("supplier_location"),
                        rs.getObject("supplier_created_at", LocalDateTime.class),
                        rs.getObject("supplier_updated_at", LocalDateTime.class));
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
    public static List<DrugViewEntity> getAllDrugs() throws SQLException {
        List<DrugViewEntity> drugs = new ArrayList<DrugViewEntity>();
//        use `try with resources` to automatically release the resources when done
        try
                (
                        Connection conn = db.connectDatabase();
                        Statement st = conn.createStatement()
                ) {

//            execute the query
            ResultSet rs = st.executeQuery("SELECT * FROM drugs_view ORDER BY drug_code");
            while (rs.next()) {
                DrugViewEntity newDrug = new DrugViewEntity(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("drug_code"),
                        rs.getInt("quantity"),
                        rs.getDouble("price"),
                        rs.getObject("created_at", LocalDateTime.class),
                        rs.getObject("updated_at", LocalDateTime.class),
                        rs.getInt("supplier_id"),
                        rs.getString("supplier_name"),
                        rs.getString("supplier_contact"),
                        rs.getString("supplier_location"),
                        rs.getObject("supplier_created_at", LocalDateTime.class),
                        rs.getObject("supplier_updated_at", LocalDateTime.class));
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

//    search for drug with parameters

    /**
     * This method accepts a hashmap containing all valid parameters (column names) to perform a partial case-insensitive search and retrieve matching records
     *
     * @param params - a hashmap containing the parameters (column) names and their respective values
     * @return List<DrugEntity>
     * @throws SQLException
     */
    public static List<DrugViewEntity> searchDrugs(Map<String, Object> params) throws SQLException {
//        create a string builder for the query
        StringBuilder query = new StringBuilder("SELECT * FROM drugs_view WHERE 1=1");

//        store the validated column values in a queue
        List<Object> values = new ArrayList<>();

//        iterate over the params map and append the available params
        if (params.containsKey("name")) {
            query.append(" AND name ILIKE ?");
            values.add("%" + params.get("name") + "%");
        }
        if (params.containsKey("drug_code")) {
            query.append(" AND drug_code ILIKE ?");
            values.add("%" + params.get("drug_code") + "%");

        }
        if (params.containsKey("supplier_name")) {
            query.append(" AND name ILIKE ?");
            values.add("%" + params.get("supplier_name") + "%");
        }

//        append order by clause
        query.append(" ORDER BY drug_code");

        List<DrugViewEntity> drugs = new ArrayList<>();
//        use `try with resources` to automatically release the resources when done
        try
                (
                        Connection conn = db.connectDatabase();
                        PreparedStatement st = conn.prepareStatement(query.toString())
                ) {

            //        bind inputs
            for (int i = 0; i < values.size(); i++) {
                st.setObject(i + 1, values.get(i));
            }


//            execute the query by chaining the parameters in the hashmap
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                DrugViewEntity newDrug = new DrugViewEntity(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("drug_code"),
                        rs.getInt("quantity"),
                        rs.getDouble("price"),
                        rs.getObject("created_at", LocalDateTime.class),
                        rs.getObject("updated_at", LocalDateTime.class),
                        rs.getInt("supplier_id"),
                        rs.getString("supplier_name"),
                        rs.getString("supplier_contact"),
                        rs.getString("supplier_location"),
                        rs.getObject("supplier_created_at", LocalDateTime.class),
                        rs.getObject("supplier_updated_at", LocalDateTime.class));
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

    //    update drug stock
    public static void updateOneDrugStock(int id, int quantity) throws SQLException, Exception {

        Connection conn = db.connectDatabase();
        try {
//          disable the default autocommit behavior of the pgdbc driver for transactions
            conn.setAutoCommit(false);

//          fetch drug with drug_code and quantity
            PreparedStatement drugSt = conn.prepareStatement("SELECT drug_code, quantity FROM drugs WHERE id = ?");

            drugSt.setInt(1, id);

//            execute the query
            ResultSet drugRs = drugSt.executeQuery();

            if (!drugRs.next()) {
                throw new Exception("Drug not found");
            }

            int quantityInStock = drugRs.getInt("quantity");
            String drugCode = drugRs.getString("drug_code");

//          throw Quantity out of stock error
            if (quantityInStock < quantity) {
                throw new Exception("Drug: " + drugCode + " has insufficient quantity in stock");
            }

            PreparedStatement st = conn.prepareStatement("UPDATE drugs SET quantity = ? WHERE id = ?");

//            bind inputs
            st.setInt(1, quantityInStock - quantity);
            st.setInt(2, id);

//            execute the query
            st.executeUpdate();

//            commit transaction
            conn.commit();
        } catch (Exception e) {
//            rollback transaction in case of error
            conn.rollback();
//            TODO: handle errors properly
            System.out.println("Could not update drug stock");
            e.printStackTrace();
            throw e;
        } finally {
            conn.close();
        }
    }

}
