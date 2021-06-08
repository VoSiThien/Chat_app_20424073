
package chat_server;

import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServer {
    private int port;
    private ArrayList<String> userNames = new ArrayList<String>();//danh sach ten
    private ArrayList<UserThread> userThreads = new ArrayList<UserThread>();//danh sach cac thredd
 
    public ChatServer(int port) {
        this.port = port;
    }
 
    public void execute() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
 
            System.out.println("Chat Server is listening on port " + port);
 
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New user connected");
 
                UserThread newUser = new UserThread(socket, this);
                userThreads.add(newUser);
                newUser.start();
 
            }
 
        } catch (IOException ex) {
            System.out.println("Error in the server: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
 
    public static void main(String[] args) {
//        if (args.length < 1) {
//            System.out.println("Syntax: java ChatServer <port-number>");
//            System.exit(0);
//        }
 
        //Scanner scan = new Scanner(System.in);
        //int p = scan.nextInt();
        //int port = Integer.parseInt(args[0]);
        int port = 8989;
 
        ChatServer server = new ChatServer(port);
        server.execute();//khởi tạo một userThread mới
    }
 
    /**
     * Delivers a message from one user to others (broadcasting)
     */
    public void broadcast(String message, UserThread excludeUser,String n) {//dung de gui tin nhan den tat ca cac client tu 1 client
        int i = 0;
        for (String name: userNames){
            if(name.equals(n)){
                break;
                
            }
            i++;
        }
        int j = 0;
        for (UserThread aUser : userThreads) {
            if (j == i) {
                aUser.sendMessage(message);
            }
            j++;
        }
    }
 
    /**
     * Stores username of the newly connected client.
     */
    public void addUserName(String userName) {
        userNames.add(userName);
    }
 
    /**
     * When a client is disconneted, removes the associated username and UserThread
     */
    public void removeUser(String userName, UserThread aUser) {
        boolean removed = userNames.remove(userName);
        if (removed) {
            userThreads.remove(aUser);
            System.out.println("The user " + userName + " quitted");
        }
    }
 
    public ArrayList<String> getUserNames() {
        return this.userNames;
    }
 
    /**
     * Returns true if there are other users connected (not count the currently connected user)
     */
    public boolean hasUsers() {
        return !this.userNames.isEmpty();
    }
}
