import java.util.*;
import java.io.*;
import jsjf.LinkedQueue;
import jsjf.exceptions.*;

public class Simulator {

	private LinkedQueue<Vehicle>[] lanes = (LinkedQueue<Vehicle>[]) (new LinkedQueue[8]);
	private String[] laneBounds = new String[8]; 
	private int totalTime = 0;
	private int trafficCount = 0;
	private int carCount = 0;
	private final int TOTAL_CARS = 120;
	private PrintWriter outFile; 
	
	public void simulationStart() {
		try {
			outFile = new PrintWriter(new BufferedWriter(new FileWriter("output.txt")));
		}
		catch (IOException e) {
			System.err.println("There was a problem with writing to the file");
			System.exit(1);
		}
		for (int i = 0; i < lanes.length; i++) {
			lanes[i] = new LinkedQueue<Vehicle>();
		}
		while (trafficCount < TOTAL_CARS) {
			this.objectCreation();
			this.NSTraffic();
			this.objectCreation();
			this.EWTraffic();
		}
		outFile.println();
		outFile.println(carCount);
		outFile.println("-----Simulation terminating -----");
		outFile.close();
	}
	private void objectCreation() {
		int currentCarNo = 0;
		Random generator = new Random();
		if (totalTime == 0) {
				currentCarNo = generator.nextInt(6) + 7;
				outFile.println("-----The light is changing to North/South traffic-----");
		}
		else if (totalTime == 6) {
			currentCarNo = generator.nextInt(8) + 8;
			outFile.println("this is the second time it's run");
		}
		else if (totalTime > 9) {
			currentCarNo = generator.nextInt(13) + 3;
			outFile.println("the rest of the times!");
			if (carCount + currentCarNo > TOTAL_CARS){
				currentCarNo = TOTAL_CARS-carCount;
			}
			else {
				currentCarNo = currentCarNo;
			}
		}
		for (int i = 0; i < currentCarNo; i++) {
				int lane = generator.nextInt(2);
				int randDirection = generator.nextInt(4);
				if (randDirection == 0) {
					if (lane == 0) {
						carCount++;
						lanes[0].enqueue(new Vehicle(carCount, Street.Church, Direction.N, totalTime));
						laneBounds[0] = "southbound straight";
						
					}
					else if (lane == 1) {
						carCount++;
						lanes[1].enqueue(new Vehicle(carCount, Street.Church, Direction.N, totalTime));
						laneBounds[1] = "southbound right";
					}
				}
				else if (randDirection == 1) {
					if (lane == 0) {
						carCount++;
						lanes[2].enqueue(new Vehicle(carCount, Street.Church, Direction.S, totalTime));
						laneBounds[2] = "northbound straight";
					}
					else if (lane == 1) {
						carCount++;
						lanes[3].enqueue(new Vehicle(carCount, Street.Church, Direction.S, totalTime));
						laneBounds[3] = "northbound right";
					}
				}
				else if (randDirection == 2) {
					if (lane == 0) {
						carCount++;
						lanes[4].enqueue(new Vehicle(carCount, Street.Main, Direction.E, totalTime));
						laneBounds[4] = "westbound straight";
						
					}
					else if (lane == 1) {
						carCount++;
						lanes[5].enqueue(new Vehicle(carCount, Street.Main, Direction.E, totalTime));
						laneBounds[5] = "westbound right";
					}
				}
				else if (randDirection == 3) {
					if (lane == 0) {
						carCount++;
						lanes[6].enqueue(new Vehicle(carCount, Street.Main, Direction.W, totalTime));
						laneBounds[6] = "eastbound straight";
						
					}
					else if (lane == 1) {
						carCount++;
						lanes[7].enqueue(new Vehicle(carCount, Street.Main, Direction.W, totalTime));
						laneBounds[7] = "eastbound right";
					}

				}
			}
	}
	
