package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
//
//import testweb.model.AppDataBase;
//import testweb.model.FCMBean;

@WebServlet("/fcm")
public class FcmController extends HttpServlet {

   // Method to send Notifications from server to client end.

   /**
    * 
    */
   private static final long serialVersionUID = 1L;
   public final static String AUTH_KEY_FCM = "AAAAHpFL9uQ:APA91bELnoJVl74fcA9ICQ7BIDOHiCjpv1ateJqWZiF7YBurGW7aam3GtPNtroqxwlG3KZ6vaT0g1GF81vCasDZoiM3dJTdArpGXy6KqOLFPPtb_wyC0UhylFJWIiqGkVezZ-Xi9dw5Y";

   public final static String API_URL_FCM = "https://fcm.googleapis.com/fcm/send";

   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      // TODO Auto-generated method stub
      try {

         String type = req.getParameter("type"); // 1:1, 멀티 구분
         String title = req.getParameter("title");
         String message = req.getParameter("comment");
         String token = req.getParameter("token");
         System.out.println(token + ":" + title + ":" + message);

         pushFCMNotification("test type", "test postMessge", "test title", token);
      } catch (Exception e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }

   @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      // TODO Auto-generated method stub
//      try {
//         AppDataBase db = AppDataBase.getInstance();
//         String type = "0";
//         String email = req.getParameter("email");
//         String title = req.getParameter("title");
//         String postMessge = req.getParameter("comment");
//         String token = db.getToken(email);
//         System.out.println(token + ":" + title + ":" + postMessge);
//
//         String myEmail = req.getParameter("my");
//         
//         String key = req.getParameter("key");
//         String chat_code = "";
//         if(key.contentEquals("multi")) {
//            chat_code = req.getParameter("chat_code");
//         } else {
//            chat_code = db.getChat_code(myEmail, email);   
//         }
//         pushFCMNotification(type, postMessge, title, token, myEmail, chat_code, email);
//      } catch (Exception e) {
//         // TODO Auto-generated catch block
//         e.printStackTrace();
//      }
   }

   public static void pushFCMNotification(String typeStr, String message, String title, String token) throws Exception {
      String FMCurl = API_URL_FCM;
      String deviceToken = token;

      URL url = new URL(FMCurl);
      HttpURLConnection conn = (HttpURLConnection) url.openConnection();

      conn.setUseCaches(false);
      conn.setDoInput(true);
      conn.setDoOutput(true);
      conn.setRequestMethod("POST");
      conn.setRequestProperty("Authorization", "key=" + AUTH_KEY_FCM);
      conn.setRequestProperty("Content-Type", "application/json");

      JSONObject json = new JSONObject();
      JSONObject info = new JSONObject();

      info.put("title", title);
      info.put("message", message);
      info.put("type", typeStr);
      info.put("click_action", ".notify");
      info.put("img", "high");
      info.put("link_type", "PRODUCT");
      info.put("link_no", "4");
         
//      json.put("notification", info);
      json.put("data", info);
      json.put("priority", "high");
      json.put("to", deviceToken); // deviceID

     
      System.out.println(json.toString());
      try (OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream(), "UTF-8")) {
         wr.write(json.toString());
         wr.flush();
      } catch (Exception e) {
      }

      if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
         throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
      }

      BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

      String output;
      System.out.println("Output from Server .... \n");
      while ((output = br.readLine()) != null) {
         System.out.println(output);
      }

      conn.disconnect();
   }



}