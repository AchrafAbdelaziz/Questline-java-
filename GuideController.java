/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pijava;

import gestionVoyages.entities.Guide;
import gestionVoyages.entities.Voyage;
import gestionVoyages.services.GuideService;
import gestionVoyages.services.VoyageService;
import gestionVoyages.utils.MaConnexion;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author acer
 */
public class GuideController implements Initializable {

    @FXML
    private Label lbvoy;
    @FXML
    private Label lbnom;
    @FXML
    private Label lbprenom;
    @FXML
    private Label lbnum;
    private TextField tfvoy;
    @FXML
    private TextField tfnom;
    @FXML
    private TextField tfprenom;
    @FXML
    private TextField tfemail;
    @FXML
    private TextField tfnum;
    @FXML
    private TableView<Guide> tview;
    @FXML
    private TableColumn<Guide, Integer> colvoy;
    @FXML
    private TableColumn<Guide, String> colnom;
    @FXML
    private TableColumn<Guide, String> colemail;
    @FXML
    private TableColumn<Guide, Integer> colnum;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnShow;
    @FXML
    private TableColumn<Guide, Integer> colid;
    @FXML
    private TableColumn<Guide, String> colprénom;
    @FXML
    private ComboBox<Integer> combo;
    @FXML
    private TextField tfid;
    @FXML
    private Label lbid;
    @FXML
    private PieChart piechart;
    
    ObservableList<PieChart.Data> piechartdata;

    /**
     * Initialize0s the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        GuideService guideC = new GuideService();
        combo.setItems(FXCollections.observableArrayList(guideC.getAllDestinations()));
        
        piechart.setData(piechartdata);
        Stat();
      
        
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
    
    
      @FXML
     private void InsertButton(ActionEvent event)  {

        
           Integer voyage = combo.getValue();
           String nom = tfnom.getText();
           String prenom = tfprenom.getText();
           String email = tfemail.getText();
           Integer num = Integer.parseInt(tfnum.getText());
        
         GuideService guideC = new GuideService();
         
         Guide g = new Guide(voyage,nom,prenom,email,num);
          
         guideC.add(g);

          showGuides();
    }
     
     
      
    @FXML
    private void UpdateButton(ActionEvent event) {
      
      int id = Integer.parseInt(tfid.getText());
      String nom = tfnom.getText();
      
        
         GuideService voy =  new GuideService();

        voy.updateGuide(id,nom);
        
        showGuides();
    
            JOptionPane.showMessageDialog(null,"Modification effectuée");

        
    }

    /*@FXML
    private void InsertButton(ActionEvent event) {
        
        Guide g = new  Guide();
            
            g.setVoyage_id(Integer.parseInt(combo.getValue()));
            g.setNom(tfnom.getText());
            g.setPrenom(tfprenom.getText());
            g.setEmail(tfemail.getText());
            g.setNum_tel(Integer.parseInt(tfnum.getText()));
            
            new GuideService().add(g);
            tfvoy.setText("");
            tfnom.setText("");
            tfprenom.setText("");
            tfemail.setText("");
            tfnum.setText("");
            
            showGuides();
         
          
          JOptionPane.showMessageDialog(null,"Ajout effectué");
            
    }*/

    
    @FXML
    private void DeleteButton(ActionEvent event) {
        
        Guide g = tview.getSelectionModel().getSelectedItem();
        GuideService guide =  new GuideService();
        guide.delete(g);
        
         showGuides();
         
       
        JOptionPane.showMessageDialog(null,"Voulez-vous vraiment supprimer ?");
    }

    private void onTap(ActionEvent event) {
          System.out.println("ONTAP");
    }

     @FXML
    private void getSelected(MouseEvent event) {
        
        Guide g = tview.getSelectionModel().getSelectedItem();
          
        
            tfid.setText(colid.getCellData(g).toString());
            
            tfnom.setText(colnom.getCellData(g).toString());
            tfprenom.setText(colprénom.getCellData(g).toString());
            tfemail.setText(colemail.getCellData(g).toString());
            tfnum.setText(colnum.getCellData(g).toString());
    }
    
    
    private void Stat(){
        
           ArrayList<Integer> np=new ArrayList<Integer>();
ArrayList<String> cat=new ArrayList<String>();
    Connection cnx=MaConnexion.getInstance().getCnx();

        piechartdata=FXCollections.observableArrayList();
    try {
       
        PreparedStatement pst=cnx.prepareStatement("select * from Voyage");
         ResultSet rs=pst.executeQuery();
       
        while(rs.next() )
        {
           ;
             
            PreparedStatement pst1=cnx.prepareStatement("select count(*) as countab FROM Guide WHERE voyage_id="+rs.getInt("id"));
           ResultSet rs1=pst1.executeQuery(); 
        while(rs1.next())
        {       
            int i=Integer.valueOf(rs1.getString("countab"));
            piechartdata.add(new PieChart.Data(rs.getString("destination"),i));
            
            np.add(i);
            cat.add(rs.getString("destination"));
        }
      
    }
    } catch (SQLException ex) {
        System.out.println("stat email ");
    } 
     piechart.setData(piechartdata);
      }
    

    
}
