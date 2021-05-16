/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;




import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;

import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.DateFormatPatterns;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.properties.UiBinding.DateConverter;


import com.codename1.ui.events.ActionListener;
import com.codename1.util.BigDecimal;
import com.codename1.util.BigInteger;
import com.mycompany.entities.ReservationH;
import com.mycompany.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TimeZone;
import static javafx.application.Application.launch;
       

/**
 *
 * @author RAMI
 */
public class ServiceReservationH {
    
    
    public  static ServiceReservationH instance =null ;
    
    
    private ConnectionRequest req;
    
    
    public  static ServiceReservationH getInstance (){
    
    if(instance == null)
        instance = new ServiceReservationH ();
    return instance ;
    
     }
    
    
    public ServiceReservationH(){
        req = new ConnectionRequest();
    }
    //ajout
    public void ajouterReservationH(ReservationH ReservationH){
        
        String url =Statics.BASE_URL+"/addHotelJson?debut="+ReservationH.getDebut()+"&confirmation="+ReservationH.getConfirmation()+"&room="+ReservationH.getRoom_id_id()+"&user="+ReservationH.getUser_id_id()+"&fin="+ReservationH.getFin();

        req.setUrl(url);
        req.addResponseListener((e) -> {
            
            String str = new String (req.getResponseData());
            System.out.println("data == "+str);
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
    }
    
    //affchage
    public ArrayList<ReservationH>AffichageReservationH() {
        ArrayList<ReservationH> result = new ArrayList<>();
    
        String url =Statics.BASE_URL+"/displayHotelsJson";
        req.setUrl(url);
        
        
        req.addResponseListener(new ActionListener<NetworkEvent>(){
            @Override
                    public void actionPerformed (NetworkEvent evt) {
            JSONParser jsonp ;
            jsonp = new JSONParser();
            
            try {
                    Map<String,Object>mapReservationH = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));

                    List<Map<String,Object>>listOfMaps = (List<Map<String,Object>>) mapReservationH.get("root");
                    
                    for(Map<String, Object> obj : listOfMaps){
                        ReservationH rH = new ReservationH();
                        //Integer num;
                        //num = (int) obj.get("id").intValue();
                        
                       // BigDecimal bdOne = new BigDecimal((BigInteger) obj.get("id"), 0);
                        
                        //System.out.println(obj.get("userId"));
                       // System.out.println(bdOne);
                        //System.out.println(Math.round((Double)obj.get("userId")));
                       //int id_reclamtion = Integer.parseInt(obj.get("id").toString());
                        

                        // int id_reclamtion = Integer.parseInt(obj.get("id").toString());
                        
                        
                        //int user = (int) Math.round((Double)obj.get("userId"));
                        
                        /*String[] cutText;
                        cutText = obj.get("userId").toString().split("2");
                        System.out.println(obj.get("userId").toString());*/
                       
                         /*StringTokenizer st = new StringTokenizer(obj.get("userId").toString(),"{");
                                System.out.println(st.nextToken("="));*/
                                
                               
                        //System.out.println(Math.round((Double)(stringValues.values().toArray()[0])));
                        
                         Map<String,Object> stringValues = ( Map<String,Object>)obj.get("userId");
                         Map<String,Object> stringValues2 = ( Map<String,Object>)obj.get("roomId");
                         
                        System.out.println(obj.get("debut").getClass().getSimpleName());
                        
                        
                        
                        int user = (int) Math.round((Double)(stringValues.values().toArray()[0]));
                        int room = (int) Math.round((Double)(stringValues2.values().toArray()[0]));
                        int id = (int) Math.round((Double)((obj.get("id"))));
                        String debut = (String) obj.get("debut");
                        String fin = (String) obj.get("fin");
                        String confirmation = obj.get("confirmation").toString();
                        
                        //System.out.println(debut.substring(0,10));

                        
                         SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

                        
  
                           Date datedeb = format.parse(debut.substring(0,10));
                            Date datefin = format.parse(fin.substring(0,10));



                        System.out.println(datedeb);
                        
                        rH.setUser_id_id(user);
                        rH.setRoom_id_id(room);
                        rH.setId(id);
                        rH.setDebut(datedeb);
                        rH.setFin(datefin);
                        rH.setConfirmation(confirmation);
                        
                          
                        
                        
                        
                        
                        
                        
                       // int room = (int) Math.round((Double)obj.get("room_id_id"));
                        //float room = (int)obj.get("room_id_id");*/
                        
                       // String confirmation = obj.get("confirmation").toString();
                            //System.out.println(confirmation);
                        /*String debut = obj.get("debut").toString();
                        String fin = obj.get("fin").toString();*/
                        
                      //  rH.setId((int)id);
                        
                        //System.out.println(rH);
                        
                        
                        //String input = debut;
                       /* SimpleDateFormat format = new 
                        SimpleDateFormat(DateFormatPatterns.ISO8601);
                        Date datedeb = format.parse(debut);     
                        Date datefin = format.parse(fin);     
                      
                        rH.setDebut(datedeb);
                        rH.setFin(datefin);
                        
                        
                        
                        
                        
                        
                        
                        rH.setRoom_id_id((int)room);
                        rH.setUser_id_id((int)user);*/
                       // rH.setConfirmation(confirmation);
                       // rH.setUser_id_id(user);
                        
                         // h.setDebut(rs.getDate("debut"));
                         // h.setDebut(java.sql.Date.valueOf(debut.getValue()));

                        
                        
                        //Date
                        
                      /*  String DateConverter = obj.get("debut").toString().substring(obj.get("debut").toString().indexOf("timestamp")+10,obj.get("obj").toString().lastIndexOf("}"));
                        Date currentTime = new Date(Double.valueOf(DateConverter).longValue()* 1000);
                        
                         String DateConverterfin = obj.get("fin").toString().substring(obj.get("fin").toString().indexOf("timestamp")+10,obj.get("obj").toString().lastIndexOf("}"));
                        Date currentTimefin = new Date(Double.valueOf(DateConverterfin).longValue()* 1000);
                        //import if chak mtaa format simpledate
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                        String dateString = formatter.format(currentTime);
                        rH.setDebut(DateConverter);
                        rH.setFin(DateConverterfin);*/
                        
                        
                        result.add(rH);
                        
                    }
                    
                    
                    
                     }catch(Exception ex){
                         ex.printStackTrace();
                     }
            
            
            
                    }
        
        
        
    });
    NetworkManager.getInstance().addToQueueAndWait(req);
        
    return result;
    
    }
    
    
    
