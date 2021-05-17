/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;


import com.codename1.components.ImageViewer;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.CategorieActivite;
import com.mycompany.myapp.services.ServiceCategorieActivite;
import java.io.IOException;
import java.util.ArrayList;




/**
 *
 * @author asus
 */
public class ListCatagoriesActForm {
Form f; 
Resources res;
public ListCatagoriesActForm(Form previous) throws IOException  {
        
            f = new Form("Categories list", BoxLayout.y());
        ArrayList<CategorieActivite> lscat = ServiceCategorieActivite.getInstance().getAllCategories();


        for(CategorieActivite cat : lscat){
            
                addItem(cat,f);
            
        }
        f.show();
    }
    public void addItem(CategorieActivite cat, Form f) throws IOException {
              ImageViewer img = null;
        Dimension d = new Dimension(50, 50);
        
        Container cl = new Container(new BoxLayout(BoxLayout.X_AXIS));
        img = new ImageViewer(Image.createImage("/list.png"));
        Container cl1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Container cl2 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Container cldp = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Label lbimageP = new Label();
        Label lbId = new Label(String.valueOf(cat.getId()) );
        lbId.setUIID("LabelBlack");
        Label lbNom = new Label(cat.getNom());
        lbNom.setUIID("LabelBlack");
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
        if(diag.show("deleting","Are you sure to delete?","Annuler","OK")){
            diag.dispose();
        }
        else{
                        diag.dispose();
  if( ServiceCategorieActivite.getInstance().deleteCat(cat.getId()))
                        
                            try {
                                new ListCatagoriesActForm(f);
                        } catch (IOException ex) {
                        }
                        
 
        }
                    });
         Style suppStyl=new Style(lMod.getUnselectedStyle());
       FontImage modImage=FontImage.createMaterial(FontImage.MATERIAL_EDIT,suppStyl);
lMod.setIcon(modImage);
        lMod.addPointerPressedListener(l->{
          new  UpdateCategorieActForm(res,cat).show();
            
        });
          cl1.add(lbId);
     cl1.add(lbNom);
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

 

   
        

