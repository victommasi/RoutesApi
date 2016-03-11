package br.com.trix.model;

import org.springframework.data.annotation.Id;

public class Waypoint {
	
	private String name;
	private double lat;
	private double lng;
	
	public Waypoint(){}
	
	public Waypoint(String name, double lat, double lng){
		this.name = name;
		this.lat = lat;
		this.lng = lng;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}
	
	public String fillRouteJson(){
		return this.getLat() +","+ this.getLng();
	}
	
	@Override
	public String toString(){
		return this.getLat() +","+ this.getLng();
	}
	
}
