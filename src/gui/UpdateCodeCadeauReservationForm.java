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
public class UpdateCodeCadeauReservationForm extends Form {

    public UpdateCodeCadeauReservationForm() {
        this.setTitle("Update Gift Code");
        this.setLayout(BoxLayout.y());
        
        TextField txtcode = new TextField("", "Enter Gift code");
        TextField txtprice = new TextField("", "Enter price");
        Button submitBtn = new Button("Update");
        
        submitBtn.addActionListener((evt) -> {
            
            CodeCadeauReservation c = new CodeCadeauReservation();
            
            if (txtcode.getText().length() == 0 || txtprice.getText().length() == 0 ){
                Dialog.show("Alert", "TextFields cannot be empty.", null, "Ok");
            }
            else if (Double.parseDouble(txtprice.getText())<0){
                 Dialog.show("Alert", "Price cannot be negatif.", null, "Ok");
            }
            else{
                try{
                   c.setCode(txtcode.getText().toString());
                   c.setPrix(Double.parseDouble(txtprice.getText())); 
                   
                   if (new CodeCadeauReservationService().updateCodeCadeauReservationAction(c).equals("success")){
                       Dialog.show("Success", "Gift code successfully updated!", null, "Ok");
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
        
        this.addAll(txtcode,txtprice,submitBtn);
        
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, ev-> new CodeCadeauReservationHome().showBack());
    }
    
    
    
}
