/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LogIn;

import admin.Admin;
import java.sql.*;
import javax.swing.*;
import main.Main;

/**
 *
 * @author cdgan
 */
public class LogInProcess {

    Statement stmt;

    public int checkAcc(String user, String pass) throws SQLException, ClassNotFoundException {
        Main main = new Main();
        main.connectToDatabase();

        int flag = 0;
        stmt = (Statement) main.mc.createStatement();

        String userN = user;
        String userP = pass;

        String query = "select username, passw from staff";

        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
            if (userN.equals(rs.getString(1)) && userP.equals(rs.getString(2))) {
                if(userN.equals("Admin")){
                    flag = 1;
                } else{
                    flag = 2;
                }
                break;
            }
        }

        switch (flag) {
            case 1:
                JOptionPane.showMessageDialog(null, "Welcome. Our Team leader!");
                break;
            case 2:
                JOptionPane.showMessageDialog(null, "Welcome our team member!");
                break;
            default:
                JOptionPane.showMessageDialog(null, "There is no such an account");
                break;
        }
        System.out.println(flag);
        return flag;
    }

}
