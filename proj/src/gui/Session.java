/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Utilisateur;
import sservice.UtilisateurCRUD;
/**
 *
 * @author asus
 */
public class Session {
    private static int id;
     private static String pin = "";
     public  static Utilisateur connectedUser;
    
    public static void start(int currentUserID) {
        id = currentUserID;
        UtilisateurCRUD us = new UtilisateurCRUD();
        connectedUser = us.FindUserByID(getCurrentSession());
      
    }


     public static int getCurrentSession() {
        if (id != -1) 
            
            return id;
       
        return -1;
        
    }
    public static void close() throws Exception {
        if (id != -1) {
            id = -1;
        } else {
            throw new Exception("Session has not started yet!");
        }
    }
       public static void generatePIN() {
        int randomPIN = (int) (Math.random() * 9000) + 1000;
        pin = "" + randomPIN;
    } 

    public static String getPin() {
        return pin;
    }
}
