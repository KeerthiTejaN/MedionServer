package com.starters.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//@Service
//@Transactional
public class FcmNotificationService {
	
	private static final String API_KEY =  "AIzaSyAj7eEqC0SSabOMbs_ruSqzvcrTs4dmTGY";
	
	public void notify(String message, String fcmToken){
		
		try {
			
            // Prepare JSON containing the FCM message content. What to send and where to send.
            JSONObject jGcmData = new JSONObject();
            JSONObject jData = new JSONObject();
            jData.put("message", message);
            
            // Whom to send FCM message.
            jGcmData.put("to", "ezEWc9FsCmE:APA91bGl6Utzr3vUJnLh1ZPNJgD_zjSUcrML9JdtgYXHUivYreAh1YvnPnhx19c8DwK3hrpEgS8A8CknZJd_eJVzvF2yZ7iHChjop4ccElG2d_F8lDTWQEfEmfJbDZ16JNgK6q3pmaJi");
           
            // What to send in FCM message.
            jGcmData.put("data", jData);

            // Create connection to send FCM Message request.
            URL url = new URL("https://fcm.googleapis.com/fcm/send");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Authorization", "key=" + API_KEY);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            // Send FCM message content.
            OutputStream outputStream = conn.getOutputStream();
            outputStream.write(jGcmData.toString().getBytes());

            // Read FCM response.
            InputStream inputStream = conn.getInputStream();
            String resp = IOUtils.toString(inputStream);
            System.out.println(resp);
            System.out.println("Check your device/emulator for notification or logcat for " +
                    "confirmation of the receipt of the GCM message.");
        } catch (IOException e) {
            System.out.println("Unable to send FCM message.");
            System.out.println("Please ensure that API_KEY has been replaced by the server " +
                    "API key, and that the device's registration token is correct (if specified).");
            e.printStackTrace();
        }
    }
	
}
