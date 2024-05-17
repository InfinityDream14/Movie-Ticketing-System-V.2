/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package admin;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;
import jnafilechooser.api.JnaFileChooser;
/**
 *
 * @author Christian
 */
public final class Admin extends javax.swing.JFrame {

    /**
     * Creates new form AdminII
     */
    String adminpass;
    String EmpId, FName, LName, PNum, pass, UserPass = " "; // for creating new Staff
    String Email = "null"; // for creating new Staff

    String MovieID, Title, Genre, Director, Mpass; // for creating new Movie
    String Movie_Pic_Location = "null"; // for creating new Movie
    int price; // for creating new Movie
    String Duration = "null"; // for creating new Movie;
    int showtimeTime;
    public static String ShowtimeMovieID;
    
    Statement stmt;
    Connection conn;
    ResultSet rs;
    ArrayList <Object[]> vec = new ArrayList<>();
    DefaultTableModel tmodel = new DefaultTableModel(); 
    DefaultTableModel tmodel1 = new DefaultTableModel();
    SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm a");
    DecimalFormat dec = new DecimalFormat("#.##");
    
    File file = null;

    public Admin() throws SQLException, ClassNotFoundException {
        connectToDatabase();
        initComponents();

        sales.setVisible(true);
        employee.setVisible(false);
        addEmplyee.setVisible(false);
        movies.setVisible(false);
        addMovies.setVisible(false);
        
        dec.setRoundingMode(RoundingMode.CEILING);
        createTableSales();
        getIDs();
    }

