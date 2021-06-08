/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import java.util.*;
import java.sql.*;

public class DBConnection {
    public Connection con = null;
    public Connection connect() {

        try {
            String user = "sa";
            String password = "";
            String db = "qlchat";
            int port = 1433;
            SQLServerDataSource ds = new SQLServerDataSource();
            ds.setUser(user);
            ds.setPassword(password);
            ds.setDatabaseName(db);
            ds.setPortNumber(port);
            con = ds.getConnection();
            return con;
            
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return null;
    }
    public int Add_Delete_Update(String sql, Connection connect)
    {
        if(connect !=null)
        {
            try {
                Statement statement = connect.createStatement();
                int rs = statement.executeUpdate(sql);
                return rs;
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return -1;
    }
    public ResultSet loadDB(String sql, Connection connect)
    {
        ResultSet rs = null;
        if(connect !=null) {
            try {
                Statement statement = connect.createStatement();
                rs = statement.executeQuery(sql);

            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
        }
        return rs;
    }
}
