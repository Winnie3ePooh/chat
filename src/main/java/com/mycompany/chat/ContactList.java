package com.mycompany.chat;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class ContactList {
    
    private static final String dbUrl = "jdbc:h2:~/test1";

    public ContactList() {
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
    
    /**
     * Создать БД.
     * @return в случае успеха возвражает пустой JSON объект, иначе null.
     * st.execute("DROP TABLE PUBLIC.PEOPLE");
     */
    public JSONObject createDB() {
        Connection conn = null;
        JSONObject result = null;
        try {
            conn = DriverManager.getConnection(dbUrl);
            Statement st = conn.createStatement();
            st.execute("create table people(id INT PRIMARY KEY AUTO_INCREMENT, nick varchar(255),"
                    + "mess varchar(255))");
            
            result = new JSONObject();
        } catch (SQLException ex) {
            Logger.getLogger(ContactList.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeQuietly(conn);
        }
        return result;
    }

    public JSONArray list() {
        Connection conn = null;
        JSONArray list = null;
        try {
            conn = DriverManager.getConnection(dbUrl);
            
            Statement st = conn.createStatement();
            ResultSet result = st.executeQuery("SELECT * FROM (SELECT * FROM PEOPLE ORDER BY id DESC LIMIT 10) ORDER BY id");
            
            list = new JSONArray();
            while (result.next()) {
                Contact c = new Contact(result.getLong("ID"),
                        result.getString("NICK"),result.getString("MESS"));
                list.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ContactList.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeQuietly(conn);
        }
        return list;
    }

    public JSONObject add(Contact c) {
        Connection conn = null;
        JSONObject result = null;
        try {
            conn = DriverManager.getConnection(dbUrl);
            
            String q = "INSERT INTO PEOPLE(nick,mess) VALUES(?,?)";
            PreparedStatement st = conn.prepareStatement(q);

            st.setString(1, c.getNICK());;
            st.setString(2, c.getMESS());
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
