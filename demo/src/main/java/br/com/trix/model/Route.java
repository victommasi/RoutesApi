package br.com.trix.model;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Route {
	
	@Id
	private String id; 
	private String name;
	private Date date;
	private String vehicleId;
	private List<Waypoint> stops;
	private List<Waypoint> path;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
    
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
	}

	public List<Waypoint> getPath() {
		return path;
	}

	public void setPath(List<Waypoint> path) {
		this.path = path;
	}

	public List<Waypoint> getStops() {
		return path;
	}

	public void setStops(List<Waypoint> stops) {
		this.path = stops;
	}
	
	@Override
	public String toString(){
		return "";
	}
}
