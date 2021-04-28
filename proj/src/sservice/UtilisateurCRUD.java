/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sservice;

import entities.Utilisateur;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tools.MyConnection;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author asus
 */
public class UtilisateurCRUD {
   public void addUtilisateur (Utilisateur user) throws SQLException{
    try{
        String requete = "INSERT INTO utilisateur (nom,prenom,email,mdp,img)"
                + "VALUES(?,?,?,?,?)" ;
           PreparedStatement pst =
            new MyConnection().cn.prepareStatement(requete);
    pst.setString(1,user.getNom());
    pst.setString(2,user.getPrenom());
    pst.setString(3,user.getEmail());
    pst.setString(4,user.getMdp());
    pst.setString(5,user.getImg());
  
    pst.executeUpdate();
    System.out.println("Utilisateur  ajoutée!");




    }
    catch (SQLException ex) {
            Logger.getLogger(Utilisateur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
public ObservableList<Utilisateur> getUtilisateur() {

        ObservableList<Utilisateur> UtilisateurList = FXCollections.observableArrayList();
        String requete = "SELECT * FROM utilisateur ";
        try {
            PreparedStatement pst
                    = new MyConnection().cn.prepareStatement(requete);
         
            ResultSet rs;
            try {
                
                rs = pst.executeQuery(requete);
               

                while (rs.next()) {
                   Utilisateur user = new Utilisateur(rs.getInt("id"),rs.getString("nom"),rs.getString("prenom"),rs.getString("email"),rs.getString("mdp"),rs.getString("img"));
                    UtilisateurList.add(user);
                }

            } catch (Exception ex) {
               
                ex.printStackTrace();
            }
        } catch (SQLException ex) {
            Logger.getLogger(UtilisateurCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }

        return UtilisateurList;
    }




public ObservableList<Utilisateur> getUtilisateurByEmail(String email) {

        ObservableList<Utilisateur> UtilisateurList = FXCollections.observableArrayList();
        String requete = "SELECT * FROM utilisateur WHERE email =" + email + "";
        try {
            PreparedStatement pst
                    = new MyConnection().cn.prepareStatement(requete);
         
            ResultSet rs;
            try {
                
                rs = pst.executeQuery(requete);
               

                while (rs.next()) {
                   Utilisateur user = new Utilisateur(rs.getInt("id"),rs.getString("nom"),rs.getString("prenom"),rs.getString("email"),rs.getString("mdp"),rs.getString("img"));
                    UtilisateurList.add(user);
                }

            } catch (Exception ex) {
               
                ex.printStackTrace();
            }
        } catch (SQLException ex) {
            Logger.getLogger(UtilisateurCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }

        return UtilisateurList;
    }
public String Login(Utilisateur u) {
         String role = null;
            String query = "SELECT * FROM utilisateur WHERE  email = ? ";
        try {

//            BCrypt.checkpw(u.getMdp(), u.getMdp());
            PreparedStatement ste = new MyConnection().cn.prepareStatement(query);
            ste.setString(1, u.getEmail());
            System.out.println("get email"+u.getEmail());
 //           ste.setString(2, u.getMdp());
            System.out.println("sout mdp logincrud"+u.getMdp());
           // ste.setString(2, u.getMdp());            
            ResultSet rs = ste.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString("role"));
                u.setEmail(rs.getString(1));
 //               u.setMdp(rs.getString(2));
                
 //               u.setMdp(rs.getString(2));
                System.out.println("sout mdp getsring1"+rs.getString(5));
                System.out.println("sout mdp while"+rs.getString("mdp"));
                u.setRole(rs.getString("role"));
                if (BCrypt.checkpw(u.getMdp(), rs.getString("mdp"))== true){
                    System.out.println("Mot de passe correct");
                    
                }
                else{
                    System.out.println("Erreur mdp");
                return "erreur";
                        }
              //  role = rs.getInt(6);

            }
        } catch (SQLException ex) {
            Logger.getLogger(UtilisateurCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
//        if(u.getRole().equals(""))
//            return null ; // null n existe rien
        return role;
        
        
                     
            
             }
    public Utilisateur FindUserByID(int id) {
        Utilisateur u = new Utilisateur();
        
        try
        {
            System.out.println("debut Recherche");
            Statement stm = new MyConnection().cn.createStatement();
            ResultSet rest=stm.executeQuery("select * from utilisateur where id = "+id);
            
            while(rest.next())
                {
                        
                
                
                    System.out.println("debut recuperation find user by id");
                    u.setId(rest.getInt(1));
                    u.setNom(rest.getString(2));
                    u.setPrenom(rest.getString(3));
                    u.setEmail(rest.getString(4));
                    u.setMdp(rest.getString(5));
                    //BCrypt.checkpw(mdp,rest.getString(4));
                    
                    u.setImg(rest.getString(6));
                    u.setRole(rest.getString(7));

                    System.out.println("user looking for "+id);
                    System.out.println("user founded "+u.getId());
                    System.out.println("role  = "+u.getRole());
                
                
                }
        }
        catch (SQLException ex)
        {
            System.out.println("Probleme affichage");
        }
        return u;
    }
    
}