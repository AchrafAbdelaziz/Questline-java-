/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Voyage;
import com.mycompany.myapp.utils.DataSource;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 *
 * @author bhk
 */
public class ServiceVoyage {

    public ArrayList<Voyage> voyages;
    
    public static ServiceVoyage instance=null;
    public boolean resultOK;
    private ConnectionRequest req;


    public static ServiceVoyage getInstance() {
        if (instance == null) {
            instance = new ServiceVoyage();
        }
        return instance;
    }
    
    
    public ServiceVoyage() {
          req = DataSource.getInstance().getRequest();
    }

    public boolean addVoyage(Voyage t) {
         
        String url = "http://127.0.0.1:8000/addJson?Destination=" + t.getDestination()
                + "&Prix=" + t.getPrix()+ "&Periode=" + t.getPeriode()
                + "&imageName=" + t.getImage_name(); //création de l'URL
          req.setUrl(url);
          req.setPost(false);

        //System.out.println("tt");

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               resultOK = req.getResponseCode() == 200; // Code HTTP 200 OK
                req.removeResponseListener(this);

     
            }

        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        
        return  resultOK;
    }

    public ArrayList<Voyage> parseTasks(String jsonText){
        try {
             voyages =new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> voyagesListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)voyagesListJson.get("root");
            
            //Parcourir la liste des tâches Json
            for(Map<String,Object> obj : list){
                //Création des tâches et récupération de leurs données
                int id = (int)Float.parseFloat(obj.get("id").toString());
                String  destination = obj.get("Destination").toString();
                int prix = (int)Float.parseFloat(obj.get("Prix").toString());
                String periode = obj.get("Periode").toString();
                String image = obj.get("imageName").toString(); 
                
                //Ajouter la tâche extraite de la réponse Json à la liste
                
                voyages.add(new Voyage(id,destination,prix,periode,image));
                
            }
            
            
        } catch (IOException ex) {
            
        }
         /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
        */
        return voyages;
    }
    
    public ArrayList<Voyage> getAllVoyages(){
        String url = "http://127.0.0.1:8000/listJson";
        req.setUrl(url);
        req.setPost(true);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                voyages = parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return voyages;
    }
    
   public boolean deletVoyage(Voyage c) {
        String urle = "http://127.0.0.1:8000/deleteJson/" + c.getId();
        req.setUrl(urle);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                 resultOK= req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
       
        return  resultOK;
    }
            
            public boolean modifier(Voyage t) {
       String url = "http://127.0.0.1:8000/updateJson?id=" + t.getId()+ "&Destination=" + t.getDestination()
                + "&Prix=" + t.getPrix()+ "&Periode=" + t.getPeriode()
                + "&imageName=" + t.getImage_name(); 
   //    String url = Statics.BASE_URL +"/posts/update/"+e.getId()+"/"+e.getTitle()+"/"+e.getDescription()+"/"+e.getRating();
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK= req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
}

