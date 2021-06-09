/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat_server;

/**
 *
 * @author Admin
 */
public class User {
    private static final long serialVersionUID = 6529685098267757690L;
    private String name;
    private String ip;
    private String port;
    private String status;
    public User(){}
    public User(String name, String ip, String port, String status) {
        this.name = name;
        this.ip = ip;
        this.port = port;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public String getIp() {
        return ip;
    }

    public String getPort() {
        return port;
    }

    public String getStatus() {
        return status;
    }
}
