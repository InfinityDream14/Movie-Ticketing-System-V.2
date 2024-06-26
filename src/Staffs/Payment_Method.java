/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Staffs;
import java.awt.Image;
import java.awt.geom.RoundRectangle2D;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.swing.*;
/**
 *
 * @author MIGUEL
 */
public class Payment_Method extends javax.swing.JFrame {

    /**
     * Creates new form Payment_Method
     */
    public Payment_Method() {
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
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        lp_bg = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        GCash = new javax.swing.JLabel();
        Maya = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        BDO = new javax.swing.JLabel();
        BPI = new javax.swing.JLabel();
        UnionBank = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        rp_bg = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 204, 102));
        setMinimumSize(new java.awt.Dimension(850, 480));
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 204, 102));
        jPanel1.setPreferredSize(new java.awt.Dimension(280, 480));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/profile icon.png"))); // NOI18N
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(73, 15, -1, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("STAFF NAME");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 24, -1, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 8)); // NOI18N
        jLabel8.setText("LOG OUT");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 44, -1, -1));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setText("CART");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(112, 112, 112)
                .addComponent(jLabel9)
                .addGap(120, 120, 120))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addContainerGap(350, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 88, 260, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));
        getContentPane().add(lp_bg, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 280, 480));

        jPanel2.setPreferredSize(new java.awt.Dimension(570, 480));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel10.setText("Payment");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(235, 19, -1, -1));

        jPanel8.setBackground(new java.awt.Color(246, 246, 246));
        jPanel8.setMaximumSize(new java.awt.Dimension(412, 345));
        jPanel8.setMinimumSize(new java.awt.Dimension(412, 345));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("E-Wallets");
        jPanel8.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 10, -1, -1));

        GCash.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/Gcash70.png"))); // NOI18N
        GCash.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                GCashMouseClicked(evt);
            }
        });
        jPanel8.add(GCash, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 40, -1, -1));

        Maya.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/Maya-70.png"))); // NOI18N
        Maya.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MayaMouseClicked(evt);
            }
        });
        jPanel8.add(Maya, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 40, -1, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Cash");
        jPanel8.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 240, -1, -1));

        BDO.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/Bdo-70.png"))); // NOI18N
        BDO.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BDOMouseClicked(evt);
            }
        });
        jPanel8.add(BDO, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 160, -1, -1));

        BPI.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/Bpi-70.png"))); // NOI18N
        BPI.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BPIMouseClicked(evt);
            }
        });
        jPanel8.add(BPI, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 160, -1, -1));

        UnionBank.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/Unionbak-70.png"))); // NOI18N
        UnionBank.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                UnionBankMouseClicked(evt);
            }
        });
        jPanel8.add(UnionBank, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 160, -1, -1));
        jPanel8.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 270, 139, 30));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel13.setText("AMOUNT");
        jPanel8.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 270, -1, 30));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("Cards");
        jPanel8.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 130, -1, -1));

        jPanel2.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(79, 69, 412, 352));

        jButton2.setBackground(new java.awt.Color(255, 204, 102));
        jButton2.setText("Proceed");
        jButton2.setPreferredSize(new java.awt.Dimension(137, 30));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(235, 432, 100, -1));

        rp_bg.setMaximumSize(new java.awt.Dimension(570, 480));
        rp_bg.setMinimumSize(new java.awt.Dimension(570, 480));
        rp_bg.setPreferredSize(new java.awt.Dimension(570, 480));
        jPanel2.add(rp_bg, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 0, 570, 480));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
    
    }//GEN-LAST:event_jButton2ActionPerformed

    private void GCashMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_GCashMouseClicked
       GCash gc = new GCash();
       gc.setVisible(true);
    }//GEN-LAST:event_GCashMouseClicked

    private void MayaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MayaMouseClicked
        PayMaya py= new PayMaya();
        py.setVisible(true);
        
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;instance=COMPUTERBERRY;databaseName=Database", "sa", "Java");
            
            String sql = "insert into payment values (?,?,?,?,?)";
            
            PreparedStatement ptsmt = conn.prepareStatement(sql);
            
            ptsmt.setString(1, "");
            ptsmt.setString(2, Maya.getText());
            ptsmt.setString(3, "");
            ptsmt.setString(4, "");
            ptsmt.setString(5, "");
            
            ptsmt.executeUpdate();
        }
        catch(Exception e){
            
        }
    }//GEN-LAST:event_MayaMouseClicked

    private void BPIMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BPIMouseClicked
        BPI bp =new BPI();
        bp.setVisible(true);
    }//GEN-LAST:event_BPIMouseClicked

    private void BDOMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BDOMouseClicked
        BDO bd=new BDO();
        bd.setVisible(true);
    }//GEN-LAST:event_BDOMouseClicked

    private void UnionBankMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_UnionBankMouseClicked
        UnionBank ub= new UnionBank();
        ub.setVisible(true);
    }//GEN-LAST:event_UnionBankMouseClicked
   
    // Add an action listener to the GCash label/icon

  



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel BDO;
    private javax.swing.JLabel BPI;
    private javax.swing.JLabel GCash;
    private javax.swing.JLabel Maya;
    private javax.swing.JLabel UnionBank;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel lp_bg;
    private javax.swing.JLabel rp_bg;
    // End of variables declaration//GEN-END:variables
}
