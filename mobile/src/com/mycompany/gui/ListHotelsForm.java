/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.l10n.DateFormatPatterns;
import com.codename1.l10n.ParseException;

import com.codename1.l10n.SimpleDateFormat;

import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;

import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.ReservationH;
import com.mycompany.services.ServiceReservationH;
import java.util.ArrayList;
import java.util.Date;


/**
 *
 * @author rami
 */
public class ListHotelsForm extends BaseForm {
    Form current;
    public ListHotelsForm(Resources res){
        
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
        
        addTab(swipe,s1, res.getImage("back-logo.jpeg"),"","",res);
        
        //
        
          swipe.setUIID("Container");
        swipe.getContentPane().setUIID("Container");
        swipe.hideTabs();

        ButtonGroup bg = new ButtonGroup();
        int size = Display.getInstance().convertToPixels(1);
        Image unselectedWalkthru = Image.createImage(size, size, 0);
        Graphics g = unselectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAlpha(100);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        Image selectedWalkthru = Image.createImage(size, size, 0);
        g = selectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        RadioButton[] rbs = new RadioButton[swipe.getTabCount()];
        FlowLayout flow = new FlowLayout(CENTER);
        flow.setValign(BOTTOM);
        Container radioContainer = new Container(flow);
        for (int iter = 0; iter < rbs.length; iter++) {
            rbs[iter] = RadioButton.createToggle(unselectedWalkthru, bg);
            rbs[iter].setPressedIcon(selectedWalkthru);
            rbs[iter].setUIID("Label");
            radioContainer.add(rbs[iter]);
        }

        rbs[0].setSelected(true);
        swipe.addSelectionListener((i, ii) -> {
            if (!rbs[ii].isSelected()) {
                rbs[ii].setSelected(true);
            }
        });

        Component.setSameSize(radioContainer, s1, s2);
        add(LayeredLayout.encloseIn(swipe, radioContainer));

        ButtonGroup barGroup = new ButtonGroup();
        RadioButton mesListes = RadioButton.createToggle("HOTELS", barGroup);
        mesListes.setUIID("SelectBar");
        RadioButton liste = RadioButton.createToggle("Autres", barGroup);
        liste.setUIID("SelectBar");
        RadioButton partage = RadioButton.createToggle("Reserver", barGroup);
        partage.setUIID("SelectBar");
        Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");


        mesListes.addActionListener((e) -> {
               
         ListHotelsForm a = new ListHotelsForm(res);
           a.show();
            refreshTheme();
        });
        
        partage.addActionListener((e) -> {
               
         AjoutReservationHForm a = new AjoutReservationHForm(res);
           a.show();
            refreshTheme();
        });

        add(LayeredLayout.encloseIn(
                GridLayout.encloseIn(3, mesListes, liste, partage),
                FlowLayout.encloseBottom(arrow)
        ));

        mesListes.setSelected(true);
        arrow.setVisible(false);
        addShowListener(e -> {
            arrow.setVisible(true);
            updateArrowPosition(mesListes, arrow);
        });
        bindButtonSelection(mesListes, arrow);
        bindButtonSelection(liste, arrow);
        bindButtonSelection(partage, arrow);
        // special case for rotation
        addOrientationListener(e -> {
            updateArrowPosition(barGroup.getRadioButton(barGroup.getSelectedIndex()), arrow);
        });

        
        ArrayList<ReservationH>list = ServiceReservationH.getInstance().AffichageReservationH();
        
        for(ReservationH rh : list){

            //System.out.println(rh.getUser_id_id().getClass().getSimpleName());
            
            String urlImage = "back-logo.jpeg";
            
            Image placeHolder = Image.createImage(120,90);
            EncodedImage enc = EncodedImage.createFromImage(placeHolder,false);
            URLImage urlim =  URLImage.createToStorage(enc, urlImage, urlImage, URLImage.RESIZE_SCALE);
            
            
            
            addButton(urlim,rh.getConfirmation(),rh.getUser_id_id(),rh.getRoom_id_id(),rh.getId(),rh.getDebut(),rh.getFin(),res,rh);
            
            
            
            ScaleImageLabel image = new  ScaleImageLabel(urlim);
            Container   containerImg = new Container();
            image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
                        //System.out.println(rh.getConfirmation());
                        
                        
                        

            
            //addButton(urlim,rh.getUser_id_id(),rh.getRoom_id_id(),rh.getDebut(),rh.getFin(),rh.getConfirmation());
           // addButton(urlim,3,2,rh.getDebut(),rh.getFin(),rh.getConfirmation());
        }
        
        
    }
    
    
    
    
    private void addTab(Tabs swipe ,Label spacer , Image image, String string, String string2, Resources res) {
        int size = Math.min(Display.getInstance().getDisplayWidth(),Display.getInstance().getDisplayHeight() );
        
        if (image.getHeight() < size){
            image = image.scaledHeight(size);
        }
        
        if(image.getHeight() > Display.getInstance().getDisplayHeight() / 2) {
            image = image.scaledHeight(Display.getInstance().getDisplayHeight() / 2);
        }
        
        ScaleImageLabel imageScale = new ScaleImageLabel(image);
        imageScale.setUIID("Container");
        imageScale.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        
        Label overLay = new Label("","ImageOverlay");
        
        Container page1 = 
                LayeredLayout.encloseIn(
                imageScale,
                        overLay,
                        BorderLayout.south(
                        BoxLayout.encloseY(
                        new SpanLabel("LargeWhiteText"),
                                        spacer
                        )
                    )
                );
        
        
        swipe.addTab("", res.getImage("back-logo.jpeg"),page1);
    }
    
