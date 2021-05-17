/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.InfiniteProgress;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.RadioButton;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.CategorieActivite;
import com.mycompany.myapp.services.ServiceCategorieActivite;

/**
 *
 * @author asus
 */
public class AddCategorieActForm extends Form{
    Resources res;
    public AddCategorieActForm( Form previous) {
        super("Newsfeed",BoxLayout.y());
        Toolbar tb =new Toolbar();
      setTitle("Add a new category");
      Form hi = new Form("Picker", new BoxLayout(BoxLayout.Y_AXIS));

        TextField tfNom = new TextField("","CategoryName");
        tfNom.setUIID("TextFieldBlack");

   
      Button btnValide = new Button("Add category");
btnValide.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfNom.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try{
                        CategorieActivite c = new CategorieActivite( tfNom.getText());
                        if( ServiceCategorieActivite.getInstance().addCategorie(c))
                            Dialog.show("Success","Connection accepted",new Command("OK"));
                        
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Name must be a string", new Command("OK"));
                    }
                }
                
                
            }
        });


        addAll(tfNom,btnValide);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
                , e-> previous.showBack()); // Revenir vers l'interface précédente
                
    }
   
}
