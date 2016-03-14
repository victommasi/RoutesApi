package br.com.trix.model;
import java.util.List;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import com.google.maps.model.LatLng;

@Document (collection="Routes")
public class Route {
	
	@Id
	private String id; 
	private String name;
	private Date date;
	private String vehicleId;
	private List<String> stop;
	private String path;
	
	public Route(){}
	
	public Route(String name, Date date, String vehicleId, List<String> stop, String path){
		this.name = name;
		this.date = date;
		this.vehicleId = vehicleId;
		this.stop = stop;
		this.path = path;
	}
	
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

	public String getPath() {
		return path;
	}

	public void setPath(String routePath) {
		this.path = routePath;
	}

	public List<String> getStop() {
		return stop;
	}

	public void setStop(List<String> stop) {
		this.stop = stop;
	}
}
