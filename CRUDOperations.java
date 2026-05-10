
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


    public static String viewResidents(){
        Connection conn = null;
        try{
            conn = DBConnection.getConnection();

            String query = "SELECT * FROM residents";

            PreparedStatement ps = conn.prepareStatement(query);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                  String fname = rs.getString("first_name");
                  String lname = rs.getString("last_name");
                  String mname =rs.getString("middle_name");
                  String birthdate = rs.getString("birthdate");
                  String gender = rs.getString("gender");
                  String cstatus = rs.getString("civil_status");
                  String contactNo =rs.getString("contact_number");
                  int isVoter = rs.getInt("is_voter");
                  int houseID = rs.getInt("household_id");
                  String createdAT = rs.getString("created_at");

                  System.out.println("Full name: " + fname + " " + mname + " " + lname);
                  System.out.println("Birthdate: " + birthdate + " | " + "Gender: " + gender);
                  System.out.println("Civil Status: " + cstatus + " | " + "Contact Number: " + contactNo);
                  System.out.println("Is Voter: " + (isVoter == 1 ? "Yes" : "No") + " | " + "Household ID: " + houseID);
                  System.out.println("Created At: " + createdAT);
                  System.out.println("==========================================");;

            }
            
            return "Operation Success";

               } catch(Exception e){
                
                  return "Error: " + e.getMessage();
                
               }
               finally{
                    DBConnection.closeConnection(conn);
                }
               
        }

        public static String viewResidentbyID(int input){
        Connection conn = null;
        try{
            conn = DBConnection.getConnection();

            String query = "SELECT * FROM residents WHERE id = ?";

            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, input);

            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                  String fname = rs.getString("first_name");
                  String lname = rs.getString("last_name");
                  String mname =rs.getString("middle_name");
                  String birthdate = rs.getString("birthdate");
                  String gender = rs.getString("gender");
                  String cstatus = rs.getString("civil_status");
                  String contactNo =rs.getString("contact_number");
                  int isVoter = rs.getInt("is_voter");
                  int houseID = rs.getInt("household_id");
                  String createdAT = rs.getString("created_at");

                  System.out.println("Full name: " + fname + " " + mname + " " + lname);
                  System.out.println("Birthdate: " + birthdate + " | " + "Gender: " + gender);
                  System.out.println("Civil Status: " + cstatus + " | " + "Contact Number: " + contactNo);
                  System.out.println("Is Voter: " + (isVoter == 1 ? "Yes" : "No") + " | " + "Household ID: " + houseID);
                  System.out.println("Created At: " + createdAT);
                  System.out.println("==========================================");;

            } else {
                System.out.println("No resident found with ID: " + input);
            }
            
            return "Operation Success";

               } catch(Exception e){
                
                  return "Error: " + e.getMessage();
                
               }
               finally{
                    DBConnection.closeConnection(conn);
                }
               
        }
        public static String updateResident(String choice, Object newValue, int ID){
            Connection conn  = null;
            try{
                 conn = DBConnection.getConnection();
                conn.setAutoCommit(false);

                String query = "UPDATE residents SET " + choice + " = ? WHERE id = ?";
                PreparedStatement ps = conn.prepareStatement(query);

                ps.setObject(1, newValue);
                ps.setInt(2, ID);
                int row = ps.executeUpdate();

                if(row == 0){
                    return "No resident found with ID: " + ID;
                }

                conn.commit();
                return "Updated Successfully!";
               
            } catch(SQLException e){
                try{
                if(conn != null) conn.rollback();
                } catch(SQLException ab){
                    System.out.println("Rollback failed: " + ab.getMessage());
                }
                return "Database Error: " + e.getMessage();
                
            } catch(Exception e){
                return "Unexpected Error: " + e.getMessage();
            } finally{
                DBConnection.closeConnection(conn);
            }
            
        }


    }





