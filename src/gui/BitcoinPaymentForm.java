/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import services.ReservationService;

/**
 *
 * @author mbembli
 */
public class BitcoinPaymentForm extends Form {
         // GET BTC ADRESS
String addr = new ReservationService().getBTCaddress().toString();
    public BitcoinPaymentForm(double amount) {
        this.setTitle("Payment");
        this.setLayout(BoxLayout.y());
        Label l = new Label("BITCOIN");
   

        // QR CODE
        //Create a fresh grey EncodedImage when label doesn't have any icon set initially
        int deviceWidth = Display.getInstance().getDisplayWidth();
        int deviceHeight = Display.getInstance().getDisplayHeight();
        Image placeholder = Image.createImage(deviceWidth , deviceWidth/3, 0xbfc9d2); //square image set to 10% of screen width
        placeholder.scaledHeight(deviceHeight/2);
        EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);
        Label label = new Label();
        label.setIcon(URLImage.createToStorage(encImage, "Large_" + "https://chart.googleapis.com/chart?chs=150x150&cht=qr&chl="+addr, "https://chart.googleapis.com/chart?chs=150x150&cht=qr&chl="+addr, URLImage.RESIZE_SCALE));
        
        SpanLabel btcAddr = new SpanLabel(addr);
        Label titlebtc = new Label("BTC address : ");
        Label hr = new Label("------------------------------------------");
        SpanLabel spl1 = new SpanLabel("1 - Please send "+amount+" USD to the following bitcoin address (you can use QR code too).");
        SpanLabel spl2 = new SpanLabel("2- Click on the 'Confirm payment' button once the amount has been sent.");
                //        
        Button confirmPayment = new Button("Confirm payment");
        
        confirmPayment.addActionListener((evt) -> { 
          String montant =  new ReservationService().checkPaymentReceived(addr);
            if (Double.parseDouble(montant) == 0.0 ){
                Dialog.show("Alert", "No payment received!", null, "Ok");
            }
            else if (Double.parseDouble(montant) < amount){
                
                Dialog.show("Alert", "You need to pay more ("+(amount-Double.parseDouble(montant))+")", null, "Ok");
            }
            else {
               Dialog.show("Alert", "Payment successfully received", null, "Ok"); 
               // add reservation
               
               // redirection to confirmation page
                new Confirmation().show();
            }
        
        });
                
                
         this.addAll(l,label,titlebtc,btcAddr,hr,spl1,spl2,confirmPayment);
        //
        
        
        

    }
    
    
}
