/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Staffs;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.io.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author John Paul
 */
public class Staffs_Profile extends javax.swing.JFrame {

    /**
     * Creates new form Staffs_Profile
     */
    public Staffs_Profile(){
        initComponents();
         setLocationRelativeTo(null);
        
        this.setShape(new RoundRectangle2D.Double(0, 0, (850), 
        (480), 25, 25));
        
        left_panel_bg();
        right_panel_bg();
      
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
    
    void move_pf_file() throws FileNotFoundException{
        
        JFileChooser jfc = new JFileChooser();
        jfc.setCurrentDirectory(new File("D:\\Users\\Backup\\Desktop"));
        int response = jfc.showOpenDialog(null);
        String fildest = System.getProperty("user.dir");
        
        if(response == JFileChooser.APPROVE_OPTION){
            File file = new File(jfc.getSelectedFile().getAbsolutePath());
            System.out.println(file);
            
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
                        Thread.sleep(5);
                    }
                    
                    
                }catch(IOException | InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
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
        Log_out = new javax.swing.JButton();
        icon_prof = new javax.swing.JLabel();
        staff_name = new javax.swing.JLabel();
        lp_bg = new javax.swing.JLabel();
        back = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        rp_bg = new javax.swing.JLabel();
        profile = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        fname = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        lname = new javax.swing.JTextField();
        f_n = new javax.swing.JLabel();
        email = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        pass = new javax.swing.JTextField();
        p_no = new javax.swing.JLabel();
        Edit_prof = new javax.swing.JButton();
        edit_name = new javax.swing.JLabel();
        jPasswordField1 = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 204, 102));
        jPanel1.setPreferredSize(new java.awt.Dimension(280, 480));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Log_out.setBackground(new java.awt.Color(255, 204, 102));
        Log_out.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        Log_out.setForeground(new java.awt.Color(255, 255, 255));
        Log_out.setText("Log out");
        Log_out.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Log_outMouseClicked(evt);
            }
        });
        jPanel1.add(Log_out, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 400, -1, -1));

        icon_prof.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/profile icon.png"))); // NOI18N
        icon_prof.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                icon_profMouseClicked(evt);
            }
        });
        jPanel1.add(icon_prof, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 140, 80, 80));

        staff_name.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        staff_name.setText("Staff name");
        jPanel1.add(staff_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 210, -1, -1));
        jPanel1.add(lp_bg, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        back.setText("BACK");
        back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backActionPerformed(evt);
            }
        });
        jPanel1.add(back, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 280, 480));

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));

        rp_bg.setPreferredSize(new java.awt.Dimension(570, 480));

        profile.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        profile.setText("Profile");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Email:");

        fname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fnameActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Password:");

        lname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lnameActionPerformed(evt);
            }
        });

        f_n.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        f_n.setText("Frist Name:");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Last Name");

        p_no.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        p_no.setText("Phone no.");

        Edit_prof.setBackground(new java.awt.Color(255, 204, 102));
        Edit_prof.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Edit_prof.setForeground(new java.awt.Color(255, 255, 255));
        Edit_prof.setText("Edit Profile");
        Edit_prof.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Edit_profActionPerformed(evt);
            }
        });

        edit_name.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/seat.png"))); // NOI18N

        jPasswordField1.setText("jPasswordField1");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(Edit_prof)
                .addGap(188, 188, 188))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(280, 280, 280)
                        .addComponent(profile, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(91, 91, 91)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(f_n)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel5)))
                            .addComponent(p_no, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(fname, javax.swing.GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE)
                            .addComponent(lname)
                            .addComponent(email)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jPasswordField1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                                .addComponent(pass, javax.swing.GroupLayout.Alignment.LEADING)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(edit_name)
                .addContainerGap(65, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(rp_bg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(profile, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(f_n)
                    .addComponent(edit_name))
                .addGap(35, 35, 35)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(35, 35, 35)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(35, 35, 35)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(p_no))
                .addGap(35, 35, 35)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addComponent(Edit_prof)
                .addContainerGap(47, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(rp_bg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 0, 570, 480));

        setSize(new java.awt.Dimension(850, 480));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void fnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fnameActionPerformed

    private void lnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lnameActionPerformed

    private void Edit_profActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Edit_profActionPerformed
           if(!fname.getText().equals("") && !lname.getText().equals("")&& !email.getText().equals("")&& !pass.getText().equals("")&& !phone_no.getText().equals("")){
            fname.getText();
            lname.getText();
            email.getText();
            pass.getText();
            phone_no.getText();
        }
        JOptionPane.showMessageDialog(null, "Confirm");
        System.out.println("First Name:"+ fname.getText());
        System.out.println("Last Name:"+ lname.getText());
        System.out.println("Email:"+email.getText());
        System.out.println("Password:"+ pass.getText());
        System.out.println("Phone No."+phone_no.getText());
    }//GEN-LAST:event_Edit_profActionPerformed

    private void Log_outMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Log_outMouseClicked
       this.dispose();
    }//GEN-LAST:event_Log_outMouseClicked

    private void backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backActionPerformed
        try {
            new Movie_List().setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(Staffs_Profile.class.getName()).log(Level.SEVERE, null, ex);
        }
        dispose();
    }//GEN-LAST:event_backActionPerformed

    private void icon_profMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_icon_profMouseClicked
        try {      
            move_pf_file();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Staffs_Profile.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }//GEN-LAST:event_icon_profMouseClicked

    /**
     * @param args the command line arguments
     */
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Edit_prof;
    private javax.swing.JButton Log_out;
    private javax.swing.JButton back;
    private javax.swing.JLabel edit_name;
    private javax.swing.JTextField email;
    private javax.swing.JLabel f_n;
    private javax.swing.JTextField fname;
    private javax.swing.JLabel icon_prof;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JTextField lname;
    private javax.swing.JLabel lp_bg;
    private javax.swing.JLabel p_no;
    private javax.swing.JTextField pass;
    private javax.swing.JLabel profile;
    private javax.swing.JLabel rp_bg;
    private javax.swing.JLabel staff_name;
    // End of variables declaration//GEN-END:variables
}