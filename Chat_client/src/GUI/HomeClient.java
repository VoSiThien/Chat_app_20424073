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
    
    private ArrayList<User> ListUser;
    private JMenuItem Download = new JMenuItem("Chat with this user");
    HashMap<String, Integer> tableIndex = new HashMap<String, Integer>();
    private PrintWriter writer;
    
    
    //-------------top----------
    JPanel Container;
    JPanel TopPanel;
    
    JLabel StudentNameLabel;
    
    //-------------center----------
    JPanel CenterCenterPanel;
    DefaultTableModel DefaultModel;
    JScrollPane ScrollPane;
    JTable UserTable;
    //-------------bottom----------

    public void setWriter(PrintWriter writer) {
        this.writer = writer;
    }
    
    public JMenuItem getDownload() {
        return Download;
    }
    
    //----------------------------
    public void setupForm() {
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                writer.println(1);
                writer.println("I want to close.....//////");
                writer.println("CLOSE");
            }
        });
        
        //set up
        this.setSize(750, 800);
        this.setTitle("Home chat app");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        this.setBackground(Color.white);
        
        
        //------------------LAYOUT BORDER NORTH-------------------------------------------------
        TopPanel = new JPanel();
        TopPanel.setPreferredSize(new Dimension(750, 90));
        TopPanel.setBackground(Color.red);
        TopPanel.setLayout(new GridLayout(2, 2));
        StudentNameLabel = new JLabel("");
        StudentNameLabel.setFont(new Font("Verdana", Font.PLAIN, 18));
        TopPanel.add(StudentNameLabel);
        //------------------LAYOUT BORDER CENTER----------------------------------------------

        CenterCenterPanel = new JPanel();
        CenterCenterPanel.setPreferredSize(new Dimension(750, 350));
        CenterCenterPanel.setBackground(Color.black);
        CenterCenterPanel.setLayout(new BorderLayout());


        DefaultModel = new DefaultTableModel();
        DefaultModel.addColumn("Username");
        DefaultModel.addColumn("IP");
        DefaultModel.addColumn("Port");
        DefaultModel.addColumn("Status");
        UserTable = new JTable(DefaultModel);

        this.UserTable.setPreferredScrollableViewportSize(new Dimension(750, 100));

        UserTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                JTable source = (JTable) e.getSource();
                int row = source.rowAtPoint(e.getPoint());
                int column = source.columnAtPoint(e.getPoint());
                String UserName = (String) UserTable.getValueAt(row, 0);
                final JPopupMenu menu = new JPopupMenu("Menu");
                Download.setActionCommand("CHATWITH-" + UserName);
                menu.add(Download);
                menu.show(e.getComponent(), e.getX(), e.getY());
            }
        });

        ScrollPane = new JScrollPane();
        this.ScrollPane = new JScrollPane(UserTable);
        CenterCenterPanel.add(ScrollPane, BorderLayout.CENTER);

        
        
        
        Container = new JPanel();
        Container.setPreferredSize(this.getSize());
        Container.setBackground(Color.red);
        Container.setLayout(new BorderLayout());

        Container.add(TopPanel, BorderLayout.NORTH);
        Container.add(CenterCenterPanel, BorderLayout.CENTER);
        this.add(Container, BorderLayout.CENTER);
    }

    public HomeClient(String currentUser) {
        Myinit();
        setupForm();
    }

    public void ShowHomeClient(String UserName, ArrayList<User> listUser) {
        StudentNameLabel.setText("Username : " + UserName);
        this.ListUser = listUser;
        setValueTable();
        this.setVisible(true);
    }

    public void reloadTable(ArrayList<User> listUser) {
        this.ListUser = listUser;
        DefaultModel.setRowCount(0);
        setValueTable();
    }

    public void setValueTable() {
        for (int i = 0; i < ListUser.size(); i++) {
            String[] rows = {ListUser.get(i).getName(), ListUser.get(i).getIp(), ListUser.get(i).getPort(), ListUser.get(i).getStatus()};
            DefaultModel.addRow(rows);
        }
    }
}
