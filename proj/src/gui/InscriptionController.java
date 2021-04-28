/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import entities.Utilisateur;
import static gui.AddUtilisateurController.hashPassword;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import org.mindrot.jbcrypt.BCrypt;
import sservice.JavaMailUtil;
import sservice.Upload;
import sservice.UtilisateurCRUD;
import tools.VoiceUtils;


/**
 * FXML Controller class
 *
 * @author asus
 */
public class InscriptionController implements Initializable {

    @FXML
    private JFXTextField nomuser;
    @FXML
    private JFXTextField prenomuser;
    @FXML
    private JFXTextField emailuser;
    @FXML
    private JFXPasswordField mdpuser;
    @FXML
    private JFXButton btnvalider;
    @FXML
    private ImageView nomcheck;
    @FXML
    private ImageView prenomcheck;
    @FXML
    private ImageView passwordcheck;
    @FXML
    private ImageView emailcheck;
    @FXML
    private JFXButton Browse;
    @FXML
    private ImageView image;
    int ID;
    UtilisateurCRUD pcr = new UtilisateurCRUD();
    Utilisateur u = new Utilisateur();
    private File file;
     private String fileName = "No picture";
    @FXML
    private JFXButton btnhav;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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

    @FXML
    private void AjoutUtilisateur(ActionEvent event) throws Exception {
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
        JavaMailUtil.sendMail("achraf.abdelaziz@esprit.tn");
        
        }
            //*********
        
        
        
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

        if (nbNonChar == 0 && mdpuser.getText().trim().length() >= 8) {
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
            erreur = erreur + ("Veuillez verifier votre Mot de passe: seulement des caractères et de nombre >= 8");
        }
        

        if ( (!testNom()) || (!testPrenom()) || !testemail() || !testPassword()  ) {
           
           
           
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Erreur de format");
        alert.setHeaderText("Vérifier les champs");
        alert.setContentText(erreur);
        alert.showAndWait();
       
        }

        return  testNom() && testPrenom() && testPassword() && testemail()  ;
    }
    public static String hashPassword(String txtpassword){
    return BCrypt.hashpw(txtpassword, BCrypt.gensalt()); 
    }

    @FXML
    private void HaveAccount(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
            Parent root=loader.load();
            btnhav.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
