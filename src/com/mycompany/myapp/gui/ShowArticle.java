/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.ToastBar;
import com.codename1.ui.BrowserComponent;
import com.codename1.ui.Button;
import static com.codename1.ui.CN.NORTH;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Article;
import java.io.IOException;

/**
 *
 * @author MSI PC
 */
public class ShowArticle extends Form{
    
    public ShowArticle(Article t) throws IOException {
        
      
          Form hi = new Form(new BorderLayout());
    final BrowserComponent cmp = new BrowserComponent();
    Container ct = new Container(new BoxLayout(BoxLayout.Y_AXIS));
    Container ct2 = new Container(new BoxLayout(BoxLayout.X_AXIS));

    ImageViewer vg = new ImageViewer(Image.createImage("/user-male.png"));
    
    Label lb1 = new Label("THIS IS A LABEL");
    Label lb2 = new Label(" ");
    Label lb3 = new Label(" ");
    Label lb4 = new Label(" ");
    Label lb5 = new Label(" ");
        Label lb6 = new Label("UserName 11");


    cmp.setPage(t.getTexthtml(), NORTH);
    Button btn = new Button("your Article");
    btn.addActionListener(evt->{
        ToastBar.showInfoMessage(cmp.executeAndReturnString("navigator.userAgent"));
    });
    ct2.add(vg);
    ct2.add(lb6);
    ct.add(ct2);
    ct.add(lb1);
    ct.add(lb2);
    ct.add(lb3);
    ct.add(lb4);
    ct.add(lb5);

    hi.add(CENTER, cmp);
    hi.add(NORTH, btn);
    hi.add(BorderLayout.SOUTH, ct);
    hi.show();
     

    }
}
