import java.io.*;
import java.util.*;
import java.net.*;
public class readData {

	private String line, zipCode, location, state, county, type;
	private double lat, lon;
	private Scanner scan, lineScan;
	private zipCodeLocation obj1;
	private URL url;
	
	public readData ();
	
	public void readInfo () throws IOException {
		url = new URL ("https://s3.amazonaws.com/depasquale/datasets/zipcodes.txt");
		scan = new Scan (new InputStreamReader(url.openStream()));
		while (scan.hasNext()) {
			line = scan.nextLine();
			lineScan = new Scanner(line);
			lineScan.useDelimiter(",|\"");
			while (lineScan.hasNext()){
				zipCode = lineScan.next();
				lineScan.next();
				lineScan.next();
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
				type = lineScan.nextLine();
				obj1 = new zipCodeLocation (zipCode, lat, lon, location, state, county, type);
			}
		}
	}
	
	public String toString() {
	
	}
}
	
