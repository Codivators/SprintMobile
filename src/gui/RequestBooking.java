/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import entites.CodeCadeauReservation;
import java.util.ArrayList;
import services.CodeCadeauReservationService;
import services.ReservationService;

/**
 *
 * @author mbembli
 */
public class RequestBooking extends Form {

    boolean usegiftcard = false;
    public RequestBooking(int userid,int act_id,String act_city,String act_name,String date_deb,String date_fin,int nb_pers,double prix_personne) {
         this.setTitle("Booking details");
         this.setLayout(BoxLayout.y());
         
         Label l = new Label("Booking summary :");
         SpanLabel sp  = new SpanLabel(act_city+" : "+act_name);
         SpanLabel sp1 = new SpanLabel(date_deb+" - "+date_fin);
         SpanLabel sp2 = new SpanLabel(nb_pers+" x "+"Adult(s)");
         SpanLabel sp3 = new SpanLabel("Service fees : 10$");
         SpanLabel sp5 = new SpanLabel("Total : ");
         SpanLabel sp4 = new SpanLabel(""+((prix_personne*nb_pers)+10));
         Label l1 = new Label("Promo code");
         TextField giftcode = new TextField("", "Enter Gift code");
         Button recalculBtn = new Button("Realculate");
          recalculBtn.addActionListener((evt) -> {
            
            CodeCadeauReservation c = new CodeCadeauReservation();
            
            if (giftcode.getText().length() == 0  ){
                Dialog.show("Alert", "TextFields cannot be empty.", null, "Ok");
            }
            else{
                if (usegiftcard){
                    Dialog.show("Alert", "you can use only one gift code per booking!.", null, "Ok");
                }
                else{
                    try{
                   c.setCode(giftcode.getText().toString());
                   c.setPrix(0); 
                   
                   ArrayList<String> str = new ReservationService().recalculateTotalAction(c);
                   if (str.get(0).toString().equals("existcode")){
                       double total = Double.parseDouble(sp4.getText().toString());
                       System.out.println(total);
                       // test if gift card price < total (true)
                       if (Double.parseDouble(str.get(1).toString()) > total)
                          Dialog.show("Alert", "Your gift card price > basket price!", null, "Ok");
                       else if (Double.parseDouble(str.get(1).toString()) <= total)
                       sp4.setText(""+(total -Double.parseDouble(str.get(1).toString())));
                       usegiftcard = true;
                       }
                   else if (str.get(0).toString().equals("codenotfound")){
                       Dialog.show("Alert", "Gift code not found.", null, "Ok");
                   }
                }
                catch(Exception e){
                    e.getStackTrace();
                }
                }
                
                
            }
            

        });
         
         
         Label l2 = new Label("Payment method :");
         RadioButton rb1 = new RadioButton("Credit card");
         RadioButton rb2 = new RadioButton("BITCOIN");
         new ButtonGroup(rb1, rb2);
         rb1.setSelected(true);
         Button btnSubmit = new Button("Next");
         btnSubmit.addActionListener((evt) -> {
             if (rb1.isSelected()){
                 System.out.println("creditcard");
             }
             else if (rb2.isSelected()){
                 new BitcoinPaymentForm(Double.parseDouble(sp4.getText().toString())).show();
             }
         
         });
         
         
         this.addAll(l,sp,sp1,sp2,sp3,sp5,sp4,l1,giftcode,recalculBtn,l2,rb1,rb2,btnSubmit);
         

         
         
    }
       
    
    
}