    public void connectToDatabase() throws ClassNotFoundException {
        String hostname = "localhost";
        String sqlInstanceName = "MTS"; //computer name 
        String sqlDatabase = "MovieTicketSystem";  //sql server database name
        String sqlUser = "sa";
        String sqlPassword = "Java"; //passwrod sa account

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            //jdbc:sqlserver://localhost:1433;instance=COMPUTERBERRY;databaseName=Database;
            String connectURL = "jdbc:sqlserver://" + hostname + ":1433"
                    + ";instance=" + sqlInstanceName + ";databaseName=" + sqlDatabase
                    + ";encrypt=true;trustServerCertificate=true";

            conn = DriverManager.getConnection(connectURL, sqlUser, sqlPassword);

            System.out.println("Connect to database successful!!");
        } catch (SQLException ex) {
        }
    }

    // for creating and reseting sales table
    public void createTableSales() {
        tmodel = new DefaultTableModel();

        salesTable.setModel(tmodel);
        tmodel.addColumn("Ticket ID");
        tmodel.addColumn("Amount");
        tmodel.addColumn("Date Purchased");
        tmodel.addColumn("Time Purchased");
        ListSelectionModel cellSelectionModel = salesTable.getSelectionModel();
        cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        cellSelectionModel.addListSelectionListener((ListSelectionEvent e) -> {
            String selectedData = null;

            int[] selectedRow = salesTable.getSelectedRows();
            int[] selectedColumn = salesTable.getSelectedColumns();

            for (int i = 0; i < selectedRow.length; i++) {
                for (int j = 0; j < selectedColumn.length; j++) {
                    selectedData = (String) salesTable.getValueAt(selectedRow[i], selectedColumn[j]);
                }
            }
            System.out.println("Selected: " + selectedData);
        });
        getSalesData();
    }

    // for getting the sales datas from database to the jtable
    public void getSalesData() {
        String sql = """
                     select t.TicketID, p.Amount, p.PaymentDate
                     from ticket t join payment p on t.TicketPaymentID = p.PaymentID""";
        String newTimeSell, dateSell, amount;
        
        try {
            stmt = conn.createStatement();
            this.rs = stmt.executeQuery(sql);
            SimpleDateFormat dateFormat  = new SimpleDateFormat("MMMMM dd, yyyy");
            this.vec = new ArrayList<>();
            
            while (rs != null && rs.next()) {
                
                amount = (dec.format(rs.getDouble("Amount")));
                newTimeSell = this.dateFormat.format(rs.getTime("PaymentDate"));
                dateSell = dateFormat.format(rs.getDate("PaymentDate"));
                
                this.vec.add(new Object[]{rs.getString("TicketID"), "Php "+amount, dateSell, newTimeSell});
            }
            for(Object[] row : vec){
                tmodel.addRow(row);
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    // for creating and reseting employee table
    public void createTableEmployee() {
        tmodel = new DefaultTableModel();

        empTable.setModel(tmodel);
        tmodel.addColumn("Employee ID");
        tmodel.addColumn("Name");
        ListSelectionModel cellSelectionModel = empTable.getSelectionModel();
        cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        cellSelectionModel.addListSelectionListener((ListSelectionEvent e) -> {
            String selectedData = null;

            int[] selectedRow = empTable.getSelectedRows();
            int[] selectedColumn = empTable.getSelectedColumns();

            for (int i = 0; i < selectedRow.length; i++) {
                for (int j = 0; j < selectedColumn.length; j++) {
                    selectedData = (String) empTable.getValueAt(selectedRow[i], selectedColumn[j]);
                }
            }
            System.out.println("Selected: " + selectedData);
        });
        getEmployeedata();
    }

    // for getting the employee datas from database to the jtable
    public void getEmployeedata() {
        String sql = """
                     select EmployeeID, Fname + ' ' + Lname as 'FullName'
                     from staff
                     order by len(EmployeeID), EmployeeID""";

        try {
            stmt = conn.createStatement();
            this.rs = stmt.executeQuery(sql);
            vec = new ArrayList<>();

            while (this.rs != null && rs.next()) {
                vec.add(new Object[] {rs.getString("EmployeeID"), rs.getString("FullName")});
            }
            for(Object[] row : vec){
                tmodel.addRow(row);
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void createTableLogs() {
        tmodel = new DefaultTableModel();

        logsTable.setModel(tmodel);
        tmodel.addColumn("Employee ID");
        tmodel.addColumn("Full Name");
        tmodel.addColumn("Date Logged");
        tmodel.addColumn("Log In");
        tmodel.addColumn("Log Out");
        ListSelectionModel cellSelectionModel = logsTable.getSelectionModel();
        cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        cellSelectionModel.addListSelectionListener((ListSelectionEvent e) -> {
            String selectedData = null;

            int[] selectedRow = logsTable.getSelectedRows();
            int[] selectedColumn = logsTable.getSelectedColumns();

            for (int i = 0; i < selectedRow.length; i++) {
                for (int j = 0; j < selectedColumn.length; j++) {
                    selectedData = (String) logsTable.getValueAt(selectedRow[i], selectedColumn[j]);
                }
            }
            System.out.println("Selected: " + selectedData);
        });
        getLogsdata();
    }

    // for getting the employee datas from database to the jtable
    public void getLogsdata() {
        Time logInT, logOutT;
        String newLogIn = "", newLogOut = "";

        String sql = """
             select l.Employee_ID, s.Fname +', '+ s.Lname as 'Full Name', l.DateLog, l.Log_In, l.Log_Out
             	from LOGS l left join staff s on l.Employee_ID = s.EmployeeID
             	order by l.DateLog, l.Log_In""";

        try {
            this.stmt = conn.createStatement();
            this.rs = stmt.executeQuery(sql);
            vec = new ArrayList<>();
            while (rs.next()) {
                logInT = rs.getTime("Log_In");
                newLogIn = (logInT != null) ? dateFormat.format(logInT) : "";

                logOutT = rs.getTime("Log_Out");
                newLogOut = (logOutT != null) ? dateFormat.format(logOutT) : "";
                
                vec.add(new Object[] {rs.getString("Employee_ID"), rs.getString("Full Name"), rs.getString("DateLog"), newLogIn, newLogOut});
            }
            
            for(Object[] row : vec){
                tmodel.addRow(row);
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

        jPanel3 = new javax.swing.JPanel();
        sales = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        salesTable = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        datePicker2 = new com.github.lgooddatepicker.components.DatePicker();
        logs = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        logsTable = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        employee = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        empTable = new javax.swing.JTable();
        jLabel14 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        logsB = new javax.swing.JButton();
        staffsB = new javax.swing.JButton();
        addStaffsB = new javax.swing.JButton();
        moviesB = new javax.swing.JButton();
        salesB1 = new javax.swing.JButton();
        addMoviesB1 = new javax.swing.JButton();
        logOut = new javax.swing.JButton();
        movies = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        MovieTable = new javax.swing.JTable();
        jLabel13 = new javax.swing.JLabel();
        MovieSearchField = new javax.swing.JTextField();
        Select_Button_Movies = new javax.swing.JButton();
        addMovies = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        AddMovie_AddMovie_Label = new javax.swing.JLabel();
        AddMovie_MovieID_Label = new javax.swing.JLabel();
        AddMovie_MovieID_TextField = new javax.swing.JTextField();
        AddMovie_Title_Label1 = new javax.swing.JLabel();
        AddMovie_Title_TextField1 = new javax.swing.JTextField();
        AddMovie_Genre_Label = new javax.swing.JLabel();
        AddMovie_Genre_TextField = new javax.swing.JTextField();
        AddMovie_Director_Label = new javax.swing.JLabel();
        AddMovie_Director_TextField = new javax.swing.JTextField();
        AddMovie_Duration_Label = new javax.swing.JLabel();
        AddMovie_Duration_TextField = new javax.swing.JTextField();
        AddMovie_Price_Label = new javax.swing.JLabel();
        AddMovie_Price_TextField = new javax.swing.JTextField();
        AddMovie_AddMovie_Button = new javax.swing.JButton();
        AddMovie_Back_Button = new javax.swing.JButton();
        MoviePoster = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        PosterName = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        addEmplyee = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        AddStaff_StaffDetailsLabel = new javax.swing.JLabel();
        AddStaff_EmployeeIDLabel = new javax.swing.JLabel();
        AddStaff_EmployeeID_TextField = new javax.swing.JTextField();
        AddStaff_FirstNameLabel = new javax.swing.JLabel();
        AddStaff_FirstName_TextField = new javax.swing.JTextField();
        AddStaff_LastNameLabel = new javax.swing.JLabel();
        AddStaff_LastName_TextField = new javax.swing.JTextField();
        AddStaff_EmailLabel = new javax.swing.JLabel();
        AddStaff_Email_TextField = new javax.swing.JTextField();
        AddStaff_PhoneNumberLabel = new javax.swing.JLabel();
        AddStaff_PhoneNumber_TextField1 = new javax.swing.JTextField();
        Add_Staff = new javax.swing.JButton();
        Add_Another_Staff = new javax.swing.JButton();
        Back = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel3.setBackground(new java.awt.Color(211, 191, 114));
        jPanel3.setLayout(null);

        sales.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Bookman Old Style", 1, 36)); // NOI18N
        jLabel3.setText("Sales");

        salesTable.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        salesTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        salesTable.setToolTipText("");
        salesTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_NEXT_COLUMN);
        salesTable.setFocusable(false);
        salesTable.setRowHeight(40);
        salesTable.setShowGrid(false);
        jScrollPane1.setViewportView(salesTable);

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel9.setText("Receipt No.");

        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton1.setText("Search");
        jButton1.setFocusPainted(false);
        jButton1.setMargin(new java.awt.Insets(2, 30, 3, 30));
        jButton1.setOpaque(true);

        datePicker2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        javax.swing.GroupLayout salesLayout = new javax.swing.GroupLayout(sales);
        sales.setLayout(salesLayout);
        salesLayout.setHorizontalGroup(
            salesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(salesLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(salesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(salesLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(29, 29, 29)
                        .addComponent(datePicker2, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(514, 514, 514))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 854, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(53, Short.MAX_VALUE))
            .addGroup(salesLayout.createSequentialGroup()
                .addGap(277, 277, 277)
                .addGroup(salesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, salesLayout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, salesLayout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(138, 138, 138)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        salesLayout.setVerticalGroup(
            salesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(salesLayout.createSequentialGroup()
                .addGroup(salesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(salesLayout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jLabel3))
                    .addGroup(salesLayout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(datePicker2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(47, 47, 47)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addGroup(salesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addContainerGap(80, Short.MAX_VALUE))
        );

        jPanel3.add(sales);
        sales.setBounds(162, 0, 940, 652);

        logs.setBackground(new java.awt.Color(255, 255, 255));

        jLabel4.setFont(new java.awt.Font("Bookman Old Style", 1, 36)); // NOI18N
        jLabel4.setText("Logs");

        logsTable.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        logsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        logsTable.setRowHeight(40);
        jScrollPane2.setViewportView(logsTable);

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel10.setText("Receipt No.");

        jTextField2.setText("jTextField1");

        jButton2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton2.setText("Search");
        jButton2.setFocusPainted(false);
        jButton2.setMargin(new java.awt.Insets(2, 30, 3, 30));
        jButton2.setOpaque(true);

        javax.swing.GroupLayout logsLayout = new javax.swing.GroupLayout(logs);
        logs.setLayout(logsLayout);
        logsLayout.setHorizontalGroup(
            logsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(logsLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(logsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 854, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, logsLayout.createSequentialGroup()
                .addGap(277, 277, 277)
                .addGroup(logsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, logsLayout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, logsLayout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addGap(138, 138, 138)))
                .addGap(270, 270, 270))
        );
        logsLayout.setVerticalGroup(
            logsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(logsLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel4)
                .addGap(47, 47, 47)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addGroup(logsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2)
                .addContainerGap(76, Short.MAX_VALUE))
        );

        jPanel3.add(logs);
        logs.setBounds(162, 0, 939, 652);

        employee.setBackground(new java.awt.Color(255, 255, 255));
        employee.setPreferredSize(new java.awt.Dimension(939, 652));

        jLabel5.setFont(new java.awt.Font("Bookman Old Style", 1, 36)); // NOI18N
        jLabel5.setText("Employees");

        empTable.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        empTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        empTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_NEXT_COLUMN);
        empTable.setRowHeight(40);
        jScrollPane4.setViewportView(empTable);

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel14.setText("Receipt No.");

        jTextField5.setText("jTextField1");

        jButton4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton4.setText("Search");
        jButton4.setFocusPainted(false);
        jButton4.setMargin(new java.awt.Insets(2, 30, 3, 30));
        jButton4.setOpaque(true);

        javax.swing.GroupLayout employeeLayout = new javax.swing.GroupLayout(employee);
        employee.setLayout(employeeLayout);
        employeeLayout.setHorizontalGroup(
            employeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(employeeLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(employeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 854, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, employeeLayout.createSequentialGroup()
                .addGap(277, 277, 277)
                .addGroup(employeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, employeeLayout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, employeeLayout.createSequentialGroup()
                        .addComponent(jButton4)
                        .addGap(138, 138, 138)))
                .addGap(270, 270, 270))
        );
        employeeLayout.setVerticalGroup(
            employeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(employeeLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel5)
                .addGap(47, 47, 47)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addGroup(employeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton4)
                .addContainerGap(80, Short.MAX_VALUE))
        );

        jPanel3.add(employee);
        employee.setBounds(162, 0, 939, 652);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/AAG Movie Ticketing System Logo .png"))); // NOI18N
        jPanel3.add(jLabel1);
        jLabel1.setBounds(6, 0, 150, 150);

        jLabel2.setFont(new java.awt.Font("Bookman Old Style", 0, 24)); // NOI18N
        jLabel2.setText("Admin");
        jPanel3.add(jLabel2);
        jLabel2.setBounds(39, 156, 77, 29);

        jPanel1.setBackground(new java.awt.Color(221, 211, 171));
        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(192, 156, 117), null, null));
        jPanel1.setOpaque(false);
        jPanel1.setLayout(null);

        logsB.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        logsB.setText("Logs");
        logsB.setMargin(new java.awt.Insets(2, 20, 3, 20));
        logsB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logsBActionPerformed(evt);
            }
        });
        jPanel1.add(logsB);
        logsB.setBounds(10, 60, 130, 27);

        staffsB.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        staffsB.setText("Staffs");
        staffsB.setMargin(new java.awt.Insets(2, 20, 3, 20));
        staffsB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                staffsBActionPerformed(evt);
            }
        });
        jPanel1.add(staffsB);
        staffsB.setBounds(10, 110, 130, 27);

        addStaffsB.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        addStaffsB.setText("Add Staffs");
        addStaffsB.setMargin(new java.awt.Insets(2, 18, 3, 18));
        addStaffsB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addStaffsBActionPerformed(evt);
            }
        });
        jPanel1.add(addStaffsB);
        addStaffsB.setBounds(10, 160, 130, 27);

        moviesB.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        moviesB.setText("Movies");
        moviesB.setMargin(new java.awt.Insets(2, 18, 3, 18));
        moviesB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moviesBActionPerformed(evt);
            }
        });
        jPanel1.add(moviesB);
        moviesB.setBounds(10, 210, 130, 27);

        salesB1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        salesB1.setText("Sales");
        salesB1.setMargin(new java.awt.Insets(2, 20, 3, 20));
        salesB1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salesB1ActionPerformed(evt);
            }
        });
        jPanel1.add(salesB1);
        salesB1.setBounds(10, 10, 130, 27);

        addMoviesB1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        addMoviesB1.setText("Add Movies");
        addMoviesB1.setMargin(new java.awt.Insets(2, 12, 3, 12));
        addMoviesB1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addMoviesB1ActionPerformed(evt);
            }
        });
        jPanel1.add(addMoviesB1);
        addMoviesB1.setBounds(10, 260, 130, 27);

        jPanel3.add(jPanel1);
        jPanel1.setBounds(6, 224, 150, 310);

        logOut.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        logOut.setText("Log Out");
        logOut.setMargin(new java.awt.Insets(2, 12, 3, 12));
        logOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logOutActionPerformed(evt);
            }
        });
        jPanel3.add(logOut);
        logOut.setBounds(10, 570, 140, 27);

        movies.setBackground(new java.awt.Color(255, 255, 255));
        movies.setMinimumSize(new java.awt.Dimension(932, 652));
        movies.setPreferredSize(new java.awt.Dimension(939, 652));
        movies.setLayout(null);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 3, 36)); // NOI18N
        jLabel7.setText("Movies");
        movies.add(jLabel7);
        jLabel7.setBounds(34, 39, 117, 48);

        MovieTable.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        MovieTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        MovieTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_NEXT_COLUMN);
        MovieTable.setRowHeight(40);
        jScrollPane3.setViewportView(MovieTable);

        movies.add(jScrollPane3);
        jScrollPane3.setBounds(45, 132, 847, 365);

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel13.setText("Search: ");
        movies.add(jLabel13);
        jLabel13.setBounds(210, 510, 70, 25);

        MovieSearchField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MovieSearchFieldActionPerformed(evt);
            }
        });
        MovieSearchField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                MovieSearchFieldKeyReleased(evt);
            }
        });
        movies.add(MovieSearchField);
        MovieSearchField.setBounds(297, 506, 365, 30);

        Select_Button_Movies.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        Select_Button_Movies.setText("Select");
        Select_Button_Movies.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Select_Button_MoviesActionPerformed(evt);
            }
        });
        movies.add(Select_Button_Movies);
        Select_Button_Movies.setBounds(391, 542, 150, 41);

        jPanel3.add(movies);
        movies.setBounds(160, 0, 940, 652);

        addMovies.setBackground(new java.awt.Color(255, 255, 255));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 3, 36)); // NOI18N
        jLabel8.setText("Add Movies");

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(153, 153, 153), new java.awt.Color(0, 0, 0)));
        jPanel4.setPreferredSize(new java.awt.Dimension(676, 424));
        jPanel4.setLayout(null);

        AddMovie_AddMovie_Label.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        AddMovie_AddMovie_Label.setText("Movie Details");
        AddMovie_AddMovie_Label.setMaximumSize(new java.awt.Dimension(156, 31));
        AddMovie_AddMovie_Label.setMinimumSize(new java.awt.Dimension(156, 31));
        AddMovie_AddMovie_Label.setPreferredSize(new java.awt.Dimension(156, 31));
        jPanel4.add(AddMovie_AddMovie_Label);
        AddMovie_AddMovie_Label.setBounds(61, 29, 175, 31);

        AddMovie_MovieID_Label.setFont(new java.awt.Font("Segoe UI", 0, 23)); // NOI18N
        AddMovie_MovieID_Label.setText("Movie ID: ");
        AddMovie_MovieID_Label.setPreferredSize(new java.awt.Dimension(103, 31));
        jPanel4.add(AddMovie_MovieID_Label);
        AddMovie_MovieID_Label.setBounds(201, 78, 103, 31);

        AddMovie_MovieID_TextField.setEditable(false);
        AddMovie_MovieID_TextField.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        AddMovie_MovieID_TextField.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(102, 102, 102), new java.awt.Color(153, 153, 153), new java.awt.Color(51, 51, 51), new java.awt.Color(204, 204, 204)));
        jPanel4.add(AddMovie_MovieID_TextField);
        AddMovie_MovieID_TextField.setBounds(343, 78, 309, 31);

        AddMovie_Title_Label1.setFont(new java.awt.Font("Segoe UI", 0, 23)); // NOI18N
        AddMovie_Title_Label1.setText("Title: ");
        AddMovie_Title_Label1.setPreferredSize(new java.awt.Dimension(103, 31));
        jPanel4.add(AddMovie_Title_Label1);
        AddMovie_Title_Label1.setBounds(201, 121, 103, 31);

        AddMovie_Title_TextField1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        AddMovie_Title_TextField1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(102, 102, 102), new java.awt.Color(153, 153, 153), new java.awt.Color(51, 51, 51), new java.awt.Color(204, 204, 204)));
        jPanel4.add(AddMovie_Title_TextField1);
        AddMovie_Title_TextField1.setBounds(343, 121, 309, 32);

        AddMovie_Genre_Label.setFont(new java.awt.Font("Segoe UI", 0, 23)); // NOI18N
        AddMovie_Genre_Label.setText("Genre:");
        AddMovie_Genre_Label.setPreferredSize(new java.awt.Dimension(103, 31));
        jPanel4.add(AddMovie_Genre_Label);
        AddMovie_Genre_Label.setBounds(201, 159, 91, 31);

        AddMovie_Genre_TextField.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        AddMovie_Genre_TextField.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(102, 102, 102), new java.awt.Color(153, 153, 153), new java.awt.Color(51, 51, 51), new java.awt.Color(204, 204, 204)));
        AddMovie_Genre_TextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddMovie_Genre_TextFieldActionPerformed(evt);
            }
        });
        jPanel4.add(AddMovie_Genre_TextField);
        AddMovie_Genre_TextField.setBounds(343, 163, 309, 32);

        AddMovie_Director_Label.setFont(new java.awt.Font("Segoe UI", 0, 23)); // NOI18N
        AddMovie_Director_Label.setText("Director: ");
        AddMovie_Director_Label.setPreferredSize(new java.awt.Dimension(103, 31));
        jPanel4.add(AddMovie_Director_Label);
        AddMovie_Director_Label.setBounds(201, 201, 119, 31);

        AddMovie_Director_TextField.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        AddMovie_Director_TextField.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(102, 102, 102), new java.awt.Color(153, 153, 153), new java.awt.Color(51, 51, 51), new java.awt.Color(204, 204, 204)));
        AddMovie_Director_TextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddMovie_Director_TextFieldActionPerformed(evt);
            }
        });
        jPanel4.add(AddMovie_Director_TextField);
        AddMovie_Director_TextField.setBounds(343, 205, 309, 32);

        AddMovie_Duration_Label.setFont(new java.awt.Font("Segoe UI", 0, 23)); // NOI18N
        AddMovie_Duration_Label.setText("Duration:");
        AddMovie_Duration_Label.setPreferredSize(new java.awt.Dimension(103, 31));
        jPanel4.add(AddMovie_Duration_Label);
        AddMovie_Duration_Label.setBounds(201, 243, 119, 31);

        AddMovie_Duration_TextField.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        AddMovie_Duration_TextField.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(102, 102, 102), new java.awt.Color(153, 153, 153), new java.awt.Color(51, 51, 51), new java.awt.Color(204, 204, 204)));
        jPanel4.add(AddMovie_Duration_TextField);
        AddMovie_Duration_TextField.setBounds(343, 247, 309, 32);

        AddMovie_Price_Label.setFont(new java.awt.Font("Segoe UI", 0, 23)); // NOI18N
        AddMovie_Price_Label.setText("Price:");
        AddMovie_Price_Label.setPreferredSize(new java.awt.Dimension(103, 31));
        jPanel4.add(AddMovie_Price_Label);
        AddMovie_Price_Label.setBounds(201, 285, 119, 31);

        AddMovie_Price_TextField.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        AddMovie_Price_TextField.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(102, 102, 102), new java.awt.Color(153, 153, 153), new java.awt.Color(51, 51, 51), new java.awt.Color(204, 204, 204)));
        AddMovie_Price_TextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                AddMovie_Price_TextFieldKeyTyped(evt);
            }
        });
        jPanel4.add(AddMovie_Price_TextField);
        AddMovie_Price_TextField.setBounds(343, 289, 309, 32);

        AddMovie_AddMovie_Button.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        AddMovie_AddMovie_Button.setText("Add Movie");
        AddMovie_AddMovie_Button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AddMovie_AddMovie_ButtonMouseClicked(evt);
            }
        });
        AddMovie_AddMovie_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddMovie_AddMovie_ButtonActionPerformed(evt);
            }
        });
        jPanel4.add(AddMovie_AddMovie_Button);
        AddMovie_AddMovie_Button.setBounds(505, 341, 147, 35);

        AddMovie_Back_Button.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        AddMovie_Back_Button.setText("Back");
        AddMovie_Back_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddMovie_Back_ButtonActionPerformed(evt);
            }
        });
        jPanel4.add(AddMovie_Back_Button);
        AddMovie_Back_Button.setBounds(340, 340, 147, 35);
        jPanel4.add(MoviePoster);
        MoviePoster.setBounds(35, 90, 138, 180);

        jButton5.setText("Add Poster");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton5);
        jButton5.setBounds(50, 290, 100, 30);

        PosterName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PosterNameActionPerformed(evt);
            }
        });
        jPanel4.add(PosterName);
        PosterName.setBounds(20, 340, 200, 30);
        jPanel4.add(jLabel11);
        jLabel11.setBounds(0, 0, 0, 430);

        javax.swing.GroupLayout addMoviesLayout = new javax.swing.GroupLayout(addMovies);
        addMovies.setLayout(addMoviesLayout);
        addMoviesLayout.setHorizontalGroup(
            addMoviesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addMoviesLayout.createSequentialGroup()
                .addGroup(addMoviesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(addMoviesLayout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(addMoviesLayout.createSequentialGroup()
                        .addGap(140, 140, 140)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(124, Short.MAX_VALUE))
        );
        addMoviesLayout.setVerticalGroup(
            addMoviesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addMoviesLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel8)
                .addGap(42, 42, 42)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(106, Short.MAX_VALUE))
        );

        jPanel3.add(addMovies);
        addMovies.setBounds(160, 0, 940, 650);

        addEmplyee.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(153, 153, 153), new java.awt.Color(0, 0, 0)));

        AddStaff_StaffDetailsLabel.setFont(new java.awt.Font("Segoe UI", 1, 23)); // NOI18N
        AddStaff_StaffDetailsLabel.setText("Staff Details");

        AddStaff_EmployeeIDLabel.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        AddStaff_EmployeeIDLabel.setText("Employee ID: ");

        AddStaff_EmployeeID_TextField.setEditable(false);
        AddStaff_EmployeeID_TextField.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        AddStaff_EmployeeID_TextField.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(102, 102, 102), new java.awt.Color(153, 153, 153), new java.awt.Color(51, 51, 51), new java.awt.Color(204, 204, 204)));
        AddStaff_EmployeeID_TextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddStaff_EmployeeID_TextFieldActionPerformed(evt);
            }
        });

        AddStaff_FirstNameLabel.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        AddStaff_FirstNameLabel.setText("First Name: ");

        AddStaff_FirstName_TextField.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        AddStaff_FirstName_TextField.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(102, 102, 102), new java.awt.Color(153, 153, 153), new java.awt.Color(51, 51, 51), new java.awt.Color(204, 204, 204)));
        AddStaff_FirstName_TextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddStaff_FirstName_TextFieldActionPerformed(evt);
            }
        });

        AddStaff_LastNameLabel.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        AddStaff_LastNameLabel.setText("Last Name: ");

        AddStaff_LastName_TextField.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        AddStaff_LastName_TextField.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(102, 102, 102), new java.awt.Color(153, 153, 153), new java.awt.Color(51, 51, 51), new java.awt.Color(204, 204, 204)));
        AddStaff_LastName_TextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddStaff_LastName_TextFieldActionPerformed(evt);
            }
        });

        AddStaff_EmailLabel.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        AddStaff_EmailLabel.setText("Email: ");

        AddStaff_Email_TextField.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        AddStaff_Email_TextField.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(102, 102, 102), new java.awt.Color(153, 153, 153), new java.awt.Color(51, 51, 51), new java.awt.Color(204, 204, 204)));
        AddStaff_Email_TextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddStaff_Email_TextFieldActionPerformed(evt);
            }
        });

        AddStaff_PhoneNumberLabel.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        AddStaff_PhoneNumberLabel.setText("Phone Number: ");

        AddStaff_PhoneNumber_TextField1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        AddStaff_PhoneNumber_TextField1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(102, 102, 102), new java.awt.Color(153, 153, 153), new java.awt.Color(51, 51, 51), new java.awt.Color(204, 204, 204)));
        AddStaff_PhoneNumber_TextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddStaff_PhoneNumber_TextField1ActionPerformed(evt);
            }
        });

        Add_Staff.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        Add_Staff.setText("Add Staff");
        Add_Staff.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Add_StaffMouseClicked(evt);
            }
        });
        Add_Staff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Add_StaffActionPerformed(evt);
            }
        });

        Add_Another_Staff.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        Add_Another_Staff.setText("Add Another Staff");
        Add_Another_Staff.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Add_Another_StaffMouseClicked(evt);
            }
        });
        Add_Another_Staff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Add_Another_StaffActionPerformed(evt);
            }
        });

        Back.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        Back.setText("Back");
        Back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BackActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(Back, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Add_Another_Staff)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Add_Staff, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(AddStaff_EmployeeIDLabel)
                            .addComponent(AddStaff_EmailLabel)
                            .addComponent(AddStaff_LastNameLabel)
                            .addComponent(AddStaff_PhoneNumberLabel)
                            .addComponent(AddStaff_FirstNameLabel))
                        .addGap(86, 86, 86)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(AddStaff_LastName_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(AddStaff_FirstName_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(AddStaff_EmployeeID_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(AddStaff_Email_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(AddStaff_PhoneNumber_TextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(AddStaff_StaffDetailsLabel)))
                .addContainerGap(82, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(AddStaff_StaffDetailsLabel)
                .addGap(52, 52, 52)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AddStaff_EmployeeIDLabel)
                    .addComponent(AddStaff_EmployeeID_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(AddStaff_FirstName_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AddStaff_FirstNameLabel))
                .addGap(7, 7, 7)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addComponent(AddStaff_LastName_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(AddStaff_Email_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(AddStaff_PhoneNumber_TextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addComponent(AddStaff_LastNameLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(AddStaff_EmailLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(AddStaff_PhoneNumberLabel)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 71, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Add_Staff)
                    .addComponent(Add_Another_Staff)
                    .addComponent(Back))
                .addGap(48, 48, 48))
        );

        jLabel6.setFont(new java.awt.Font("Segoe UI", 3, 36)); // NOI18N
        jLabel6.setText("Add Staffs");

        javax.swing.GroupLayout addEmplyeeLayout = new javax.swing.GroupLayout(addEmplyee);
        addEmplyee.setLayout(addEmplyeeLayout);
        addEmplyeeLayout.setHorizontalGroup(
            addEmplyeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 939, Short.MAX_VALUE)
            .addGroup(addEmplyeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(addEmplyeeLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(addEmplyeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(addEmplyeeLayout.createSequentialGroup()
                            .addGap(100, 100, 100)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        addEmplyeeLayout.setVerticalGroup(
            addEmplyeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 652, Short.MAX_VALUE)
            .addGroup(addEmplyeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(addEmplyeeLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel6)
                    .addGap(42, 42, 42)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jPanel3.add(addEmplyee);
        addEmplyee.setBounds(162, 0, 939, 652);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 652, Short.MAX_VALUE)
        );

        setSize(new java.awt.Dimension(1117, 660));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void logOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logOutActionPerformed
        LocalTime lTime = LocalTime.now();
        Time cTime = Time.valueOf(lTime);

        String empID = "", dateLog = "", logIn = "";

        System.out.println(cTime);

        String q1 = """
                    select l.Employee_ID, s.Fname +', '+ s.Lname as 'Full Name', l.DateLog, l.Log_In, l.Log_Out
                    from LOGS l left join staff s on l.Employee_ID = s.EmployeeID
                    order by l.DateLog, l.Log_In""";

        try {

            rs = stmt.executeQuery(q1);

            while (rs.next()) {
//                System.out.println(rs.getString(1)+", "+rs.getString(3)+ ", "+rs.getString(4));
                if (rs.getString("Employee_ID").charAt(0) == 'A') {
                    empID = rs.getString("Employee_ID");
                    dateLog = rs.getString("DateLog");
                    logIn = rs.getString("Log_In");
                }
            }

            String q2 = "UPDATE logs SET Log_Out = '" + cTime + "' where Employee_ID = '" + empID + "' AND DateLog = '" + dateLog + "' AND Log_In = '" + logIn + "';";
            stmt.executeQuery(q2);

        } catch (SQLException ex) {
        }
        try {
            new LogIn.LogIn().setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
        main.Main.choose = 0;
        dispose();
    }//GEN-LAST:event_logOutActionPerformed

    private void staffsBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_staffsBActionPerformed
        sales.setVisible(false);
        logs.setVisible(false);
        employee.setVisible(true);
        addEmplyee.setVisible(false);
        movies.setVisible(false);
        addMovies.setVisible(false);

        createTableEmployee();
    }//GEN-LAST:event_staffsBActionPerformed

    private void addStaffsBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addStaffsBActionPerformed
        sales.setVisible(false);
        logs.setVisible(false);
        employee.setVisible(false);
        addEmplyee.setVisible(true);
        AddStaff_EmployeeID_TextField.setText(newempid);
        movies.setVisible(false);
        addMovies.setVisible(false);
    }//GEN-LAST:event_addStaffsBActionPerformed

    private void moviesBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moviesBActionPerformed
        sales.setVisible(false);
        logs.setVisible(false);
        employee.setVisible(false);
        addEmplyee.setVisible(false);
        movies.setVisible(true);
        addMovies.setVisible(false);
        createTableMovie();
    }//GEN-LAST:event_moviesBActionPerformed

    private void logsBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logsBActionPerformed
        sales.setVisible(false);
        logs.setVisible(true);
        createTableLogs();
        employee.setVisible(false);
        addEmplyee.setVisible(false);
        movies.setVisible(false);
        addMovies.setVisible(false);
    }//GEN-LAST:event_logsBActionPerformed

    private void salesB1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salesB1ActionPerformed
        sales.setVisible(true);
        logs.setVisible(false);
        employee.setVisible(false);
        addEmplyee.setVisible(false);
        movies.setVisible(false);
        addMovies.setVisible(false);
    }//GEN-LAST:event_salesB1ActionPerformed

    private void addMoviesB1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addMoviesB1ActionPerformed
        sales.setVisible(false);
        logs.setVisible(false);
        employee.setVisible(false);
        addEmplyee.setVisible(false);
        movies.setVisible(false);
        addMovies.setVisible(true);
        AddMovie_MovieID_TextField.setText(newmovieid);
    }//GEN-LAST:event_addMoviesB1ActionPerformed

    private void MovieSearchFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MovieSearchFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MovieSearchFieldActionPerformed

    private void MovieSearchFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MovieSearchFieldKeyReleased
        // TODO add your handling code here:
        DefaultTableModel SearchTable = (DefaultTableModel) MovieTable.getModel();
        TableRowSorter<DefaultTableModel> MovieSearch = new TableRowSorter<>(SearchTable);
        MovieTable.setRowSorter(MovieSearch);
        MovieSearch.setRowFilter(RowFilter.regexFilter(MovieSearchField.getText()));
    }//GEN-LAST:event_MovieSearchFieldKeyReleased

    private void Select_Button_MoviesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Select_Button_MoviesActionPerformed

        try {
            selectMovie();

        } catch (SQLException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_Select_Button_MoviesActionPerformed

    private void AddMovie_Genre_TextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddMovie_Genre_TextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AddMovie_Genre_TextFieldActionPerformed

    private void AddMovie_Director_TextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddMovie_Director_TextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AddMovie_Director_TextFieldActionPerformed

    private void AddMovie_Price_TextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AddMovie_Price_TextFieldKeyTyped
        // TODO add your handling code here:
        // for number input only in price textfield
        char c = evt.getKeyChar();
        if (!Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE)) {
            evt.consume();
        }
    }//GEN-LAST:event_AddMovie_Price_TextFieldKeyTyped

    private void AddMovie_AddMovie_ButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AddMovie_AddMovie_ButtonMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_AddMovie_AddMovie_ButtonMouseClicked

    private void AddMovie_AddMovie_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddMovie_AddMovie_ButtonActionPerformed
        // Ang kulang nalang dito is yung pic_poster_location
        // Tapos may error na lumalabas kapag mali yung input sa price (Ex kapag string yung nailagay mag eerror)

        for (int i = 0; i < 4; i++) {
            MovieID = AddMovie_MovieID_TextField.getText();
            Title = AddMovie_Title_TextField1.getText();
            Genre = AddMovie_Genre_TextField.getText();
            Director = AddMovie_Director_TextField.getText();
            Duration = AddMovie_Duration_TextField.getText();
            price = Integer.parseInt(AddMovie_Price_TextField.getText());
            Movie_Pic_Location = PosterName.getText();
            Mpass = "Admin";

            if (AddMovie_MovieID_TextField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please Fill In Data");
                i--;
                break;
            } else if (AddMovie_Title_TextField1.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please Fill In Data");
                i--;
                break;
            } else if (AddMovie_Genre_TextField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please Fill In Data");
                i--;
                break;
            } else if (AddMovie_Director_TextField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please Fill In Data");
                i--;
                break;
            } else if (AddMovie_Price_TextField.getText().isEmpty() || AddMovie_Price_TextField.getText().matches("[a-zA-Z]+")) {
                JOptionPane.showMessageDialog(null, "Please Fill In Data Correctly");
                i--;
                break;
            } else {
                adminpass = JOptionPane.showInputDialog("Enter Admin PassWord: ");
                if (adminpass != null && adminpass.matches(Mpass)) {
                    System.out.println("Data Added: ");
                    System.out.println(MovieID);
                    System.out.println(Title);
                    System.out.println(Genre);
                    System.out.println(Director);
                    System.out.println(AddMovie_Duration_TextField.getText());
                    System.out.println(price);
                    System.out.println(Movie_Pic_Location);
                    System.out.println(PNum);
                    if(Movie_Pic_Location.equals(""))
                    Movie_Pic_Location = "default poster.jpg";

                    try {  // INSERTING VALUES FOR ADDMOVIE
                        Statement stmt2 = conn.createStatement();
                        stmt2 = conn.createStatement();
                        String qry = "insert into Movie (MovieID, Title, Genre, Director, Duration, Price, Movie_pic_loc)"
                        + "values('" + MovieID + "','" + Title + "','" + Genre + "','" + Director + "','" + Duration + "','"
                        + price + "','" + Movie_Pic_Location + "')";
                        int rows = stmt.executeUpdate(qry);
                        if (rows > 0) {
                            DefaultTableModel model = (DefaultTableModel) MovieTable.getModel();
                            model.setRowCount(0);
                            getMovieData();
                            System.out.println("Insert Successful");
                            stmt2.close(); // note lang

                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    showtimeTime = JOptionPane.showConfirmDialog(this, "Add Show Time? ", "Showtime", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (showtimeTime == JOptionPane.YES_OPTION) {
                        try {
                            new Showtime().movieID = AddMovie_MovieID_TextField.getText();
                            new Showtime().setVisible(true);

                        } catch (SQLException ex) {
                            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (ParseException ex) {
                            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    } else if (showtimeTime == JOptionPane.NO_OPTION || showtimeTime == JOptionPane.CLOSED_OPTION) {

                        System.out.println("No");
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Confirmation Cancelled");
                }

                ClearFieldMovies();
                break;
            }
        }
    }//GEN-LAST:event_AddMovie_AddMovie_ButtonActionPerformed

    private void AddMovie_Back_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddMovie_Back_ButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AddMovie_Back_ButtonActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        JnaFileChooser chooser = new JnaFileChooser();
        
        chooser.addFilter("All Files", "*");
        chooser.addFilter("Pictures", "jpg", "jpeg", "png", "gif", "bmp");
        
        String fildest = System.getProperty("user.dir");
        fildest = fildest + "\\Movie Posters\\";

        boolean res = chooser.showOpenDialog(Admin.this);
        if (res == true) {
            file = chooser.getSelectedFile();
            String path = file.getAbsolutePath();
            ImageIcon imgIcon = new ImageIcon(file.getAbsolutePath());
            Rectangle rec = MoviePoster.getBounds();
            Image scaledimg = imgIcon.getImage().getScaledInstance(rec.width, rec.height, Image.SCALE_SMOOTH);
            imgIcon = new ImageIcon(scaledimg);
            MoviePoster.setIcon(imgIcon);
            PosterName.setText(file.getAbsolutePath().substring(file.getAbsolutePath().lastIndexOf("\\") + 1));

                if (file.exists()) {
                    File destfile = new File(fildest + File.separator + file.getName());

                    try (InputStream is = new FileInputStream(file); OutputStream os = new FileOutputStream(destfile)) {

                        int len;
                        float srcfsize = is.available() / 1000.0f;
                        float totalcopied = 0.0f;
                        byte[] byt = new byte[1024];
                        while ((len = is.read(byt)) > 0) {
                            os.write(byt, 0, len);
                            totalcopied += len;
                            System.out.println("\rcopied" + totalcopied / 1000.0f + "kb/" + file + "kb");

                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            } else {
                JOptionPane.showMessageDialog(null, "No Image has been Selected");
            }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void PosterNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PosterNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PosterNameActionPerformed

    private void AddStaff_EmployeeID_TextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddStaff_EmployeeID_TextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AddStaff_EmployeeID_TextFieldActionPerformed

    private void AddStaff_FirstName_TextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddStaff_FirstName_TextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AddStaff_FirstName_TextFieldActionPerformed

    private void AddStaff_LastName_TextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddStaff_LastName_TextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AddStaff_LastName_TextFieldActionPerformed

    private void AddStaff_Email_TextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddStaff_Email_TextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AddStaff_Email_TextFieldActionPerformed

    private void AddStaff_PhoneNumber_TextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddStaff_PhoneNumber_TextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AddStaff_PhoneNumber_TextField1ActionPerformed

    private void Add_StaffMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Add_StaffMouseClicked

        //String EmpId, FName, LName, Email ,PNum;
        EmpId = AddStaff_EmployeeID_TextField.getText();
        FName = AddStaff_FirstName_TextField.getText();
        LName = AddStaff_LastName_TextField.getText();
        PNum = AddStaff_PhoneNumber_TextField1.getText();
        AddStaff_EmployeeID_TextField.setText(newempid);
        for (int i = 0; i < 3; i++) {
            if (AddStaff_EmployeeID_TextField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please Fill In Data");
                i--;
                break;
            } else if (AddStaff_FirstName_TextField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please Fill In Data");
                i--;
                break;
            } else if (AddStaff_LastName_TextField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please Fill In Data");
                i--;
                break;
            } else if (AddStaff_PhoneNumber_TextField1.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please Fill In Data");
                i--;
                break;
            } else {
            }

        }
    }//GEN-LAST:event_Add_StaffMouseClicked

    private void Add_StaffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Add_StaffActionPerformed
        EmpId = AddStaff_EmployeeID_TextField.getText();
        FName = AddStaff_FirstName_TextField.getText();
        LName = AddStaff_LastName_TextField.getText();
        PNum = AddStaff_PhoneNumber_TextField1.getText();
        Email = AddStaff_Email_TextField.getText();
        pass = "E00" + lnum;
        UserPass = " ";
        Mpass = "Admin";

        adminpass = JOptionPane.showInputDialog("Enter Admin Username: ");

        UserPass = JOptionPane.showInputDialog("Enter Admin Password: ");
        if (UserPass != null && UserPass.matches(Mpass)) {
            System.out.println("Data Added: ");
            System.out.println(EmpId);
            System.out.println(FName);
            System.out.println(LName);
            System.out.println(AddStaff_Email_TextField.getText());
            System.out.println(PNum);
            System.out.println(UserPass = pass);
            System.out.println(lnum);

            try {  // INSERTING VALUES FOR ADDSTAFF

                stmt = conn.createStatement();
                String qry = "insert into Staff (EmployeeID,fname, lname, email, phone,username,passw)"
                + "values('" + EmpId + "','" + FName + "','" + LName + "','" + Email + "','" + PNum + "','"
                + EmpId + "','" + UserPass + "')";
                int rows = stmt.executeUpdate(qry);
                if (rows > 0) {
                    System.out.println("Insert Successful");
                }

                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Wrong Input");
        }
    }//GEN-LAST:event_Add_StaffActionPerformed

    private void Add_Another_StaffMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Add_Another_StaffMouseClicked
        newempid = "E" + (lnum + 1);

        AddStaff_EmployeeID_TextField.setText(" ");
        AddStaff_EmployeeID_TextField.setText(newempid);
        AddStaff_FirstName_TextField.setText(" ");
        AddStaff_LastName_TextField.setText(" ");
        AddStaff_Email_TextField.setText(" ");
        AddStaff_PhoneNumber_TextField1.setText(" ");
    }//GEN-LAST:event_Add_Another_StaffMouseClicked

    private void Add_Another_StaffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Add_Another_StaffActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Add_Another_StaffActionPerformed

    private void BackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BackActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BackActionPerformed

    /**
     * @param args
     */
    public static void main(String args[]) {
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            try {
                new Admin().setVisible(true);
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    void getIDs() throws SQLException {
        get_last_empid();
        get_last_movieid();
        get_last_showtimeid();
    }

    //Last EmpID
    static String lempid;
    static int lnum;
    static String newempid;

    void get_last_empid() throws SQLException {
        lempid = "";
        stmt = conn.createStatement();

        String qry = """
                     select * from staff
                     order by len(EmployeeID), EmployeeID""";
        ResultSet rs = stmt.executeQuery(qry);
        while (rs.next()) {
            lempid = rs.getString(1);
        }
        lnum = Integer.parseInt(lempid.substring(1, lempid.length())) + 1;
        String nmpd = "";
        newempid = "E" + String.valueOf(lnum);

        System.out.println(newempid);
    }

    //Last MovieID
    static String lmovieid;
    static int lmovidnum;
    static String newmovieid;

    void get_last_movieid() throws SQLException {
        lmovieid = "";
        Statement stmt = conn.createStatement();

        String qry = "select * from movie order by len(MovieID), MovieID";
        ResultSet rs = stmt.executeQuery(qry);
        while (rs.next()) {
            lmovieid = rs.getString(1);
        }
        lmovidnum = Integer.parseInt(lmovieid.substring(1, lmovieid.length())) + 1;
        newmovieid = "M" + String.valueOf(lmovidnum);
        System.out.println(newmovieid);
        ShowtimeMovieID = newmovieid;
    }

    static String lshowtimeid; // for creating new showtimeid
    int lshowtimeidnum;
    static String newshowtimeid;

    void get_last_showtimeid() throws SQLException {
        lshowtimeid = "";
        Statement stmt = conn.createStatement();

        String qry = "select * from showtime order by len(showtimeID), showtimeID";
        ResultSet rs = stmt.executeQuery(qry);
        while (rs.next()) {
            lshowtimeid = rs.getString(2);
        }
        lshowtimeidnum = Integer.parseInt(lshowtimeid.substring(1)) + 1;
        newshowtimeid = "S" + String.valueOf(lshowtimeidnum);
        System.out.println(newshowtimeid);
    }

    public void getMovieData() {
        String sql1 = """
                  select MovieID, Title
                   from movie 
                   order by len (MovieID), MovieID""";

        try {

            stmt = conn.createStatement();
            this.rs = stmt.executeQuery(sql1);
            this.vec = new ArrayList<>();

            while (rs != null && rs.next()) {

                this.vec.add(new Object[]{rs.getString("MovieID"), rs.getString("Title")});
                System.out.println(rs.getString("MovieID"));
                System.out.println(rs.getString("Title"));
            }

            for (Object[] row : vec) {
                tmodel1.addRow(row);
            }

        } catch (SQLException e) {
            System.out.println(e);

        }
    }

    public void createTableMovie() {
        tmodel1 = new DefaultTableModel();
        MovieTable.setModel(tmodel1);
        tmodel1.addColumn("Movie ID");
        tmodel1.addColumn("Movie Title");
        ListSelectionModel cellSelectionModel = MovieTable.getSelectionModel();
        cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        cellSelectionModel.addListSelectionListener((e) -> {
            String SelectedMovieData = null;

            int[] selectedRow = MovieTable.getSelectedRows();
            int[] selectedColumn = MovieTable.getSelectedColumns();

            for (int i = 0; i < selectedRow.length; i++) {
                for (int j = 0; j < selectedColumn.length; j++) {
                    SelectedMovieData = (String) MovieTable.getValueAt(selectedRow[i], selectedColumn[j]);
                }
                System.out.println("Selected: " + SelectedMovieData);
            }
        });
        getMovieData();
    }

    public void ClearFieldStaff() { // for clearing fields sa staff
        AddStaff_EmployeeID_TextField.setText(newempid = "E" + (lnum + 1));
        AddStaff_FirstName_TextField.setText(null);
        AddStaff_LastName_TextField.setText(null);
        AddStaff_Email_TextField.setText(null);
        AddStaff_PhoneNumber_TextField1.setText(null);
        lnum += 1;
    }

    public void ClearFieldMovies() {
        AddMovie_MovieID_TextField.setText(ShowtimeMovieID);
        AddMovie_Title_TextField1.setText(null);
        AddMovie_Genre_TextField.setText(null);
        AddMovie_Director_TextField.setText(null);
        AddMovie_Duration_TextField.setText(null);
        AddMovie_Price_TextField.setText(null);
        MoviePoster.setIcon(null);
        PosterName.setText(null);
        lmovidnum += 1;
    }

    public void selectMovie() throws SQLException {
        int i = MovieTable.getSelectedRow();
        String movieID = tmodel1.getValueAt(i, 0).toString();
//        System.out.println(movieID);

        String query = "select * from movie where MovieID = '" + movieID + "' order by len(MovieID), MovieID";
        ResultSet rs = stmt.executeQuery(query);
        if (rs.next()) {
            System.out.println(rs.getString("Title"));
            System.out.println(rs.getString("Genre"));

            String MovDetsMovID, MovDetsTitle, MovDetsGenre, MovDetsDir, MovDetsDur, MovDetPrice, MovDetsPLoc;
            MovieDetails mds = new MovieDetails();

            MovDetsMovID = rs.getString(1);
            MovDetsTitle = rs.getString(2);
            MovDetsGenre = rs.getString(3);
            MovDetsDir = rs.getString(4);
            MovDetsDur = rs.getString(5);
            MovDetPrice = rs.getString(6);
            MovDetsPLoc = rs.getString(7);

            MovieDetails movieDetails = new MovieDetails(MovDetsMovID, MovDetsTitle, MovDetsGenre, MovDetsDir, MovDetsDur, MovDetPrice);
            movieDetails.setVisible(true);

        }
    }
    // kukuhanin yung showtime id 
    // tapos iseset sa null yung mga value
    
    //


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddMovie_AddMovie_Button;
    private javax.swing.JLabel AddMovie_AddMovie_Label;
    private javax.swing.JButton AddMovie_Back_Button;
    private javax.swing.JLabel AddMovie_Director_Label;
    private javax.swing.JTextField AddMovie_Director_TextField;
    private javax.swing.JLabel AddMovie_Duration_Label;
    private javax.swing.JTextField AddMovie_Duration_TextField;
    private javax.swing.JLabel AddMovie_Genre_Label;
    private javax.swing.JTextField AddMovie_Genre_TextField;
    private javax.swing.JLabel AddMovie_MovieID_Label;
    private javax.swing.JTextField AddMovie_MovieID_TextField;
    private javax.swing.JLabel AddMovie_Price_Label;
    private javax.swing.JTextField AddMovie_Price_TextField;
    private javax.swing.JLabel AddMovie_Title_Label1;
    private javax.swing.JTextField AddMovie_Title_TextField1;
    private javax.swing.JLabel AddStaff_EmailLabel;
    private javax.swing.JTextField AddStaff_Email_TextField;
    private javax.swing.JLabel AddStaff_EmployeeIDLabel;
    private javax.swing.JTextField AddStaff_EmployeeID_TextField;
    private javax.swing.JLabel AddStaff_FirstNameLabel;
    private javax.swing.JTextField AddStaff_FirstName_TextField;
    private javax.swing.JLabel AddStaff_LastNameLabel;
    private javax.swing.JTextField AddStaff_LastName_TextField;
    private javax.swing.JLabel AddStaff_PhoneNumberLabel;
    private javax.swing.JTextField AddStaff_PhoneNumber_TextField1;
    private javax.swing.JLabel AddStaff_StaffDetailsLabel;
    private javax.swing.JButton Add_Another_Staff;
    private javax.swing.JButton Add_Staff;
    private javax.swing.JButton Back;
    public javax.swing.JLabel MoviePoster;
    private javax.swing.JTextField MovieSearchField;
    private javax.swing.JTable MovieTable;
    private javax.swing.JTextField PosterName;
    private javax.swing.JButton Select_Button_Movies;
    private javax.swing.JPanel addEmplyee;
    private javax.swing.JPanel addMovies;
    private javax.swing.JButton addMoviesB1;
    private javax.swing.JButton addStaffsB;
    private com.github.lgooddatepicker.components.DatePicker datePicker2;
    private javax.swing.JTable empTable;
    private javax.swing.JPanel employee;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JButton logOut;
    private javax.swing.JPanel logs;
    private javax.swing.JButton logsB;
    private javax.swing.JTable logsTable;
    private javax.swing.JPanel movies;
    private javax.swing.JButton moviesB;
    private javax.swing.JPanel sales;
    private javax.swing.JButton salesB1;
    private javax.swing.JTable salesTable;
    private javax.swing.JButton staffsB;
    // End of variables declaration//GEN-END:variables

}