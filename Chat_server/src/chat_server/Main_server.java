/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat_server;

import GUI.HomeServer;
public class Main_server {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        int port = 8484;
        ChatServer server = new ChatServer(port);
        server.execute();
    }
    
}
