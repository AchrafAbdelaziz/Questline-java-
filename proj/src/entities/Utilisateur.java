/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import javafx.scene.image.ImageView;

/**
 *
 * @author asus
 */
public class Utilisateur {
    private int id;
    private String nom;
    private String prenom;
    private String email;
    private String img;
    private String mdp;
    private ImageView photo_view;
    private String role;
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public ImageView getPhoto_view() {
        return photo_view;
    }

    public void setPhoto_view(ImageView photo_view) {
        this.photo_view = photo_view;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    

    public Utilisateur() {
    }

    public Utilisateur( String nom, String prenom, String email, String mdp) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.mdp = mdp;
    }

    public Utilisateur(int id, String nom, String prenom, String email, String mdp) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.mdp = mdp;
    }

    public Utilisateur(String nom, String prenom, String email, String img, String mdp) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.img = img;
        this.mdp = mdp;
    }

    public Utilisateur(int id, String nom, String prenom, String email, String mdp, String img) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.mdp = mdp;
        this.img = img;
    }

    public Utilisateur(int id, String nom, String prenom, String email, String img, String mdp, String role) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.img = img;
        this.mdp = mdp;
        this.role = role;
    }

    @Override
    public String toString() {
        return "Utilisateur{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", img=" + img + ", mdp=" + mdp + '}';
    }
    
}
