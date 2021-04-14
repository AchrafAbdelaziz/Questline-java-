/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entity.Categorie;
import entity.Evennement;
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
 * @author Msi
 */
public class EvennementCRUD {
    public void addEvent (Evennement e) throws SQLException{
    try{
        String requete = "INSERT INTO Event (nom,destination,prix,nbr_places)"
                + "VALUES(?,?,?,?)" ;
           PreparedStatement pst =
            new MyConnection().cn.prepareStatement(requete);
    pst.setString(1,e.getNom());
    pst.setString(2,e.getDestination());
     pst.setString(3,Float.toString(e.getPrix()));
      pst.setString(4,String.valueOf(e.getNbrplaces()));
       
        

  
    pst.executeUpdate();
    System.out.println("evennement ajoutée!");




    }
    catch (SQLException ex) {
            Logger.getLogger(CategorieCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
     
     
     
     public ObservableList<Evennement> getEvennement() {

        ObservableList<Evennement> EvennementList = FXCollections.observableArrayList();
        String requete = "SELECT * FROM Event ";
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
                Evennement e;

                while (rs.next()) {
                   Evennement event = new Evennement(rs.getInt("id"), rs.getString("nom"), rs.getString("destination"),rs.getFloat("prix"),rs.getInt("nbr_places"));
               
                    
                    EvennementList.add(event);
                }

            } catch (Exception ex) {
                //System.out.println("AHAYYYAA L7KEEEYAAAAA!!!!");
                ex.printStackTrace();
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategorieCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }

        return EvennementList;
    }
    
}
