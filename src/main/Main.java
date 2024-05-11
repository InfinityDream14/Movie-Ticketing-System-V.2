package main;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
/**
 *
 * @author cdgan
 */
import admin.Admin;
import Staffs.Movie_List;
import java.awt.Toolkit;
import java.sql.*;

public class Main {

    /**
     * @param args the command line arguments
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    public Connection mc;

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Main m = new Main();
        m.connectToDatabase();
//        new Movie_List().setVisible(true);
        new LogIn.LogIn().setVisible(true);
    }
    
    public void connectToDatabase() throws SQLException, ClassNotFoundException {
        try {
            String hostname = "user";
            String sqlInstanceName = "MTS"; //computer name 
            String sqlDatabase = "movieticketsystem";  //sql server database name
            String sqlUser = "sa";
            String sqlPassword = "Java"; //passwrod sa account

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            //jdbc:sqlserver://localhost:1433;instance=COMPUTERBERRY;databaseName=Database;
            String connectURL = "jdbc:sqlserver://" + hostname + ":1433"
                    + ";instance=" + sqlInstanceName + ";databaseName=" + sqlDatabase
                    + ";encrypt=true;trustServerCertificate=true";

            Connection conn = DriverManager.getConnection(connectURL, sqlUser, sqlPassword);
            System.out.println("Connect to database successful!!");
            mc = conn;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        //new Seat_Management().setVisible(true);
        //new Payment_Method().setVisible(true);

    }
}
