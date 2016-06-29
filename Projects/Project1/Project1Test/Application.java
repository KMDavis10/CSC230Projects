/** Kyle Davis
  * 2/17/14 
  * Project 1 - Zip Code Analysis 
  * CSC230 - Dr. DePasquale */

import java.io.*;

/** This class is designed to instantiate a readWebsite object and calls various methods to read data from a website and answer questions
  * about that data. After these methods are called, Application calls a method that writes these answers to an output file. 
  * Application class only instantiates one readWebsite object and no other data types. 
  *
  * @throws an IOException if readData/writeFile methods produce file exceptions 
  * @author Kyle Davis */

public class Application {
	public static void main (String args[]) throws IOException {
		/** The name of the readWebsite object */
		readWebsite data = new readWebsite();
		/** Calls to readWebsite's methods */
		data.readData();
		data.listRTowns();
		data.listCounties();
		data.listTypes();
		data.totalCounties();
		data.zipCodes("PORTSMOUTH");
		data.writeFile();
	}
}