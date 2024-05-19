/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package admin;

import java.sql.*;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import main.Main;

/**
 *
 * @author Administrator
 */
public class LogSummary extends javax.swing.JFrame {

    /**
     * Creates new form LogSummary
     */
    public LogSummary() {
        initComponents();
        this.setLocationRelativeTo(null);
        setFilter();
      
    }
    static Connection mc;
    Statement stmt;
    ArrayList<Object[]> row = new ArrayList<>();
    
    DefaultTableModel tmodel1 = new DefaultTableModel();
    private String dateFilter;
    private String filteredDate;
    private static String yrg;
    
    public void setFilter() {
        sDayChooser.setVisible(false);
        sMonthChooser.setVisible(false);
        sYearChooser.setVisible(false);
        dateFilter = "";
        filteredDate = "";
        String month;

        if (dayFilter.getSelectedItem().equals("Day")) {
            sDayChooser.setVisible(true);
            filteredDate = sDayChooser.getDateStringOrEmptyString();

        } else if (dayFilter.getSelectedItem().equals("Month")) {

            sMonthChooser.setVisible(true);
            sYearChooser.setVisible(true);

            month = sMonthChooser.getMonth() + 1 + "";
            if (month.length() == 1) {
                month = 0 + month;
            }
            filteredDate = sYearChooser.getYear() + "-" + month;

        } else if (dayFilter.getSelectedItem().equals("Year")) {
            sYearChooser.setVisible(true);

            filteredDate = sYearChooser.getYear() + "";
        }
        System.out.println(filteredDate);
        dateFilter = " WHERE datelog like '%" + filteredDate + "%'";
        get_logs_data_all();
    }
    
    void get_logs_data_all(){
        String lqry = "select ls.EMployee_ID, CONCAT(s.fname,' ', s.lname) as Employee_name, ls.TotalLogs\n" +
                        "from (SELECT Employee_ID, COUNT(*) AS TotalLogs\n" +
                        "		FROM LOGS \n" +dateFilter+
                        "		GROUP BY Employee_ID) ls left join staff s \n" +
                        "on  ls.employee_ID = s.employeeid";
        
        tmodel1 = new DefaultTableModel();
        LogTable.setModel(tmodel1);
        tmodel1.addColumn("Employee ID");
        tmodel1.addColumn("Employee Name");
        tmodel1.addColumn("Total Logs");
        
        try {

            stmt = mc.createStatement();
            ResultSet rs = stmt.executeQuery(lqry);
            
            row = new ArrayList<>();

            while (rs != null && rs.next()) {
                    this.row.add(new Object[]{rs.getString(1), rs.getString(2), rs.getString(3)});

            }

            for (Object[] row : row) {
                tmodel1.addRow(row);
            }

        } catch (SQLException e) {
            System.out.println(e);

        }
        
        
    }
    
    void get_logs_data_year(String year){
        String lqry = "select ls.EMployee_ID, CONCAT(s.fname,' ', s.lname) as Employee_name, ls.TotalLogs\n" +
                        "from (SELECT Employee_ID, COUNT(*) AS TotalLogs\n" +
                        "		FROM LOGS \n" +
                        "		WHERE datelog like '"+year+"%'\n" +
                        "		GROUP BY Employee_ID) ls left join staff s \n" +
                        "on  ls.employee_ID = s.employeeid";
        
        tmodel1 = new DefaultTableModel();
        LogTable.setModel(tmodel1);
        tmodel1.addColumn("Employee ID");
        tmodel1.addColumn("Employee Name");
        tmodel1.addColumn("Total Logs");
        
        try {

            stmt = mc.createStatement();
            ResultSet rs = stmt.executeQuery(lqry);
            
            row = new ArrayList<>();

            while (rs != null && rs.next()) {
                    this.row.add(new Object[]{rs.getString(1), rs.getString(2), rs.getString(3)});

            }

            for (Object[] row : row) {
                tmodel1.addRow(row);
            }

        } catch (SQLException e) {
            System.out.println(e);

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
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        LogTable = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        sMonthChooser = new com.toedter.calendar.JMonthChooser();
        sYearChooser = new com.toedter.calendar.JYearChooser();
        sDayChooser = new com.github.lgooddatepicker.components.DatePicker();
        dayFilter = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Logs Summary");
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        LogTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        LogTable.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        LogTable.setRowHeight(40);
        jScrollPane1.setViewportView(LogTable);

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        sMonthChooser.setFocusable(false);
        sMonthChooser.setRequestFocusEnabled(false);
        sMonthChooser.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                sMonthChooserPropertyChange(evt);
            }
        });
        jPanel2.add(sMonthChooser, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 20, -1, -1));

        sYearChooser.setFocusable(false);
        sYearChooser.setRequestFocusEnabled(false);
        sYearChooser.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                sYearChooserPropertyChange(evt);
            }
        });
        jPanel2.add(sYearChooser, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 20, -1, -1));

        sDayChooser.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                sDayChooserPropertyChange(evt);
            }
        });
        jPanel2.add(sDayChooser, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 20, 190, -1));

        dayFilter.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All", "Day", "Month", "Year" }));
        dayFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dayFilterActionPerformed(evt);
            }
        });
        dayFilter.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dayFilterPropertyChange(evt);
            }
        });
        jPanel2.add(dayFilter, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 20, 90, -1));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(39, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void sMonthChooserPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_sMonthChooserPropertyChange
        setFilter();
    }//GEN-LAST:event_sMonthChooserPropertyChange

    private void sYearChooserPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_sYearChooserPropertyChange
        setFilter();
    }//GEN-LAST:event_sYearChooserPropertyChange

    private void dayFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dayFilterActionPerformed
        setFilter();
    }//GEN-LAST:event_dayFilterActionPerformed

    private void dayFilterPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dayFilterPropertyChange

    }//GEN-LAST:event_dayFilterPropertyChange

    private void sDayChooserPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_sDayChooserPropertyChange
        setFilter();
    }//GEN-LAST:event_sDayChooserPropertyChange

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws ClassNotFoundException {
        try{
            String hostname = "localhost";
            String sqlInstanceName = "MTS"; //computer name 
            String sqlDatabase = "movieticketsystem";  //sql server database name
            String sqlUser = "sa";
            String sqlPassword = "Java"; //passwrod sa account

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            //jdbc:sqlserver://localhost:1433;instance=COMPUTERBERRY;databaseName=Database;
            String connectURL = "jdbc:sqlserver://" + hostname + ":1433" 
                    + ";instance=" + sqlInstanceName + ";databaseName=" + sqlDatabase
                    +";encrypt=true;trustServerCertificate=true";

            Connection conn = DriverManager.getConnection(connectURL, sqlUser, sqlPassword);
            System.out.println("Connect to database successful!!");
            mc=conn;

       }catch(SQLException e){
           e.printStackTrace();
       }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LogSummary().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable LogTable;
    private javax.swing.JComboBox<String> dayFilter;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private com.github.lgooddatepicker.components.DatePicker sDayChooser;
    private com.toedter.calendar.JMonthChooser sMonthChooser;
    private com.toedter.calendar.JYearChooser sYearChooser;
    // End of variables declaration//GEN-END:variables
}
