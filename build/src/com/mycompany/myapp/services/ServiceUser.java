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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author bhk
 */
public class ServiceUser {

    public ArrayList<User> user;
    public User t ;
    public static ServiceUser instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceUser() {
         req = new ConnectionRequest();
    }

    public static ServiceUser getInstance() {
        if (instance == null) {
            instance = new ServiceUser();
        }
        return instance;
    }

    public boolean registration(User u) {
        String url = Statics.BASEE_URL + "/registerr/new?email=" +  u.getEmail()+ "&nomUser="+ u.getNomuser() + "&password=" + u.getPassword()+"&roles=" + u.getRoles()+"&coder=" + u.getCoder(); //création de l'URL
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200;
                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
   public User sendcodeP(String email,int codep) {
        String url = Statics.BASEE_URL + "/sendcodep/"+email+"/"+codep; //création de l'URL
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               t = parseUser(new String(req.getResponseData()));
               req.removeResponseListener(this); 
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return t;
    }
  /*  public ArrayList<User> parseUsers(String jsonText){
        try {
            user=new ArrayList<>();
     
    JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            
            //Parcourir la liste des tâches Json
            for(Map<String,Object> obj : list){
                //Création des tâches et récupération de leurs données
                User t = new User();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int)id);
                t.setNomuser(obj.get("nomUser").toString());
                t.setEmail(obj.get("email").toString());
                t.setRoles(obj.get("roles").toString());
                //Ajouter la tâche extraite de la réponse Json à la liste
               user.add(t);
               System.out.println(t);            }
            
        } catch (IOException ex) {
            
        }
      
        return user;
    }
    */
         public User parseUser(String jsonText){
            User u=null;
           
        try {
            
            JSONObject obj = new JSONObject(jsonText);
            if(obj.getJSONObject("requette").getString("reponse").equals("no")){
                return u;
            }else{
                u=new User();
                u.setId((int) obj.getJSONObject("requette").getJSONObject("user").getInt("id"));
                u.setEmail(obj.getJSONObject("requette").getJSONObject("user").getString("email"));
                u.setNomuser(obj.getJSONObject("requette").getJSONObject("user").getString("nomUser"));
                u.setSuspension(obj.getJSONObject("requette").getJSONObject("user").getString("suspension"));
                u.setPassword(obj.getJSONObject("requette").getJSONObject("user").getString("password"));
                u.setImage(obj.getJSONObject("requette").getJSONObject("user").getString("image"));
                u.setRoles(obj.getJSONObject("requette").getJSONObject("user").getString("roles"));
                u.setCoder(obj.getJSONObject("requette").getJSONObject("user").getInt("coder"));
                u.setCodep(obj.getJSONObject("requette").getJSONObject("user").getInt("codep"));
                u.setConfirmpassword(obj.getJSONObject("requette").getJSONObject("user").getString("confirm_password"));
                
            }
            
            
        } catch (JSONException ex) {
            System.out.println("erruer de format json");
        }
        return u;
    }
   public ArrayList<User> getAllTasks(){
        String url = Statics.BASEE_URL+"/service/user";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                t = parseUser(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return user;
    }
    
     public User getUser(String username,String password){
        String url = Statics.BASEE_URL+"/serviceLogin/"+username+"/"+password;
        System.out.println(url);  
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                t= parseUser(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);  
        return t;
    } 
       
       public void updateUser(String str1, String str2, int id){
        String url = Statics.BASEE_URL+"/updateUser/"+id+"/?email=" +str1+ "&nomUser="+ str2; 
        System.out.println(url);  
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                t= parseUser(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);  
       
    } 
       public void updateImage(String str1,int id){
        String url = Statics.BASEE_URL+"/updateImage/"+id+"/?image=" +str1; 
        System.out.println(url);  
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                t= parseUser(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);  
       
    } 
       public void updatePwd(String email,String str1,String str2){
        String url = Statics.BASEE_URL+"/updatePwd/"+email+"/?password=" +str1+"&confirm_password="+ str2; 
        System.out.println(url);  
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                t= parseUser(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);  
       
    } 
}
