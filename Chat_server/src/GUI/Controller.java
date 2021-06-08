/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import DAO.process;
public class Controller {
    private process p = new process();
    public int login(String username, String password){
        int check = p.Login(username, password);
        return check;
    }
}
