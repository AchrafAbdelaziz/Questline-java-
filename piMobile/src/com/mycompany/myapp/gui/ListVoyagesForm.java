/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.FloatingActionButton;
import com.codename1.components.MultiButton;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.TOP;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Voyage;
import com.mycompany.myapp.services.ServiceVoyage;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author bhk
 */
public class ListVoyagesForm extends SideMenuBaseForm{

    List<Voyage> lse = new ArrayList();
    
    public ListVoyagesForm(Resources res) {
       super("Voyages disponibles", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        //setTitle("Newsfeed");
        getContentPane().setScrollVisible(false);
        
        super.setupSideMenu(res);
        tb.addSearchCommand(e -> {});
        
        Tabs swipe = new Tabs();

        Label spacer1 = new Label();
        Label spacer2 = new Label();
        addTab(swipe, res.getImage("voyy.png"), spacer1, "  ", "", "");
      // addTab(swipe, res.getImage("voy.png"), spacer2, "100 Likes  ", "66 Comments", "Dogs are cute: story at 11");
                
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
        for(int iter = 0 ; iter < rbs.length ; iter++) {
            rbs[iter] = RadioButton.createToggle(unselectedWalkthru, bg);
            rbs[iter].setPressedIcon(selectedWalkthru);
            rbs[iter].setUIID("Label");
            radioContainer.add(rbs[iter]);
        }
                
        rbs[0].setSelected(true);
        swipe.addSelectionListener((i, ii) -> {
            if(!rbs[ii].isSelected()) {
                rbs[ii].setSelected(true);
            }
        });
        
        Component.setSameSize(radioContainer, spacer1, spacer2);
        add(LayeredLayout.encloseIn(swipe, radioContainer));
        
        
        Container remainingTasks = BoxLayout.encloseY(
                        new Label("Reservations", "CenterTitle"),
                        new Label("Vols", "CenterSubTitle")
                        
                );
        remainingTasks.setUIID("RemainingTasks");
        Container completedTasks = BoxLayout.encloseY(
                        new Label("paiemets", "CenterSubTitle"),
                        new Label("Donnez avis", "CenterTitle")
                        
        );
        completedTasks.setUIID("CompletedTasks");

      /*  Container titleCmp = BoxLayout.encloseY(
                        //FlowLayout.encloseIn(menuButton),
                        BorderLayout.centerAbsolute(
                                BoxLayout.encloseY(
                                    new Label("Jardin d'enfant ", "Title"),
                                    new Label("OUELDY", "SubTitle")
                                )
                            ).add(BorderLayout.WEST, profilePicLabel),
                        GridLayout.encloseIn(2, remainingTasks, completedTasks)
                );*/
        
        
        /*FloatingActionButton fab = FloatingActionButton.createFAB(FontImage.MATERIAL_ADB);
        fab.getAllStyles().setMarginUnit(Style.BACKGROUND_GRADIENT_LINEAR_HORIZONTAL);
        fab.getAllStyles().setMargin(BOTTOM, completedTasks.getPreferredH() - fab.getPreferredH() / 2);
        tb.setTitleComponent(fab.bindFabToContainer(titleCmp, CENTER, BOTTOM));*/
                        
        //add(new Label("Today", "TodayTitle"));
        
        FontImage arrowDown = FontImage.createMaterial(FontImage.MATERIAL_KEYBOARD_ARROW_DOWN, "Label", 3);
        
        
        
         setLayout(new BoxLayout(BoxLayout.Y_AXIS));
         Container C1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
         Label titre = new Label("","WelcomeBlue");
         //setLayout(new FlowLayout(CENTER,TOP));
               C1.add(titre);
               add(C1);
               
 
    
        ServiceVoyage taskServiceE = new ServiceVoyage();
        List<Voyage> events = taskServiceE.getAllVoyages();
        if (events!= null && !events.isEmpty()) {
            for (Voyage event : events) {
               
                Container C2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
  Container C3 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
    Container C4 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
 Container C5 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
              Container C6 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
               Container C7 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
               Container C8 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
              
               
                Font font = titre.getUnselectedStyle().getFont();
                Font newFont = Font.createSystemFont(font.getFace(), Font.STYLE_BOLD, font.getSize());
                titre.getUnselectedStyle().setFont(newFont);
                SpanLabel dest = new SpanLabel("Destination:   " + event.getDestination());
                SpanLabel prix = new SpanLabel("Prix:    " + event.getPrix());
                SpanLabel per = new SpanLabel("Periode:    " + event.getPeriode());
                //SpanLabel img = new SpanLabel("Image:   " + event.getImage_name());
              // PickerComponent date = PickerComponent.createDate(new Date());
               // System.out.println("");
                
               Button modifier = new Button("Modifier");
              Button supp = new Button("Supprimer");
              
          supp.addActionListener(sup -> {
             taskServiceE.deletVoyage(event);
            
             new ListVoyagesForm(res).show();
            });
          modifier.addActionListener(mod -> {
            
            new UpdateVoyageForm(res,event).show();
            });
           
               
            /*   Button participer = new Button("Participer");*/
                 SpanLabel ligne = new SpanLabel("__________________________________________________  ", "BlueSeparatorLine" );
               /*  participer.addActionListener((evt) -> {
            new ParticiperClub(club).show();
            
            
           
                Dialog.show("SUCCESS", "vous aver participer au club", "OK", null);
            
        });*/
                  
               
                
                   //  C1.add(titre);
                C2.add(dest);
                C3.add(prix);
                C4.add(per);
               // C7.add(date);
               C8.add(ligne);
              // C4.add(branche);
              
              //  C5.add(C1);
                C5.add(C2);
                C5.add(C3);
                C5.add(C4);
                C5.add(C6);
                C5.add(C7);
               C5.add(C8);
                
                add(C5);
           
                super.add(modifier);
                super.add(supp);
            }
            
            
               /* TextField searchField;
searchField = new TextField("", "Voyages List");
searchField.getHintLabel().setUIID("Title");
searchField.setUIID("Title");
getToolbar().setTitleComponent(searchField);
//if field content changed
searchField.addDataChangeListener((i1, i2) -> {
String t = searchField.getText();
if(t.length() < 1) {
for(Component cmp : getContentPane()) {
cmp.setHidden(false);
cmp.setVisible(true);
}
} else {
t = t.toLowerCase();
for(Component cmp: getContentPane()) {
//tekhou el val ta3 el champ : champ li 3malt 3lih el recherche type span label (emplacement : container->container->spanlabel )
String val = ((SpanLabel) ((Container)((Container) cmp).getComponentAt(0)).getComponentAt(0)).getText();
System.out.println( val );
boolean show = val != null && val.toLowerCase().indexOf(t) > -1;
cmp.setHidden(!show);
cmp.setVisible(show);
}
}
getContentPane().animateLayout(250);
});*/
  
            
        }
        
    }

 
    
  
    private void addTab(Tabs swipe, Image img, Label spacer, String likesStr, String commentsStr, String text) {
        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());
/*        if(img.getHeight() < size) {
            img = img.scaledHeight(size);
        }*/
        Label likes = new Label(likesStr);
        Style heartStyle = new Style(likes.getUnselectedStyle());
        heartStyle.setFgColor(0xff2d55);
        FontImage heartImage = FontImage.createMaterial(FontImage.MATERIAL_FAVORITE, heartStyle);
        likes.setIcon(heartImage);
        likes.setTextPosition(RIGHT);

        Label comments = new Label(commentsStr);
        FontImage.setMaterialIcon(comments, FontImage.MATERIAL_CHAT);
        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 2) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 2);
        }
        ScaleImageLabel image = new ScaleImageLabel(img);
        image.setUIID("Container");
        image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        Label overlay = new Label(" ", "ImageOverlay");
        
        Container page1 = 
            LayeredLayout.encloseIn(
                image,
                overlay,
                BorderLayout.south(
                    BoxLayout.encloseY(
                            new SpanLabel(text, "LargeWhiteText"),
                            FlowLayout.encloseIn(likes, comments),
                            spacer
                        )
                )
            );

        swipe.addTab("", page1);
    }
    
    private void addButtonBottom(Image arrowDown, String text, int color, boolean first) {
        MultiButton finishLandingPage = new MultiButton(text);
        finishLandingPage.setEmblem(arrowDown);
        finishLandingPage.setUIID("Container");
        finishLandingPage.setUIIDLine1("TodayEntry");
        finishLandingPage.setIcon(createCircleLine(color, finishLandingPage.getPreferredH(),  first));
        finishLandingPage.setIconUIID("Container");
        add(FlowLayout.encloseIn(finishLandingPage));
    }
    
    private Image createCircleLine(int color, int height, boolean first) {
        Image img = Image.createImage(height, height, 0);
        Graphics g = img.getGraphics();
        g.setAntiAliased(true);
        g.setColor(0xcccccc);
        int y = 0;
        if(first) {
            y = height / 6 + 1;
        }
        g.drawLine(height / 2, y, height / 2, height);
        g.drawLine(height / 2 - 1, y, height / 2 - 1, height);
        g.setColor(color);
        g.fillArc(height / 2 - height / 4, height / 6, height / 2, height / 2, 0, 360);
        return img;
    }

   
    protected void showOtherForm(Resources res) {
        //new StatsForm(res).show();
    }
    
    
}
