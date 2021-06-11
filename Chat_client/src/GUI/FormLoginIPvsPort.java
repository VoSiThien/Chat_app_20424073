/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import chat_client.ChatClient;
import java.awt.*;
import java.io.*;
import javax.swing.*;
public class FormLoginIPvsPort extends JFrame {
    private String username;
    private PrintWriter writer;
    
    private JLabel jLableTitle;
    private JLabel jLablePort;
    private JLabel jLableIP;
    private JTextField jTextPort;
    private JTextField jTextIP;
    private JButton jButtonLoginServer;
    
    public FormLoginIPvsPort() {
        Myinit();
    }
    private void Myinit(){
        this.setSize(500, 500);
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
        
        
        this.jLableTitle = new JLabel("Login Server");
        this.jLableTitle.setBounds(210, 60, 200, 30);
        
        this.jLablePort = new JLabel("Port");
        this.jLablePort.setBounds(50, 120, 60, 30);
        this.jTextPort = new JTextField();
        this.jTextPort.setBounds(140, 120, 250, 30);
        
        this.jLableIP = new JLabel("IP Address");
        this.jLableIP.setBounds(50, 190, 100, 30);
        this.jTextIP = new JTextField();
        this.jTextIP.setBounds(140, 190, 250, 30);
        
        this.jButtonLoginServer = new JButton();
        this.jButtonLoginServer.setText("Login");
        this.jButtonLoginServer.setBounds(180, 280, 140, 40);
        
        jpan.add(this.jButtonLoginServer);
        jpan.add(this.jTextPort);
        jpan.add(this.jTextIP);
        jpan.add(this.jLablePort);
        jpan.add(this.jLableIP);
        jpan.add(this.jLableTitle);
        
        this.add(jpan);
        this.jButtonLoginServer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ConnectServer();
            }
        });
    }
    private ChatClient chatclient;
    private String hostname;
    private int port;
    private Controller controller;
    public void ConnectServer() {
        int tempCheck = 0;
        hostname = this.jTextIP.getText();
        try {
            port = Integer.parseInt(this.jTextPort.getText().toString());
        } catch (NumberFormatException e) {
            tempCheck = 1;
        }
        if(tempCheck == 0){
            int checkCon = 0;
            controller = new Controller(checkCon);
            chatclient = new ChatClient(controller, hostname, port, this);
            chatclient.Checkexecute();
        }
        else{
            Notification(3);
        }
        
    }
    public void Notification(int check){
        if(check == 1){
            JOptionPane.showMessageDialog(this, "Kết nối địa chỉ ip và port không thành công!!!", "WARNING", JOptionPane.WARNING_MESSAGE);
        }
        else if(check == 2){
            this.setVisible(false);
            controller = new Controller(1);
            chatclient = new ChatClient(controller, hostname, port, this);
            chatclient.execute();
        }
        else if(check == 3){
            JOptionPane.showMessageDialog(this, "Port phải là dạng số  - vd : 8080!!!", "WARNING", JOptionPane.WARNING_MESSAGE);
        }
    }
}
