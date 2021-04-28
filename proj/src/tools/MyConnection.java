/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import entities.Utilisateur;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author asus
 */
public class MyConnection {
     public String url="jdbc:mysql://localhost:3306/impact";
    public String login="root";
    public String pwd="";
    public Connection cn;
    private static MyConnection insatance = null;

    public MyConnection() {
        try {
          cn = DriverManager.getConnection(url, login, pwd);
            System.out.println("Connexion établie!");
        } catch (SQLException ex) {
            System.out.println("Erreur de connexion");
            System.out.println(ex.getMessage());
            
        }
        
    }
    public Connection getConnection() {
        return cn;
    }
    public static MyConnection getInsatance() {
        if (insatance == null) {
            insatance = new MyConnection();
        }
        return insatance;
    }
        public PreparedStatement prepareStatement(String query) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
