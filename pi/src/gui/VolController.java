/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Destination;
import entities.Vol;
import java.net.URL;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
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
import javafx.scene.control.DatePicker;
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
public class VolController implements Initializable {

    @FXML
    private TextField nbplacevol;
    @FXML
    private TextField prixvol;
    @FXML
    private DatePicker datevol;
    @FXML
    private TableView<Vol> voltab;
    @FXML
    private TableColumn<Vol, Date> dateVoltab;
    @FXML
    private TableColumn<Vol, Float> prixVoltab;
    @FXML
    private TableColumn<Vol, Integer> nbplaceVoltab;
    @FXML
    private TableColumn<Destination, String> destinations_idtab;
    @FXML
    private ComboBox<String> voldestination;
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
       try {
            String requete = "SELECT nom FROM destination  ";
            Statement st = MyConnection.getInstance().getConnection().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
               voldestination.getItems().add(rs.getString("nom"));
               
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        // TODO
        
    }    

    @FXML
    private void SetValue(MouseEvent event) {
        Vol selected = voltab.getSelectionModel().getSelectedItem();
        if (selected != null) {
            datevol.setValue(selected.getDate().toLocalDate());
           prixvol.setText(String.valueOf(selected.getPrix()));
            nbplacevol.setText(String.valueOf(selected.getNbplace()));
            ID = selected.getId();
            System.out.println(ID);

        }
    }

    @FXML
    private void AjouterVol(ActionEvent event) {
          try
        {
       LocalDate localDate = datevol.getValue();
//Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
//Date datee =  Date.from(instant);
//System.out.println(localDate + "\n" + instant + "\n" + datee);
 // ZoneId defaultZoneId = ZoneId.systemDefault();
Date  DPCurrentDate1 =  Date.valueOf(localDate);
    //              java.util.Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
       float rprix = Float.parseFloat(prixvol.getText());
       int rnbplace = Integer.parseInt(nbplacevol.getText()); 
        //default time zone
	/*ZoneId defaultZoneId = ZoneId.systemDefault();
		
	//creating the instance of LocalDate using the day, month, year info
        LocalDate localDate = datevol.getValue();
        
        //local date + atStartOfDay() + default time zone + toInstant() = Date
        Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());*/

    //              java.util.Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
          String rdestinations_id = destinations_idtab.getText();

    System.out.println(voltab.getSelectionModel().getSelectedItem());
                      
        
        Vol v= new Vol(DPCurrentDate1,rprix,rnbplace);
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
    }
    

    @FXML
    private void AfficherVol() throws SQLException {
          System.out.println("DEBUGG!!!!");
        ObservableList<Vol> list =pcr.getVol();
        prixVoltab.setCellValueFactory(new PropertyValueFactory<>("prix"));
        nbplaceVoltab.setCellValueFactory(new PropertyValueFactory<>("nbplace"));
        dateVoltab.setCellValueFactory(new PropertyValueFactory<>("date"));
        destinations_idtab.setCellValueFactory(new PropertyValueFactory<>("destinations_id"));
        voltab.setItems(list);
        
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
    }

    @FXML
    private void ModifierVol() throws SQLException {
         try {
        
         LocalDate localDate = datevol.getValue();
        Date  DPCurrentDate1 =  Date.valueOf(localDate);
      int rprix = Integer.parseInt(prixvol.getText());
        int rnbplace = Integer.parseInt(nbplacevol.getText());                   
    System.out.println(voltab.getSelectionModel().getSelectedItem());
             String requete = "UPDATE vol SET date=? ,prix=? ,nbplace=? WHERE id="+ID+"";
            PreparedStatement pst
                    = new MyConnection().cn.prepareStatement(requete);
          pst.setDate(1, DPCurrentDate1);
           pst.setInt(2,rprix);
           pst.setInt(3,rnbplace);
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
    private void SupprimerVol() throws SQLException {
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
    
}
