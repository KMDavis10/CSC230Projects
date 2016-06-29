/** Kyle Davis
  * 3/24/14 
  * Project 2 - Three Card Poker Simulator  
  * CSC230 - Dr. DePasquale 
  */
import java.io.*;

/** The Driver class provides a minimal main method which instaniates a Dealer object and calls its "deal" method 
  *
  * @throws IOException if Dealer produces a file exception when writing possible exceptions to a text file 
  * @author Kyle Davis 
  */
public class Driver {
	public static void main (String args[]) throws IOException {
		/** dealer - name of Dealer type object that is created to call its deal method */
		Dealer dealer = new Dealer();
		dealer.deal();
	}
}