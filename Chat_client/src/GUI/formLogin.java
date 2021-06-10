/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.*;
import java.io.*;
import javax.swing.*;

public class formLogin extends JFrame{
    private String username;
    private PrintWriter writer;
    
    //private JDialog jdialogLogin;
    private JLabel jLableTitle;
    private JLabel jLableUsername;
    private JLabel jLablePassword;
    private JTextField jTextUsername;
    private JPasswordField jtextPass;
    private JButton JButtonlogin;
    
    public formLogin() {
        Myinit();
        this.setVisible(true);
    }
    private void Myinit(){
        this.setSize(500, 500);
        this.setTitle("Login chat app");
        this.setLocationRelativeTo(null);
        this.setLayout(new FlowLayout());
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                writer.println("CLOSELOGINVIEW");
                System.exit(0);
            }
        });
        
        JPanel jpan = new JPanel();
        jpan.setLayout(null);
        jpan.setPreferredSize(new Dimension(500, 500));
        
        
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
        
//        this.JButtonlogin.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                loginApp();
//            }
//        });
        
        jpan.add(this.JButtonlogin);
        jpan.add(this.jTextUsername);
        jpan.add(this.jtextPass);
        jpan.add(this.jLableUsername);
        jpan.add(this.jLablePassword);
        jpan.add(this.jLableTitle);
        
        this.add(jpan);
        System.out.println("Da vao day");
        this.JButtonlogin.setActionCommand("JButtonlogin");
    }

    

//    public void loginApp() {
//        this.username = this.jTextUsername.getText();
//        String password = new String(this.jtextPass.getPassword());
//        
//        if (username == "" || password == "") {
//            JOptionPane.showMessageDialog(this, "login fail, User account or password incorrect!!!", "WARNING", JOptionPane.WARNING_MESSAGE);
//        } else {
//
//            JOptionPane.showMessageDialog(null, "Login sucess!");
//            this.jdialogLogin.setModal(true);
//            this.jdialogLogin.setVisible(false);
//
//        }
//    }

    public JButton getButtonLogin() {
        return JButtonlogin;
    }
    public void Notification(int check){
        if(check == 0){
            JOptionPane.showMessageDialog(this, "Đăng nhập thất bại, Tài khoản hoặc mật khẩu không đúng!!!", "WARNING", JOptionPane.WARNING_MESSAGE);
        }else if(check == 1){
            JOptionPane.showMessageDialog(this, "Tài khoản đang được sử dụng, vui lòng sử dụng tài khoản khác!!!", "WARNING", JOptionPane.WARNING_MESSAGE);
        }else if(check == 2){
            JOptionPane.showMessageDialog(null, "Đăng nhập thành công!!!");
        }else if(check == 3){
            JOptionPane.showMessageDialog(this, "Tài khoản đã tồn tại, vui lòng đăng ký tên tài khoản khác!!!", "WARNING", JOptionPane.WARNING_MESSAGE);
        }else if(check == 4){
            JOptionPane.showMessageDialog(null, "Đăng ký thành công, vui lòng đăng nhập");
        }
        this.setVisible(false);
    }
    public void setWriter(PrintWriter writer) {
        this.writer = writer;
    }
    public String getUsername() {
        this.username = this.jTextUsername.getText();
        return username;
    }
    public String getPassword() {
        String password = new String(this.jtextPass.getPassword());
        return password;
    }
}
