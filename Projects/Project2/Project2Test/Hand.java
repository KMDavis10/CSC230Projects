/** Kyle Davis
  * 3/24/14 
  * Project 2 - Three Card Poker Simulator  
  * CSC230 - Dr. DePasquale 
  */
import java.util.*;

/** The class Hand implements Comparable and uses generics to limit what objects compareTo takes in. Each Hand object 
  * consists of 3 PlayingCard objects, which are placed in order; the Hand's ranking (straight flush, straight, etc).
  * is then determined and and the type of output to be sent back to Dealer is decided. compareTo is utilized to determine
  * if the current Hand is better, worse or equal to the one passed in 
  * 
  * @author Kyle Davis 
  */
public class Hand implements Comparable <Hand> {
	/** PlayingCard card1 - the first card in the Hand */
	private PlayingCard card1;
	/** PlayingCard card2 - the second card in the Hand */
	private PlayingCard card2;
	/** PlayingCard card3 - the third card in the Hand */
	private PlayingCard card3;
	/** Hand's constructor - takes in 3 PlayingCards as parameters. It then ranks the cards according to smallest, 
	  * middle and highest, based on the PlayingCards' ranks */
	public Hand (PlayingCard eCard1, PlayingCard eCard2, PlayingCard eCard3) {
		card1 = eCard1;
		card2 = eCard2;
		card3 = eCard3;
		/** A temporary array is used to store the values of each PlayingCard's rank */
		int [] temp = new int[3];
		temp[0] = card1.getRank();
		temp[1] = card2.getRank();
		temp[2] = card3.getRank();
		/** The temporary array is sorted low-high */
		Arrays.sort(temp);
		/** if the lowest int in the temporary array is the same as card1's rank */
		if (temp[0] == card1.getRank()){
			/** if the middle position in the temporary array is the same as card2's rank, then nothing needs to 
			  * be changed 
			  */
			if (temp[1] == card2.getRank()) {
					
			}
			/** else, we can determine that the highest rank is card2's and the middle rank is card3's 
			  * These PlayingCard values are then swapped, to ensure that they are ranked low-high
			  */
			else {
				card2 = eCard3;
				card3 = eCard2;
			}
		}
		/** if the lowest int in the temporary array is the same as card2's rank */
		else if (temp[0] == card2.getRank()) {
			/** if the middle position in the temporary array is the same as card1's rank, these two PlayingCards are 
			  * swapped. card3's rank remains the highest, so it does not need swapping */
			if (temp[1] == card1.getRank()) {
				card1 = eCard2;
				card2 = eCard1;
			}
			/** Else, we can determine all the positions are incorrect. card1's rank is the highest, so it becomes card3
			  * card2's is the lowest, so it becomes card1. card3's is the middle, so it becomes card2. */
			else {
				card1 = eCard2;
				card2 = eCard3;
				card3 = eCard1;
			}
		}
		/** else, we can assume the lowest int in the temporary array is the same as card3's rank */
		else {
			/** if the middle position in the temporary array is the same as card1's rank, all the positions are incorrect
			  * card1's rank is the middle, so it becomes card2. Card2's rank is the highest, so it becomes card3. Card3's
			  * rank is the lowest, so it becomes card1. */
			if (temp[1] == card1.getRank()) {
				card1 = eCard3;
				card2 = eCard1;
				card3 = eCard2;
			}
			/** else, card2 is in the correct place, and only card1 and card3 need to be swapped */
			else {
				card1 = eCard3;
				card3 = eCard1;
			}
		}
	}
	
	/** the rankHand method returns an int based on the type of PlayingCards Hand has 
	  * 
	  * @return an int based on the rank of the Hand 
	  */
	public int rankHand() {
		/** if the Hand has each card in sequential order */
		if (card2.getRank() == card1.getRank()+1 && card3.getRank() == card1.getRank()+2) {
			/** if the Hand has each card of the same Suit, then it is a straight flush 
			  * the Hand's highest card's value is added to the number 150 to show that is
			  * the highest rank of Hand possible
			  */ 
			if (card1.getSuit() == card2.getSuit() && card1.getSuit() == card3.getSuit()) {
				return card3.getRank() + 150;
			}
			/** else, is a straight, the third highest rank of Hand possible. 
			  * The Hand's highest card's value is added to the number 80. 
			  */
			else {
				return card3.getRank() + 80;
			}
		}
		/** if all the cards' ranks in the Hand are the same, then it is a three of a kind, 
		  * the second highest rank of Hand possible. The Hand's highest ranking card 
		  * (arbitrary, as they are all the same rank) is added to the number 100. 
		  */
		if (card1.getRank() == card2.getRank() && card1.getRank() == card3.getRank()){
			return card3.getRank() + 100;
		}
		/** if all of the cards' suits in the Hand are the same, then it is a flush, then fourth 
		  * highest rank of Hand possible. The Hand's highest ranking card is added to the number 60. 
		  */
		if (card1.getSuit() == card2.getSuit() && card1.getSuit() == card3.getSuit()) {
			return card3.getRank() + 60;
		}
		/** The following 2 if-statements determine if 2 of cards have ranks equal to each other. If they
		  * do, it is a pair, the fifth highest rank of Hand. One of the pair's ranking is added to the 
		  * number 30. */
		if (card1.getRank() == card2.getRank() && card1.getRank() != card3.getRank()) {
			return card1.getRank() + 30;
		}
		if (card2.getRank() == card3.getRank() && card2.getRank() != card1.getRank()) {
			return card2.getRank() + 30;
		}
		/** If none of the cards ranks are equal to each other, then it is a high card, the lowest rank of Hand. 
		  * The Hand's highest ranking card is added to the number 10 */
		if (card1.getRank() != card2.getRank() && card1.getRank() != card3.getRank() && card2.getRank() != card3.getRank()) {
			return card3.getRank() + 10;
		}
		/** final else should only be entered if something goes wrong */
		else {
			return 0;
		}
	}
	