	private void NSTraffic () {
		int currentTime = 0;
		int time = 0;
		while (currentTime < time+6) {
					currentTime = currentTime + 3;
					totalTime = totalTime + 3;
					try {
						lanes[0].first().setDepartureTime(totalTime);
						outFile.println("[Time: " + totalTime + "] Vehicle #" + lanes[0].first() + " continued straight." + " total wait time: " + lanes[0].first().totalTime());
						lanes[0].dequeue();
						trafficCount++;
					}
					catch (EmptyCollectionException e) {
						this.ExceptionHandler("southbound straight");
					}
					try {
						lanes[1].first().setDepartureTime(totalTime);
						outFile.println("[Time: " + totalTime + "] Vehicle #" + lanes[1].first() + " turned right and headed westbound." + " total wait time: " + lanes[1].first().totalTime());
						lanes[1].dequeue();
						trafficCount++;
					}
					catch (EmptyCollectionException e) {
						this.ExceptionHandler("southbound right");
					}
					try {
						lanes[2].first().setDepartureTime(totalTime);
						outFile.println("[Time: " + totalTime + "] Vehicle #" + lanes[2].first() + " continued straight." + " total wait time: " + lanes[2].first().totalTime());
						lanes[2].dequeue();
						trafficCount++;
						
					}
					catch (EmptyCollectionException e) {
						this.ExceptionHandler("northbound straight");
					}
					try {
						lanes[3].first().setDepartureTime(totalTime);
						outFile.println("[Time: " + totalTime + "] Vehicle #" + lanes[3].first() + " turned right and headed eastbound." +" total wait time: " + lanes[3].first().totalTime());
						lanes[3].dequeue();
						trafficCount++;
					}
					catch (EmptyCollectionException e) {
						this.ExceptionHandler("northbound right");
					}
					
			}	
			if (carCount < TOTAL_CARS) {
				outFile.println();
				outFile.println("-----The light is changing to East/West traffic-----");
			}
			else if (carCount == TOTAL_CARS && trafficCount < TOTAL_CARS){
				outFile.println();
				outFile.println("-----The light is changing to East/West traffic-----");
			}
	}
	private void EWTraffic () {
		int currentTime = 0;
		int time = 0;
		while (currentTime < time+9) {
			currentTime = currentTime+3;
			totalTime = totalTime + 3;
			try {
				lanes[4].first().setDepartureTime(totalTime);
				outFile.println("[Time: " + totalTime + "] Vehicle #" + lanes[4].first() + " continued straight." +" total wait time: " + lanes[4].first().totalTime());
				lanes[4].dequeue();
				trafficCount++;
			}
			catch (EmptyCollectionException e) {
				this.ExceptionHandler("westbound straight");
			}
			try {
				lanes[5].first().setDepartureTime(totalTime);
				outFile.println("[Time: " + totalTime + "] Vehicle #" + lanes[5].first() + " turned right and headed northbound." + " total wait time: " + lanes[5].first().totalTime());
				lanes[5].dequeue();
				trafficCount++;
			}
			catch (EmptyCollectionException e) {
				this.ExceptionHandler("westbound right");
			}
			try {
				lanes[6].first().setDepartureTime(totalTime);
				outFile.println("[Time: " + totalTime + "] Vehicle #" + lanes[6].first() + " continued straight." + " total wait time: " + lanes[6].first().totalTime());
				lanes[6].dequeue();
				trafficCount++;
			}
			catch (EmptyCollectionException e) {
				this.ExceptionHandler("eastbound straight");
			}
			try {
				lanes[7].first().setDepartureTime(totalTime);
				outFile.println("[Time: " + totalTime + "] Vehicle #" + lanes[7].first() + " turned right and headed southbound." + " total wait time: " + lanes[7].first().totalTime());
				lanes[7].dequeue();
				trafficCount++;
			}
			catch (EmptyCollectionException e) {
				this.ExceptionHandler("eastbound right");
			}
				
		}
		if (carCount < TOTAL_CARS) {
			outFile.println();
			outFile.println("-----The light is changing to North/South traffic-----");
		}
		else if (carCount == TOTAL_CARS && trafficCount < TOTAL_CARS){
			outFile.println();
			outFile.println("-----The light is changing to North/South traffic-----");
		}
			
	}
	private void ExceptionHandler(String laneDirection) {
		try {
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