package br.com.trix.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.trix.model.Route;
import br.com.trix.model.Waypoint;
import br.com.trix.service.RouteService;

//@RepositoryRestController
@RestController
@RequestMapping("/route")
public class RouteController
{
	@Autowired
	RouteService routeService;
	
	private static final Logger LOGGER = Logger.getLogger(Route.class); 
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "index";
    }

	@RequestMapping(value = "/list)", method = RequestMethod.GET)
	@ResponseBody
	public List <Route> getAllRoutes() {
		return routeService.findAll();
	}
   
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public void create(@RequestBody List<Waypoint> waypoints) {
		Route route = new Route();
		try {
			routeService.createRoute(route, waypoints);
		} catch (Exception e) {
			LOGGER.error("Error creating Route: " + e);
		}
	}
	
}
