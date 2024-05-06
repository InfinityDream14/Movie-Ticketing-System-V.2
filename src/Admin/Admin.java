/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Admin;

import java.awt.geom.RoundRectangle2D;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

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

    String MovieID, Title, Genre, Director, Mpass, Muserpass = " "; // for creating new Movie
    String Movie_Pic_Location = "null"; // for creating new Movie
    int price; // for creating new Movie
    String Duration = "null"; // for creating new Movie;

    Statement stmt;
    Connection conn;
    DefaultTableModel tmodel = new DefaultTableModel();

    public Admin() throws SQLException, ClassNotFoundException {
        connectToDatabase();
        initComponents();

        sales.setVisible(true);
        employee.setVisible(false);
        addEmplyee.setVisible(false);
        movies.setVisible(false);
        addMovies.setVisible(false);
                
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
            ex.printStackTrace();
        }
    }

    // for creating and reseting sales table
    public void createTableSales() {
        tmodel = new DefaultTableModel();

        salesTable.setModel(tmodel);
        tmodel.addColumn("Ticket ID");
        tmodel.addColumn("Date Purchased");
        ListSelectionModel cellSelectionModel = salesTable.getSelectionModel();
        cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        cellSelectionModel.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                String selectedData = null;

                int[] selectedRow = salesTable.getSelectedRows();
                int[] selectedColumn = salesTable.getSelectedColumns();

                for (int i = 0; i < selectedRow.length; i++) {
                    for (int j = 0; j < selectedColumn.length; j++) {
                        selectedData = (String) salesTable.getValueAt(selectedRow[i], selectedColumn[j]);
                    }
                }
                System.out.println("Selected: " + selectedData);
            }
        });
        getSalesData();
    }

    // for getting the sales datas from database to the jtable
    public void getSalesData() {
        String sql = """
                     select t.TicketID, p.PaymentDate
                     from ticket t join payment p on t.TicketPaymentID = p.PaymentID""";

        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs != null && rs.next()) {
                Vector vec = new Vector();
                vec.add(rs.getString("TicketID"));
                vec.add(rs.getString("PaymentDate"));
                tmodel.addRow(vec);
            }

        } catch (Exception e) {
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

        cellSelectionModel.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                String selectedData = null;

                int[] selectedRow = empTable.getSelectedRows();
                int[] selectedColumn = empTable.getSelectedColumns();

                for (int i = 0; i < selectedRow.length; i++) {
                    for (int j = 0; j < selectedColumn.length; j++) {
                        selectedData = (String) empTable.getValueAt(selectedRow[i], selectedColumn[j]);
                    }
                }
                System.out.println("Selected: " + selectedData);
            }
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
            ResultSet rs = stmt.executeQuery(sql);

            while (rs != null && rs.next()) {
                Vector vec = new Vector();
                vec.add(rs.getString("EmployeeID"));
                vec.add(rs.getString("FullName"));
                tmodel.addRow(vec);
            }

        } catch (Exception e) {
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
        AddMovie_AddAnotherMovie_Button = new javax.swing.JButton();
        AddMovie_Back_Button = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        addMoviesB = new javax.swing.JButton();
        logsB = new javax.swing.JButton();
        staffsB = new javax.swing.JButton();
        addStaffsB = new javax.swing.JButton();
        moviesB = new javax.swing.JButton();
        salesB1 = new javax.swing.JButton();
        movies = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel13 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel3.setBackground(new java.awt.Color(211, 191, 114));
        jPanel3.setLayout(null);

        sales.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Bookman Old Style", 1, 36)); // NOI18N
        jLabel3.setText("Logs");

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
        jScrollPane1.setViewportView(salesTable);

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel9.setText("Receipt No.");

        jTextField1.setText("jTextField1");

        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton1.setText("Search");
        jButton1.setFocusPainted(false);
        jButton1.setMargin(new java.awt.Insets(2, 30, 3, 30));
        jButton1.setOpaque(true);

        javax.swing.GroupLayout salesLayout = new javax.swing.GroupLayout(sales);
        sales.setLayout(salesLayout);
        salesLayout.setHorizontalGroup(
            salesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(salesLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(salesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 854, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, salesLayout.createSequentialGroup()
                .addGap(277, 277, 277)
                .addGroup(salesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, salesLayout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, salesLayout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(138, 138, 138)))
                .addGap(270, 270, 270))
        );
        salesLayout.setVerticalGroup(
            salesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(salesLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel3)
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
        sales.setBounds(162, 0, 939, 652);

        logs.setBackground(new java.awt.Color(255, 255, 255));

        jLabel4.setFont(new java.awt.Font("Bookman Old Style", 1, 36)); // NOI18N
        jLabel4.setText("Sales");

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

        addMovies.setBackground(new java.awt.Color(255, 255, 255));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 3, 36)); // NOI18N
        jLabel8.setText("Add Movies");

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(153, 153, 153), new java.awt.Color(0, 0, 0)));
        jPanel4.setPreferredSize(new java.awt.Dimension(676, 424));

        AddMovie_AddMovie_Label.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        AddMovie_AddMovie_Label.setText("Movie Details");
        AddMovie_AddMovie_Label.setMaximumSize(new java.awt.Dimension(156, 31));
        AddMovie_AddMovie_Label.setMinimumSize(new java.awt.Dimension(156, 31));
        AddMovie_AddMovie_Label.setPreferredSize(new java.awt.Dimension(156, 31));

        AddMovie_MovieID_Label.setFont(new java.awt.Font("Segoe UI", 0, 23)); // NOI18N
        AddMovie_MovieID_Label.setText("Movie ID: ");
        AddMovie_MovieID_Label.setPreferredSize(new java.awt.Dimension(103, 31));

        AddMovie_MovieID_TextField.setEditable(false);
        AddMovie_MovieID_TextField.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        AddMovie_MovieID_TextField.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(102, 102, 102), new java.awt.Color(153, 153, 153), new java.awt.Color(51, 51, 51), new java.awt.Color(204, 204, 204)));

        AddMovie_Title_Label1.setFont(new java.awt.Font("Segoe UI", 0, 23)); // NOI18N
        AddMovie_Title_Label1.setText("Title: ");
        AddMovie_Title_Label1.setPreferredSize(new java.awt.Dimension(103, 31));

        AddMovie_Title_TextField1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        AddMovie_Title_TextField1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(102, 102, 102), new java.awt.Color(153, 153, 153), new java.awt.Color(51, 51, 51), new java.awt.Color(204, 204, 204)));

        AddMovie_Genre_Label.setFont(new java.awt.Font("Segoe UI", 0, 23)); // NOI18N
        AddMovie_Genre_Label.setText("Genre:");
        AddMovie_Genre_Label.setPreferredSize(new java.awt.Dimension(103, 31));

        AddMovie_Genre_TextField.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        AddMovie_Genre_TextField.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(102, 102, 102), new java.awt.Color(153, 153, 153), new java.awt.Color(51, 51, 51), new java.awt.Color(204, 204, 204)));
        AddMovie_Genre_TextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddMovie_Genre_TextFieldActionPerformed(evt);
            }
        });

        AddMovie_Director_Label.setFont(new java.awt.Font("Segoe UI", 0, 23)); // NOI18N
        AddMovie_Director_Label.setText("Director: ");
        AddMovie_Director_Label.setPreferredSize(new java.awt.Dimension(103, 31));

        AddMovie_Director_TextField.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        AddMovie_Director_TextField.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(102, 102, 102), new java.awt.Color(153, 153, 153), new java.awt.Color(51, 51, 51), new java.awt.Color(204, 204, 204)));
        AddMovie_Director_TextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddMovie_Director_TextFieldActionPerformed(evt);
            }
        });

        AddMovie_Duration_Label.setFont(new java.awt.Font("Segoe UI", 0, 23)); // NOI18N
        AddMovie_Duration_Label.setText("Duration:");
        AddMovie_Duration_Label.setPreferredSize(new java.awt.Dimension(103, 31));

        AddMovie_Duration_TextField.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        AddMovie_Duration_TextField.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(102, 102, 102), new java.awt.Color(153, 153, 153), new java.awt.Color(51, 51, 51), new java.awt.Color(204, 204, 204)));

        AddMovie_Price_Label.setFont(new java.awt.Font("Segoe UI", 0, 23)); // NOI18N
        AddMovie_Price_Label.setText("Price:");
        AddMovie_Price_Label.setPreferredSize(new java.awt.Dimension(103, 31));

        AddMovie_Price_TextField.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        AddMovie_Price_TextField.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(102, 102, 102), new java.awt.Color(153, 153, 153), new java.awt.Color(51, 51, 51), new java.awt.Color(204, 204, 204)));

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

        AddMovie_AddAnotherMovie_Button.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        AddMovie_AddAnotherMovie_Button.setText("Add Another Movie");
        AddMovie_AddAnotherMovie_Button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AddMovie_AddAnotherMovie_ButtonMouseClicked(evt);
            }
        });
        AddMovie_AddAnotherMovie_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddMovie_AddAnotherMovie_ButtonActionPerformed(evt);
            }
        });

        AddMovie_Back_Button.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        AddMovie_Back_Button.setText("Back");
        AddMovie_Back_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddMovie_Back_ButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addComponent(AddMovie_AddMovie_Label, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(89, 89, 89)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(AddMovie_Director_Label, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(AddMovie_Title_Label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(AddMovie_MovieID_Label, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(AddMovie_Genre_Label, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(AddMovie_Duration_Label, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(AddMovie_Price_Label, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(AddMovie_Genre_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(AddMovie_Title_TextField1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 309, Short.MAX_VALUE)
                                        .addComponent(AddMovie_MovieID_TextField, javax.swing.GroupLayout.Alignment.TRAILING))))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(65, 65, 65)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(AddMovie_Director_TextField)
                                    .addComponent(AddMovie_Duration_TextField)
                                    .addComponent(AddMovie_Price_TextField))))))
                .addGap(90, 90, 90))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(AddMovie_Back_Button, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(AddMovie_AddAnotherMovie_Button, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(AddMovie_AddMovie_Button, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(AddMovie_AddMovie_Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(AddMovie_MovieID_Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(AddMovie_MovieID_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AddMovie_Title_Label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AddMovie_Title_TextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AddMovie_Genre_Label, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AddMovie_Genre_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AddMovie_Director_Label, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AddMovie_Director_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AddMovie_Duration_Label, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AddMovie_Duration_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AddMovie_Price_Label, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AddMovie_Price_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AddMovie_AddMovie_Button, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AddMovie_AddAnotherMovie_Button, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AddMovie_Back_Button, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(48, 48, 48))
        );

        javax.swing.GroupLayout addMoviesLayout = new javax.swing.GroupLayout(addMovies);
        addMovies.setLayout(addMoviesLayout);
        addMoviesLayout.setHorizontalGroup(
            addMoviesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addMoviesLayout.createSequentialGroup()
                .addGroup(addMoviesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(addMoviesLayout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(addMoviesLayout.createSequentialGroup()
                        .addGap(178, 178, 178)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(103, Short.MAX_VALUE))
        );
        addMoviesLayout.setVerticalGroup(
            addMoviesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addMoviesLayout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addComponent(jLabel8)
                .addGap(34, 34, 34)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(45, Short.MAX_VALUE))
        );

        jPanel3.add(addMovies);
        addMovies.setBounds(162, 0, 957, 652);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/AAG Movie Ticketing System Logo .png"))); // NOI18N
        jPanel3.add(jLabel1);
        jLabel1.setBounds(6, 0, 150, 150);

        jLabel2.setFont(new java.awt.Font("Bookman Old Style", 0, 24)); // NOI18N
        jLabel2.setText("Admin");
        jPanel3.add(jLabel2);
        jLabel2.setBounds(39, 156, 68, 32);

        jPanel1.setBackground(new java.awt.Color(221, 211, 171));
        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(192, 156, 117), null, null));
        jPanel1.setLayout(null);

        addMoviesB.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        addMoviesB.setText("Add Movies");
        addMoviesB.setMargin(new java.awt.Insets(2, 12, 3, 12));
        addMoviesB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addMoviesBActionPerformed(evt);
            }
        });
        jPanel1.add(addMoviesB);
        addMoviesB.setBounds(10, 260, 130, 27);

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

        jPanel3.add(jPanel1);
        jPanel1.setBounds(6, 224, 150, 310);

        movies.setBackground(new java.awt.Color(255, 255, 255));
        movies.setMinimumSize(new java.awt.Dimension(932, 652));
        movies.setPreferredSize(new java.awt.Dimension(939, 652));
        movies.setLayout(null);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 3, 36)); // NOI18N
        jLabel7.setText("Movies");
        movies.add(jLabel7);
        jLabel7.setBounds(34, 39, 117, 48);

        jTable2.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Movie ID", "Movie Title"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane3.setViewportView(jTable2);

        movies.add(jScrollPane3);
        jScrollPane3.setBounds(45, 132, 847, 365);

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel13.setText("Search: ");
        movies.add(jLabel13);
        jLabel13.setBounds(210, 510, 70, 25);

        jTextField4.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });
        movies.add(jTextField4);
        jTextField4.setBounds(297, 506, 365, 23);

        jButton3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton3.setText("Search");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        movies.add(jButton3);
        jButton3.setBounds(391, 542, 150, 41);

        jPanel3.add(movies);
        movies.setBounds(162, 0, 939, 652);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setSize(new java.awt.Dimension(1117, 660));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void addMoviesBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addMoviesBActionPerformed
        sales.setVisible(false);
        logs.setVisible(false);
        employee.setVisible(false);
        addEmplyee.setVisible(false);
        movies.setVisible(false);
        addMovies.setVisible(true);
        AddMovie_MovieID_TextField.setText(newmovieid);
    }//GEN-LAST:event_addMoviesBActionPerformed

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
    }//GEN-LAST:event_moviesBActionPerformed

    private void logsBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logsBActionPerformed
        sales.setVisible(false);
        logs.setVisible(true);
        employee.setVisible(false);
        addEmplyee.setVisible(false);
        movies.setVisible(false);
        addMovies.setVisible(false);
    }//GEN-LAST:event_logsBActionPerformed

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

    private void Add_Another_StaffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Add_Another_StaffActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Add_Another_StaffActionPerformed

    private void BackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BackActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BackActionPerformed

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void AddMovie_AddMovie_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddMovie_AddMovie_ButtonActionPerformed
        // Ang kulang nalang dito is yung pic_poster_location
        // Tapos may error na lumalabas kapag mali yung input sa price (Ex kapag string yung nailagay mag eerror)

        MovieID = AddMovie_MovieID_TextField.getText();
        Title = AddMovie_Title_TextField1.getText();
        Genre = AddMovie_Genre_TextField.getText();
        Director = AddMovie_Director_TextField.getText();
        Duration = AddMovie_Duration_TextField.getText();
        price = Integer.parseInt(AddMovie_Price_TextField.getText());
        Movie_Pic_Location = "null";
        Mpass = "Admin";

        adminpass = JOptionPane.showInputDialog("Enter Admin PassWord: ");
        if (adminpass != null && adminpass.matches(Mpass)) {
            System.out.println("Data Added: ");
            System.out.println(MovieID);
            System.out.println(Title);
            System.out.println(Genre);
            //System.out.println(AddStaff_Email_TextField.getText()); // eto reference ng null sa addStaff
            System.out.println(Director);
            System.out.println(AddMovie_Duration_TextField.getText());
            System.out.println(price);
            System.out.println(Movie_Pic_Location);
            System.out.println(PNum);

            try {  // INSERTING VALUES FOR ADDMOVIE

                Statement stmt = conn.createStatement();
                String qry = "insert into Movie (MovieID, Title, Genre, Director, Duration, Price, Movie_pic_loc)"
                        + "values('" + MovieID + "','" + Title + "','" + Genre + "','" + Director + "','" + Duration + "','"
                        + price + "','" + Movie_Pic_Location + "')";
                int rows = stmt.executeUpdate(qry);
                if (rows > 0) {
                    System.out.println("Insert Successful");
                }
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Confirmation Cancelled");
        }


    }//GEN-LAST:event_AddMovie_AddMovie_ButtonActionPerformed

    private void AddMovie_AddAnotherMovie_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddMovie_AddAnotherMovie_ButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AddMovie_AddAnotherMovie_ButtonActionPerformed

    private void AddMovie_Back_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddMovie_Back_ButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AddMovie_Back_ButtonActionPerformed

    private void AddMovie_Genre_TextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddMovie_Genre_TextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AddMovie_Genre_TextFieldActionPerformed

    private void AddMovie_Director_TextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddMovie_Director_TextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AddMovie_Director_TextFieldActionPerformed

    private void AddMovie_AddMovie_ButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AddMovie_AddMovie_ButtonMouseClicked
        // TODO add your handling code here:

        MovieID = AddMovie_MovieID_TextField.getText();
        Title = AddMovie_Title_TextField1.getText();
        Genre = AddMovie_Genre_TextField.getText();
        Director = AddMovie_Director_TextField.getText();
        Duration = AddMovie_Duration_TextField.getText();
        price = Integer.parseInt(AddMovie_Price_TextField.getText());

        for (int i = 0; i < 4; i++) {
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
                System.out.println("Add Movie Details: ");
                System.out.println(MovieID);
                System.out.println(Title);
                System.out.println(Genre);
                System.out.println(Director);
                System.out.println(Duration);
                System.out.println(price);
            }

        }
    }//GEN-LAST:event_AddMovie_AddMovie_ButtonMouseClicked

    private void AddMovie_AddAnotherMovie_ButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AddMovie_AddAnotherMovie_ButtonMouseClicked
        // TODO add your handling code here: 

    }//GEN-LAST:event_AddMovie_AddAnotherMovie_ButtonMouseClicked

    private void Add_Another_StaffMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Add_Another_StaffMouseClicked
        newempid = "E" + (lnum + 1);

        AddStaff_EmployeeID_TextField.setText(" ");
        AddStaff_EmployeeID_TextField.setText(newempid);
        AddStaff_FirstName_TextField.setText(" ");
        AddStaff_LastName_TextField.setText(" ");
        AddStaff_Email_TextField.setText(" ");
        AddStaff_PhoneNumber_TextField1.setText(" ");

    }//GEN-LAST:event_Add_Another_StaffMouseClicked

    private void salesB1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salesB1ActionPerformed
        sales.setVisible(true);
        logs.setVisible(false);
        employee.setVisible(false);
        addEmplyee.setVisible(false);
        movies.setVisible(false);
        addMovies.setVisible(false);
    }//GEN-LAST:event_salesB1ActionPerformed

    /**
     * @param args
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static void main(String args[]) {
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Admin().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    void getIDs() throws SQLException {
        get_last_empid();
        get_last_movieid();
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
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddMovie_AddAnotherMovie_Button;
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
    private javax.swing.JPanel addEmplyee;
    private javax.swing.JPanel addMovies;
    private javax.swing.JButton addMoviesB;
    private javax.swing.JButton addStaffsB;
    private javax.swing.JTable empTable;
    private javax.swing.JPanel employee;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
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
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
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
