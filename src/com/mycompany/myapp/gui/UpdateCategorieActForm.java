/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;

import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.CategorieActivite;
import com.mycompany.myapp.services.ServiceCategorieActivite;
import java.io.IOException;





/**
 *
 * @author asus
 */
public class UpdateCategorieActForm extends Form{
    Form previous;
    public UpdateCategorieActForm(Resources res,CategorieActivite cat){
            setTitle("Update  category");
            Form hi = new Form("Picker", new BoxLayout(BoxLayout.Y_AXIS));
        setLayout(BoxLayout.y());

        TextField nom=new TextField(cat.getNom());
                nom.setUIID("TextFieldBlack");

        Button btnMod=new Button("update");


        btnMod.addActionListener(l->{
               
                    cat.setNom(nom.getText());
                    
                    if(ServiceCategorieActivite.getInstance().editCat(cat)) {
                        
                      
                        try {
                            new ListCatagoriesActForm(previous);
                        } catch (IOException ex) {
                ex.printStackTrace();
                        }
                       
                              }
            });
            

        
          Button btnAnnuler=new Button("Cancel");
            btnAnnuler.addActionListener(l->{
               
               
                try {
                    new ListCatagoriesActForm(previous);
                } catch (IOException ex) {
                ex.printStackTrace();
                }
               
                
            });
                      addAll(nom,btnMod,btnAnnuler);
                      


    }
    
}
