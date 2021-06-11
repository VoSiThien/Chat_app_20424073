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
    private Register fRegister;
    private HomeClient homeclient = null;
    //private RegisterForm registerView;
    private HashMap<String, formChat> ListFormChat;
    private MethodForm methodform;
    private PrintWriter socketWriter;

    private String CurrentUserName = "";

    public Controller() {
        flogin = new formLogin();
        CurrentUserName = flogin.getUsername();
        fRegister = new Register();
        fRegister.getButtonRegister().addActionListener(this);
        ListFormChat = new HashMap<String, formChat>();
        flogin.getJButtonOpenRegister().addActionListener(this);
        flogin.getButtonLogin().addActionListener(this);
    }

    public void showHomeClient(String currentUser, ArrayList<User> listUser) {
        CurrentUserName = flogin.getUsername();
        homeclient = new HomeClient(currentUser);
        homeclient.setWriter(socketWriter);
        homeclient.ShowHomeClient(this.CurrentUserName, listUser);
        homeclient.getButtonStartChat().addActionListener(this);
    }

    public void reloadTable(ArrayList<User> listUser) {
        homeclient.reloadTable(listUser);
    }
    
    public HomeClient getHomeClient() {
        return homeclient;
    }

    public void addNewFormChat(String UserName) {
        if(!ListFormChat.containsKey(UserName)){
            formChat formchat = new formChat(UserName);
            formchat.getbuttonSend().addActionListener(this);
            ListFormChat.put(UserName, formchat);
        }
        else{
            formChat formchat = ListFormChat.get(UserName);
            formchat.setVisible(true);
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (ListFormChat.size() != 0 && e.getActionCommand().startsWith("SendMessage")) {
            String[] SplitCommand = e.getActionCommand().split("\\-");
            String Username = SplitCommand[1];
            formChat formchat = ListFormChat.get(Username);
            methodform = new MethodForm(formchat);
            methodform.AddNewMessage();
            socketWriter.println(1);

            socketWriter.println(Username);
            socketWriter.println(methodform.GetMessage());
            methodform.ClearText();
        }
        if (e.getActionCommand().startsWith("OpenChatForm")) {
            String[] SplitCommand = e.getActionCommand().split("\\-");
            String Username = SplitCommand[1];
            if(Username.equals("")){
                homeclient.CannotSelect();
            }
            else{
                formChat formchat = new formChat(Username);
                formchat.getbuttonSend().addActionListener(this);
                ListFormChat.put(Username, formchat);
            }
        }
        if (e.getActionCommand().equals("JButtonlogin")){
            String Uname = flogin.getUsername();
            String Pass = flogin.getPassword();

            socketWriter.println("--Login");
            socketWriter.println(Uname);
            socketWriter.println(Pass);
        }
        if (e.getActionCommand().equals("JButtonOpenRegister")) {
            
            fRegister.setFormLogin(flogin);
            fRegister.setVisible(true);
            flogin.setVisible(false);
        }
        if(e.getActionCommand().equals("JButtonRegister")){
            String Uname = fRegister.getUsername();
            String Pass = fRegister.getPassword();
            String ComfirmPass = fRegister.getComfirmPassword();
            if(Uname.equals("") || Pass.equals("") || ComfirmPass.equals("")){
                fRegister.Notification(-2);
            }else{
                if(ComfirmPass.equals(Pass)){

                    socketWriter.println("--Register");
                    socketWriter.println(Uname);
                    socketWriter.println(Pass);
                }
                else{
                    fRegister.Notification(-1);
                }
            }
        }
    }

    public HashMap<String, formChat> getListFormChat() {
        return ListFormChat;
    }

    public String getCurrentUserName() {
        return CurrentUserName;
    }

    public void Notification(int check, String CAction){
        if(CAction.equals("login")){
            flogin.Notification(check);
        }else{
            fRegister.Notification(check);
        }
    }
    public void setSocketWriter(PrintWriter socketWriter) {
        this.socketWriter = socketWriter;
        flogin.setWriter(socketWriter);
    }

    public void setCurrentUserName(String CurrentUserName) {
        this.CurrentUserName = CurrentUserName;
    }

    public void ViewMessage(String UserName, String message) {
        formChat chatView = ListFormChat.get(UserName);
        MethodForm methodform = new MethodForm(chatView);
        methodform.AddNewMessage();
    }
}
