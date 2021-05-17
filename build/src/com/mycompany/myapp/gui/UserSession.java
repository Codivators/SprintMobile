/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;



import com.mycompany.myapp.entities.User;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 *
 * @author LENOVO
 */
public final class UserSession {
    private static UserSession instance;

    private static User user;
    private static List<String> privileges;
    
    private Stage stage;

    public UserSession(User user, List<String> privileges) {
        this.user = user;
        this.privileges = privileges;
    }

    public UserSession() {
    }
 
    public static UserSession setInstace(User user, List<String> privileges) {
        if(instance == null) {
            instance = new UserSession(user,privileges);
        }
        return instance;
    }

    public static User getUser() {
        return user;
    }

    public List<String> getPrivileges() {
        return privileges;
    }

  
    public  void logOut() {
        user = null;// or null
        privileges = new ArrayList<>();// or null
      
    }
    
    @Override
    public String toString() {
        return "UserSession{" + "user =" + user +" privileges : "+privileges+ '}';
    }

    

    
    
}
