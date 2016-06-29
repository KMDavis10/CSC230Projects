import java.util.*;
import java.io.*;
import java.net.*;

public class readWebsite2 {
	private Comparable [] comparable;
	private Comparable [] zipCodeObjectArray = new Comparable [100000];
	private String [] sortTowns = new String [100000];
	private String [] counties = new String [100000];
	private String[] postalTypes = new String [100000];
	private String [] compare = new String [100000];
	private int countyCount = 0;
	private int count = 0;
	
	public readWebsite2 () {
		
	}
	
	public void readData () throws IOException {
		URL url = new URL ("https://s3.amazonaws.com/depasquale/datasets/zipcodes.txt");
		Scanner scan = new Scanner (new InputStreamReader(url.openStream()));
		while (scan.hasNext()) {
			String line = scan.nextLine();
			Scanner lineScan = new Scanner(line);
			lineScan.useDelimiter(",|\"");
			while (lineScan.hasNext()){
				String zipCode = lineScan.next();
				lineScan.next();
				lineScan.next();
				try {
					double lat = lineScan.nextDouble();
					lineScan.next();
					lineScan.next();
					double lon = lineScan.nextDouble();
					lineScan.next();
					lineScan.next();
					String location = lineScan.next();
					lineScan.next();
					lineScan.next();
					String state = lineScan.next();
					lineScan.next();
					lineScan.next();
					String county = lineScan.next();
					lineScan.next();
					lineScan.next();
					String type = lineScan.next();
					lineScan.nextLine();
					zipCodeLocation obj1 = new zipCodeLocation ();
					obj1.setZipCode(zipCode);
					obj1.setLatitude(lat);
					obj1.setLongitude(lon);
					obj1.setLocation(location);
					obj1.setState(state);
					obj1.setCounty(county);
					obj1.setType(type);
					zipCodeObjectArray[count] = obj1;
					count++;
				}
			 
				catch (InputMismatchException e) {
					System.out.println("Missing information at: " + zipCode);
					lineScan.nextLine();
				}
				
			}
		}
		
		int count2 = 0;
		comparable = new Comparable[count];
		for (int i = 0; i < comparable.length; i++) {
			if (zipCodeObjectArray[i] != null) {
				comparable[count2] = zipCodeObjectArray[i];
				count2++;
			}
		}
		
		
		
	}
	/** Finds all towns in Rhode Island and lists them uniquely and in alphabetical order */
	public String [] listRTowns () {
		//int townCount = 0;
		zipCodeLocation temp = new zipCodeLocation ();
		/** The comparable array is sorted first by towns and then by state */
		temp.setCurrentCompType(4);
		Arrays.sort(comparable);
		temp.setCurrentCompType(2);
		Arrays.sort(comparable);
		for (int i = 0; i < comparable.length-1; i++) {
				/** The objects stored in comparable must be cast as zipCodeLocation objects in order to use ZCL methods */
				zipCodeLocation foo = (zipCodeLocation) comparable[i];
				zipCodeLocation foo2 = (zipCodeLocation) comparable[i+1];
				if (foo.getState().equals("RI")) {
					foo.setCurrentCompType(4);
					/** Each town in Rhode island is compared in order. If the town is equal to the one it is compared to (duplicates)
					  * then the count is increased. If not, then it is stored to a string array and the count is increased.
					  * The last town in Rhode Island is compared to a town from the next state and added to the array. 
					  * We know that that town will not be the same as the last Rhode Island town because the next state 
					  * also will have their towns sorted alphabetically */
					if (foo.compareTo(foo2) != 0){
						sortTowns[i] = foo.getLocation();
					}
					else {
						//townCount++;
					}
			} 
		}
		return sortTowns;
		
	}
	/** Lists all counties that USPS delivers to */
	public String[] listCounties() {
		/** The comparable array is set to be sorted by counties */
		zipCodeLocation temp = new zipCodeLocation();
		temp.setCurrentCompType(3);
		Arrays.sort(comparable);
		counties = new String[comparable.length];
 		for (int i = 0; i < comparable.length-1; i++) {
				zipCodeLocation foo = (zipCodeLocation) comparable[i];
				zipCodeLocation foo2 = (zipCodeLocation) comparable[i+1];
				if (foo.compareTo(foo2) != 0) { 
					counties[i] = foo.getCounty();
				}
				/** Because the array takes all counties, there is nothing to compare the last location to. Therefore, it is 
				  * automatically stored in the array */
				else if (i+1 == comparable.length-1){
					counties[i+1] = foo2.getCounty();
				}
				else {
					
				}
		}
		return counties;
	}
	
	
	public String[] listTypes() {
		int count2 = 1;
		int typeCount = 0;
		zipCodeLocation temp = new zipCodeLocation();
		temp.setCurrentCompType(5);
		Arrays.sort(comparable);
		for (int i = 0; i < comparable.length-1; i++) {
			zipCodeLocation foo = (zipCodeLocation) comparable[i];
			zipCodeLocation foo2 = (zipCodeLocation) comparable[i+1];
			foo.setCurrentCompType(5);
			if(foo.compareTo(foo2) == 0){
				count2++;
				postalTypes[typeCount] = foo.getType() + ": " + count2; 
			}
			else {
				count2 = 1;
				typeCount++;
			} 
		}
		
		return postalTypes;
	
	}
	
	
	public int totalCounties() {
		zipCodeLocation.setCurrentCompType(3);
		Arrays.sort(comparable);
		zipCodeLocation.setCurrentCompType(2);
		Arrays.sort(comparable);
		zipCodeLocation.setCurrentCompType(3);
 		for (int i = 0; i < comparable.length-1; i++) {
				zipCodeLocation foo = (zipCodeLocation) comparable[i];
				zipCodeLocation foo2 = (zipCodeLocation) comparable[i+1];
				if (foo.compareTo(foo2) != 0) { 
					countyCount++;
				}
				else if (i+1 == comparable.length-1){
					countyCount++;
				}
				else {
					
				}
		}
		return countyCount;
	}
	
