/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Destination;
import entities.Vol;
import java.net.URL;
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
import services.DestCRUD;
import services.VolCRUD;
import tools.MyConnection;

/**
 * FXML Controller class
 *
 * @author AZIZ AKARI
 */
public class AddDestinationController implements Initializable {

    @FXML
    private TextField iddest;
    @FXML
    private TextField nomdest;
    @FXML
    private TableView<Destination> desttab;
    @FXML
    private TableColumn<Destination, Integer> iddesttab;
    @FXML
    private TableColumn<Destination, String> nomdesttab;
    @FXML
    private Button btnajouter;
    @FXML
    private Button btnafficher;
       int ID;
    DestCRUD pcr= new DestCRUD();
    @FXML
    private Button btnmodifier;
    @FXML
    private Button btnsuppimer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AjouterDest(ActionEvent event) {
         try
        {
       int rid = Integer.parseInt(iddest.getText());
       String rnom = nomdest.getText();

        Destination dest= new Destination(rid,rnom);
        DestCRUD destt= new DestCRUD();
        destt.addDestination(dest);
        // Alert 
            Alert 
              a = new Alert(Alert.AlertType.WARNING); 
              a.setTitle(" Destination!");
              a.setHeaderText(null);
              a.setContentText("Destination ajouté !!!");
              a.showAndWait();
            
            //*********
            
        
        }  catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
             //showSujet();
                       //showSujet();
        
    }

    @FXML
    private void AfficherDest() {
           System.out.println("DEBUGG!!!!");
        ObservableList<Destination> list =pcr.getDest();
        iddesttab.setCellValueFactory(new PropertyValueFactory<Destination, Integer>("id"));
        nomdesttab.setCellValueFactory(new PropertyValueFactory<Destination, String>("nom"));
    
        desttab.setItems(list);
        
    }

    @FXML
    private void SetValue(MouseEvent event) {
          Destination selected = desttab.getSelectionModel().getSelectedItem();
        if (selected != null) {
       
            
           iddest.setText(String.valueOf(selected.getId()));
            nomdest.setText(String.valueOf(selected.getNom()));
            ID = selected.getId();
            System.out.println(ID);

        }
        
    }

    @FXML
    private void ModifierDest(ActionEvent event) {
                 try {
        int rid = Integer.parseInt(iddest.getText());
        String rnom = nomdest.getText();

             String requete = "UPDATE destination SET id=? ,nom=?  WHERE id="+ID+"";
            PreparedStatement pst
                    = new MyConnection().cn.prepareStatement(requete);
            
           pst.setInt(1,rid);
           pst.setString(2,rnom);
            pst.executeUpdate();
            // Alert 
            Alert 
              a = new Alert(Alert.AlertType.WARNING); 
              a.setTitle(" Destination!");
              a.setHeaderText(null);
              a.setContentText("Destination Modifié !!!");
              a.showAndWait();
            
            //*********
            
            System.out.println("Destination modifié!");
        } catch (SQLException ex) {
            Logger.getLogger(VolCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
          AfficherDest();
    }

    @FXML
    private void SupprimerDest() {
                try {
            String requete = "DELETE FROM destination WHERE id="+ID+"";
            PreparedStatement pst
                    = new MyConnection().cn.prepareStatement(requete);
            pst.executeUpdate();
            // Alert 
            Alert 
              a = new Alert(Alert.AlertType.WARNING); 
              a.setTitle(" Destination!");
              a.setHeaderText(null);
              a.setContentText("Destination supprimé !!!");
              a.showAndWait();
            
            //*********
            
            System.out.println("Destination supprimé!");
        } catch (SQLException ex) {
            Logger.getLogger(VolCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
          AfficherDest(); 
        
    }
    
}
