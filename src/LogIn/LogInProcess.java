/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LogIn;

import Admin.Admin;
import javax.swing.JOptionPane;

/**
 *
 * @author cdgan
 */
public class LogInProcess extends LogIn {

    public void checkAcc(String user, String pass) {
        System.out.println(user +", "+pass);
        if (user.equals("Admin") && user.equals("Admin")) {
            JOptionPane.showMessageDialog(null, "You are an admin!");
            Admin.main(null);
            dispose();
        } else{
            JOptionPane.showMessageDialog(null,"You entered wrong data!", "Wrong data input", JOptionPane.WARNING_MESSAGE);
        }
        lUserIn.setText("");
        lPassIn.setText("");
    }
}
