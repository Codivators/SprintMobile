/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entites.CodeCadeauReservation;
import entites.Reservation;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;


import utils.Statics;

/**
 *
 * @author mbembli
 */
public class ReservationService {
    
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
      
   
   public ArrayList<String> recalculateTotalAction(CodeCadeauReservation c){
        
         ArrayList<String> str = new ArrayList<>();
         try {
            String url = Statics.BASE_URL+"/api/codecadeaureservation/recalculate";
            ConnectionRequest request = new ConnectionRequest(url);
            request.setPost(true);
            request.setContentType("application/json");
            request.setRequestBody(this.convertToJson(c));
            NetworkManager.getInstance().addToQueueAndWait(request);

                byte[] data = request.getResponseData();
                JSONParser parser = new JSONParser();
                Map response = parser.parseJSON(new InputStreamReader(new ByteArrayInputStream(data), "UTF-8"));
                str.add((String)response.get("status"));
                str.add((String)response.get("price"));
                System.out.println(str.get(0));
                System.out.println(str.get(1));
            } catch (Exception ex) {
               ex.getStackTrace();
            }

        return str;
    }
   
   public String getBTCaddress(){
       
       
       String url = "https://block.io/api/v2/get_new_address/?api_key=1f6a-df3e-e55c-df45";
       try {
         ConnectionRequest req = new ConnectionRequest(url);
         req.setPost(false);
         NetworkManager.getInstance().addToQueueAndWait(req);
         byte[] data = req.getResponseData();
         JSONParser parser = new JSONParser();
         Map response = parser.parseJSON(new InputStreamReader(new ByteArrayInputStream(data), "UTF-8"));
        
         JSONObject obj = new JSONObject(response);
         String adrBTC = obj.getJSONObject("data").getString("address");
         System.out.println(adrBTC);
         return adrBTC;
          } catch (Exception ex) {
              ex.getStackTrace();
          }
       return "";
   }
   
   public String checkPaymentReceived(String btcAddr){
       
        String url = "https://block.io/api/v2/get_address_balance/?api_key=1f6a-df3e-e55c-df45&addresses="+btcAddr;
       try {
         ConnectionRequest req = new ConnectionRequest(url);
         req.setPost(false);
         NetworkManager.getInstance().addToQueueAndWait(req);
         byte[] data = req.getResponseData();
         JSONParser parser = new JSONParser();
         Map response = parser.parseJSON(new InputStreamReader(new ByteArrayInputStream(data), "UTF-8"));
        
         JSONObject obj = new JSONObject(response);
         String prb = obj.getJSONObject("data").getString("pending_received_balance");
         return prb;
          } catch (Exception ex) {
              ex.getStackTrace();
          }
       return "";
   }
   
   public boolean addReservation (Reservation r){
       
       
       return false;
   }
    
}
