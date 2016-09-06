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
	private List<String> stop;
	private List<LatLng> path;
	
	public Route(){}
	
	public Route(String name, Date date, List<String> stop, List<LatLng> path){
		this.name = name;
		this.date = date;
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

	public List<LatLng> getPath() {
		return path;
	}

	public void setPath(List<LatLng> routePath) {
		this.path = routePath;
	}

	public List<String> getStop() {
		return stop;
	}

	public void setStop(List<String> stop) {
		this.stop = stop;
	}
}
