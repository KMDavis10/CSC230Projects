import java.util.*;
import java.io.*;
import java.net.*;

public class zipCodeLocation implements Comparable {
	private String line, zipCode, location, state, county, type;
	private double lat, lon;
	private static int currentCompType = 4;
	
	
	public zipCodeLocation () {
		
	}
	public void setCurrentCompType (int temp) {
		currentCompType = temp;
	}
	public void setZipCode (String eZipCode) {
		zipCode = eZipCode;
	}
	public void setLatitude(double eLat) {
		lat = eLat;
	}
	public void setLongitude (double eLon) {
		lon = eLon;
	}
	public void setLocation(String eLocation) {
		location = eLocation;
	}
	public void setState (String eState) {
		state = eState;
	}
	public void setCounty(String eCounty) {
		county = eCounty;
	}
	public void setType (String eType) {
		type = eType;
	}
	public String getZipCode() {
		return zipCode;
	}
	public double getLatitude() {
		return lat;
	}
	public double getLongitude() {
		return lon;
	}
	public String getState() {
		return state;
	}
	public String getCounty() {
		return county;
	}
	public String getLocation() {
		return location;
	}
	public String getType() {
		return type;
	}
	public int compareTo(Object obj2) {
	zipCodeLocation newObj = (zipCodeLocation) obj2;
		switch (currentCompType) {
			case 1: 
				return this.zipCode.compareTo(newObj.getZipCode());
				//break;
			case 2: 
				return this.state.compareTo(newObj.getState());
				//break;
			case 3:
				return this.county.compareTo(newObj.getCounty());
				//break;
			case 4:
				return this.location.compareTo(newObj.getLocation());
				//break;
			case 5:
				return this.type.compareTo(newObj.getType());
				//break;
			default:
				return 0;
				//break;
		}
			
		
		//zipCodeLocation newObj = (zipCodeLocation) obj2;
		//return this.getZipCode().compareTo(newObj.getZipCode());
	}
	public String toString () {
		return (zipCode + " " + lat + " " + lon + " " + state + " " + county + " " + type);
	}
}
