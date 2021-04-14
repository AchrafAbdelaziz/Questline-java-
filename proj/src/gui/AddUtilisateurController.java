/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import sservice.UtilisateurCRUD;
import java.net.URL;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import tools.MyConnection;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class AddUtilisateurController implements Initializable {

    @FXML
    private Button ajouterutilisateur;
    @FXML
    private Button afficher;
    @FXML
    private TableView<Utilisateur> tabutil;
    @FXML
    private TableColumn<Utilisateur, Integer> idUtilisateurtab;
    @FXML
    private TableColumn<Utilisateur, String> nomUtilisateurtab;
    @FXML
    private TableColumn<Utilisateur, String> prenomUtilisateurtab;
    @FXML
    private TableColumn<Utilisateur, String> emailUtilisateurtab;
    @FXML
    private TableColumn<Utilisateur, String> mdpUtilisateurtab;
    @FXML
    private Button modifier;
    @FXML
    private Button supprimer;
    int ID;
    UtilisateurCRUD pcr = new UtilisateurCRUD();
    @FXML
    private TextField nomuser;
    @FXML
    private TextField prenomuser;
    @FXML
    private TextField emailuser;
    @FXML
    private TextField mdpuser;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void AjouterUtilisateur(ActionEvent event) {
        try {
            //save Msg in DATA BASE
            String rnom = nomuser.getText();
            String rprenom = prenomuser.getText();
            String remail = emailuser.getText();
            String rmdp = mdpuser.getText();
            Utilisateur user = new Utilisateur(rnom, rprenom, remail, rmdp);
            UtilisateurCRUD userr = new UtilisateurCRUD();
            userr.addUtilisateur(user);
            // Alert 
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setTitle(" Utilisateur!");
            a.setHeaderText(null);
            a.setContentText("Utilsateur ajouté !!!");
            a.showAndWait();

            //*********
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        //showSujet();
        //showSujet();
        AfficherUtilisateur();

    }

    @FXML
    private void AfficherUtilisateur() {
        System.out.println("DEBUGG!!!!");
        ObservableList<Utilisateur> list = pcr.getUtilisateur();
        //   System.out.println(pcr.getSujet().toString());
        //ObservableList<Product> list = FXCollections.observableList(pcd.getProductList());
        idUtilisateurtab.setCellValueFactory(new PropertyValueFactory<Utilisateur, Integer>("id_utilisateur"));
        //idcreateurtab.setCellValueFactory(new PropertyValueFactory<Sujet, String>("id_createur"));
        nomUtilisateurtab.setCellValueFactory(new PropertyValueFactory<Utilisateur, String>("nom"));
        //   dateposttab.setCellValueFactory(new PropertyValueFactory<Sujet, Date>("date_heure_creation"));
        prenomUtilisateurtab.setCellValueFactory(new PropertyValueFactory<Utilisateur, String>("prenom"));
        emailUtilisateurtab.setCellValueFactory(new PropertyValueFactory<Utilisateur, String>("email"));
        mdpUtilisateurtab.setCellValueFactory(new PropertyValueFactory<Utilisateur, String>("mdp"));

        tabutil.setItems(list);
        //search_user();
    }

    /*private void SetValue(MouseEvent event) {
         Utilisateur selected = tabutil.getSelectionModel().getSelectedItem();
        if (selected != null) {
         
          //  String idsujettab = String.valueOf(selected.getId_suj());
            
           // tfprixP.setText(Sprix);
            
            sujettext.setText(selected.getSujet());
            ID = selected.getId_suj();
        }
    }*/
    @FXML
    private void SetValue(MouseEvent event) {
        Utilisateur selected;
        selected = tabutil.getSelectionModel().getSelectedItem();
        if (selected != null) {

            //  String idsujettab = String.valueOf(selected.getId_suj());
            // tfprixP.setText(Sprix);
            nomuser.setText(selected.getNom());
            prenomuser.setText(selected.getPrenom());
            emailuser.setText(selected.getEmail());
            mdpuser.setText(selected.getMdp());

            ID = selected.getId();
            System.out.println(ID);
            System.out.println(selected);

        }
    }

    @FXML
    private void ModifierUtilisateur(ActionEvent event) {
        try {
            String rnom = nomuser.getText();
            String rprenom = prenomuser.getText();
            String remail = emailuser.getText();
            String rmdp = mdpuser.getText();
            String requete = "UPDATE utilisateur SET nom=? , prenom=? , email=? , mdp=?  WHERE id=" + ID + "";
            PreparedStatement pst
                    = new MyConnection().cn.prepareStatement(requete);

            pst.setString(1, rnom);
            pst.setString(2, rprenom);
            pst.setString(3, remail);
            pst.setString(4, rmdp);


            pst.executeUpdate();
            // Alert 
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setTitle(" Sujet!");
            a.setHeaderText(null);
            a.setContentText("Sujet Modifié !!!");
            a.showAndWait();

            //*********
            System.out.println("Message modifié!");
        } catch (SQLException ex) {
            Logger.getLogger(UtilisateurCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        AfficherUtilisateur();
    }

    @FXML
    private void SupprimerUtilisateur(ActionEvent event) {

        try {

            String requete = "DELETE FROM utilisateur WHERE id=" + ID + "";
            PreparedStatement pst
                    = new MyConnection().cn.prepareStatement(requete);
            pst.executeUpdate();
            // Alert 
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setTitle(" Utilisateur!");
            a.setHeaderText(null);
            a.setContentText("Utilisateur supprimé !!!");
            a.showAndWait();

            //*********
            System.out.println("Utilisateur supprimé!");
        } catch (SQLException ex) {
            Logger.getLogger(UtilisateurCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        AfficherUtilisateur();
    }

}
