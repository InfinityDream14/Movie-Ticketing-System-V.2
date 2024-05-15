
package Staffs;

import java.sql.*;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import main.Main;


public class Main_Staff {
    Main m = new Main();
    static Connection mc = Main.mc;
    
    public static void main(String[] args) throws ClassNotFoundException, SQLException, ParseException {
        
       
           new Movie_List().setVisible(true);
           //new Seat_Management().setVisible(true);
           //new Payment_Method().setVisible(true);
           //new Staffs_Profile().setVisible(true);
           //new Staff_Name().setVisible(true);
        
    }
    
}
