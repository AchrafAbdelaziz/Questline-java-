/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;

/**
 *
 * @author AZIZ AKARI
 */
public class Vol {
    private int id;
    private Date date;
    private float prix;
    private int nbplace;
    String destination_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public int getNbplace() {
        return nbplace;
    }

    public void setNbplace(int nbplace) {
        this.nbplace = nbplace;
    }

    public String getDestination_id() {
        return destination_id;
    }

    public void setDestination_id(String destination_id) {
        this.destination_id = destination_id;
    }

    @Override
    public String toString() {
        return "Vol{" + "id=" + id + ", date=" + date + ", prix=" + prix + ", nbplace=" + nbplace + ", destination_id=" + destination_id + '}';
    }

    public Vol(int id, Date date, float prix, int nbplace, String destination_id) {
        this.id = id;
        this.date = date;
        this.prix = prix;
        this.nbplace = nbplace;
        this.destination_id = destination_id;
    }

    public Vol(Date date, float prix, int nbplace) {
        this.date = date;
        this.prix = prix;
        this.nbplace = nbplace;
    }
    
    
    
}
