/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import chat_server.ChatServer;
import java.awt.*;
import java.io.*;
import javax.swing.*;

public class formLogin extends JFrame{
    private String username;
    private PrintWriter writer;
    
    private JLabel jLableTitle;
    private JLabel jLableUsername;
    private JLabel jLablePassword;
    private JTextField jTextUsername;
    private JPasswordField jtextPass;
    private JButton JButtonlogin;
    
    public formLogin() {
        Myinit();
    }
    private void Myinit(){
        this.setSize(450, 300);
        this.setTitle("Login Server");
        this.setLocationRelativeTo(null);
        this.setLayout(new FlowLayout());
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                System.exit(0);
            }
        });
        
        JPanel jpan = new JPanel();
        jpan.setLayout(null);
        jpan.setPreferredSize(new Dimension(500, 500));
        
        
        this.jLableTitle = new JLabel("Login server");
        this.jLableTitle.setBounds(210, 40, 200, 30);
        
        this.jLableUsername = new JLabel("port");
        this.jLableUsername.setBounds(70, 100, 60, 30);
        this.jTextUsername = new JTextField();
        this.jTextUsername.setBounds(120, 100, 250, 30);
        
        
        this.JButtonlogin = new JButton();
        this.JButtonlogin.setText("Login");
        this.JButtonlogin.setBounds(180, 150, 140, 40);
        
   
        jpan.add(this.JButtonlogin);
        jpan.add(this.jTextUsername);
        jpan.add(this.jLableUsername);
        jpan.add(this.jLableTitle);
        
        this.add(jpan);
        this.JButtonlogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LoginServer();
            }
        });
    }


    public JButton getButtonLogin() {
        return JButtonlogin;
    }
    public void LoginServer(){
        int port = 0;
        int tempCheck = 0;
        try {
            port = Integer.parseInt(this.jTextUsername.getText().toString());
        } catch (NumberFormatException e) {
            tempCheck = 1;
        }
        if(tempCheck == 0){
            ChatServer server = new ChatServer(port);
            this.setVisible(false);
            server.execute();
        }
        else{
            JOptionPane.showMessageDialog(this, "Port phải là dạng số  - vd : 8080!!!", "WARNING", JOptionPane.WARNING_MESSAGE);
        }
    }
    public void Notification(int check){
        if(check == 1){
            JOptionPane.showMessageDialog(this, "Kết nối port không thành công!!!", "WARNING", JOptionPane.WARNING_MESSAGE);
            System.exit(0);
        }
        else{
            JOptionPane.showMessageDialog(null, "Đăng nhập server thành công!!!");
            
        }
    }
}
