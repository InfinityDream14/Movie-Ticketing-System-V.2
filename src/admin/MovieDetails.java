package admin;

import java.awt.event.KeyEvent;
import java.sql.*;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import main.Main;

public class MovieDetails extends javax.swing.JFrame {

    static Connection connMD = Main.mc;
    

    public MovieDetails() {
        initComponents();

    }

    public MovieDetails(String MovieID, String Title, String Genre, String Director, String Duration,
            String Price) throws SQLException {
        initComponents();
        getMovieDets_fromDB();

        MovieDetails_MovieIDTx.setText(MovieID);
        MovieDetails_TitleTx.setText(Title);
        MovieDetails_GenreTx.setText(Genre);
        MovieDetails_DirectorTx.setText(Director);
        MovieDetails_DurationTx.setText(Duration);
        MovieDetails_PriceTx.setText(Price);

    }

    static String MDTitle, MDGenre, MDDirector, MDDuration, MDPrice;

    void getMovieDets_fromDB() throws SQLException {

        Statement stmtMD = connMD.createStatement();
        String qry = "Select * from Movie";
        ResultSet rs = stmtMD.executeQuery(qry);

        while (rs.next()) {
            if (rs.getString(1).equals(MovieDetails_MovieIDTx.getText())) {
                MDTitle = rs.getString(2);
                MDGenre = rs.getString(3);
                MDDirector = rs.getString(4);
                MDDuration = rs.getString(5);
                MDPrice = rs.getString(6);
            }
        }
        MovieDetails_TitleTx.setText(MDTitle);
        MovieDetails_GenreTx.setText(MDGenre);
        MovieDetails_DirectorTx.setText(MDDirector);
        MovieDetails_DurationTx.setText(MDDuration);
        MovieDetails_PriceTx.setText(MDPrice);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        MovieDetails_MovieID = new javax.swing.JLabel();
        MovieDetails_MovieIDTx = new javax.swing.JTextField();
        MovieDetails_Title = new javax.swing.JLabel();
        MovieDetails_TitleTx = new javax.swing.JTextField();
        MovieDetails_Genre = new javax.swing.JLabel();
        MovieDetails_GenreTx = new javax.swing.JTextField();
        MovieDetails_Director = new javax.swing.JLabel();
        MovieDetails_DirectorTx = new javax.swing.JTextField();
        MovieDetails_Duration = new javax.swing.JLabel();
        MovieDetails_DurationTx = new javax.swing.JTextField();
        MovieDetails_Price = new javax.swing.JLabel();
        MovieDetails_PriceTx = new javax.swing.JTextField();
        Save_Details = new javax.swing.JButton();
        MovieDetails_PosterPic = new javax.swing.JLabel();
        AddShowtimeButton = new javax.swing.JButton();
        UpdateMovStatAvail = new javax.swing.JButton();
        UpdateMovStatUnavail = new javax.swing.JButton();
        MovieDetails_MStatus = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jLabel2.setText("jLabel2");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Movie Details");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 7, 142, 28));

        MovieDetails_MovieID.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        MovieDetails_MovieID.setText("Movie ID:");
        jPanel1.add(MovieDetails_MovieID, new org.netbeans.lib.awtextra.AbsoluteConstraints(42, 47, 87, -1));

        MovieDetails_MovieIDTx.setEditable(false);
        jPanel1.add(MovieDetails_MovieIDTx, new org.netbeans.lib.awtextra.AbsoluteConstraints(135, 47, 286, -1));

        MovieDetails_Title.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        MovieDetails_Title.setText("Title:");
        jPanel1.add(MovieDetails_Title, new org.netbeans.lib.awtextra.AbsoluteConstraints(42, 81, 87, -1));
        jPanel1.add(MovieDetails_TitleTx, new org.netbeans.lib.awtextra.AbsoluteConstraints(135, 81, 286, -1));

        MovieDetails_Genre.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        MovieDetails_Genre.setText("Genre: ");
        jPanel1.add(MovieDetails_Genre, new org.netbeans.lib.awtextra.AbsoluteConstraints(42, 115, 87, -1));
        jPanel1.add(MovieDetails_GenreTx, new org.netbeans.lib.awtextra.AbsoluteConstraints(135, 115, 286, -1));

        MovieDetails_Director.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        MovieDetails_Director.setText("Director:");
        jPanel1.add(MovieDetails_Director, new org.netbeans.lib.awtextra.AbsoluteConstraints(42, 149, 87, -1));
        jPanel1.add(MovieDetails_DirectorTx, new org.netbeans.lib.awtextra.AbsoluteConstraints(135, 149, 286, -1));

        MovieDetails_Duration.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        MovieDetails_Duration.setText("Duration:");
        jPanel1.add(MovieDetails_Duration, new org.netbeans.lib.awtextra.AbsoluteConstraints(42, 183, 87, -1));
        jPanel1.add(MovieDetails_DurationTx, new org.netbeans.lib.awtextra.AbsoluteConstraints(135, 183, 286, -1));

        MovieDetails_Price.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        MovieDetails_Price.setText("Price:");
        jPanel1.add(MovieDetails_Price, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 220, 87, -1));

