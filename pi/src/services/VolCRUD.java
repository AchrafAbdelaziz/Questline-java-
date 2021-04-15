/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

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
public class VolCRUD {
    
    public void addVol (Vol v) throws SQLException{
    try{
        String requete = "INSERT INTO vol (prix,nbplace)"
                + "VALUES(?,?)" ;
           PreparedStatement pst =
            new MyConnection().cn.prepareStatement(requete);
    pst.setInt(1,v.getPrix());
    pst.setInt(2,v.getNbplace());  
    pst.executeUpdate();
    System.out.println("Vol ajoutée!");




    }
    catch (SQLException ex) {
            Logger.getLogger(VolCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
         public ObservableList<Vol> getVol(){

        ObservableList<Vol> VolList = FXCollections.observableArrayList();
        String requete = "SELECT * FROM vol ";
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
                   Vol v = new Vol(rs.getInt("id"), rs.getInt("prix"), rs.getInt("nbplace"));
               //   Sujet suj = new Sujet(rs.getString("id_suj"));
                    VolList.add(v);
                }

            } catch (Exception ex) {
                //System.out.println("AHAYYYAA L7KEEEYAAAAA!!!!");
                ex.printStackTrace();
            }
        } catch (SQLException ex) {
            Logger.getLogger(VolCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }

        return VolList;
    }
        }
