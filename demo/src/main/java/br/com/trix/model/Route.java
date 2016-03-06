package br.com.trix.model;
import java.util.List;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Route {
	
	@Id
	private String id; 
	private String routeName;
	private LocalDateTime routeDate;
	private String vehicleId;
	private List<String> stops;
	private List<String> path;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return routeName;
	}

	public void setName(String name) {
		this.routeName = name;
	}
    
	public LocalDateTime getDate() {
		return routeDate;
	}

	public void setDate(LocalDateTime localDateTime) {
		this.routeDate = localDateTime;
	}

	public String getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
	}

	public List<String> getPath() {
		return path;
	}

	public void setPath(List<String> path) {
		this.path = path;
	}

	public List<String> getStops() {
		return path;
	}

	public void setStops(List<String> stops) {
		this.path = stops;
	}
	
	@Override
	public String toString(){
		return "";
	}
}
