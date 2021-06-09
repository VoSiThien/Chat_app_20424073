/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Process;

import GUI.formChat;
public class MethodForm {
    private formChat formchat;
    public MethodForm(formChat formchat){
        this.formchat = formchat;
    }
    public void AddNewMessage() {
        formchat.gettextAllmessage().append("Me : " + this.GetAddedMessage() + "\n");
        formchat.gettextAllmessage().setCaretPosition(formchat.gettextAllmessage().getDocument().getLength());
    }
    public void DisplayRecievedMessage(String message){
        formchat.gettextAllmessage().append(message);
        formchat.gettextAllmessage().setCaretPosition(formchat.gettextAllmessage().getDocument().getLength());
    }
    public String GetAddedMessage(){
        return formchat.getTextMessage().getText();
    }
    public void SetBlankForAddedMessageField(){
        formchat.getTextMessage().setText("");
    }
}
