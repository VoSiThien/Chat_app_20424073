/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat_client;

import java.net.*;
import java.io.*;
import java.util.Scanner;
public class ChatClient {
    private String hostname;
    private int port;
    private String userName;
 
    public ChatClient(String hostname, int port) {
        this.hostname = hostname;
        this.port = port;
    }
 
    public void execute() {
        try {
            Socket socket = new Socket(hostname, port);
 
            System.out.println("Connected to the chat server");
 
            new ReadThread(socket, this).start();
            new WriteThread(socket, this).start();
 
        } catch (UnknownHostException ex) {
            System.out.println("Server not found: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("I/O Error: " + ex.getMessage());
        }
 
    }
 
    public void setUserName(String userName) {
        this.userName = userName;
    }
 
    public String getUserName() {
        return this.userName;
    }
 
 
    public static void main(String[] args) {
        //if (args.length < 2) return;
 
        Scanner scan = new Scanner(System.in);
        
        
        //String hostname = args[0];
        //int port = Integer.parseInt(args[1]);
        String hostname = scan.nextLine();
        int p = scan.nextInt();
        int port = p;
 
        ChatClient client = new ChatClient(hostname, port);
        client.execute();
    }
}
