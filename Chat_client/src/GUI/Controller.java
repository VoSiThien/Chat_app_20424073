package GUI;

import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import com.sun.xml.internal.ws.api.message.saaj.SAAJFactory;

import chat_client.ChatClient;
import chat_client.User;
import Process.MethodForm;

public class Controller implements ActionListener{
    //private ConnectionForm connectionView;
    private formLogin flogin;
    private HomeClient homeclient = null;
    //private RegisterForm registerView;
    private HashMap<String, formChat> ListFormChat;
    private MethodForm methodform;
    private PrintWriter socketWriter;

    private String CurrentUserName = "";

    public Controller() {
        flogin = new formLogin();
        CurrentUserName = flogin.getUsername();
        ListFormChat = new HashMap<String, formChat>();

    }

    public void showHomeClient(String currentUser, ArrayList<User> listUser) {
        homeclient = new HomeClient(currentUser);
        homeclient.setWriter(socketWriter);
        homeclient.ShowHomeClient(this.CurrentUserName, listUser);
        homeclient.getDownload().addActionListener(this);
    }

    public void reloadTable(ArrayList<User> listUser) {
        homeclient.reloadTable(listUser);
    }
    
    public HomeClient getHomeClient() {
        return homeclient;
    }

    public void addNewFormChat(String UserName) {
        formChat formchat = new formChat(UserName);
        ListFormChat.put(UserName, formchat);
    }

    public void actionPerformed(ActionEvent e) {
        if (ListFormChat.size() != 0 && e.getActionCommand().startsWith("ADDMESSAGE")) {
            //display message of current user
            String[] SplitCommand = e.getActionCommand().split("\\-");
            String Username = SplitCommand[1];
            formChat chatView = ListFormChat.get(Username);
            methodform = new MethodForm(chatView);
            methodform.AddNewMessage();
            socketWriter.println(1);

            socketWriter.println(Username);
            socketWriter.println(methodform.GetAddedMessage());
            methodform.SetBlankForAddedMessageField();
        }
        if (e.getActionCommand().startsWith("CHATWITH")) {
            String[] SplitCommand = e.getActionCommand().split("\\-");
            String Username = SplitCommand[1];
            formChat chatView = new formChat(Username);
            chatView.getbuttonSend().addActionListener(this);
            ListFormChat.put(Username, chatView);

        }
    }

    public HashMap<String, formChat> getListFormChat() {
        return ListFormChat;
    }

    public String getCurrentUserName() {
        return CurrentUserName;
    }

    public void setSocketWriter(PrintWriter socketWriter) {
        this.socketWriter = socketWriter;
    }

    public void setCurrentUserName(String CurrentUserName) {
        this.CurrentUserName = CurrentUserName;
    }

    public void DisplayMessage(String UserName, String message) {
        formChat chatView = ListFormChat.get(UserName);
        MethodForm methodform = new MethodForm(chatView);
        methodform.AddNewMessage();
    }
}