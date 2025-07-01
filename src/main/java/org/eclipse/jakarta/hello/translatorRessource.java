package org.eclipse.jakarta.hello;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.json.JSONObject;

@Path("translation")
public class translatorRessource {

    @GET
    @Consumes(MediaType.TEXT_PLAIN)  
    @Produces(MediaType.APPLICATION_JSON) 
 
    public Response translate(@QueryParam("Text")String Text) {
        // 
        System.out.println("\nReceived text : " +Text + "\n");

        
        translator translator = new translator();
        String translatedText;

        try {
           
            translatedText = translator.translate(Text); 
            
        } catch (Exception e) {
           
        	System.out.println("funtion failed");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity("{\"error\": \"translation failed: " + e.getMessage() + "\"}")
                           .build();
        }

        
        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("result", translatedText);
        
       // return Response.ok(jsonResponse.toString(), MediaType.APPLICATION_JSON).build();
        return Response.ok(jsonResponse.toString(), MediaType.APPLICATION_JSON)
                   .header("Access-Control-Allow-Origin", "*")
                   .header("Access-Control-Allow-Methods", "GET, POST, OPTIONS")
                   .header("Access-Control-Allow-Headers", "Content-Type")               
                   .build();

    }
}
