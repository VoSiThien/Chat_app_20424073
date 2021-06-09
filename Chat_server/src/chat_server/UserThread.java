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

            //send new list of connected user to new user
            printUsers(output);
            //create new user info
            String userName = reader.readLine();
            System.out.println(userName);
            this.userName = userName;
            User connectedUser = new User(userName, "", "", "online");
            server.addUserName(connectedUser);
            server.addUserThread(userName, this);
            //add this new user to list of all user by controller 
            String serverMessage = "NewUser_" + userName;
            server.processMessage(serverMessage, this, null);

            //recieve client message, if message is "bye" -> end
            String clientMessage;
            do {
                //recieve the users that client want to send message
                ArrayList<String> reciever = new ArrayList<String>();
                //--read number of users
                int numberOfUser = Integer.parseInt(reader.readLine());
                for (int i = 0; i < numberOfUser; i++) {
                    String recieverUser = reader.readLine();
                    reciever.add(recieverUser);
                }

                clientMessage = reader.readLine();

                if (clientMessage.equals("CLOSE")) {
                    //do nothing
                    

                } else {
                    serverMessage = "[" + userName + "]: " + clientMessage;
                    server.processMessage(serverMessage, this, reciever);
                }
            } while (!clientMessage.equals("CLOSE"));
            server.removeUser(userName, this);
            socket.close();
            //notify all user that a user is quit/
            server.processMessage("Quit_" + userName, this, null);
            
        } catch (IOException ex) {
            System.out.println("Error in UserThread: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    /**
     * Sends a list of online users to the newly connected user.
     */
    public void printUsers(OutputStream output) throws IOException {
        writer.println("LoadDashBoard");
        writer.println(server.getListConnectedUser().size());
        for (int i = 0; i < server.getListConnectedUser().size(); i++) {
            writer.println(server.getListConnectedUser().get(i).getName());
            writer.println(server.getListConnectedUser().get(i).getStatus());
        }
    }

    /**
     * Sends a message to the client.
     */
    public void sendMessage(String message) {
        writer.println(message);
    }
}
