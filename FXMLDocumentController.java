/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pijava;

import com.sun.javafx.runtime.VersionInfo;
import java.sql.Connection;
import gestionVoyages.entities.Voyage;
import gestionVoyages.services.VoyageService;
import gestionVoyages.utils.MaConnexion;
import java.awt.Desktop.Action;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.JobAttributes.DialogType;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.chart.PieChart;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author acer
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Label lbdest;
    @FXML
    private Label lbprix;
    @FXML
    private Label lbperiode;
    @FXML
    private TextField tfdest;
    @FXML
    private TextField tfprix;
    @FXML
    private TextField tfperiode;
    @FXML
    private TableView<Voyage> tview;
    @FXML
    private TableColumn<Voyage, Integer> colprix;
    @FXML
    private TableColumn<Voyage, String> colperiode;
    @FXML
    private Button btnInsert;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnShow;
    @FXML
    private TableColumn<Voyage, Integer> colid;
    @FXML
    private TableColumn<Voyage, String> coldestination;
    @FXML
    private Label lbimage;
    @FXML
    private TextField tfimage;
    @FXML
    private TableColumn<Voyage, String> colimage;
    @FXML
    private TextField tfid;
    @FXML
    private Button btnTrier;
    @FXML
    private TextField tfsearch;
    @FXML
    private PieChart piechart;
    
    ObservableList<PieChart.Data> piechartdata;
    @FXML
    private Label lbid;
    @FXML
    private Button btnAnimate;
    @FXML
    private Label lbsearch;
   
    
    public void initialize(URL url, ResourceBundle rb) {
        
        piechart.setData(piechartdata);
        Stat();
        VoyageService voyageC = new VoyageService();
       

        
    }    
    
  
    @FXML
    public void showVoyages()
    {
       
        
         ObservableList<Voyage> VoyageList = (ObservableList<Voyage>) new VoyageService().getAll();
        
         colid.setCellValueFactory(new PropertyValueFactory<Voyage, Integer>("id"));
         coldestination.setCellValueFactory(new PropertyValueFactory<Voyage, String>("destination"));
         colprix.setCellValueFactory(new PropertyValueFactory<Voyage, Integer>("prix"));
         colperiode.setCellValueFactory(new PropertyValueFactory<Voyage, String>("periode"));
         colimage.setCellValueFactory(new PropertyValueFactory<Voyage, String>("image_name"));
         
         tview.setItems(VoyageList);
                 
   }

    
    @FXML
    private void trierVoyage(ActionEvent event) {
        ObservableList<Voyage> VoyageList = (ObservableList<Voyage>) new VoyageService().trier();
        
         colid.setCellValueFactory(new PropertyValueFactory<Voyage, Integer>("id"));
         coldestination.setCellValueFactory(new PropertyValueFactory<Voyage, String>("destination"));
         colprix.setCellValueFactory(new PropertyValueFactory<Voyage, Integer>("prix"));
         colperiode.setCellValueFactory(new PropertyValueFactory<Voyage, String>("periode"));
         colimage.setCellValueFactory(new PropertyValueFactory<Voyage, String>("image_name"));
         
         tview.setItems(VoyageList);
    }
    
    
    @FXML
    private void insertButton(ActionEvent event) {
         
            Voyage v = new  Voyage();
            
            v.setDestination(tfdest.getText());
            v.setPrix((Integer.parseInt(tfprix.getText())));
            v.setPeriode(tfperiode.getText());
            v.setImage_name(tfimage.getText());
            
           
            new VoyageService().add(v);
            tfdest.setText("");
            tfprix.setText("");
            tfperiode.setText("");
            tfimage.setText("");
            
          showVoyages();
          
          Notification();

          
    }
    
    
    @FXML
    private void updateButton(ActionEvent event) {
      
      int id = Integer.parseInt(tfid.getText());
      String destination = tfdest.getText();
      
        
         VoyageService voy =  new VoyageService();

        voy.updateVoyage(id,destination);
        
        showVoyages();
    
            JOptionPane.showMessageDialog(null,"Modification effectuée");

        
    }

    /*@FXML
    private void updateButton(ActionEvent event) {
        
           
             tview.setEditable(true);
                
            Voyage v = new  Voyage();
            
            v.setDestination(tfdest.getText());
            v.setPrix((Integer.parseInt(tfprix.getText())));
            v.setPeriode(tfperiode.getText());
            v.setImage_name(tfimage.getText());
            
            btnUpdate.setOnAction(e-> { 
                    VoyageService voy =  new VoyageService(); 
                    voy.update(v);
                    
                     
            tfdest.setText("");
            tfprix.setText("");
            tfperiode.setText("");
            tfimage.setText("");
            
                    });
           
          
            
            showVoyages();
           
    }*/
    
      

    @FXML
    private void deleteButton(ActionEvent event) {
        
         Voyage v = tview.getSelectionModel().getSelectedItem();
         VoyageService voy =  new VoyageService();
         voy.delete(v);
         
        showVoyages();
       
        JOptionPane.showMessageDialog(null,"Voulez-vous vraiment supprimer ?");
    }


    @FXML
    private void getSelected(MouseEvent event) {
        
        Voyage v = tview.getSelectionModel().getSelectedItem();
          
            tfid.setText(colid.getCellData(v).toString());
            tfdest.setText(coldestination.getCellData(v).toString());
            tfprix.setText(colprix.getCellData(v).toString());
            tfperiode.setText(colperiode.getCellData(v).toString());
            tfimage.setText(colimage.getCellData(v).toString());
    }


    @FXML
    private void search(KeyEvent event) {
         VoyageService voy =  new VoyageService();
     
          ObservableList<Voyage> list = voy.getAll();
       FilteredList<Voyage> Filtered = new FilteredList<>(list,e-> true);
        tfsearch.textProperty().addListener((Observablevalue,OldValue,NewValue)->{
            Filtered.setPredicate((Predicate<? super Voyage>) (Voyage ab) ->{
                if (NewValue ==null || NewValue.isEmpty()){
                    return true;
                }
               
                
                
                  String lowerCaseFilter  = NewValue.toLowerCase();
                if(ab.getDestination().toLowerCase().contains(NewValue)){
                    return true;
                }
                
                return false;
                
            });
            SortedList<Voyage> sorted = new SortedList<> (Filtered);
            sorted.comparatorProperty().bind(tview.comparatorProperty());
                 tview.setItems(sorted);
        });
    }
    
    /*@FXML
    private void upload(ActionEvent event) {
        Stage primary = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Selectionner une image");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter(".jpg", ".jpeg","image Files", ".png", ".gif"));
        File file = fileChooser.showOpenDialog(primary);
//String path = "C:\\Users\\ASUS\\Documents\\NetBeansProjects\\produit\\src\\image";
        btnupload.setText(file.getPath());
        String m = file.getPath();

        if (file != null) {
            try {
                Files.copy(file.toPath(), new File("\\" + file.getName()).toPath());

                System.out.println(m);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }*/
    
    private void Stat(){
        
           ArrayList<Integer> np=new ArrayList<Integer>();
ArrayList<Integer> cat=new ArrayList<Integer>();
    Connection cnx=MaConnexion.getInstance().getCnx();

        piechartdata=FXCollections.observableArrayList();
    try {
       
        PreparedStatement pst=cnx.prepareStatement("select * from Voyage");
         ResultSet rs=pst.executeQuery();
       
        while(rs.next() )
        {
           ;
             
            PreparedStatement pst1=cnx.prepareStatement("select count(*) as countab FROM Voyage WHERE id="+rs.getInt("id"));
           ResultSet rs1=pst1.executeQuery(); 
        while(rs1.next())
        {       
            int i=Integer.valueOf(rs1.getString("countab"));
            piechartdata.add(new PieChart.Data(rs.getString("prix"),i));
            
            np.add(i);
            cat.add(rs.getInt("prix"));
        }
      
    }
    } catch (SQLException ex) {
        System.out.println("stat email ");
    } 
     piechart.setData(piechartdata);
      }
    
    
    public void Notification(){
	Platform.runLater(new Runnable() {
		@Override
		public void run() {
                     //=Image img = new Image("/tick.png");
			Notifications notify = Notifications.create()
                                        .title("Success Message")
					.text("Un nouveau voyage est ajouté")
                                        //.graphic(new ImageView(img))
					.hideAfter(javafx.util.Duration.seconds(5))
					.position(Pos.TOP_RIGHT);
                                        
			
			notify.showInformation();
		}
	}); 


}

    @FXML
    private void Animation(ActionEvent event) {
        
        new animatefx.animation.Pulse(tview).play();
        
         new animatefx.animation.RollIn(tfdest).play();
          new animatefx.animation.RollIn(tfprix).play();
           new animatefx.animation.RollIn(tfperiode).play();
            new animatefx.animation.RollIn(tfimage).play();
            new animatefx.animation.RollIn(tfsearch).play();
            
          new animatefx.animation.Flash(btnShow).play();
          new animatefx.animation.Flash(btnUpdate).play();
          new animatefx.animation.Flash(btnDelete).play();
           new animatefx.animation.Flash(btnInsert).play();
            new animatefx.animation.Flash(btnTrier).play();
            
            new animatefx.animation.Bounce(lbdest).play();
            new animatefx.animation.Bounce(lbprix).play();
            new animatefx.animation.Bounce(lbperiode).play();
            new animatefx.animation.Bounce(lbimage).play();
            new animatefx.animation.Bounce(lbsearch).play();
          
            new animatefx.animation.RollIn(piechart).play();
          
          
    }

    private void uploadImage(ActionEvent event) {
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        File f = chooser.getSelectedFile();
         String fileName = f.getAbsolutePath();
         ImageIcon img;
        img = new ImageIcon (new ImageIcon(fileName).getImage().getScaledInstance(20,35,Image.SCALE_SMOOTH));
        //lbimage.setIcon(ImageIcon);
    }
    
}

