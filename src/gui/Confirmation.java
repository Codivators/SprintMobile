/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author mbembli
 */
public class Confirmation extends Form{

    public Confirmation() {
        
         this.setTitle("Confirmation");
        this.setLayout(BoxLayout.y());
        Label l = new Label("Thank you for your reservation request");
        Label l2 = new Label("The reservation is currently being processed by us under the request number given above. We therefore contacted the activity provider. As soon as we have their response, we will send you an email with additional information.");
        
        this.addAll(l,l2);
    }
    
    
}
