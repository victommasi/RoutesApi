package br.com.trix.service;



import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import com.google.maps.DirectionsApi;
import com.google.maps.model.DirectionsResult;
import com.google.maps.GeoApiContext;
import com.google.maps.internal.PolylineEncoding;
import com.google.maps.model.LatLng;

import br.com.trix.model.Waypoint;

public class RouteService {
	
	public void getApiResult(List<Waypoint> routePoints) throws Exception{
		
		DirectionsResult dirResult = getDirectionsResult(routePoints);
		List<String> listStops = getListStops(dirResult);
		List<LatLng> routePath = getRoutePath(dirResult);
		routePath.toString();
		listStops.toString();
		
	}

	@Test
	public DirectionsResult  getDirectionsResult(List<Waypoint> routePoints) {
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
		assertNotNull(result.routes);	
		return result;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	private List<String> getListStops(DirectionsResult dirResult) {
		List<String> stops = new ArrayList<>();
		for (int i = 0; i < dirResult.routes[0].legs.length; i++) {
			stops.add(dirResult.routes[0].legs[i].startAddress);
		}
		System.out.println(stops.toString());
		return stops;
	}

	public List<LatLng> getRoutePath(DirectionsResult dirResult){
		String polyLinesEncoded = dirResult.routes[0].overviewPolyline.toString();	
		List<LatLng> polyLines = PolylineEncoding.decode(polyLinesEncoded);
		return polyLines;
	}
}
