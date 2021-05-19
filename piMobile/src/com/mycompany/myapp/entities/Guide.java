/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author acer
 */
public class Guide {
    
    private int id;
    private int voyage_id;
    private String nom;
    private String prenom;
    private String email;
    private int num_tel;

    public Guide() {
    }

    public Guide(int id, int voyage_id, String nom, String prenom, String email, int num_tel) {
        this.id = id;
        this.voyage_id = voyage_id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.num_tel = num_tel;
    }

    public Guide(int voyage_id, String nom, String prenom, String email, int num_tel) {
        this.voyage_id = voyage_id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.num_tel = num_tel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVoyage_id() {
        return voyage_id;
    }

    public void setVoyage_id(int voyage_id) {
        this.voyage_id = voyage_id;
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

    public int getNum_tel() {
        return num_tel;
    }

    public void setNum_tel(int num_tel) {
        this.num_tel = num_tel;
    }

    @Override
    public String toString() {
        return "Guide{" + "id=" + id + ", voyage_id=" + voyage_id + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", num_tel=" + num_tel + '}';
    }

    
    
    
}
