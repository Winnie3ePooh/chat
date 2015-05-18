package com.mycompany.chat;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class UsersFunc {
    
    private static final String dbUrl = "jdbc:h2:~/test1";

    public UsersFunc() {
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ContactList.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static void closeQuietly(Connection closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (SQLException ex) {
                // ignore
            }
        }
    }

    public JSONObject createDB() {
        Connection conn = null;
        JSONObject result = null;
        try {
            conn = DriverManager.getConnection(dbUrl);
            Statement st = conn.createStatement();
            st.execute("create table users(id INT PRIMARY KEY AUTO_INCREMENT, login varchar(255),"
                    + "pass varchar(255))");
            
            result = new JSONObject();
        } catch (SQLException ex) {
            Logger.getLogger(ContactList.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeQuietly(conn);
        }
        return result;
    }

    public boolean checking(String loginCheck, String passCheck) {
        boolean check = false;
        Connection conn = null;
        JSONArray list = null;
        try {
            conn = DriverManager.getConnection(dbUrl);
            
            Statement st = conn.createStatement();
            ResultSet result = st.executeQuery("SELECT * FROM USERS");
            while (result.next()) {
                if(result.getString("LOGIN").equals(loginCheck) && result.getString("PASS").equals(passCheck)){
                    check = true;
                    break;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ContactList.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeQuietly(conn);
        }
        return check;
    }

    public JSONObject add(Users c) {
        Connection conn = null;
        JSONObject result = null;
        try {
            conn = DriverManager.getConnection(dbUrl);
            
            String q = "INSERT INTO USERS(login,pass) VALUES(?,?)";
            PreparedStatement st = conn.prepareStatement(q);

            st.setString(1, c.getLOGIN());
            st.setString(2, c.getPASS());
            st.execute();
            
            long id = -1;
            ResultSet rs = st.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
            }
            
            result = new JSONObject();
            result.put("id", id);
        } catch (SQLException ex) {
            Logger.getLogger(ContactList.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeQuietly(conn);
        }
        return result;
    } 
    
}
