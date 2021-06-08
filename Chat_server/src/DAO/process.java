/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;
import java.util.*;
import java.sql.*;
import DB.DBConnection;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class process {
    private DBConnection db = new DBConnection();
    
    public int Login(String username, String password){
        db.connect();
        String sql = String.format("select * from users where username = '%s' and password = '%s'", username, password);
        ResultSet rs = db.loadDB(sql, db.con);
        int check = 0;


        try {
            if(rs.next()){
                check = 1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(process.class.getName()).log(Level.SEVERE, null, ex);
        }

        return check;
    }
    public int register(String username, String password){
        String sqlkt = String.format("select * from users where username = '%s' and password = '%s'", username, password);
        String sql = String.format("insert into users values('%s', '%s', %d)",username, password, 1);//0 la admin, 1 la user.
        ResultSet rs  = db.loadDB(sql, db.con);
        int check = 0;
        try {
            if(rs.next()){
                check = -1;
            }
            else{
                check = db.Add_Delete_Update(sql, db.con);
            }
        } catch (SQLException ex) {
            Logger.getLogger(process.class.getName()).log(Level.SEVERE, null, ex);
        }
        return check;
    }
}
