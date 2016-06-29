/** Kyle Davis
  * 2/17/14 
  * Project 1 - Zip Code Analysis 
  * CSC230 - Dr. DePasquale 
  */
import java.util.*;
import java.io.*;
import java.net.*;

/** The readWebsite class takes in lines of postal information from a website, passes it through to zipCodeLocation and stores the
  * zipCodeLocation objects in a Comparable type array. Contains methods to answer questions about the information stored in this array.
  * Contains String arrays and integer values to store the answers to these questions. After all the other methods are called, the information
  * is written to a file called "zipout.txt" 
  * 
  * @author Kyle Davis 
  */
public class readWebsite {
	/** Comparable type array to store the zipCodeLocation objects - no size is declared initially */
	private Comparable [] comparable;
	/** Comparable type array to store zipCodeLocation objects initially - given an arbitrary size of 100,000 */
	private Comparable [] zipCodeObjectArray = new Comparable [100000];
	/** String array to store the towns in Rhode Island alphabetically - given an arbitrary size of 100,000 */
	private String [] sortTowns = new String [100000];
	/** String array to store the unique counties - given an arbitrary size of 100,000 */
	private String [] counties = new String [100000];
	/** String array to store the different postal types and how many times they show up - given an arbitrary size of 100 */
	private String[] postalTypes = new String [100];
	/** String array to store the zipcodes containing the town passed in as a parameter - given an arbitrary size of 100,000 */
	private String [] compare = new String [100000];
	/** Integer set to 0 which will contain the total count of all counties that the USPS delivers to */
	private int countyCount = 0;
	/** Integer set to 0 which will dictate the size of the array comparable */
	private int count = 0;
	
	/** Empty constructor - no information is passed as parameters when readWebsite is called */
	public readWebsite () {
		
	}
	
	/** Takes in information from url and dissects it into String and Doubles - these are then passed into zipCodeLocation and stored
	  * into a Comparable array. If latitude/longitude are missing, it catches an inputMismatch exception and prints an error message
	  * stating the zipCode at which the information was missing.
	  *
	  * @throws an IOException if there are issues with the reading from the website 
	  */
	public void readData () throws IOException {
	    /** URL object that points to the website that contains the postal information */
		URL url = new URL ("https://s3.amazonaws.com/depasquale/datasets/zipcodes.txt");
		/** Scanner object that gets information from the website */
		Scanner scan = new Scanner (new InputStreamReader(url.openStream()));
		while (scan.hasNext()) {
		    /* String variable that contains a line of data from the website */
			String line = scan.nextLine();
			/** Scanner object that gets information from line */
			Scanner lineScan = new Scanner(line);
			/** delimits commas and double-quotes to get the information stored properly */
			lineScan.useDelimiter(",|\"");
			while (lineScan.hasNext()){
				/** String zipCode is the String up to the first delimiter character - we assume it will always be there, so it is 
				  * not included in the try statement */
				String zipCode = lineScan.next();
				lineScan.next();
				lineScan.next();
				try {
					/** double lat sets the latititude for each line */
					double lat = lineScan.nextDouble();
					lineScan.next();
					lineScan.next();
					/** double lon sets the longitude for each line - can contain +/-*/
					double lon = lineScan.nextDouble();
					lineScan.next();
					lineScan.next();
					/** String location sets the location for each line */
					String location = lineScan.next();
					lineScan.next();
					lineScan.next();
					/** String state sets the state for each line */
					String state = lineScan.next();
					lineScan.next();
					lineScan.next();
					/** String county sets the county for each line */
					String county = lineScan.next();
					lineScan.next();
					lineScan.next();
					/** String type sets the postal code type for each line */
					String type = lineScan.next();
					lineScan.nextLine();
					/** instantiates a new zipCodeLocation object */
					zipCodeLocation obj1 = new zipCodeLocation ();
					/** All variables declared above are set in obj1 */
					obj1.setZipCode(zipCode);
					obj1.setLatitude(lat);
					obj1.setLongitude(lon);
					obj1.setLocation(location);
					obj1.setState(state);
					obj1.setCounty(county);
					obj1.setType(type);
					/** places obj1 into a temporary array for storage */
					zipCodeObjectArray[count] = obj1;
					/** increases the count to determine how many non-null positions the temporary array has */
					count++;
				}
			 	/** If an InputMismatchException is thrown, then it is caught here
				  * The zipcode at which the problematic information was read is displayed and it moves to the next line
				  */
				catch (InputMismatchException e) {
					System.out.println("Missing information at: " + zipCode);
					lineScan.nextLine();
				}
				
			}
		}
		/** int count2 - a new count to fill each position of the new array */
		int count2 = 0;
		/** sets the size of the array to the non-null values in the temporary array */
		comparable = new Comparable[count];
		for (int i = 0; i < comparable.length; i++) {
		    /** if the temporary array's position does not contain a null value, then that information is set in the 
			  * comparable array. Count2 is increased every time this occurs 
			  */
			if (zipCodeObjectArray[i] != null) {
				comparable[count2] = zipCodeObjectArray[i];
				count2++;
			}
		}
		
		
		
	}
	/** Finds all towns in Rhode Island and lists them uniquely and in alphabetical order 
	  * 
	  * @return the array containing the Rhode Island towns 
	  */
	public String [] listRTowns () {
		//int townCount = 0;
		/** The comparable array is sorted first by towns and then by state */
		zipCodeLocation.setCurrentCompType(4);
		Arrays.sort(comparable);
		zipCodeLocation.setCurrentCompType(2);
		Arrays.sort(comparable);
		for (int i = 0; i < comparable.length-1; i++) {
				/** Two zipCodeLocation objects are created and set to the objects at position[i] and position[i+1] of the comparable array 
				  * The comparable array positions must first be cast to type zipCodeLocation in order for these objects to be compatible 
				  */
				zipCodeLocation foo = (zipCodeLocation) comparable[i];
				zipCodeLocation foo2 = (zipCodeLocation) comparable[i+1];
				if (foo.getState().equals("RI")) {
					zipCodeLocation.setCurrentCompType(4);
					/** Each town in Rhode island is compared in order. If they are not the same town, (duplicates)
					  * then it is stored to a string array. If they are, nothing is done. 
					  * The last town in Rhode Island is compared to a town from the next state and added to the array. 
					  * We know that that town will not be the same as the last Rhode Island town because the next state 
					  * also will have their towns sorted alphabetically */
					if (foo.compareTo(foo2) != 0){
						sortTowns[i] = foo.getLocation();
					}
					else {
						
					}
			} 
		}
		
		return sortTowns;
		
	}
	/** Finds all the unique counties that the USPS delivers to and places them in an array 
	  *
	  * @return the array countaining all the unique counties 
	  */
	public String[] listCounties() {
		/** The comparable array is set to be sorted by counties */
		zipCodeLocation.setCurrentCompType(3);
		Arrays.sort(comparable);
 		for (int i = 0; i < comparable.length-1; i++) {
				/** Two zipCodeLocation objects are created and set to the objects at position[i] and position[i+1] of the comparable array 
				  * The comparable array positions must first be cast to type zipCodeLocation in order for these objects to be compatible 
				  */
				zipCodeLocation foo = (zipCodeLocation) comparable[i];
				zipCodeLocation foo2 = (zipCodeLocation) comparable[i+1];
				/** If the counties are not equal to each other (duplicates), then the first county is stored in the array */
				if (foo.compareTo(foo2) != 0) { 
					counties[i] = foo.getCounty();
				}
				/** Because the array takes all counties, there is nothing to compare the last location to. Therefore, it is 
				  * automatically stored in the array */
				else if (i+1 == comparable.length-1){
					counties[i+1] = foo2.getCounty();
				}
				/** If the counties are duplicates, nothing is done */
				else {
					
				}
		}
		return counties;
	}
	
