
package Staffs;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import java.sql.*;
import java.text.ParseException;
/**
 *
 * @author Administrator
 */
public class Movie_List extends javax.swing.JFrame {

    //Seat_Management sm = new Seat_Management();
    //Payment_Method pm= new Payment_Method();
    static Temp_Data tempd = new Temp_Data();
    
    public Movie_List() throws SQLException{
        initComponents();
        setLocationRelativeTo(null);
        
        this.setShape(new RoundRectangle2D.Double(0, 0, (850), 
        (480), 25, 25));
        
        left_panel_bg();
        right_panel_bg();
        create_movie_list_panel();
        //create_ticket_list();
        ticklist_scrollpane();
        
        components = tempd.jp_mlist.getComponents();
        try{
            if(components.length !=0){
                for(Component c : components){
                    receipt_panel.add(c);
                    System.out.println("Nakuha na ng movie list: " + c.getName());
                }
                receipt_panel.revalidate();
                receipt_panel.repaint();
            }
        }catch(Exception e){
            e.getStackTrace();
        }
    }
    static String title_to_sm;
    static String genre_to_sm;
    static Component[] components = {};
    String st1, cid, mprc, mimgloc;
    String tixid = "T1";
    
    String ticket_title = "The Avengers";
    public void create_movie_list_panel() throws SQLException {
        Main_Staff ms = new Main_Staff();
        
        Statement stmt = ms.mc.createStatement();
            
            String qry = "select * from movie";
            ResultSet rs = stmt.executeQuery(qry);
            while(rs.next()){
                String mposter = rs.getString(7);
                ImageIcon m1 = new ImageIcon(mposter);
                JLabel mve1 = new JLabel(m1);
                String mtitle = rs.getString(2);
                JLabel mt = new JLabel(mtitle);
                String mgenre = rs.getString(3);
                JLabel gr = new JLabel("Genre: " + mgenre);
                JPanel movie_panel1 = new JPanel();
                movie_panel1.setPreferredSize(new Dimension(160,225));
                movie_panel1.setLayout(new FlowLayout(FlowLayout.CENTER,20,2));
                
                movie_panel1.add(mve1);
                movie_panel1.add(mt);
                movie_panel1.add(gr);
                
                movie_panel.add(movie_panel1);
                
                movie_panel1.addMouseListener(new MouseAdapter(){
                    @Override
                    public void mouseClicked(MouseEvent e){
                        try {
                            JLabel jt = (JLabel)movie_panel1.getComponent(1);
                            title_to_sm = jt.getText();
                            JLabel jg = (JLabel)movie_panel1.getComponent(2);
                            genre_to_sm = jg.getText();
                            send_receipt_panel_comp();
                            try {
                                Seat_Management sm = new Seat_Management();
                                sm.setVisible(true);
                            } catch (ParseException ex) {
                                Logger.getLogger(Movie_List.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            dispose();
                        } catch (SQLException ex) {
                            Logger.getLogger(Movie_List.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
            }
            
            main_panel.add(movie_panel);
            movie_panel.setLayout(new FlowLayout(FlowLayout.LEFT,0,5));
            movie_panel.setPreferredSize(new Dimension(500,2000));
            //movie_panel.setBounds(10, 50, 280,530);
            JScrollPane scrollPane = new JScrollPane(movie_panel);
            scrollPane.setMinimumSize(new Dimension(10, 10));
            scrollPane.setPreferredSize(new Dimension(500,300));
            scrollPane.setBounds(15, 15, 500, 335);
            scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
            scrollPane.setOpaque(false);
            main_panel.add(scrollPane);
        }
    
    public void send_receipt_panel_comp(){
        
        Component[] c = receipt_panel.getComponents();
        for(Component cp : c){
            tempd.jp_mlist.add(cp);
            System.out.println("nalagay na ulit sa tempd for seat: " + cp.getName());
        }
        
    }
    
    void left_panel_bg(){
        ImageIcon rpbg = new ImageIcon("lpbg.png");
        Image image = rpbg.getImage(); // transform it 
        Image newimg = image.getScaledInstance(570, 480,  java.awt.Image.SCALE_DEFAULT); // scale it the smooth way  
        rpbg = new ImageIcon(newimg);
        lp_bg.setIcon(rpbg);
    }
    void right_panel_bg(){

        ImageIcon rpbg = new ImageIcon("rpbg.png");
        Image image = rpbg.getImage(); // transform it 
        Image newimg = image.getScaledInstance(570, 480,  java.awt.Image.SCALE_DEFAULT); // scale it the smooth way  
        rpbg = new ImageIcon(newimg);
        rp_bg.setIcon(rpbg);
                
    }
    
    public void ticklist_scrollpane() {
        main_receipt_panel.add(receipt_panel);
        receipt_panel.setLayout(new FlowLayout(FlowLayout.CENTER,5,5));
        receipt_panel.setPreferredSize(new Dimension(228,900));
        JScrollPane scrollPane = new JScrollPane(receipt_panel);
        scrollPane.setMinimumSize(new Dimension(5, 5));
        scrollPane.setPreferredSize(new Dimension(248,327));
        scrollPane.setBounds(5, 5, 248, 327);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setOpaque(false);
        main_receipt_panel.add(scrollPane);
    }
    
    public void create_ticket_list(JRadioButton rb){
        JPanel receipt_panel1 = new JPanel();
        receipt_panel1.setPreferredSize(new Dimension(225,95));
        receipt_panel1.setLayout(null);
        receipt_panel1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        receipt_panel1.setBackground(Color.WHITE);
        receipt_panel.add(receipt_panel1);
        
        JLabel m_title = new JLabel(ticket_title);
        JLabel s_num = new JLabel("Seat No:" + rb.getText());
        JLabel amt = new JLabel("Amount:" + mprc);
        m_title.setBounds(100, 20, 80, 20);
        s_num.setBounds(100, 40, 80, 20);
        amt.setBounds(100, 60, 80, 20);
        receipt_panel1.add(m_title);
        receipt_panel1.add(amt);
        receipt_panel1.add(s_num);
        
        JPanel image_panel =new JPanel();
        receipt_panel1.add(image_panel);
        image_panel.setLayout(new FlowLayout(FlowLayout.CENTER,1,-7));
        image_panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        image_panel.setPreferredSize(new Dimension(89, 85));
        image_panel.setBounds(5, 5, 89, 85);
        ImageIcon image = new ImageIcon(mimgloc);
        JLabel new_image = new JLabel(image);
        new_image.setSize(89, 85);
        image_panel.add(new_image);
    }
    
    public void add_movie_comp_to_receipt_panel() throws SQLException, ParseException{
        
        for(Component c : components){
            receipt_panel.add(c);
        }
        receipt_panel.revalidate();
        receipt_panel.repaint();
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
        prof_icon = new javax.swing.JLabel();
        staff_name = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cart_panel = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        main_receipt_panel = new javax.swing.JPanel();
        receipt_panel = new javax.swing.JPanel();
        lp_bg = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        main_panel = new javax.swing.JPanel();
        movie_panel = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        p_to_payment = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        rp_bg = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setSize(new java.awt.Dimension(0, 0));
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                exit_staff(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 204, 102));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        prof_icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/profile icon.png"))); // NOI18N
        prof_icon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                prof_iconMouseClicked(evt);
            }
        });
        jPanel1.add(prof_icon, new org.netbeans.lib.awtextra.AbsoluteConstraints(73, 15, -1, -1));

        staff_name.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        staff_name.setText("STAFF NAME");
        staff_name.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                staff_nameMouseClicked(evt);
            }
        });
        jPanel1.add(staff_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 24, -1, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 8)); // NOI18N
        jLabel3.setText("LOG OUT");
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 44, -1, -1));

        cart_panel.setPreferredSize(new java.awt.Dimension(280, 386));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("CART");
        cart_panel.add(jLabel4);

        main_receipt_panel.setPreferredSize(new java.awt.Dimension(260, 348));

        receipt_panel.setBackground(new java.awt.Color(255, 255, 255));
        receipt_panel.setPreferredSize(new java.awt.Dimension(248, 327));
        main_receipt_panel.add(receipt_panel);

        cart_panel.add(main_receipt_panel);

        jPanel1.add(cart_panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 88, 260, 380));
        jPanel1.add(lp_bg, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 280, 480));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 280, 480));

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        main_panel.setBackground(new java.awt.Color(246, 246, 246));
        main_panel.setPreferredSize(new java.awt.Dimension(530, 365));

        movie_panel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        movie_panel.setPreferredSize(new java.awt.Dimension(500, 100));
        movie_panel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        javax.swing.GroupLayout main_panelLayout = new javax.swing.GroupLayout(main_panel);
        main_panel.setLayout(main_panelLayout);
        main_panelLayout.setHorizontalGroup(
            main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(main_panelLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(movie_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );
        main_panelLayout.setVerticalGroup(
            main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(main_panelLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(movie_panel, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jPanel2.add(main_panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, -1, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel5.setText("AVAILABLE MOVIES");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(173, 20, -1, -1));

        p_to_payment.setBackground(new java.awt.Color(255, 204, 102));
        p_to_payment.setText("Proceed to Payment");
        p_to_payment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                p_to_paymentActionPerformed(evt);
            }
        });
        jPanel2.add(p_to_payment, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 440, -1, -1));

        jButton1.setBackground(new java.awt.Color(204, 204, 204));
        jButton1.setText("Reset");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reset_selected(evt);
            }
        });
        jPanel2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 440, -1, -1));

        rp_bg.setBackground(new java.awt.Color(204, 204, 204));
        jPanel2.add(rp_bg, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 570, 480));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 0, 570, 480));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void reset_selected(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reset_selected
        if(receipt_panel.getComponents().length !=0){
            JOptionPane.showMessageDialog(null,
                    "The cart will cleared", "System Notice", JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            JOptionPane.showMessageDialog(null,
                    "There's no item in cart", "System Notice", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_reset_selected

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        try {
            new Movie_List().setVisible(false);
        } catch (SQLException ex) {
            Logger.getLogger(Movie_List.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            try {
                new Seat_Management().setVisible(true);
            } catch (ParseException ex) {
                Logger.getLogger(Movie_List.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Movie_List.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jLabel5MouseClicked

    private void jPanel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel5MouseClicked
        try {
            new Movie_List().setVisible(false);
        } catch (SQLException ex) {
            Logger.getLogger(Movie_List.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            try {
                new Seat_Management().setVisible(true);
            } catch (ParseException ex) {
                Logger.getLogger(Movie_List.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Movie_List.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jPanel5MouseClicked
 
    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        System.exit(0);
    }//GEN-LAST:event_jLabel3MouseClicked

    private void exit_staff(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_exit_staff
        if(evt.getKeyCode() == KeyEvent.VK_END){
             System.exit(0);
         }
    }//GEN-LAST:event_exit_staff

    private void prof_iconMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_prof_iconMouseClicked
        try {
            Staff_Profile_main spm =new Staff_Profile_main();
            spm.setVisible(true);
            this.dispose();
        } catch (SQLException ex) {
            Logger.getLogger(Movie_List.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_prof_iconMouseClicked

    private void staff_nameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_staff_nameMouseClicked
        try {
            Staff_Profile_main spm =new Staff_Profile_main();
            spm.setVisible(true);
            this.dispose();
        } catch (SQLException ex) {
            Logger.getLogger(Movie_List.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_staff_nameMouseClicked

    private void p_to_paymentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_p_to_paymentActionPerformed

        if(receipt_panel.getComponents().length !=0){
            send_receipt_panel_comp();
            Payment_Method pm;
            try {
                pm = new Payment_Method();
                pm.setVisible(true);
            } catch (SQLException ex) {
                Logger.getLogger(Movie_List.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(Movie_List.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            this.dispose();
        }
        else{
            JOptionPane.showMessageDialog(null,
                    "Please add to cart first", "System Notice", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_p_to_paymentActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel cart_panel;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lp_bg;
    private javax.swing.JPanel main_panel;
    private javax.swing.JPanel main_receipt_panel;
    private javax.swing.JPanel movie_panel;
    private javax.swing.JButton p_to_payment;
    private javax.swing.JLabel prof_icon;
    private javax.swing.JPanel receipt_panel;
    private javax.swing.JLabel rp_bg;
    private javax.swing.JLabel staff_name;
    // End of variables declaration//GEN-END:variables

}
