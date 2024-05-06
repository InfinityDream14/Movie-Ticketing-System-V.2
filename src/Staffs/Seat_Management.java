/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Staffs;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import java.lang.System.*;
import javax.swing.*;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
/**
 *
 * @author Administrator
 */
public class Seat_Management extends javax.swing.JFrame implements MouseListener{

    //Movie_List mlst = new Movie_List();

    Movie_List mlst = new Movie_List();
    public Seat_Management() throws SQLException, ParseException {
        initComponents();
        setLocationRelativeTo(null);
        
        this.setShape(new RoundRectangle2D.Double(0, 0, (850), 
        (480), 25, 25));
        
        right_panel_bg(); // putting image background to right panel
        left_panel_bg(); // putting image background to left panel
        get_info_in_database();
        
        create_receipt_panel();
    }
    

    String mvt = mlst.title_to_sm;
    String mvg = mlst.genre_to_sm;
    static String mvp;
    static String mvd;
    
    Main_Staff ms = new Main_Staff();
    Statement stmt = ms.mc.createStatement();
    //this method will get the exact seat count of a cinema where a movie will be played
    
    String qry = "select m.Title, m.MovieID, st.ShowtimeMovieID ,st.startTime, st.ShowtimeCinemaID,\n" +
                    "        c.CinemaID, c.NumofSteats, m.price, st.showtimeID\n" +
                    "from cinema c inner join(movie m inner join showtime st\n" +
                    "              on m.MovieID = st.ShowtimeMovieID)\n" +
                    "     on c.CinemaID = st.ShowtimeCinemaID";
    
