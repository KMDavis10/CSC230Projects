import java.util.*;
import java.io.*;
import java.net.*;
public class Test2 {
	public static void main (String args []) throws IOException {
		String line;
		Scanner scan, lineScan;
		String zipCode, location, state, county, type;
		double lat, lon;
		zipCodeLocation obj1;
		URL url;
		int count = 0;
		String [] compare;
	
		String [] compare = new String [100000];
		Comparable[] comparable;
		Comparable[] zipCodeObjectArray = new Comparable[100000]; 
		url = new URL ("https://s3.amazonaws.com/depasquale/datasets/zipcodes.txt");
		scan = new Scanner (new InputStreamReader(url.openStream()));
		while (scan.hasNext()) {
			line = scan.nextLine();
			lineScan = new Scanner(line);
			lineScan.useDelimiter(",|\"");
			while (lineScan.hasNext()){
				zipCode = lineScan.next();
				lineScan.next();
				lineScan.next();
				try {
					lat = lineScan.nextDouble();
					lineScan.next();
					lineScan.next();
					lon = lineScan.nextDouble();
					lineScan.next();
					lineScan.next();
					location = lineScan.next();
					lineScan.next();
					lineScan.next();
					state = lineScan.next();
					lineScan.next();
					lineScan.next();
					county = lineScan.next();
					lineScan.next();
					lineScan.next();
					type = lineScan.next();
					lineScan.nextLine();
					obj1 = new zipCodeLocation ();
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
	
		comparable = new Comparable[count];
		int count2 = 0;
		for (int i = 0; i < comparable.length; i++) {
			if (zipCodeObjectArray[i] != null) {
				comparable[count2] = zipCodeObjectArray[i];
				count2++;
			}
		}
		Arrays.sort(comparable);
		for (int i = 0; i < comparable.length; i++) {
				System.out.println(comparable[i]);
		}  
		
		for (int i = 0; i < comparable.length-1; i++) {
			if (comparable[i].getState().equals("RI")) {
				if (comparable[i].getLocation().equals(comparable[i+1].getLocation())){
					
				}
				else {
					compare[i] = comparable[i].getLocation()
				}
			} 
		}
		
		for (int i= 0; i < towns.length; i++){
			System.out.println(towns[i]);
		} 
	}
}
		