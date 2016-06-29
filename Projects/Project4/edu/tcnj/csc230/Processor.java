/** Kyle Davis
  * 4/30/14
  * Project 4 - Hashtables 
  * CSC230 - Dr. DePasquale 
  */
package edu.tcnj.csc230;
import java.io.*;
import java.util.*;
import jsjf.*;

/** The Processor class creates a LinkedChainedHashtable object and fills it with HTTPLog objects extracted from the data
  * in the file. After the hashtable is populated, the user can choose what they wish to find out about the hashtable.
  *
  * @author Kyle Davis
  */
public class Processor {
	/** LinkedChainedHashtable hashtable - the hashtable that we will place our data into */
	private LinkedChainedHashtable<HTTPLog> hashtable = new LinkedChainedHashtable<HTTPLog>(75);
	/** Scanner scan - the Scanner object that we need to read the data file */
	private Scanner scan;
	
	/** The process method reads data from a file, breaks up the lines into sections of data and stores the data in
	  * HTTPLog objects. The HTTPLog objects are then either added to the hashtable, or are used to update the data 
	  * in the table, depending on whether or not an object with the same resource name is contained.
	  * It then loops and lets the user pick from DETAILS, TOPTEN or CHAIN to get information from the hashtable.
	  */
	public void process() {
		/** The instantiation of outFile is enclosed in a try-catch. If it throws an IOException, the exceptionHandler
		  * method is called to deal with it. 
		  */
		try {
			scan = new Scanner (new File("access_log.inp"));
		}
		catch (IOException e) {
			this.exceptionHandler();
		}
		while (scan.hasNext()) {
		    /* String variable that contains a line of data from the file */
			String line = scan.nextLine();
			/** Scanner object that gets information from line */
			Scanner lineScan = new Scanner(line);
			while (lineScan.hasNext()){
				HTTPLog obj = new HTTPLog();
				try {
					/** HTTPLog obj - used to store the data read in from one line of the file */
					lineScan.next();
					lineScan.next();
					lineScan.next();
					/** String dateTime - used to store the date and time of the resource requested */
					String dateTime = lineScan.next() + lineScan.next();
					obj.setDateTime(dateTime);
					lineScan.next();
					/** String resource - used to store the name of the website requested */
					String resource = lineScan.next(); 
					lineScan.next();
					obj.setResource(resource);
					/** int responseCode - used to store the response code of the resource requested */
					int responseCode = lineScan.nextInt();
					obj.setResponseCode(responseCode);
					/** int numBytes - used to store the number of bytes for the resource requested */
					int numBytes;
					/** If the responseCode was not an error and there was a number of bytes given in the file */
					if (lineScan.hasNextInt()) {
						numBytes = lineScan.nextInt();
					}
					/** else, it is set to 0 */
					else {
						numBytes = 0;
					}
					obj.setNumBytes(numBytes);
					lineScan.nextLine();
				}
				/** There are 2 lines in the file which contain whitespace in their resource names - the catch handles 
				  * the issues and places the correct response code & number of bytes into the HTTPLog oject 
				  */
				catch (InputMismatchException e) {
					lineScan.next();
					lineScan.next();
					lineScan.next();
					/** int responseCode - used to store the response code of the resource requested */
					int responseCode;
					if (lineScan.hasNextInt()) {
						responseCode = lineScan.nextInt();
					}
					else {
						responseCode = 0;
					}
					obj.setResponseCode(responseCode);
					/** int numBytes - used to store the number of bytes for the resource requested */
					int numBytes;
					if (lineScan.hasNextInt()) {
						numBytes = lineScan.nextInt();
					}
					else {
						numBytes = 0;
					}
					obj.setNumBytes(numBytes);
					lineScan.nextLine();
				}
				/** if hashtable's addElement method returns true, then an object with the same resource name did not 
				  * already exist and it was successfully added to the hashtable. 
				  */
				if (hashtable.addElement(obj)) {
					
				}
				/** else, we update the object that is already in the hashtable with the current object's date and 
				  * and response code. The number of bytes are combined and the count is increased for that resource. 
				  */
				else {
					hashtable.find(obj).setDateTime(obj.getDateTime());
					hashtable.find(obj).setNumBytes(obj.getNumBytes());
					hashtable.find(obj).setResponseCode(obj.getResponseCode());
					hashtable.find(obj).increaseCount();
				}
				
			}	
		}
		/** boolean endInput - variable that keeps the loop running until the user enters QUIT */
		boolean endInput = false;
		/** Scanner inputScan - Scanner object used for reading the user's input */
		Scanner inputScan;
		/** String line - used to store the user's input */
		String line;
		while (!endInput) {
			System.out.println("Please enter DETAILS, TOPTEN or CHAIN. Type QUIT to exit: ");
			inputScan = new Scanner(System.in);
			line = inputScan.nextLine();
			if (line.equals("DETAILS")) {
					this.detailsMethod();
			}
			else if (line.equals("TOPTEN")) {
				this.topTenMethod();
				
			}
			else if (line.equals("CHAIN")) {
				this.chainMethod();
				
			}
			else if (line.equals("QUIT")) {
				System.out.println("Now terminating the program.");
				endInput = true;
				System.exit(1);
			}
		}
	}
	
