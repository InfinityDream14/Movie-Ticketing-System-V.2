/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package admin;


import static admin.MovieDetails.connMD;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Romel
 */
public final class ShowtimeList extends javax.swing.JFrame {

    /**
     * Creates new form ShowtimeList
     */
    public ShowtimeList() throws ClassNotFoundException {
        initComponents();
        createShowtimeListTable();
    }
    public static String mid;
    public Statement stmt;
    
     ResultSet rs;
     
     DefaultTableModel tmodel1 = new DefaultTableModel();
     ArrayList<Object[]> vec = new ArrayList<>();
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ShowTimeTable = new javax.swing.JTable();
        AddShowtimeBtn = new javax.swing.JButton();
        RemoveBtn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        ShowTimeTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(ShowTimeTable);

        AddShowtimeBtn.setText("Add Showtime");
        AddShowtimeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddShowtimeBtnActionPerformed(evt);
            }
        });

        RemoveBtn.setText("Remove");
        RemoveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RemoveBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(RemoveBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(AddShowtimeBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(57, 57, 57))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AddShowtimeBtn)
                    .addComponent(RemoveBtn))
                .addGap(0, 21, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("Show Time List");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void AddShowtimeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddShowtimeBtnActionPerformed
        // TODO add your handling code here:
        Showtime.movieID = mid;
        try {
            new Showtime().setVisible(true);
        } catch (SQLException | ClassNotFoundException | ParseException ex) {
            Logger.getLogger(ShowtimeList.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_AddShowtimeBtnActionPerformed

    private void RemoveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RemoveBtnActionPerformed
        // remove button nalang ang wala
    }//GEN-LAST:event_RemoveBtnActionPerformed

    public void getShowtimelistdata() throws ClassNotFoundException {
        String sql1 = """
                  select st.showtimeid,st.starttime, st.showtimemovieid, m.movieid, m.title
                  from showtime st left join movie m
                  on st.ShowtimeMovieID = m.MovieID
                   """;

        try {

            stmt = new Admin().conn.createStatement();
            this.rs = stmt.executeQuery(sql1);
            this.vec = new ArrayList<>();

            while (rs.next()) { // rs.getstring (pangilang moviedid) -- showtimelist

                if(rs.getString(3).equals(mid)){
                this.vec.add(new Object[]{rs.getString("MovieID"), rs.getString("Title"), rs.getString("Starttime"), rs.getString("showtimeid")});
                    System.out.println("MovieID");
                System.out.println(rs.getString("Title"));
                System.out.println(rs.getString("StartTime"));
                System.out.println("ShowtimeID");
                }
               
            }

            for (Object[] row : vec) {
                tmodel1.addRow(row);
            }

        } catch (SQLException e) {
            System.out.println(e);

        }
    }
    
     public void createShowtimeListTable() throws ClassNotFoundException {
        tmodel1 = new DefaultTableModel();
        ShowTimeTable.setModel(tmodel1);
        tmodel1.addColumn("Movie ID");
        tmodel1.addColumn("Movie Title");
        tmodel1.addColumn("Start Time");
        tmodel1.addColumn("Showtime ID");
        
        ListSelectionModel cellSelectionModel = ShowTimeTable.getSelectionModel();
        cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        cellSelectionModel.addListSelectionListener((e) -> {
            String SelectedMovieData = null;

            int[] selectedRow = ShowTimeTable.getSelectedRows();
            int[] selectedColumn = ShowTimeTable.getSelectedColumns();

            for (int i = 0; i < selectedRow.length; i++) {
                for (int j = 0; j < selectedColumn.length; j++) {
                    SelectedMovieData = (String) ShowTimeTable.getValueAt(selectedRow[i], selectedColumn[j]);
                }
                System.out.println("Selected: " + SelectedMovieData);
            }
        });
        getShowtimelistdata();
    }
     
     
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddShowtimeBtn;
    private javax.swing.JButton RemoveBtn;
    private javax.swing.JTable ShowTimeTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
