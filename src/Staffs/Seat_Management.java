/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Staffs;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
/**
 *
 * @author Administrator
 */
public class Seat_Management extends javax.swing.JFrame implements MouseListener{

    Movie_List mlst = new Movie_List();
    public Seat_Management() throws SQLException {
        initComponents();
        setLocationRelativeTo(null);
        retreive_seat_count();
        add_lseat_button(lcount);
        add_mseat_button(mcount);
        add_rseat_button(rcount);
        
    }
    
    int scount;
    int lcount;
    int mcount = 21;
    int rcount;
    String mvt = "The Avengers";
    void retreive_seat_count() throws SQLException{
        Main_Staff ms = new Main_Staff();
        
        Statement stmt = ms.ucon.createStatement();
            
        String qry = "select * from movieshowtime_cinemadesig";
        ResultSet rs = stmt.executeQuery(qry);
        while(rs.next()){
            if(rs.getString(1).trim().equals(mvt)){
                scount = Integer.parseInt(rs.getString(7));
            }
        }
        lcount =(scount-21)/2;
        rcount = lcount;
    }
    
    //this methods add JRadioButton for seat_management
    void add_lseat_button( int sc){
        String cn="";
        ImageIcon seat_icon = new ImageIcon("seat.png");
        for(int i=1; i<=sc;i++){
            if(i<=9){
                cn = "L0" + Integer.toString(i);
            }
            else{
                cn = "L"+Integer.toString(i);
            }
            JRadioButton jr = new JRadioButton(cn,seat_icon);
            left_seat_panel.add(jr);
        }
    }
    void add_mseat_button( int sc){
        String cn="";
        ImageIcon seat_icon = new ImageIcon("seat.png");
        for(int i=1; i<=sc;i++){
            if(i<=9){
                cn = "M0" + Integer.toString(i);
            }
            else{
                cn = "M"+Integer.toString(i);
            }
            JRadioButton jr = new JRadioButton(cn,seat_icon);
            mid_seat_panel.add(jr);
        }
    }
    void add_rseat_button( int sc){
        String cn="";
        ImageIcon seat_icon = new ImageIcon("seat.png");
        for(int i=1; i<=sc;i++){
            if(i<=9){
                cn = "R0" + Integer.toString(i);
            }
            else{
                cn = "R" + Integer.toString(i);
            }
            JRadioButton jr = new JRadioButton(cn,seat_icon);
            right_seat_panel.add(jr);
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

        Left_yellow_Panel = new javax.swing.JPanel();
        profile_icon = new javax.swing.JLabel();
        staff_name = new javax.swing.JLabel();
        log_out = new javax.swing.JLabel();
        cart_panel = new javax.swing.JPanel();
        Cart_label = new javax.swing.JLabel();
        right_main_panel = new javax.swing.JPanel();
        panel_for_seats = new javax.swing.JPanel();
        left_seat_panel = new javax.swing.JPanel();
        mid_seat_panel = new javax.swing.JPanel();
        right_seat_panel = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Left_yellow_Panel.setBackground(new java.awt.Color(255, 204, 102));

        profile_icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/profile icon.png"))); // NOI18N

        staff_name.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        staff_name.setText("STAFF NAME");

        log_out.setFont(new java.awt.Font("Segoe UI", 0, 8)); // NOI18N
        log_out.setText("LOG OUT");

        Cart_label.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Cart_label.setText("CART");

        javax.swing.GroupLayout cart_panelLayout = new javax.swing.GroupLayout(cart_panel);
        cart_panel.setLayout(cart_panelLayout);
        cart_panelLayout.setHorizontalGroup(
            cart_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cart_panelLayout.createSequentialGroup()
                .addGap(111, 111, 111)
                .addComponent(Cart_label)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        cart_panelLayout.setVerticalGroup(
            cart_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cart_panelLayout.createSequentialGroup()
                .addComponent(Cart_label)
                .addContainerGap(356, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout Left_yellow_PanelLayout = new javax.swing.GroupLayout(Left_yellow_Panel);
        Left_yellow_Panel.setLayout(Left_yellow_PanelLayout);
        Left_yellow_PanelLayout.setHorizontalGroup(
            Left_yellow_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Left_yellow_PanelLayout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addComponent(profile_icon)
                .addGap(0, 0, 0)
                .addGroup(Left_yellow_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(staff_name)
                    .addComponent(log_out))
                .addContainerGap(70, Short.MAX_VALUE))
            .addGroup(Left_yellow_PanelLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(cart_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(10, 10, 10))
        );
        Left_yellow_PanelLayout.setVerticalGroup(
            Left_yellow_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Left_yellow_PanelLayout.createSequentialGroup()
                .addGroup(Left_yellow_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Left_yellow_PanelLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(staff_name)
                        .addGap(0, 0, 0)
                        .addComponent(log_out))
                    .addGroup(Left_yellow_PanelLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(profile_icon)))
                .addGap(16, 16, 16)
                .addComponent(cart_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        getContentPane().add(Left_yellow_Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 280, 480));

        right_main_panel.setBackground(new java.awt.Color(204, 204, 204));
        right_main_panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panel_for_seats.setBackground(new java.awt.Color(204, 204, 204));
        panel_for_seats.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 204, 102), 2));

        left_seat_panel.setBackground(new java.awt.Color(204, 204, 204));
        left_seat_panel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 15));

