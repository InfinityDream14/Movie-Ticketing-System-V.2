
package Staffs;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Main_Staff {
    static Connection mc;
    public static void main(String[] args) throws ClassNotFoundException {
        
       try{
            String hostname = "localhost";
            String sqlInstanceName = "MTS"; //computer name 
            String sqlDatabase = "movieticketsystem";  //sql server database name
            String sqlUser = "sa";
            String sqlPassword = "Java"; //passwrod sa account

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            //jdbc:sqlserver://localhost:1433;instance=COMPUTERBERRY;databaseName=Database;
            String connectURL = "jdbc:sqlserver://" + hostname + ":1433" 
                    + ";instance=" + sqlInstanceName + ";databaseName=" + sqlDatabase
                    +";encrypt=true;trustServerCertificate=true";

            Connection conn = DriverManager.getConnection(connectURL, sqlUser, sqlPassword);
            System.out.println("Connect to database successful!!");
            mc=conn;
            
       }catch(SQLException e){
           e.printStackTrace();
       }
        try {
            new Movie_List().setVisible(true);
            //new Seat_Management().setVisible(true);
            //new Payment_Method().setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(Main_Staff.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
