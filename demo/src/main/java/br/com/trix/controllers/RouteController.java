package br.com.trix.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.net.HttpURLConnection;
import java.net.URL;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import br.com.trix.model.Route;
import br.com.trix.model.Waypoint;
import br.com.trix.service.RouteRepository;

//CHAVE API: AIzaSyCGUhLM8pidet05dKWxJ5U9oV0v_mPq9gA


@RestController
@RequestMapping("/")
public class RouteController
{
   private String startPoint;
   private String endPoint;
   private StringBuilder middlePoints;
	
   @Autowired
   private RouteRepository repo;

   @RequestMapping("/")
   public String index()
   {
      return "home/index";
   }
   
   /*
   * public Document getDocument(String start, String end, List<Waypoint> list) {
        try {
            String startPoint = URLEncoder.encode(start, "utf-8");
            String endPoint = URLEncoder.encode(end, "utf-8");
			
            String url = "http://maps.googleapis.com/maps/api/directions/json?"
                    + "origin=" + startPoint
                    + "&destination=" + endPoint
                    + "&waypoints=optizime:true|"
                    + " "
                    + "AIzaSyCGUhLM8pidet05dKWxJ5U9oV0v_mPq9gA";

            HttpClient httpClient = new DefaultHttpClient();
            HttpContext localContext = new BasicHttpContext();
            HttpPost httpPost = new HttpPost(url);
            HttpResponse response = httpClient.execute(httpPost, localContext);
            InputStream in = response.getEntity().getContent();
            DocumentBuilder builder = DocumentBuilderFactory.newInstance()
                    .newDocumentBuilder();
            Document doc = builder.parse(in);
            return doc;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    */
   
   @RequestMapping(value = "/route)", method = RequestMethod.GET, produces = "application/json")
   @ResponseBody
   public ResponseEntity <Route> getRoute(@RequestBody Route route){
	   
	   return new ResponseEntity<Route>(route, HttpStatus.OK);
   }
   
  
   @RequestMapping(value = "/routes)", method = RequestMethod.GET, produces = "application/json")
   @ResponseBody
   public ResponseEntity <List<Waypoint>> readRoutes(@RequestBody List<Waypoint> waypoints){
	   
	   return new ResponseEntity<List<Waypoint>>(waypoints, HttpStatus.OK);
   }
   
   
   @RequestMapping(value = "/create", method = RequestMethod.POST)
   public ResponseEntity<List<Waypoint>> createRoute(@RequestBody List<Waypoint> waypoints){
	   
	  String route = getRoute(waypoints);
	  
	   return new ResponseEntity<List<Waypoint>>(waypoints, HttpStatus.OK);
   }
   
   public String getRoute(List<Waypoint> waypoints) {
       
	   String test;
	   System.out.println("\n\n -----------------------------");
	   //Ponto de origem também é o destino
	   this.startPoint = waypoints.get(0).toString();
	   this.endPoint = startPoint;
	   this.middlePoints = new StringBuilder();
	  
	   //Os pontos dos traçado da rota vao ter suas lat e lng concatenadas para criar a url da Google Api.
	   for(int i = 1; i < waypoints.size(); i++){
		   this.middlePoints.append("|");
		   this.middlePoints.append(waypoints.get(i).toString());
	   }
	   
	   String sUrl = "https://maps.googleapis.com/maps/api/directions/json?"
               + "origin=" + startPoint 
               + "&destination=" + endPoint
               + "&waypoints=optimize:true"
               + middlePoints 
               + "&key=AIzaSyCGUhLM8pidet05dKWxJ5U9oV0v_mPq9gA";
	   
	   System.out.println("api call: "+sUrl);

       try {
    	   //Connect to Url
    	   URL url = new URL(sUrl);
    	   HttpURLConnection request = (HttpURLConnection) url.openConnection();
    	   request.connect();
    	   
    	   //Convert Json
    	   JsonParser jsonParser = new JsonParser();
    	   JsonElement jsonElement = jsonParser.parse(new InputStreamReader((InputStream) request.getContent()))
				    			   	.getAsJsonObject().getAsJsonArray("routes").get(0)
				    			   	.getAsJsonObject().getAsJsonArray("waypoint_order");

    	   //JsonObject jsonWaypoints = jsonElement.getAsJsonObject().get; 
    	   // = jsonObject.getAsJsonArray("routes").get(0).getAsJsonArray()("w"); 
    	  
    	   /*JsonObject jsonWaypoints = jsonRoutes.getAsJsonObject().get("summary"); 
    	   jsonWaypoints = jsonObject.getAsJsonObject("summary");*/
    	   System.out.println(jsonElement);
    	   
    	   
    	   request.disconnect();	
    	  return ""; 
       } catch (IOException ie){ 
    	   ie.printStackTrace();
    	   return null;
       } 
   }

}
