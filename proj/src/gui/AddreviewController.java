/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.CurrentUser;
import sservice.ReviewCRUD;
import java.net.URL;
import entities.Review;
import entities.Utilisateur;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import org.controlsfx.control.Rating;
import sservice.UtilisateurCRUD;
import tools.MyConnection;



/**
 * FXML Controller class
 *
 * @author asus
 */
public class AddreviewController implements Initializable {

    
    @FXML
    private TableColumn<Review, Integer> idReviewtab;
    @FXML
    private TableColumn<Review, String> commentReviewtab;
    @FXML
    private TableColumn<Review, Integer> ratingReviewtab;
    @FXML
    private TextArea commentrev;
    @FXML
    private TextField ratingrev;
    @FXML
    private Button btnajout;
    @FXML
    private Button btnafficher;
    int ID;
    ReviewCRUD pcr = new ReviewCRUD();
    @FXML
    private Button btnmodifier;
    @FXML
    private Button btnsupp;
    @FXML
    private TableView<Review> reviewtab;
    @FXML
    private Rating stars;
    UtilisateurCRUD us = new UtilisateurCRUD();
    Utilisateur u = new Utilisateur();

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AjouterReview() {
     try {
         u = us.FindUserByID(Integer.parseInt(Session.connectedUser.getEmail()));
         //System.out.println("current usr id  = "+CurrentUser.id);
         System.out.println("u.getid"+u.getId());
         System.out.println("u.getemail"+u.getEmail());
            //save Msg in DATA BASE
            int rid = u.getId();
            String rcom  = commentrev.getText();
            int rrat = (int) stars.getRating();
            Review rev = new Review(rcom, rrat,rid);
            ReviewCRUD revv = new ReviewCRUD();
            revv.addReview(rev);
            // Alert 
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setTitle(" Review!");
            a.setHeaderText(null);
            a.setContentText("Review ajouté !!!");
            a.showAndWait();

            //*********
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        AfficherReview();

    }
    

    @FXML
    private void AfficherReview() {
        try{
      System.out.println("DEBUGG!!!!");
        ObservableList<Review> lisst = pcr.getReview();
        idReviewtab.setCellValueFactory(new PropertyValueFactory<Review,Integer>("id_review"));
        commentReviewtab.setCellValueFactory(new PropertyValueFactory<Review,String>("comment"));
        ratingReviewtab.setCellValueFactory(new PropertyValueFactory<Review,Integer>("rating"));

   
        
        reviewtab.setItems(lisst);
     } catch (NullPointerException ex) {
            System.out.println(ex.getMessage());
        }
        }
    @FXML
private void SetValue(MouseEvent event) {
        Review selected;
        selected = reviewtab.getSelectionModel().getSelectedItem();
        if (selected != null) {

        
            ratingrev.setText(String.valueOf(selected.getRating()));
            commentrev.setText(selected.getComment());
            

            ID = selected.getId();
            System.out.println(ID);
            System.out.println(selected);

        }
    }
    @FXML
    private void ModifierReview() {
        try {
            int rrat = Integer.parseInt(ratingrev.getText());
            String rcom = commentrev.getText();
            String requete = "UPDATE review SET comment=? , rating=?   WHERE id=" + ID + "";
            PreparedStatement pst
                    = new MyConnection().cn.prepareStatement(requete);

            pst.setString(1, rcom);
            pst.setInt(2, rrat);
            


            pst.executeUpdate();
            // Alert 
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setTitle(" Review!");
            a.setHeaderText(null);
            a.setContentText("Review Modifié !!!");
            a.showAndWait();

            //*********
            System.out.println("Review modifié!");
        } catch (SQLException ex) {
            Logger.getLogger(ReviewCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        AfficherReview();
    }
    

    @FXML
    private void SupprimerReview() {
        try {

            String requete = "DELETE FROM review WHERE id=" + ID + "";
            PreparedStatement pst
                    = new MyConnection().cn.prepareStatement(requete);
            pst.executeUpdate();
            // Alert 
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setTitle(" Review!");
            a.setHeaderText(null);
            a.setContentText("Review supprimé !!!");
            a.showAndWait();

            //*********
            System.out.println("Review supprimé!");
        } catch (SQLException ex) {
            Logger.getLogger(ReviewCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        AfficherReview();
    }
//private boolean testComment() {
//     
//        int nbNonChar = 0;
//        for (int i = 1; i < commentrev.getText().trim().length(); i++) {
//            char ch = commentrev.getText().charAt(i);
//            if (!Character.isLetter(ch)) {
//                nbNonChar++;
//            }
//        }
//
//        if (nbNonChar == 0 && nomuser.getText().trim().length() >= 3) {
//          nomcheck.setImage(new Image("Image/checkmark.png"));
//            return true;
//        } else {
//           nomcheck.setImage(new Image("Image/alertemark.png"));
//               
//            return false;
//
//        }
//        
//    }
    }
    

