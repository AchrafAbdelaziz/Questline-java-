/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.ReservationVol;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;
import services.ResCRUD;
import tools.MyConnection;

/**
 * FXML Controller class
 *
 * @author AZIZ AKARI
 */
public class ReservationVolController implements Initializable {

    @FXML
    private TextField nbpesonneres;
    @FXML
    private DatePicker dateres;
    @FXML
    private TableView<ReservationVol> restab;
    @FXML
    private TableColumn<ReservationVol, Integer> nbpersonneRestab;
    @FXML
    private TableColumn<ReservationVol, Date> dateRestab;
    @FXML
    private Button btnajouter;
    @FXML
    private Button btnafficher;
    int ID;
    ResCRUD pcr= new ResCRUD();
    @FXML
    private Button btnmodifier;
    @FXML
    private Button btnsupprimer;
    @FXML
    private Button btnexport;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void SetValue(MouseEvent event) {
         ReservationVol selected = (ReservationVol) restab.getSelectionModel().getSelectedItem();
        if (selected != null) {
       
            
            nbpesonneres.setText(String.valueOf(selected.getNombre_personne()));
           dateres.setValue(selected.getDate_res().toLocalDate());
            ID = selected.getId();
            System.out.println(ID);

    }
    }

    @FXML
    private void AjouterReservation(ActionEvent event) {
          try
        {
       int rnombre_personne = Integer.parseInt(nbpesonneres.getText());
        LocalDate localDate = dateres.getValue();
        Date  DPCurrentDate1 =  Date.valueOf(localDate);
        ReservationVol res= new ReservationVol(rnombre_personne,DPCurrentDate1);
        ResCRUD resv= new ResCRUD();
        resv.addReservationVol(res);
        // Alert 
            Alert 
              a = new Alert(Alert.AlertType.WARNING); 
              a.setTitle(" ReservationVol!");
              a.setHeaderText(null);
              a.setContentText("ReservationVol ajouté !!!");
              a.showAndWait();
            
            //*********
            
        
        }  catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    
    }

    @FXML
    private void AfficherReservation() throws SQLException {
         System.out.println("DEBUGG!!!!");
        ObservableList<ReservationVol> list =pcr.getRes();
        nbpersonneRestab.setCellValueFactory(new PropertyValueFactory<ReservationVol, Integer>("nombre_personne"));
        dateRestab.setCellValueFactory(new PropertyValueFactory<>("date"));

        restab.setItems(list);

    }

    @FXML
    private void ModifierReservation(ActionEvent event) throws SQLException {
         try {
        int rnombre_personne = Integer.parseInt(nbpesonneres.getText());
        LocalDate localDate = dateres.getValue();
        Date  DPCurrentDate1 =  Date.valueOf(localDate);

             System.out.println(restab.getSelectionModel().getSelectedItem());
             String requete = "UPDATE reservation_vol SET nombre_personne=? ,date=?  WHERE id="+ID+"";
            PreparedStatement pst
                    = new MyConnection().cn.prepareStatement(requete);
            
           pst.setInt(1,rnombre_personne);
           pst.setDate(2,DPCurrentDate1);
           pst.executeUpdate();
            // Alert 
            Alert 
              a = new Alert(Alert.AlertType.WARNING); 
              a.setTitle(" ReservationVol!");
              a.setHeaderText(null);
              a.setContentText("ReservationVol Modifié !!!");
              a.showAndWait();
            
            //*********
            
            System.out.println("ReservationVol modifié!");
        } catch (SQLException ex) {
            Logger.getLogger(ResCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
          AfficherReservation();
    }

    @FXML
    private void SupprimerReservation(ActionEvent event) throws SQLException {
         try {
            String requete = "DELETE FROM ReservationVol WHERE id="+ID+"";
            PreparedStatement pst
                    = new MyConnection().cn.prepareStatement(requete);
            pst.executeUpdate();
            // Alert 
            Alert 
              a = new Alert(Alert.AlertType.WARNING); 
              a.setTitle(" ReservationVol!");
              a.setHeaderText(null);
              a.setContentText("ReservationVol supprimé !!!");
              a.showAndWait();
            
            //*********
            
            System.out.println("ReservationVol supprimé!");
        } catch (SQLException ex) {
            Logger.getLogger(ResCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
          AfficherReservation(); 

    }

    @FXML
    private void ExportExel(ActionEvent event) {
         saveHeader("ReservationVol.csv");
        try {
            String requete = "SELECT * FROM reservation_vol ";
            Statement st = MyConnection.getInstance().getConnection().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                saveRecord(rs.getInt("id"), rs.getInt("nombre_personne"), rs.getDate("date"), "ReservationVol.csv");
            }
            JOptionPane.showMessageDialog(null, "Record saved");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    private void saveHeader(String filepath) {
        try {
            FileWriter fw = new FileWriter(filepath, false);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            pw.println("id,nom,quantite,prix,categorie,image");
            pw.flush();
            pw.close();

        } catch (Exception E) {
            JOptionPane.showMessageDialog(null, "Record not saved");
        }
    }

    private void saveRecord(int id, int nombre_personne, Date date_res, String reservationVolcsv) {
        try {
            String filepath = null;
            FileWriter fw = new FileWriter(filepath, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            pw.println(id + "," + nombre_personne + "," + date_res   );
            pw.flush();
            pw.close();

        } catch (Exception E) {
            JOptionPane.showMessageDialog(null, "Record not saved");
        }
}
}
