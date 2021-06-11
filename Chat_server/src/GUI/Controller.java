/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import DAO.process;
import chat_server.ChatServer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Controller implements ActionListener{
    private process p = new process();
    private HomeServer homeserver;
    public Controller(){
        homeserver = new HomeServer();
        homeserver.getJButtonStartServer().addActionListener(this);
    }
    public void RunServer(){
        int port = 8484;
        ChatServer server = new ChatServer(port);
        server.execute();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("RunServer") ){
            RunServer();
            homeserver.setVisablejButtonStartServer();
            homeserver.setVisablejButtonStopServer();
        }
    }
}
