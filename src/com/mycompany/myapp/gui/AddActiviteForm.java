/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.mycompany.myapp.entities.Activite;
import com.mycompany.myapp.entities.CategorieActivite;
import com.mycompany.myapp.services.ServiceCategorieActivite;
import com.mycompany.myapp.services.ServiceActivite;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author asus
 */
public class AddActiviteForm extends Form{

    public AddActiviteForm(Form previous) {
        /*
        Le paramètre previous définit l'interface(Form) précédente.
        Quelque soit l'interface faisant appel à AddTask, on peut y revenir
        en utilisant le bouton back
        */
        setTitle("Add a new activity");
        setLayout(BoxLayout.y());
        Form f = new Form("Add a new activity", new BoxLayout(BoxLayout.Y_AXIS));

f.show();


        TextField tfNom = new TextField("","ActivityName");
                tfNom.setUIID("TextFieldBlack");
        TextField tfDescription= new TextField("", "ActivityDescription");
                        tfDescription.setUIID("TextFieldBlack");
        TextField tfPointfort= new TextField("", "ActivityStrongpoint");
                        tfPointfort.setUIID("TextFieldBlack");
        TextField tfActiviteDeconseille= new TextField("", "activity not recommended for the following people");
                        tfActiviteDeconseille.setUIID("TextFieldBlack");
        TextField tfPrix= new TextField("", "ActivityPrice");
                        tfPrix.setUIID("TextFieldBlack");
        TextField tfDuree= new TextField("", "ActivityDuration");
                        tfDuree.setUIID("TextFieldBlack");
        TextField TFinfoimporatant= new TextField("", "ActivityImportantInformation");
                        TFinfoimporatant.setUIID("TextFieldBlack");

        TextField tfpointderencontre= new TextField("", "ActivityMeeting point");
                        tfpointderencontre.setUIID("TextFieldBlack");

        TextField tfville= new TextField("", "ActivityCity");
                        tfville.setUIID("TextFieldBlack");

        TextField tfGouvernerat= new TextField("", "ActivityGouvernerate");
                        tfGouvernerat.setUIID("TextFieldBlack");

        TextField tfPays= new TextField("", "ActivityCountry");
                        tfPays.setUIID("TextFieldBlack");

        TextField tfNbMax= new TextField("", "Activitymaximum number");
                        tfNbMax.setUIID("TextFieldBlack");

        Picker dateDebut = new Picker();
        dateDebut.setType(Display.PICKER_TYPE_DATE);
        Picker  dateFin=new Picker();
        dateFin.setType(Display.PICKER_TYPE_DATE);
        CheckBox cb1 = new CheckBox("Guided?");
        CheckBox cb2 = new CheckBox("Has a duration?");
cb1.setSelected(true);
cb2.setSelected(false);

        ComboBox<CategorieActivite>idCategorie=new ComboBox<>(                        
        );
        
        
        ArrayList<CategorieActivite> ls=ServiceCategorieActivite.getInstance().getAllCategories();
       
        for(int i=0;i<ls.size();i++){
         ObservableList<CategorieActivite> options = 
    FXCollections.observableArrayList(ls.get(i)); 

         

        }
        Button btnValider = new Button("Add activity");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfNom.getText().length()==0)||(tfDescription.getText().length()==0)||(tfPointfort.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                        Activite e = new Activite();
                              e.setCategorie_id(CENTER);
                              e.setNom(tfNom.getText()); 
                              e.setDescription(tfDescription.getText()); 
                              e.setPointfort(tfPointfort.getText());  
                              e.setPointderencontre(tfpointderencontre.getText());              
                              e.setPrixparpersonne(((int)Double.parseDouble(tfPrix.getText().toString())));
                              e.setVille(tfville.getText());
                              e.setGouvernerat(tfGouvernerat.getText());
                              e.setPays( tfPays.getText());
                              e.setActivitedeconseille(tfActiviteDeconseille.getText());
                              e.setDatedebut(dateDebut.getDate());
                              e.setDatefin(dateFin.getDate());
                              e.setImage("4.PNG");
                              e.setNbmax((int)Double.parseDouble(tfNbMax.getText().toString()));
                               e.setGuidee(true);
                               e.setPeriode(false);
                              e.setDureeparminute(((int)Double.parseDouble(tfDuree.getText().toString())));
                              e.setInfoimporatant(TFinfoimporatant.getText()); 
                              e.setUser_id(1);
                  
                        if( ServiceActivite.getInstance().addTask(e))
                            Dialog.show("Success","Connection accepted",new Command("OK"));
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }
                
                
            }
        });
        
        addAll(tfNom,tfDescription,tfPointfort,tfpointderencontre,tfActiviteDeconseille,tfPrix,tfDuree,TFinfoimporatant,tfville,tfGouvernerat,tfPays,tfNbMax,dateDebut,dateFin,cb1,cb2,idCategorie,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
                , e-> previous.showBack()); // Revenir vers l'interface précédente
                

        }
    
    
}
