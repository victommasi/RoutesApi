package br.com.trix.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

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
import br.com.trix.model.Waypoint;
import br.com.trix.repository.RouteRepository;
import br.com.trix.service.RouteService;

//CHAVE API: AIzaSyCGUhLM8pidet05dKWxJ5U9oV0v_mPq9gA

@RestController
@RequestMapping("/")
public class RouteController
{
	private String startPoint;
	private String endPoint;
	private StringBuilder middlePoints;
	private List<String> stop;
	private List<String> path;
	private HttpURLConnection connRequest;

	@Autowired
	private RouteRepository repo;
	
	@RequestMapping("/test")
    public String index() {
        return "index";
    }

	@RequestMapping(value = "/routes)", method = RequestMethod.GET)
	@ResponseBody
	public List <Route> getAllRoutes() {
		  return repo.findAll();
	}
   
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public Route createRoute(@RequestBody List<Waypoint> waypoints) {
		
		RouteService rService = new RouteService();
		
		try {
			rService.getApiResult(waypoints);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
		/*JsonElement jElement = getWaypointOrder(waypoints);
		JsonArray jArray = jElement.getAsJsonArray();
		stop = new ArrayList<String>();
		path = new ArrayList<String>();

		// fill stop and path with jarray content
		fillStopList(jArray);
		fillPathList(jArray);
		

		//create route and save
		Route route = new Route();
		route.setName(waypoints.get(0).getName());
		route.setDate(Calendar.getInstance().getTime());
		route.setVehicleId("001");
		route.setStop(stop);
		route.setPath(path);*/
		
		Route route = new Route();
		//return repo.insert(route);
		return route;
	}

	private void fillStopList(JsonArray jArray) {
		
		for (int i = 0; i < jArray.size(); i++) {
			stop.add(jArray.get(i).getAsJsonObject().get("start_address").getAsString());
		}
		stop.add(jArray.get(0).getAsJsonObject().get("start_address").getAsString());
	}

	private void fillPathList(JsonArray jArray) {
		
		for (int i = 0; i < jArray.size(); i++) {
			path.add(jArray.get(i).getAsJsonObject().get("start_location").getAsJsonObject().get("lat").getAsString());
			path.add(jArray.get(i).getAsJsonObject().get("start_location").getAsJsonObject().get("lng").getAsString());
		}
		path.add(jArray.get(0).getAsJsonObject().get("start_location").getAsJsonObject().get("lat").getAsString());
		path.add(jArray.get(0).getAsJsonObject().get("start_location").getAsJsonObject().get("lng").getAsString());
	}

}
