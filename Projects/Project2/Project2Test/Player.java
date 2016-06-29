/** Kyle Davis
  * 3/24/14 
  * Project 2 - Three Card Poker Simulator  
  * CSC230 - Dr. DePasquale 
  */
  
/** The Player class implements Comparable and uses generics to limit what object compareTo takes in. 
  * Each Player class has a name and a Hand. The Player class utilizes compareTo to determine if it has 
  * won against the Dealer 
  * 
  * @author Kyle Davis
  */
public class Player implements Comparable <Player> {
	/** String name stores the name of the Player - it is protected so Dealer can use it */
	protected String name;
	/** Hand hand stores the Player's hand - it is protected so Dealer can use it */
	protected Hand hand;
	/** setName takes in a String value and sets it to name */
	public void setName(String eName) {
		name = eName;
	}
	/** setHand takes in a Hand object and sets it as hand */
	public void setHand(Hand eHand) {
		hand = eHand;
	}
	/** getHand returns the Hand it has been passed 
	  * @return hand */
	public Hand getHand() {
		return hand;
	}
	/** compareTo utilizes hand's compareTo and returns the int that it produces
	  * @return int hand's compareTo produces 
	  */
	public int compareTo(Player player) {
		return this.getHand().compareTo(player.getHand());
	}
	/** compareTo returns a String consisting of the Player's name and hand 
	  * @return String of name & hand 
	  */
	public String toString() {
		return ("(" + name + "): \t" + hand);
	}
}