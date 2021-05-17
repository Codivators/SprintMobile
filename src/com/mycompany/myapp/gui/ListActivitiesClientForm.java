/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

/**
 *
 * @author asus
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.codename1.components.ImageViewer;
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;

import com.codename1.ui.geom.Dimension;
import com.mycompany.myapp.entities.Activite;
import com.mycompany.myapp.services.ServiceActivite;
import java.util.ArrayList;

import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import java.io.IOException;

/**
 *
 * @author bhk
 */
public class ListActivitiesClientForm {
Form f; 
Resources res;

    public ListActivitiesClientForm(Form previous) throws IOException  {
        f = new Form("Activities list", BoxLayout.y());
        ArrayList<Activite> ls = ServiceActivite.getInstance().getAllActivities();
         
        Image  icon = Image.createImage("/flesheLeft.png");
        Toolbar.setGlobalToolbar(true);
        
f.getToolbar().addCommandToLeftBar("Back", icon, (e) -> {
            
                new HomeForm().show();
            
        });


        for(Activite cat : ls){
            
                addItem(cat,f);
            
        }
        f.show();

    }

    public void addItem(Activite e, Form f) throws IOException  {
        ImageViewer img = null;
        Dimension d = new Dimension(50, 50);
        
        Container cl = new Container(new BoxLayout(BoxLayout.X_AXIS));
        img = new ImageViewer(Image.createImage("/activite-icon2.jpg"));
              
        Container cl1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Container cl2 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Container cldp = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Label lbimageP = new Label();

     
        Label lbNom = new Label("Name : "+e.getNom());
        lbNom.setUIID("LabelBlack");

       
        Label lbPrix  = new Label("Price: "+e.getPrixparpersonne());
                lbPrix.getAllStyles().setFgColor(0xff0000);


        Label tfNbMax= new Label("Maximum number of people:  "+e.getNbmax());
                tfNbMax.setUIID("LabelBlack");

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
        Label tfDateDebut = new Label(dateFormat.format(e.getDatedebut()) );
        Label tfDateFin = new Label(dateFormat.format(e.getDatefin()) );
        Button map =new Button("Map");

 map.addActionListener((l) -> {
        
             new GoogleMapsTestApp();
            
        });
 

         cl1.add(lbNom);
         cl1.add(tfNbMax);
        cl1.add(lbPrix);
        cl1.add(tfDateDebut);
        cl1.add(tfDateFin);

            cl2.add(map);
        cl1.add(cl2);
        cl.add(img);
        cl.add(cl1);
        
        cl.add(cldp);
        f.add(cl);
        f.add(new Label(" "));
                
        
               

       

        
        
        
        
        
        
        
        
        

    }
    



    

    
    
}
