import java.util.*;
public class Test {
	public static void main (String args[]) {
		String name = "Dealer";
		Hand dealerHand;
		playingCard[] deck = new playingCard[52];
		int count = 0;
		for (int i = 2; i < 15; i++) {
			String suit = "H";
			deck[count] = new playingCard(i, suit);
			count++;
		}
		for (int i = 2; i < 15; i++) {
			String suit = "S";
			deck[count] = new playingCard(i, suit);
			count++;
		}
		for (int i = 2; i < 15; i++) {
			String suit = "C";
			deck[count] = new playingCard(i, suit);
			count++;
		}
		for (int i = 2; i < 15; i++) {
			String suit = "D";
			deck[count] = new playingCard(i, suit);
			count++;
		}
		Random rand = new Random();
		for (int i = 51; i >= 0; i--) {
			int rn = rand.nextInt(i+1);
			playingCard temp;
			temp = deck[rn];
			deck[rn] = deck[i];
			deck[i] = temp;
		}
		//for (int i = 0; i < 52; i++) {
			//System.out.println(deck[i]);
		//}
		ArrayStack<playingCard> stack = new ArrayStack<playingCard>();
		for (int i = 0; i < 52; i++) {
			stack.push(deck[i]);
	    }
		//stack.push(deck[0]);
		//System.out.println(stack.pop());
		
		Hand hand1 = new Hand (stack.pop(), stack.pop(), stack.pop());
		Hand hand2 = new Hand (stack.pop(), stack.pop(), stack.pop());
		Hand hand3 = new Hand (stack.pop(), stack.pop(), stack.pop());
		Hand hand4 = new Hand (stack.pop(), stack.pop(), stack.pop());
		dealerHand = new Hand (stack.pop(), stack.pop(), stack.pop());
		Player Bob = new Player();
		Player Sue = new Player();
		Player Steve = new Player ();
		Player Joe = new Player();
		Bob.setHand(hand1);
		Bob.setName("Bob");
		Sue.setHand(hand2);
		Sue.setName("Sue");
		Steve.setHand(hand3);
		Steve.setName("Steve");
		Joe.setHand(hand4);
		Joe.setName("Joe");
		System.out.println(name + ": " + dealerHand);
		
		if (dealerHand.compareTo(Bob.getHand()) == 0 && Bob.getHand().rankHand() == 100) {
			System.out.println(Bob + " (push with a straight flush)");
		}
		if (dealerHand.compareTo(Bob.getHand()) == 1 && Bob.getHand().rankHand() == 90) {
			System.out.println(Bob + " Lost to the dealer with 3 of a kind");
		}
		if (dealerHand.compareTo(Bob.getHand()) == 1 && Bob.getHand().rankHand() == 80) {
			System.out.println(Bob + " Lost to the dealer with a straight");
		}
		if (dealerHand.compareTo(Bob.getHand()) == 1 && Bob.getHand().rankHand() == 70) {
			System.out.println(Bob + " Lost to the dealer with a flush");
		}
		if (dealerHand.compareTo(Bob.getHand()) == 1 && Bob.getHand().rankHand() < 70 && Bob.getHand().rankHand() > 30) {
			System.out.println(Bob + " Lost to the dealer with a pair of " + (Bob.getHand().rankHand() - 30) + "s");
		}
		if (dealerHand.compareTo(Bob.getHand()) == 1 && Bob.getHand().rankHand() < 30) {
			System.out.println(Bob + " Lost to the dealer with a " + (Bob.getHand().rankHand() - 10) + " high card");
		}
		if (dealerHand.compareTo(Bob.getHand()) == -1 && Bob.getHand().rankHand() == 100) {
			System.out.println(Bob + " Beat the dealer with a straight flush");
		}
		if (dealerHand.compareTo(Bob.getHand()) == -1 && Bob.getHand().rankHand() == 90) {
			System.out.println(Bob + " Beat the dealer with 3 of a kind");
		}
		if (dealerHand.compareTo(Bob.getHand()) == -1 && Bob.getHand().rankHand() == 80) {
			System.out.println(Bob + " Beat the dealer with a straight");
		}
		if (dealerHand.compareTo(Bob.getHand()) == -1 && Bob.getHand().rankHand() == 70) {
			System.out.println(Bob + " Beat the dealer with a flush");
		}
		if (dealerHand.compareTo(Bob.getHand()) == -1 && Bob.getHand().rankHand() < 70 && Bob.getHand().rankHand() > 30) {
			System.out.println(Bob + " Beat the dealer with a pair of " + (Bob.getHand().rankHand() - 30) + "s");
		}
		if (dealerHand.compareTo(Bob.getHand()) == -1 && Bob.getHand().rankHand() < 30) {
			System.out.println(Bob + " Beat the dealer with a " + (Bob.getHand().rankHand() - 10) + " high card");
		}
		if (dealerHand.compareTo(Bob.getHand()) == 0 && Bob.getHand().rankHand() == 90) {
			System.out.println(Bob + " Push with 3 of a kind");
		}
		if (dealerHand.compareTo(Bob.getHand()) == 0 && Bob.getHand().rankHand() == 80) {
			System.out.println(Bob + " Push with a straight");
		}
		if (dealerHand.compareTo(Bob.getHand()) == 0 && Bob.getHand().rankHand() == 70) {
			System.out.println(Bob + " Push with a flush");
		}
		if (dealerHand.compareTo(Bob.getHand()) == 0 && Bob.getHand().rankHand() < 70 && Bob.getHand().rankHand() > 30) {
			System.out.println(Bob + " Push with a pair of " + (Bob.getHand().rankHand() - 30) + "s");
		}
		if (dealerHand.compareTo(Sue.getHand()) == 0 && Sue.getHand().rankHand() == 100) {
			System.out.println(Sue + " (push with a straight flush)");
		}
		if (dealerHand.compareTo(Sue.getHand()) == 1 && Sue.getHand().rankHand() == 90) {
			System.out.println(Sue + " Lost to the dealer with 3 of a kind");
		}
		if (dealerHand.compareTo(Sue.getHand()) == 1 && Sue.getHand().rankHand() == 80) {
			System.out.println(Sue + " Lost to the dealer with a straight");
		}
		if (dealerHand.compareTo(Sue.getHand()) == 1 && Sue.getHand().rankHand() == 70) {
			System.out.println(Sue + " Lost to the dealer with a flush");
		}
		if (dealerHand.compareTo(Sue.getHand()) == 1 && Sue.getHand().rankHand() < 70 && Sue.getHand().rankHand() > 30) {
			System.out.println(Sue + " Lost to the dealer with a pair of " + (Sue.getHand().rankHand() - 30) + "s");
		}
		if (dealerHand.compareTo(Sue.getHand()) == 1 && Sue.getHand().rankHand() < 30) {
			System.out.println(Sue + " Lost to the dealer with a " + (Sue.getHand().rankHand() - 10) + " high card");
		}
		if (dealerHand.compareTo(Sue.getHand()) == -1 && Sue.getHand().rankHand() == 100) {
			System.out.println(Sue + " Beat the dealer with a straight flush");
		}
		if (dealerHand.compareTo(Sue.getHand()) == -1 && Sue.getHand().rankHand() == 90) {
			System.out.println(Sue + " Beat the dealer with 3 of a kind");
		}
		if (dealerHand.compareTo(Sue.getHand()) == -1 && Sue.getHand().rankHand() == 80) {
			System.out.println(Sue + " Beat the dealer with a straight");
		}
		if (dealerHand.compareTo(Sue.getHand()) == -1 && Sue.getHand().rankHand() == 70) {
			System.out.println(Sue + " Beat the dealer with a flush");
		}
		if (dealerHand.compareTo(Sue.getHand()) == -1 && Sue.getHand().rankHand() < 70 && Sue.getHand().rankHand() > 30) {
			System.out.println(Sue + " Beat the dealer with a pair of " + (Sue.getHand().rankHand() - 30) + "s");
		}
		if (dealerHand.compareTo(Sue.getHand()) == -1 && Sue.getHand().rankHand() < 30) {
			System.out.println(Sue + " Beat the dealer with a " + (Sue.getHand().rankHand() - 10) + " high card");
		}
		if (dealerHand.compareTo(Sue.getHand()) == 0 && Sue.getHand().rankHand() == 90) {
			System.out.println(Sue + " Push with 3 of a kind");
		}
		if (dealerHand.compareTo(Sue.getHand()) == 0 && Sue.getHand().rankHand() == 80) {
			System.out.println(Sue + " Push with a straight");
		}
		if (dealerHand.compareTo(Sue.getHand()) == 0 && Sue.getHand().rankHand() == 70) {
			System.out.println(Sue + " Push with a flush");
		}
		if (dealerHand.compareTo(Sue.getHand()) == 0 && Sue.getHand().rankHand() < 70 && Sue.getHand().rankHand() > 30) {
			System.out.println(Sue + " Push with a pair of " + (Sue.getHand().rankHand() - 30) + "s");
		}
		if (dealerHand.compareTo(Sue.getHand()) == 0 && Sue.getHand().rankHand() < 30) {
			System.out.println(Sue + " Push with a " + (Sue.getHand().rankHand() - 10) + " high card");
		}
		if (dealerHand.compareTo(Steve.getHand()) == 0 && Steve.getHand().rankHand() == 100) {
			System.out.println(Steve + " (push with a straight flush)");
		}
		if (dealerHand.compareTo(Steve.getHand()) == 1 && Steve.getHand().rankHand() == 90) {
			System.out.println(Steve + " Lost to the dealer with 3 of a kind");
		}
		if (dealerHand.compareTo(Steve.getHand()) == 1 && Steve.getHand().rankHand() == 80) {
			System.out.println(Steve + " Lost to the dealer with a straight");
		}
		if (dealerHand.compareTo(Steve.getHand()) == 1 && Steve.getHand().rankHand() == 70) {
			System.out.println(Steve + " Lost to the dealer with a flush");
		}
		if (dealerHand.compareTo(Steve.getHand()) == 1 && Steve.getHand().rankHand() < 70 && Steve.getHand().rankHand() > 30) {
			System.out.println(Steve + " Lost to the dealer with a pair of " + (Steve.getHand().rankHand() - 30) + "s");
		}
		if (dealerHand.compareTo(Steve.getHand()) == 1 && Steve.getHand().rankHand() < 30) {
			System.out.println(Steve + " Lost to the dealer with a " + (Steve.getHand().rankHand() - 10) + " high card");
		}
		if (dealerHand.compareTo(Steve.getHand()) == -1 && Steve.getHand().rankHand() == 100) {
			System.out.println(Steve + " Beat the dealer with a straight flush");
		}
		if (dealerHand.compareTo(Steve.getHand()) == -1 && Steve.getHand().rankHand() == 90) {
			System.out.println(Steve + " Beat the dealer with 3 of a kind");
		}
		if (dealerHand.compareTo(Steve.getHand()) == -1 && Steve.getHand().rankHand() == 80) {
			System.out.println(Steve + " Beat the dealer with a straight");
		}
		if (dealerHand.compareTo(Steve.getHand()) == -1 && Steve.getHand().rankHand() == 70) {
			System.out.println(Steve + " Beat the dealer with a flush");
		}
		if (dealerHand.compareTo(Steve.getHand()) == -1 && Steve.getHand().rankHand() < 70 && Steve.getHand().rankHand() > 30) {
			System.out.println(Steve + " Beat the dealer with a pair of " + (Steve.getHand().rankHand() - 30) + "s");
		}
		if (dealerHand.compareTo(Steve.getHand()) == -1 && Steve.getHand().rankHand() < 30) {
			System.out.println(Steve + " Beat the dealer with a " + (Steve.getHand().rankHand() - 10) + " high card");
		}
		if (dealerHand.compareTo(Steve.getHand()) == 0 && Steve.getHand().rankHand() == 90) {
			System.out.println(Steve + " Push with 3 of a kind");
		}
		if (dealerHand.compareTo(Steve.getHand()) == 0 && Steve.getHand().rankHand() == 80) {
			System.out.println(Steve + " Push with a straight");
		}
		if (dealerHand.compareTo(Steve.getHand()) == 0 && Steve.getHand().rankHand() == 70) {
			System.out.println(Steve + " Push with a flush");
		}
		if (dealerHand.compareTo(Steve.getHand()) == 0 && Steve.getHand().rankHand() < 70 && Steve.getHand().rankHand() > 30) {
			System.out.println(Steve + " Push with a pair of " + (Steve.getHand().rankHand() - 30) + "s");
		}
		if (dealerHand.compareTo(Steve.getHand()) == 0 && Steve.getHand().rankHand() < 30) {
			System.out.println(Steve + " Push with a " + (Steve.getHand().rankHand() - 10) + " high card");
		}
		if (dealerHand.compareTo(Joe.getHand()) == 0 && Joe.getHand().rankHand() == 100) {
			System.out.println(Joe + " (push with a straight flush)");
		}
		if (dealerHand.compareTo(Joe.getHand()) == 1 && Joe.getHand().rankHand() == 90) {
			System.out.println(Joe + " Lost to the dealer with 3 of a kind");
		}
		if (dealerHand.compareTo(Joe.getHand()) == 1 && Joe.getHand().rankHand() == 80) {
			System.out.println(Joe + " Lost to the dealer with a straight");
		}
		if (dealerHand.compareTo(Joe.getHand()) == 1 && Joe.getHand().rankHand() == 70) {
			System.out.println(Joe + " Lost to the dealer with a flush");
		}
		if (dealerHand.compareTo(Joe.getHand()) == 1 && Joe.getHand().rankHand() < 70 && Joe.getHand().rankHand() > 30) {
			System.out.println(Joe + " Lost to the dealer with a pair of " + (Joe.getHand().rankHand() - 30) + "s");
		}
		if (dealerHand.compareTo(Joe.getHand()) == 1 && Joe.getHand().rankHand() < 30) {
			System.out.println(Joe + " Lost to the dealer with a " + (Joe.getHand().rankHand() - 10) + " high card");
		}
		if (dealerHand.compareTo(Joe.getHand()) == -1 && Joe.getHand().rankHand() == 100) {
			System.out.println(Joe + " Beat the dealer with a straight flush");
		}
		if (dealerHand.compareTo(Joe.getHand()) == -1 && Joe.getHand().rankHand() == 90) {
			System.out.println(Joe + " Beat the dealer with 3 of a kind");
		}
		if (dealerHand.compareTo(Joe.getHand()) == -1 && Joe.getHand().rankHand() == 80) {
			System.out.println(Joe + " Beat the dealer with a straight");
		}
		if (dealerHand.compareTo(Joe.getHand()) == -1 && Joe.getHand().rankHand() == 70) {
			System.out.println(Joe + " Beat the dealer with a flush");
		}
		if (dealerHand.compareTo(Joe.getHand()) == -1 && Joe.getHand().rankHand() < 70 && Joe.getHand().rankHand() > 30) {
			System.out.println(Joe + " Beat the dealer with a pair of " + (Joe.getHand().rankHand() - 30) + "s");
		}
		if (dealerHand.compareTo(Joe.getHand()) == -1 && Joe.getHand().rankHand() < 30) {
			System.out.println(Joe + " Beat the dealer with a " + (Joe.getHand().rankHand() - 10) + " high card");
		}
		if (dealerHand.compareTo(Joe.getHand()) == 0 && Joe.getHand().rankHand() == 90) {
			System.out.println(Joe + " Push with 3 of a kind");
		}
		if (dealerHand.compareTo(Joe.getHand()) == 0 && Joe.getHand().rankHand() == 80) {
			System.out.println(Joe + " Push with a straight");
		}
		if (dealerHand.compareTo(Joe.getHand()) == 0 && Joe.getHand().rankHand() == 70) {
			System.out.println(Joe + " Push with a flush");
		}
		if (dealerHand.compareTo(Joe.getHand()) == 0 && Joe.getHand().rankHand() < 70 && Joe.getHand().rankHand() > 30) {
			System.out.println(Joe + " Push with a pair of " + (Joe.getHand().rankHand() - 30) + "s");
		}
		if (dealerHand.compareTo(Joe.getHand()) == 0 && Joe.getHand().rankHand() < 30) {
			System.out.println(Joe + " Push with a " + (Joe.getHand().rankHand() - 10) + " high card");
		}
	}
}