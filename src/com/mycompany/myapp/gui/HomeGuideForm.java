/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author 2020
 */
public class HomeGuideForm extends Form {
     Form current;
   
    
    public HomeGuideForm() {
        current = this; 
        setTitle("Home");
        setLayout(BoxLayout.y());

        add(new Label("Choose an option"));
        Button btnAddGuide = new Button("Add Guide");
        Button btnListGuide = new Button("List Guide");

        btnAddGuide.addActionListener(e -> new addGuideForm(current).show());
        btnListGuide.addActionListener(e -> new ListGuideForm(current).show());
        addAll(btnAddGuide, btnListGuide);

    }
    
}
