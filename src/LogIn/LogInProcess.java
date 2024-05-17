/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LogIn;

import java.sql.*;
import java.time.LocalTime;
import javax.swing.*;
import main.*;

/**
 *
 * @author cdgan
 */
public class LogInProcess implements Logs {

    Statement stmt;
    Main main = new Main();
    
    public int checkIfLoged() throws SQLException, ClassNotFoundException{
        
        this.main.connectToDatabase();
        this.stmt = (Statement) this.main.mc.createStatement();
        
        String sql = """
                     select l.Employee_ID, s.Fname +', '+ s.Lname as 'Full Name', l.DateLog, l.Log_In, l.Log_Out
                     \tfrom LOGS l left join staff s on l.Employee_ID = s.EmployeeID
                     \torder by l.DateLog, l.Log_In""";
        String empID = "", logOut = "";
        
        ResultSet rs = stmt.executeQuery(sql);
        while(rs.next()){
            empID = rs.getString(1);
            logOut = rs.getString(5);
        }
        if(logOut == null){
            System.out.println(empID);
           if(empID.charAt(0) == 'A'){
               return 1;               
           } else {
               return 2;               
           }           
        }
        return 0;
    }
    
    public int checkAcc(String user, String pass) throws SQLException, ClassNotFoundException {
        

        int flag = 0;
        String employeeID = "";
        this.stmt = (Statement) this.main.mc.createStatement();

        String userN = user;
        String userP = pass;

        String query = "select username, passw, EmployeeID from staff";

        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
            System.out.println(rs.getString(1).charAt(0));
            if (userN.equals(rs.getString(1)) && userP.equals(rs.getString(2))) {
                if (userN.charAt(0) == 'a') {
                    flag = 1;
                } else {
                    flag = 2;
                }
                System.out.println(rs.getString(3));
                employeeID = rs.getString(3);
                logs(employeeID);
                break;
            } else if (userN.equals(rs.getString(1))) {
                flag = 9;
            }
        }  

        switch (flag) {
            case 1 -> JOptionPane.showMessageDialog(null, "Welcome. Our Team leader!");
            case 2 -> JOptionPane.showMessageDialog(null, "Welcome our team member!");
            case 9 -> JOptionPane.showMessageDialog(null, "Your password is incorrect");
            default -> JOptionPane.showMessageDialog(null, "There is no such an account");
        }

        return flag;
    }

    @Override
    public void logs(String employeeID) {
        LocalTime now = LocalTime.now();
        System.out.println(employeeID);
        String query = "insert into logs (Employee_ID, Log_In) values ('" + employeeID + "', '" + now + "')";
        
        try {
            stmt.executeQuery(query);

        } catch (SQLException ex) {
            
        }
    }

}
