package sms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class conn {
    Connection c;
    Statement s;
    conn(){
        String username = "root";
        String password = "Yashingale@2004";
        String url = "jdbc:mysql://localhost:3306/studmonsys";//jdbc = java database connectivity
        try{
          c = DriverManager.getConnection(url, username,password);
          s = c.createStatement();
            System.out.println("Login Successful");

        }catch (Exception e){
            System.out.println("Connection failed");
        }
    }

}
