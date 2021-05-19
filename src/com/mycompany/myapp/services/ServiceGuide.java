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
import com.mycompany.myapp.entities.Guide;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author 2020
 */
public class ServiceGuide {
    public ArrayList<Guide> guides;
    
    public static ServiceGuide instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceGuide() {
         req = new ConnectionRequest();
    }

    public static ServiceGuide getInstance() {
        if (instance == null) {
            instance = new ServiceGuide();
        }
        return instance;
    }

    public boolean addGuide(Guide g) {
        String url = Statics.BASE_URL + "/new?Nom=" + g.getNom() + "&Prenom=" + g.getPrenom() + "&Domaine" + g.getDomaine() + "&Email" + g.getEmail(); 
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; 
                req.removeResponseListener(this); 
                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public ArrayList<Guide> parseGuides(String jsonText){
        try {
            guides=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> guidesListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
              
            List<Map<String,Object>> list = (List<Map<String,Object>>)guidesListJson.get("root");
            
           
            for(Map<String,Object> obj : list){
                
                Guide g = new Guide();
                float id = Float.parseFloat(obj.get("id").toString());
                g.setId((int)id);
                g.setNom(obj.get("nom").toString());
                g.setPrenom(obj.get("Prenom").toString());
                g.setDomaine(obj.get("Domaine").toString());
                g.setEmail(obj.get("Email").toString());
             
               
                
                guides.add(g);
            }
            
            
        } catch (IOException ex) {
            
        }
        
        return guides;
    }
    
    public ArrayList<Guide> getAllGuide(){
        String url = Statics.BASE_URL+"/AllGuide/";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                guides = parseGuides(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return guides;
    }
    public boolean deleteArticle(Guide g) {
        String url = Statics.BASE_URL + "/deleteguide/" + g.getId() + "&text="; 
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; 
                req.removeResponseListener(this); 
               
                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    public boolean updateArticle(Guide g) {
        String url = Statics.BASE_URL + "/updateguide/" + g.getId() ; 
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; 
                req.removeResponseListener(this); 
                
                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    
}
