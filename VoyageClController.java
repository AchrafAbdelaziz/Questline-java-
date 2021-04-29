/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pijava;

import gestionVoyages.entities.Voyage;
import gestionVoyages.services.VoyageService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author acer
 */
public class VoyageClController implements Initializable {

    @FXML
    private Rating rating;
    @FXML
    private Label msg;
    @FXML
    private Button btnshow;
    @FXML
    private TableView<Voyage> tview;
    @FXML
    private TableColumn<Voyage, String> coldestination;
    @FXML
    private TableColumn<Voyage, Integer> colprix;
    @FXML
    private TableColumn<Voyage, String> colperiode;
    @FXML
    private TableColumn<Voyage, String> colimage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       rating.ratingProperty().addListener(new ChangeListener<Number>() {
            @Override public void changed(ObservableValue<? extends Number> ov, Number t, Number t1) {
              msg.setText("Rating : "+ t1.toString());
            }
        });
    }    
    
     @FXML
    public void showVoyages()
    {
        
         ObservableList<Voyage> VoyageList = (ObservableList<Voyage>) new VoyageService().getAll();
        
         coldestination.setCellValueFactory(new PropertyValueFactory<Voyage, String>("destination"));
         colprix.setCellValueFactory(new PropertyValueFactory<Voyage, Integer>("prix"));
         colperiode.setCellValueFactory(new PropertyValueFactory<Voyage, String>("periode"));
         colimage.setCellValueFactory(new PropertyValueFactory<Voyage, String>("image_name"));
         
         tview.setItems(VoyageList);
                 
   }
}
