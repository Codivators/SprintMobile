/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ToastBar;
import com.codename1.ui.BrowserComponent;
import com.codename1.ui.Button;
import static com.codename1.ui.CN.CENTER;
import static com.codename1.ui.CN.NORTH;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Toolbar;
import com.codename1.ui.html.HTMLComponent;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import java.io.IOException;

/**
 *
 * @author MSI PC
 */
public class WebBrowser  extends Form {
    
    public WebBrowser(Form previous) throws IOException {
        
      Form hi = new Form("Start your Article", new BorderLayout());
      Image  icon = Image.createImage("/flesheLeft.png");
        Toolbar.setGlobalToolbar(true);
        hi.getToolbar().addCommandToLeftBar("Back", icon, (e) -> new HomeForm().show());
    final BrowserComponent cmp = new BrowserComponent();

    cmp.setURL("http://127.0.0.1:8000/htmleditor");

   
   
   hi.add(CENTER, cmp);
    hi.show();
     getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
                , e-> previous.showBack()); // Revenir vers l'interface précédente
    }
}
