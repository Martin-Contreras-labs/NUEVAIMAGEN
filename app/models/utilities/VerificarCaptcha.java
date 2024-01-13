package models.utilities;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import controllers.HomeController;


public class VerificarCaptcha {
    public static boolean verificar(String gRecaptchaResponse) throws IOException {
        if (gRecaptchaResponse == null || "".equals(gRecaptchaResponse)) {
            return false;
        }
        try {
        	String url = "https://www.google.com/recaptcha/api/siteverify";
        	String USER_AGENT = "Mozilla/5.0";
        	String secreto = HomeController.config.getString("secretKeyCapcha");
            URL obj = new URL(url);
            HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("User-Agent", USER_AGENT);
            con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
            String postParams = "secret=" + secreto + "&response=" + gRecaptchaResponse;
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(postParams);
            wr.flush();
            wr.close();
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer respuesta = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                respuesta.append(inputLine);
            }
            in.close();
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode2 = mapper.readTree(respuesta.toString());
            boolean success = jsonNode2.findPath("success").booleanValue();
            Double score = jsonNode2.findPath("score").doubleValue();
            
            if(success && score > 0.5) {
            	return(true);
            }else {
            	return(false);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}




