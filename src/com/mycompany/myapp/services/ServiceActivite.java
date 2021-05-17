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
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Activite;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 *
 * @author bhk
 */
public class ServiceActivite {

    public ArrayList<Activite> activities;
    
    public static ServiceActivite instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceActivite() {
         req = new ConnectionRequest();
    }

    public static ServiceActivite getInstance() {
        if (instance == null) {
            instance = new ServiceActivite();
        }
        return instance;
    }

    public boolean addTask(Activite e) {
        String url = Statics.BASE_URL + "/AddActJSON/new?categorie_id=1&&nom=" + e.getNom()+ "&&description=" + e.getDescription()+ "&&pointfort=" + e.getPointfort()+ "&&pointderencontre=" + e.getPointderencontre()+"&&prixparpersonne=" + e.getPrixparpersonne()+"&&ville=" + e.getVille()+"&&gouvernerat=" + e.getGouvernerat()+"&&pays=" + e.getPays()+"&&activitedeconseille=" + e.getActivitedeconseille()+"&&date_debut=" + e.getDatedebut()+"&&date_fin=" + e.getDatefin()+"&&image=" + e.getImage()+"&&nbMax=" + e.getNbmax() +"&&dureeparminute="+ e.getDureeparminute()+"&&guidee=1"+"&&periode=0"  +"&&infoimportant="+ e.getInfoimporatant()+"&&user_id=1";//création de l'URL
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        //requ.setPost("false");
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

    public ArrayList<Activite> parseTasks(String jsonText) throws ParseException, com.codename1.l10n.ParseException{
        try {
            activities=new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            /*
                On doit convertir notre réponse texte en CharArray à fin de
            permettre au JSONParser de la lire et la manipuler d'ou vient 
            l'utilité de new CharArrayReader(json.toCharArray())
            
            La méthode parse json retourne une MAP<String,Object> ou String est 
            la clé principale de notre résultat.
            Dans notre cas la clé principale n'est pas définie cela ne veux pas
            dire qu'elle est manquante mais plutôt gardée à la valeur par defaut
            qui est root.
            En fait c'est la clé de l'objet qui englobe la totalité des objets 
                    c'est la clé définissant le tableau de tâches.
            */
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
              /* Ici on récupère l'objet contenant notre liste dans une liste 
            d'objets json List<MAP<String,Object>> ou chaque Map est une tâche.               
            
            Le format Json impose que l'objet soit définit sous forme
            de clé valeur avec la valeur elle même peut être un objet Json.
            Pour cela on utilise la structure Map comme elle est la structure la
            plus adéquate en Java pour stocker des couples Key/Value.
            
            Pour le cas d'un tableau (Json Array) contenant plusieurs objets
            sa valeur est une liste d'objets Json, donc une liste de Map
            */
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            
            //Parcourir la liste des tâches Json
            String aux, auxd,auxf;
            for(Map<String,Object> obj : list){
                //Création des tâches et récupération de leurs données
                Activite e = new Activite();
                float id = Float.parseFloat(obj.get("id").toString());
                e.setId((int)id);
               e.setNom(obj.get("nom").toString());
                e.setDescription(obj.get("description").toString());
               e.setPointfort(obj.get("pointfort").toString());
               e.setActivitedeconseille(obj.get("activitedeconseille").toString());
               e.setPrixparpersonne((int)Double.parseDouble(obj.get("prixparpersonne").toString()));
               e.setDureeparminute((int)Double.parseDouble(obj.get("dureeparminute").toString()));
               e.setInfoimporatant(obj.get("infoimportant").toString());
               e.setPointderencontre(obj.get("pointderencontre").toString());
               e.setVille(obj.get("ville").toString());
               e.setGouvernerat(obj.get("gouvernerat").toString());
               e.setPays(obj.get("pays").toString());
               e.setNbmax((int)Double.parseDouble(obj.get("nbMax").toString()));
                e.setImage(obj.get("image").toString());
              auxd = obj.get("dateDebut").toString().substring(0, obj.get("dateDebut").toString().length()-15);
              auxf=obj.get("dateFin").toString().substring(0, obj.get("dateFin").toString().length()-15);
           SimpleDateFormat  sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date date = sdf.parse(auxd);
                Date datef= sdf.parse(auxf);
                System.out.println(auxd);
               e.setDatedebut(date);
               e.setDatefin(datef);
                //Ajouter la tâche extraite de la réponse Json à la liste
                activities.add(e);
            }
            
            
        } catch (IOException ex) {
            
        }
         /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
        */
        return activities;
    }
    
    public ArrayList<Activite> getAllActivities(){
        String url = Statics.BASE_URL+"/listeActivites";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    activities = parseTasks(new String(req.getResponseData()));
                } catch (com.codename1.l10n.ParseException ex) {
                    ex.printStackTrace();
                }
                    req.removeResponseListener(this);
                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return activities;
    }
    public boolean deleteAct(int id){
        Activite e=new Activite();
      
                String url = Statics.BASE_URL +"/DeleteteActJSON/"+id ;
                 req.setUrl(url);
             req.setPost(false);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
@Override
            public void actionPerformed(NetworkEvent evt) {
                                    resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK

                req.removeResponseListener(this);
            }
    });
                NetworkManager.getInstance().addToQueueAndWait(req);
                return resultOK;
    
}
     public boolean editAct(Activite e) {
        
String url = Statics.BASE_URL + "/UpdateActJSON/"+e.getId()+"?categorie_id=1"+"&&nom="+ e.getNom()+"&&description="+ e.getDescription()+ e.getDescription()+ "&&pointfort=" + e.getPointfort()+ "&&pointderencontre=" + e.getPointderencontre()+"&&prixparpersonne=" + e.getPrixparpersonne()+"&&ville=" + e.getVille()+"&&gouvernerat=" + e.getGouvernerat()+"&&pays=" + e.getPays()+"&&activitedeconseille=" + e.getActivitedeconseille()+"&&date_debut=2022-04-01T00:00:00+00:00"+"&&date_fin=2022-04-01T00:00:00+00:00"+"&&nbMax="+e.getNbmax()+"&&dureeparminute="+ e.getDureeparminute()+"&&guidee=1"+"&&periode=0"+"&&infoimportant="+ e.getInfoimporatant()+"&&user_id=1" ;         
req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this); 
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
     public void updateImage(String str1,int id){
        String url = Statics.BASE_URL+"/updateImage/"+id+"/?image=" +str1; 
        System.out.println(url);  
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);  
       
    }

}
