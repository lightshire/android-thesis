package com.classes;

public class Location {
	
	private int id;
	private String locationName;
	
	public Location() {
		id = 0;
		locationName = "n/a";
	}
	
	public Location(int id, String locationName) {
		this.id = id;
		this.locationName = locationName;
	}
	
	/*
	 * Mutator Classes
	 * ----------------/
	 */
	
	public int getId() {
		return id;
	}
	
	public String getLocationName() {
		return locationName;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setLocationName(String location) {
		this.locationName = location;
	}
	
	public boolean equalsTo(Object object) {
		Location _loc = (Location) object;
		return _loc.id == this.id;
	}
	
	public boolean equalsTo(Location location) {
		return location.id == this.id;
	}
	
}
