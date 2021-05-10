/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import java.io.IOException;


/**
 *
 * @author bhk
 */
public class HomeForm extends Form {

    Form current;
    /*Garder traçe de la Form en cours pour la passer en paramètres 
    aux interfaces suivantes pour pouvoir y revenir plus tard en utilisant
    la méthode showBack*/
    Resources res;
    public HomeForm() {
        
        
      
        
        current = this; //Récupération de l'interface(Form) en cours
        setTitle("Stack Activity Blog App");
        setLayout(BoxLayout.y());

        Button btnAddTask = new Button("Add Article");
        Button btnListTasks = new Button("List Articles");
                Button btnWeb = new Button("Web comp");
                Button btnShowArticle = new Button("Show Article");

        btnAddTask.addActionListener(e -> new WebBrowser(current));
        btnListTasks.addActionListener(e -> {
            try {
                new ListTasksForm(current);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
             //   btnWeb.addActionListener(e -> new WebBrowser());
               // btnShowArticle.addActionListener(e -> new ShowArticle());

        addAll(btnAddTask, btnListTasks);

    }

}
