/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Staffs;

import static Staffs.Payment_Method.*;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.RoundRectangle2D;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author MIGUEL
 */
public class Payment_Cash extends javax.swing.JFrame {

    /**
     * Creates new form Payment_Cash
     */
    Payment_Method pm = new Payment_Method();
    public Payment_Cash() throws SQLException, ParseException{
        initComponents();
        setLocationRelativeTo(null);
        pm.setVisible(true);
        this.setVisible(true);
        
        this.setShape(new RoundRectangle2D.Double(0, 0, (300), 
        (200), 25, 25));
        
        right_panel_bg();
        pay_cash();
    }

    void right_panel_bg(){

        ImageIcon rpbg = new ImageIcon("rpbg.png");
        Image image = rpbg.getImage(); // transform it 
        Image newimg = image.getScaledInstance(300, 200,  java.awt.Image.SCALE_DEFAULT); // scale it the smooth way  
        rpbg = new ImageIcon(newimg);
        rp_bg.setIcon(rpbg);
                
    }
    
    public void pay_cash() throws SQLException, ParseException{
        JLabel cash_label = new JLabel("Cash Amount");
        main_panel.add(cash_label);
        cash_label.setBounds(60,50, 95, 50);
        
        JTextField cash_amount = new JTextField(20);
        main_panel.add(cash_amount);
        cash_amount.setBounds(145,60, 100, 25);
        
        JButton okBtn = new JButton("OK");
        main_panel.add(okBtn);
        okBtn.setBounds(165,110, 80, 25);
        
        JButton cancelBtn = new JButton("CANCEL");
        main_panel.add(cancelBtn);
        cancelBtn.setBounds(60,110, 80, 25);
        
        double price = pm.totalp;
        String payment_m = "Cash";
        okBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String pera = cash_amount.getText();
                int money = Integer.parseInt(pera);
                if (okBtn.getActionCommand().equals("OK") && !cash_amount.getText().equals("") && money >= price) {
                    cash_amount.getText();
                    double sukli = money - price;
                    JOptionPane.showMessageDialog(null, "Successfull, your change is " + sukli, "Payment Success", JOptionPane.INFORMATION_MESSAGE);
                    setVisible(false);
                    if (!cash_amount.getText().equals("") && money >= price) {
                        try {
                            Temp_Data td = new Temp_Data();
                            td.jp_mlist.removeAll();
                            td.jp_mlist.revalidate();
                            td.jp_mlist.repaint();
                            td.stopper =0;
                            td.total_amount =0;
                            //Payment_Method pm = new Payment_Method();
                            pm.update_seat_list();
                            pm.insert_whole_payment(payment_m);
                            pm.print_receipt_to_pdf();
                            new Movie_List().setVisible(true);
                            pm.dispose();
                            dispose();
                            //pm.update_ticket_on_database();
                        } catch (SQLException ex) {
                            Logger.getLogger(BDO.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (ParseException ex) {
                            Logger.getLogger(BDO.class.getName()).log(Level.SEVERE, null, ex);
                        }
//                        System.out.println(Payment_Method.payment_m);
//                        System.out.println("ito yung total_amount: " + price);
//                        System.out.println(Payment_Method.emp_log);
                    }
                }else if (money < price) {
                    JOptionPane.showMessageDialog(null, "Insufficient amount, please enter valid amount", "Payment Unsuccessful", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        
        cancelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if(cancelBtn.getActionCommand().equals("CANCEL")) {
                    setVisible(false);
                }
            }
        });
        
        cash_amount.addKeyListener(new KeyListener(){
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE)) {
                    e.consume();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public void keyReleased(KeyEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }
        });
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        main_panel = new javax.swing.JPanel();
        rp_bg = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        javax.swing.GroupLayout main_panelLayout = new javax.swing.GroupLayout(main_panel);
        main_panel.setLayout(main_panelLayout);
        main_panelLayout.setHorizontalGroup(
            main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(rp_bg, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );
        main_panelLayout.setVerticalGroup(
            main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(rp_bg, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(main_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(main_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel main_panel;
    private javax.swing.JLabel rp_bg;
    // End of variables declaration//GEN-END:variables
}
