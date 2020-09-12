package com.app.family.models;

public class Location {
	
	private String locationType;
	private Address address;
	
	public Location() {
		super();
	}	

	public String getLocationType() {
		return locationType;
	}

	public void setLocationType(String locationType) {
		this.locationType = locationType;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
	
}
