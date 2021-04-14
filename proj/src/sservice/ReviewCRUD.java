/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sservice;

import entities.Review;
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
public class ReviewCRUD {
    
    
    public void addReview (Review rev) throws SQLException{
    try{
        String requete = "INSERT INTO review (comment,rating)"
                + "VALUES(?,?)" ;
           PreparedStatement pst =
            new MyConnection().cn.prepareStatement(requete);
    pst.setString(1,rev.getComment());
    pst.setInt(2,rev.getRating());

  
    pst.executeUpdate();
    System.out.println("Review  ajoutée!");




    }
    catch (SQLException ex) {
            Logger.getLogger(Review.class.getName()).log(Level.SEVERE, null, ex);
        }
    }








public ObservableList<Review> getReview() {

        ObservableList<Review> ReviewList = FXCollections.observableArrayList();
        String requete = "SELECT * FROM review ";
        try {
            PreparedStatement pst
                    = new MyConnection().cn.prepareStatement(requete);
         
            ResultSet rs;
            try {
                
                rs = pst.executeQuery(requete);
               

                while (rs.next()) {
                   Review rev = new Review(rs.getInt("id"),rs.getString("comment"),rs.getInt("rating"));
                    ReviewList.add(rev);
                }

            } catch (Exception ex) {
               
                ex.printStackTrace();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReviewCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ReviewList;
    }








}
