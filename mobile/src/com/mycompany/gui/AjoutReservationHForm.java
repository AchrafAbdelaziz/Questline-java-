/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.InfiniteProgress;
import com.codename1.l10n.DateFormatPatterns;

import com.codename1.l10n.SimpleDateFormat;

import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Tabs;

import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.ReservationH;
import com.mycompany.services.ServiceReservationH;
import java.util.Date;


/**
 *
 * @author RAMI
 */
public class AjoutReservationHForm extends BaseForm {
    
   
    
    Form current;
    public AjoutReservationHForm(Resources res ){
        super ("Newsfeed",BoxLayout.y());
        
        
        Toolbar tb = new Toolbar(true);
        current = this ;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Ajouter Reservation Hotel");
        getContentPane().setScrollVisible(false);
        
        tb.addSearchCommand(e-> {
        
        });
        
        Tabs swipe = new Tabs();
        
        Label s1 = new Label();
        Label s2 = new Label();
        
        //addTab(swipe,res.getImage(id))
        
        
        
        
        TextField user = new TextField("","entrer user id");
        user.setUIID("TextFieldBlack");
        addStringValue("user",user); 
        
        TextField room = new TextField("","entrer room id");
        room.setUIID("TextFieldBlack");
        addStringValue("room",room); 
        
         TextField debut = new TextField("","entrer date debut");
        debut.setUIID("TextFieldBlack");
        addStringValue("debut",debut); 
        
         TextField fin = new TextField("","entrer date fin");
        fin.setUIID("TextFieldBlack");
        addStringValue("fin",fin); 
        
         TextField confirmation = new TextField("","entrer confirmation ");
        confirmation.setUIID("TextFieldBlack");
        addStringValue("confirmation",confirmation); 

        
        Button btnAjouter = new Button("Ajouter");
        addStringValue("", btnAjouter);
        
        
        //on click button event
        
        btnAjouter.addActionListener((e) -> {
            
             /*ReservationH
        private Integer id;
    private Integer user_id_id;
    private Integer room_id_id;
    private Date debut;
    private Date fin;
    private String confirmation;
    private String email;
    private String type;
    */
        
        
                try {
                    if (user.getText()=="" || room.getText()=="" || debut.getText()=="" || fin.getText()=="" || confirmation.getText()=="" ){
                        Dialog.show("Veulliez vérifier les données","","Annuler","OK");
                        
                    }
                    else {
                        InfiniteProgress ip = new InfiniteProgress(); //loading start
                        
                        final Dialog iDialog = ip.showInfiniteBlocking();
                        
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                        //Integer user_id_id, Integer room_id_id, Date debut, Date fin, String confirmation

                        
                        Date datedeb = format.parse(String.valueOf(debut.getText()));     
                        Date datefin = format.parse(String.valueOf(fin.getText())); 
                        System.out.println(datedeb);
                        ReservationH resH = new ReservationH(
                                Integer.valueOf(user.getText()),
                                Integer.valueOf(room.getText()),
                                datedeb,
                                datefin,
                                String.valueOf(confirmation.getText()).toString()

                                ); 
                                System.out.println("data Reservation Hotel =="+resH);
                        
                                ServiceReservationH.getInstance().ajouterReservationH(resH);
                                
                                iDialog.dispose(); //loading end
                                
                                refreshTheme();
                    }
                }catch(Exception ex ){
                    ex.printStackTrace();
                } 
    
    
    }) ;
    }
    private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s,"PadderLabel"))
        .add(BorderLayout.CENTER,v));
        add(createLineSeparator(0xeeeeee));
    }

    
    
    
    
    
    
}

