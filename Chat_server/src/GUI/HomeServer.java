package GUI;

import java.io.PrintWriter;
import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class HomeServer extends JFrame{
    private void Myinit(){
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 500, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 400, Short.MAX_VALUE)
        );
        pack();
    }
    
    JPanel Container;
    JPanel jPanelCenter;
    JLabel Title;
    JButton StartServer;
    JButton StopServer;
    public HomeServer() {
        Myinit();
        setupForm();
        this.setVisible(true);
    }
    public void setupForm(){
        
        this.setSize(750, 700);
        this.setTitle("Home server");
        this.setLayout(new BorderLayout());
        this.setBackground(Color.white);
         this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
//        this.addWindowListener(new java.awt.event.WindowAdapter() {
//            @Override
//            public void windowClosing(java.awt.event.WindowEvent e) {
//                e.getWindow().dispose();
//            }
//        });
        
        StartServer = new JButton("Start Server");
        StartServer.setBounds(320, 20, 90, 50);
        StartServer.setBackground(Color.GRAY);
        StartServer.setVisible(true);
        
        StopServer = new JButton("Stop Server");
        StopServer.setBounds(320, 20, 90, 50);
        StopServer.setBackground(Color.red);
        StopServer.setVisible(true);
        
        jPanelCenter = new JPanel();
        jPanelCenter.setPreferredSize(this.getSize());
        jPanelCenter.setBackground(Color.lightGray);
        jPanelCenter.setLayout(new FlowLayout());
        jPanelCenter.add(this.StartServer);
        jPanelCenter.add(this.StopServer);
        
        Container = new JPanel();
        Container.setPreferredSize(this.getSize());
        Container.setBackground(Color.lightGray);
        Container.setLayout(new BorderLayout());
        
        Container.add(jPanelCenter, BorderLayout.CENTER);
        
        this.add(Container, BorderLayout.CENTER);
        this.StopServer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                System.exit(0);
            }
        });
        StartServer.setActionCommand("RunServer");
    }
    public JButton getJButtonStartServer(){
        return this.StartServer;
    }
    public void setVisablejButtonStopServer(){
        this.StopServer.setVisible(true);
    }
    public void setVisablejButtonStartServer(){
        this.StartServer.setVisible(false);
    }
}
