/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import entites.CodeCadeauReservation;
import java.util.ArrayList;
import services.CodeCadeauReservationService;

/**
 *
 * @author mbembli
 */
public class ShowListCodeCadeauReservationForm extends Form {

    public ShowListCodeCadeauReservationForm() {
        
        this.setTitle("Show Gift Codes List");
        this.setLayout(BoxLayout.y());
        SpanLabel sp = new SpanLabel();
        CodeCadeauReservationService cs = new CodeCadeauReservationService();
        ArrayList<CodeCadeauReservation> list = cs.getCodesCadeauReservation();
        cs.createList(list);
        
        this.add(sp);
        
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, ev-> new CodeCadeauReservationHome().showBack());
        
    }
    
    
    
}
