/** Kyle Davis
  * 4/30/14
  * Project 4 - Hashtables 
  * CSC230 - Dr. DePasquale 
  */

package edu.tcnj.csc230;
import java.util.*;
/** The HTTPLog class is designed to store information passed to it. It provides a hashCode based on the resource it is
  * given overwriting Object's hashCode method. It also takes in other objects and compares them based on either their 
  * resources or their counts, depending on the currentCompType 
  */
public class HTTPLog implements Comparable {
	/** String dateTime - stores the date & time of the String passed in */
	private String dateTime;
	/** String resource - stores the resource passed in */
	private String resource;
	/** int response - stores the response code of the resource passed in */
	private int responseCode;
	/** int numBytes - stores the number of bytes expended when the resource was called */
	private int numBytes;
	/** int count - total number of times an object with the same resource name has been requested - starts at 1 */
	private int count = 1;
	/** int currentCompType - int for determining what the compareTo method compares on - static so other methods can
	  * change it without a mutator method 
	  */
	public static int currentCompType = 1;
	
	/** HTTPLog constructor - empty, because all information is passed through mutator methods */
	public HTTPLog() {
	
	}
	
	/** The setDateTime method takes in a String and sets it as this object's date & time 
	  * 
	  * @param eDateTime - String variable for date & time */
	public void setDateTime(String eDateTime) {
		dateTime = eDateTime;
	}
	
	/** The setResource method takes in a resource name and does a number of if statements to set it to resource 
	  *
	  * @param eResource - the initial resource passed in without any modifications */
	public void setResource(String eResource) {
		/** if, for instance eResource is "/index.html?g2_showComment" then resource is eResource up until the first "?" */
		if (eResource.contains("?")) {
			resource = eResource.substring(0, eResource.indexOf('?'));
		}
		/** if eResource does not contain a "?" then resource equals eResource */
		else {
			resource = eResource;
		}

		/** if resource is only of length 1, and that character is "/" then we convert resource to "/index.html" */
		if (resource.length() == 1) {
			if (resource.substring(0, 1).equals("/")) {
				resource = "/index.html";
			}
		}
	}
	
	/** The setResponseCode method takes in an int and sets it as responseCode 
	  *
	  * @param eResponseCode, the int passed in from the method that called it 
	  */
	public void setResponseCode(int eResponseCode) {
		responseCode = eResponseCode;
	}
	
	/** The setNumBytes method takes in an int and adds it to the current number of bytes for this object 
	  *
	  * @param eNumBytes - the int to be added to numBytes 
	  */
	public void setNumBytes(int eNumBytes) {
		numBytes += eNumBytes;
	}
	
	/** The getDateTime method returns the date & time of the object 
	  *
	  * @return String dateTime 
	  */
	public String getDateTime() {
		return dateTime;
	}
	
	/** The getResource method returns the resource name of the object 
	  *
	  * @return String resource 
	  */
	public String getResource() {
		return resource;
	}
	
	/** The getResponseCode method returns the response code of the object 
	  *
	  * @return int responseCode 
	  */
	public int getResponseCode() {
		return responseCode;
	}
	
	/** The increaseCount method increases the count of the number of times this resource has been requested */
	public void increaseCount() {
		count++;
	}
	
	/** The getCount method returns the number of times the resource has been requested 
	  * 
	  * @return int count 
	  */
	public int getCount() {
		return count;
	}
	
	/** The getNumBytes returns the total number of bytes across all the requests for the resource 
	  *
	  * @return int numBytes
	  */
	public int getNumBytes() {
		return numBytes;
	}
	
	/** The hashCode method overwrites Object's hashCode method with its own implementation that involves adding
	  * up all of the characters in resource, dividing it by 7 & adding 1855 and returning the result 
	  *
	  * @return int key - the result of the hashCode method 
	  */
	public int hashCode() {
		int key = 0;
		for (int i = 0; i < resource.length(); i++) {
			key += (int) resource.charAt(i);
		}
		key = (key/7) + 1855;
		return key;
	}
	
	/** The compareTo method takes in an object, casts it to HTTPLog and compares either its resource name or its count, 
	  * depending on what currentCompType is set to 
	  *
	  * @param Object obj 
	  * @return a 0, negative or positive number 
	  */
	public int compareTo(Object obj) {
		HTTPLog newResource = (HTTPLog) obj;
		switch (currentCompType) {
			case 1:
				return resource.compareTo(newResource.getResource());
			case 2: 
				if (count < newResource.getCount()) {
					return -1;
				}
				else if (count > newResource.getCount()) {
					return 1;
				}
				else {
					return 0;
				}
			default: 
				return 0;
		}
	}
	
	/** The toString method returns a String representation of the data in the HTTPLog object 
	  *
	  * @return dateTime, resource, responseCode, numBytes and count as String 
	  */
	public String toString () {
		String result = "";
		result += dateTime + " Website name: " + resource + " Response code: " + responseCode + " Total bytes: " + numBytes + " Count: " + count;
		return result;
	}
	
}