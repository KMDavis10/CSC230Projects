/** Kyle Davis
  * 3/24/14 
  * Project 2 - Three Card Poker Simulator  
  * CSC230 - Dr. DePasquale 
  */
import java.util.*;
import java.io.*;
import jsjf.ArrayStack;

/** The Dealer class is a child of Player & therefore implements Comparable. It is designed to create a "deck"
  * of cards, distribute those cards into hands, give the hands to players and compare each player's hand to 
  * the dealer's hand to determine if they won, lost or pushed with the dealer. 
  *
  * @author Kyle Davis
  */
public class Dealer extends Player {

	/** stack of PlayingCard objects - set to 52 since we know how many cards to use */
	ArrayStack<PlayingCard> stack = new ArrayStack<PlayingCard>(52); 
	
	/** Creates 52 PlayingCard objects and places them in an array. The cards in the array positions are then
	  * "shuffled" and pushed onto a stack. The PlayingCards are then popped into Hand objects and given to 
	  * each Player. Each Player object is then compared to the Dealer and determines if they won, lost or pushed. 
	  * 
	  * @throws IOException in case there are problems writing exceptions to the text file 
	  */
	public void deal() throws IOException {
		try {
			/** the String variable name is taken from Player and is set to "Dealer" */
			name = "Dealer";
			/** calls the shuffleDeck method to create, and shuffle an array of PlayingCard objects and populate the stack */
			this.shuffleDeck();
			/** Hand hand1 - parameters are PlayingCard objects popped from stack */
			Hand hand1 = new Hand (stack.pop(), stack.pop(), stack.pop());
			/** Hand hand2 - parameters are PlayingCard objects popped from stack */
			Hand hand2 = new Hand (stack.pop(), stack.pop(), stack.pop());
			/** Hand hand3 - parameters are PlayingCard objects popped from stack */
			Hand hand3 = new Hand (stack.pop(), stack.pop(), stack.pop());
			/** Hand hand4 - parameters are PlayingCard objects popped from stack */
			Hand hand4 = new Hand (stack.pop(), stack.pop(), stack.pop());
			/** hand - declared in the Dealer's parent and initialized in Dealer with PlayingCard objects popped from stack */
			hand = new Hand (stack.pop(), stack.pop(), stack.pop());
			/** Player Bob - first Player against the Dealer*/
			Player Bob = new Player();
			/** Player Sue - second Player against the Dealer*/
			Player Sue = new Player();
			/** Player Steve - third Player against the Dealer */ 
			Player Steve = new Player ();
			/** Player Joe - fourth Player against the Dealer */
			Player Joe = new Player();
			/** Sets Hands & Names for all of the Players */
			Bob.setHand(hand1);
			Bob.setName("Bob");
			Sue.setHand(hand2);
			Sue.setName("Sue");
			Steve.setHand(hand3);
			Steve.setName("Steve");
			Joe.setHand(hand4);
			Joe.setName("Joe");
			/** players - array of type Player to store the Player objects for comparison in the loop */
			Player [] players = new Player[4];
			players[0] = Bob;
			players[1] = Sue;
			players[2] = Steve;
			players[3] = Joe;
			/** Loop for comparing each of the Players to the Dealer */
			for (int i = 0; i < 4; i++) {
				/** local String variable handRanking contains the result of the game for one Player */
				String handRanking = players[i].getHand().result();
				/** If the Player is equal to the Dealer (their Hands are the same), then it is a push */
				if (this.compareTo(players[i]) == 0) {
					System.out.println("Player " + (i + 1) + " " + players[i] + "\t" + "(push " + handRanking);
				}
				/** If the Dealer is less than the Player (Player Hand is better), then the Player wins */
				else if (this.compareTo(players[i]) == -1) {
					System.out.println("Player " + (i + 1) + " " + players[i] + "\t" + "(won " + handRanking);
				}
				/** If the Dealer is greater than the Player (Dealer Hand is better), then the Player loses */
				else if (this.compareTo(players[i]) == 1) {
					System.out.println("Player " + (i + 1) + " " + players[i] + "\t" + "(lost " + handRanking);
				}
			}
			System.out.println("Dealer (" + name + "): \t" + hand);	
		}
		/** catches any kind of exception the Deal method could throw */
		catch (Exception e) {
			/** PrintWriter object - made global so each catch statement can write to the file */
			PrintWriter outFile;
			/** String object - made global so each catch statement can knows what file to write to */
			String file = "errors.txt";
			outFile = new PrintWriter(new BufferedWriter(new FileWriter(file)));
			outFile.println("The code has thrown a(n) " + e.getClass().getSimpleName() + " exception");
			e.printStackTrace(outFile);
			
			/** if an exception was thrown, then the exception file is closed */
			outFile.close();
			 
		}
		
	}
	/** getHand returns the Dealer's hand 
	  *
	  * @return the Dealer's hand */
	public Hand getHand() {
		return hand;
	}
	/** compareTo returns the int that Hand's compareTo provides
	  *
	  * @return Hand's compareTo 
	  */
	public int compareTo(Player play) {
		return hand.compareTo(play.getHand());
	} 
	
	/** shuffleDeck creates a PlayingCard array, shuffles it and populates stack with the shuffled elements */
	private void shuffleDeck() {
		PlayingCard[] deck = new PlayingCard[52];
			/** int count is an index for deck */
			int count = 0;
			/** 4 seperate for loops create PlayingCard objects of a particular suit and place them in deck */
			for (int i = 2; i < 15; i++) {
				/** local String variable suit stores H - hearts */
				String suit = "H";
				/** a new PlayingCard object is instantiated & given a rank of 2-14 and is placed into the array */
				deck[count] = new PlayingCard(i, suit);
				count++;
			}
			for (int i = 2; i < 15; i++) {
				/** local String variable suit stores S - spades */
				String suit = "S";
				/** a new PlayingCard object is instantiated & given a rank of 2-14 and is placed into the array */
				deck[count] = new PlayingCard(i, suit);
				count++;
			}
			for (int i = 2; i < 15; i++) {
				/** local String variable suit stores C - clubs */
				String suit = "C";
				/** a new PlayingCard object is instantiated & given a rank of 2-14 and is placed into the array */
				deck[count] = new PlayingCard(i, suit);
				count++;
			}
			for (int i = 2; i < 15; i++) {
				/** local String variable suit stores d - diamonds */
				String suit = "D";
				/** a new PlayingCard object is instantiated & given a rank of 2-14 and is placed into the array */
				deck[count] = new PlayingCard(i, suit);
				count++;
			}
			/** Random object rand - used for shuffling the deck */
			Random rand = new Random();
			/** algorithm goes from the last position in the array and  */
			for (int i = 0; i < 51; i++) {
				/** int rn is set to a random number from 1-51, then 2-51, then 3-51, etc. */
				int rn = rand.nextInt(51-i) + i+1;
				/** PlayingCard temp is a temporary PlayingCard object used to store the PlayingCard in the random 
				  * position of the array
				  */
				PlayingCard temp;
				/** the positions are switched */
				temp = deck[rn];
				deck[rn] = deck[i];
				deck[i] = temp;
			}
			/** the elements are pushed onto the stack */
			for (int i = 0; i < 52; i++) {
				stack.push(deck[i]);
		    }
			
	}
	
}