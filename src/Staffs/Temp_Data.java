
package Staffs;

import java.awt.*;
import javax.swing.*;
import java.sql.*;

public class Temp_Data {
    
    Main_Staff ms = new Main_Staff();
    public static JPanel jp_mlist = new JPanel();
    public static JPanel jp_rlist = new JPanel();
    public static Component[] rcp = {};
    public static String empid = "E5";
    public static String staff_pf;
    public static ImageIcon stf_img_pf;
    public static int stopper =0;
    public  double total_amount;
    
    void Temp_Data() throws SQLException{
        get_staff_profile();
    }
    
    void get_staff_profile() throws SQLException{
        
        Statement stmt = ms.mc.createStatement();
        
        String qry = "select * from staff";
        
        ResultSet rs = stmt.executeQuery(qry);
        
        while(rs.next()){
            
            if(rs.getString(1).equals(empid)){
                staff_pf =rs.getString(8);
            }
        }
        
        ImageIcon mi = new ImageIcon(staff_pf);
        Image image = mi.getImage(); // transform it 
        Image newimg = image.getScaledInstance(57, 57,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
        stf_img_pf = new ImageIcon(newimg); 
        
    }
}
