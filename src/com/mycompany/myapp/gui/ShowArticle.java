/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.io.FileSystemStorage;
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.BrowserComponent;
import com.codename1.ui.Button;
import static com.codename1.ui.CN.NORTH;
import com.codename1.ui.Command;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Article;
import com.mycompany.myapp.entities.CommentaireArticle;
import com.mycompany.myapp.entities.Tag;
import com.mycompany.myapp.services.ServiceCommentaireArticle;
import com.mycompany.myapp.services.ServiceTags;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;


/**
 *
 * @author MSI PC
 */
public class ShowArticle extends Form{
    Resources res;
    public ShowArticle(Article t, Resources res) throws IOException {
        
      
          Form hi = new Form(t.getTitle(),new BorderLayout());
          Image  icon = Image.createImage("/flesheLeft.png");
        Toolbar.setGlobalToolbar(true);
        hi.getToolbar().addCommandToLeftBar("Back", icon, (e) -> {
              try {
                  new ListTasksForm(hi,res );
              } catch (IOException ex) {
                  ex.printStackTrace();
              }
          });
       /* hi.getToolbar().addCommandToRightBar("Screenshot", icon, (e) -> {
             ImageViewer vg1 =  new ImageViewer(this.getScreenshot());
             hi.add(BorderLayout.NORTH,vg1);
             hi.refreshTheme();
          }); */
       
       Toolbar tb= hi.getToolbar();
        Button btnshot = new Button("Screenshot");
        
        Image icon2 = Image.createImage("/flesheLeft.png");
Container topBar = BorderLayout.east(new Label(icon));
topBar.setUIID("SideCommand");
tb.addComponentToRightSideMenu(topBar);


tb.addMaterialCommandToRightSideMenu("Share", FontImage.MATERIAL_SHARE, e -> {
            new ShareArticle();

});

 
        
        
    final BrowserComponent cmp = new BrowserComponent();
           Container cl1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
           Container clMain = new Container(new BoxLayout(BoxLayout.Y_AXIS));

           TextField tfComment = new TextField();
           tfComment.setUIID("TextField");
           Button btnaddComment = new Button("Add");


    ArrayList <CommentaireArticle> lsComm = ServiceCommentaireArticle.getInstance().getAllCommArticles(t.getId());
    for(CommentaireArticle comm : lsComm){
               addItem(comm,hi,cl1);

    }
    CommentaireArticle comment = new CommentaireArticle();
    
    comment.setContenu(tfComment.getText());
    comment.setArticlename(t.getTitle());

            btnaddComment.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent evt) {
                            if (tfComment.getText().length() == 0){
                            Dialog.show("Blank comment", "Please enter a comment", new Command("OK"));
                            }
                            else {
                          ServiceCommentaireArticle.getInstance().addCommentArticle(comment,tfComment.getText() );
                              try {
                                  addLastItem(tfComment.getText(), comment.getUsername(),hi,cl1);
                                  hi.showBack();
                                  
                              } catch (IOException ex) {
                                  ex.printStackTrace();
                              }
                            }
    
                      
                       }
                    });
    
    btnshot.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent evt) {
                         
                    ImageViewer vg1 =  new ImageViewer(getScreenshot());
                    hi.add(BorderLayout.NORTH,vg1);
                    hi.refreshTheme();
                      
                       }
                    });
    
    cmp.setPage(t.getTexthtml(), NORTH);
    Button btn = new Button("your Article");
    clMain.add(btnshot);
    clMain.add(tfComment);
    clMain.add(btnaddComment);
    clMain.add(cl1);
    clMain.setScrollableY(true);
    hi.add(BorderLayout.NORTH, new Label("Tags :" + getAllTags(t.getId()).toString()));
    hi.add(BorderLayout.SOUTH,clMain);
    hi.add(CENTER, cmp);
   
    hi.show();
     

    }
    public void addItem(CommentaireArticle a, Form f, Container cl1) throws IOException {
           
        ImageViewer vg = new ImageViewer(Image.createImage("/user-male.png")); 
                    Label lb6 = new Label(a.getUsername());
                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
                    System.out.println(dateFormat.format(a.getDatepub()));
                    Label lb8 = new Label(dateFormat.format(a.getDatepub()) );
                    Label lb7 = new Label(a.getContenu());


              
        Container cl2 = new Container(new BoxLayout(BoxLayout.X_AXIS));


        cl2.add(vg);
        cl2.add(lb6);
        cl2.add(new Label("         "));
        cl2.add(lb8);
        cl1.add(cl2);
        cl1.add(lb7);
        
      

    }
    
    public void addLastItem(String str, String str2, Form f, Container cl1) throws IOException {
           
        ImageViewer vg = new ImageViewer(Image.createImage("/user-male.png")); 
                    Label lb6 = new Label("Your comment");
                    Label lb7 = new Label(str);


              
        Container cl2 = new Container(new BoxLayout(BoxLayout.X_AXIS));


        cl2.add(vg);
        cl2.add(lb6);
        cl1.add(cl2);
        cl1.add(lb7);
        
      

    }
    
    public static Image getScreenshot() {
        Form form = Display.getInstance().getCurrent();
        if (form != null) {
            Image screenshot = Image.createImage(form.getWidth(), form.getHeight());
            form.paintComponent(screenshot.getGraphics(), true);
            return screenshot;
        } else {
            return null;
        }
    }
    
    public ArrayList<Tag> getAllTags(int id){
    ArrayList<Tag> lstags = ServiceTags.getInstance().getAlltags(id);
    return lstags;
    }
    
    
    
}
