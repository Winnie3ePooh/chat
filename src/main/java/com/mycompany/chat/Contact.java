package com.mycompany.chat;

import java.util.Date;
import org.json.simple.JSONAware;
import org.json.simple.JSONObject;

public class Contact implements JSONAware {

    private Long id;
    private String nick;
    private String mess;

    public Contact(Long id, String nick, String mess) {
        this.id = id;
        this.nick = nick;
        this.mess = mess;
    }
    
    public Long getId() {
        return id;
    }

    public String getNICK() {
        return nick;
    }
    
    public String getMESS(){
        return mess;
    }

    @Override
    public String toJSONString() {
        StringBuilder sb = new StringBuilder();

        sb.append("{");

        sb.append("\"").append(JSONObject.escape("id")).append("\"");
        sb.append(":");
        sb.append(id);

        sb.append(",");
        
        sb.append("\"").append("nick").append("\"");
        sb.append(":");
        sb.append("\"").append(JSONObject.escape(nick)).append("\"");

        sb.append(",");
        
        sb.append("\"").append("mess").append("\"");
        sb.append(":");
        sb.append("\"").append(JSONObject.escape(mess)).append("\"");
        
        sb.append("}");

        return sb.toString();
    }
}
