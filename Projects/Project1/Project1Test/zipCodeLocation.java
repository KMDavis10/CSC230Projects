/** Kyle Davis
  * 2/17/14 
  * Project 1 - Zip Code Analysis 
  * CSC230 - Dr. DePasquale 
  */
import java.util.*;
import java.io.*;
import java.net.*;

/** The zipCodeLocation class implements Comparable. It serves as a way to store information taken in from readWebsite and has methods to
  * get this information.
  *
  * @author Kyle Davis
  */
public class zipCodeLocation implements Comparable {
	/** String zipCode - the zipcode */
	private String zipCode;
	/** String location - the town */
	private String location;
	/** String state - the state */
	private String state;
	/** String county - the county */
	private String county;
	/** String type - the postal type */
	private String type;
	/** double lat - the latitude */
	private double lat;
	/** double long- the longitude */
	private double lon;
	/** private static int currentCompType - determines what case in the compareTo is used */
	private static int currentCompType = 1;
	
	/** Empty constructor - all information is passed through sets */
	public zipCodeLocation () {
		
	}
	/** setCurrentCompType sets the integer that the compareTo method compares by 
	  * 
	  * @param temp - the integer being passed in set to currentCompType 
	  */
	public static void setCurrentCompType (int temp) {
		currentCompType = temp;
	}
	/** setZipCode sets the String zipCode 
	  *
	  * @param eZipCode - zipcode passed in that String zipCode is set to 
	  */
	public void setZipCode (String eZipCode) {
		zipCode = eZipCode;
	}
	
	/** setLatitude sets the double lat as the parameter passed in
	  *
	  * @param eLat - latitude passed in that double lat is set to 
	  */
	public void setLatitude(double eLat) {
		lat = eLat;
	}
	
	/** setLongitude sets the double lon as the parameter passed in
	  *
	  * @param eLon - longitude passed in that double lon is set to */
	public void setLongitude (double eLon) {
		lon = eLon;
	}
	
	/** setLocation sets the String location as the parameter passed in
	  *
	  * @param eLocation - location passed in that String location is set to */
	public void setLocation(String eLocation) {
		location = eLocation;
	}
	
	/** setState sets the String state as the parameter passed in
	  * 
	  * @param eState - state passed in that String state is set to 
	  */
	public void setState (String eState) {
		state = eState;
	}
	
	/** setCounty sets the String county as the parameter passed in
	  *
	  * @param eCounty - county passed in that String county is set to */
	public void setCounty(String eCounty) {
		county = eCounty;
	}
	
	/** setType sets the String type as the parameter passed in 
	  *
	  * @param eType - type passed in that String type is set to 
	  */
	public void setType (String eType) {
		type = eType;
	}
	
	/** getType returns the String zipCode 
	  *
	  * @return the zipcode as a String value */
	public String getZipCode() {
		return zipCode;
	}
	
	/** getLatitude returns the double lat 
	  * 
	  * @return the latitude as a double value 
	  */
	public double getLatitude() {
		return lat;
	}
	
	/** getLongitude returns the double lon
	 *
	 * @return the longitude as a double value
	 */
	public double getLongitude() {
		return lon;
	}
	
	/** getState returns the String state
	  * 
	  * @return the state as a double value 
	  */
	public String getState() {
		return state;
	}
	
	/** getCounty returns the String county
	  * 
	  * @return the county as a String value 
	  */
	public String getCounty() {
		return county;
	}
	
	/** getLocation returns the String location
	  *
	  * @return the location as a String value 
	  */
	public String getLocation() {
		return location;
	}
	
	/** getType returns the String type
	  *
	  * @return the postal type as a String value 
	  */
	public String getType() {
		return type;
	}
	
	/** compareTo provides a way to compare multiple variables in one method based on a static variable currentCompType
	  * using a switch statement. This provides readWebsite with the ability to compare and sort in many ways. 
	  *
	  * @param - Object obj2 is passed in as a parameter and cast to type zipCodeLocation in order to use its methods 
	  * @return - returns the integer returned when the two Strings are compared to each other. This utilizes the 
	  * String class's compareTo method. 
	  */
	public int compareTo(Object obj2) {
	/** zipCodeLocation newObj is set to equal obj2 casted as type zipCodeLocation */
	zipCodeLocation newObj = (zipCodeLocation) obj2;
		switch (currentCompType) {
			case 1: 
				return this.zipCode.compareTo(newObj.getZipCode());
				
			case 2: 
				return this.state.compareTo(newObj.getState());
				
			case 3:
				return this.county.compareTo(newObj.getCounty());
				
			case 4:
				return this.location.compareTo(newObj.getLocation());
			
			case 5:
				return this.type.compareTo(newObj.getType());
				
			default:
				return 0;
			}
	
	}
	
	/** The toString method returns the below variables one String when a zipCodeLocation object is printed 
	  * 
	  * @return a String version of zipcode, latitude, longitude, state, county and type 
	  */
	public String toString () {
		return (zipCode + " " + lat + " " + lon + " " + state + " " + county + " " + type);
	}
}