	public String[] zipCodes(String eTown) {
		//int zipCount = 0;
		zipCodeLocation temp = new zipCodeLocation();
		String town = eTown;
		temp.setCurrentCompType(1);
		Arrays.sort(comparable);
		for (int i = 0; i < comparable.length-1; i++) {
			zipCodeLocation foo = (zipCodeLocation) comparable[i];
			zipCodeLocation foo2 = (zipCodeLocation) comparable[i+1];
				if (foo.getLocation().contains(town)) {
					foo.setCurrentCompType(1);
					if(foo.compareTo(foo2) != 0){
						compare[i] = foo.getZipCode();
					}
					else {
						
					} 
				} 
		}
		
		return compare;
	}
	
	public void writeFile() throws IOException {
		String file = "zipout.txt";
		FileWriter fw = new FileWriter (file);
		BufferedWriter bw = new BufferedWriter (fw);
		PrintWriter outFile = new PrintWriter(bw);
		outFile.println("-------------------------------------------");
		outFile.println("Alphabetical list of all towns in Rhode Island: ");
		outFile.println("-------------------------------------------");
		for (int i = 0; i < sortTowns.length; i++) {
			if (sortTowns[i] != null) {
				outFile.print(sortTowns[i] + ", ");
			}
		}
		outFile.println();
		outFile.println("-------------------------------------------");
		outFile.println("All of the counties the USPS delivers to: ");
		outFile.println("-------------------------------------------");
		for (int i = 0; i < counties.length; i++) {
			if (counties[i] != null) {
				outFile.print(counties[i] + ", ");
			}
		}
		outFile.println();
		outFile.println("-------------------------------------------");
		outFile.println("All of the different postal codes: ");
		outFile.println("-------------------------------------------");
		for (int i = 0; i < postalTypes.length; i++) {
			if (postalTypes[i] != null) {
				outFile.println(postalTypes[i]);
			}
		}
		outFile.println();
		outFile.println("-------------------------------------------");
		outFile.println("Total number of counties the USPS delivers to: ");
		outFile.println("-------------------------------------------");
		outFile.println(countyCount);
		
		outFile.println("-------------------------------------------");
		outFile.println("All of the zipcodes whose location contains \"PORTSMOUTH\"");
		outFile.println("-------------------------------------------");
		for (int i = 0; i < compare.length; i++) {
			if (compare[i] != null) {
				outFile.print(compare[i] + ", ");
			}
		}
		
		outFile.close();
	}
	
}