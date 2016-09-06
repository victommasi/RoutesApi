package br.com.trix.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.maps.DirectionsApi;
import com.google.maps.GeoApiContext;
import com.google.maps.internal.PolylineEncoding;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.LatLng;

import br.com.trix.model.Route;
import br.com.trix.model.Waypoint;

@Service
public class GoogleService {
	
	@Value("${apikey}")
	private String apiKey;
	
	private static final Logger LOGGER = Logger.getLogger(Route.class);

	public GoogleService(){}
	
	public DirectionsResult getDirectionsResult(List<Waypoint> routePoints) throws Exception {
		GeoApiContext context = new GeoApiContext().setApiKey("AIzaSyCGUhLM8pidet05dKWxJ5U9oV0v_mPq9gA");
		DirectionsResult result = null;
		String startPoint;
		String[] wayPoints;
		
		startPoint = routePoints.get(0).toString();
		int waypointSize = routePoints.size()-1;
		wayPoints = new String[waypointSize];
		for (int i = 0; i < waypointSize; i++) {
			wayPoints[i] = routePoints.get(i+1).toString();
		}
		
		try {
			result = DirectionsApi.newRequest(context)
										 .origin(startPoint)
										 .destination(startPoint)
										 .optimizeWaypoints(true)
										 .waypoints(wayPoints)
										 .await();
			LOGGER.info("Route result ok!");
			return result;
		} catch (Exception e) {
			LOGGER.error("Error requesting DirectionsApi", e);
			throw e;
		} 
	}
	
	public List<String> getStopsList(DirectionsResult dirResult) {
		List<String> stops = new ArrayList<String>();
		for (int i = 0; i < dirResult.routes[0].legs.length; i++) {
			stops.add(dirResult.routes[0].legs[i].startAddress);
		}
		return stops;
	}

	public List<LatLng> getRoutePath(DirectionsResult dirResult){
		List<LatLng> polyLines = new ArrayList<>();
		String EncodedpolyLines = dirResult.routes[0].overviewPolyline.getEncodedPath().toString();	
		polyLines = PolylineEncoding.decode(EncodedpolyLines);
		return polyLines;
	}

}
