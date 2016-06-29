/** Kyle Davis
  * 3/24/14 
  * Project 3 - Traffic Simulator 
  * CSC230 - Dr. DePasquale 
  */
import java.util.*;
import java.io.*;
import jsjf.LinkedQueue;
import jsjf.exceptions.*;

/** The Simulator class is designed to instantiated 8 LinkedQueue "lanes" and populate them randomly with Vehicle objects.
  * It then moves the vehicles through the intersection depending on the lane they're in, and writes the current time, 
  * the vehicle's direction & lane, and the vehicle's total time waiting at the intersection to "output.txt". Any empty 
  * queues accessed will write an appropriate message to a file called "errors.txt" 
  *
  * @author Kyle Davis 
  */
public class Simulator {
	/** lanes - a LinkedQueue array of Vehicle objects - size is set to 8, the number of lanes in the simulation */
	private LinkedQueue<Vehicle>[] lanes = (LinkedQueue<Vehicle>[]) (new LinkedQueue[8]);
	/** laneBounds - a String array that will contain the direction and lane a Vehicle is in */
	private String[] laneBounds = new String[8]; 
	/** totalTime - int value that counts how long it takes for all 120 Vehicle objects to move through the intersection */
	private int totalTime = 0;
	/** trafficCount - int value that keeps track of how many Vehicles have moved through the intersection */
	private int trafficCount = 0;
	/** carCount - int value that keeps track of how many Vehicle objects have been instantiated */
	private int carCount = 0;
	/** TOTAL_CARS - a final int value that states how many cars are to be created/moved through the intersection */
	private final int TOTAL_CARS = 120;
	/** outFile - a PrintWriter object that writes a Vehicle's information after it moves through the intersection */
	private PrintWriter outFile; 
	
	/** The simulationStart method instantiates the lanes, calls the methods that create the Vehicle objects and moves them
	  * through the intersection and terminates the simulation after trafficCount is greater than 120 
	  */
	public void simulationStart() {
		/** The instantiation of outFile is enclosed in a try-catch. If it throws an IOException, the program is exited */
		try {
			outFile = new PrintWriter(new BufferedWriter(new FileWriter("output.txt")));
		}
		catch (IOException e) {
			System.err.println("There was a problem with writing to the file");
			System.exit(1);
		}
		/** Instantiates the LinkedQueue */
		for (int i = 0; i < lanes.length; i++) {
			lanes[i] = new LinkedQueue<Vehicle>();
		}
		outFile.println("----- Start of simulation - time set to 0 -----");
		outFile.println();
		/** The program runs so long as the number of cars passed through the intersection is less than 120 - once 120 is 
		  * hit, the simulation ends 
		  */
		while (trafficCount < TOTAL_CARS) {
			this.objectCreation();
			this.NSTraffic();
			this.objectCreation();
			this.EWTraffic();
		}
		outFile.println();
		outFile.println("----- Simulation terminating -----");
		outFile.close();
	}
	
