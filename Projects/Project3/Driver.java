/** Kyle Davis
  * 3/24/14 
  * Project 3 - Red Light Simulator  
  * CSC230 - Dr. DePasquale 
  */
  
/** The Driver class provides a minimal main method which instantiates a new Simulator object and calls its simulationStart method
  *
  * @author Kyle Davis
  */
public class Driver {
	public static void main (String[] args) {
		/** runTest - a Simulator object that is created to call its simulationStart method */
		Simulator runTest = new Simulator();
		runTest.simulationStart();
	}
}