/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.ReservationVol;
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
public class ResCRUD {
    Connection cn;
    public ResCRUD() {
        cn=MyConnection.getInstance().getConnection();
    }
     public void addReservationVol (ReservationVol res) throws SQLException{
    try{
        String requete = "INSERT INTO reservation_vol (nombre_personne,date)"
                + "VALUES(?)" ;
           PreparedStatement pst =
            new MyConnection().cn.prepareStatement(requete);
    pst.setInt(1,res.getId());
    pst.setInt(2,res.getNombre_personne());
    pst.setDate(3,res.getDate_res());

    pst.executeUpdate();
    System.out.println("ReservationVol ajoutée!");




    }
    catch (SQLException ex) {
            Logger.getLogger(ResCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
         public ObservableList<ReservationVol> getRes(){

        ObservableList<ReservationVol> ReservationVolList = FXCollections.observableArrayList();
        String requete = "SELECT * FROM reservation_vol ";
        try {
            PreparedStatement pst
                    = new MyConnection().cn.prepareStatement(requete);
            //Statement st;
            ResultSet rs;
            try {
                rs = pst.executeQuery(requete);

                while (rs.next()) {
                   ReservationVol res = new ReservationVol(rs.getInt("id"), rs.getInt("nombre_personne"), rs.getDate("date_res"), rs.getString("vols_id"));
                    ReservationVolList.add(res);
                }

            } catch (Exception ex) {
                //System.out.println("AHAYYYAA L7KEEEYAAAAA!!!!");
                ex.printStackTrace();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DestCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ReservationVolList;
    }
  
}
