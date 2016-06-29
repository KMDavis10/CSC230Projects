/** Kyle Davis
  * Project 1 - Zip Code Analysis 
  * CSC230 */

/** This program is designed to read a large amount of data from a website */

import java.io.*;

public class Application {
	public static void main (String args[]) throws IOException {
		readWebsite data;
		data = new readWebsite();
		data.readData();
		data.listRTowns();
		data.listCounties();
		data.listTypes();
		data.totalCounties();
		data.zipCodes("PORTSMOUTH");
		data.writeFile();
	}
}