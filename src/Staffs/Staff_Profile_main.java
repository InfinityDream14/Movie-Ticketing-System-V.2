/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Staffs;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.io.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author John Paul
 */
public class Staff_Profile_main extends javax.swing.JFrame {
       
    /**
     * Creates new form Staffs_Profile
     */
    Main_Staff ms = new Main_Staff();
    public Staff_Profile_main() throws SQLException{
        initComponents();
         setLocationRelativeTo(null);
        
        this.setShape(new RoundRectangle2D.Double(0, 0, (850), 
        (480), 25, 25));
        
        left_panel_bg();
        right_panel_bg();
        get_details_fromdb();
      
    }
     void left_panel_bg(){
        ImageIcon lpbg = new ImageIcon("lpbg.png");
        Image image = lpbg.getImage(); // transform it 
        Image newimg = image.getScaledInstance(570, 480,  java.awt.Image.SCALE_DEFAULT); // scale it the smooth way  
        lpbg = new ImageIcon(newimg);
        lp_bg.setIcon(lpbg);
    }
    void right_panel_bg(){

        ImageIcon rpbg = new ImageIcon("rpbg.png");
        Image image = rpbg.getImage(); // transform it 
        Image newimg = image.getScaledInstance(570, 480,  java.awt.Image.SCALE_DEFAULT); // scale it the smooth way  
        rpbg = new ImageIcon(newimg);
        rp_bg.setIcon(rpbg);
                
    }
    
