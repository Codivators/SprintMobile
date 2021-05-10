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
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Article;
import com.mycompany.myapp.services.ServiceArticle;
import java.io.IOException;
import java.io.InputStream;

import java.util.ArrayList;


/**
 *
 * @author bhk
 */
public class ListTasksForm{
Form f;
   
    public ListTasksForm(Form previous) throws IOException {
        ArrayList<Article> lsart = ServiceArticle.getInstance().getAllArticles();
        f = new Form("Liste des articles", BoxLayout.y());

        for(Article art : lsart){
            addItem(art,f);
        }
        f.show();
    }
    public void addItem(Article a, Form f) throws IOException {
        ImageViewer img = null;
        Dimension d = new Dimension(50, 50);
        
        Container cl = new Container(new BoxLayout(BoxLayout.X_AXIS));
        img = new ImageViewer(Image.createImage("/degustation2.jpg"));
        
              
        Container cl1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                Container cl2 = new Container(new BoxLayout(BoxLayout.X_AXIS));

        Label lbUser = new Label(String.valueOf(a.getUserid()) );
        Label lbTitle = new Label(a.getTitle());
        Button btndel = new Button("Delete");
        Button btnupdt = new Button("Update");

        
        lbTitle.addPointerPressedListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent evt) {
                            System.out.println(a.toString() + "$$$$$$$$$$$$$$$");
                            ShowArticle showArticle = new ShowArticle(a);
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
    
    
/*public ListTasksForm(Resources res, Form previous) {
        super("NewsFeed",BoxLayout.y());
        Toolbar tb=new Toolbar(true);
      getTitleArea().setUIID("Container");
              setTitle("List categories");

                 
ArrayList<Article>list=ServiceArticle.getInstance().getAllArticles();     
for(Article cat:list){
    addButton(cat,res);
    
}
    getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
}
 

    private void addButton(Article cat,Resources res) {
        int height=Display.getInstance().convertToPixels(11.5f);
                int width=Display.getInstance().convertToPixels(14f);

        Label ta= new Label(cat.getTitle());
add(ta);
        Label lSuppCat=new Label();
        lSuppCat.setUIID("NewsTopLine");
        Style suppStyle=new Style(lSuppCat.getUnselectedStyle());
        suppStyle.setFgColor(0xf21f1f);
        FontImage suppImage=FontImage.createMaterial(FontImage.MATERIAL_DELETE,suppStyle);
        lSuppCat.setIcon(suppImage);
        lSuppCat.setTextPosition(RIGHT);
    add(lSuppCat);
    
    lSuppCat.addPointerPressedListener((ActionEvent l)->{
        
                        
        new ShowArticle(cat);
        
        
    });
        
    }
    
    
    
    
    
    public ListTasksForm(Form previous) {
        setTitle("List Articles");
        int i;
        
        for(i=0;i<ServiceArticle.getInstance().getAllArticles().size();i++){
        SpanLabel sp = new SpanLabel();
        SpanLabel sp1 = new SpanLabel();
         sp.setText(ServiceArticle.getInstance().getAllArticles().get(i).toString());
            add(sp);
        }
        
       
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
    
    
    
    
    
    
    }*/
    
    
}
