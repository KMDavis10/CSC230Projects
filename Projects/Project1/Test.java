import java.util.*;
import java.io.*;
import java.net.*;
public class Test {
	public static void main (String args []) throws IOException {
		String line;
		Scanner scan, lineScan;
		String zipCode, location, state, county, type;
		double lat, lon;
		zipCodeLocation obj1;
		URL url;
		int count = 0;
		int typeCount = 0;
		int townCount = 0;
		//int count2 = 0;
		int countyCount = 0;
		
		//int typeCount = 0;
		//int count3 = 0;
		//int countyCount = 0;
		//String [] types = new String [100000];
		String [] compare = new String [10000];
		Comparable[] comparable;
		Comparable[] zipCodeObjectArray = new Comparable[100000]; 
		//System.out.println(line);
		url = new URL ("https://s3.amazonaws.com/depasquale/datasets/zipcodes.txt");
		scan = new Scanner (new InputStreamReader(url.openStream()));
		while (scan.hasNext()) {
			line = scan.nextLine();
			lineScan = new Scanner(line);
			lineScan.useDelimiter(",|\"");
			while (lineScan.hasNext()){
				zipCode = lineScan.next();
				//System.out.println(zipCode);
				lineScan.next();
				lineScan.next();
				try {
					lat = lineScan.nextDouble();
					//System.out.println(lat);
					lineScan.next();
					lineScan.next();
					lon = lineScan.nextDouble();
					//System.out.println(lon);
					lineScan.next();
					lineScan.next();
					location = lineScan.next();
					//System.out.println(location);
					lineScan.next();
					lineScan.next();
					state = lineScan.next();
					//System.out.println(state);
					lineScan.next();
					lineScan.next();
					county = lineScan.next();
					//System.out.println(county);
					lineScan.next();
					lineScan.next();
					type = lineScan.next();
					lineScan.nextLine();
					//System.out.println(type);
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
					//System.out.println(obj1.getLatitude());
		
			}
		}
	
		comparable = new Comparable[count];
		compare = new String [count];
		int count2 = 0;
		for (int i = 0; i < comparable.length; i++) {
			if (zipCodeObjectArray[i] != null) {
				comparable[count2] = zipCodeObjectArray[i];
				count2++;
			}
		}
		//for (int i = 0; i < comparable.length; i++) {
				//System.out.println(comparable[i]);
	//	}  
		zipCodeLocation temp = new zipCodeLocation();
		temp.setCurrentCompType(4);
		Arrays.sort(comparable);
		temp.setCurrentCompType(2);
		Arrays.sort(comparable);
	
	
		 Arrays.sort(comparable);
		/*for (int i = 0; i < comparable.length-1; i++) {
				zipCodeLocation foo = (zipCodeLocation) comparable[i];
				zipCodeLocation foo2 = (zipCodeLocation) comparable[i+1];
				if (foo.getState().equals("RI")) {
					foo.setCurrentCompType(4);
					if (foo.compareTo(foo2) != 0){
						compare[i] = foo.getLocation();
					}
					else {
					
					}
			} 
		}
		
		for (int i= 0; i < comparable.length; i++){
			if (compare[i] != null) {
				System.out.println(compare[i]);
			}
		} */
		
		/*count = 0;
		for (int i = 0; i < comparable.length-1; i++) {
				zipCodeLocation foo = (zipCodeLocation) comparable[i];
				zipCodeLocation foo2 = (zipCodeLocation) comparable[i+1];
				foo.setCurrentCompType(3);
				if (foo.compareTo(foo2) != 0) { 
					compare[i] = foo.getCounty();
					count++;
				}
				else if (i+1 == comparable.length-1 && foo.compareTo(foo2) == 0) {
					compare[i+1] = foo2.getCounty();
					System.out.println("---------------FOOOOOOOOOo----------------");
					System.out.println(compare[i+1]);
					System.out.println(foo);
					System.out.println(foo2);
					count++;
				}
				else if (i+1 == comparable.length-1 && foo.compareTo(foo2) != 0){
					compare[i+1] = foo2.getCounty();
					count++;
				}
				else {
					
				}
			
		}
			for (int i = 0; i < comparable.length; i++) {
				if (compare[i] != null) {
					System.out.println(compare[i]);
				}
			}
			System.out.println(count);  */
		/**count2 = 1;
		typeCount = 0;
		String [] diffTypes = new String [comparable.length];
		
		for (int i = 0; i < comparable.length-1; i++) {
			zipCodeLocation foo = (zipCodeLocation) comparable[i];
			zipCodeLocation foo2 = (zipCodeLocation) comparable[i+1];
			foo.setCurrentCompType(5);
			if(foo.compareTo(foo2) == 0){
				count2++;
				compare[typeCount] = foo.getType() + ": " + count2; 
			}
			else {
				count2 = 1;
				typeCount++;
			} 
		} 
		for (int i = 0; i < compare.length; i++) {
			if (compare[i] != null) {
				System.out.println(compare[i]);
			}
		} */
		/*for (int i = 0; i < comparable.length; i++) {
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
				//System.out.println(compare[i]);
				countyCount++;
			}
		}
	    //System.out.println("Total counties: " + countyCount); */
		
		for (int i = 0; i < comparable.length-1; i++) {
			zipCodeLocation foo = (zipCodeLocation) comparable[i];
			zipCodeLocation foo2 = (zipCodeLocation) comparable[i+1];
				if (foo.getLocation().contains("PORTSMOUTH")) {
					foo.setCurrentCompType(1);
					if(foo.compareTo(foo2) != 0){
						compare[i] = foo.getZipCode();
						System.out.println(foo.getZipCode());
					}
					else {
						
					} 
					System.out.println(foo.getLocation());
				} 
		}
		
	/*	for (int i = 0; i < compare.length; i++) {
			if (compare[i] != null) {
				System.out.print(compare[i] + ", ");
			}
		}  */

		/*for (int i = 0; i < comparable.length; i++) {
			if (comparable[i] != null) {
				zipCodeLocation foo = (zipCodeLocation) comparable[i];
				//System.out.println(foo.getType());
				//System.out.println(foo.getLocation());
					//if(Arrays.asList(compare).contains(foo.getType())){
					
					//}
					//else {
						compare[i] = foo.getType();
						typeCount++;
					//} 
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
		for (int i= 0; i < types2.length; i++){
				System.out.println(types2[i]);
		}
		count2 = 1;
		typeCount = 0;
		int [] typeTimes = new int [types2.length];
		String [] diffTypes = new String [types2.length];
		for (int i = 0; i < types2.length-1; i++) {
			//diffTypes[0] = types2[0];
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
		
		for (int i = 0; i < types2.length; i++) {
			if (diffTypes[i] != null && typeTimes[i] != 0) {
				System.out.println(diffTypes[i] + "times: " + typeTimes[i]);
			}
		} */
		
		
		/*readWebsite read = new readWebsite();
		String file = "zipout.txt";
		FileWriter fw = new FileWriter (file);
		BufferedWriter bw = new BufferedWriter (fw);
		PrintWriter outFile = new PrintWriter(bw);
		outFile.println("Total number of counties USPS delivers to: " + countyCount);
		
		outFile.close(); */
		
		
	}
}