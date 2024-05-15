/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Admin;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

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

    int showtimeTime;

    public Statement stmt;
    public static Connection conn;
    ResultSet rs;
    ArrayList<Object[]> vec = new ArrayList<>();

    public static String ShowtimeMovieID;

    DefaultTableModel tmodel = new DefaultTableModel();
    DefaultTableModel tmodel1 = new DefaultTableModel();

    File file = null;

    public Admin() throws SQLException, ClassNotFoundException {
        connectToDatabase();
        initComponents();

        sales.setVisible(true);
        employee.setVisible(false);
        addEmplyee.setVisible(false);
        movies.setVisible(false);
        addMovies.setVisible(false);
        setTitle("Admin");

        createTableSales();
        getIDs();
        //createTableMovies();

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
    // Commit and Push

    @Override
    public int getX() {
        return super.getX(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }
    //G, kapag ayaw padin, chat mo l;ang ako, sige sige

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
        Back = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
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
        AddMovie_Back_Button = new javax.swing.JButton();
        MoviePoster = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        PosterName = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
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
        MovieTable = new javax.swing.JTable();
        jLabel13 = new javax.swing.JLabel();
        MovieSearchField = new javax.swing.JTextField();
        Select_Button_Movies = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel3.setBackground(new java.awt.Color(211, 191, 114));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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

        jPanel3.add(sales, new org.netbeans.lib.awtextra.AbsoluteConstraints(162, 0, 939, -1));

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

        jPanel3.add(logs, new org.netbeans.lib.awtextra.AbsoluteConstraints(162, 0, 939, 652));

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

        jPanel3.add(employee, new org.netbeans.lib.awtextra.AbsoluteConstraints(162, 0, -1, -1));

        addEmplyee.setBackground(new java.awt.Color(255, 255, 255));
        addEmplyee.setPreferredSize(new java.awt.Dimension(957, 652));
        addEmplyee.setLayout(null);

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
        AddStaff_PhoneNumber_TextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                AddStaff_PhoneNumber_TextField1KeyTyped(evt);
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

        Back.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        Back.setText("Back");
        Back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BackActionPerformed(evt);
            }
        });

        jLabel12.setBackground(new java.awt.Color(204, 204, 204));
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/bg_addmovie.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(AddStaff_StaffDetailsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(AddStaff_EmployeeIDLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(94, 94, 94)
                .addComponent(AddStaff_EmployeeID_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(AddStaff_FirstNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(111, 111, 111)
                .addComponent(AddStaff_FirstName_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(AddStaff_LastNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(108, 108, 108)
                .addComponent(AddStaff_LastName_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(AddStaff_EmailLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(149, 149, 149)
                .addComponent(AddStaff_Email_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(AddStaff_PhoneNumberLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(70, 70, 70)
                .addComponent(AddStaff_PhoneNumber_TextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(359, 359, 359)
                .addComponent(Back, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Add_Staff, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 670, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(AddStaff_StaffDetailsLabel)
                .addGap(56, 56, 56)
                .addComponent(AddStaff_EmployeeID_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(AddStaff_FirstNameLabel)
                    .addComponent(AddStaff_FirstName_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(AddStaff_LastNameLabel)
                    .addComponent(AddStaff_LastName_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(AddStaff_EmailLabel)
                    .addComponent(AddStaff_Email_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(AddStaff_PhoneNumberLabel)
                    .addComponent(AddStaff_PhoneNumber_TextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(71, 71, 71)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Back)
                    .addComponent(Add_Staff)))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(107, 107, 107)
                .addComponent(AddStaff_EmployeeIDLabel))
            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        addEmplyee.add(jPanel2);
        jPanel2.setBounds(140, 120, 674, 424);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 3, 36)); // NOI18N
        jLabel6.setText("Add Staffs");
        addEmplyee.add(jLabel6);
        jLabel6.setBounds(50, 30, 190, 48);

        jPanel3.add(addEmplyee, new org.netbeans.lib.awtextra.AbsoluteConstraints(162, 0, 957, 652));

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

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/bg_addmovie.png"))); // NOI18N
        jPanel4.add(jLabel11);
        jLabel11.setBounds(0, 0, 670, 420);

        javax.swing.GroupLayout addMoviesLayout = new javax.swing.GroupLayout(addMovies);
        addMovies.setLayout(addMoviesLayout);
        addMoviesLayout.setHorizontalGroup(
            addMoviesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addMoviesLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, addMoviesLayout.createSequentialGroup()
                .addContainerGap(144, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(137, 137, 137))
        );
        addMoviesLayout.setVerticalGroup(
            addMoviesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addMoviesLayout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(jLabel8)
                .addGap(53, 53, 53)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(83, Short.MAX_VALUE))
        );

        jPanel3.add(addMovies, new org.netbeans.lib.awtextra.AbsoluteConstraints(162, 0, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/AAG Movie Ticketing System Logo .png"))); // NOI18N
        jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 0, -1, -1));

        jLabel2.setFont(new java.awt.Font("Bookman Old Style", 0, 24)); // NOI18N
        jLabel2.setText("Admin");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(39, 156, -1, -1));

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

        jPanel3.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 224, 150, 300));

        movies.setBackground(new java.awt.Color(255, 255, 255));
        movies.setMinimumSize(new java.awt.Dimension(932, 652));
        movies.setPreferredSize(new java.awt.Dimension(939, 652));
        movies.setLayout(null);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 3, 36)); // NOI18N
        jLabel7.setText("Movies");
        movies.add(jLabel7);
        jLabel7.setBounds(34, 39, 117, 48);

        MovieTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
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

        jPanel3.add(movies, new org.netbeans.lib.awtextra.AbsoluteConstraints(162, 0, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 1104, Short.MAX_VALUE)
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
        createTableMovie();
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

    }//GEN-LAST:event_Add_StaffMouseClicked

    private void Add_StaffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Add_StaffActionPerformed

        pass = "E00" + lnum;

        for (int i = 0; i < 3; i++) {
            EmpId = AddStaff_EmployeeID_TextField.getText();
            FName = AddStaff_FirstName_TextField.getText();
            LName = AddStaff_LastName_TextField.getText();
            PNum = AddStaff_PhoneNumber_TextField1.getText();
            Email = AddStaff_Email_TextField.getText();
            Mpass = "Admin";

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
                System.out.println("Hello");
                i--;
                break;
            } else {
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
                        Statement stmt1 = conn.createStatement();
                        stmt1 = conn.createStatement();
                        String qry = "insert into Staff (EmployeeID,fname, lname, email, phone,username,passw)"
                                + "values('" + EmpId + "','" + FName + "','" + LName + "','" + Email + "','" + PNum + "','"
                                + EmpId + "','" + UserPass + "')";
                        int rows = stmt.executeUpdate(qry);
                        if (rows > 0) {
                            System.out.println("Insert Successful");
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Wrong Input");
                }
                ClearFieldStaff();
                break;
            }

        }

    }//GEN-LAST:event_Add_StaffActionPerformed

    private void BackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BackActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BackActionPerformed

    private void MovieSearchFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MovieSearchFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MovieSearchFieldActionPerformed

    private void Select_Button_MoviesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Select_Button_MoviesActionPerformed
        try {
            selectMovie();

        } catch (SQLException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_Select_Button_MoviesActionPerformed

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
            Movie_Pic_Location = file.toString().substring(file.toString().lastIndexOf("\\") + 1);
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

    private void AddMovie_Genre_TextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddMovie_Genre_TextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AddMovie_Genre_TextFieldActionPerformed

    private void AddMovie_Director_TextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddMovie_Director_TextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AddMovie_Director_TextFieldActionPerformed

    private void AddMovie_AddMovie_ButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AddMovie_AddMovie_ButtonMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_AddMovie_AddMovie_ButtonMouseClicked

    private void salesB1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salesB1ActionPerformed
        sales.setVisible(true);
        logs.setVisible(false);
        employee.setVisible(false);
        addEmplyee.setVisible(false);
        movies.setVisible(false);
        addMovies.setVisible(false);
    }//GEN-LAST:event_salesB1ActionPerformed

    private void AddStaff_PhoneNumber_TextField1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AddStaff_PhoneNumber_TextField1KeyTyped
        // TODO add your handling code here:
        // for number input only in phonenumber textfield
        char c = evt.getKeyChar();
        if (!Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE)) {
            evt.consume();
        }
    }//GEN-LAST:event_AddStaff_PhoneNumber_TextField1KeyTyped

    private void AddMovie_Price_TextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AddMovie_Price_TextFieldKeyTyped
        // TODO add your handling code here:
        // for number input only in price textfield
        char c = evt.getKeyChar();
        if (!Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE)) {
            evt.consume();
        }
    }//GEN-LAST:event_AddMovie_Price_TextFieldKeyTyped

    private void MovieSearchFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MovieSearchFieldKeyReleased
        // TODO add your handling code here:
        DefaultTableModel SearchTable = (DefaultTableModel) MovieTable.getModel();
        TableRowSorter<DefaultTableModel> MovieSearch = new TableRowSorter<>(SearchTable);
        MovieTable.setRowSorter(MovieSearch);
        MovieSearch.setRowFilter(RowFilter.regexFilter(MovieSearchField.getText()));
    }//GEN-LAST:event_MovieSearchFieldKeyReleased

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        String fildest = System.getProperty("user.dir");

        int res = chooser.showOpenDialog(Admin.this);
        if (res == JFileChooser.APPROVE_OPTION) {
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
                        Thread.sleep(5);
                    }

                } catch (IOException | InterruptedException e) {
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

    //For Creating Table Sales
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

    //For Getting data for table
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
        }
    }


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
    private javax.swing.JButton Add_Staff;
    private javax.swing.JButton Back;
    private javax.swing.JLabel MoviePoster;
    private javax.swing.JTextField MovieSearchField;
    private javax.swing.JTable MovieTable;
    private javax.swing.JTextField PosterName;
    private javax.swing.JButton Select_Button_Movies;
    private javax.swing.JPanel addEmplyee;
    private javax.swing.JPanel addMovies;
    private javax.swing.JButton addMoviesB;
    private javax.swing.JButton addStaffsB;
    private javax.swing.JTable empTable;
    private javax.swing.JPanel employee;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
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
