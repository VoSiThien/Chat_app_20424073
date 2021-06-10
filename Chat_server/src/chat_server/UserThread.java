/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat_server;

import java.io.*;
import java.net.*;
import java.util.*;
import DAO.process;
public class UserThread extends Thread{
    private Socket socket;
    private ChatServer server;
    private PrintWriter writer;
    private String userName;
    private process processDAO;

    public UserThread(Socket socket, ChatServer server) {
        this.socket = socket;
        this.server = server;
        processDAO = new process();
    }


    public void run() {
        try {
            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            OutputStream output = socket.getOutputStream();
            writer = new PrintWriter(output, true);

            String type;
            String username = "", password = "";
            do {
                type = reader.readLine();
                if(type.equals("CLOSELOGINVIEW")){//login view is closed when user don't want to login
                    socket.close();
                    System.out.println("End connection with unknown user");
                    return;
                }
                username = reader.readLine();
                password = reader.readLine();
                if (type.equals("REGISTER")) {
                    int KT = processDAO.register(username, password);
                    if (KT == -99) {
                        sendMessage("REGISTER_failed");
                    } else if(KT != 0){
                        sendMessage("REGISTER_successful");
                    }
                }
                if (type.equals("LOGIN")) {
                    System.out.println("USER login");
                    System.out.println("username:" + username);
                    System.out.println("password:" + password);
                    ArrayList<User> list = new ArrayList<User>();
                    int check = processDAO.Login(username, password);
                    if (check == 0) {
                        this.sendMessage("FAILED");
                        System.out.println("FAILED");
                    }
                    if (check != 0) {
                        boolean isDuplicate = false;
                        ArrayList<User> connectedUser = server.getListConnectedUser();
                        for (int i = 0; i < connectedUser.size(); i++) {
                            if (connectedUser.get(i).getName().equals(username)) {
                                this.sendMessage("FAILED-CONNECTEDDUPLICATE");
                                System.out.println("FAILED-CONNECTEDDUPLICATE");
                                isDuplicate = true;
                            }
                        }
                        if (isDuplicate == false) {//login succesfully
                            //Send list of all user connected to current user
                            printUsers(output);
                            //login successfully
                            this.sendMessage("SUCCESS");
                            System.out.println("SUCCESS");
                            break;
                        }

                    }
                }

            } while (type.equals("REGISTER") || type.equals("LOGIN"));
            //chan ngay day
            //printUsers(output);
            userName = username;
            System.out.println(userName + " --- " + type);
            User connectedUser = new User(userName, "online");
            server.addUserName(connectedUser);
            server.addUserThread(userName, this);
            String serverMessage = "NewClient_" + userName;
            server.SendMessage(serverMessage, this, null);

            String clientMessage;
            do {
                ArrayList<String> ListClients = new ArrayList<String>();
                int numberOfUser = Integer.parseInt(reader.readLine());
                System.out.println("so: "+ numberOfUser);
                for (int i = 0; i < numberOfUser; i++) {
                    String OneUser = reader.readLine();
                    System.out.println("thang usser: "+ OneUser);
                    ListClients.add(OneUser);
                }

                clientMessage = reader.readLine();
                System.out.println("tin nhan:  " + clientMessage);

                if (clientMessage.equals("Exit")) {
                    

                } else {
                    serverMessage = "[" + userName + "]: " + clientMessage;
                    server.SendMessage(serverMessage, this, ListClients);
                }
            } while (!clientMessage.equals("Exit"));
            server.removeUser(userName, this);
            socket.close();
            server.SendMessage("CloseNow_" + userName, this, null);
            
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

    public String getUserName() {
        return userName;
    }
    public void sendMessage(String message) {
        writer.println(message);
    }
}
