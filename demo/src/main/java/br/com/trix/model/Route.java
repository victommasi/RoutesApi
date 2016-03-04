package br.com.trix.model;
import java.util.List;
import java.util.ArrayList;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Route {
	
	@Id
	private String id; 
	private List<Waypoint> waypoints = new ArrayList<Waypoint>();

	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<Waypoint> getStops() {
		return waypoints;
	}

	public void setStops(List<Waypoint> stops) {
		this.waypoints = stops;
	}
	
	@Override
	public String toString(){
		return "";
	}
}