    void get_info_in_database() throws SQLException, ParseException{
         
        
        sm_mtitle.setText(mvt);
        sm_mgenre.setText(mvg);
        String t1,t2;
        ResultSet rs = stmt.executeQuery(qry);
        while(rs.next()){
            if(rs.getString(1).trim().equals(mvt)){
                String ttime = rs.getString(4);
                ttime = ttime.substring(0,5);
                String result = LocalTime.parse(ttime, DateTimeFormatter.ofPattern("HH:mm"))
                            .format(DateTimeFormatter.ofPattern("hh:mm a"));
                if(av_time1.getText().equals("unavailable")){
                    av_time1.setText(result);
                }
                if(av_time2.getText().equals("unavailable")&& !(av_time1.getText().equals(result))){
                    av_time2.setText(result);
                }
            }
        }

        String qry1 = "select * from movie";
        
        ResultSet rs1 = stmt.executeQuery(qry1);
        while(rs1.next()){
            if(rs1.getString(2).trim().equals(mvt)){
                String imgloc = rs1.getString(7);
                ImageIcon mi = new ImageIcon(imgloc);
                Image image = mi.getImage(); // transform it 
                Image newimg = image.getScaledInstance(100, 80,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
                mi = new ImageIcon(newimg);  // transform it back
                sm_image_slot.setIcon(mi);
                sm_image_slot.setText("");
                mvp = rs1.getString(6);
                mvp = mvp.substring(0, 3);
                mvd = rs1.getString(5);
                mvd = "Duration: "+mvd.charAt(0) +" hr "+mvd.substring(2,4) +" mins";
                break;
            }
        }
        sm_mduration.setText(mvd);
        sm_mprice.setText(mvp);
        av_time1.setBackground(new Color(255,204,102));
        av_time2.setBackground(Color.LIGHT_GRAY);
        
        String real_time = convert_12hr_to_24hr(av_time1.getText());
        
        change_seat_list(real_time);
    }
    
    String convert_12hr_to_24hr(String tm) throws ParseException{
        SimpleDateFormat displayFormat = new SimpleDateFormat("HH:mm");
        SimpleDateFormat parseFormat = new SimpleDateFormat("hh:mm a");
        java.util.Date date = parseFormat.parse(tm);
        String real_time = displayFormat.format(date);
        real_time = real_time +":00.0000000";
        return real_time;
    }
    
    void change_seat_list(String rt) throws SQLException, ParseException{
        String st1="";
        ResultSet rs2 = stmt.executeQuery(qry);
       
        while(rs2.next()){
            if(rs2.getString(1).equals(sm_mtitle.getText()) 
                    && rs2.getString(4).equals(rt) ){
                st1 = rs2.getString(9);
            }
        }
        
        String qry3 = "select st.showtimeid, sl.showtimeid,sl.seat_location, sl.seat_number, sl.seat_status\n" +
                        "from showtime st inner join seat_list sl\n" +
                        "	on st.showtimeid = sl.showtimeid";
        ResultSet rs = stmt.executeQuery(qry3);
        while(rs.next()){
            if(rs.getString(1).equals(st1)){
                String cn="";
                ImageIcon seat_icon = new ImageIcon("seat.png");
                JRadioButton jr = new JRadioButton();
                if(rs.getString(5).equals("A")){
                    jr.setBackground(Color.white);
                }
                else
                    jr.setBackground(new Color(255,204,102));
                jr.addMouseListener(new MouseAdapter(){
                    @Override
                    public void mouseClicked(MouseEvent e){
                        if(!(jr.getBackground().equals(Color.cyan))){
                            jr.setBackground(Color.cyan);
                        }
                        else
                            jr.setBackground(Color.white);
                        
                    }
                });
                if(rs.getString(3).equals("L")){
                    cn = cn+"L"+rs.getString(4);
                    jr.setText(cn); jr.setIcon(seat_icon);
                    left_seat_panel.add(jr);
                }
                else if(rs.getString(3).equals("M")){
                    cn = cn+"M"+rs.getString(4);
                    jr.setText(cn);jr.setIcon(seat_icon);
                    mid_seat_panel.add(jr);
                }
                else if(rs.getString(3).equals("R")){
                    cn = cn+"R"+rs.getString(4);
                    jr.setText(cn);jr.setIcon(seat_icon);
                    right_seat_panel.add(jr);
                }
            }
        }
    }
    //this methods add JRadioButton for seat_management
 

    
    void right_panel_bg(){

        ImageIcon rpbg = new ImageIcon("rpbg.png");
        Image image = rpbg.getImage(); // transform it 
        Image newimg = image.getScaledInstance(570, 480,  java.awt.Image.SCALE_DEFAULT); // scale it the smooth way  
        rpbg = new ImageIcon(newimg);
        rp_bg.setIcon(rpbg);
                
    }
        void left_panel_bg(){

        ImageIcon rpbg = new ImageIcon("lpbg.png");
        Image image = rpbg.getImage(); // transform it 
        Image newimg = image.getScaledInstance(280, 480,  java.awt.Image.SCALE_DEFAULT); // scale it the smooth way  
        rpbg = new ImageIcon(newimg);
        lp_bg.setIcon(rpbg);
                
    }
        
        public void create_receipt_panel(){
            
            JPanel receipt_panel1 = new JPanel();
            receipt_panel1.setPreferredSize(new Dimension(228,268));
            receipt_panel1.setLayout(null);
            receipt_panel.add(receipt_panel1);
            
            JLabel rob = new JLabel("ROBINSON");
            receipt_panel1.add(rob);
            rob.setBounds(80, 0, 200, 30);
            rob.setFont(new Font("Segoe UI",Font.BOLD,15));
            
            JLabel loc = new JLabel("ROBINSON PLACE MALOLOS");
            receipt_panel1.add(loc);
            loc.setBounds(30, 20, 200, 30);
            
            JLabel date_time = new JLabel("DATE & TIME: ");
            receipt_panel1.add(date_time);
            date_time.setBounds(5, 60, 105, 30);
            
            JLabel m_ttl = new JLabel("TITLE");
            receipt_panel1.add(m_ttl);
            m_ttl.setBounds(95, 90, 200, 30);
            m_ttl.setFont(new Font("Segoe UI",Font.BOLD,15));
            
            JLabel cinema = new JLabel("CINEMA");
            receipt_panel1.add(cinema);
            cinema.setBounds(20, 125, 100, 30);
            cinema.setFont(new Font("Segoe UI",Font.BOLD,11));
            
            JLabel s_num = new JLabel("SEAT. NUM");
            receipt_panel1.add(s_num);
            s_num.setBounds(150, 125, 100, 30);
            s_num.setFont(new Font("Segoe UI",Font.BOLD,11));
            
            JPanel square = new JPanel();
            square.setLayout(null);
            receipt_panel1.add(square);
            square.setBackground(Color.WHITE);
            square.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            square.setSize(new Dimension(190,70));
            square.setBounds(20, 150, 190, 70);
            
            JLabel price = new JLabel("PRICE: ");
            square.add(price);
            price.setBounds(5, 10, 105, 30);
            
            JLabel t_id = new JLabel("TICKET ID: ");
            square.add(t_id);
            t_id.setBounds(5, 30, 105, 30);
            
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
        main_receipt_panel = new javax.swing.JPanel();
        receipt_panel = new javax.swing.JPanel();
        lp_bg = new javax.swing.JLabel();
        right_main_panel = new javax.swing.JPanel();
        panel_for_seats = new javax.swing.JPanel();
        left_seat_panel = new javax.swing.JPanel();
        mid_seat_panel = new javax.swing.JPanel();
        right_seat_panel = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        av_time1 = new javax.swing.JButton();
        av_time2 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        sm_image_slot = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        jPanel1 = new javax.swing.JPanel();
        sm_mtitle = new javax.swing.JLabel();
        sm_mgenre = new javax.swing.JLabel();
        sm_mduration = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        sm_mprice = new javax.swing.JLabel();
        rp_bg = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Left_yellow_Panel.setBackground(new java.awt.Color(255, 204, 102));
        Left_yellow_Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        profile_icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/profile icon.png"))); // NOI18N
        Left_yellow_Panel.add(profile_icon, new org.netbeans.lib.awtextra.AbsoluteConstraints(73, 15, -1, -1));

        staff_name.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        staff_name.setText("STAFF NAME");
        Left_yellow_Panel.add(staff_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 24, -1, -1));

        log_out.setFont(new java.awt.Font("Segoe UI", 0, 8)); // NOI18N
        log_out.setText("LOG OUT");
        Left_yellow_Panel.add(log_out, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 44, -1, -1));

