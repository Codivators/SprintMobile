/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.ui.Button;
import com.codename1.ui.CN;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.ImageIO;
import com.mycompany.myapp.entities.Guide;
import com.mycompany.myapp.services.ServiceGuide;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;






/**
 *
 * @author 2020
 */
public class addGuideForm extends Form {
    protected String saveFileToDevice(String hi, String ext) throws IOException {
        URI uri;
        try {
            uri = new URI(hi);
            String path = uri.getPath();
            int index = hi.lastIndexOf("/");
            hi = hi.substring(index + 1);
            return hi;
        } catch (URISyntaxException ex) {
        }
        return "hh";
    }
     public addGuideForm(Form previous) {
        /*
        Le paramètre previous définit l'interface(Form) précédente.
        Quelque soit l'interface faisant appel à AddTask, on peut y revenir
        en utilisant le bouton back
        */
        setTitle("Add a new Guide");
        setLayout(BoxLayout.y());
        
        TextField tfNom = new TextField("","GuideNom");
        TextField tfPrenom = new TextField("","GuidePrenom");
        TextField tfDomaine = new TextField("","GuideDomaine");
        TextField tfEmail = new TextField("","GuideEmail");
        
     
     
        Button btnValider = new Button("Add Guide");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfNom.getText().length()==0)||(tfPrenom.getText().length()==0)
                        ||(tfDomaine.getText().length()==0)||(tfEmail.getText().length()==0))
                 
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                        Guide g = new Guide(tfNom.getText(), tfPrenom.getText(), tfDomaine.getText(), tfEmail.getText());
                        if( ServiceGuide.getInstance().addGuide(g))
                            Dialog.show("Success","Connection accepted",new Command("OK"));
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }
                
                
            }
        });
        
        addAll(tfNom,tfPrenom,tfDomaine,tfEmail,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
                , e-> previous.showBack()); // 
                
    }                      
    
}
                    
