/** Kyle Davis
  * 3/24/14 
  * Project 2 - Three Card Poker Simulator  
  * CSC230 - Dr. DePasquale 
  */

/** The PlayingCard class is designed to represent a single playing card in a deck. It contains a rank variable and
  * a suit variable. It contains methods to give these variables to whatever class calls them. 
  *
  * @author Kyle Davis 
  */
public class PlayingCard {
	/** int rank - the card number of the PlayingCard */
	private int rank;
	/** String suit - the suit of the PlayingCard */
	private String suit;
	
	/** The constructor - takes in an int and a String as parameters and sets them equal to rank & suit, respectively */
	public PlayingCard(int eRank, String eSuit){
		rank = eRank;
		suit = eSuit;
	}
	
	/** The getRank method returns the rank of the card 
	  *
	  * @return PlayingCard's rank
	  */
	public int getRank() {
		return rank;
	}
	
	/** The getSuit method returns the suit of the card 
	  * 
	  * @return PlayingCard's suit  
	  */
	public String getSuit() {
		return suit;
	}
	
	/** The toString method returns a String representation of the PlayingCard object
	  *
	  * @return rank & suit concatenated together 
	  */
	public String toString(){
		/** if the PlayingCard is considered not a face card (rank 2-10), then it is concatenated as is
		  * if it is a face card, then its value is determined (jack, queen, king, etc) and then it is concatenated 
		  */
		if (rank < 11) {
			return (rank + suit);
		}
		if (rank == 11) {
			return ("J" + suit);
		}
		if (rank == 12) {
			return ("Q" + suit);
		}
		if (rank == 13) {
			return ("K" + suit);
		}
		if (rank == 14){
			return ("A" + suit);
		}
		/** This else should only be reached if something goes wrong */
		else { 
			return "There has been an error";
		}
	}
}