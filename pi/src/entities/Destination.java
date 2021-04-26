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
public class Destination {
    private int id;
    private String nom;

    public Destination() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

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

    public Destination(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public Destination(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "Destination{" + "id=" + id + ", nom=" + nom + '}';
    }
    
}
