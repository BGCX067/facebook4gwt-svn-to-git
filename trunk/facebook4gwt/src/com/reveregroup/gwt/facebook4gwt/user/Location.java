package com.reveregroup.gwt.facebook4gwt.user;

import com.reveregroup.gwt.facebook4gwt.JsObject;

public class Location {
	String city;
	String state;
	String country;
	String zip;
	
	public Location(JsObject data) {
		city = data.getString("city");
		state = data.getString("state");
		country = data.getString("county");
		zip = data.getString("zip");
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}

	public String getCountry() {
		return country;
	}

	public String getZip() {
		return zip;
	}
	
	public String toString() {
		return (city == null ? "--" : city) + ", " + (state == null ? "--" : state) + (zip == null ? "" : " " + zip) + (country == null ? "" : " " + country);
	}

}
