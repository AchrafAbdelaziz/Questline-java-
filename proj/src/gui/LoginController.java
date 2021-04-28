/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import entities.Utilisateur;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import sservice.UtilisateurCRUD;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class LoginController implements Initializable {

    @FXML
    private JFXButton btnlog;
    @FXML
    private JFXTextField email;
    @FXML
    private JFXPasswordField password;
    @FXML
    private Label msgerreur;
    @FXML
    private JFXButton btnins;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        msgerreur.setVisible(false);
        
    }    

    @FXML
    private void authentifier(ActionEvent event) throws IOException {
        String  rolex ;
        UtilisateurCRUD us = new UtilisateurCRUD();
        Utilisateur u = new Utilisateur();
        u.setEmail(email.getText());
        u.setMdp(password.getText());
        System.out.println("u.getEmail"+u.getEmail());
        System.out.println(password.getText());
        
        rolex = us.Login(u);
     
        if(rolex != "erreur"){
        
        if((email.getText()!=null)&&(password.getText()!=null)){
            
                        Session.connectedUser = u ;
                        System.out.println("session.connected = u  "+Session.connectedUser.getEmail());
                        System.out.println("connected userrrrrrrrrrr"+Session.connectedUser.getEmail());
        if( u.getRole().equals("admin"))
        {
            System.out.println("user id is : " + u.getEmail());
            
            Stage primary = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Parent root2 = FXMLLoader.load(getClass().getResource("HomeAdmin.fxml"));
            Scene scene2 = new Scene(root2);
            primary.setTitle("Admin!");
            primary.setScene(scene2);
            primary.show();
        }
        else if( u.getRole().equals("user")){
        System.out.println("user id is : " + u.getEmail());
            
            Stage primary = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Parent root2 = FXMLLoader.load(getClass().getResource("HomeUser.fxml"));
            Scene scene2 = new Scene(root2);
            primary.setTitle("User!");
            primary.setScene(scene2);
            primary.show();
        }
        else{
            System.out.println("ereur dans login controller ");}
        }
    
    }else{
            msgerreur.setVisible(true);
        }
}

    @FXML
    private void sign(ActionEvent event) {
          try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Inscription.fxml"));
            Parent root=loader.load();
            btnins.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(InscriptionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
