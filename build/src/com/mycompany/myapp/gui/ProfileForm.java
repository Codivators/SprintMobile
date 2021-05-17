/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */

package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.MultiButton;
import com.codename1.components.ScaleImageLabel;
import com.codename1.ext.filechooser.FileChooser;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.services.ServiceUser;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.ui.CN;
import com.codename1.ui.events.ActionEvent.Type;
import com.codename1.ui.util.ImageIO;
import java.io.OutputStream;


/**
 * The user profile form
 *
 * @author Shai Almog
 */
public class ProfileForm extends BaseForm {
User u;
    public ProfileForm(Resources res,User u) throws IOException {
        
        super("Newsfeed", BoxLayout.y());
        u = UserSession.getUser(); 
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        setTitle("Profile");
        getContentPane().setScrollVisible(false);
        super.addSideMenu(res);
        tb.addSearchCommand(e -> {});
        
        
        Image img = res.getImage("a4.jpg");
        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
        }
        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);

       Label facebook = new Label("", res.getImage(""), "BottomPad");
       // Label twitter = new Label("486 followers", res.getImage("twitter-logo.png"), "BottomPad");
       // facebook.setTextPosition(BOTTOM);
       // twitter.setTextPosition(BOTTOM);
       
      
      Label lbimageP = new Label();
        
        /*if (u.getImage().equals("null")){  
        Image imggg = Image.createImage("file://C:/Users/LENOVO/AppData/Local/Temp/temp5731567177592601517s..png");
        lbimageP.setIcon(imggg);}
        
        else {Image imgg = Image.createImage(u.getImage());
        lbimageP.setIcon(imgg);}*/
        
        add(LayeredLayout.encloseIn(
                sl,
                BorderLayout.south(
                    GridLayout.encloseIn(3, 
                            facebook,
                            FlowLayout.encloseCenter(
                               lbimageP
                            
                    )
                )
        )));
      
    
        TextField tfnomuser = new TextField(u.getNomuser());
        tfnomuser.setUIID("TextFieldBlack");
        addStringValue("Username", tfnomuser);

        TextField tfemail = new TextField(u.getEmail());
        tfemail.setUIID("TextFieldBlack");
        addStringValue("E-Mail", tfemail);
   
     
      CheckBox img1 = CheckBox.createToggle(res.getImage("on-off-off.png"));
      img1.setUIID("Label");
      img1.setPressedIcon(res.getImage("on-off-on.png"));
      addStringValue("Profile picture", FlowLayout.encloseRightMiddle(img1));
      MultiButton multiSelect = new MultiButton("Edit profile");  
    
         
         
     Button edit = new Button("Edit profile");
     addStringValue("", edit);
    
    
    
     CheckBox capture = CheckBox.createToggle(res.getImage("news-tab-down-arrow.png"));
     capture.setUIID("Label");
     capture.setPressedIcon(res.getImage("news-tab-down-arrow.png"));
     addStringValue("Screenshot", FlowLayout.encloseRightMiddle(capture));
     
     capture.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent evt) {
                         
                    ImageViewer vg1 =  new ImageViewer(getScreenshot());
                    add(vg1);
                    refreshTheme();
                      
                       }
                    });
     
   
    
    img1.addActionListener((ActionEvent e) -> {
        
            if (FileChooser.isAvailable()) {
                FileChooser.setOpenFilesInPlace(true);
                FileChooser.showOpenDialog(multiSelect.isSelected(), ".jpg, .jpeg, .png/plain", (ActionEvent e2) -> {
                    if (e2 == null || e2.getSource() == null) {
                        add("No file was selected");
                        revalidate();
                        return;
                    }
                    if (multiSelect.isSelected()) {
                        String[] paths = (String[]) e2.getSource();
                        for (String path : paths) {
                            System.out.println(path);
                            CN.execute(path);
                        }
                        return;
                    }

                    String file = (String) e2.getSource();
                    
                    if (file == null) {
                        add("No file was selected");
                        revalidate();
                    } else {
                        Image logo ;
                        Image logo2 ;
                        Image logo3 ;
                        User u1 = UserSession.getUser();
                        System.out.println(u1+"jjjjjjjjjjjjjjj");
                        try {
                             logo = Image.createImage(file).scaledHeight(300);;
                             logo2 = Image.createImage(u1.getImage());
                             logo3 = Image.createImage("/no_user_picture.jpg");
                                if (u1.getImage().equals("null")){ //  add(logo);
                                    lbimageP.setIcon(logo3);                             }
                                else if (u1.getImage()!= "null"){lbimageP.setIcon(logo2);}
                                else {lbimageP.setIcon(logo);}
                                    //
                            String imageFile = FileSystemStorage.getInstance().getAppHomePath() + "photo.png";

                            try (OutputStream os = FileSystemStorage.getInstance().openOutputStream(imageFile)) {
                                System.out.println(imageFile);
                                ImageIO.getImageIO().save(logo, os, ImageIO.FORMAT_PNG, 1);
                            } catch (IOException err) {
                            }
                        } catch (IOException ex) {
                        }

                        String extension = null;
                        if (file.lastIndexOf(".") > 0) {
                            extension = file.substring(file.lastIndexOf(".") + 1);
                            StringBuilder hi = new StringBuilder(file);
                            if (file.startsWith("file://")) {
                                hi.delete(0, 7);
                            }
                            int lastIndexPeriod = hi.toString().lastIndexOf(".");
                            Log.p(hi.toString());
                            String ext = hi.toString().substring(lastIndexPeriod);
                            String hmore = hi.toString().substring(0, lastIndexPeriod - 1);
                           
                            String namePic;
                            
                            try {
                                namePic = saveFileToDevice(file, ext);
                                System.out.println(namePic);
                                revalidate();
                            } catch (IOException ex) {
                               
                            }
                            String namePic2 =  file.substring(file.lastIndexOf("/") + 1,file.length());
                            User u2 = UserSession.getUser();
                            ServiceUser.getInstance().updateImage(namePic2,u1.getId());
                            u1.setImage(namePic2);
                    }
                    }
                        });
            }
                });
     edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                        User u = UserSession.getUser();
                        ServiceUser.getInstance().updateUser(tfemail.getText(),tfnomuser.getText(),u.getId());
                        u.setNomuser(tfnomuser.getText());
                        u.setEmail(tfemail.getText());
                      
                        Dialog.show("Done", "Your changes have been successfully saved", new Command("OK"));          
        }});  
    
    
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

protected String saveFileToDevice(String hi, String ext) throws IOException {
        URI uri;
        try {
            uri = new URI(hi);
            String path = uri.getPath();
            int index = hi.lastIndexOf("/");
            hi = hi.substring(index + 1);
            System.out.println(hi);
            return hi;
            
        } catch (URISyntaxException ex) {
        }
        return "hh";
    }
 
   private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
                add(BorderLayout.CENTER, v));
        add(createLineSeparator(0xeeeeee));
   }
}
