/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;



import com.codename1.capture.Capture;
import com.codename1.components.FloatingHint;
import static com.codename1.ui.CN.CENTER;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.mycompany.myapp.MyApplication;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.services.ServiceUser;
import com.mycompany.myapp.gui.BaseForm;
import com.codename1.components.FloatingHint;
import com.codename1.components.MediaPlayer;
import com.codename1.io.FileSystemStorage;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import static com.codename1.ui.ComponentSelector.$;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;
import static com.mycompany.myapp.MyApplication.theme;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author armand
 */
public class loginPage extends Form {

    public loginPage( Resources res ) {
        
         super(new BorderLayout());
        
        if(!Display.getInstance().isTablet()) {
            BorderLayout bl = (BorderLayout)getLayout();
            bl.defineLandscapeSwap(BorderLayout.NORTH, BorderLayout.EAST);
            bl.defineLandscapeSwap(BorderLayout.SOUTH, BorderLayout.CENTER);
        }
        getTitleArea().setUIID("Container");
        setUIID("SignIn");
        add(BorderLayout.NORTH, new Label(res.getImage("icon.png").scaled(200, 240), "LogoLabel"));
        Container messageError=new Container(BoxLayout.y());
        messageError.setUIID("messageErrorShow");
        messageError.setVisible(false);
        Label textError=new Label("* Username or password incorrect");
        textError.setUIID("msgError");
        messageError.add(textError);
        
        
        TextField tffemail = new TextField("", "email", 20, TextField.ANY);
        TextField tffpassword = new TextField("", "Password", 20, TextField.PASSWORD);
        tffemail.setSingleLineTextArea(false);
        tffpassword.setSingleLineTextArea(false);
        Button signIn = new Button("Sign In");
        Button signUp = new Button("Sign Up");
        Button forgotpwd = new Button("Forgot password ?");
        signUp.addActionListener(e -> new registerPage(res).show());
       
        signUp.setUIID("Link");
        forgotpwd.addActionListener(e -> new ActivatePWDForm(res).show());
        forgotpwd.setUIID("CenterLink");
        
        Label doneHaveAnAccount = new Label("Don't have an account?");
          signIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tffemail.getText().length()!=0)||(tffpassword.getText().length()!=0)){
                
                    User u=new User();
                    u= ServiceUser.getInstance().getUser(tffemail.getText(),tffpassword.getText());
                    if(u!=null){
                        $(() -> {
           $("messageErrorShow").slideUp(); 
       });
                       
                        System.out.println(u+"<------------------");
                        if(u.getSuspension().equals("0")){
                        if(u.getRoles().equals("[\"ROLE_CLIENT\"]")){
                             List<String> privileges = new ArrayList<String>();
                             privileges.add(u.getRoles());
                             UserSession.setInstace(u, privileges);
                             System.out.println(u);
                            try {
                                new ProfileForm(theme,u).show();
                            } catch (IOException ex) {
                                ex.getMessage();
                            }
                           
                           
                        }else{
                             List<String> privileges = new ArrayList<String>();
                             privileges.add(u.getRoles());
                             UserSession.setInstace(u, privileges);
                            try {
                                new ProfileForm(theme,u).show();
                            } catch (IOException ex) {
                               ex.getMessage();
                            }
                        }                            
                        }
                        else if(u.getSuspension().equals("1")){
                             List<String> privileges = new ArrayList<String>();
                             privileges.add(u.getRoles());
                             UserSession.setInstace(u, privileges);
                        new blockForm().show();
                    } 
                    } 
                
                }
                   
              else{
                          messageError.setUIID("messageErrorShow");
                     textError.setUIID("msgError");
                     textError.setText("* remplir les champs");
                                                                     $(() -> {
           $("messageErrorShow").fadeIn(); 
       });      
                     }
    
            } 
        });
       
    
    Container content = BoxLayout.encloseY(
                new FloatingHint(tffemail),
                new FloatingHint(tffpassword),
                signIn,forgotpwd,
                FlowLayout.encloseCenter(doneHaveAnAccount, signUp )
        );
        content.setScrollableY(true);
        add(BorderLayout.SOUTH, content);
        signIn.requestFocus();
       // signIn.addActionListener(e -> new NewsfeedForm(res).show());
    }
              
        
    }
    

