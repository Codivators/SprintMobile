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
import com.mycompany.myapp.entities.Article;
import com.mycompany.myapp.entities.CommentaireArticle;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author MSI PC
 */
public class ServiceCommentaireArticle {
     public ArrayList<CommentaireArticle> CommArticles;
     public ArrayList<User> User;
     public User user;
     public       ArrayList<String> ls;
    
    public static ServiceCommentaireArticle instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceCommentaireArticle() {
         req = new ConnectionRequest();
    }

    public static ServiceCommentaireArticle getInstance() {
        if (instance == null) {
            instance = new ServiceCommentaireArticle();
        }
        return instance;
    }
    
    
        public boolean addCommentArticle(CommentaireArticle t, String str) {
        String url = Statics.BASE_URL + "/addComment?contenu=" + str + "&articlename=" + t.getArticlename() ; //création de l'URL
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this); //Supprimer cet actionListener
                /* une fois que nous avons terminé de l'utiliser.
                La ConnectionRequest req est unique pour tous les appels de 
                n'importe quelle méthode du Service task, donc si on ne supprime
                pas l'ActionListener il sera enregistré et donc éxécuté même si 
                la réponse reçue correspond à une autre URL(get par exemple)*/
                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    
    
    
    
    

    

    public ArrayList<CommentaireArticle> parseCommArticles(String jsonText) throws ParseException{
        try {
            CommArticles =new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            
            Map<String,Object> CommArticlesListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
              
            List<Map<String,Object>> list = (List<Map<String,Object>>)CommArticlesListJson.get("root");
            String aux;
            //Parcourir la liste des tâches Json
            for(Map<String,Object> obj : list){
                //Création des tâches et récupération de leurs données
                CommentaireArticle t = new CommentaireArticle();
                t.setId((int) Float.parseFloat(obj.get("id").toString()));
                t.setContenu(obj.get("contenu").toString());
                t.setArticlename(obj.get("articlename").toString());
                t.setUsername(obj.get("username").toString());
                aux = obj.get("user").toString().substring(4, obj.get("user").toString().length()-2);
                t.setId_user((int) Float.parseFloat(aux));
                t.setDatepub(new SimpleDateFormat("yyyy-MM-dd").parse(obj.get("datepub").toString()) );
                //Ajouter la tâche extraite de la réponse Json à la liste
                CommArticles.add(t);
            }
            
            
        } catch (IOException ex) {
            
        }
         /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
        */
        return CommArticles;
    }
    

    
    
    public ArrayList<CommentaireArticle> getAllCommArticles(int id){
        String url = Statics.BASE_URL+"/getComment/"+ id;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                
                try {
                    CommArticles = parseCommArticles(new String(req.getResponseData()));
                } catch (ParseException ex) {
                        ex.getMessage();
                }
                    req.removeResponseListener(this);
                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return CommArticles;
    }
    
   
    public boolean deleteCommArticle(int id) {
        String url = Statics.BASE_URL + "/deletecomm/" + id; //création de l'URL
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this); //Supprimer cet actionListener
                /* une fois que nous avons terminé de l'utiliser.
                La ConnectionRequest req est unique pour tous les appels de 
                n'importe quelle méthode du Service task, donc si on ne supprime
                pas l'ActionListener il sera enregistré et donc éxécuté même si 
                la réponse reçue correspond à une autre URL(get par exemple)*/
                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    
   
}