    //Detail reservation
    public ReservationH DetailReservationH ( int id , ReservationH ReservationH) {
    
        String url = Statics.BASE_URL+"/detailHotelJson?"+id;
        req.setUrl(url);
        
        String str = new String(req.getResponseData());
        req.addResponseListener(((r)-> {
            JSONParser jsonp = new JSONParser();
            try{
                Map<String,Object>obj = jsonp.parseJSON(new CharArrayReader(new String(str).toCharArray()));
                

                
                String debut = obj.get("debut").toString();
                String fin = obj.get("fin").toString();                

                
               /* ReservationH.setDebut(java.sql.Date.valueOf(debut));
                  
                ReservationH.setFin(java.sql.Date.valueOf(debut));*/
               
                        SimpleDateFormat format = new 
                        SimpleDateFormat(DateFormatPatterns.ISO8601);
                        Date datedeb = format.parse(debut);     
                        Date datefin = format.parse(fin);   
                ReservationH.setDebut(datedeb);
                ReservationH.setFin(datefin);
                
                float user = Float.parseFloat(obj.get("user_id_id").toString());
                        
                float room = Float.parseFloat(obj.get("room_id_id").toString());
                
                
                
                ReservationH.setUser_id_id((int)user);
                
                                
                ReservationH.setRoom_id_id((int)room);
                
                ReservationH.setConfirmation(obj.get("confirmation").toString());


                
                
            }catch(IOException ex){
                System.out.println("error related to sql"+ex.getMessage());
            } catch (ParseException ex) {
                
            }
            
            System.out.println("data === "+str);
        
                }));
    
     
    NetworkManager.getInstance().addToQueueAndWait(req);
    return ReservationH; 
    
    
    
    
}
}
