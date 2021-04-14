/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entity.Categorie;
import entity.Evennement;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import services.CategorieCRUD;
import services.EvennementCRUD;
import tools.MyConnection;

/**
 * FXML Controller class
 *
 * @author Msi
 */
public class AddEventController implements Initializable {

    @FXML
    private TextField nomev;
    @FXML
    private TextField destev;
    @FXML
    private TextField prixev;
    @FXML
    private TextField nbrev;
    @FXML
    private ComboBox<String> catev;
    @FXML
    private Button btnajout;
    @FXML
    private TableView<Evennement> tabevent;
    @FXML
    private TableColumn<Evennement, String> colnomev;
    @FXML
    private TableColumn<Evennement, String> coldestev;
    @FXML
    private TableColumn<Evennement, Float> colprixev;
    @FXML
    private TableColumn<Evennement, Integer> colnbrev;
    EvennementCRUD pcr=new EvennementCRUD();
    int id;
    @FXML
    private Button btnmodifev;
    @FXML
    private Button btnsupev;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            showEvent();
        } catch (SQLException ex) {
            Logger.getLogger(AddEventController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    
    @FXML
    private void AjouterEvent(ActionEvent event) throws SQLException {
              try
        {
        //save Msg in DATA BASE
       String rnom = nomev.getText();
       String rdestination = destev.getText();
       String rprix=prixev.getText();
       String rnbrplaces=nbrev.getText();
               String rcategorie=catev.getSelectionModel().getSelectedItem();
             
                   
                       System.out.println(catev.getSelectionModel().getSelectedItem());
                      
                      
                     
                  
       float fprix=Float.parseFloat(rprix);
        Integer fnbrplaces=Integer.parseInt(rnbrplaces);
            System.out.println(fnbrplaces); 
             System.out.println(fnbrplaces); 
              System.out.println(fnbrplaces); 
       
        Evennement e= new Evennement(fnbrplaces,fprix,rnom,rdestination);
        EvennementCRUD eve=new EvennementCRUD();
        eve.addEvent(e);
       
        // Alert 
            Alert 
              a = new Alert(Alert.AlertType.WARNING); 
              a.setTitle(" Evennement!");
              a.setHeaderText(null);
              a.setContentText("Evennement ajouté !!!");
              a.showAndWait();
            
            //*********
            
        
        }  catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
            showEvent(); 
            
    }
    public void showEvent() throws SQLException {
        System.out.println("DEBUGG!!!!");
        ObservableList<Evennement> list =pcr.getEvennement();
        //   System.out.println(pcr.getSujet().toString());
        //ObservableList<Product> list = FXCollections.observableList(pcd.getProductList());
        colnomev.setCellValueFactory(new PropertyValueFactory<Evennement, String>("nom"));
        //idcreateurtab.setCellValueFactory(new PropertyValueFactory<Sujet, String>("id_createur"));
        coldestev.setCellValueFactory(new PropertyValueFactory<Evennement, String>("destination"));
     //   dateposttab.setCellValueFactory(new PropertyValueFactory<Sujet, Date>("date_heure_creation"));
        colprixev.setCellValueFactory(new PropertyValueFactory<Evennement, Float>("prix"));
        colnbrev.setCellValueFactory(new PropertyValueFactory<Evennement, Integer>("nbrplaces"));
   
        
        tabevent.setItems(list);
        String requete = "SELECT nom FROM Categorie ";

PreparedStatement pst
           = new MyConnection().cn.prepareStatement(requete);
        ResultSet rs = pst.executeQuery();
          // System.out.println(rs.getString("sujet"));
              ObservableList<String> catList = FXCollections.observableArrayList(); ;

          while (rs.next())
          {
                //ObservableList<String> SujetList = FXCollections.observableArrayList(rs.getString("sujet"));
              // System.out.println(SujetList);
                                           // listesujet.setItems(SujetList);
              String cat = rs.getString("nom");
              catList.add(cat);

          }
                
        
          
          
          
          System.out.println(catList);
               catev.setItems(catList);
     
    }

    @FXML
    private void ModifierEvent(ActionEvent event) throws SQLException {
         try {
            String requete = "UPDATE Event SET nom=?,destination=?,prix=?,nbr_places=?  WHERE id="+id+"";
            PreparedStatement pst
                    = new MyConnection().cn.prepareStatement(requete);
            
            pst.setString(1, nomev.getText());
            pst.setString(2, destev.getText());
             pst.setString(3, prixev.getText());
              pst.setString(4, nbrev.getText());
           
            pst.executeUpdate();
            // Alert 
            Alert 
              a = new Alert(Alert.AlertType.WARNING); 
              a.setTitle("Evennement!");
              a.setHeaderText(null);
              a.setContentText("Evennement Modifié !!!");
              a.showAndWait();
            
            //*********
            
            System.out.println("Evennement modifié!");
        } catch (SQLException ex) {
            Logger.getLogger(CategorieCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
          showEvent();
        
    }

    @FXML
    private void SupprimerEvent(ActionEvent event) throws SQLException {
         try {
            String requete = "DELETE FROM Event WHERE id="+id+"";
            PreparedStatement pst
                    = new MyConnection().cn.prepareStatement(requete);
            pst.executeUpdate();
            // Alert 
            Alert 
              a = new Alert(Alert.AlertType.WARNING); 
              a.setTitle(" Event!");
              a.setHeaderText(null);
              a.setContentText("Event supprimé !!!");
              a.showAndWait();
            
            //*********
            
            System.out.println("Event supprimé!");
        } catch (SQLException ex) {
            Logger.getLogger(EvennementCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
          showEvent();  

    }
    
     @FXML
    private void SetValue(MouseEvent event) {
         Evennement selected = tabevent.getSelectionModel().getSelectedItem();
        if (selected != null) {
         
          
            
           // tfprixP.setText(Sprix);
            
            nomev.setText(selected.getNom());
            destev.setText(selected.getDestination());
             prixev.setText(Float.toString(selected.getPrix()));
              nbrev.setText(Integer.toString(selected.getNbrplaces()));
            
            id = selected.getId();
        }
    }
}