	/** The objectCreation method creates several random numbers - one to determine how many Vehicle objects are created
	  * this round, and 2 to determine what lane the new Vehicle object goes into. Depending on the totalTime, a different 
	  * range is given to the random numbers
	  */
	private void objectCreation() {
		/** Instantiation of the String array laneBounds */
		laneBounds[0] = "southbound straight";
		laneBounds[1] = "southbound right";
		laneBounds[2] = "northbound straight";
		laneBounds[3] = "northbound right";
		laneBounds[4] = "westbound straight";
		laneBounds[5] = "westbound right";
		laneBounds[6] = "eastbound straight";
		laneBounds[7] = "eastbound right";
		
		/** currentCarNo - int value used to store the randomly generated number */
		int currentCarNo = 0;
		/** generator - Random object used to determine the value of currentCarNo */
		Random generator = new Random();
		/** if this is the first time the program is creating objects, then generator's range is 7-12 */
		if (totalTime == 0) {
				currentCarNo = generator.nextInt(6) + 7;
				outFile.println("-----The light is changing to North/South traffic-----");
		}
		/** if this is the second time the program is creating objects, then generator's range is 8-15 */
		else if (totalTime == 6) {
			currentCarNo = generator.nextInt(8) + 8;
		}
		/** otherwise, generator's range is 3-15 */
		else if (totalTime > 9) {
			currentCarNo = generator.nextInt(13) + 3;
			/** if carCount + currentCarNo will create more than 120 Vehicles, then we must change currentCarNo so that
			  * it creates enough Vehicles to exactly hit 120 */
			if (carCount + currentCarNo > TOTAL_CARS){
				currentCarNo = TOTAL_CARS-carCount;
			}
			else {
				currentCarNo = currentCarNo;
			}
		}
		/** creates a random number of Vehicles and places them in a lane based on a series of if-statements */
		for (int i = 0; i < currentCarNo; i++) {
				/** lane - int variable for determining if they are going straight or turning right */
				int lane = generator.nextInt(2);
				/** randDirection - int variable for determining what direction they are going*/
				int randDirection = generator.nextInt(4);
				/** "North" lanes */
				if (randDirection == 0) {
					/** The Vehicle is added to the appropriate lane - straight/right - and the carCount is increased */
					if (lane == 0) {
						carCount++;
						lanes[0].enqueue(new Vehicle(carCount, Street.Church, Direction.N, totalTime, laneBounds[0]));
						
					}
					else if (lane == 1) {
						carCount++;
						lanes[1].enqueue(new Vehicle(carCount, Street.Church, Direction.N, totalTime, laneBounds[1]));
					}
				}
				/** "South" lanes */
				else if (randDirection == 1) {
					/** The Vehicle is added to the appropriate lane - straight/right - and the carCount is increased */
					if (lane == 0) {
						carCount++;
						lanes[2].enqueue(new Vehicle(carCount, Street.Church, Direction.S, totalTime, laneBounds[2]));
					}
					else if (lane == 1) {
						carCount++;
						lanes[3].enqueue(new Vehicle(carCount, Street.Church, Direction.S, totalTime, laneBounds[3]));
					}
				}
				/** "East" lanes */
				else if (randDirection == 2) {
					/** The Vehicle is added to the appropriate lane - straight/right - and the carCount is increased */
					if (lane == 0) {
						carCount++;
						lanes[4].enqueue(new Vehicle(carCount, Street.Main, Direction.E, totalTime, laneBounds[4]));
						
					}
					else if (lane == 1) {
						carCount++;
						lanes[5].enqueue(new Vehicle(carCount, Street.Main, Direction.E, totalTime, laneBounds[5]));
					}
				}
				/** "West" lanes */
				else if (randDirection == 3) {
					/** The Vehicle is added to the appropriate lane - straight/right - and the carCount is increased */
					if (lane == 0) {
						carCount++;
						lanes[6].enqueue(new Vehicle(carCount, Street.Main, Direction.W, totalTime, laneBounds[6]));
						
					}
					else if (lane == 1) {
						carCount++;
						lanes[7].enqueue(new Vehicle(carCount, Street.Main, Direction.W, totalTime, laneBounds[7]));
					}

				}
			}
	}
	
	/** The NSTraffic method moves North/South bound Vehicles through the intersection. Each Vehicle takes 3 seconds to move through the 
	  * intersection. Their light is 6 seconds long, meaning a total of 8 vehicles could theoretically go per light. 
	  */
	private void NSTraffic () {
		if (trafficCount < 120) {
			/** currentTime - int value that increases as the light goes on */
			int currentTime = 0;
			/** time - int value that remains the same as the light goes on - used as a comparision for currentTime */
			int time = 0;
			/** while the light has not gone on for more than 6 seconds */
			while (currentTime < time+6) {
				/** currentTime & totalTime adjust for the time it takes for the Vehicles to cross the road */
				currentTime = currentTime + 3;
				totalTime = totalTime + 3;
				/** Sets the departure times for the Vehicles in Lanes[0]-[3] and moves them through the light. It prints each Vehicle's
				  * information to the outFile and removes them from the queue. If the lane is empty, then then EmptyCollectionException
				  * is caught and the information about which lane is empty is handed to ExceptionHandler 
				  */
				for (int i = 0; i < 4; i++) {
					try {
						lanes[i].first().setDepartureTime(totalTime);
						outFile.println("[Time: " + totalTime + "] Vehicle #" + lanes[i].first() + lanes[i].first().getLane() + " total wait time: " + lanes[i].first().totalTime());
						lanes[i].dequeue();
						trafficCount++;
					}
					catch (EmptyCollectionException e) {
						this.ExceptionHandler(laneBounds[i]);
					}
				}
						
			}	
			/** if there are still cars left to move through the intersection, then it prints a message for changing the light. 
			  * Otherwise, nothing is said and the program moves back up to the top of the while where it is terminated. 
			  */
			if (carCount < TOTAL_CARS) {
				outFile.println();
				outFile.println("-----The light is changing to East/West traffic-----");
			}
			else if (carCount == TOTAL_CARS && trafficCount < TOTAL_CARS){
				outFile.println();
				outFile.println("-----The light is changing to East/West traffic-----");
			}
		}
	}
	
