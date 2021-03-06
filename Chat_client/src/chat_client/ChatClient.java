/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat_client;

import java.net.*;
import java.io.*;
import java.util.Scanner;
import Process.ReadThread;
import GUI.Controller;
import Process.ReadThread;
import GUI.FormLoginIPvsPort;
public class ChatClient {
    private String hostname;
    private int port;
    private String userName;
    private Controller controller;
    private OutputStream output;
    private PrintWriter writer;
    private Socket socket;
    private FormLoginIPvsPort FloginServer = new FormLoginIPvsPort();

    public ChatClient(Controller _controler, String hostname, int port, FormLoginIPvsPort floginServer) {
        this.hostname = hostname;
        this.port = port;
        this.controller = _controler;
        this.FloginServer = floginServer;

    }

    public void execute() {
        try {
            Socket socket = new Socket(hostname, port);
            try {
                output = socket.getOutputStream();
                writer = new PrintWriter(output, true);
                this.controller.setSocketWriter(writer);
            } catch (IOException ex) {
                System.out.println("Error getting output stream: " + ex.getMessage());
                ex.printStackTrace();
            }
            new ReadThread(controller, socket, this, userName).start();
            // co the xet dk o day
            String userName = controller.getCurrentUserName();
            this.setUserName(userName);
            controller.setCurrentUserName(userName);
            //writer.println(userName);
            
            

        } catch (UnknownHostException ex) {
            System.out.println("Server not found: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("I/O Error: " + ex.getMessage());
        }

    }
    
    public void Checkexecute() {
        Socket socket;
        try {
            socket = new Socket(hostname, port);
            
            socket = null;
            this.FloginServer.Notification(2);
        } catch (UnknownHostException ex) {
            this.FloginServer.Notification(1);
            System.out.println("Server not found: " + ex.getMessage());
        } catch (IOException ex) {
            this.FloginServer.Notification(1);
            System.out.println("I/O Error: " + ex.getMessage());
        }
        
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return this.userName;
    }
}