	/** Finds all of the postal types of the USPS and how many times they occur, and stores this information in an array
	  *
	  * @return the array containing the postal types and how many they occur */
	
	public String[] listTypes() {
		/** integer count2 determines how many times each postal type shows up */
		int count2 = 1;
		/** typeCount increments when another postal type is found and changes the position of the array that the
		  * new postal type is to be stored in */
		int typeCount = 0;
		/** Array is sorted by postal type */
		zipCodeLocation.setCurrentCompType(5);
		Arrays.sort(comparable);
		for (int i = 0; i < comparable.length-1; i++) {
			/** Two zipCodeLocation objects are created and set to the objects at position[i] and position[i+1] of the comparable array 
			  * The comparable array positions must first be cast to type zipCodeLocation in order for these objects to be compatible 
			  */
			zipCodeLocation foo = (zipCodeLocation) comparable[i];
			zipCodeLocation foo2 = (zipCodeLocation) comparable[i+1];
			foo.setCurrentCompType(5);
			/** If the two objects' postal codes are equal then the count is incremented and the postal type is stored into a String array
			  * along with a record of how many times it has been seen. */
			if(foo.compareTo(foo2) == 0){
				count2++;
				postalTypes[typeCount] = foo.getType() + ": " + count2; 
			}
			/** If they are not equal (a new type has been seen) then count2 is reset to 1 and the position of the array is changed */
			else {
				count2 = 1;
				typeCount++;
			} 
		}
		
		return postalTypes;
	
	}
	
