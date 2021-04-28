/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.jfoenix.controls.JFXButton;
import sservice.UtilisateurCRUD;
import java.net.URL;
import entities.Utilisateur;
import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import tools.MyConnection;
import sservice.Upload;
import entities.Utilisateur;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.regex.Pattern;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.input.KeyEvent;
import sservice.JavaMailUtil;
import org.mindrot.jbcrypt.BCrypt;
import tools.VoiceUtils;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class AddUtilisateurController implements Initializable {

    @FXML
    private TableView<Utilisateur> tabutil;
    @FXML
    private TableColumn<Utilisateur, Integer> idUtilisateurtab;
    @FXML
    private TableColumn<Utilisateur, String> nomUtilisateurtab;
    @FXML
    private TableColumn<Utilisateur, String> prenomUtilisateurtab;
    @FXML
    private TableColumn<Utilisateur, String> emailUtilisateurtab;
    @FXML
    private TableColumn<Utilisateur, String> mdpUtilisateurtab;
    @FXML
    private TableColumn<Utilisateur, ImageView> imgUtilisateurtab;
    int ID;
    UtilisateurCRUD pcr = new UtilisateurCRUD();
    Utilisateur u = new Utilisateur();
    
    @FXML
    private TextField nomuser;
    @FXML
    private TextField prenomuser;
    @FXML
    private TextField emailuser;
    @FXML
    private TextField mdpuser;
    @FXML
    private JFXButton ajouterbtn;
    @FXML
    private JFXButton afficherbtn;
    @FXML
    private JFXButton modifierbtn;
    @FXML
    private JFXButton supprimerbtn;
    @FXML
    private JFXButton Browse;
    private File file;
     private String fileName = "No picture";
    @FXML
    private ImageView image;
    @FXML
    private ImageView nomcheck;
    @FXML
    private ImageView prenomcheck;
    @FXML
    private ImageView emailcheck;
    @FXML
    private ImageView passwordcheck;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void AjouterUtilisateur(ActionEvent event) throws Exception {
        if (testSaisie()){
        
            //save Msg in DATA BASE
            
            u.setNom(nomuser.getText());
            u.setPrenom(prenomuser.getText());
            u.setEmail(emailuser.getText());
            u.setMdp(hashPassword(mdpuser.getText()));
            u.setImg(fileName);
            u.setRole("admin");
             
            //String rprenom = prenomuser.getText();
            //String remail = emailuser.getText();
            //String rmdp = mdpuser.getText();
            
            
            UtilisateurCRUD userr = new UtilisateurCRUD();
            userr.addUtilisateur(u);
            // Alert 
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setTitle(" Utilisateur!");
            a.setHeaderText(null);
            a.setContentText("Utilsateur ajouté !!!");
            a.showAndWait();
            System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
        VoiceUtils v=new VoiceUtils("kevin16");
        String[] msg={"User added"};
            v.sayMultiple(msg);
        }
            //*********
        
        
        AfficherUtilisateur();
        JavaMailUtil.sendMail("achraf.abdelaziz@esprit.tn");

    }

    @FXML
    private void AfficherUtilisateur() {
        System.out.println("DEBUGG!!!!");
        ObservableList<Utilisateur> list = pcr.getUtilisateur();
        
        idUtilisateurtab.setCellValueFactory(new PropertyValueFactory<Utilisateur, Integer>("id_utilisateur"));
        
        nomUtilisateurtab.setCellValueFactory(new PropertyValueFactory<Utilisateur, String>("nom"));
    
        prenomUtilisateurtab.setCellValueFactory(new PropertyValueFactory<Utilisateur, String>("prenom"));
        emailUtilisateurtab.setCellValueFactory(new PropertyValueFactory<Utilisateur, String>("email"));
        mdpUtilisateurtab.setCellValueFactory(new PropertyValueFactory<Utilisateur, String>("mdp"));
        imgUtilisateurtab.setCellValueFactory(new PropertyValueFactory<Utilisateur, ImageView>("img"));
        

        tabutil.setItems(list);
        //search_user();
    }

    
    @FXML
    private void SetValue(MouseEvent event) throws Exception {
        Utilisateur selected;
        selected = tabutil.getSelectionModel().getSelectedItem();
        if (selected != null) {
            String var = "/files/" +selected.getImg();
            Image img = new Image(var,160,160,false,true);
            System.out.println(img);
            nomuser.setText(selected.getNom());
            prenomuser.setText(selected.getPrenom());
            emailuser.setText(selected.getEmail());
            mdpuser.setText(selected.getMdp());
            
            image.setImage(img);

            ID = selected.getId();
            System.out.println(ID);
            System.out.println(selected);

        }
        
    }

    @FXML
    private void ModifierUtilisateur(ActionEvent event) {
        try {
            String rnom = nomuser.getText();
            String rprenom = prenomuser.getText();
            String remail = emailuser.getText();
            String rmdp = mdpuser.getText();
            String rimg = fileName;
            String requete = "UPDATE utilisateur SET nom=? , prenom=? , email=? , mdp=? , img=?  WHERE id=" + ID + "";
            PreparedStatement pst
                    = new MyConnection().cn.prepareStatement(requete);

            pst.setString(1, rnom);
            pst.setString(2, rprenom);
            pst.setString(3, remail);
            pst.setString(4, rmdp);
            pst.setString(5, rimg);


            pst.executeUpdate();
            // Alert 
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setTitle(" Utilisateur!");
            a.setHeaderText(null);
            a.setContentText("Utilisateur Modifié !!!");
            a.showAndWait();

            //*********
            System.out.println("Message modifié!");
        } catch (SQLException ex) {
            Logger.getLogger(UtilisateurCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        AfficherUtilisateur();
    }

    @FXML
    private void SupprimerUtilisateur(ActionEvent event) {

        try {

            String requete = "DELETE FROM utilisateur WHERE id=" + ID + "";
            PreparedStatement pst
                    = new MyConnection().cn.prepareStatement(requete);
            pst.executeUpdate();
            // Alert 
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setTitle(" Utilisateur!");
            a.setHeaderText(null);
            a.setContentText("Utilisateur supprimé !!!");
            a.showAndWait();

            //*********
            System.out.println("Utilisateur supprimé!");
        } catch (SQLException ex) {
            Logger.getLogger(UtilisateurCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        AfficherUtilisateur();
    }

    @FXML
    private void Browse(ActionEvent event) throws IOException {
        image.setVisible(true);
       FileChooser fileChooser = new FileChooser();
      
        FileChooser.ExtensionFilter exjpg = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter exjpg2 = new FileChooser.ExtensionFilter("JPEG (Joint Photographic Experts Group)", "*.jpeg");
        FileChooser.ExtensionFilter expng = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fileChooser.getExtensionFilters().addAll(exjpg, exjpg2, expng);
        fileChooser.setTitle("Choose an image File");

        File file = fileChooser.showOpenDialog(null);

        if (file != null) {
            if (file.length() < 6000000) {

                    fileName = (file.getName());
                Upload u = new Upload();
                u.upload(file);
                Image img =new Image(file.toURI().toString(),100,150,true,true);
            image.imageProperty().unbind();
            image.setImage(img);
            image.setFitWidth(150);
            image.setFitHeight(100);

                System.out.println(fileName);
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Permiss");
                alert.setHeaderText("Permission denied");
                alert.setContentText("Your Image file is too big to upload \nplease choose another image");
            }

        }
    }

    
//    void search_user() {          
//      idUtilisateurtab.setCellValueFactory(new PropertyValueFactory<>("id_suj"));
//        nomUtilisateurtab.setCellValueFactory(new PropertyValueFactory<>("nom"));
//        prenomUtilisateurtab.setCellValueFactory(new PropertyValueFactory<>("prenom"));
//        emailUtilisateurtab.setCellValueFactory(new PropertyValueFactory<>("email"));
//        mdpUtilisateurtab.setCellValueFactory(new PropertyValueFactory<>("mdp"));
//        imgUtilisateurtab.setCellValueFactory(new PropertyValueFactory<>("img"));
//        
//        ObservableList<Utilisateur> dataList = pcr.getUtilisateur();
//        tabutil.setItems(dataList);
//        FilteredList<Utilisateur> filteredData = new FilteredList<>(dataList, b -> true);  
// textrech.textProperty().addListener((observable, oldValue, newValue) -> {
// filteredData.setPredicate(message -> {
//    if (newValue == null || newValue.isEmpty()) {
//     return true;
//    }    
//    String lowerCaseFilter = newValue.toLowerCase();
//    
//    if (message.getEmail().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
//     return true; // Filter matches username
//    } 
//  
//        
//    
//                                
//         else  
//          return false; // Does not match.
//   });
//  });  
//  SortedList<Utilisateur> sortedData = new SortedList<>(filteredData);  
//  sortedData.comparatorProperty().bind(tabutil.comparatorProperty());  
//  tabutil.setItems(sortedData);      
//    }
public static String hashPassword(String txtpassword){
    return BCrypt.hashpw(txtpassword, BCrypt.gensalt()); 
    }
private boolean testNom() {
     
        int nbNonChar = 0;
        for (int i = 1; i < nomuser.getText().trim().length(); i++) {
            char ch = nomuser.getText().charAt(i);
            if (!Character.isLetter(ch)) {
                nbNonChar++;
            }
        }

        if (nbNonChar == 0 && nomuser.getText().trim().length() >= 3) {
          nomcheck.setImage(new Image("Image/checkmark.png"));
            return true;
        } else {
           nomcheck.setImage(new Image("Image/alertemark.png"));
               
            return false;

        }
        
    }
    private boolean testPrenom() {
        int nbNonChar = 0;
        for (int i = 1; i < prenomuser.getText().trim().length(); i++) {
            char ch = prenomuser.getText().charAt(i);
            if (!Character.isLetter(ch)) {
                nbNonChar++;
            }
        }

        if (nbNonChar == 0 && prenomuser.getText().trim().length() >= 3) {
            prenomcheck.setImage(new Image("Image/checkmark.png"));
            return true;
        } else {
            prenomcheck.setImage(new Image("Image/alertemark.png"));
            return false;

        }
    }
        private boolean testPassword() {
        int nbNonChar = 0;
        for (int i = 1; i < mdpuser.getText().trim().length(); i++) {
            char ch = mdpuser.getText().charAt(i);
            if (!Character.isLetter(ch)) {
                nbNonChar++;
            }
        }

        if (nbNonChar == 0 && mdpuser.getText().trim().length() >= 3) {
            passwordcheck.setImage(new Image("Image/checkmark.png"));
            return true;
        } else {
            passwordcheck.setImage(new Image("Image/alertemark.png"));
            return false;

        }
    }
        private boolean testemail() {
                
          String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."
                + "[a-zA-Z0-9_+&*-]+)*@"
                + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
                + "A-Z]{1,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (emailuser.getText() == null) {
            return false;
        }

        if (pat.matcher(emailuser.getText()).matches() == false) {
           emailcheck.setImage(new Image("Image/alertemark.png"));
          //   String erreur = erreur + ("Veuillez verifier votre adresse email\n");
            return false;
//            

        } else {
           emailcheck.setImage(new Image("Image/checkmark.png"));
        }
        return true;
    }
private Boolean testSaisie() {
        String erreur = "";
        if (!testNom()) {
            erreur = erreur + ("Veuillez verifier votre Nom: seulement des caractères et de nombre >= 3 \n");
        }
        if (!testPrenom()) {
            erreur = erreur + ("Veuillez verifier votre Prenom: seulement des caractères et de nombre >= 3");
        }
        if (!testemail()) {
            erreur = erreur + ("Veuillez verifier que votre adresse email est de la forme : ***@***.** \n");
        }
        if (!testPassword()) {
            erreur = erreur + ("Veuillez verifier votre Mot de passe: seulement des caractères et de nombre >= 3");
        }
        

        if ( (!testNom()) || (!testPrenom()) || !testemail() || !testPassword()  ) {
           
           
           
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Erreur de format");
        alert.setHeaderText("Vérifier les champs");
        alert.setContentText(erreur);
        alert.showAndWait();
       
        }

        return  testNom()  ;
    }

    @FXML
    private void nomcheck(KeyEvent event) {
        testNom();
    }

    @FXML
    private void prenomcheck(KeyEvent event) {
        testPrenom();
    }

    @FXML
    private void emailcheck(KeyEvent event) {
        testemail();
    }

    @FXML
    private void passwordcheck(KeyEvent event) {
        testPassword();
    }
}
