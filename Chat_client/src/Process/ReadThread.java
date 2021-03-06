/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Process;

import java.io.*;
import java.net.*;
import chat_client.ChatClient;
import java.util.ArrayList;
import GUI.Controller;
import chat_client.User;
import GUI.formChat;

public class ReadThread extends Thread {
    private Controller controller;
    private InputStream input;
    private String currentUser;
    private BufferedReader reader;
    private Socket socket;
    private ChatClient client;
    ArrayList<User> users;

    public ReadThread(Controller Controller, Socket socket, ChatClient client, String CurrentUser) {
        this.socket = socket;
        this.client = client;
        this.controller = Controller;
        this.currentUser = CurrentUser;
        try {
            input = socket.getInputStream();
            reader = new BufferedReader(new InputStreamReader(input));

        } catch (IOException ex) {
            System.out.println("Error getting input stream: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void run() {
        while (true) {
            try {
                String response = reader.readLine();
                if (response.equals("LoadHomeClient")) {
                    users = new ArrayList<User>();
                    int n = Integer.parseInt(reader.readLine());

                    for (int i = 0; i < n; i++) {
                        String Username = reader.readLine();
                        String Status = reader.readLine();
                        users.add(new User(Username, Status));
                    }
                    controller.showHomeClient(currentUser, users);
                } else if (response.startsWith("NewClientOnline_")) {
                    String[] message = response.split("\\_");
                    User newUser = new User(message[1], "online");
                    users.add(newUser);
                    controller.reloadTable(users);
                }
                else if(response.startsWith("CloseNow_")){
                    String[] message = response.split("\\_");
                    users.removeIf(user -> (message[1].equals(user.getName())));
                    controller.reloadTable(users);
                }
                else if(response.equals("++LoginFail")){// dang nhap sai tk mk
                    controller.Notification(0, "login");
                }
                else if(response.equals("++AccountUsed")){ // tk mk da dang nhap
                    controller.Notification(1, "login");
                }
                else if(response.equals("++LoginSuccess")){ // Dang nhap thanh cong
                    controller.Notification(2, "login");
                }
                else if(response.equals("++AccountExist")){ // Dang ky bi trung
                    controller.Notification(3, "Register");
                }
                else if(response.equals("++RegisterSuccess")){ // Dang ky thanh cong
                    controller.Notification(4, "Register");
                }
                else {
                    if (client.getUserName() != null) {
                        
                        String sender = response;
                        formChat formchat = controller.getListFormChat().get(sender);
                        MethodForm chatMethod = new MethodForm(formchat);
                        String message = reader.readLine();
                        chatMethod.DisplayRecievedMessage(message + "\n"); 
                    }
                }

            } catch (IOException ex) {
                System.out.println("Error reading from server: " + ex.getMessage());
                ex.printStackTrace();
                break;
            }
        }
    }
}
