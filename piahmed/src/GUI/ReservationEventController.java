/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import services.EvennementCRUD;
import tools.MyConnection;

/**
 * FXML Controller class
 *
 * @author Msi
 */
public class ReservationEventController implements Initializable {

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         try {
             showEvent();
         } catch (SQLException ex) {
             Logger.getLogger(ReservationEventController.class.getName()).log(Level.SEVERE, null, ex);
         }
    }    

    @FXML
    private void SetValue(MouseEvent event) {
        
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
       
     }
}