    public void bindButtonSelection(Button btn, Label l){
        
        btn.addActionListener(e-> {
            if(btn.isSelected()) {
                updateArrowPosition(btn,l);
            }
        });
    }

    private void updateArrowPosition(Button btn, Label l) {
        l.getUnselectedStyle().setMargin(LEFT,btn.getX() + btn.getWidth() / 2 - l.getWidth() / 2);
        l.getParent().repaint();
    }
//    private void addButton(URLImage urlim,Integer user_id_id, Integer room_id_id, Date debut, Date fin, String confirmation) {

    private void addButton(URLImage img,String confirmation, Integer user, Integer room, Integer id, Date datedeb, Date datefin,Resources res, ReservationH rh) {

           Container cnt = new Container(new BorderLayout());
           String userC;
           userC = user+"";
           
           String idC;
           idC = id+"";
           
           String roomC;
           roomC = room+"";
           
           String debC;
           debC = datedeb+"";
           
           String finC;
           finC = datefin+"";
          
          //cnt.add(BorderLayout.WEST, new Label(idC));
           cnt.add(BorderLayout.NORTH, new Label(idC+" "+userC+" "+roomC,"TextFieldRed"));
          cnt.add(BorderLayout.CENTER, new Label(debC+" "+finC));
          cnt.add(BorderLayout.SOUTH, new Label(confirmation));
          //cnt.add(BorderLayout.CENTER, new TextField(confirmation).);

          //cnt.add(BorderLayout.CENTER, BoxLayout.encloseY(ta5));
          
          //suprimer ic
          Label lSupprimer = new Label(" ");
          lSupprimer.setUIID("NewsTopLine");
          Style supprimerStyle = new Style(lSupprimer.getUnselectedStyle());
          supprimerStyle.setFgColor(0xf21f1f);
          FontImage supprimerImage = FontImage.createMaterial(FontImage.MATERIAL_DELETE, supprimerStyle);
          lSupprimer.setIcon(supprimerImage);
          lSupprimer.setTextPosition(RIGHT);  
          
           //update ic
          Label lModifier = new Label(" ");
          lModifier.setUIID("NewsTopLine");
          Style modifierStyle = new Style(lModifier.getUnselectedStyle());
          modifierStyle.setFgColor(0xf151f5);
          
          FontImage mFontImage = FontImage.createMaterial(FontImage.MATERIAL_UPDATE, modifierStyle);
          lModifier.setIcon(mFontImage);
          lModifier.setTextPosition(LEFT);  
          
          lModifier.addPointerPressedListener(l->{
               try {
                   //System.out.println("updaaate");
                   new ModifierReservationHForm(res,rh).show();
               } catch (ParseException ex) {
                   System.out.println("modifier ma t3adetsh bro");
               }
          });
          
          
          
          
          //el delete
          
          lSupprimer.addPointerPressedListener(l->{
                  Dialog dig = new Dialog("DELETE BRO");
          
                  if (dig.show("DELETE "," you gonna delete ?"," NAH","BET")) {
                      dig.dispose();
                  }
                  else {
                      dig.dispose();
                      ServiceReservationH.getInstance().deleteReservation(id);
                      ListHotelsForm a = new ListHotelsForm(res);
                         a.show();
                      refreshTheme();
                     
                       //revalidate();
                       //refreshTheme();
                       
                      
                  }
                  
                  
          });
          
          
          cnt.add(BorderLayout.EAST,lSupprimer);
          cnt.add(BorderLayout.NORTH,lModifier);
//          cnt.add(BorderLayout.CENTER_BEHAVIOR_TOTAL_BELOW,lModifier);
          
          add(cnt);
          
           
    }

    
    
    
       
}
