/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Staffs;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.geom.RoundRectangle2D;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
/**
 *
 * @author MIGUEL
 */
public class Payment_Method extends javax.swing.JFrame {

    /**
     * Creates new form Payment_Method
     */
    Main_Staff ms = new Main_Staff();
    public Payment_Method() throws SQLException, ParseException {
        initComponents();
        setLocationRelativeTo(null);
        
        this.setShape(new RoundRectangle2D.Double(0, 0, (850), 
        (480), 25, 25));
        
        left_panel_bg();
        right_panel_bg();
        
        receipt_scrollpane();
        get_last_ticketid();
        get_last_paymentid();
        get_ticklist_info();
        
        total_prc.setText(Double.toString(totalp));
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
    
    ArrayList<String> ticket_insert = new ArrayList();
    public void create_receipt_panel() throws SQLException, ParseException {
        JPanel receipt_panel1 = new JPanel();
        receipt_panel1.setPreferredSize(new Dimension(228, 268));
        receipt_panel1.setLayout(null);
        receipt_panel.add(receipt_panel1);

        JLabel rob = new JLabel("ROBINSON");
        receipt_panel1.add(rob);
        rob.setBounds(80, 0, 200, 30);
        rob.setFont(new Font("Segoe UI", Font.BOLD, 15));

        JLabel loc = new JLabel("ROBINSON PLACE MALOLOS");
        receipt_panel1.add(loc);
        loc.setBounds(30, 20, 200, 30);

        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
        String str = formatter.format(date);
        System.out.print("Current date: " + str);

        JLabel date_time = new JLabel("DATE & TIME: " + str);
        receipt_panel1.add(date_time);
        date_time.setBounds(5, 60, 200, 30);

        JLabel m_ttl = new JLabel("Title: " + mt);
        receipt_panel1.add(m_ttl);
        m_ttl.setBounds(5, 80, 200, 30);
        m_ttl.setFont(new Font("Segoe UI", Font.BOLD, 15));
        
        JLabel stime = new JLabel("Show Time: " + time);
        receipt_panel1.add(stime);
        stime.setBounds(5, 100, 200, 30);
        stime.setFont(new Font("Segoe UI", Font.PLAIN, 12));

        JLabel c_id = new JLabel("Cinema: " + cnm.charAt(9));
        receipt_panel1.add(c_id);
        c_id.setBounds(20, 125, 100, 30);
        c_id.setFont(new Font("Segoe UI", Font.BOLD, 11));

        JLabel s_num = new JLabel(stno);
        receipt_panel1.add(s_num);
        s_num.setBounds(135, 125, 100, 30);
        s_num.setFont(new Font("Segoe UI", Font.BOLD, 11));

        JPanel square = new JPanel();
        square.setLayout(null);
        receipt_panel1.add(square);
        square.setBackground(Color.WHITE);
        square.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        square.setSize(new Dimension(190, 70));
        square.setBounds(20, 150, 190, 70);

        JLabel price = new JLabel(prc);
        square.add(price);
        price.setBounds(5, 10, 105, 30);
        totalp+= Double.parseDouble(prc.substring(8));

        JLabel t_id = new JLabel("TICKET ID: " + newticketid);
        square.add(t_id);
        t_id.setBounds(5, 30, 105, 30);
        
        String mid="m1";
        Statement stmt_mid = ms.mc.createStatement();
        String qry_mid = "select m.title, m.movieid, st.showtimemovieid, st.showtimeid,st.starttime\n" +
                            "from movie m left join showtime st\n" +
                            "on m.movieid = st.showtimemovieid";
        
        ResultSet rsmid = stmt_mid.executeQuery(qry_mid);
        String ttime = convert_12hr_to_24hr(time);
        
        while(rsmid.next()){
            if(rsmid.getString(1).equalsIgnoreCase(mt)
                    && rsmid.getString(5).equals(ttime)){
                mid = rsmid.getString(2);
            }
        }
        
        String seatn = stno.substring(9);
        String cid = cnm.substring(8);
        String insertticket = "INSERT INTO ticket\n" +"VALUES \n" 
                    + "('"+newticketid+"','"+str+"','"+seatn+"','"+cid+"','"+
                    newpaymentid+"','"+mid+"')";
        
        System.out.println(insertticket);
        
        ticket_insert.add(insertticket);
        
//        JLabel total = new JLabel("Total: ");
//        total.setBounds(70, 330, 150, 30);
//        total.setFont(new Font("Segoe UI", Font.BOLD, 13));
//        cart_panel.add(total);
    }
    
    public void receipt_scrollpane(){
        main_receipt_panel.add(receipt_panel);
        receipt_panel.setLayout(new FlowLayout(FlowLayout.CENTER,0,5));
        receipt_panel.setPreferredSize(new Dimension(235,900));
        JScrollPane scrollPane = new JScrollPane(receipt_panel);
        scrollPane.setMinimumSize(new Dimension(5, 5));
        scrollPane.setPreferredSize(new Dimension(230,270));
        scrollPane.setBounds(5, 5, 255, 280);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setOpaque(false);
        main_receipt_panel.add(scrollPane);
        
    }
    
    String lsttid, newticketid, lstpaymentid, newpaymentid,payment_m,emplog;
    static int lnum,lnumpm;
    static double totalp = 0;
    
    void get_last_ticketid() throws SQLException {
        
        Statement stmt = ms.mc.createStatement();
        lsttid = "";
        
        String qry = "select * from ticket order by len(ticketID), ticketID";
        ResultSet rs = stmt.executeQuery(qry);
        while (rs.next()) {
            lsttid = rs.getString(1);
            System.out.println(rs.getString(1));

        }
        lnum = 1;//Integer.parseInt(lsttid.substring(1, lsttid.length()));
        String nmpd = "";
        newticketid = "T" + String.valueOf(lnum);
        System.out.println(newticketid);
    }
    void get_last_paymentid() throws SQLException{
        Statement stmt = ms.mc.createStatement();
        lstpaymentid = "";
        
        String qry = "select * from payment order by len(paymentid), paymentid";
        ResultSet rs = stmt.executeQuery(qry);
        while (rs.next()) {
            lstpaymentid = rs.getString(1);
            System.out.println(rs.getString(1));

        }
        lnumpm = Integer.parseInt(lstpaymentid.substring(1))+1;
        String nmpd = "";
        newpaymentid = "P" + String.valueOf(lnumpm);
        System.out.println(newpaymentid);
    }
    
    
    String mt, cnm, stno, time, prc;
    void get_ticklist_info() throws SQLException, ParseException{
        Component[] c = new Temp_Data().jp_mlist.getComponents();
        for(Component cp : c){
            
            JPanel jpl = (JPanel) cp;
            JLabel title = (JLabel) jpl.getComponent(0);
            mt = title.getText();
            
            JLabel amount = (JLabel) jpl.getComponent(1);
            prc = amount.getText();
            
            JLabel seat = (JLabel) jpl.getComponent(2);
            stno = seat.getText();
            
            JLabel cinema = (JLabel) jpl.getComponent(3);
            cnm = cinema.getText();
            
            JLabel tm = (JLabel) jpl.getComponent(4);
            time = tm.getText();
            
            newticketid = "T" + Integer.toString(lnum+1);
            
            create_receipt_panel();
            
            lnum++;

        }
    }
    
    void insert_whole_payment() throws SQLException{
        Statement stmt = ms.mc.createStatement();
        
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
        
        String in_payment ="INSERT INTO payment ([PaymentID],[PaymentMethod],[Amount],[PaymentDate],[EmployeeID])\n" +
                        "VALUES \n" +"('"+newpaymentid+"','"+payment_m+"',"
                        +totalp+",'"+timeStamp+"','"+emplog+"')";
        
        int in = stmt.executeUpdate(in_payment);
        if(in>0){
            System.out.println("Payment added to database");
        }
    }
    
    String convert_12hr_to_24hr(String tm) throws ParseException{
        SimpleDateFormat displayFormat = new SimpleDateFormat("HH:mm");
        SimpleDateFormat parseFormat = new SimpleDateFormat("hh:mm a");
        java.util.Date date = parseFormat.parse(tm);
        String real_time = displayFormat.format(date);
        real_time = real_time +":00.0000000";
        return real_time;
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
        icon = new javax.swing.JLabel();
        staff_name = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        cart_panel = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        main_receipt_panel = new javax.swing.JPanel();
        receipt_panel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        total_prc = new javax.swing.JLabel();
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
        amount_field = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        cancel_payment = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        rp_bg = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 204, 102));
        setMinimumSize(new java.awt.Dimension(850, 480));
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 204, 102));
        jPanel1.setPreferredSize(new java.awt.Dimension(280, 480));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/profile icon.png"))); // NOI18N
        icon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                iconMouseClicked(evt);
            }
        });
        jPanel1.add(icon, new org.netbeans.lib.awtextra.AbsoluteConstraints(73, 15, -1, -1));

        staff_name.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        staff_name.setText("STAFF NAME");
        staff_name.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                staff_nameMouseClicked(evt);
            }
        });
        jPanel1.add(staff_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 24, -1, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 8)); // NOI18N
        jLabel8.setText("LOG OUT");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 44, -1, -1));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setText("CART");

        main_receipt_panel.setPreferredSize(new java.awt.Dimension(260, 300));

        receipt_panel.setBackground(new java.awt.Color(255, 255, 255));
        receipt_panel.setPreferredSize(new java.awt.Dimension(228, 268));

        javax.swing.GroupLayout main_receipt_panelLayout = new javax.swing.GroupLayout(main_receipt_panel);
        main_receipt_panel.setLayout(main_receipt_panelLayout);
        main_receipt_panelLayout.setHorizontalGroup(
            main_receipt_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(main_receipt_panelLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(receipt_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );
        main_receipt_panelLayout.setVerticalGroup(
            main_receipt_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(main_receipt_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(receipt_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40))
        );

        jLabel2.setText("Total Amount:");

        total_prc.setText("0.00");

        javax.swing.GroupLayout cart_panelLayout = new javax.swing.GroupLayout(cart_panel);
        cart_panel.setLayout(cart_panelLayout);
        cart_panelLayout.setHorizontalGroup(
            cart_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cart_panelLayout.createSequentialGroup()
                .addGap(112, 112, 112)
                .addComponent(jLabel9))
            .addComponent(main_receipt_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(cart_panelLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(total_prc))
        );
        cart_panelLayout.setVerticalGroup(
            cart_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cart_panelLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jLabel9)
                .addGap(5, 5, 5)
                .addComponent(main_receipt_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(cart_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(total_prc))
                .addGap(28, 28, 28))
        );

        jPanel1.add(cart_panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 88, 260, 380));

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
        jPanel8.add(amount_field, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 270, 139, 30));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel13.setText("AMOUNT");
        jPanel8.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 270, -1, 30));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("Cards");
        jPanel8.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 130, -1, -1));

        jPanel2.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(79, 69, 412, 352));

        cancel_payment.setBackground(new java.awt.Color(204, 204, 204));
        cancel_payment.setText("Cancel");
        cancel_payment.setPreferredSize(new java.awt.Dimension(137, 30));
        cancel_payment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancel_paymentActionPerformed(evt);
            }
        });
        jPanel2.add(cancel_payment, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 430, 100, -1));

        jButton3.setBackground(new java.awt.Color(255, 204, 102));
        jButton3.setText("Proceed");
        jButton3.setPreferredSize(new java.awt.Dimension(137, 30));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 430, 100, -1));

        rp_bg.setMaximumSize(new java.awt.Dimension(570, 480));
        rp_bg.setMinimumSize(new java.awt.Dimension(570, 480));
        rp_bg.setPreferredSize(new java.awt.Dimension(570, 480));
        jPanel2.add(rp_bg, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 0, 570, 480));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cancel_paymentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancel_paymentActionPerformed

        try {
            new Movie_List().setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(Payment_Method.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        dispose();
    }//GEN-LAST:event_cancel_paymentActionPerformed
    
    static String payment = "";
    static String emp_log = "E1";
    private void GCashMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_GCashMouseClicked
       payment="E-Wallet";
       GCash gc = new GCash();
       gc.setVisible(true);
    }//GEN-LAST:event_GCashMouseClicked

    private void MayaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MayaMouseClicked
        payment="E-Wallet";
        PayMaya py= new PayMaya();
        py.setVisible(true);
    }//GEN-LAST:event_MayaMouseClicked

    private void BPIMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BPIMouseClicked
        BPI bp =new BPI();
        bp.setVisible(true);
        payment="Card";
    }//GEN-LAST:event_BPIMouseClicked

    private void BDOMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BDOMouseClicked
        BDO bd=new BDO();
        bd.setVisible(true);
        payment="Card";
    }//GEN-LAST:event_BDOMouseClicked

    private void UnionBankMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_UnionBankMouseClicked
        UnionBank ub= new UnionBank();
        ub.setVisible(true);
        payment="Card";
    }//GEN-LAST:event_UnionBankMouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        
        try {
            new Movie_List().setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(Payment_Method.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void iconMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_iconMouseClicked
        Staffs_Profile sp =new Staffs_Profile();
        sp.setVisible(true);
         this.dispose();
    }//GEN-LAST:event_iconMouseClicked

    private void staff_nameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_staff_nameMouseClicked
        Staffs_Profile sp =new Staffs_Profile();
        sp.setVisible(true);
         this.dispose();
    }//GEN-LAST:event_staff_nameMouseClicked
   
    // Add an action listener to the GCash label/icon

  



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel BDO;
    private javax.swing.JLabel BPI;
    private javax.swing.JLabel GCash;
    private javax.swing.JLabel Maya;
    private javax.swing.JLabel UnionBank;
    private javax.swing.JTextField amount_field;
    private javax.swing.JButton cancel_payment;
    private javax.swing.JPanel cart_panel;
    private javax.swing.JLabel icon;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JLabel lp_bg;
    private javax.swing.JPanel main_receipt_panel;
    private javax.swing.JPanel receipt_panel;
    private javax.swing.JLabel rp_bg;
    private javax.swing.JLabel staff_name;
    private javax.swing.JLabel total_prc;
    // End of variables declaration//GEN-END:variables
}
