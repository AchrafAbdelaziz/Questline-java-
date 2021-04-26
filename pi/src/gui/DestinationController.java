/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Destination;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
import tools.MyConnection;

/**
 * FXML Controller class
 *
 * @author AZIZ AKARI
 */
public class DestinationController implements Initializable {

    @FXML
    private TextField nomdest;
    @FXML
    private TableView<Destination> desttab;
    @FXML
    private TableColumn<Destination, String> nomDesttab;
    @FXML
    private Button btnajouter;
    @FXML
    private Button btnafficher;
     int ID;
    DestCRUD pcr= new DestCRUD();
    @FXML
    private Button btnmodifier;
    @FXML
    private Button btnsupprimer;
    @FXML
    private TextField txtrechercher;
     ObservableList<Destination> liste2= FXCollections.observableArrayList();
    @FXML
    private Button btntri;
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        RechercherDestination();
        // TODO
    }    

    @FXML
    private void SetValue(MouseEvent event) {
         Destination selected = (Destination) desttab.getSelectionModel().getSelectedItem();
        if (selected != null) {
       
            
            nomdest.setText(String.valueOf(selected.getNom()));
            ID = selected.getId();
            System.out.println(ID);

    }
    }

    @FXML
    private void AjouterDestination(ActionEvent event) {
        try
        {
       String rnom = nomdest.getText();

        Destination dest= new Destination(rnom);
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
    }

    @FXML
    private void AfficherDestination( ) throws SQLException {
          System.out.println("DEBUGG!!!!");
        ObservableList<Destination> list =pcr.getDest();
        nomDesttab.setCellValueFactory(new PropertyValueFactory<Destination, String>("nom"));
        desttab.setItems(list);
        String requete = "SELECT nom FROM Destination ";

PreparedStatement pst
           = new MyConnection().cn.prepareStatement(requete);
        ResultSet rs = pst.executeQuery();
         
              ObservableList<String> catList = FXCollections.observableArrayList(); ;

          while (rs.next())
          {
               
                                           
              String cat = rs.getString("nom");
              catList.add(cat);

          }
     //RechercherDestination();
    }

    @FXML
    private void ModifierDestination(ActionEvent event) throws SQLException {
         try {
        
        String rnom = nomdest.getText();

             String requete = "UPDATE destination SET nom=?  WHERE id="+ID+"";
            PreparedStatement pst
                    = new MyConnection().cn.prepareStatement(requete);
            
           pst.setString(1,rnom);
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
            Logger.getLogger(DestCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
          AfficherDestination();
    }

    @FXML
    private void SupprimerDestination(ActionEvent event) throws SQLException {
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
            Logger.getLogger(DestCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
          AfficherDestination(); 
    }

    @FXML
    private void RechercherDestination() {
        nomDesttab.setCellValueFactory(new PropertyValueFactory<Destination, String>("Nom"));
     //   dateposttab.setCellValueFactory(new PropertyValueFactory<Sujet, Date>("date_heure_creation"));

        ObservableList<Destination> dataList = pcr.getDest();
               desttab.setItems(dataList);
        FilteredList<Destination> filteredData = new FilteredList<>(dataList, b -> true);  
 txtrechercher.textProperty().addListener((observable, oldValue, newValue) -> {
 filteredData.setPredicate(message -> {
    if (newValue == null || newValue.isEmpty()) {
     return true;
    }    
    String lowerCaseFilter = newValue.toLowerCase();
    
    if (message.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
     return true; // Filter matches username
    } 
  
        
    
                                
         else  
          return false; // Does not match.
   });
  });  
  SortedList<Destination> sortedData = new SortedList<>(filteredData);  
  sortedData.comparatorProperty().bind(desttab.comparatorProperty());  
  desttab.setItems(sortedData);      
    
    }

    @FXML
    private void TriDestination() {
        ObservableList<Destination> list = FXCollections.observableArrayList();
        try {
            
            String requete = "select * from destination ORDER BY destination.nom ASC";
            PreparedStatement pst
                    = new MyConnection().cn.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
              
   
            list.add(new Destination(rs.getString(1)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } 
        desttab.setItems(list);

      
        nomDesttab.setCellValueFactory(new PropertyValueFactory<Destination,String>("nom"));
       
    }
    
    
}
