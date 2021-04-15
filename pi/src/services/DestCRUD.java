/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Destination;
import entities.Vol;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
     public void addDestination (Destination dest) throws SQLException{
    try{
        String requete = "INSERT INTO destination (id,nom)"
                + "VALUES(?,?)" ;
           PreparedStatement pst =
            new MyConnection().cn.prepareStatement(requete);
    pst.setInt(1,dest.getId());
    pst.setString(2,dest.getNom());  
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
    
}
