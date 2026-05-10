
import java.sql.*;


public class CRUDOperations {




    public static String insertResidents(Object[] data){

        Connection conn = null;
        try {
             conn = DBConnection.getConnection();
            conn.setAutoCommit(false);


            String query = "INSERT INTO residents (first_name, last_name, middle_name, birthdate, gender, civil_status, contact_number, is_voter, household_id) VAlUES (?,?,?,?,?,?,?,?,?)";

            PreparedStatement ps = conn.prepareStatement(query);
            for(int i = 0; i < data.length; i++){
                ps.setObject(i + 1, data[i]);
            }   
            int row = ps.executeUpdate();



            String query2 = "UPDATE households SET totalresidentCount = totalresidentCount + 1 WHERE householdID = ?";
            PreparedStatement ps2 = conn.prepareStatement(query2);

          

            ps2.setObject(1, data[8]);
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


    public static String viewResidents(Object data[]){
        Connection conn = null;
        try{
            conn = DBConnection.getConnection();
            conn.setAutoCommit(false);

            String query = "SELECT * FROM residents";

            PreparedStatement ps = conn.prepareStatement(query);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
               rs.getString("first_name");
               rs.getString("last_name");
               rs.getString("middle_name");
               rs.getString("birthdate");
               rs.getString("gender");
               rs.getString("civil_status");
               
                
            }
               } 
               
        }


    }




}
