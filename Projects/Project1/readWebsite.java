import java.util.*;
import java.io.*;
import java.net.*;

public class readWebsite {
	private Comparable [] comparable;
	private Comparable [] zipCodeObjectArray = new Comparable [100000];
	private String [] sortTowns;
	private String [] counties;
	private String[] postalTypes;
	private String [] compare;
	private int countyCount;
	private int count = 0;
	
	public readWebsite () {
		
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
					//System.out.println(obj1);
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
				System.out.println(comparable[count2]);
				count2++;
			}
		}
		
		
		
	}
	
	public String[] listRTowns () {
		int townCount = 0;
		int count2 = 0;
		String [] compare = new String [comparable.length];
		
		for (int i = 0; i < comparable.length-1; i++) {
			if (comparable[i] != null) {
				zipCodeLocation foo = (zipCodeLocation) comparable[i];
				if (foo.getState().equals("RI")) {
					if(Arrays.asList(compare).contains(foo.getLocation())){
					
					}
					else {
						compare[i] = foo.getLocation();
						townCount++;
					}
				} 
			}
		}
	
		sortTowns = new String [townCount];
		for (int i = 0; i < compare.length; i++) {
			if (compare[i] != null) {
				sortTowns[count2] = compare[i];
				count2++;
			}
		}
		
		Arrays.sort(sortTowns);
		
		return sortTowns;
		
	}
	
	public String[] listCounties() {
		counties = new String[comparable.length];
 		for (int i = 0; i < comparable.length; i++) {
			if (comparable[i] != null) {
				zipCodeLocation foo = (zipCodeLocation) comparable[i];
				if (Arrays.asList(counties).contains(foo.getCounty())) {
					
				}
				else {
					counties[i] = foo.getCounty();
				}
			}
		}
		
		return counties;
			
	}
	
	
	public String[] listTypes() {
		int typeCount = 0;
		int count2 = 0;
		String [] compare = new String[comparable.length];
		
		for (int i = 0; i < comparable.length; i++) {
			if (comparable[i] != null) {
				zipCodeLocation foo = (zipCodeLocation) comparable[i];
				compare[i] = foo.getType();
				typeCount++;	
			}
		}
		
		String [] types2 = new String [typeCount];
		for (int i = 0; i < compare.length; i++) {
			if (compare[i] != null) {
				types2[count2] = compare[i];
				count2++;
			}
		} 
		
		Arrays.sort(types2);
		
		count2 = 1;
		typeCount = 0;
		int [] typeTimes = new int [types2.length];
		String [] diffTypes = new String [types2.length];
		
		for (int i = 0; i < types2.length-1; i++) {
			if(types2[i].compareTo(types2[i+1]) == 0){
				count2++;
				diffTypes[typeCount] = types2[i];
				typeTimes[typeCount] = count2; 
			}
			else {
				count2 = 1;
				typeCount++;
			} 
		}
		postalTypes = new String[types2.length];
		for (int i = 0; i < types2.length; i++) {
			if (diffTypes[i] != null && typeTimes[i] != 0) {
				postalTypes[i] = diffTypes[i] + ": " + typeTimes[i];
			}
		}
		return postalTypes;
	}
	
	
	public int totalCounties() {
		String[] compare = new String[comparable.length];
		countyCount = 0;
		
		for (int i = 0; i < comparable.length; i++) {
			if (comparable[i] != null) {
				zipCodeLocation foo = (zipCodeLocation) comparable[i];
				if (Arrays.asList(compare).contains(foo.getCounty())) {
					
				}
				else {
					compare[i] = foo.getCounty();
				}
			}
		}
		for (int i = 0; i < comparable.length; i++) {
			if (compare[i] != null) {
				countyCount++;
			}
		}
		
		return countyCount;
	}
	
	public String[] zipCodes(String eTown) {
		String town = eTown;
		compare = new String [comparable.length];
		for (int i = 0; i < comparable.length; i++) {
			if (comparable[i] != null) {
				zipCodeLocation foo = (zipCodeLocation) comparable[i];	
				if (foo.getLocation().contains(town)) {
					if(Arrays.asList(compare).contains(foo.getZipCode())){
					
					}
					else {
						compare[i] = foo.getZipCode();
					} 
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