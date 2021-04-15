/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author AZIZ AKARI
 */
public class Vol {
 private int id;
 private int prix;
 private int nbplace;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public int getNbplace() {
        return nbplace;
    }

    public void setNbplace(int nbplace) {
        this.nbplace = nbplace;
    }

    public Vol(int prix, int nbplace) {
        this.prix = prix;
        this.nbplace = nbplace;
    }

    public Vol(int id, int prix, int nbplace) {
        this.id = id;
        this.prix = prix;
        this.nbplace = nbplace;
    }

    @Override
    public String toString() {
        return "Vol{" + "id=" + id + ", prix=" + prix + ", nbplace=" + nbplace + '}';
    }

}