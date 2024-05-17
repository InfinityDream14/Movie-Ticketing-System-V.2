/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package admin;



import java.sql.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Date;
import main.Main;



/**
 *
 * @author Romel
 */
public final class Showtime extends javax.swing.JFrame {
  Admin ad = new Admin();
  static String movieID = "";
  static Connection connMD = Main.mc;
  
    public Showtime() throws SQLException, ClassNotFoundException, ParseException {
        initComponents();
        get_last_showtimeid();
        
        ShowtimeID_TX.setText(newshowtimeid);
        MovieID_Tx.setText(ad.ShowtimeMovieID);
        
        
        String midnum = MovieID_Tx.getText().substring(1);
        midnum = "M" + Integer.toString(Integer.parseInt(midnum) - 1);
        System.out.println(midnum);
        MovieID_Tx.setText(midnum);
        
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        Showtime = new javax.swing.JLabel();
        ShowtimeID_Label = new javax.swing.JLabel();
        CinemaID_Label = new javax.swing.JLabel();
        Movie_Label = new javax.swing.JLabel();
        StartTime_Label = new javax.swing.JLabel();
        ShowtimeID_TX = new javax.swing.JTextField();
        CinemaID_Tx = new javax.swing.JTextField();
        MovieID_Tx = new javax.swing.JTextField();
        StartTime_Tx = new javax.swing.JTextField();
        AddShowtime_Button = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setPreferredSize(new java.awt.Dimension(676, 424));
        jPanel1.setLayout(null);

        Showtime.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        Showtime.setText("Showtime");
        jPanel1.add(Showtime);
        Showtime.setBounds(30, 20, 130, 40);

        ShowtimeID_Label.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        ShowtimeID_Label.setText("Showtime ID: ");
        jPanel1.add(ShowtimeID_Label);
        ShowtimeID_Label.setBounds(70, 70, 160, 25);

        CinemaID_Label.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        CinemaID_Label.setText("Cinema ID: ");
        jPanel1.add(CinemaID_Label);
        CinemaID_Label.setBounds(70, 120, 160, 25);

        Movie_Label.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        Movie_Label.setText("Movie ID: ");
        jPanel1.add(Movie_Label);
        Movie_Label.setBounds(70, 170, 119, 25);

        StartTime_Label.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        StartTime_Label.setText("Start Time: ");
        jPanel1.add(StartTime_Label);
        StartTime_Label.setBounds(70, 220, 121, 25);

        ShowtimeID_TX.setEditable(false);
        ShowtimeID_TX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ShowtimeID_TXActionPerformed(evt);
            }
        });
        jPanel1.add(ShowtimeID_TX);
        ShowtimeID_TX.setBounds(250, 70, 360, 30);

        CinemaID_Tx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CinemaID_TxActionPerformed(evt);
            }
        });
        jPanel1.add(CinemaID_Tx);
        CinemaID_Tx.setBounds(250, 120, 360, 30);

        MovieID_Tx.setEditable(false);
        jPanel1.add(MovieID_Tx);
        MovieID_Tx.setBounds(250, 170, 360, 30);
        jPanel1.add(StartTime_Tx);
        StartTime_Tx.setBounds(250, 220, 360, 30);

        AddShowtime_Button.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        AddShowtime_Button.setText("Add Showtime");
        AddShowtime_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddShowtime_ButtonActionPerformed(evt);
            }
        });
        jPanel1.add(AddShowtime_Button);
        AddShowtime_Button.setBounds(510, 280, 160, 30);

        jButton2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton2.setText("Back");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2);
        jButton2.setBounds(330, 280, 160, 30);

        jLabel1.setBackground(new java.awt.Color(153, 153, 153));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/bg_addmovie.png"))); // NOI18N
        jPanel1.add(jLabel1);
        jLabel1.setBounds(20, 20, 670, 320);

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 710, 360));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void ShowtimeID_TXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ShowtimeID_TXActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ShowtimeID_TXActionPerformed

    private void CinemaID_TxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CinemaID_TxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CinemaID_TxActionPerformed

    private void AddShowtime_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddShowtime_ButtonActionPerformed
          
        try {
          insert_Showtime_to_DB();
          get_Cinema_seat_count();
          create_seatList();
      } catch (ParseException | SQLException ex) {
          Logger.getLogger(Showtime.class.getName()).log(Level.SEVERE, null, ex);
      }
      dispose();
    }//GEN-LAST:event_AddShowtime_ButtonActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed


    static String lshowtimeid; // for creating new showtimeid
    int lshowtimeidnum;
    static String newshowtimeid;
    void get_last_showtimeid() throws SQLException {
        lshowtimeid = "";
        Statement stmt = connMD.createStatement();

        String qry = "select * from showtime order by len(showtimeID), showtimeID";
        ResultSet rs = stmt.executeQuery(qry);
        while (rs.next()) {
            lshowtimeid = rs.getString(2);
        }
        lshowtimeidnum = Integer.parseInt(lshowtimeid.substring(1)) + 1;
        newshowtimeid = "S" + String.valueOf(lshowtimeidnum);
        System.out.println(newshowtimeid);
        
    }
    
    void insert_Showtime_to_DB() throws ParseException, SQLException{
        
        
      String STCinemaID = CinemaID_Tx.getText();
      String STMovieID =  MovieID_Tx.getText().trim();
      String STShowtimeID = ShowtimeID_TX.getText();
      String STStartT = StartTime_Tx.getText();
      STStartT = convert_12hr_to_24hr(STStartT);
      
        System.out.println(STCinemaID);
        System.out.println(STMovieID);
        System.out.println(STShowtimeID);
        System.out.println(STStartT);
        
      try {  // INSERTING VALUES FOR ADDMOVIE
                        
                        
                        Statement stmt2 = connMD.createStatement();
                        String qry = "INSERT INTO showtime VALUES "
                                + "('"+STStartT+"','"+STShowtimeID+"','"+STCinemaID+"','"+STMovieID+"')";
                        int rows = stmt2.executeUpdate(qry);
                        if (rows > 0) {
                            System.out.println("Insert Successful");
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
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
    
    ArrayList<String> Slist = new ArrayList<>();
    
    
    void create_seatList () throws SQLException{
        String stime = ShowtimeID_TX.getText();
        String cinemaid = CinemaID_Tx.getText();
        String starttime = StartTime_Tx.getText();
        String movieid = MovieID_Tx.getText();
        
        int left = 0, mid = 0, right = 0;
        
        System.out.println(numofseats);
        left = (numofseats - 21) / 2;
        mid = 21;
        right = left;
        
        for(int i =1; i<=left;i++){
           String SL = "('L',"+ i +",'A','"+stime+"','"+cinemaid+"','"+ starttime +"','"+movieid+"')";
           Slist.add(SL);
        }
        for(int i =1; i<=mid;i++){
            String SM = "('M',"+ i +",'A','"+stime+"','"+cinemaid+"','"+ starttime +"','"+movieid+"')";
            Slist.add(SM);
        }
        for(int i =1; i<=right;i++){
            String SR = "('R',"+ i +",'A','"+stime+"','"+cinemaid+"','"+ starttime +"','"+movieid+"')";
            Slist.add(SR);
        }
        for(int i = 0; i < Slist.size(); i++){
         String input = "";
         Statement stmt4 = connMD.createStatement();
         input = "INSERT INTO Seat_List(seat_location,seat_number,seat_status,ShowtimeID,CinemaID,starttime,stmovieid) "
                 + "VALUES" + Slist.get(i);
         int rows = stmt4.executeUpdate(input);
        
        }
        Slist.clear();
    }
    
    static int numofseats; 
    void get_Cinema_seat_count() throws SQLException{
        
           Statement stmt = connMD.createStatement();

        String qry = "select * from Cinema";
        ResultSet rs = stmt.executeQuery(qry);
        while (rs.next()) {
            if(rs.getString(1).equals(CinemaID_Tx.getText())){
              numofseats = Integer.parseInt(rs.getString(2));
            }
        }
    }
   
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddShowtime_Button;
    private javax.swing.JLabel CinemaID_Label;
    private javax.swing.JTextField CinemaID_Tx;
    private javax.swing.JTextField MovieID_Tx;
    private javax.swing.JLabel Movie_Label;
    private javax.swing.JLabel Showtime;
    private javax.swing.JLabel ShowtimeID_Label;
    private javax.swing.JTextField ShowtimeID_TX;
    private javax.swing.JLabel StartTime_Label;
    private javax.swing.JTextField StartTime_Tx;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
