/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

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
import services.VolCRUD;
import tools.MyConnection;

/**
 * FXML Controller class
 *
 * @author AZIZ AKARI
 */
public class AddVolController implements Initializable {

    @FXML
    private TextField prixvol;
    @FXML
    private TextField nbplacevol;
    @FXML
    private TableView<Vol> voltab;
    @FXML
    private TableColumn<Vol, Integer> prixVoltab;
    @FXML
    private TableColumn<Vol, Integer> nbplaceVoltab;
    @FXML
    private Button btnajouter;
    @FXML
    private Button btnafficher;
    int ID;
    VolCRUD pcr= new VolCRUD();
    @FXML
    private Button btnmodifier;
    @FXML
    private Button btnsupprimer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AjouterVol(ActionEvent event) {
         try
        {
       int rprix = Integer.parseInt(prixvol.getText());
       int rnbplace = Integer.parseInt(nbplacevol.getText());

        Vol v= new Vol(rprix,rnbplace);
        VolCRUD vv= new VolCRUD();
        vv.addVol(v);
        // Alert 
            Alert 
              a = new Alert(Alert.AlertType.WARNING); 
              a.setTitle(" Vol!");
              a.setHeaderText(null);
              a.setContentText("Vol ajouté !!!");
              a.showAndWait();
            
            //*********
            
        
        }  catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
             //showSujet();
                       //showSujet();
    }

    @FXML
    private void AfficherVol() {
        
         System.out.println("DEBUGG!!!!");
        ObservableList<Vol> list =pcr.getVol();
        prixVoltab.setCellValueFactory(new PropertyValueFactory<Vol, Integer>("prix"));
        nbplaceVoltab.setCellValueFactory(new PropertyValueFactory<Vol, Integer>("nbplace"));
    
        voltab.setItems(list);
    }

    @FXML
    private void ModifierVol() {
         try {
        int rprix = Integer.parseInt(prixvol.getText());
        int rnbplace = Integer.parseInt(nbplacevol.getText());
             String requete = "UPDATE vol SET prix=? ,nbplace=?  WHERE id="+ID+"";
            PreparedStatement pst
                    = new MyConnection().cn.prepareStatement(requete);
            
           pst.setInt(1,rprix);
           pst.setInt(2,rnbplace);
            pst.executeUpdate();
            // Alert 
            Alert 
              a = new Alert(Alert.AlertType.WARNING); 
              a.setTitle(" Vol!");
              a.setHeaderText(null);
              a.setContentText("Vol Modifié !!!");
              a.showAndWait();
            
            //*********
            
            System.out.println("Vol modifié!");
        } catch (SQLException ex) {
            Logger.getLogger(VolCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
          AfficherVol();
    }

    @FXML
    private void SupprimerVol() {
         try {
            String requete = "DELETE FROM vol WHERE id="+ID+"";
            PreparedStatement pst
                    = new MyConnection().cn.prepareStatement(requete);
            pst.executeUpdate();
            // Alert 
            Alert 
              a = new Alert(Alert.AlertType.WARNING); 
              a.setTitle(" Vol!");
              a.setHeaderText(null);
              a.setContentText("Vol supprimé !!!");
              a.showAndWait();
            
            //*********
            
            System.out.println("Vol supprimé!");
        } catch (SQLException ex) {
            Logger.getLogger(VolCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
          AfficherVol(); 
        
    }
    
     @FXML
    private void SetValue(MouseEvent event) {
         Vol selected = voltab.getSelectionModel().getSelectedItem();
        if (selected != null) {
       
            
           prixvol.setText(String.valueOf(selected.getPrix()));
            nbplacevol.setText(String.valueOf(selected.getNbplace()));
            ID = selected.getId();
            System.out.println(ID);

        }
    }

}