        mid_seat_panel.setBackground(new java.awt.Color(204, 204, 204));
        mid_seat_panel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 8));

        right_seat_panel.setBackground(new java.awt.Color(204, 204, 204));
        right_seat_panel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 15));

        javax.swing.GroupLayout panel_for_seatsLayout = new javax.swing.GroupLayout(panel_for_seats);
        panel_for_seats.setLayout(panel_for_seatsLayout);
        panel_for_seatsLayout.setHorizontalGroup(
            panel_for_seatsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_for_seatsLayout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addComponent(left_seat_panel, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(mid_seat_panel, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(right_seat_panel, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );
        panel_for_seatsLayout.setVerticalGroup(
            panel_for_seatsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_for_seatsLayout.createSequentialGroup()
                .addContainerGap(40, Short.MAX_VALUE)
                .addGroup(panel_for_seatsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(left_seat_panel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
                    .addComponent(mid_seat_panel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(right_seat_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        right_main_panel.add(panel_for_seats, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 122, 530, 310));

        jPanel5.setBackground(new java.awt.Color(153, 153, 153));
        jPanel5.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 50, 5));

        jLabel11.setText("Available Time");
        jPanel5.add(jLabel11);

        jLabel13.setText("time 2");
        jPanel5.add(jLabel13);

        jLabel14.setBackground(new java.awt.Color(102, 255, 51));
        jLabel14.setText("time 2");
        jPanel5.add(jLabel14);

        right_main_panel.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 40, 130, 80));

        jPanel6.setBackground(new java.awt.Color(204, 255, 204));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setText("DIto yung image");
        jPanel6.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, 67));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("Title: dito ang title");
        jPanel6.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 10, -1, -1));

        jLabel8.setText("Genre: Comedy, Sex");
        jPanel6.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 30, -1, -1));

        jLabel9.setText("Duration: 1hr 45 mins");
        jPanel6.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 50, -1, -1));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel10.setText("PRICE");
        jPanel6.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 20, -1, -1));

        right_main_panel.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 400, 80));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setText("SEATS");
        right_main_panel.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 10, -1, -1));

        jButton1.setText("Confirm");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        right_main_panel.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 450, -1, -1));

        jButton2.setText("Cancel");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        right_main_panel.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 450, -1, -1));

        jRadioButton2.setBackground(new java.awt.Color(255, 255, 255));
        jRadioButton2.setText("Available");
        jRadioButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/seat.png"))); // NOI18N
        right_main_panel.add(jRadioButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 450, -1, -1));

        jRadioButton3.setBackground(new java.awt.Color(255, 204, 102));
        jRadioButton3.setText("Unavailable");
        jRadioButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/seat.png"))); // NOI18N
        right_main_panel.add(jRadioButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 450, -1, -1));

        getContentPane().add(right_main_panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 0, 570, 480));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.setVisible(false);
        mlst.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Cart_label;
    private javax.swing.JPanel Left_yellow_Panel;
    private javax.swing.JPanel cart_panel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JPanel left_seat_panel;
    private javax.swing.JLabel log_out;
    private javax.swing.JPanel mid_seat_panel;
    private javax.swing.JPanel panel_for_seats;
    private javax.swing.JLabel profile_icon;
    private javax.swing.JPanel right_main_panel;
    private javax.swing.JPanel right_seat_panel;
    private javax.swing.JLabel staff_name;
    // End of variables declaration//GEN-END:variables

    @Override
    public void mouseClicked(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mousePressed(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseExited(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
