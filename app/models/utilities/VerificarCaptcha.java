package models.utilities;

import java.io.*;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;

import javax.net.ssl.HttpsURLConnection;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import controllers.HomeController;


public class VerificarCaptcha {
    public static boolean verificar(String gRecaptchaResponse) throws IOException {
        if (gRecaptchaResponse == null || gRecaptchaResponse.isEmpty()) {
            return false;
        }
        String url = "https://www.google.com/recaptcha/api/siteverify";
        String USER_AGENT = "Mozilla/5.0";
        String secreto = HomeController.config.getString("secretKeyCapcha");
        try {
            URL obj = URI.create(url).toURL();
            HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("User-Agent", USER_AGENT);
            con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded"); // IMPORTANTE

            String postParams = "secret=" + URLEncoder.encode(secreto, "UTF-8") +
                    "&response=" + URLEncoder.encode(gRecaptchaResponse, "UTF-8");

            con.setDoOutput(true);

            try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
                wr.writeBytes(postParams);
                wr.flush();
            }

            int responseCode = con.getResponseCode();
            InputStream is = (responseCode == 200) ? con.getInputStream() : con.getErrorStream();

            StringBuilder respuesta = new StringBuilder();

            try (BufferedReader in = new BufferedReader(new InputStreamReader(is))) {
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    respuesta.append(inputLine);
                }
            }

            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readTree(respuesta.toString());
            boolean success = jsonNode.path("success").asBoolean(false);
            double score = jsonNode.path("score").asDouble(0.0);

            return success && score > 0.5;


            
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}




