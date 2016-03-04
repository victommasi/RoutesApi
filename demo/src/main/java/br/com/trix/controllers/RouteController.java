package br.com.trix.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.trix.model.Route;
import br.com.trix.model.Waypoint;
import br.com.trix.service.RouteRepository;

//CHAVE API: AIzaSyCGUhLM8pidet05dKWxJ5U9oV0v_mPq9gA


@Controller
@RequestMapping("/")
public class RouteController
{
	
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
	   
	  // Optional<Waypoint> firstWaypoint = waypoints.stream().findFirst();
	  /* System.out.println(firstWaypoint);
	   System.out.println("\n\n -----------------------------");
	   System.out.println(waypoints.get(0).getLat() + waypoints.get(0).getLat());*/
	   
	  /* String url = "http://maps.googleapis.com/maps/api/directions/json?"
               + "origin=" + waypoints.get(0).getLat() +
               + "&destination=" + endPoint
               + "&waypoints=optizime:true|"
               + " "
               + "AIzaSyCGUhLM8pidet05dKWxJ5U9oV0v_mPq9gA";
      */
	   
	   waypoints.stream().forEach(w -> w.setName("White"));
	   return new ResponseEntity<List<Waypoint>>(waypoints, HttpStatus.OK);
   }
}
