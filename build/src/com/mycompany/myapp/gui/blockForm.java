/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;


import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author LENOVO
 */
public class blockForm extends Form {
  Form current;
    
    public blockForm() {
      current = this; //Récupération de l'interface(Form) en cours
        setTitle("block");
        setLayout(BoxLayout.y());

        add(new Label("SORRY , You are blocked!!!"));}
}
