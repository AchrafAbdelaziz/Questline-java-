/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import entities.CurrentUser;
import entities.Utilisateur;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import sservice.UtilisateurCRUD;
import tools.MyConnection;
import tools.VoiceUtils;


/**
 * FXML Controller class
 *
 * @author asus
 */
public class ProfilController implements Initializable {

    @FXML
    private JFXTextField nomuser;
    @FXML
    private JFXTextField prenomuser;
    @FXML
    private JFXTextField emailuser;
    @FXML
    private JFXTextField mdpuser;
    @FXML
    private JFXButton btnconfirmer;
    @FXML
    private JFXButton btnannuler;
    UtilisateurCRUD us = new UtilisateurCRUD();
    Utilisateur u = new Utilisateur();
    int ID;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        u = us.FindUserByID(Integer.parseInt(Session.connectedUser.getEmail()));//Selon user connecter
        System.out.println("profil sout Session.connecteduser.getgetid"+Session.connectedUser.getId());
        System.out.println("profil sout Session.connecteduser.getemail"+Session.connectedUser.getEmail());
        System.out.println("profil sout u.getEmail"+u.getEmail());
        nomuser.setText(u.getNom());
        prenomuser.setText(u.getPrenom());
        emailuser.setText(u.getEmail());
        mdpuser.setText(u.getMdp());
    }    

    @FXML
    private void UpdateProfil(ActionEvent event) throws IOException {
        u = us.FindUserByID(CurrentUser.id);
        System.out.println("current usr id  = "+CurrentUser.id);
        System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
        VoiceUtils v =new VoiceUtils("kevin16");
     try {
        u.setNom(nomuser.getText());
        u.setPrenom(prenomuser.getText());
        u.setEmail(emailuser.getText());
        u.setMdp(mdpuser.getText());
        
         String requete = "UPDATE utilisateur SET nom=? , prenom=? , email=? , mdp=?   WHERE id=" + ID + "";
            PreparedStatement pst
                    = new MyConnection().cn.prepareStatement(requete);

            pst.setString(1, u.getNom());
            pst.setString(2, u.getPrenom());
            pst.setString(3, u.getEmail());
            pst.setString(4, u.getMdp());
//            pst.setString(5, rimg);


            pst.executeUpdate();
            // Alert 
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setTitle(" Utilisateur!");
            a.setHeaderText(null);
            a.setContentText("User has been Modified !!!");
            a.showAndWait();

            //*********
            System.out.println("Message modifié!");
        } catch (SQLException ex) {
            Logger.getLogger(UtilisateurCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String[] msg={"User has been modified"};
        v.sayMultiple(msg);
        
       
        Stage primary = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root2 = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene scene2 = new Scene(root2);    
        primary.setTitle("Login!");
        primary.setScene(scene2);
        primary.show();
                
        
    }

    @FXML
    private void retour(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("HomeUser.fxml"));
            Parent root=loader.load();
            btnannuler.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
//        private void ModifierUtilisateur(ActionEvent event) {
//        try {
//            String rnom = nomuser.getText();
//            String rprenom = prenomuser.getText();
//            String remail = emailuser.getText();
//            String rmdp = mdpuser.getText();
//            String rimg = fileName;
//            String requete = "UPDATE utilisateur SET nom=? , prenom=? , email=? , mdp=? , img=?  WHERE id=" + ID + "";
//            PreparedStatement pst
//                    = new MyConnection().cn.prepareStatement(requete);
//
//            pst.setString(1, rnom);
//            pst.setString(2, rprenom);
//            pst.setString(3, remail);
//            pst.setString(4, rmdp);
//            pst.setString(5, rimg);
//
//
//            pst.executeUpdate();
//            // Alert 
//            Alert a = new Alert(Alert.AlertType.WARNING);
//            a.setTitle(" Utilisateur!");
//            a.setHeaderText(null);
//            a.setContentText("Utilisateur Modifié !!!");
//            a.showAndWait();
//
//            //*********
//            System.out.println("Message modifié!");
//        } catch (SQLException ex) {
//            Logger.getLogger(UtilisateurCRUD.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    
}
