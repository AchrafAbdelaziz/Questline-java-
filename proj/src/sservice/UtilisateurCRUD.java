/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sservice;

import entities.Utilisateur;
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
 * @author asus
 */
public class UtilisateurCRUD {
   public void addUtilisateur (Utilisateur user) throws SQLException{
    try{
        String requete = "INSERT INTO utilisateur (nom,prenom,email,mdp)"
                + "VALUES(?,?,?,?)" ;
           PreparedStatement pst =
            new MyConnection().cn.prepareStatement(requete);
    pst.setString(1,user.getNom());
    pst.setString(2,user.getPrenom());
    pst.setString(3,user.getEmail());
    pst.setString(4,user.getMdp());
  
    pst.executeUpdate();
    System.out.println("Utilisateur  ajoutée!");




    }
    catch (SQLException ex) {
            Logger.getLogger(Utilisateur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
public ObservableList<Utilisateur> getUtilisateur() {

        ObservableList<Utilisateur> UtilisateurList = FXCollections.observableArrayList();
        String requete = "SELECT * FROM utilisateur ";
        try {
            PreparedStatement pst
                    = new MyConnection().cn.prepareStatement(requete);
         
            ResultSet rs;
            try {
                
                rs = pst.executeQuery(requete);
               

                while (rs.next()) {
                   Utilisateur user = new Utilisateur(rs.getInt("id"),rs.getString("nom"),rs.getString("prenom"),rs.getString("email"),rs.getString("mdp"));
                    UtilisateurList.add(user);
                }

            } catch (Exception ex) {
               
                ex.printStackTrace();
            }
        } catch (SQLException ex) {
            Logger.getLogger(UtilisateurCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }

        return UtilisateurList;
    }







}