    void move_pf_file() throws FileNotFoundException, SQLException{
        
        JFileChooser jfc = new JFileChooser();
        jfc.setCurrentDirectory(new File("D:\\Users\\Backup\\Desktop"));
        int response = jfc.showOpenDialog(null);
        String fildest = System.getProperty("user.dir" );
        fildest = fildest + "\\Staff Profile";
        String newpf = "pf_null.png";
        if(response == JFileChooser.APPROVE_OPTION){
            File file = new File(jfc.getSelectedFile().getAbsolutePath());
            System.out.println(file);
            newpf = file.toString().substring(file.toString().lastIndexOf("\\")+1);
            System.out.println(newpf);
            if(file.exists()){
                File destfile = new File(fildest + File.separator + file.getName());
                
                try(InputStream is = new FileInputStream(file);
                        OutputStream os = new FileOutputStream(destfile)){
                    
                    int len;
                    float srcfsize = is.available() / 1000.0f;
                    float totalcopied = 0.0f;
                    byte[] byt = new byte[1024];
                    while( (len = is.read(byt)) > 0){
                        os.write(byt, 0, len);
                        totalcopied += len;
                        System.out.println("\rcopied" + totalcopied / 1000.0f + "kb/" + file + "kb");
                        //Thread.sleep(5);
                    }
                    
                    
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
        }
        Statement stmpf = ms.mc.createStatement();
        
        String qry = "select * from staff";
        
        ResultSet rs = stmpf.executeQuery(qry);
        
        while(rs.next()){
            Statement stmpf1 = ms.mc.createStatement();
            
            if(rs.getString(1).equals(empid)){
                
                String pfin = "UPDATE staff set pf_loc = '"+newpf+"' where employeeid = '"+empid+"'";
                
                int row = stmpf1.executeUpdate(pfin);
                
                if(row>0){
                    System.out.println("Profile picture changed");
                }
            }
        }
        
    }
    static String empid = "E5";
    static String fname,lname,phone,email,passw,pf_loc,usern;
    void get_details_fromdb() throws SQLException{
        
        Statement stmt = ms.mc.createStatement();
        
        String qry = "select * from staff";
        
        ResultSet rs = stmt.executeQuery(qry);
        
        while(rs.next()){
            if(rs.getString(1).equals(empid)){
                fname = rs.getString(2);
                lname = rs.getString(3);
                email = rs.getString(4);
                phone = rs.getString(5);
                usern = rs.getString(6);
                passw = rs.getString(7);
                pf_loc = rs.getString(8);
            }
        }
        fnamejtx.setText(fname);
        staff_name.setText(fname);
        lnamejtx.setText(lname);
        emailjtx.setText(email);
        phonejtx.setText(phone);
        usernjtx.setText(usern);
        passwordpf.setText(passw);
        
        String fildest = System.getProperty("user.dir");
        ImageIcon im = new ImageIcon(fildest +"\\Staff Profile\\"+ pf_loc);
        Image image = im.getImage(); // transform it 
        Image newimg = image.getScaledInstance(90, 90,  java.awt.Image.SCALE_SMOOTH);
        ImageIcon nim =new ImageIcon(newimg);
        icon_prof.setIcon(nim);
           
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        icon_prof = new javax.swing.JLabel();
        staff_name = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lp_bg = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        profile = new javax.swing.JLabel();
        Edit_prof = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        passwordpf = new javax.swing.JPasswordField();
        userlabel = new javax.swing.JLabel();
        change_password_button = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        usernjtx = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        f_n = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        fnamejtx = new javax.swing.JTextField();
        lnamejtx = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        p_no1 = new javax.swing.JLabel();
        emailjtx = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        phonejtx = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        rp_bg = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 204, 102));
        jPanel1.setPreferredSize(new java.awt.Dimension(280, 480));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        icon_prof.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        icon_prof.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        icon_prof.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                icon_profMouseClicked(evt);
            }
        });
        jPanel1.add(icon_prof, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 110, 90, 90));

        staff_name.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        staff_name.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        staff_name.setText("Staff name");
        staff_name.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel1.add(staff_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 210, -1, -1));

        jLabel2.setText("Log out");
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pf_log_out(evt);
            }
        });
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 390, -1, -1));
        jPanel1.add(lp_bg, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 280, 480));

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        profile.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        profile.setText("Profile");
        jPanel2.add(profile, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 20, -1, 50));

        Edit_prof.setBackground(new java.awt.Color(255, 204, 102));
        Edit_prof.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Edit_prof.setText("Save Details");
        Edit_prof.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Edit_profActionPerformed(evt);
            }
        });
        jPanel2.add(Edit_prof, new org.netbeans.lib.awtextra.AbsoluteConstraints(236, 421, -1, -1));

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)), "Security", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(102, 102, 102))); // NOI18N

        passwordpf.setEditable(false);
        passwordpf.setText("Password");

        userlabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        userlabel.setText("username:");

        change_password_button.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        change_password_button.setText("change password");
        change_password_button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                change_password_buttonMouseClicked(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Password:");

        usernjtx.setBackground(new java.awt.Color(204, 204, 204));
        usernjtx.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        usernjtx.setText("username");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(passwordpf, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(change_password_button))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(userlabel, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(usernjtx)))
                .addGap(12, 12, 12))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(userlabel)
                    .addComponent(usernjtx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(passwordpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(change_password_button))
                .addContainerGap())
        );

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 320, 400, 90));

        jPanel4.setBackground(new java.awt.Color(204, 204, 204));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)), "Personal Details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(102, 102, 102))); // NOI18N

        f_n.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        f_n.setText("Frist Name:");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Last Name:");

        fnamejtx.setBackground(new java.awt.Color(204, 204, 204));
        fnamejtx.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        fnamejtx.setText("Staff Fname");

        lnamejtx.setBackground(new java.awt.Color(204, 204, 204));
        lnamejtx.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lnamejtx.setText("Staff Lname");
        lnamejtx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lnamejtxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(lnamejtx))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(f_n)
                        .addGap(18, 18, 18)
                        .addComponent(fnamejtx, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(f_n)
                    .addComponent(fnamejtx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(lnamejtx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 88, 400, -1));

        jPanel5.setBackground(new java.awt.Color(204, 204, 204));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)), "Contact", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(102, 102, 102))); // NOI18N

        p_no1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        p_no1.setText("Phone num:");

        emailjtx.setBackground(new java.awt.Color(204, 204, 204));
        emailjtx.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        emailjtx.setText("sample@gmail.com");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Email:");

        phonejtx.setBackground(new java.awt.Color(204, 204, 204));
        phonejtx.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        phonejtx.setText("09466885295");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(p_no1))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel4)))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(phonejtx)
                    .addComponent(emailjtx, javax.swing.GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(p_no1)
                    .addComponent(phonejtx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(emailjtx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 209, 400, -1));

        jLabel1.setForeground(new java.awt.Color(102, 102, 102));
        jLabel1.setText("Back");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 10, -1, -1));
        jPanel2.add(rp_bg, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 570, 480));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 0, 570, 480));

        setSize(new java.awt.Dimension(850, 480));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    static String newfname,newlname,newphone,newemail,newusern;
    private void Edit_profActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Edit_profActionPerformed
        try {
            Statement stmt = ms.mc.createStatement();
            String qry = "select * from staff";
            ResultSet rs = stmt.executeQuery(qry);
            
            newfname = fnamejtx.getText();
            newlname = lnamejtx.getText();
            newemail = emailjtx.getText();
            newphone = phonejtx.getText();
            newusern = usernjtx.getText();
            staff_name.setText(fnamejtx.getText());
            
            while(rs.next()){
                Statement stmt1 = ms.mc.createStatement();
                if(rs.getString(1).equals(empid)){
            String rsin = "UPDATE staff \n" +
                    "set Fname = '"+newfname+"', Lname = '"+newlname+"', email = '"+newemail+"',"
                    + " phone = '"+newphone+"', username = '"+newusern+"' where EmployeeID = '"+empid+"'";
            
            int up = stmt1.executeUpdate(rsin);
            if(up>0){
                System.out.println("Staff details updated");
                JOptionPane.showMessageDialog(null, "Success");
            }
                }
        }    
            
        } catch (SQLException ex) {
            Logger.getLogger(Staff_Profile_main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_Edit_profActionPerformed

    private void icon_profMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_icon_profMouseClicked
        try {      
            move_pf_file();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Staff_Profile_main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Staff_Profile_main.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.setVisible(true);
    }//GEN-LAST:event_icon_profMouseClicked

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
       try {
            new Movie_List().setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(Staff_Profile_main.class.getName()).log(Level.SEVERE, null, ex);
        }
        dispose();
    }//GEN-LAST:event_jLabel1MouseClicked

    private void change_password_buttonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_change_password_buttonMouseClicked
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1));

        JPasswordField oldPasswordField = new JPasswordField();
        JPasswordField newPasswordField = new JPasswordField();
        JPasswordField confirmPasswordField = new JPasswordField();

        panel.add(new JLabel("Enter your old password:"));
        panel.add(oldPasswordField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Change Password", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {

            char[] oldPasswordChars = oldPasswordField.getPassword();
            String oldPassword = new String(oldPasswordChars);


            if (oldPassword.equals(passw)) { 

                panel = new JPanel();
                panel.setLayout(new GridLayout(0, 1));

                panel.add(new JLabel("Enter your new password:"));
                panel.add(newPasswordField);
                panel.add(new JLabel("Confirm your new password:"));
                panel.add(confirmPasswordField);

                result = JOptionPane.showConfirmDialog(null, panel, "Change Password", JOptionPane.OK_CANCEL_OPTION);

                if (result == JOptionPane.OK_OPTION) {

                    char[] newPasswordChars = newPasswordField.getPassword();
                    char[] confirmedPasswordChars = confirmPasswordField.getPassword();
                    String newPassword = new String(newPasswordChars);
                    String confirmedPassword = new String(confirmedPasswordChars);


                    if (newPassword.equals(confirmedPassword)) {

                        System.out.println("New password: " + newPassword);
                        JOptionPane.showMessageDialog(null, "Password changed successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        passw = newPassword;
                        
                        try {
                            Statement stmt = ms.mc.createStatement();
                            String updpass = "UPDATE staff\n" +
                                        "set passw = '"+passw+"'\n" +
                                        "where employeeid = '"+empid+"'";
                        
                            int up = stmt.executeUpdate(updpass);

                            if(up>0)
                                System.out.println("Password Updated");
                        } catch (SQLException ex) {
                            Logger.getLogger(Staff_Profile_main.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                        
                        
                        
                    } else {
                        JOptionPane.showMessageDialog(null, "Passwords do not match!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Incorrect old password!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_change_password_buttonMouseClicked

    private void lnamejtxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lnamejtxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lnamejtxActionPerformed

    private void pf_log_out(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pf_log_out
        System.exit(0);
    }//GEN-LAST:event_pf_log_out
      
    /**
     * @param args the command line arguments
     */
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Edit_prof;
    private javax.swing.JLabel change_password_button;
    private javax.swing.JTextField emailjtx;
    private javax.swing.JLabel f_n;
    private javax.swing.JTextField fnamejtx;
    private javax.swing.JLabel icon_prof;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JTextField lnamejtx;
    private javax.swing.JLabel lp_bg;
    private javax.swing.JLabel p_no1;
    private javax.swing.JPasswordField passwordpf;
    private javax.swing.JTextField phonejtx;
    private javax.swing.JLabel profile;
    private javax.swing.JLabel rp_bg;
    private javax.swing.JLabel staff_name;
    private javax.swing.JLabel userlabel;
    private javax.swing.JTextField usernjtx;
    // End of variables declaration//GEN-END:variables
}