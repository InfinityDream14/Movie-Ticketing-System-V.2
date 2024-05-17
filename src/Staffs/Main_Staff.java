
package Staffs;

import java.sql.*;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Main_Staff {
    static Connection mc;
    static String emplog;
    
    public static void main(String[] args) throws ClassNotFoundException, SQLException, ParseException {
       
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
       get_empid();
       
           new Movie_List().setVisible(true);
           //new Seat_Management().setVisible(true);
           //new Payment_Method().setVisible(true);
           //new Staffs_Profile().setVisible(true);
           //new Staff_Name().setVisible(true);
        
    }
    
    public static void get_empid() throws SQLException{
        Statement stmt = mc.createStatement();
        
        String qry = """
                    select l.Employee_ID, s.Fname +', '+ s.Lname as 'Full Name', l.DateLog, l.Log_In, l.Log_Out
                    from LOGS l left join staff s on l.Employee_ID = s.EmployeeID
                    order by l.DateLog, l.Log_In""";
        
        ResultSet rs = stmt.executeQuery(qry);
        
        while(rs.next()){
            emplog = rs.getString(1);
            new Temp_Data().empid = emplog;
        }
    }
    
}
