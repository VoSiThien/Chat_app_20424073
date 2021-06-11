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
            String currentAction;
            String username = "", password = "";
            do {
                currentAction = reader.readLine();
                if(currentAction.equals("--CloseLoginRegister")){
                    socket.close();
                    System.out.println("User cancels login to client");
                    return;
                }
                username = reader.readLine();
                password = reader.readLine();
                if (currentAction.equals("--Register")) {
                    int KT = processDAO.register(username, password);
                    if (KT == -99) {
                        sendMessage("++AccountExist");
                    } else if(KT != 0){
                        sendMessage("++RegisterSuccess");
                    }
                }
                if (currentAction.equals("--Login")) {
                    ArrayList<User> list = new ArrayList<User>();
                    int check = processDAO.Login(username, password);
                    if (check == 0) {
                        this.sendMessage("++LoginFail");
                    }
                    if (check != 0) {
                        boolean ACCUsed = false;
                        ArrayList<User> UserConnect = server.getListUser();
                        for (int i = 0; i < UserConnect.size(); i++) {
                            if (UserConnect.get(i).getName().equals(username)) {
                                this.sendMessage("++AccountUsed");
                                ACCUsed = true;
                            }
                        }
                        if (ACCUsed == false) {
                            printUsers(output);
                            this.sendMessage("++LoginSuccess");
                            break;
                        }

                    }
                }

            } while (currentAction.equals("--Register") || currentAction.equals("--Login"));
            userName = username;
            User UserConnect = new User(userName, "online");
            server.addUserName(UserConnect);
            server.addUserThread(userName, this);
            String serverMessage = "NewClientOnline_" + userName;
            server.SendMessage(serverMessage, this, null);

            String clientMessage;
            do {
                ArrayList<String> ListClients = new ArrayList<String>();
                int numberOfUser = Integer.parseInt(reader.readLine());
                for (int i = 0; i < numberOfUser; i++) {
                    String OneUser = reader.readLine();
                    ListClients.add(OneUser);
                }
                clientMessage = reader.readLine();
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
        writer.println(server.getListUser().size());
        for (int i = 0; i < server.getListUser().size(); i++) {
            writer.println(server.getListUser().get(i).getName());
            writer.println(server.getListUser().get(i).getStatus());
        }
    }

    public String getUserName() {
        return userName;
    }
    public void sendMessage(String message) {
        writer.println(message);
    }
}
