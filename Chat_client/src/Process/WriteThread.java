/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat_client;

import java.io.*;
import java.net.*;
import java.util.*;
public class WriteThread extends Thread {
    private PrintWriter writer;
    private Socket socket;
    private ChatClient client;
 
    public WriteThread(Socket socket, ChatClient client) {
        this.socket = socket;
        this.client = client;
 
        try {
            OutputStream output = socket.getOutputStream();
            writer = new PrintWriter(output, true);
        } catch (IOException ex) {
            System.out.println("Error getting output stream: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
 
    public void run() {
 
        //Console console = System.console();
        Scanner scan = new Scanner(System.in);
        System.out.print("\nEnter your name: ");
        String Uname = scan.nextLine();
        
        //String userName = console.readLine("\nEnter your name: ");
        String userName = Uname;
        client.setUserName(userName);
        writer.println(userName);
 
        String text;
 
        do {
            System.out.print("\nEnter To name: ");
            String ToName = scan.nextLine();
            System.out.print("[" + userName + "]: ");
            //text = console.readLine("[" + userName + "]: ");
            text = scan.nextLine();
            writer.println(ToName);
            writer.println(text);
 
        } while (!text.equals("bye"));
 
        try {
            socket.close();
        } catch (IOException ex) {
 
            System.out.println("Error writing to server: " + ex.getMessage());
        }
    }
}
