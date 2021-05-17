/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.capture.Capture;
import com.codename1.ui.Button;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Activite;
import com.mycompany.myapp.services.ServiceActivite;
import java.io.IOException;


/**
 *
 * @author asus
 */
public class UpdateActivite extends Form {
    Form previous;
    public UpdateActivite(Resources res,Activite e){
            setTitle("Update  category");
            Form hi = new Form("Picker", new BoxLayout(BoxLayout.Y_AXIS));
        setLayout(BoxLayout.y());

        TextField nom=new TextField(e.getNom());
        nom.setUIID("TextFieldBlack");
                nom.setSingleLineTextArea(false);

       TextField description=new TextField(e.getDescription());
               description.setUIID("TextFieldBlack");

        TextField pointfort=new TextField(e.getPointfort());
                       pointfort.setUIID("TextFieldBlack");

        TextField tfActiviteDeconseille= new TextField(e.getActivitedeconseille());
                       tfActiviteDeconseille.setUIID("TextFieldBlack");

        TextField tfPrix= new TextField(""+e.getPrixparpersonne());
                       tfPrix.setUIID("TextFieldBlack");

        TextField tfDuree= new TextField(""+e.getDureeparminute());
                       tfDuree.setUIID("TextFieldBlack");

        TextField TFinfoimporatant= new TextField(e.getInfoimporatant());
                       TFinfoimporatant.setUIID("TextFieldBlack");

        TextField tfpointderencontre= new TextField(e.getPointderencontre());
                       tfpointderencontre.setUIID("TextFieldBlack");

        TextField tfville= new TextField(e.getVille());
                       tfville.setUIID("TextFieldBlack");

        TextField tfGouvernerat= new TextField(e.getGouvernerat());
                       tfGouvernerat.setUIID("TextFieldBlack");

        TextField tfPays= new TextField(e.getPays());
                       tfPays.setUIID("TextFieldBlack");

        TextField tfNbMax= new TextField(""+e.getNbmax());
                       tfNbMax.setUIID("TextFieldBlack");

        Button btnCapture = new Button("Browse");
Label labelImage=new Label();
        
        

        Button btnMod=new Button("update");
        btnCapture.addActionListener(l->{
         String path=  Capture.capturePhoto(Display.getInstance().getDisplayWidth(), -1);
         if(path!=null)
          try {
                                      String filepath = path;

             Image img= Image.createImage(path);
             labelImage.setIcon(img);
             revalidate();
             Image imgg = Image.createImage(filepath);
                        ServiceActivite.getInstance().updateImage(path,e.getId());
                        e.setImage(path);
         } catch (IOException ex) {
             ex.printStackTrace();
        }
        });


        btnMod.addActionListener(l->{
               
                    e.setNom(nom.getText());
                    e.setDescription(description.getText());
                    e.setPointfort(pointfort.getText());
                    
                    if(ServiceActivite.getInstance().editAct(e)) {
                        
                        try {
                            new ListActivitiesForm(previous);
                        } catch (IOException ex) {
                        }
                        
                              }
            });
            

        
          Button btnAnnuler=new Button("Cancel");
            btnAnnuler.addActionListener(l->{
               
                try {
                    new ListActivitiesForm(previous);
                } catch (IOException ex) {
                }
                
            });
           
                      addAll(nom,description,pointfort,tfActiviteDeconseille,tfPrix,tfDuree,TFinfoimporatant,tfville,tfGouvernerat,tfPays,tfNbMax,btnCapture,labelImage,btnMod,btnAnnuler);
                      


    }
    
}
