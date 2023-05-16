package com.example.ucenicka_sluzba;

import java.io.File;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {
    Connection conn = null;
    private static Connector instance = null;

    public static synchronized Connector getInstance() {
        if(instance == null) {
            instance = new Connector();
        }
        return instance;
    }

    public Connector() {
    }

    public void createDatabase(){
       try{
           conn = DriverManager.getConnection(URLDecoder.decode("jdbc:sqlite:ucenicka_sluzba.db", StandardCharsets.UTF_8));
       }catch(SQLException se){
           se.printStackTrace();
       }
    }
    public void checkIfExists(String filePath){
        File database = new File(filePath);
        if (!database.exists() || database.isDirectory()) {
            createDatabase();
        }
    }

    public Connection getConn() {
        return conn;
    }


}
