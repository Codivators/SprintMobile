/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
 import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.mycompany.myapp.entities.Activite;
import com.mycompany.myapp.services.ServiceActivite;
import java.util.ArrayList;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import java.io.IOException;



/**
 *
 * @author bhk
 */
public class ListActivitiesForm {
Form f; 
Resources res;

    public ListActivitiesForm(Form previous) throws IOException   {
        f = new Form("Activities list", BoxLayout.y());
        ArrayList<Activite> ls = ServiceActivite.getInstance().getAllActivities();
       
f.show();
        for(Activite cat : ls){
            
                addItem(cat,f);
            
        }
        f.show();

    }
    public void addItem(Activite e, Form f) throws IOException {
        ImageViewer img = null;
        Dimension d = new Dimension(100, 100);
        
        Container cl = new Container(new BoxLayout(BoxLayout.X_AXIS));
        img = new ImageViewer(Image.createImage("/listActivities.png"));
        Container cl1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Container cl2 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Container cldp = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Label lbimageP = new Label();
       
        Label lbId = new Label(String.valueOf(e.getId()) );
                lbId.setUIID("LabelBlack");
        Label lbNom = new Label(e.getNom());
                lbNom.setUIID("LabelBlack");
        Label lbDesc = new Label(e.getDescription() );
                        lbDesc.setUIID("LabelBlack");
        Label lbPointfort = new Label(e.getPointfort() );
                        lbPointfort.setUIID("LabelBlack");
        Label tfActiviteDeconseille= new Label(e.getActivitedeconseille());
                        tfActiviteDeconseille.setUIID("LabelBlack");
        Label tfPrix= new Label(""+e.getPrixparpersonne());
                        tfPrix.setUIID("LabelBlack");

        Label tfDuree= new Label(""+e.getDureeparminute());
                        tfDuree.setUIID("LabelBlack");

        Label TFinfoimporatant= new Label(e.getInfoimporatant());
                        TFinfoimporatant.setUIID("LabelBlack");

        Label tfpointderencontre= new Label(e.getPointderencontre());
                        tfpointderencontre.setUIID("LabelBlack");

        Label tfville= new Label(e.getVille());
                        tfville.setUIID("LabelBlack");

        Label tfGouvernerat= new Label(e.getGouvernerat());
                        tfGouvernerat.setUIID("LabelBlack");

        Label tfPays= new Label(e.getPays());
                        tfPays.setUIID("LabelBlack");

        Label tfNbMax= new Label(""+e.getNbmax());
                                tfNbMax.setUIID("LabelBlack");
        Label lMod=new Label("Update");
                        lMod.setUIID("LabelBlack");

        Label lSuppCat=new Label("Delete");
                        lSuppCat.setUIID("LabelBlack");

        Style suppStyle=new Style(lSuppCat.getUnselectedStyle());
        suppStyle.setFgColor(0xf21f1f);
        FontImage suppImage=FontImage.createMaterial(FontImage.MATERIAL_DELETE,suppStyle);
        lSuppCat.setIcon(suppImage);
    
        lSuppCat.addPointerPressedListener(l->{
        Dialog diag=new Dialog("Deleting");
        if(diag.show("deleting","Are you sure to delete?","Cancel","OK")){
            diag.dispose();
        }
        else{
                        diag.dispose();
  if( ServiceActivite.getInstance().deleteAct(e.getId()))
                       
                            try {
                                new ListActivitiesForm(f);
                        } catch (IOException ex) {
                        }
                       
 
        }
                    });
         Style suppStyl=new Style(lMod.getUnselectedStyle());
       FontImage modImage=FontImage.createMaterial(FontImage.MATERIAL_EDIT,suppStyl);
lMod.setIcon(modImage);
        lMod.addPointerPressedListener(l->{
          new  UpdateActivite(res,e).show();
        });
         cl1.add(lbId);
     cl1.add(lbNom);
      cl1.add(lbDesc); 
      cl1.add(lbPointfort);
      cl1.add(tfActiviteDeconseille);
      cl1.add(tfPrix);
      cl1.add(tfDuree);
         cl1.add(TFinfoimporatant);
          cl1.add(tfpointderencontre);
           cl1.add(tfville);
          cl1.add(tfGouvernerat);
          cl1.add(tfPays);
          cl1.add(tfNbMax);

                    
          
         cl1.add(lSuppCat);
         cl1.add(lMod);
            
        cl1.add(cl2);
        cl.add(img);
        cl.add(cl1);
        
        cl.add(cldp);
        f.add(cl);
        f.add(new Label(" "));
        f.refreshTheme();

    }

    
    
}
