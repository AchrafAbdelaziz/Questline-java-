/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Samar
 */
public class MyConnection {
    private static MyConnection instance;
     public String url="jdbc:mysql://localhost:3306/aziz";
    public String login="root";
    public String pwd="";
    public Connection cn;

    public MyConnection() {
        try {
          cn = DriverManager.getConnection(url, login, pwd);
            System.out.println("Connexion établie!");
        } catch (SQLException ex) {
            System.out.println("Erreur de connexion");
            System.out.println(ex.getMessage());
            
        }
        
    }
    public static MyConnection getInstance(){
        if(instance==null){
            instance= new MyConnection();
        }
        return instance;
    }
    public Connection getConnection(){
        return cn ;
    }
}
