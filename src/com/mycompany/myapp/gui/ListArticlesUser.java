/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Article;
import com.mycompany.myapp.services.ServiceArticle;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author MSI PC
 */
public class ListArticlesUser {
    
    
    Form f;
Resources res;
   
    public ListArticlesUser(Form previous, Resources res, int id) throws IOException {
        ArrayList<Article> lsart = ServiceArticle.getInstance().getAllArticlesUser(id);
        f = new Form("Your Articles", BoxLayout.y());


      Image  icon = Image.createImage("/flesheLeft.png");
        Toolbar.setGlobalToolbar(true);
        
f.getToolbar().addCommandToLeftBar("Back", icon, (e) -> {
            try {
                new HomeForm().show();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        for(Article art : lsart){
            addItem(art,f);
        }
        
                
        
        
        f.show();
    }
    public void addItem(Article a, Form f) throws IOException {
        ImageViewer img = null;
        Dimension d = new Dimension(50, 50);
        
        Container cl = new Container(new BoxLayout(BoxLayout.X_AXIS));
        img = new ImageViewer(Image.createImage("/Article-icon2.jpg"));
        System.out.println(a.getImage());
              
        Container cl1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Container cl2 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Label lbUser = new Label("added by : "+a.getUsername());
        Label lbTitle = new Label(a.getTitle());
        Button btndel = new Button("Delete");
        Button btnupdt = new Button("Update");

        
        lbTitle.addPointerPressedListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent evt) {
                            System.out.println(a.toString() + "$$$$$$$$$$$$$$$");
                            try {
                                ShowArticle showArticle = new ShowArticle(a, res);
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                        }
                    });
        btndel.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent evt) {
                          
                           ServiceArticle.getInstance().deleteArticle(a);
                        }
                    });
        btnupdt.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent evt) {
                          
                            new EditArticle(f, a.getId());                        
                                    }
                    });
        

        
        
        
        
        
        
        cl1.add(lbUser);
        cl1.add(lbTitle);
        cl2.add(btndel);
        cl2.add(btnupdt);
        cl1.add(cl2);
        cl.add(img);
        cl.add(cl1);
        f.add(cl);
        f.refreshTheme();

    }
    
    
}
