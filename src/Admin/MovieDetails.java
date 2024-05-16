/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Admin;

/**
 *
 * @author Romel
 */
public class MovieDetails extends javax.swing.JFrame {

    public MovieDetails() {
        initComponents();

    }

    public void getMoviedets(String MovieID, String Title, String Genre, String Director, String Duration,
            String Price, String PosterLoc) {

        MovieDetails_MovieIDTx.setText(MovieID);
        MovieDetails_TitleTx.setText(Title);
        MovieDetails_GenreTx.setText(Genre);
        MovieDetails_DirectorTx.setText(Director);
        MovieDetails_DurationTx.setText(Duration);
        MovieDetails_PriceTx.setText(Price);
        

        System.out.println(MovieID);
        System.out.println(Title);
        System.out.println(Genre);
        System.out.println(Director);
        System.out.println(Duration);
        System.out.println(Price);
        System.out.println(PosterLoc);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
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
        MovieDetails_Poster = new javax.swing.JLabel();
        MovieDetails_PosterTx = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        MovieDetails_PosterPic = new javax.swing.JLabel();

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

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

        MovieDetails_TitleTx.setEditable(false);
        jPanel1.add(MovieDetails_TitleTx, new org.netbeans.lib.awtextra.AbsoluteConstraints(135, 81, 286, -1));

        MovieDetails_Genre.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        MovieDetails_Genre.setText("Genre: ");
        jPanel1.add(MovieDetails_Genre, new org.netbeans.lib.awtextra.AbsoluteConstraints(42, 115, 87, -1));

        MovieDetails_GenreTx.setEditable(false);
        jPanel1.add(MovieDetails_GenreTx, new org.netbeans.lib.awtextra.AbsoluteConstraints(135, 115, 286, -1));

        MovieDetails_Director.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        MovieDetails_Director.setText("Director:");
        jPanel1.add(MovieDetails_Director, new org.netbeans.lib.awtextra.AbsoluteConstraints(42, 149, 87, -1));

        MovieDetails_DirectorTx.setEditable(false);
        jPanel1.add(MovieDetails_DirectorTx, new org.netbeans.lib.awtextra.AbsoluteConstraints(135, 149, 286, -1));

        MovieDetails_Duration.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        MovieDetails_Duration.setText("Duration:");
        jPanel1.add(MovieDetails_Duration, new org.netbeans.lib.awtextra.AbsoluteConstraints(42, 183, 87, -1));

        MovieDetails_DurationTx.setEditable(false);
        jPanel1.add(MovieDetails_DurationTx, new org.netbeans.lib.awtextra.AbsoluteConstraints(135, 183, 286, -1));

        MovieDetails_Price.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        MovieDetails_Price.setText("Price:");
        jPanel1.add(MovieDetails_Price, new org.netbeans.lib.awtextra.AbsoluteConstraints(42, 217, 87, -1));

        MovieDetails_PriceTx.setEditable(false);
        jPanel1.add(MovieDetails_PriceTx, new org.netbeans.lib.awtextra.AbsoluteConstraints(135, 217, 286, -1));

        MovieDetails_Poster.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        MovieDetails_Poster.setText("Poster_Loc:");
        jPanel1.add(MovieDetails_Poster, new org.netbeans.lib.awtextra.AbsoluteConstraints(42, 251, 87, -1));

        MovieDetails_PosterTx.setEditable(false);
        jPanel1.add(MovieDetails_PosterTx, new org.netbeans.lib.awtextra.AbsoluteConstraints(135, 251, 286, -1));

        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton1.setText("Add Showtime");
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(435, 304, 158, 36));

        MovieDetails_PosterPic.setText("jLabel2");
        jPanel1.add(MovieDetails_PosterPic, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 50, 160, 220));

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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel MovieDetails_Director;
    private javax.swing.JTextField MovieDetails_DirectorTx;
    private javax.swing.JLabel MovieDetails_Duration;
    private javax.swing.JTextField MovieDetails_DurationTx;
    private javax.swing.JLabel MovieDetails_Genre;
    private javax.swing.JTextField MovieDetails_GenreTx;
    private javax.swing.JLabel MovieDetails_MovieID;
    private javax.swing.JTextField MovieDetails_MovieIDTx;
    private javax.swing.JLabel MovieDetails_Poster;
    private javax.swing.JLabel MovieDetails_PosterPic;
    private javax.swing.JTextField MovieDetails_PosterTx;
    private javax.swing.JLabel MovieDetails_Price;
    private javax.swing.JTextField MovieDetails_PriceTx;
    private javax.swing.JLabel MovieDetails_Title;
    private javax.swing.JTextField MovieDetails_TitleTx;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}