	/** Finds all the total counties that the USPS delivers to, including duplicate names in different states and counts them 
	  * 
	  * @return the total number of counties 
	  */
	public int totalCounties() {
		/** The array is sorted first by county, then by state */
		zipCodeLocation.setCurrentCompType(3);
		Arrays.sort(comparable);
		zipCodeLocation.setCurrentCompType(2);
		Arrays.sort(comparable);
		/** The sort is set back to county after the sorting is done */
		zipCodeLocation.setCurrentCompType(3);
 		for (int i = 0; i < comparable.length-1; i++) {
			   /** Two zipCodeLocation objects are created and set to the objects at position[i] and position[i+1] of the comparable array 
			 	 * The comparable array positions must first be cast to type zipCodeLocation in order for these objects to be compatible 
			     */
				zipCodeLocation foo = (zipCodeLocation) comparable[i];
				zipCodeLocation foo2 = (zipCodeLocation) comparable[i+1];
				/** If the counties are not the same (duplicates) then the count is increased */
				if (foo.compareTo(foo2) != 0) { 
					countyCount++;
				}
				/** Because all counties are looked at, there is nothing to compare the last county to. Therefore, it is automatically
				  * counted */
				else if (i+1 == comparable.length-1){
					countyCount++;
				}
				/** If the counties are duplicates, nothing is done */
				else {
					
				}
		}
		return countyCount;
	}
	
	/** Finds all the zipcodes pertaining to the town passed in as parameters and puts them in an array 
	  * 
	  * @param eTown - the town being passed in that the zipcodes are found for 
	    @return the array containing the zipcodes */
	public String[] zipCodes(String eTown) {
		/** String town is set to the town passed as parameters */
		String town = eTown;
		/** The array is sorted by zipcodes */
		zipCodeLocation.setCurrentCompType(1);
		Arrays.sort(comparable);
		for (int i = 0; i < comparable.length-1; i++) {
			/** Two zipCodeLocation objects are created and set to the objects at position[i] and position[i+1] of the comparable array 
			  * The comparable array positions must first be cast to type zipCodeLocation in order for these objects to be compatible 
			  */
			zipCodeLocation foo = (zipCodeLocation) comparable[i];
			zipCodeLocation foo2 = (zipCodeLocation) comparable[i+1];
				if (foo.getLocation().contains(town)) {
					/** if the zipcodes are not the same (duplicates) then the first one is added to the array. The last zipcode will be 
					  * a zipcode from a location not containing town and will be added to the array as well */
					if(foo.compareTo(foo2) != 0){
						compare[i] = foo.getZipCode();
					}
					/** If the zipcodes are duplicates, then nothing is done*/
					else {
						
					} 
				} 
		}
		
		return compare;
	}
	/** writeFile writes all of the information in the String arrays and countyCount to a textfile called "zipout.txt" 
	  * 
	  * @throws an IOException in case of permission or other file issues */
	public void writeFile() throws IOException {
		/** String file contains the name of the location that the information is written to */
		String file = "zipout.txt";
		/** A new FileWriter object is created and passed in file */
		FileWriter fw = new FileWriter (file);
		/** A new BufferedWriter object is created and passed in the FileWriter object */
		BufferedWriter bw = new BufferedWriter (fw);
		/** A new PrintWriter object is created and passed in the BufferedWriter object */
		PrintWriter outFile = new PrintWriter(bw);
		outFile.println("-------------------------------------------");
		outFile.println("Alphabetical list of all towns in Rhode Island: ");
		outFile.println("-------------------------------------------");
		/** Prints all of the non-null values of the array containing the Rhode Island towns to the file */
		for (int i = 0; i < sortTowns.length; i++) {
			if (sortTowns[i] != null) {
				outFile.print(sortTowns[i] + ", ");
			}
		}
		outFile.println();
		outFile.println("-------------------------------------------");
		outFile.println("All of the counties the USPS delivers to: ");
		outFile.println("-------------------------------------------");
		/** Prints all of the non-null values of the array containing all unique counties to the file */
		for (int i = 0; i < counties.length; i++) {
			if (counties[i] != null) {
				outFile.print(counties[i] + ", ");
			}
		}
		outFile.println();
		outFile.println("-------------------------------------------");
		outFile.println("All of the different postal codes: ");
		outFile.println("-------------------------------------------");
		/** Prints all of the non-null values of the array containing all postal types to the file */
		for (int i = 0; i < postalTypes.length; i++) {
			if (postalTypes[i] != null) {
				outFile.println(postalTypes[i]);
			}
		}
		outFile.println();
		outFile.println("-------------------------------------------");
		outFile.println("Total number of counties the USPS delivers to: ");
		outFile.println("-------------------------------------------");
		/** Prints the count of all the counties the USPS delivers to */
		outFile.println(countyCount);
		
		outFile.println("-------------------------------------------");
		outFile.println("All of the zipcodes whose location contains \"PORTSMOUTH\"");
		outFile.println("-------------------------------------------");
		/** Prints all of the non-null values of the array containing the zipcodes whose town name is the passed in parameter 
		  * (in this case, "PORTSMOUTH") 
		  */
		for (int i = 0; i < compare.length; i++) {
			if (compare[i] != null) {
				outFile.print(compare[i] + ", ");
			}
		}
		/** Closes the file */
		outFile.close();
	}
	
}