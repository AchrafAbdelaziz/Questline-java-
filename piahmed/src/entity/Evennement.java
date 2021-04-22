/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.sql.Date;
import java.time.LocalDate;

/**
 *
 * @author Msi
 */
public class Evennement {
    private int id;
    private int nbrplaces;
    private float prix;
    String nom;
    String destination;
    String image;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    Date dateev;
    public Evennement() {
    }

    public Evennement(int id, int nbrplaces, float prix, String nom, String destination) {
        this.id = id;
        this.nbrplaces = nbrplaces;
        this.prix = prix;
        this.nom = nom;
        this.destination = destination;
    }
    

    public Date getDateev() {
        return dateev;
    }

    public void setDateev(Date dateev) {
        this.dateev = dateev;
    }
    
    public Evennement(int id, String nom, String destination, float prix, int nbrplaces) {
        this.id = id;
        this.nom = nom;
        this.destination = destination;
        this.prix = prix;
        this.nbrplaces = nbrplaces;
        
        
    }
    public Evennement(int id, String nom, String destination, float prix, int nbrplaces,String image) {
        this.id = id;
        this.nom = nom;
        this.destination = destination;
        this.prix = prix;
        this.nbrplaces = nbrplaces;
        this.image=image;
        
        
    }

    public Evennement(int id, String nom, String destination,float prix,int nbrplaces, Date dateev) {
        this.id = id;
        this.nbrplaces = nbrplaces;
        this.prix = prix;
        this.nom = nom;
        this.destination = destination;
        this.dateev = dateev;
    }
     public Evennement(int id, String nom, String destination,float prix,int nbrplaces, Date dateev,String image) {
        this.id = id;
        this.nbrplaces = nbrplaces;
        this.prix = prix;
        this.nom = nom;
        this.destination = destination;
        this.dateev = dateev;
        this.image=image;
    }

    public int getId() {
        return id;
    }

    public Evennement(int nbrplaces, float prix, String nom, String destination,Date dateev) {
        this.nbrplaces = nbrplaces;
        this.prix = prix;
        this.nom = nom;
        this.destination = destination;
        this.dateev=dateev;
    }
      public Evennement(int nbrplaces, float prix, String nom, String destination,Date dateev,String image) {
        this.nbrplaces = nbrplaces;
        this.prix = prix;
        this.nom = nom;
        this.destination = destination;
        this.dateev=dateev;
        this.image=image;
    }
    
    

    
    public void setId(int id) {
        this.id = id;
    }

    public int getNbrplaces() {
        return nbrplaces;
    }

    public void setNbrplaces(int nbrplaces) {
        this.nbrplaces = nbrplaces;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    @Override
    public String toString() {
        return "Evennement{" + "dateev=" + dateev + '}';
    }

    
    
    
}
