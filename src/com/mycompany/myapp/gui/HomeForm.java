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
import com.codename1.ui.util.Resources;
import java.io.IOException;




/**
 *
 * @author bhk
 */
public class HomeForm extends Form {

    Form current;
    Resources res;
    /*Garder traçe de la Form en cours pour la passer en paramètres 
    aux interfaces suivantes pour pouvoir y revenir plus tard en utilisant
    la méthode showBack*/
    
    public HomeForm() {
        current = this; //Récupération de l'interface(Form) en cours
        
        setLayout(BoxLayout.y());

        add(new Label("Choose an option"));
        Button btnAddTask = new Button("Add activity");
        Button btnListTasks = new Button("List activities");
        Button btnAddCat = new Button("Add Category");
        Button btnListCat = new Button("List categories");
                Button btnListAct = new Button("List activities for client");



        btnAddTask.addActionListener(e -> new AddActiviteForm(current).show());
        btnListTasks.addActionListener(e -> {
         
            try {
                new ListActivitiesForm(current);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
           
        });
        btnAddCat.addActionListener(e -> new AddCategorieActForm(current).show());
        btnListCat.addActionListener(e -> {
            try {
                new ListCatagoriesActForm(current);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        btnListAct.addActionListener(e -> {
            try {
                new ListActivitiesClientForm(current);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        addAll(btnAddTask, btnListTasks,btnAddCat,btnListCat,btnListAct);

    }

}
