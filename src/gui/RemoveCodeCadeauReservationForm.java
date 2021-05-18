/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import entites.CodeCadeauReservation;
import services.CodeCadeauReservationService;

/**
 *
 * @author mbembli
 */
public class RemoveCodeCadeauReservationForm extends Form {

    public RemoveCodeCadeauReservationForm() {
        this.setTitle("Update Gift Code");
        this.setLayout(BoxLayout.y());
        
        TextField txtcode = new TextField("", "Enter Gift code");
        Button submitBtn = new Button("Remove");
              submitBtn.addActionListener((evt) -> {
            
            CodeCadeauReservation c = new CodeCadeauReservation();
            
            if (txtcode.getText().length() == 0){
                Dialog.show("Alert", "TextFields cannot be empty.", null, "Ok");
            }
            else{
                try{
                   c.setCode(txtcode.getText().toString());
                   c.setPrix(0); 
                   if (new CodeCadeauReservationService().removeCodeCadeauReservationAction(c).equals("success")){
                       Dialog.show("Success", "Gift code successfully removed!", null, "Ok");
                   }
                   else if (new CodeCadeauReservationService().updateCodeCadeauReservationAction(c).equals("codenotfound")){
                       Dialog.show("Alert", "Gift code not found.", null, "Ok");
                   }
                }
                catch(NumberFormatException e){
                    Dialog.show("Alert", "price must be a number.", null, "Ok");
                }
                
            }
            

        });
              
       this.addAll(txtcode,submitBtn);
        
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, ev-> new CodeCadeauReservationHome().showBack());
    }
    
}
