/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.*;
import javax.swing.*;

public class formLogin extends JFrame{
    private String username;
    
    private JDialog jdialogLogin;
    private JLabel jLableTitle;
    private JLabel jLableUsername;
    private JLabel jLablePassword;
    private JTextField jTextUsername;
    private JPasswordField jtextPass;
    private JButton JButtonlogin;
    
    public formLogin() {
        JPanel jpan = new JPanel();
        jpan.setLayout(null);
        jpan.setPreferredSize(new Dimension(500, 500));
        
        jdialogLogin = new JDialog(this, "Login chat app");
        jdialogLogin.setSize(500, 500);
        jdialogLogin.setLocationRelativeTo(null);
        jdialogLogin.setModal(true);
        jdialogLogin.setLayout(new FlowLayout());
        jdialogLogin.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        jdialogLogin.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                System.exit(0);
            }
        });
        this.jLableTitle = new JLabel("Login chat app");
        this.jLableTitle.setBounds(210, 60, 200, 30);
        
        this.jLableUsername = new JLabel("Username");
        this.jLableUsername.setBounds(70, 120, 60, 30);
        this.jTextUsername = new JTextField();
        this.jTextUsername.setBounds(140, 120, 250, 30);
        
        this.jLablePassword = new JLabel("Password");
        this.jLablePassword.setBounds(70, 190, 60, 30);
        this.jtextPass = new JPasswordField();
        this.jtextPass.setBounds(140, 190, 250, 30);
        
        this.JButtonlogin = new JButton();
        this.JButtonlogin.setText("Login");
        this.JButtonlogin.setBounds(180, 330, 140, 40);
        this.JButtonlogin.setActionCommand("JButtonlogin");
        this.JButtonlogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginApp();
            }
        });
        
        jpan.add(this.JButtonlogin);
        jpan.add(this.jTextUsername);
        jpan.add(this.jtextPass);
        jpan.add(this.jLableUsername);
        jpan.add(this.jLablePassword);
        jpan.add(this.jLableTitle);
        
        jdialogLogin.add(jpan);
        jdialogLogin.setVisible(true);
    }

    public JButton getButtonLogin() {
        return JButtonlogin;
    }

    public void loginApp() {
        this.username = this.jTextUsername.getText();
        String password = new String(this.jtextPass.getPassword());
        
        if (username == "" || password == "") {
            JOptionPane.showMessageDialog(this, "login fail, User account or password incorrect!!!", "WARNING", JOptionPane.WARNING_MESSAGE);
        } else {

            JOptionPane.showMessageDialog(null, "Login sucess!");
            this.jdialogLogin.setModal(true);
            this.jdialogLogin.setVisible(false);

        }
    }

    public String getUsername() {
        return username;
    }
}
