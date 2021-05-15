/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.services.ServiceUser;
import java.util.concurrent.ThreadLocalRandom;


/**
 *
 * @author bhk
 */
public class registerPage extends BaseForm{

    public static int randomNum = ThreadLocalRandom.current().nextInt(1000, 9998 + 1);
    public registerPage(Resources res) {
        super(new BorderLayout());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.setUIID("Container");
        getTitleArea().setUIID("Container");
        Form previous = Display.getInstance().getCurrent();
        tb.setBackCommand("", e -> previous.showBack());
        setUIID("SignIn");
        add(BorderLayout.NORTH, new Label(res.getImage("icon.png").scaled(200, 240), "LogoLabel"));
        TextField tfnomuser = new TextField("", "username", 20, TextField.ANY);
        TextField tfemail = new TextField("", "email", 20, TextField.EMAILADDR);
        TextField tfroles = new TextField("", "roles", 20, TextField.ANY);
        TextField tfpassword = new TextField("", "password", 20, TextField.PASSWORD);
        tfnomuser.setSingleLineTextArea(false);
        tfemail.setSingleLineTextArea(false);
        tfroles.setSingleLineTextArea(false);
        tfpassword.setSingleLineTextArea(false);
        Button next = new Button("Next");
        Button signIn = new Button("Sign In");
        signIn.addActionListener(e -> previous.showBack());
        signIn.setUIID("Link");
        Label alreadHaveAnAccount = new Label("Already have an account?");
             next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfnomuser.getText().length()==0)||(tfemail.getText().length()==0)||(tfpassword.getText().length()==0)||(tfroles.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                        User u = new User();
                        u.setNomuser(tfnomuser.getText());
                        u.setEmail(tfemail.getText());
                        u.setPassword(tfpassword.getText());
                        u.setRoles(tfroles.getText());
                        u.setCoder(randomNum);
                        System.out.println(u);
                        
                        if( ServiceUser.getInstance().registration(u))
                            
                       { 
                          System.out.println(u);
                          System.out.println(u.getCoder());
                          new ActivateForm(res,u).show();
                       
                       }
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }
                
                
            }
        });
        Container content = BoxLayout.encloseY(
                new Label("Welcome To StackActivity", "LogoLabel"),
                new FloatingHint(tfnomuser),
               
                new FloatingHint(tfemail),
                new FloatingHint(tfroles),
                
                new FloatingHint(tfpassword)
         
        );
        content.setScrollableY(true);
        add(BorderLayout.CENTER, content);
        add(BorderLayout.SOUTH, BoxLayout.encloseY(
                next,
                FlowLayout.encloseCenter(alreadHaveAnAccount, signIn)
        ));
        next.requestFocus();
       
    }
      
                
    }
    
    

