/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */

package com.mycompany.myapp.gui;


import com.codename1.components.ToastBar;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.Layout;
import com.codename1.ui.util.Resources;


/**
 * Common code that can setup the side menu
 *
 * @author Shai Almog
 */
public abstract class SideMenuBaseForm extends Form {

    public SideMenuBaseForm(String title, Layout contentPaneLayout) {
        super(title, contentPaneLayout);
    }

    public SideMenuBaseForm(String title) {
        super(title);
    }

    public SideMenuBaseForm() {
    }

    public SideMenuBaseForm(Layout contentPaneLayout) {
        super(contentPaneLayout);
    }
    
    public void setupSideMenu(Resources res) {
        Image profilePic = res.getImage("voyage.jpg");
        Image mask = res.getImage("voyage.jpg");
        mask = mask.scaledHeight(mask.getHeight());
        profilePic = profilePic.fill(mask.getWidth(), mask.getHeight());
        Label profilePicLabel = new Label("Jardin d'enfant OUELDY", profilePic, "SideMenuTitle");
       // profilePicLabel.setMask(mask.createMask());

        Container sidemenuTop = BorderLayout.center(profilePicLabel);
        sidemenuTop.setUIID("SidemenuTop");
        
        getToolbar().addComponentToSideMenu(sidemenuTop);

        getToolbar().addMaterialCommandToSideMenu("Liste des voyages", FontImage.MATERIAL_STYLE,  e ->new ListVoyagesForm(res).show());
        getToolbar().addMaterialCommandToSideMenu("Ajouter un voyage", FontImage.MATERIAL_ADD_BOX,  e -> new AddVoyageForm(res).show());
        getToolbar().addMaterialCommandToSideMenu("Liste des guides", FontImage.MATERIAL_STYLE,  e ->new ListVoyagesForm(res).show());
        //getToolbar().addMaterialCommandToSideMenu("Ajouter un guide", FontImage.MATERIAL_ADD_BOX,  e -> new AddVoyageForm(res).show());

        //getToolbar().addMaterialCommandToSideMenu(" Participer a un event", FontImage.MATERIAL_STAR,  e ->new ParticE(res).show());
               //getToolbar().addMaterialCommandToSideMenu("  menu", FontImage.MATERIAL_EXIT_TO_APP,  e -> new MenuForm(res).show());




    }
    
    protected abstract void showOtherForm(Resources res);
}
