/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat_server;

import java.io.*;
import java.net.*;
import java.util.*;
public class UserThread extends Thread{
    private Socket socket;
    private ChatServer server;
    private PrintWriter writer;
    private String userName;

    public UserThread(Socket socket, ChatServer server) {
        this.socket = socket;
        this.server = server;
    }

    public String getUserName() {
        return userName;
    }

    public void run() {
        try {
            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            OutputStream output = socket.getOutputStream();
            writer = new PrintWriter(output, true);

            printUsers(output);
            String userName = reader.readLine();
            System.out.println(userName);
            this.userName = userName;
            User connectedUser = new User(userName, "online");
            server.addUserName(connectedUser);
            server.addUserThread(userName, this);
            String serverMessage = "NewUser_" + userName;
            server.SendMessage(serverMessage, this, null);

            String clientMessage;
            do {
                ArrayList<String> reciever = new ArrayList<String>();
                int numberOfUser = Integer.parseInt(reader.readLine());
                for (int i = 0; i < numberOfUser; i++) {
                    String recieverUser = reader.readLine();
                    reciever.add(recieverUser);
                }

                clientMessage = reader.readLine();

                if (clientMessage.equals("Exit")) {
                    

                } else {
                    serverMessage = "[" + userName + "]: " + clientMessage;
                    server.SendMessage(serverMessage, this, reciever);
                }
            } while (!clientMessage.equals("Exit"));
            server.removeUser(userName, this);
            socket.close();
            server.SendMessage("Quit_" + userName, this, null);
            
        } catch (IOException ex) {
            System.out.println("Error in UserThread: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void printUsers(OutputStream output) throws IOException {
        writer.println("LoadHomeClient");
        writer.println(server.getListConnectedUser().size());
        for (int i = 0; i < server.getListConnectedUser().size(); i++) {
            writer.println(server.getListConnectedUser().get(i).getName());
            writer.println(server.getListConnectedUser().get(i).getStatus());
        }
    }

    public void sendMessage(String message) {
        writer.println(message);
    }
}
