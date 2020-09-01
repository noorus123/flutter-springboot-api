package com.app.family.models;

import com.app.family.enums.LocationType;

public class Location {
	
	private LocationType locationType;
	private Address address;
	
	public Location() {
		super();
	}

	public LocationType getLocationType() {
		return locationType;
	}

	public void setLocationType(LocationType locationType) {
		this.locationType = locationType;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
	
}
