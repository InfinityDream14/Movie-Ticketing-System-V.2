/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LogIn;

import java.sql.*;
import java.text.ParseException;
import java.time.LocalTime;
import java.util.Base64;
import javax.swing.*;
import main.*;

/**
 *
 * @author cdgan
 */
public class LogInProcess implements Logs, Crypting {

    static Statement stmt;

    public int checkIfLoged() throws SQLException, ClassNotFoundException {
        Main main = new Main();
        stmt = main.mc.createStatement();

        String sql = """
                    select l.Employee_ID, s.Fname +', '+ s.Lname as 'Full Name', l.DateLog, l.Log_In, l.Log_Out
                    from LOGS l left join staff s on l.Employee_ID = s.EmployeeID
                    order by l.DateLog, l.Log_In""";
        String empID = "", logOut = "";

        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            empID = rs.getString("Employee_ID");
            logOut = rs.getString("Log_Out");
        }
        if (logOut == null) {
            System.out.println(empID);
            if (empID.charAt(0) == 'A') {
                return 1;
            } else {
                return 2;
            }
        }
        return 0;
    }

    public int checkAcc(String user, String pass) throws SQLException, ClassNotFoundException {
        Main main = new Main();
        stmt = main.mc.createStatement();

        int flag = 0;
        String employeeID = "";

        String userN = user;
        String userP = pass;

        String query = "select username, passw, EmployeeID from staff";

        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {

            String checkUser = rs.getString(3);
            if (checkUser.equals("A1") || checkUser.equals("E1") || 
                    checkUser.equals("E2") || checkUser.equals("E3") || 
                    checkUser.equals("E4") || checkUser.equals("E5")) {
                
                if (userN.equals(rs.getString("username")) && userP.equals(rs.getString("passw"))) {
                    if (userN.charAt(0) == 'a') {
                        flag = 1;
                    } else {
                        flag = 2;
                    }
                    employeeID = rs.getString("EmployeeID");
                    logs(employeeID);
                    break;
                } else if (userN.equals(rs.getString(1))) {
                    flag = 9;
                }
            } else {

                if (userN.equals(decrypt(rs.getString("username"))) && userP.equals(decrypt(rs.getString("passw")))) {
                    if (userN.charAt(0) == 'a') {
                        flag = 1;
                    } else {
                        flag = 2;
                    }
                    employeeID = rs.getString("EmployeeID");
                    System.out.println(employeeID + "If empid != E1-E5");
                    logs(employeeID);
                    break;
                } else if (userN.equals(decrypt(rs.getString(1)))) {
                    flag = 9;
                }
            }

        }

        switch (flag) {
            case 1 ->
                JOptionPane.showMessageDialog(null, "Welcome. Our Team leader!");
            case 2 ->
                JOptionPane.showMessageDialog(null, "Welcome our team member!");
            case 9 ->
                JOptionPane.showMessageDialog(null, "Your password is incorrect");
            default ->
                JOptionPane.showMessageDialog(null, "There is no such an account");
        }

        return flag;
    }

    @Override
    public void logs(String employeeID) {
        LocalTime now = LocalTime.now();
        System.out.println(employeeID + "  dsdsdfsd");
        String query = "insert into logs (Employee_ID, Log_In) values ('" + employeeID + "', '" + now + "')";

        try {
            stmt.executeQuery(query);
            System.out.println("Success!!");

        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public String encrypt(String value) {
        try {
            return Base64.getEncoder().encodeToString(value.getBytes());

        } catch (Exception e) {
            System.err.println("Error in Encrypt: " + e);
        }
        return null;
    }

    @Override
    public String decrypt(String value) {
        try {
            byte[] decodedBytes = Base64.getDecoder().decode(value);
            return new String(decodedBytes);
        } catch (Exception e) {

        }
        return null;
    }

}
