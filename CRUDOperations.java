package fundaProg_System;
import java.sql.*;


public class CRUDOperations {




    public String insertResidents(int householdID, String name, int age, String address){

        Connection conn = null;
        try {
             conn = DBConnection.getConnection();
            conn.setAutoCommit(false);


            String query = "INSERT INTO residents (householdID, name, age, address) VAlUES (?,?,?,?)";

            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, householdID);
            ps.setString(2, name);
            ps.setInt(3, age);
            ps.setString(4, address);

            int row = ps.executeUpdate();



            String query2 = "UPDATE households SET totalresidentCount = totalresidentCount + 1 WHERE householdID = ?";
            PreparedStatement ps2 = conn.prepareStatement(query2);
            ps2.setInt(2, householdID);
            int row2 = ps2.executeUpdate();

            conn.commit();


            return "Inserted successfully!";


        }

         catch (Exception e){
            try {
                if (conn != null) conn.rollback();
            }

            catch(SQLException rb){
                System.out.println("Rollback failed" + rb.getMessage());
                }
            return "Failed to commit: " + e.getMessage();

        }
    }







}
