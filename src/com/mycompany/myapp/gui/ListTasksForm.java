/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Tabs;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Article;
import com.mycompany.myapp.services.ServiceArticle;
import com.mycompany.myapp.services.ServiceCommentaireArticle;
import java.io.IOException;

import java.util.ArrayList;



/**
 *
 * @author bhk
 */
public class ListTasksForm{
Form f;
Resources res;
   
    public ListTasksForm(Form previous, Resources res) throws IOException {
        ArrayList<Article> lsart = ServiceArticle.getInstance().getAllArticles();
        f = new Form("Liste des articles", BoxLayout.y());


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
        Container cldp = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Label lbUser = new Label("added by : "+a.getUsername());
        Label lbTitle = new Label(a.getTitle());
        Label lbComms = new Label("Nb Comments : " + ServiceCommentaireArticle.getInstance().getAllCommArticles(a.getId()).size());
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
        Label lb8 = new Label(dateFormat.format(a.getDatepub()) );
       

        
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
        
        

        
        
        
        
        
        
        cl1.add(lbUser);
        cl1.add(lbTitle);
            cl1.add(lbComms);
        cl1.add(cl2);
        cl.add(img);
        cl.add(cl1);
        cldp.add(lb8);
        cl.add(cldp);
        f.add(cl);
        f.add(new Label(" "));
        f.refreshTheme();

    }
    

    
    
}
