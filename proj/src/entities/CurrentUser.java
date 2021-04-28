/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;
import entities.Utilisateur;
/**
 *
 * @author asus
 */
public class CurrentUser {
    public static int id;
    public static String nom;
    public static String prenom;
    public static String email;
    public static String mdp;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static String getNom() {
        return nom;
    }

    public static void setNom(String nom) {
        CurrentUser.nom = nom;
    }

    public static String getPrenom() {
        return prenom;
    }

    public static void setPrenom(String prenom) {
        CurrentUser.prenom = prenom;
    }

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        CurrentUser.email = email;
    }

    public static String getMdp() {
        return mdp;
    }

    public static void setMdp(String mdp) {
        CurrentUser.mdp = mdp;
    }
    
    public CurrentUser(Utilisateur m) {
        id = m.getId();
        nom = m.getNom();
        prenom = m.getPrenom();
        email = m.getEmail();
        mdp = m.getMdp();
      
                
    }

    @Override
    public String toString() {
        return "CurrentUser{" + id + nom + prenom + email  + mdp +'}';
    }

    public CurrentUser() {
    }

}
