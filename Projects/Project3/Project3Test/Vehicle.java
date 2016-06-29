/** Kyle Davis
  * 3/24/14 
  * Project 3 - Traffic Simulator 
  * CSC230 - Dr. DePasquale 
  */

/** The Vehicle class is designed to represent a car going through an intersection. It contains variables to hold the
  * Vehicle's arrival time, direction, lane, street, departure time and total time waiting
  *
  * @author Kyle Davis 
  */
public class Vehicle {
	/** street - enum Street type variable used to hold either Church or Main street */
	private Street street;
	/** cardinal - enum Direction type variable used to hold N, S, E, or W */
	private Direction cardinal;
	/** arrivalTime - int variable that stores when the Vehicle object is instantiated */
	private int arrivalTime;
	/** departureTime - int variable that stores when the Vehicle object crosses the intersection */
	private int departureTime;
	/** vehicleNo - int variable that stores the Vehicle object's number */
	private int vehicleNo;
	/** lane - String variable that stores what lane the Vehicle object is in - straight/right */
	private String lane;
	
	/** Constructor - sets the information passed in to the variables stored as instance data 
	  *
	  * @param - the Vehicle number, the Street, the cardinal direction, the arrival time and the lane 
	  */
	public Vehicle (int eVehicleNo, Street eStreet, Direction eCardinal, int aTime, String eLane) {
		vehicleNo = eVehicleNo;
		street = eStreet;
		cardinal = eCardinal;
		departureTime = 0;
		arrivalTime = aTime;
		lane = eLane;
	}
	
	/** The setArrivalTime method takes in an integer and sets it to the arrivalTime variable
	  *
	  * @param the arrival time value
	  */
	public void setArrivalTime(int eArrival) {
		arrivalTime = eArrival;
	}
	
	/** The setDepartureTime method takes in an integer and sets it to the departureTime variable
	  *
	  * @param the departure time value
	  */
	public void setDepartureTime(int eDeparture) {
		departureTime = eDeparture;
	}
	
	/** The getArrivalTime method returns the arrival time of the Vehicle object 
	  *
	  * @return the arrival time of the Vehicle
	  */
	public int getArrivalTime() {
		return arrivalTime;
	}
	
	/** The getVehicleNo method returns the number of the Vehicle object
	  *
	  * @return the number of the Vehicle
	  */
	public int getVehicleNo() {
		return vehicleNo;
	}
	
	/** The getStreet method returns the Street the Vehicle object is on
	  *
	  * @return the street
	  */
	public Street getStreet() {
		return street;
	}
	
	/** The getDirection method returns the Direction the Vehicle object is going
	  * 
	  * @return the direction
	  */
	public Direction getDirection() {
		return cardinal;
	}

	/** The getDepartureTime method returns the time that the Vehicle object crossed the intersection 
	  *
	  * @return the departureTime value 
	  */
	public int getDepartureTime() {
		return departureTime;
	}
	
	/** The totalTime method returns the total time the Vehicle has been waiting at the light 
	  *
	  * @return departureTime - arrivalTime 
	  */
	public int totalTime() {
		return departureTime - arrivalTime;
	}
	
	/** The getLane method returns a String based on the String value of lane that was passed in
	  *
	  * @return a String that represents what direction the Vehicle went 
	  */
	public String getLane() {
		/** resultLane - String variable that contains the direction & way the Vehicle went */
		String resultLane = "";
		if (lane.equals("northbound right")) {
			resultLane = " turned right and headed eastbound.";
		}
		else if (lane.equals("southbound right")) {
			resultLane = " turned right and headed westbound.";
		}
		else if (lane.equals("eastbound right")) {
			resultLane = " turned right and headed southbound.";
		}
		else if (lane.equals("westbound right")) {
			resultLane = " turned right and headed northbound.";
		}
		else if (lane.contains("straight")){
			resultLane = " continued straight.";
		}
		else {
			resultLane = " There was an issue.";
		}
		return resultLane;
	}
	
	/** The toString method returns a String value based on the Vehicle's direction 
	  *
	  * @return String that represents the Vehicle's direction
	  */
	public String toString() {
		String bound = String.valueOf(this.getVehicleNo());
		if (cardinal.name().equals("N")) {
			bound += " (southbound)";
		}
		else if (cardinal.name().equals("S")) {
			bound += " (northbound)";
		}
		else if (cardinal.name().equals("E")) {
			bound += " (westbound)";
		}
		else if (cardinal.name().equals("W")) {
			bound += " (eastbound)";
		}
		else {
			bound = "There has been an error";
		}
		return bound;
		
	}
}