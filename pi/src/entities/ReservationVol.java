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
public class ReservationVol {
    private int id;
    private int nombre_personne;
    private Date date_res;
    String vols_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNombre_personne() {
        return nombre_personne;
    }

    public void setNombre_personne(int nombre_personne) {
        this.nombre_personne = nombre_personne;
    }

    public Date getDate_res() {
        return date_res;
    }

    public void setDate_res(Date date_res) {
        this.date_res = date_res;
    }

    public String getVols_id() {
        return vols_id;
    }

    public void setVols_id(String vols_id) {
        this.vols_id = vols_id;
    }

    public ReservationVol(int id, int nombre_personne, Date date_res, String vols_id) {
        this.id = id;
        this.nombre_personne = nombre_personne;
        this.date_res = date_res;
        this.vols_id = vols_id;
    }

    public ReservationVol(int nombre_personne, Date date_res) {
        this.nombre_personne = nombre_personne;
        this.date_res = date_res;
    }

    @Override
    public String toString() {
        return "ReservationVol{" + "id=" + id + ", nombre_personne=" + nombre_personne + ", date_res=" + date_res + ", vols_id=" + vols_id + '}';
    }
    
    
}
