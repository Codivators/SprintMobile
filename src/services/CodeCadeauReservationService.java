/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import static com.codename1.ui.CN.CENTER;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entites.CodeCadeauReservation;
import gui.CodeCadeauReservationHome;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Map;
import utils.Statics;

/**
 *
 * @author mbembli
 */
public class CodeCadeauReservationService {
        
    //convert object to json
      public String convertToJson (CodeCadeauReservation obj){
          
      //Creating the ObjectMapper object
      ObjectMapper mapper = new ObjectMapper();
      //Converting the Object to JSONString
      String jsonString="";
        try {
            jsonString = mapper.writeValueAsString(obj);
        } catch (JsonProcessingException ex) {
            ex.getStackTrace();
        }
        return jsonString;
   }
    
    
    public String addCodeCadeauReservationAction(CodeCadeauReservation c){
        
         try {
            String url = Statics.BASE_URL+"/api/codecadeaureservation/addgiftcode";
            ConnectionRequest request = new ConnectionRequest(url);
            request.setPost(true);
            request.setContentType("application/json");
            request.setRequestBody(this.convertToJson(c));
            NetworkManager.getInstance().addToQueueAndWait(request);

                byte[] data = request.getResponseData();
                JSONParser parser = new JSONParser();
                Map response = parser.parseJSON(new InputStreamReader(new ByteArrayInputStream(data), "UTF-8"));
                return (String)response.get("status");
            } catch (Exception ex) {
               ex.getStackTrace();
            }

        return "";
    }

    public String updateCodeCadeauReservationAction(CodeCadeauReservation c) {
        
        
         try {
            String url = Statics.BASE_URL+"/api/codecadeaureservation/updategiftcode";
            ConnectionRequest request = new ConnectionRequest(url);
            request.setPost(true);
            request.setContentType("application/json");
            request.setRequestBody(this.convertToJson(c));
            NetworkManager.getInstance().addToQueueAndWait(request);

                byte[] data = request.getResponseData();
                JSONParser parser = new JSONParser();
                Map response = parser.parseJSON(new InputStreamReader(new ByteArrayInputStream(data), "UTF-8"));
                return (String)response.get("status");
            } catch (Exception ex) {
               ex.getStackTrace();
            }

        return "";
    }
        
    
    //PARSING : [TEXT/JSON] => Java Objects[CodeCadeauReservation]
    public ArrayList<CodeCadeauReservation> parseJsonAction(String jsonText){
        
        ArrayList<CodeCadeauReservation> codes = new ArrayList<>();
        
        JSONParser jp = new JSONParser();
        try{
            Map<String,Object> codesJson = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            ArrayList<Map<String,Object>> codesList = (ArrayList<Map<String,Object>>) codesJson.get("root");
            
            for(Map<String,Object> obj : codesList ) {
                CodeCadeauReservation c = new CodeCadeauReservation();
                c.setId((int)Float.parseFloat(obj.get("id").toString()));
                c.setCode(obj.get("code").toString());
                c.setPrix(Float.parseFloat(obj.get("prix").toString()));
                
                codes.add(c);
            }
            
        }
        catch(Exception ex){
            ex.getStackTrace();
        }
        
        
        
        return codes;
        
    }
    
    //SHOW : select [TEXT/JSON]
    public ArrayList<CodeCadeauReservation> getCodesCadeauReservation(){
        ArrayList<CodeCadeauReservation> allcodes = new ArrayList<>();
        String url = Statics.BASE_URL + "/api/codecadeaureservation/getallgiftcodes";
        ConnectionRequest req = new ConnectionRequest(url);
        req.setPost(false);
         NetworkManager.getInstance().addToQueueAndWait(req);
            // req.getResponseData[] : byte[]
            allcodes = parseJsonAction(new String(req.getResponseData()));
        
        return allcodes;
        
    }
    
    public void createList(ArrayList<CodeCadeauReservation> codes){
        Form hi = new Form("Show Gift Codes List", new BorderLayout());
         Container list = new Container(BoxLayout.y());
         list.setScrollableY(true);
         for (int i=0;i<codes.size();i++){
             SpanLabel mb = new SpanLabel("Code : "+codes.get(i).getCode()+" | Prix :"+codes.get(i).getPrix());
             list.add(mb);
         }
         
         hi.add(CENTER,list);
         hi.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, ev-> new CodeCadeauReservationHome().showBack());

         hi.show();
         
    }

    public String removeCodeCadeauReservationAction(CodeCadeauReservation c) {

        try {
            String url = Statics.BASE_URL+"/api/codecadeaureservation/removegiftcode";
            ConnectionRequest request = new ConnectionRequest(url);
            request.setPost(true);
            request.setContentType("application/json");
            request.setRequestBody(this.convertToJson(c));
            NetworkManager.getInstance().addToQueueAndWait(request);

                byte[] data = request.getResponseData();
                JSONParser parser = new JSONParser();
                Map response = parser.parseJSON(new InputStreamReader(new ByteArrayInputStream(data), "UTF-8"));
                return (String)response.get("status");
            } catch (Exception ex) {
               ex.getStackTrace();
            }

        return "";
    }
    
    
    
}
