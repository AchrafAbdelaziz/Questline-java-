/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import entity.Categorie;
import entity.Evennement;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;


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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;

import javafx.scene.control.ListView;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import services.CategorieCRUD;
import services.EvennementCRUD;
import services.Upload;
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
    @FXML
    private ListView<String> stat;
    @FXML
    private Button pdf;
    @FXML
    private TextField textrechercher;
    @FXML
    private TableColumn<Evennement, Date> coldate;
    @FXML
    private DatePicker dateevv;
    ObservableList listM = FXCollections.observableArrayList();
    private ImageView image;
    @FXML
    private Button browse;
    
    
    private File file;
     private String fileName = "No picture";
     Evennement u = new Evennement();
    @FXML
    private TableColumn<Evennement, ImageView> colimage;
    @FXML
    private ImageView imagev;
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            showEvent();
            ShowStats();
            search_user();
          
        } catch (SQLException ex) {
            Logger.getLogger(AddEventController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    
    @FXML
    private void AjouterEvent(ActionEvent event) throws SQLException, ParseException {
              try
        {
        //save Msg in DATA BASE
       String rnom = nomev.getText();
       String rdestination = destev.getText();
       String rprix=prixev.getText();
       String rnbrplaces=nbrev.getText();
       String rimage=browse.getText();
        
               String rcategorie=catev.getSelectionModel().getSelectedItem();
               
               
             LocalDate localDate = dateevv.getValue();
//Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
//Date datee =  Date.from(instant);
//System.out.println(localDate + "\n" + instant + "\n" + datee);
 // ZoneId defaultZoneId = ZoneId.systemDefault();
Date  DPCurrentDate1 =  Date.valueOf(localDate);
    //              java.util.Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
                       System.out.println(catev.getSelectionModel().getSelectedItem());
                      
             
                     
                  
       float fprix=Float.parseFloat(rprix);
        Integer fnbrplaces=Integer.parseInt(rnbrplaces);
            System.out.println(fnbrplaces); 
             System.out.println(fnbrplaces); 
              System.out.println(fnbrplaces); 
       
        Evennement e= new Evennement(fnbrplaces,fprix,rnom,rdestination, DPCurrentDate1,fileName);
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
            ShowStats();
            
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
   coldate.setCellValueFactory(new PropertyValueFactory<Evennement, Date>("dateev"));
   colimage.setCellValueFactory(new PropertyValueFactory<Evennement, ImageView>("image"));
        
        tabevent.setItems(list);
        String requete = "SELECT nom FROM Categorie ";

PreparedStatement pst
           = new MyConnection().cn.prepareStatement(requete);
        ResultSet rs = pst.executeQuery();
         
              ObservableList<String> catList = FXCollections.observableArrayList(); ;

          while (rs.next())
          {
               
                                           
              String cat = rs.getString("nom");
              catList.add(cat);

          }
                
        
          
          
          ShowStats();
          System.out.println(catList);
               catev.setItems(catList);
      search_user();
    }

    @FXML
    private void ModifierEvent(ActionEvent event) throws SQLException {
         try {
            String requete = "UPDATE Event SET image=? ,nom=?,destination=?,prix=?,nbr_places=?,dateev=?  WHERE id="+id+"";
            PreparedStatement pst
                    = new MyConnection().cn.prepareStatement(requete);
            pst.setString(1,fileName);
            pst.setString(2, nomev.getText());
            pst.setString(3, destev.getText());
             pst.setString(4, prixev.getText());
              pst.setString(5, nbrev.getText());
              
               LocalDate localDate = dateevv.getValue();
               System.out.println( (dateevv.getValue().toString()).concat("hethi"));
               pst.setString(6, dateevv.getValue().toString());
           
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
         
          
            String var="/files/"+selected.getImage();
            Image image = new Image(var,160,160,true,true);
         
            // tfprixP.setText(Sprix);
            
            nomev.setText(selected.getNom());
            destev.setText(selected.getDestination());
             prixev.setText(Float.toString(selected.getPrix()));
              nbrev.setText(Integer.toString(selected.getNbrplaces()));
              imagev.setImage(image);
            
            id = selected.getId();
        }
    }
    
    
    
    
     private void ShowStats() {
        EvennementCRUD wm = new EvennementCRUD();
        ObservableList<String> listW1 = wm.showNumbers();
        stat.setItems(listW1);
    }

    @FXML
    private void exportpdf(ActionEvent event) throws DocumentException {
        
        try {
            String file_name = ("evennement.pdf");
            Document document = new Document();
            
            PdfWriter.getInstance(document, new FileOutputStream(file_name));
            
            document.open();
            
            document.addTitle("LES evennements ");
            
                 Paragraph paraHeader1 = new Paragraph("LES evennements ");
            document.add(paraHeader1);
           //         ObservableList<Message> list =pcr.getMessage();
                  // while(list)

            
                 // ObservableList<Message> ProductList = FXCollections.observableArrayList();
       // String requete = "SELECT nom,destination,prix,nbr_places FROM event where id="+id+"";
         String requete = "SELECT  nom,destination,prix,nbr_places FROM event where id";
        try {
            PreparedStatement pst
                    = new MyConnection().cn.prepareStatement(requete);
            //Statement st;
            ResultSet rs;
            try {
                //System.out.println("AHAYYYAA!!!!");
                //st=conn.createStatement();
                //System.out.println("AHAYYYAA222!!!!");
                rs = pst.executeQuery(requete);
                
                Evennement e;

                while (rs.next()) {
                   // Message msg = new Message(rs.getInt("id_msg"),rs.getString("message"), rs.getString("id_posteur"),rs.getDate("date_heure_post"));
                   // ProductList.add(msg);
                 //String datepost =  rs.getString("date_heure_post");
                //  Paragraph paraHeader11 = new Paragraph(((rs.getString("image").concat("    ")).concat("   ".concat(rs.getString("nom"))).concat(rs.getString("destination"))).concat("   ".concat(rs.getString("prix"))).concat("   ".concat(rs.getString("nbr_places"))));       
               Paragraph paraHeader11 = new Paragraph(((rs.getString("nom").concat("    ")).concat(rs.getString("destination"))).concat("   ".concat(rs.getString("prix"))).concat("   ".concat(rs.getString("nbr_places"))));
            document.add(paraHeader11);
                }

            } catch (Exception ex) {
                //System.out.println("AHAYYYAA L7KEEEYAAAAA!!!!");
                ex.printStackTrace();
            }
        } catch (SQLException ex) {
            Logger.getLogger(EvennementCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
                  
                  
                  
                  
                  
                  
                   
            
            
            
            document.close();
            Desktop.getDesktop().open(new File(file_name));
        } catch (IOException ex) {
            Logger.getLogger(AddEventController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    void search_user() {          
     colnomev.setCellValueFactory(new PropertyValueFactory<Evennement, String>("nom"));
        //idcreateurtab.setCellValueFactory(new PropertyValueFactory<Sujet, String>("id_createur"));
        coldestev.setCellValueFactory(new PropertyValueFactory<Evennement, String>("destination"));
     //   dateposttab.setCellValueFactory(new PropertyValueFactory<Sujet, Date>("date_heure_creation"));
        colprixev.setCellValueFactory(new PropertyValueFactory<Evennement, Float>("prix"));
        colnbrev.setCellValueFactory(new PropertyValueFactory<Evennement, Integer>("nbrplaces"));
   
        
        
        
        
        

        ObservableList<Evennement> dataList = pcr.getEvennement();
               tabevent.setItems(dataList);
        FilteredList<Evennement> filteredData = new FilteredList<>(dataList, b -> true);  
 textrechercher.textProperty().addListener((observable, oldValue, newValue) -> {
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
  SortedList<Evennement> sortedData = new SortedList<>(filteredData);  
  sortedData.comparatorProperty().bind(tabevent.comparatorProperty());  
  tabevent.setItems(sortedData);      
    }

    @FXML
    private void Browse(ActionEvent event) throws IOException {
        
        imagev.setVisible(true);
       FileChooser fileChooser = new FileChooser();
      
        FileChooser.ExtensionFilter exjpg = new FileChooser.ExtensionFilter("JPG files (.jpg)", ".JPG");
        FileChooser.ExtensionFilter exjpg2 = new FileChooser.ExtensionFilter("JPEG (Joint Photographic Experts Group)", "*.jpeg");
        FileChooser.ExtensionFilter expng = new FileChooser.ExtensionFilter("PNG files (.png)", ".PNG");
        fileChooser.getExtensionFilters().addAll(exjpg, exjpg2, expng);
        fileChooser.setTitle("Choose an image File");

        File file = fileChooser.showOpenDialog(null);

        if (file != null) {
            if (file.length() < 6000000) {

                    fileName = (file.getName());
                Upload u = new Upload();
                u.upload(file);
                Image img =new Image(file.toURI().toString(),100,150,false,true);
            imagev.imageProperty().unbind();
            imagev.setImage(img);
            imagev.setFitWidth(150);
            imagev.setFitHeight(100);

                System.out.println(fileName);
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Permiss");
                alert.setHeaderText("Permission denied");
                alert.setContentText("Your Image file is too big to upload \nplease choose another image");
            }

        }
    }
    
    
}

