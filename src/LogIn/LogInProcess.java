/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LogIn;

import javax.swing.JOptionPane;

/**
 *
 * @author cdgan
 */
public class LogInProcess extends LogIn {

    public void checkAcc() {
        System.out.println(lUserIn.getText() +", "+lPassIn.getText());
        if (lUserIn.getText().equals("Admin")) {
            System.out.println("Hello World");
            JOptionPane.showMessageDialog(null, "You are an admin!");
        }
    }
}
