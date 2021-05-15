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
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author MSI PC
 */
public class ServiceUserpourArticle {
         public ArrayList<User> Users;
     public ArrayList<User> User;
     public User user;
     public       ArrayList<String> ls;
    
    public static ServiceUserpourArticle instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceUserpourArticle() {
         req = new ConnectionRequest();
    }

    public static ServiceUserpourArticle getInstance() {
        if (instance == null) {
            instance = new ServiceUserpourArticle();
        }
        return instance;
    }
    

    public ArrayList<User> parseUser(String jsonText) throws ParseException{
        try {
            Users =new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            
            Map<String,Object> UserListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
              
            List<Map<String,Object>> Userlist = (List<Map<String,Object>>)UserListJson.get("root");
            //Parcourir la liste des tâches Json
            for(Map<String,Object> obj : Userlist){
                //Création des tâches et récupération de leurs données
                User t = new User();
                t.setId((int) Float.parseFloat(obj.get("id").toString()));
                t.setNomuser(obj.get("nomUser").toString());
                //Ajouter la tâche extraite de la réponse Json à la liste
                Users.add(t);
            }
            
            
        } catch (IOException ex) {
            
        }
         /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
        */
        return Users;
    }

    
    public ArrayList<User> getUsername(int id){
        String url = Statics.BASE_URL+"/getUsername/"+ id;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                
                try {
                    Users = parseUser(new String(req.getResponseData()));
                } catch (ParseException ex) {
                        ex.getMessage();
                }
                    req.removeResponseListener(this);
                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Users;
    }
    
    
    
    
}
