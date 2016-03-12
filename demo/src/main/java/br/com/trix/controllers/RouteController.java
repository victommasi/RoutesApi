package br.com.trix.controllers;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.trix.model.Route;
import br.com.trix.model.Waypoint;
import br.com.trix.repository.RouteRepository;
import br.com.trix.service.RouteService;

@RestController
@RequestMapping("/")
public class RouteController
{
	@Autowired
	private RouteRepository repo;
	private static final Logger LOGGER = Logger.getLogger(Route.class); 
	
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
	public void createRoute(@RequestBody List<Waypoint> waypoints) {
		Route routeApi = new Route();
		RouteService rService = new RouteService();
		
		try {
			routeApi = rService.getApiResult(waypoints);
			repo.insert(routeApi);
		} catch (Exception e) {
			LOGGER.error("Error creating Route: "+waypoints.get(0).getName()+" ", e);
		}
	}
	
}