	/** The detailsMethod method takes in a resource name from the user, places it in an HTTPLog object and 
	  * searches for an object with the same resource name in the hashtable. If it is found, it is printed.
	  * If not, a message is displayed to the user.
	  */
	private void detailsMethod() {
		System.out.println("Please enter resource name: ");
		/** Scanner resourceScan - Scanner object that takes in the resource name that the user enters */
		Scanner resourceScan = new Scanner(System.in);
		/** String resource - stores the resource that the user enters */
		String resource = resourceScan.nextLine();
		/** HTTPLog obj2 - used to find the HTTPLog object with the same name contained in the hashtable,
		  * if it exists
		  */
		HTTPLog obj2 = new HTTPLog();
		obj2.setResource(resource);
		/** HTTPLog temp - object to store the one returned from the hashtable's find method */
		HTTPLog temp = hashtable.find(obj2);
		if (temp != null) {
			System.out.println(temp);
		}
		else {
			System.out.println("That resource was not found. Please try another: ");
		}
	}
	
	/** The topTenMethod method creates an array of hashtable's length & stores the element in each bucket 
	  * with the highest counts in each position. It then sorts the array lowest-highest and prints out the 
	  * last ten elements in the array.
	  */
	private void topTenMethod() {
		/** HTTPLog [] topCounts - array that stores the object with the highest count for each bucket of the hashtable */
		HTTPLog[] topCounts = new HTTPLog[hashtable.length()];
		for (int i = 0; i < topCounts.length; i++) {
			/** Iterator itr - Iterator object that gets the LinkedList iterator for each bucket of the hashtable */
			Iterator itr = hashtable.getChainIterator(i);
			while(itr.hasNext()) {
				/** HTTPLog obj - object that stores the next element in the LinkedList */
				HTTPLog obj = (HTTPLog) itr.next();
				/** If an object has already been added to the ith position of the array */
				if (topCounts[i] != null) {
					/** The count of the object stored in the array & the count of the current object are compared.
					  * If the current object's count is greater, then it replaces the object already in the array 
					  */
					if (topCounts[i].getCount() < obj.getCount()) {
						topCounts[i] = obj;
					}
				}
				/** else, this is the first iteration of while & the first element found in the iterator is added to 
				  * the ith position of the array as a default 
				  */
				else {
					topCounts[i] = obj;
				}
			}
		}
		/** Sorts the array by count instead of resource names */
		HTTPLog.currentCompType = 2;
		Arrays.sort(topCounts);
		/** resets the compareTo to resource name in case the user wishes to enter DETAILS after TOPTEN */
		HTTPLog.currentCompType = 1;
		/** Prints the objects at the last 10 positions of the sorted array */
		for (int i = topCounts.length-1; i > topCounts.length-11; i--) {
			System.out.println(topCounts[i].getResource() + " Count: " + topCounts[i].getCount());
		}
	}
	
	/** The chainMethod method goes through each bucket of the hashtable and determines which has the most elements.
	  * It then prints out all of the elements in that particular bucket 
	  */
	private void chainMethod() {
		/** int chainCount - running total for the number of elements in a chain */
		int chainCount = 0;
		/** int biggest - the current largest chain that the program has seen */
		int biggest = 0;
		/** int bestBucket - the bucket with the largest chain */
		int bestBucket = 0;
		for (int i = 0; i < hashtable.length(); i++) {
			Iterator itr = hashtable.getChainIterator(i);
			/** increases the chainCount while the bucket at position i still has elements */
			while (itr.hasNext()) {
				chainCount++;
				itr.next();
			}
			/** if the chainCount is greater than the number we currently have set as biggest, we store 
			  * chainCount as biggest 
			  */
			if (chainCount > biggest) {
				biggest = chainCount;
				/** The best bucket is the bucket we found the largest chain in */
				bestBucket = i;
				chainCount = 0;
			}
			/** chainCount is reset to 0 either way */
			else {
				chainCount = 0;
			}
		}
		/** Iterator itr - stores the Iterator for the LinkedList at the best bucket */
		Iterator itr = hashtable.getChainIterator(bestBucket);
		System.out.println("Bucket #" + bestBucket + ". Chain size: " + biggest);
		while(itr.hasNext()) {
			/** HTTPLog tempObject is stored as itr.next() - we need to cast it to HTTPLog in order to get the resource */
			HTTPLog tempObject = (HTTPLog) itr.next();
			System.out.println(tempObject.getResource());
		}
	}
	
	/** The exceptionHandler method is called if the file cannot be properly accessed & the method throws an IOException.
	  * It writes a brief message to a file called "errors.txt," which is itself encased in a try catch in case IT throws 
	  * an IOException. */
	private void exceptionHandler() {
		try {
			/** errorFile - PrintWriter object for writing information to "errors.txt." Allows information to be appended 
			  * to the file, so it can be opened and closed multiple times 
			  */
			PrintWriter errorFile = new PrintWriter(new BufferedWriter(new FileWriter("errors.txt", true)));
			errorFile.println("There was an issue reading from the file. The program will now terminate.");
			errorFile.close();
			System.exit(1);
		}
		catch (IOException e) {
			System.err.println("There was a problem writing to the error file.");
			System.exit(1);
		}	
	}
}