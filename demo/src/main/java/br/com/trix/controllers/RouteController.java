package br.com.trix.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.swing.plaf.synth.SynthSeparatorUI;

import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import br.com.trix.model.Route;
import br.com.trix.model.Vehicle;
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
   //private int waypointsOrder;
	
   @Autowired
   private RouteRepository repo;

   @RequestMapping("/")
   public String index()
   {
      return "home/index";
   }
   
   /*@RequestMapping(value = "/route)", method = RequestMethod.GET, produces = "application/json")
   @ResponseBody
   public ResponseEntity <Route> getRoute(@RequestBody Route route){
	   
	   	return new ResponseEntity<Route>(route, HttpStatus.OK);
   }
   
   @RequestMapping(value = "/routes)", method = RequestMethod.GET, produces = "application/json")
   @ResponseBody
   public List<Route> getAll() {
    
   		return repo.findAll();
  }
   */
   
   @RequestMapping(value = "/create", method = RequestMethod.POST)
   public ResponseEntity<List<Waypoint>> createRoute(@RequestBody List<Waypoint> waypoints){
	   JsonElement jElement = getWaypointOrder(waypoints);
	   JsonArray jArray = jElement.getAsJsonArray();
	   List<String> stop = new ArrayList<String>();
	   List<String> path = new ArrayList<String>();
	   
	   Route route = new Route();
	   route.setName(waypoints.get(0).getName());
	   route.setDate(LocalDateTime.now());
	   route.setVehicleId("000");
	   for(int i = 0; i < jArray.size(); i++){
		   stop.add(jArray.get(i).getAsJsonObject().get("start_address").getAsString());
		   
	   }
	   route.setStops(stop);
	   for(int i = 0; i < jArray.size(); i++){
		   path.add(jArray.get(i).getAsJsonObject().get("start_location").getAsJsonObject().get("lat").getAsString());
		   path.add(jArray.get(i).getAsJsonObject().get("start_location").getAsJsonObject().get("lng").getAsString());
	   }
	   route.setPath(path);
	   
	   return new ResponseEntity<List<Waypoint>>(waypoints, HttpStatus.OK);
   }
   
   public Route saveRoute(Route route) {
     return repo.save(route);
   }
   
   public JsonElement getWaypointOrder(List<Waypoint> waypoints) {
	   System.out.println("\n\n-----------------------------");
	   //Origin point is final point as well
	   this.startPoint = waypoints.get(0).toString();
	   this.endPoint = startPoint;
	   this.middlePoints = new StringBuilder();
	  
	   //Waipoints will have its lat and lng concatenated to create an url got from Google Api bellow.
	   for(int i = 1; i < waypoints.size(); i++){
		   this.middlePoints.append("|");
		   this.middlePoints.append(waypoints.get(i).toString());
	   }
	   
	   String sUrl = "https://maps.googleapis.com/maps/api/directions/json?"
               + "origin=" + startPoint 
               + "&destination=" + endPoint
               + "&waypoints=optimize:true" + middlePoints 
               + "&key=AIzaSyCGUhLM8pidet05dKWxJ5U9oV0v_mPq9gA";
	   System.out.println(sUrl);
	   
	  
    	   //Connect to Url
    	   //Get the result of request and treat it
    	   try {
    		   URL url = new URL(sUrl);
    		   HttpURLConnection connRequest = (HttpURLConnection) url.openConnection();
    		   connRequest.connect();
    		   
    	   //Convert Json
    	   JsonParser jsonParser = new JsonParser();
    	   JsonElement jsonElement = jsonParser.parse(new InputStreamReader((InputStream) connRequest.getContent()))
				    			   	.getAsJsonObject().getAsJsonArray("routes").get(0)
				    			   	.getAsJsonObject().getAsJsonArray("legs");
    	   return jsonElement;
    	  //return castJsonToInt(jsonElement); 

       }  catch (MalformedURLException he){ 
    	   he.printStackTrace();
    	   return null;
       } catch (IOException ie){ 
    	   ie.printStackTrace();
    	   return null;
       } finally {
    	   //connRequest.connect();
       }
   }

}
