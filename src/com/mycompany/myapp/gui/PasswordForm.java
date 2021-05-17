/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;



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
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author armand
 */
public class PasswordForm extends BaseForm {

    public PasswordForm( Resources res,String email) {
        
         super(new BorderLayout());
        
        if(!Display.getInstance().isTablet()) {
            BorderLayout bl = (BorderLayout)getLayout();
            bl.defineLandscapeSwap(BorderLayout.NORTH, BorderLayout.EAST);
            bl.defineLandscapeSwap(BorderLayout.SOUTH, BorderLayout.CENTER);
        }
        getTitleArea().setUIID("Container");
        setUIID("Activate");
        Container messageError=new Container(BoxLayout.y());
        messageError.setUIID("messageErrorShow");
        messageError.setVisible(false);
        Label textError=new Label("* Username or password incorrect");
        textError.setUIID("msgError");
        messageError.add(textError);
        
        
        TextField tfpassword = new TextField("", "Password", 20, TextField.PASSWORD);
        TextField tfCpassword = new TextField("", "Confirm Password", 20, TextField.PASSWORD);
        tfpassword.setSingleLineTextArea(false);
        tfCpassword.setSingleLineTextArea(false);
        Button rstpwd = new Button("Change password");
   
          rstpwd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
              System.out.println(email); 
              
             if ((tfCpassword.getText().length()==0)||(tfpassword.getText().length()==0)) {
              Dialog.show("Alert", "Please fill all the fields", new Command("OK"));}
             else if (tfpassword.getText().length()<6){
                 
               Dialog.show("Alert", "Your password must contain at least 6 caracters", new Command("OK"));
              
            } else if (!(tfpassword.getText().equalsIgnoreCase(tfCpassword.getText()))) 
            {  Dialog.show("Fail", "Please verify correctly your password", new Command("OK"));  }
            else {
             ServiceUser.getInstance().updatePwd(email, tfpassword.getText(), tfCpassword.getText());   
             Dialog.show("Success", "Your password has been changed !", new Command("OK"));
             new loginPage(res).show();   
            }
                }
        });
         Container content = BoxLayout.encloseY(
                new FloatingHint(tfpassword),
                new FloatingHint(tfCpassword),
                rstpwd
               // FlowLayout.encloseCenter(doneHaveAnAccount, signUp )
        );
        content.setScrollableY(true);
        add(BorderLayout.SOUTH, content);
        rstpwd.requestFocus();
       // signIn.addActionListener(e -> new NewsfeedForm(res).show());
    }
              
        
    }
    

