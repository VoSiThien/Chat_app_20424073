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
    
    private JLabel jLableTitle;
    private JLabel jLableUsername;
    private JLabel jLablePassword;
    private JTextField jTextUsername;
    private JPasswordField jtextPass;
    private JButton JButtonlogin;
    private JButton JButtonOpenRegister;
    
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
        this.JButtonlogin.setBounds(180, 280, 140, 40);
        
        this.JButtonOpenRegister = new JButton();
        this.JButtonOpenRegister.setText("Register");
        this.JButtonOpenRegister.setBounds(180, 330, 140, 40);
        

        
        jpan.add(this.JButtonlogin);
        jpan.add(this.JButtonOpenRegister);
        jpan.add(this.jTextUsername);
        jpan.add(this.jtextPass);
        jpan.add(this.jLableUsername);
        jpan.add(this.jLablePassword);
        jpan.add(this.jLableTitle);
        
        this.add(jpan);
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                writer.println("--CloseLoginRegister");
                System.exit(0);
            }
        });
        this.JButtonOpenRegister.setActionCommand("JButtonOpenRegister");
        this.JButtonlogin.setActionCommand("JButtonlogin");
    }


    public JButton getButtonLogin() {
        return JButtonlogin;
    }
    
    public JButton getJButtonOpenRegister() {
        return JButtonOpenRegister;
    }
    public void Notification(int check){
        
        if(check == 0){
            JOptionPane.showMessageDialog(this, "????ng nh???p th???t b???i, T??i kho???n ho???c m???t kh???u kh??ng ????ng!!!", "WARNING", JOptionPane.WARNING_MESSAGE);
        }else if(check == 1){
            JOptionPane.showMessageDialog(this, "T??i kho???n ??ang ???????c s??? d???ng, vui l??ng s??? d???ng t??i kho???n kh??c!!!", "WARNING", JOptionPane.WARNING_MESSAGE);
        }else if(check == 2){
            JOptionPane.showMessageDialog(null, "????ng nh???p th??nh c??ng!!!");
            this.setVisible(false);
//            int optionType = JOptionPane.OK_OPTION;
//            int result = JOptionPane.showConfirmDialog(this, "????ng nh???p th??nh c??ng!!!", "Success!!", optionType);
//            if (result == JOptionPane.OK_OPTION) {
//                this.setVisible(false);
//            }
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
}
