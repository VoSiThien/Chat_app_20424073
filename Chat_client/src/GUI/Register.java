/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.*;
import java.io.*;
import javax.swing.*;
public class Register extends JFrame{
    private String username;
    private PrintWriter writer;
    
    private JLabel jLableTitle;
    private JLabel jLableUsername;
    private JLabel jLablePassword;
    private JLabel jLableConfirmPassword;
    private JTextField jTextUsername;
    private JPasswordField jtextPass;
    private JPasswordField jtextConfirmPass;
    private JButton JButtonRegister;
    private formLogin Flogin;
    public Register() {
        Myinit();
        this.setVisible(false);
    }
    private void Myinit(){
        this.setSize(500, 500);
        this.setTitle("Register");
        this.setLocationRelativeTo(null);
        this.setLayout(new FlowLayout());
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        
        JPanel jpan = new JPanel();
        jpan.setLayout(null);
        jpan.setPreferredSize(new Dimension(500, 500));
        
        
        this.jLableTitle = new JLabel("Register account");
        this.jLableTitle.setBounds(210, 50, 200, 30);
        
        this.jLableUsername = new JLabel("Username");
        this.jLableUsername.setBounds(70, 110, 60, 30);
        this.jTextUsername = new JTextField();
        this.jTextUsername.setBounds(140, 110, 250, 30);
        
        this.jLablePassword = new JLabel("Password");
        this.jLablePassword.setBounds(70, 170, 60, 30);
        this.jtextPass = new JPasswordField();
        this.jtextPass.setBounds(140, 170, 250, 30);
        
        this.jLableConfirmPassword = new JLabel("Password");
        this.jLableConfirmPassword.setBounds(70, 230, 60, 30);
        this.jtextConfirmPass = new JPasswordField();
        this.jtextConfirmPass.setBounds(140, 230, 250, 30);
        
        this.JButtonRegister = new JButton();
        this.JButtonRegister.setText("Register");
        this.JButtonRegister.setBounds(180, 350, 140, 40);
        
        jpan.add(this.JButtonRegister);
        jpan.add(this.jTextUsername);
        jpan.add(this.jtextPass);
        jpan.add(this.jLableUsername);
        jpan.add(this.jLablePassword);
        jpan.add(this.jLableConfirmPassword);
        jpan.add(this.jtextConfirmPass);
        jpan.add(this.jLableTitle);
        
        this.add(jpan);
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                CloseRegister();
            }
        });
        this.JButtonRegister.setActionCommand("JButtonRegister");
    }

    public void CloseRegister(){
        this.setVisible(false);
        Flogin.setVisible(true);
    }
    public JButton getButtonRegister() {
        return JButtonRegister;
    }
    public void setFormLogin(formLogin flogin){
        Flogin = flogin;
    }
    public void Notification(int check){
        if(check == 3){
            JOptionPane.showMessageDialog(this, "Tài khoản đã tồn tại, vui lòng đăng ký tên tài khoản khác!!!", "WARNING", JOptionPane.WARNING_MESSAGE);
        }else if(check == 4){
            JOptionPane.showMessageDialog(null, "Đăng ký thành công, vui lòng đăng nhập");
            this.setVisible(false);
            Flogin.setVisible(true);
        }else if(check == -1){
            JOptionPane.showMessageDialog(this, "Mật khẩu xác nhận không khớp", "WARNING", JOptionPane.WARNING_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(this, "Các thông tin không được để trống", "WARNING", JOptionPane.WARNING_MESSAGE);
        }
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
    
    public String getComfirmPassword() {
        String ConfirmPass = new String(this.jtextConfirmPass.getPassword());
        return ConfirmPass;
    }
}
