
package chat_server;

import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServer {
    private int port;
    private ArrayList<User> ListUser = new ArrayList<User>();
    private HashMap<String, UserThread> userThreads = new HashMap<String, UserThread>();

    public ChatServer(int port) {
        this.port = port;
    }

    public void execute() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {

            System.out.println("Chat Server is listening on port " + port);

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New user connected");
                //create a new thread for new user connected
                UserThread newUser = new UserThread(socket, this);
                newUser.start();

            }

        } catch (IOException ex) {
            System.out.println("Error in the server: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    /**
     * Delivers a message from one user to determined others
     */
    void processMessage(String message, UserThread excludeUser, ArrayList<String> specificUser) {
        for (Map.Entry<String, UserThread> entry : userThreads.entrySet()) {
            String key = entry.getKey();
            UserThread value = entry.getValue();
            if (value != excludeUser) {
                if (specificUser == null) {
                    value.sendMessage(message);
                } else {
                    if (specificUser.contains(value.getUserName())) {
                        value.sendMessage(excludeUser.getUserName());
                        value.sendMessage(message); 
                    }
                }
            }
        }
    }

    /**
     * Stores username of the newly connected client.
     */
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

    ArrayList<User>getListConnectedUser() {
        //return this.userNames;
        return this.ListUser;
    }

    /**
     * Returns true if there are other users connected (not count the currently
     * connected user)
     */
    public boolean hasUsers() {
        return !this.ListUser.isEmpty();
    }
}
