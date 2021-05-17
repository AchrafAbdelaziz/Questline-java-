/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.FloatingHint;
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.DateFormatPatterns;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.ReservationH;
import com.mycompany.services.ServiceReservationH;
import java.util.Date;


/**
 *
 * @author rami
 */
public class ModifierReservationHForm extends BaseForm {
    Form current;
    public ModifierReservationHForm(Resources res, ReservationH r) throws ParseException{
         super ("Newsfeed",BoxLayout.y());
        
        
        Toolbar tb = new Toolbar(true);
        current = this ;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Ajouter Reservation Hotel");
        getContentPane().setScrollVisible(false);
        
        
        super.addSideMenu(res);
        
        
         
        TextField user = new TextField(r.getUser_id_id()+"" ,"entrer user id",20, TextField.ANY);

        
        TextField room = new TextField(r.getRoom_id_id()+"","entrer room id",20, TextField.ANY);
        
        
                        Date datedeb = new Date(r.getDebut().getTime());
                         Date datefin = new Date(r.getFin().getTime());
                        DateFormat f = new SimpleDateFormat("yyyy-MM-dd");
                        String d = f.format(datedeb);
                        String fx = f.format(datefin);
        
        
         TextField debut = new TextField(d+"","entrer debut",20, TextField.ANY);

        
         TextField fin = new TextField(fx+"","entrer fin",20, TextField.ANY);

        
         TextField confirmation = new TextField(r.getConfirmation(),"entrer confirmation",20, TextField.ANY);

        user.setUIID("NewsTopLine");
        room.setUIID("NewsTopLine");
        debut.setUIID("NewsTopLine");
        fin.setUIID("NewsTopLine");
        confirmation.setUIID("NewsTopLine");
        
        user.setSingleLineTextArea(true);
        room.setSingleLineTextArea(true);
        debut.setSingleLineTextArea(true);
        fin.setSingleLineTextArea(true);
        confirmation.setSingleLineTextArea(true);
        
        Button btnModifier = new Button("Modifier");
        
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                        SimpleDateFormat formatp = new 
                        SimpleDateFormat(DateFormatPatterns.ISO8601);
                       
                         //date->string->date
                       //  Date fin1 = format.parse(fin.getText());
                         
                      /*  Date datedeb = new Date(r.getDebut().getTime());
                         Date datefin = new Date(r.getFin().getTime());
                        DateFormat f = new SimpleDateFormat("yyyy-MM-dd");
                        String d = f.format(datedeb);
                        String fx = f.format(datefin);
                        System.out.println(d);
                        System.out.println(fx);*/
                        
                        

                     
                        //Integer user_id_id, Integer room_id_id, Date debut, Date fin, String confirmation

                        
                        debut.addActionListener(a->{
             try {  
                 Date datedebX = format.parse(debut.getText());
                  Date datefinX = format.parse(fin.getText());
                  System.out.println(debut.getText());
                  r.setDebut(datedebX);
                     r.setFin(datefinX);
             } catch (ParseException ex) {
                 System.out.println("pewpewp");
             }
                         
                        
                        
                        });
                        
                         

            


                        
                            
        btnModifier.addPointerPressedListener(lb->{
            
            //System.out.println("ti chbik");
            r.setConfirmation(confirmation.getText());
            //r.setUser_id_id(Integer.parseInt(user.getText()));
            r.setRoom_id_id(Integer.parseInt(room.getText()));
            
           
           // System.out.println(datedebX);
            

            ServiceReservationH.getInstance().modifierReservationH(r);
            new ListHotelsForm(res).show();
        });
        //appel func
        
       /* if(ServiceReservationH.getInstance().modifierReservationH(r)){
            new ListHotelsForm(res).show();
        }*/
        Button btnAnnuler = new Button("Annuler");
        btnAnnuler.addActionListener(e->{
            new ListHotelsForm(res).show();
        });
        Label l2 = new Label("");
        Label l3 = new Label("");
        Label l4 = new Label("");
        Label l5 = new Label("");
        Label l1 = new Label();
        Container content = BoxLayout.encloseY(
                l1,l2,
                new FloatingHint(room),
                createLineSeparator(),
                new FloatingHint(debut),
                createLineSeparator(),
                new FloatingHint(fin),
                createLineSeparator(),
                new FloatingHint(confirmation),
                btnModifier,
                btnAnnuler
        );
        add(content);
        show();
        
    }
    
}
