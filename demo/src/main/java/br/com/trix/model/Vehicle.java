package br.com.trix.model;

import org.springframework.data.annotation.Id;

public class Vehicle {
	
	@Id
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
