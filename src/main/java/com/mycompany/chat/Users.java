package com.mycompany.chat;

import java.util.Date;
import org.json.simple.JSONAware;
import org.json.simple.JSONObject;

public class Users implements JSONAware {

    private Long id;
    private String login;
    private String pass;

    public Users(Long id, String login, String pass) {
        this.id = id;
        this.login = login;
        this.pass = pass;
    }
    
    public Long getId() {
        return id;
    }

    public String getLOGIN() {
        return login;
    }
    
    public String getPASS(){
        return pass;
    }

    @Override
    public String toJSONString() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