	/** the result method returns a String based on the type of Hand it is
	  *
	  * @return a string stating "straight flush," "straight," etc. 
	  */
	public String result () {
		/** String handResult - variable local to this method that is to be given the value of the Hand's type */
		String handResult;
		if (card2.getRank() == card1.getRank()+1 && card3.getRank() == card1.getRank()+2) {
			/** determines if Hand ranking is a straight flush */
			if (card1.getSuit() == card2.getSuit() && card1.getSuit() == card3.getSuit()) {
				handResult = " with a straight flush)";
				return handResult;
				
			}
			/** determines if Hand ranking is a straight */
			else {
				handResult = " with a straight)";
				return handResult;
			}
		}
		/** Determines if Hand rank is a 3 of a kind */
		if (card1.getRank() == card2.getRank() && card1.getRank() == card3.getRank()){
			handResult = " with 3 ";
			/** If the Hand contains PlayingCards that are face cards & they are not straight flushes, straights or flushes,
		 	  * checking must be done to see what kind of face card they have. This value is then added to handResult */
			if (card3.getRank() == 14) {
				return handResult + "Aces)";
			}
			else if (card3.getRank() == 13) {
				return handResult + "Kings)";
			}
			else if (card3.getRank() == 12) {
				return handResult + "Queens)";
			}
			else if (card3.getRank() == 11) {
				return handResult + "Jacks)";
			}
			/** if it does not contain any face cards, then the normal rank is added to handResult */
			else {
				return handResult + card3.getRank() + "s)";
			}
		}
		/** Determines if Hand rank is a flush */
		if (card1.getSuit() == card2.getSuit() && card1.getSuit() == card3.getSuit()) {
			handResult = "with a flush)";
			return handResult;
		}
		/** Determines if Hand rank is a pair */
		if (card1.getRank() == card2.getRank() && card1.getRank() != card3.getRank()) {
			handResult = "with a pair of ";
			/** If the Hand contains PlayingCards that are face cards & they are not straight flushes, straights or flushes,
		 	  * checking must be done to see what kind of face card they have. This value is then added to handResult */
			if (card1.getRank() == 14) {
				return handResult + "Aces)";
			}
			else if (card1.getRank() == 13) {
				return handResult + "Kings)";
			}
			else if (card1.getRank() == 12) {
				return handResult + "Queens)";
			}
			else if (card1.getRank() == 11) {
				return handResult + "Jacks)";
			}
			else {
				return handResult + card1.getRank() + "s)";
			}
		}
		if (card2.getRank() == card3.getRank() && card2.getRank() != card1.getRank()) {
			handResult = "with a pair of ";
			/** If the Hand contains PlayingCards that are face cards & they are not straight flushes, straights or flushes,
		 	  * checking must be done to see what kind of face card they have. This value is then added to handResult */
			if (card3.getRank() == 14) {
				return handResult + "Aces)";
			}
			else if (card3.getRank() == 13) {
				return handResult + "Kings)";
			}
			else if (card3.getRank() == 12) {
				return handResult + "Queens)";
			}
			else if (card3.getRank() == 11) {
				return handResult + "Jacks)";
			}
			else {
				return handResult + card3.getRank() + "s)";
			}
		}
		/** Determines if Hand Rank is a high card */
		if (card1.getRank() != card2.getRank() && card1.getRank() != card3.getRank() && card2.getRank() != card3.getRank()) {
			handResult = "with ";
			/** If the Hand contains PlayingCards that are face cards & they are not straight flushes, straights or flushes,
		 	  * checking must be done to see what kind of face card they have. This value is then added to handResult */
			if (card3.getRank() == 14) {
				return handResult + "an Ace high card)";
			}
			else if (card3.getRank() == 13) {
				return handResult + "a King high card)";
			}
			else if (card3.getRank() == 12) {
				return handResult + "a Queen high card)";
			}
			else if (card3.getRank() == 11) {
				return handResult + "a Jack high card)";
			}
			else {
				return handResult + card3.getRank() + " high card)";
			}
		}
		/** Only reaches this else if something goes wrong */
		else {
			return "There has been an error. ";
		}
	}
	/** compareTo compares the current hand's rank with the rank of the Hand passed in 
	  * 
	  * @return 1, -1, or 0, based on whether or not the current hand ranked highest, lower or equal to the one passed in 
	  */
	public int compareTo(Hand playerHand) {
		if (this.rankHand() > playerHand.rankHand()) {
			return 1;
		}
		if (this.rankHand() < playerHand.rankHand()) {
			return -1;
		}
		if (this.rankHand() == playerHand.rankHand()) {
			return 0;
		}
		/** Only reaches this else if something goes wrong */
		else {
			return 100;
		}
	}
	
	/** toString returns the each PlayingCard object seperated by spaces 
	  * 
	  * @return the PlayingCards 
	  */
	public String toString () {
		return (card1 + " " + card2 + " " + card3);
	}
}