/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Process;

import GUI.formChat;
public class MethodForm {
    private formChat chatView;
    public MethodForm(formChat chatview){
        this.chatView = chatview;
    }
    public void AddNewMessage() {
        chatView.gettextAllmessage().append("Me : " + this.GetAddedMessage() + "\n");
        chatView.gettextAllmessage().setCaretPosition(chatView.gettextAllmessage().getDocument().getLength());
    }
    public void DisplayRecievedMessage(String message){
        chatView.gettextAllmessage().append(message);
        chatView.gettextAllmessage().setCaretPosition(chatView.gettextAllmessage().getDocument().getLength());
    }
    public String GetAddedMessage(){
        return chatView.getTextMessage().getText();
    }
    public void SetBlankForAddedMessageField(){
        chatView.getTextMessage().setText("");
    }
}
