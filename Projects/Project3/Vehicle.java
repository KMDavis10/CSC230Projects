public class Vehicle {
	private Street squaleville;
	private Direction cardinal;
	private int arrivalTime;
	private int departureTime;
	private int vehicleNo;
	
	public Vehicle (int eVehicleNo, Street eStreet, Direction eCardinal, int aTime) {
		vehicleNo = eVehicleNo;
		squaleville = eStreet;
		cardinal = eCardinal;
		departureTime = 0;
		arrivalTime = aTime;
	}
	
	public int getArrivalTime() {
		return arrivalTime;
	}
	
	public int getVehicleNo() {
		return vehicleNo;
	}
	
	public Street getStreet() {
		return squaleville;
	}
	
	public Direction getDirection() {
		return cardinal;
	}
	
	public void setArrivalTime(int eArrival) {
		arrivalTime = eArrival;
	}
	
	public void setDepartureTime(int eDeparture) {
		departureTime = eDeparture;
	}
	
	public int getDepartureTime() {
		return departureTime;
	}
	
	public int totalTime() {
		return departureTime - arrivalTime;
	}
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
			bound = "BLARGH";
		}
		return bound;
		
	}
}