	/** The EWTraffic method moves East/West bound Vehicles through the intersection. Each Vehicle takes 3 seconds to move through the 
	  * intersection. Their light is 9 seconds long, meaning a total of 12 vehicles could theoretically go per light. 
	  */
	private void EWTraffic () {
		if (trafficCount < TOTAL_CARS) {
			/** currentTime - int value that increases as the light goes on */
			int currentTime = 0;
			/** time - int value that remains the same as the light goes on - used as a comparision for currentTime */
			int time = 0;
			/** While the light has not gone on for more than 9 seconds */
			while (currentTime < time+9) {
				/** currentTime & totalTime adjust for the time it takes for the Vehicles to cross the road */
				currentTime = currentTime+3;
				totalTime = totalTime + 3;
				/** Sets the departure times for the Vehicles in Lanes[4]-[7] and moves them through the light. It prints each Vehicle's
				  * information to the outFile and removes them from the queue. If the lane is empty, then then EmptyCollectionException
				  * is caught and the information about which lane is empty is handed to ExceptionHandler 
				  */
				for (int i = 4; i < lanes.length; i++) {
					try {
						lanes[i].first().setDepartureTime(totalTime);
						outFile.println("[Time: " + totalTime + "] Vehicle #" + lanes[i].first() + lanes[i].first().getLane() + " total wait time: " + lanes[i].first().totalTime());
						lanes[i].dequeue();
						trafficCount++;
					}
					catch (EmptyCollectionException e) {
						this.ExceptionHandler(laneBounds[i]);
					}
				}
					
			}
			/** if there are still cars left to move through the intersection, then it prints a message for changing the light. 
			  * Otherwise, nothing is said and the program moves back up to the top of the while where it is terminated. 
			  */
			if (carCount < TOTAL_CARS) {
				outFile.println();
				outFile.println("-----The light is changing to North/South traffic-----");
			}
			else if (carCount == TOTAL_CARS && trafficCount < TOTAL_CARS){
				outFile.println();
				outFile.println("-----The light is changing to North/South traffic-----");
			}
		}
			
	}
	/** The ExceptionHandler method takes is called after an exception is thrown. It takes in a String and prints information
	  * about where the exception was thrown to a file called "errors.txt".
	  * 
	  * @param String that contains information about what lane the exception was thrown in 
	  */
	private void ExceptionHandler(String laneDirection) {
		/** Everything is contained in a try-catch in case the creating the PrintWriter object for documenting exceptions 
		  * itself throws an exception. If an IOException is thrown, the program is exited. 
		  */
		try {
			/** errorFile - PrintWriter object for writing information to "errors.txt." Allows information to be appended 
			  * to the file, so it can be opened and closed multiple times */
			PrintWriter errorFile = new PrintWriter(new BufferedWriter(new FileWriter("errors.txt", true)));
			errorFile.println("There was an empty queue accessed at time " + totalTime + " in the " + laneDirection + " lane.");
			errorFile.close();
		}
		catch (IOException e) {
			System.err.println("There was a problem writing to the error file.");
			System.exit(1);
		}
	}
	
	
}