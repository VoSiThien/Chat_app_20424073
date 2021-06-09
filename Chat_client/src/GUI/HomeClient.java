/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.PrintWriter;
import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import chat_client.User;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
public class HomeClient extends JFrame{
    private void Myinit() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 300, Short.MAX_VALUE)
        );
        pack();
    }
    
    private ArrayList<User> ListAllUser;
    private JMenuItem Download = new JMenuItem("Chat with this user");
    HashMap<String, Integer> tableIndex = new HashMap<String, Integer>();
    private PrintWriter writer;
    private String UserSelect = "";
    
    JPanel Container;
    JPanel TitlePanel;
    JPanel tablePanel;
    JPanel ButtomPanel;
    
    JLabel NameOfUser;
    JLabel Title;
    JButton StartChat;
    DefaultTableModel DefaultModel;
    JScrollPane ScrollPane;
    JTable TableAllUser;

    public void setWriter(PrintWriter writer) {
        this.writer = writer;
    }
    
    public JButton getButtonStartChat() {
        return StartChat;
    }

    public void setupForm() {
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                writer.println(1);
                writer.println("Close client");
                writer.println("Exit");
            }
        });
        
        this.setSize(750, 700);
        this.setTitle("Home chat app");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        this.setBackground(Color.white);
        

        TitlePanel = new JPanel();
        TitlePanel.setPreferredSize(new Dimension(750, 110));
        TitlePanel.setBackground(Color.lightGray);
        TitlePanel.setLayout(new GridLayout(2, 2));
        NameOfUser = new JLabel("");
        NameOfUser.setFont(new Font("Verdana", Font.PLAIN, 16));
        TitlePanel.add(NameOfUser);
        Title = new JLabel("Chat App 2021 - 20424073");
        Title.setBounds(50, 50, 200, 30);
        Title.setFont(new Font("Verdana", Font.PLAIN, 20));
        TitlePanel.add(Title);
        

        tablePanel = new JPanel();
        tablePanel.setPreferredSize(new Dimension(750, 330));
        tablePanel.setBackground(Color.lightGray);
        tablePanel.setLayout(new BorderLayout());


        DefaultModel = new DefaultTableModel();
        DefaultModel.addColumn("Username");
        DefaultModel.addColumn("Status");
        TableAllUser = new JTable(DefaultModel);

        TableAllUser.setPreferredScrollableViewportSize(new Dimension(750, 100));
        TableAllUser.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                    click_row_table();
            }
        });

        ScrollPane = new JScrollPane();
        this.ScrollPane = new JScrollPane(TableAllUser);
        tablePanel.add(ScrollPane, BorderLayout.CENTER);
        
        StartChat = new JButton("Start chat");
        StartChat.setBounds(320, 20, 90, 50);
        StartChat.setBackground(Color.GRAY);
        
        ButtomPanel = new JPanel();
        ButtomPanel.setPreferredSize(new Dimension(750, 90));
        ButtomPanel.setBackground(Color.lightGray);
        ButtomPanel.setLayout(null);
        ButtomPanel.add(StartChat);

        Container = new JPanel();
        Container.setPreferredSize(this.getSize());
        Container.setBackground(Color.lightGray);
        Container.setLayout(new BorderLayout());

        Container.add(TitlePanel, BorderLayout.NORTH);
        Container.add(tablePanel, BorderLayout.CENTER);
        Container.add(ButtomPanel, BorderLayout.SOUTH);
        this.add(Container, BorderLayout.CENTER);
        
    }
    public void click_row_table() {
        if (this.TableAllUser.getSelectedRows().length != 0) {
            int[] row = this.TableAllUser.getSelectedRows();
            UserSelect = this.TableAllUser.getValueAt(row[0], 0).toString();
            StartChat.setActionCommand("OPENFORMCHAT-" + UserSelect);
            System.out.println(UserSelect);
        }
        else{
            UserSelect = "";
        }
    }

    public HomeClient(String currentUser) {
        Myinit();
        setupForm();
    }

    public void ShowHomeClient(String UserName, ArrayList<User> ListAllUser) {
        NameOfUser.setText("App chat xin chào: " + UserName);
        this.ListAllUser = ListAllUser;
        setValueTable();
        this.setVisible(true);
    }

    public void reloadTable(ArrayList<User> ListAllUser) {
        this.ListAllUser = ListAllUser;
        DefaultModel.setRowCount(0);
        setValueTable();
    }

    public void setValueTable() {
        for (int i = 0; i < ListAllUser.size(); i++) {
            String[] rows = {ListAllUser.get(i).getName(), ListAllUser.get(i).getStatus()};
            DefaultModel.addRow(rows);
        }
    }
}
