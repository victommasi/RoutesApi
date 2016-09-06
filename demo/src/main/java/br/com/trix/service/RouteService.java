package br.com.trix.service;

import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.maps.model.DirectionsResult;

import br.com.trix.model.Route;
import br.com.trix.model.Waypoint;
import br.com.trix.repository.RouteRepository;

@Service
public class RouteService {
	
	@Autowired
	RouteRepository repo;
	
	public RouteService(){}
	
	public List<Route> findAll() {
		return repo.findAll();
	}
	
	public void createRoute(Route route, List<Waypoint> routePoints) throws Exception {
		GoogleService gService = new GoogleService();
		DirectionsResult dirResult = gService.getDirectionsResult(routePoints);
		
		route.setName(routePoints.get(0).getName());
		route.setDate(Calendar.getInstance().getTime());
		route.setStop(gService.getStopsList(dirResult));
		route.setPath(gService.getRoutePath(dirResult));
		repo.insert(route);
	}
}
