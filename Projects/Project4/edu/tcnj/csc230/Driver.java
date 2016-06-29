/** Kyle Davis
  * 4/30/14
  * Project 4 - Hashtables 
  * CSC230 - Dr. DePasquale 
  */
package edu.tcnj.csc230;
import java.io.*;

/** This class is designed to create a minimal Driver which creates a Processor object and calls its process method which 
  * gets information from a file & stores it in a hashtable so the user can get information about it
  *
  * @author Kyle Davis
  */
public class Driver {
	public static void main (String[] args) {
		/** Processor run - a Processor object created to call its process method */
		Processor run = new Processor();
		run.process();
	}
}