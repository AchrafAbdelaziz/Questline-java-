/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Destination;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tools.MyConnection;

/**
 *
 * @author AZIZ AKARI
 */
public class DestCRUD {
    Connection cn;
    public DestCRUD() {
        cn=MyConnection.getInstance().getConnection();
    }
     public void addDestination (Destination dest) throws SQLException{
    try{
        String requete = "INSERT INTO destination (nom)"
                + "VALUES(?)" ;
           PreparedStatement pst =
            new MyConnection().cn.prepareStatement(requete);
    pst.setString(1,dest.getNom());  
    pst.executeUpdate();
    System.out.println("Destination ajoutée!");




    }
    catch (SQLException ex) {
            Logger.getLogger(DestCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
         public ObservableList<Destination> getDest(){

        ObservableList<Destination> DestinationList = FXCollections.observableArrayList();
        String requete = "SELECT * FROM destination ";
        try {
            PreparedStatement pst
                    = new MyConnection().cn.prepareStatement(requete);
            //Statement st;
            ResultSet rs;
            try {
                //System.out.println("AHAYYYAA!!!!");
                //st=conn.createStatement();
                //System.out.println("AHAYYYAA222!!!!");
                rs = pst.executeQuery(requete);

                while (rs.next()) {
                   Destination dest = new Destination(rs.getInt("id"), rs.getString("nom"));
                    DestinationList.add(dest);
                }

            } catch (Exception ex) {
                //System.out.println("AHAYYYAA L7KEEEYAAAAA!!!!");
                ex.printStackTrace();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DestCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }

        return DestinationList;
    }
    public List<Destination> DestinationList() {
        
        List<Destination> myList = new ArrayList<>();
        try { // LES var declaré dans le try ne sont vue que dans le try, et inversement pour en dhors du try
            String requete = "SELECT * from destination"; //MAJUSCULE NON OBLIGATOIRE 
            
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                  Destination dest = new Destination();
                dest.setId(rs.getInt(1));
                dest.setNom(rs.getString("Nom"));
                
                myList.add(dest);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return myList;

    }
    
}
