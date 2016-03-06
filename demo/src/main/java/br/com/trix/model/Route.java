package br.com.trix.model;
import java.util.List;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Route {
	
	@Id
	private String id; 
	private String routeName;
	private Date routeDate;
	private String vehicleId;
	private List<String> stop;
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
    
	public Date getDate() {
		return routeDate;
	}

	public void setDate(Date date) {
		this.routeDate = date;
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

	public List<String> getStop() {
		return stop;
	}

	public void setStop(List<String> stop) {
		this.stop = stop;
	}
	
	@Override
	public String toString(){
		return "";
	}
}
