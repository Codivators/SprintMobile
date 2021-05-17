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

import com.codename1.capture.Capture;
import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Storage;
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


/**
 * The user profile form
 *
 * @author Shai Almog
 */
public class ProfileForm2 extends BaseForm2 {
User u;
    public ProfileForm2(Resources res,User u) throws IOException {
        
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
        
        if (u.getImage().equals("null")){  
        Image imggg = Image.createImage("file://C:/Users/LENOVO/AppData/Local/Temp/temp5731567177592601517s..png");
        lbimageP.setIcon(imggg);}
        
        else {Image imgg = Image.createImage(u.getImage());
        lbimageP.setIcon(imgg);}
        
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
   
     
      CheckBox bupload = CheckBox.createToggle(res.getImage("on-off-off.png"));
        bupload.setUIID("Label");
        bupload.setPressedIcon(res.getImage("on-off-on.png"));
        addStringValue("Profile picture", FlowLayout.encloseRightMiddle(bupload));
          
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
      bupload.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
            
            String path = Capture.capturePhoto(300, 300);
            if (path != null){   
                    try {
                        
                        String filepath = path;
                        String filename = filepath.substring(filepath.lastIndexOf("/") + 1,filepath.length());
                        System.out.println(filename+"kkkkkkkkkkkk");
                        Image imgg = Image.createImage(filepath);
                        lbimageP.setIcon(imgg);
                      
                        
                        User u = UserSession.getUser();
                        ServiceUser.getInstance().updateImage(path,u.getId());
                        u.setImage(path);
                      
                   } 
                    catch (IOException ex) {
                      ex.printStackTrace();
                }
          }
        }});  
    
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
   private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
                add(BorderLayout.CENTER, v));
        add(createLineSeparator(0xeeeeee));
    }
}
