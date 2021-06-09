/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat_client;

import GUI.Controller;

import java.util.Scanner;

public class Main_client {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ClassNotFoundException{
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                String hostname = "localhost";
                
                Scanner sc = new Scanner(System.in);
                int port = 5555;
                Controller controller = new Controller();
                ChatClient chatclient = new ChatClient(controller, hostname, port);
                chatclient.execute();
                
            }
        });
        
    }
    
}
