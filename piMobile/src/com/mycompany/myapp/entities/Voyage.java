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
public class Voyage {
    
    private int id;
    private String destination;
    private int prix;
    private String periode;
    private String image_name;

    public Voyage() {
    }

    public Voyage(int id, String destination, int prix, String periode, String image_name) {
        this.id = id;
        this.destination = destination;
        this.prix = prix;
        this.periode = periode;
        this.image_name = image_name;
    }

    public Voyage(String destination, int prix, String periode, String image_name) {
        this.destination = destination;
        this.prix = prix;
        this.periode = periode;
        this.image_name = image_name;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public String getPeriode() {
        return periode;
    }

    public void setPeriode(String periode) {
        this.periode = periode;
    }

    public String getImage_name() {
        return image_name;
    }

    public void setImage_name(String image_name) {
        this.image_name = image_name;
    }

    @Override
    public String toString() {
        return "Voyage{" + "id=" + id + ", destination=" + destination + ", prix=" + prix + ", periode=" + periode + ", image_name=" + image_name + '}';
    }

    
}

   
    
    
