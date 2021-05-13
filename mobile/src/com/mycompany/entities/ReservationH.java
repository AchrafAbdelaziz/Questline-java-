/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;

//import java.sql.Date;


import java.util.Date;


/**
 *
 * @author rami
 */
public class ReservationH {
    private Integer id;
    private Integer user_id_id;
    private Integer room_id_id;
    private Date debut;
    private Date fin;
    private String confirmation;
    private String email;
    private String type;

    public ReservationH() {
    }

    public ReservationH(Integer user_id_id, Integer room_id_id, Date debut, Date fin, String confirmation, String email, String type) {
        this.user_id_id = user_id_id;
        this.room_id_id = room_id_id;
        this.debut = debut;
        this.fin = fin;
        this.confirmation = confirmation;
        this.email = email;
        this.type = type;
    }

    
    
    
    public ReservationH(Integer id, Integer user_id_id, Integer room_id_id, Date debut, Date fin, String confirmation, String email, String type) {
        this.id = id;
        this.user_id_id = user_id_id;
        this.room_id_id = room_id_id;
        this.debut = debut;
        this.fin = fin;
        this.confirmation = confirmation;
        this.email = email;
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser_id_id() {
        return user_id_id;
    }

    public void setUser_id_id(Integer user_id_id) {
        this.user_id_id = user_id_id;
    }

    public Integer getRoom_id_id() {
        return room_id_id;
    }

    public void setRoom_id_id(Integer room_id_id) {
        this.room_id_id = room_id_id;
    }

    public Date getDebut() {
        return debut;
    }

    public void setDebut(Date debut) {
        this.debut = debut;
    }

    public Date getFin() {
        return fin;
    }

    public void setFin(Date fin) {
        this.fin = fin;
    }

    public String getConfirmation() {
        return confirmation;
    }

    public void setConfirmation(String confirmation) {
        this.confirmation = confirmation;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    
    
}
