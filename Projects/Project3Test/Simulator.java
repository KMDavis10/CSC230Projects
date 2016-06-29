import java.util.*;
import java.io.*;
import jsjf.LinkedQueue;
import jsjf.exceptions.*;

public class Simulator {

	private LinkedQueue<Vehicle> lane1 = new LinkedQueue<Vehicle>();
	private LinkedQueue<Vehicle> lane2 = new LinkedQueue<Vehicle>();
	private LinkedQueue<Vehicle> lane3 = new LinkedQueue<Vehicle>();
	private LinkedQueue<Vehicle> lane4 = new LinkedQueue<Vehicle>();
	private LinkedQueue<Vehicle> lane5 = new LinkedQueue<Vehicle>();
	private LinkedQueue<Vehicle> lane6 = new LinkedQueue<Vehicle>();
	private LinkedQueue<Vehicle> lane7 = new LinkedQueue<Vehicle>();
	private LinkedQueue<Vehicle> lane8 = new LinkedQueue<Vehicle>();
	private int carCount = 0;
	private int totalTime = 0;
	private int trafficCount = 0;
	private int currentCarNo = 0;
	private final int TOTAL_CARS = 120;
	private String file = "output.txt";
	private PrintWriter outFile; 
	private Random generator = new Random();
	
	public void simulationStart() throws IOException {
		outFile = new PrintWriter(new BufferedWriter(new FileWriter(file)));
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
						lane1.enqueue(new Vehicle(carCount, Street.Church, Direction.N, totalTime));
						
					}
					else if (lane == 1) {
						carCount++;
						lane2.enqueue(new Vehicle(carCount, Street.Church, Direction.N, totalTime));
					}
				}
				else if (randDirection == 1) {
					if (lane == 0) {
						carCount++;
						lane3.enqueue(new Vehicle(carCount, Street.Main, Direction.E, totalTime));
					}
					else if (lane == 1) {
						carCount++;
						lane4.enqueue(new Vehicle(carCount, Street.Main, Direction.E, totalTime));
					}
				}
				else if (randDirection == 2) {
					if (lane == 0) {
						carCount++;
						lane5.enqueue(new Vehicle(carCount, Street.Church, Direction.S, totalTime));
						
					}
					else if (lane == 1) {
						carCount++;
						lane6.enqueue(new Vehicle(carCount, Street.Church, Direction.S, totalTime));
					}
				}
				else if (randDirection == 3) {
					if (lane == 0) {
						carCount++;
						lane7.enqueue(new Vehicle(carCount, Street.Main, Direction.W, totalTime));
						
					}
					else if (lane == 1) {
						carCount++;
						lane8.enqueue(new Vehicle(carCount, Street.Main, Direction.W, totalTime));
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
						lane1.first().setDepartureTime(totalTime);
						outFile.println("[Time: " + totalTime + "] Vehicle #" + lane1.first() + " continued straight." + " total wait time: " + lane1.first().totalTime());
						lane1.dequeue();
						trafficCount++;
					}
					catch (EmptyCollectionException e) {
					}
					try {
						lane2.first().setDepartureTime(totalTime);
						outFile.println("[Time: " + totalTime + "] Vehicle #" + lane2.first() + " turned right and headed westbound." + " total wait time: " + lane2.first().totalTime());
						lane2.dequeue();
						trafficCount++;
					}
					catch (EmptyCollectionException e) {
					}
					try {
						lane5.first().setDepartureTime(totalTime);
						outFile.println("[Time: " + totalTime + "] Vehicle #" + lane5.first() + " continued straight." + " total wait time: " + lane5.first().totalTime());
						lane5.dequeue();
						trafficCount++;
						
					}
					catch (EmptyCollectionException e) {
					}
					try {
						lane6.first().setDepartureTime(totalTime);
						outFile.println("[Time: " + totalTime + "] Vehicle #" + lane6.first() + " turned right and headed eastbound." +" total wait time: " + lane6.first().totalTime());
						lane6.dequeue();
						trafficCount++;
					}
					catch (EmptyCollectionException e) {
					}
					
			}	
			if (carCount < 120) {
				outFile.println();
				outFile.println("-----The light is changing to East/West traffic-----");
			}
			else if (carCount == 120 && trafficCount < 120){
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
				lane3.first().setDepartureTime(totalTime);
				outFile.println("[Time: " + totalTime + "] Vehicle #" + lane3.first() + " continued straight." +" total wait time: " + lane3.first().totalTime());
				lane3.dequeue();
				trafficCount++;
			}
			catch (EmptyCollectionException e) {
			}
			try {
				lane4.first().setDepartureTime(totalTime);
				outFile.println("[Time: " + totalTime + "] Vehicle #" + lane4.first() + " turned right and headed northbound." + " total wait time: " + lane4.first().totalTime());
				lane4.dequeue();
				trafficCount++;
			}
			catch (EmptyCollectionException e) {
			}
			try {
				lane7.first().setDepartureTime(totalTime);
				outFile.println("[Time: " + totalTime + "] Vehicle #" + lane7.first() + " continued straight." + " total wait time: " + lane7.first().totalTime());
				lane7.dequeue();
				trafficCount++;
			}
			catch (EmptyCollectionException e) {
			}
			try {
				lane8.first().setDepartureTime(totalTime);
				outFile.println("[Time: " + totalTime + "] Vehicle #" + lane8.first() + " turned right and headed southbound." + " total wait time: " + lane8.first().totalTime());
				lane8.dequeue();
				trafficCount++;
			}
			catch (EmptyCollectionException e) {
			}
				
		}
		if (carCount < 120) {
			outFile.println();
			outFile.println("-----The light is changing to North/South traffic-----");
		}
		else if (carCount == 120 && trafficCount < 120){
			outFile.println();
			outFile.println("-----The light is changing to North/South traffic-----");
		}
		else {
		
		}
			
	}
	
	
}