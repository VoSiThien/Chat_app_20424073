/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
public class formChat extends JFrame{
    private void Myinit(){
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }
    private String UserName = "";
    
    JTextArea textAllmessage;
    JTextField textMessage;
    JPanel Panel1;
    JPanel Panel2;
    JPanel Container;
    JButton buttonSend;
    //----------------------------
    public void setupForm(String UserName) {
        this.setSize(720, 550);
        this.setTitle("you are chat with: " + UserName);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        textAllmessage = new JTextArea(1, 1);
        JScrollPane areaScrollPane = new JScrollPane(textAllmessage);
        
        areaScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        areaScrollPane.setPreferredSize(new Dimension(250, 250));
        
        textAllmessage.setEditable(false);
        textAllmessage.setLineWrap(true);
        textAllmessage.setCaretPosition(textAllmessage.getDocument().getLength());
        
        Panel1 = new JPanel();
        Panel1.setPreferredSize(new Dimension(720, 500));
        Panel1.setBackground(Color.yellow);
        Panel1.setLayout(new BorderLayout());
        Panel1.add(areaScrollPane);
        
        buttonSend = new JButton("Send");
        buttonSend.setBounds(550, 15, 100, 40);
        textMessage = new JTextField();
        textMessage.setBounds(20, 15, 500, 40);
        
        Panel2 = new JPanel();
        Panel2.setLayout(null);
        Panel2.setPreferredSize(new Dimension(720, 80));
        Panel2.add(textMessage);
        Panel2.add(buttonSend);
        Panel2.setBackground(Color.darkGray);
        //------------------Add to Jframe
        
        Container = new JPanel();
        Container.setPreferredSize(this.getSize());
        Container.setLayout(new BorderLayout());
        Container.add(Panel1, BorderLayout.CENTER);
        Container.add(Panel2, BorderLayout.SOUTH);
        this.add(Container, BorderLayout.CENTER);
        
        
        buttonSend.setActionCommand("ADDMESSAGE-"+UserName);
    }

    public JTextField getTextMessage() {
        return textMessage;
    }

    public JTextArea gettextAllmessage() {
        return textAllmessage;
    }

    public JButton getbuttonSend() {
        return buttonSend;
    }

    
    public formChat(String UserName) {
        Myinit();
        this.UserName = UserName;
        setupForm(UserName);
        this.setVisible(true);
    }
}
