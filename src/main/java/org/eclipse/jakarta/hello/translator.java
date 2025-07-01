package org.eclipse.jakarta.hello;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;


public class translator {
    private static final String GEMINI_API_KEY = "AIzaSyB7T42XhwUx6B3zUJ0fHLzdzM_c1wo_Y24"; //
    private static final String GEMINI_API_URL = "https://generativelanguage.googleapis.com/v1/models/gemini-pro:generateContent";

    public String translate(String text) {
        try {
            if (text == null || text.isEmpty()) {
                return "Can't translate text";
            }

            // Prepare JSON for the request
            JSONObject content = new JSONObject();
            content.put("text", "Translate what is written to moroccan darija, no less, no more  " + text);

            JSONArray contents = new JSONArray();
            contents.put(new JSONObject().put("parts", new JSONArray().put(content)));

            JSONObject json = new JSONObject();
            json.put("contents", contents);
            
            System.out.println("Request JSON: " + json.toString());

            // Create URL with API key as query parameter
            URL url = new URL(GEMINI_API_URL + "?key=" + GEMINI_API_KEY);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            try (OutputStream os = connection.getOutputStream()) {
                os.write(json.toString().getBytes("utf-8"));
                System.out.println("Sent request to Gemini API.");
            }

            int statusCode = connection.getResponseCode();
            System.out.println("Response Code: " + statusCode);

            if (statusCode != HttpURLConnection.HTTP_OK) {
                try (BufferedReader errorReader = new BufferedReader(
                        new InputStreamReader(connection.getErrorStream(), "utf-8"))) {
                    StringBuilder errorResponse = new StringBuilder();
                    String line;
                    while ((line = errorReader.readLine()) != null) {
                        errorResponse.append(line);
                    }
                    System.out.println("Error response: " + errorResponse.toString());
                }
                return "Can't translate text";
            }

            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream(), "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }

                System.out.println("Raw Gemini API Response: " + response.toString());
                
                JSONObject responseBody = new JSONObject(response.toString());
                JSONObject candidates = responseBody.getJSONArray("candidates").getJSONObject(0);
                JSONObject content_result = candidates.getJSONObject("content");
                JSONArray parts = content_result.getJSONArray("parts");
                String translatedText = parts.getJSONObject(0).getString("text").trim();
                
                
                System.out.println("result " + translatedText);
                return translatedText;
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
            return "Can't translate text";
        }
    }
}