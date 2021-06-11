
package chat_server;

import java.io.*;
import java.net.*;
import java.util.*;
import GUI.formLogin;

public class ChatServer {
    private formLogin fLogin;
    private int port;
    private ArrayList<User> ListUser = new ArrayList<User>();
    private HashMap<String, UserThread> userThreads = new HashMap<String, UserThread>();

    public ChatServer(int port) {
        this.port = port;
    }

    public void execute() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            fLogin = new formLogin();
            System.out.println("Server is listen on port: --  " + port);
            fLogin.Notification(2);
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New user connected server");
                System.out.println("vao day: "+ InetAddress.getLocalHost());
                UserThread newUser = new UserThread(socket, this);
                newUser.start();

            }

        } catch (IOException ex) {
            fLogin.Notification(1);
            System.out.println("Not connect port: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    void SendMessage(String message, UserThread userThread, ArrayList<String> ListUser) {
        for (Map.Entry<String, UserThread> entry : userThreads.entrySet()) {
            String key = entry.getKey();
            UserThread value = entry.getValue();
            if (value != userThread) {
                if (ListUser == null) {
                    value.sendMessage(message);
                } else {
                    if (ListUser.contains(value.getUserName())) {
                        value.sendMessage(userThread.getUserName());
                        value.sendMessage(message); 
                    }
                }
            }
        }
    }

    public void addUserName(User user) {
        ListUser.add(user);
    }

    public void addUserThread(String username, UserThread thread) {
        userThreads.put(username, thread);
    }

    public void removeUser(String userName, UserThread aUser) {
        boolean removed = false;
        for(int i = 0; i < ListUser.size(); i++){
            if(ListUser.get(i).getName().equals(userName)){
                removed = true;
                ListUser.remove(i);
                break;
            }
        }
        if (removed) {
            userThreads.remove(aUser);
        }
    }

    ArrayList<User>getListUser() {
        return this.ListUser;
    }

    public boolean hasUsers() {
        return !this.ListUser.isEmpty();
    }
}
