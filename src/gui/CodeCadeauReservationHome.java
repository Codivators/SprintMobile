/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.io.Log;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.Button;

/**
 *
 * @author mbembli
 */
public class CodeCadeauReservationHome extends Form{

    public CodeCadeauReservationHome() {
        this.setTitle("GIFT CODES HOME");
        this.setLayout(BoxLayout.y());
        
        Button addcodeBtn = new Button("Add Gift Code");
        Button listcodeBtn = new Button("Gift Codes List");
        Button updatecodeBtn = new Button("Update Gift Code");
        Button removeBtn = new Button("Remove Gift Code");
        
        
        listcodeBtn.addActionListener((evt) -> new ShowListCodeCadeauReservationForm() );
        addcodeBtn.addActionListener((evt) -> new AddCodeCadeauReservationForm().show() );
        updatecodeBtn.addActionListener((evt) -> new UpdateCodeCadeauReservationForm().show() );
        removeBtn.addActionListener((evt) -> new RemoveCodeCadeauReservationForm().show() );
        
        this.addAll(addcodeBtn,listcodeBtn,updatecodeBtn,removeBtn);
    
}

   

}