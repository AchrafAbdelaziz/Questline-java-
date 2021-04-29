/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pijava;

import gestionVoyages.entities.Guide;
import gestionVoyages.services.GuideService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author acer
 */
public class GuideClController implements Initializable {

    @FXML
    private TableView<Guide> tview;
    @FXML
    private TableColumn<Guide, Integer> colid;
    @FXML
    private TableColumn<Guide, Integer> colvoy;
    @FXML
    private TableColumn<Guide, String> colnom;
    @FXML
    private TableColumn<Guide, String> colprénom;
    @FXML
    private TableColumn<Guide, String> colemail;
    @FXML
    private TableColumn<Guide, Integer> colnum;
    @FXML
    private Button btnShow;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void getSelected(MouseEvent event) {
    }

    @FXML
    private void showGuides() {
        
         ObservableList<Guide> GuideList = (ObservableList<Guide>) new GuideService().getAll();
        
        
         colid.setCellValueFactory(new PropertyValueFactory<Guide, Integer>("id"));
         colvoy.setCellValueFactory(new PropertyValueFactory<Guide, Integer>("voyage_id"));
         colnom.setCellValueFactory(new PropertyValueFactory<Guide, String>("nom"));
         colprénom.setCellValueFactory(new PropertyValueFactory<Guide, String>("prenom"));
         colemail.setCellValueFactory(new PropertyValueFactory<Guide, String>("email"));
         colnum.setCellValueFactory(new PropertyValueFactory<Guide, Integer>("num_tel"));
         
         tview.setItems(GuideList);
                 
    }
    
}