        MovieDetails_PriceTx.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                MovieDetails_PriceTxKeyTyped(evt);
            }
        });
        jPanel1.add(MovieDetails_PriceTx, new org.netbeans.lib.awtextra.AbsoluteConstraints(135, 217, 286, -1));

        Save_Details.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Save_Details.setText("Save Details");
        Save_Details.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Save_DetailsActionPerformed(evt);
            }
        });
        jPanel1.add(Save_Details, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 300, 158, 36));
        jPanel1.add(MovieDetails_PosterPic, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 50, 160, 220));

        AddShowtimeButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        AddShowtimeButton.setText("Add Showtime");
        AddShowtimeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddShowtimeButtonActionPerformed(evt);
            }
        });
        jPanel1.add(AddShowtimeButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 300, 158, 36));

        UpdateMovStatAvail.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        UpdateMovStatAvail.setText("A");
        UpdateMovStatAvail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdateMovStatAvailActionPerformed(evt);
            }
        });
        jPanel1.add(UpdateMovStatAvail, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 250, 60, 20));

        UpdateMovStatUnavail.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        UpdateMovStatUnavail.setText("U");
        UpdateMovStatUnavail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdateMovStatUnavailActionPerformed(evt);
            }
        });
        jPanel1.add(UpdateMovStatUnavail, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 250, 60, 20));

        MovieDetails_MStatus.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        MovieDetails_MStatus.setText("Movie Status: ");
        jPanel1.add(MovieDetails_MStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 250, 100, -1));

        jLabel3.setBackground(new java.awt.Color(51, 51, 51));
        jLabel3.setForeground(new java.awt.Color(153, 153, 153));
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/bg_addmovie.png"))); // NOI18N
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(-20, 0, 660, 360));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 631, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(53, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    String NewMDTitle, NewMDGenre, NewMDDirector, NewMDDuration, NewMDPrice;
    private void Save_DetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Save_DetailsActionPerformed

        try {
            Statement stmt = connMD.createStatement();
            String qry = "select * from Movie";
            ResultSet rs = stmt.executeQuery(qry);

            NewMDTitle = MovieDetails_TitleTx.getText();
            NewMDGenre = MovieDetails_GenreTx.getText();
            NewMDDirector = MovieDetails_DirectorTx.getText();
            NewMDDuration = MovieDetails_DurationTx.getText();
            NewMDPrice = MovieDetails_PriceTx.getText();

            while (rs.next()) {
                Statement stmt1 = connMD.createStatement();
                if (rs.getString(1).equals(MovieDetails_MovieIDTx.getText())) {
                    String rsin = "UPDATE Movie \n"
                            + "set Title = '" + NewMDTitle + "', Genre = '" + NewMDGenre + "', Director = '" + NewMDDirector + "',"
                            + " Duration = '" + NewMDDuration + "', Price = " + NewMDPrice
                            + "where MovieID = '" + MovieDetails_MovieIDTx.getText() + "'";

                    int up = stmt1.executeUpdate(rsin);
                    if (up > 0) {
                        System.out.println("Movie details updated");
                        JOptionPane.showMessageDialog(null, "Success");
                    }
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(MovieDetails.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_Save_DetailsActionPerformed

    private void AddShowtimeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddShowtimeButtonActionPerformed
        try {
            // TODO add your handling code here:
            new Showtime().setVisible(true);
            // ibang showtime yung lumalabas
        } catch (SQLException | ClassNotFoundException | ParseException ex) {
            Logger.getLogger(MovieDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_AddShowtimeButtonActionPerformed

    private void MovieDetails_PriceTxKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MovieDetails_PriceTxKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (!Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE)) {
            evt.consume();
        }
    }//GEN-LAST:event_MovieDetails_PriceTxKeyTyped

    String NewMovStatvail = "A";
    private void UpdateMovStatAvailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdateMovStatAvailActionPerformed
        // TODO add your handling code here:
        try {
            Statement stmt = connMD.createStatement();
            String qry = "select * from Movie";
            ResultSet rs = stmt.executeQuery(qry);

            while (rs.next()) {
                Statement stmt1 = connMD.createStatement();
                if (rs.getString(1).equals(MovieDetails_MovieIDTx.getText())) {
                    String rsin = "UPDATE Movie \n"
                            + "set movie_status = '" + NewMovStatvail + "'"
                            + "where MovieID = '" + MovieDetails_MovieIDTx.getText() + "'";

                    int up = stmt1.executeUpdate(rsin);
                    if (up > 0) {
                        System.out.println("Movie Status updated");
                        JOptionPane.showMessageDialog(null, "Success");
                    }
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(MovieDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_UpdateMovStatAvailActionPerformed
    
    String NewMovStatUnavail = "U";
    private void UpdateMovStatUnavailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdateMovStatUnavailActionPerformed
        // TODO add your handling code here:
        try {
            Statement stmt = connMD.createStatement();
            String qry = "select * from Movie";
            ResultSet rs = stmt.executeQuery(qry);

            while (rs.next()) {
                Statement stmt1 = connMD.createStatement();
                if (rs.getString(1).equals(MovieDetails_MovieIDTx.getText())) {
                    String rsin = "UPDATE Movie \n"
                            + "set movie_status = '" + NewMovStatUnavail + "'"
                            + "where MovieID = '" + MovieDetails_MovieIDTx.getText() + "'";

                    int up = stmt1.executeUpdate(rsin);
                    if (up > 0) {
                        System.out.println("Movie Status updated");
                        JOptionPane.showMessageDialog(null, "Success");
                    }
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(MovieDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_UpdateMovStatUnavailActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddShowtimeButton;
    private javax.swing.JLabel MovieDetails_Director;
    private javax.swing.JTextField MovieDetails_DirectorTx;
    private javax.swing.JLabel MovieDetails_Duration;
    private javax.swing.JTextField MovieDetails_DurationTx;
    private javax.swing.JLabel MovieDetails_Genre;
    private javax.swing.JTextField MovieDetails_GenreTx;
    private javax.swing.JLabel MovieDetails_MStatus;
    private javax.swing.JLabel MovieDetails_MovieID;
    private javax.swing.JTextField MovieDetails_MovieIDTx;
    private javax.swing.JLabel MovieDetails_PosterPic;
    private javax.swing.JLabel MovieDetails_Price;
    private javax.swing.JTextField MovieDetails_PriceTx;
    private javax.swing.JLabel MovieDetails_Title;
    private javax.swing.JTextField MovieDetails_TitleTx;
    private javax.swing.JButton Save_Details;
    private javax.swing.JButton UpdateMovStatAvail;
    private javax.swing.JButton UpdateMovStatUnavail;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}