        Cart_label.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Cart_label.setText("CART");

        receipt_panel.setBackground(new java.awt.Color(255, 255, 255));
        receipt_panel.setPreferredSize(new java.awt.Dimension(260, 320));
        receipt_panel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        javax.swing.GroupLayout main_receipt_panelLayout = new javax.swing.GroupLayout(main_receipt_panel);
        main_receipt_panel.setLayout(main_receipt_panelLayout);
        main_receipt_panelLayout.setHorizontalGroup(
            main_receipt_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(main_receipt_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(receipt_panel, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        main_receipt_panelLayout.setVerticalGroup(
            main_receipt_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(main_receipt_panelLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(receipt_panel, javax.swing.GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)
                .addGap(17, 17, 17))
        );

        javax.swing.GroupLayout cart_panelLayout = new javax.swing.GroupLayout(cart_panel);
        cart_panel.setLayout(cart_panelLayout);
        cart_panelLayout.setHorizontalGroup(
            cart_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cart_panelLayout.createSequentialGroup()
                .addGap(112, 112, 112)
                .addComponent(Cart_label)
                .addContainerGap(112, Short.MAX_VALUE))
            .addComponent(main_receipt_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        cart_panelLayout.setVerticalGroup(
            cart_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cart_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Cart_label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(main_receipt_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Left_yellow_Panel.add(cart_panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 88, 260, 380));
        Left_yellow_Panel.add(lp_bg, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 280, 480));

        getContentPane().add(Left_yellow_Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 280, 480));

        right_main_panel.setBackground(new java.awt.Color(204, 204, 204));
        right_main_panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panel_for_seats.setBackground(new java.awt.Color(204, 204, 204));
        panel_for_seats.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        left_seat_panel.setBackground(new java.awt.Color(204, 204, 204));
        left_seat_panel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 15));

        mid_seat_panel.setBackground(new java.awt.Color(204, 204, 204));
        mid_seat_panel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 8));

        right_seat_panel.setBackground(new java.awt.Color(204, 204, 204));
        right_seat_panel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 15));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("SCREEN");

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
            .addGroup(panel_for_seatsLayout.createSequentialGroup()
                .addGap(245, 245, 245)
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panel_for_seatsLayout.setVerticalGroup(
            panel_for_seatsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_for_seatsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addGroup(panel_for_seatsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(left_seat_panel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
                    .addComponent(mid_seat_panel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(right_seat_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        right_main_panel.add(panel_for_seats, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 122, 530, 310));

        jPanel5.setBackground(new java.awt.Color(153, 153, 153));
        jPanel5.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 50, 3));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel11.setText("Available Time");
        jPanel5.add(jLabel11);

        av_time1.setText("unavailable");
        av_time1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                av_time1ActionPerformed(evt);
            }
        });
        jPanel5.add(av_time1);

        av_time2.setText("unavailable");
        av_time2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                av_time2ActionPerformed(evt);
            }
        });
        jPanel5.add(av_time2);

        right_main_panel.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 40, 110, 80));

        jPanel6.setBackground(new java.awt.Color(204, 204, 204));
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        sm_image_slot.setText("image here");
        jPanel6.add(sm_image_slot, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 100, 80));

        right_main_panel.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 100, 80));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setText("SEATS");
        right_main_panel.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 10, -1, -1));

        jButton1.setText("Confirm");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        right_main_panel.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 440, -1, -1));

        jButton2.setText("Cancel");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        right_main_panel.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 440, -1, -1));

        jRadioButton2.setBackground(new java.awt.Color(255, 255, 255));
        jRadioButton2.setText("Available");
        jRadioButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/seat.png"))); // NOI18N
        right_main_panel.add(jRadioButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 440, -1, -1));

        jRadioButton3.setBackground(new java.awt.Color(255, 204, 102));
        jRadioButton3.setText("Unavailable");
        jRadioButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/seat.png"))); // NOI18N
        right_main_panel.add(jRadioButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 440, -1, -1));

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        sm_mtitle.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        sm_mtitle.setText("Title");
        jPanel1.add(sm_mtitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, 20));

        sm_mgenre.setText("Genre");
        jPanel1.add(sm_mgenre, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, -1, -1));

        sm_mduration.setForeground(new java.awt.Color(102, 102, 102));
        sm_mduration.setText("Duration: 1hr 45 mins");
        jPanel1.add(sm_mduration, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, -1));

        right_main_panel.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 40, 230, 80));

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 20, 2));

        jLabel2.setForeground(new java.awt.Color(204, 204, 204));
        jLabel2.setText("jLabel2");
        jPanel2.add(jLabel2);

        jLabel1.setText("PHP");
        jPanel2.add(jLabel1);

        sm_mprice.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        sm_mprice.setText("PRICE");
        jPanel2.add(sm_mprice);

        right_main_panel.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 40, 90, 80));
        right_main_panel.add(rp_bg, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 570, 480));

        getContentPane().add(right_main_panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 0, 570, 480));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.setVisible(false);
        new Payment_Method().setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.setVisible(false);
        //mlst.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void av_time1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_av_time1ActionPerformed
        if(!(av_time1.getText().equals("unavailable"))){
            av_time1.setBackground(new Color(255,204,102));
            av_time2.setBackground(Color.lightGray);
            String time1="";
            try {
                time1 = convert_12hr_to_24hr(av_time1.getText());
            } catch (ParseException ex) {
                java.util.logging.Logger.getLogger(Seat_Management.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                change_seat_list(time1);
            } catch (SQLException ex) {
                java.util.logging.Logger.getLogger(Seat_Management.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                java.util.logging.Logger.getLogger(Seat_Management.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }//GEN-LAST:event_av_time1ActionPerformed

    private void av_time2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_av_time2ActionPerformed
        if(!(av_time2.getText().equals("unavailable"))){
            av_time2.setBackground(new Color(255,204,102));
            av_time1.setBackground(Color.lightGray);
            String time1="";
            try {
                time1 = convert_12hr_to_24hr(av_time2.getText());
            } catch (ParseException ex) {
                java.util.logging.Logger.getLogger(Seat_Management.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                change_seat_list(time1);
            } catch (SQLException ex) {
                java.util.logging.Logger.getLogger(Seat_Management.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                java.util.logging.Logger.getLogger(Seat_Management.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_av_time2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Cart_label;
    private javax.swing.JPanel Left_yellow_Panel;
    private javax.swing.JButton av_time1;
    private javax.swing.JButton av_time2;
    private javax.swing.JPanel cart_panel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JPanel left_seat_panel;
    private javax.swing.JLabel log_out;
    private javax.swing.JLabel lp_bg;
    private javax.swing.JPanel main_receipt_panel;
    private javax.swing.JPanel mid_seat_panel;
    private javax.swing.JPanel panel_for_seats;
    private javax.swing.JLabel profile_icon;
    private javax.swing.JPanel receipt_panel;
    private javax.swing.JPanel right_main_panel;
    private javax.swing.JPanel right_seat_panel;
    private javax.swing.JLabel rp_bg;
    private javax.swing.JLabel sm_image_slot;
    private javax.swing.JLabel sm_mduration;
    private javax.swing.JLabel sm_mgenre;
    private javax.swing.JLabel sm_mprice;
    private javax.swing.JLabel sm_mtitle;
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
