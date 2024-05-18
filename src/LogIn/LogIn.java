/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package LogIn;

import Staffs.Movie_List;
import java.awt.Color;
import admin.Admin;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import main.Main;

/**
 *
 * @author cdgan
 */
public class LogIn extends javax.swing.JFrame {

    /**
     * Creates new form LogIn
     */
    LogInProcess process;

    public LogIn() throws SQLException, ClassNotFoundException {
        initComponents();
        process = new LogInProcess();
        SignUp.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        BackGround = new javax.swing.JPanel();
        LogIn = new javax.swing.JPanel();
        Close = new javax.swing.JLabel();
        logIn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lUserIn = new javax.swing.JTextField();
        lPassIn = new javax.swing.JPasswordField();
        showPass = new javax.swing.JCheckBox();
        createAcc = new javax.swing.JLabel();
        SignUp = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        Close1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        sPassIn = new javax.swing.JPasswordField();
        sUserIn = new javax.swing.JTextField();
        sRPassIn = new javax.swing.JPasswordField();
        jLabel7 = new javax.swing.JLabel();
        showPass2 = new javax.swing.JCheckBox();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        BackGround.setBackground(new java.awt.Color(255, 204, 102));

        LogIn.setBackground(new java.awt.Color(255, 255, 255));
        LogIn.setPreferredSize(new java.awt.Dimension(502, 392));

        Close.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/icons8-x-20.png"))); // NOI18N
        Close.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CloseMouseClicked(evt);
            }
        });

        logIn.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        logIn.setText("Log In");
        logIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logInActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Bookman Old Style", 0, 36)); // NOI18N
        jLabel1.setText("Log In");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 22)); // NOI18N
        jLabel2.setText("User:");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 22)); // NOI18N
        jLabel3.setText("Password:");

        lUserIn.setFont(new java.awt.Font("Segoe UI", 0, 21)); // NOI18N
        lUserIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lUserInActionPerformed(evt);
            }
        });

        lPassIn.setFont(new java.awt.Font("Segoe UI", 0, 21)); // NOI18N

        showPass.setText("Show Password");
        showPass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showPassActionPerformed(evt);
            }
        });

        createAcc.setText("Don't have account? Create one");
        createAcc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                createAccMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                createAccMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                createAccMouseExited(evt);
            }
        });

        javax.swing.GroupLayout LogInLayout = new javax.swing.GroupLayout(LogIn);
        LogIn.setLayout(LogInLayout);
        LogInLayout.setHorizontalGroup(
            LogInLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, LogInLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(logIn)
                .addGap(200, 200, 200))
            .addGroup(LogInLayout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addGroup(LogInLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(LogInLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(showPass)
                    .addGroup(LogInLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(lUserIn)
                        .addComponent(lPassIn, javax.swing.GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, LogInLayout.createSequentialGroup()
                .addContainerGap(172, Short.MAX_VALUE)
                .addGroup(LogInLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, LogInLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(174, 174, 174))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, LogInLayout.createSequentialGroup()
                        .addComponent(createAcc)
                        .addGap(162, 162, 162))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, LogInLayout.createSequentialGroup()
                        .addComponent(Close)
                        .addContainerGap())))
        );
        LogInLayout.setVerticalGroup(
            LogInLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, LogInLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Close)
                .addGap(5, 5, 5)
                .addComponent(jLabel1)
                .addGap(53, 53, 53)
                .addGroup(LogInLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lUserIn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(LogInLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(lPassIn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(showPass)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 69, Short.MAX_VALUE)
                .addComponent(logIn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(createAcc)
                .addGap(15, 15, 15))
        );

        SignUp.setBackground(new java.awt.Color(255, 255, 255));
        SignUp.setEnabled(false);
        SignUp.setPreferredSize(new java.awt.Dimension(502, 392));

        jButton2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton2.setText("Sign Up");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        Close1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/icons8-x-20.png"))); // NOI18N
        Close1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Close1MouseClicked(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Bookman Old Style", 0, 36)); // NOI18N
        jLabel4.setText("Sign Up");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 22)); // NOI18N
        jLabel5.setText("User:");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 22)); // NOI18N
        jLabel6.setText("Password:");

        sPassIn.setFont(new java.awt.Font("Segoe UI", 0, 21)); // NOI18N
        sPassIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sPassInActionPerformed(evt);
            }
        });

        sUserIn.setFont(new java.awt.Font("Segoe UI", 0, 21)); // NOI18N
        sUserIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sUserInActionPerformed(evt);
            }
        });

        sRPassIn.setFont(new java.awt.Font("Segoe UI", 0, 21)); // NOI18N
        sRPassIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sRPassInActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 22)); // NOI18N
        jLabel7.setText("Re-type pass:");

        showPass2.setText("Show Password");
        showPass2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showPass2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout SignUpLayout = new javax.swing.GroupLayout(SignUp);
        SignUp.setLayout(SignUpLayout);
        SignUpLayout.setHorizontalGroup(
            SignUpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SignUpLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(SignUpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(SignUpLayout.createSequentialGroup()
                        .addGroup(SignUpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(SignUpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(sUserIn)
                            .addComponent(sPassIn, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(SignUpLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(SignUpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(showPass2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(sRPassIn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 44, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SignUpLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(SignUpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SignUpLayout.createSequentialGroup()
                        .addComponent(Close1)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SignUpLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(174, 174, 174))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SignUpLayout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addGap(197, 197, 197))))
        );
        SignUpLayout.setVerticalGroup(
            SignUpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SignUpLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Close1)
                .addGap(7, 7, 7)
                .addComponent(jLabel4)
                .addGap(36, 36, 36)
                .addGroup(SignUpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(sUserIn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(SignUpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(sPassIn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(SignUpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sRPassIn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(showPass2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addGap(35, 35, 35))
        );

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/AAG Movie Ticketing System Logo .png"))); // NOI18N

        javax.swing.GroupLayout BackGroundLayout = new javax.swing.GroupLayout(BackGround);
        BackGround.setLayout(BackGroundLayout);
        BackGroundLayout.setHorizontalGroup(
            BackGroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BackGroundLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(LogIn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(BackGroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BackGroundLayout.createSequentialGroup()
                    .addGap(0, 163, Short.MAX_VALUE)
                    .addComponent(SignUp, javax.swing.GroupLayout.PREFERRED_SIZE, 501, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        BackGroundLayout.setVerticalGroup(
            BackGroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(LogIn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(BackGroundLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel8)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(BackGroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(SignUp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(BackGround, javax.swing.GroupLayout.PREFERRED_SIZE, 662, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(BackGround, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setSize(new java.awt.Dimension(662, 392));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void logInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logInActionPerformed

        try {
            int flag = this.process.checkAcc(lUserIn.getText(), lPassIn.getText());
            System.out.println(lUserIn.getText());
            System.out.println(lPassIn.getText());
            switch (flag) {
                case 1:
                    Main.choose = 1;
                    break;
                case 2:
                    Main.choose = 2;
                    System.out.println(Main.choose);
                    Main.empid = lUserIn.getText();
                    break;
                default:
                    lUserIn.setText("");
                    lPassIn.setText("");
                    break;
            }
            if (flag == 1 || flag == 2) {
                Main.main(null);
                dispose();
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(LogIn.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(LogIn.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_logInActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        SignUp.setVisible(false);
        LogIn.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void Close1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Close1MouseClicked
        System.exit(0);
    }//GEN-LAST:event_Close1MouseClicked

    private void CloseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CloseMouseClicked
        System.exit(0);
    }//GEN-LAST:event_CloseMouseClicked

    private void lUserInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lUserInActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lUserInActionPerformed

    private void showPassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showPassActionPerformed
        int ctr;
        if (showPass.isSelected()) {
            char c = 0;
            lPassIn.setEchoChar(c);
            ctr = 1;
        } else {
            char c = '*';
            lPassIn.setEchoChar(c);
            ctr = 0;
        }
    }//GEN-LAST:event_showPassActionPerformed

    private void createAccMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_createAccMouseEntered
        createAcc.setForeground(Color.BLUE);
    }//GEN-LAST:event_createAccMouseEntered

    private void createAccMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_createAccMouseExited
        createAcc.setForeground(Color.BLACK);
    }//GEN-LAST:event_createAccMouseExited

    private void sUserInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sUserInActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sUserInActionPerformed

    private void showPass2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showPass2ActionPerformed
        int ctr;
        if (showPass2.isSelected()) {
            char c = 0;
            sPassIn.setEchoChar(c);
            sRPassIn.setEchoChar(c);
            ctr = 1;
        } else {
            char c = '*';
            sPassIn.setEchoChar(c);
            sRPassIn.setEchoChar(c);
            ctr = 0;
        }
    }//GEN-LAST:event_showPass2ActionPerformed

    private void createAccMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_createAccMouseClicked
        LogIn.setVisible(false);
        SignUp.setVisible(true);
    }//GEN-LAST:event_createAccMouseClicked

    private void sRPassInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sRPassInActionPerformed
        logIn.doClick();
    }//GEN-LAST:event_sRPassInActionPerformed

    private void sPassInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sPassInActionPerformed
        logIn.doClick();
    }//GEN-LAST:event_sPassInActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LogIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LogIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LogIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LogIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new LogIn().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(LogIn.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(LogIn.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel BackGround;
    private javax.swing.JLabel Close;
    private javax.swing.JLabel Close1;
    private javax.swing.JPanel LogIn;
    private javax.swing.JPanel SignUp;
    private javax.swing.JLabel createAcc;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    public javax.swing.JPasswordField lPassIn;
    public javax.swing.JTextField lUserIn;
    private javax.swing.JButton logIn;
    private javax.swing.JPasswordField sPassIn;
    private javax.swing.JPasswordField sRPassIn;
    private javax.swing.JTextField sUserIn;
    private javax.swing.JCheckBox showPass;
    private javax.swing.JCheckBox showPass2;
    // End of variables declaration//GEN-END:variables
}
