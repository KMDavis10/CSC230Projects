public class Dealer extends Player {
	public void deal() {
		super.name = "Dealer";
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
		
		ArrayStack<playingCard> stack = new ArrayStack<playingCard>();
		for (int i = 0; i < 52; i++) {
			stack.push(deck[i]);
	    }
		
		Hand hand1 = new Hand (stack.pop(), stack.pop(), stack.pop());
		Hand hand2 = new Hand (stack.pop(), stack.pop(), stack.pop());
		Hand hand3 = new Hand (stack.pop(), stack.pop(), stack.pop());
		Hand hand4 = new Hand (stack.pop(), stack.pop(), stack.pop());
		hand = new Hand (stack.pop(), stack.pop(), stack.pop());
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
		Player [] players = new Player[4];
		players[0] = Bob;
		players[1] = Sue;
		players[2] = Steve;
		players[3] = Joe;
		System.out.println(name + ": " + hand);
		for (int i = 0; i < 4; i++) {
			if (hand.compareTo(players[i].getHand()) == 0 && players[i].getHand().rankHand() > 150) {
				System.out.println(players[i] +"\t" + " (Push with a straight flush)");
			}
			else if (hand.compareTo(players[i].getHand()) == 1 && players[i].getHand().rankHand() > 150) {
				System.out.println(players[i] + "\t" + " (Lost with a straight flush)");
			}
			else if (hand.compareTo(players[i].getHand()) == 1 && players[i].getHand().rankHand() > 100 && players[i].getHand().rankHand() < 150) {
				System.out.println(players[i] + "\t" + " (Lost to the dealer with 3 " + (players[i].getHand().rankHand() - 100) + "s)");
			}
			else if (hand.compareTo(players[i].getHand()) == 1 && players[i].getHand().rankHand() > 80 && players[i].getHand().rankHand() < 100) {
				System.out.println(players[i] + "\t" + " (Lost to the dealer with a straight)");
			}
			else if (hand.compareTo(players[i].getHand()) == 1 && players[i].getHand().rankHand() > 60 && players[i].getHand().rankHand() < 80) {
				System.out.println(players[i] + "\t" + " (Lost to the dealer with a flush)");
			}
			else if (hand.compareTo(players[i].getHand()) == 1 && players[i].getHand().rankHand() < 70 && players[i].getHand().rankHand() > 30) {
				if (players[i].getHand().rankHand() - 30 == 14) {
					System.out.println(players[i] + "\t" + " (Lost to the dealer with a pair of aces)");
				}
				else if (players[i].getHand().rankHand() - 30 == 13) {
					System.out.println(players[i] + "\t" + " (Lost to the dealer with a pair of kings)");
				}
				else if (players[i].getHand().rankHand() - 30 == 12) {
					System.out.println(players[i] + "\t" + " (Lost to the dealer with a pair of queens)");
				}
				else if (players[i].getHand().rankHand() - 30 == 11) {
					System.out.println(players[i] + "\t" + " (Lost to the dealer with a pair of jacks)");
				}
				else {
					System.out.println(players[i] + "\t" + " (Lost to the dealer with a pair of " + (players[i].getHand().rankHand() - 30) + "s)");
				}
			}
			else if (hand.compareTo(players[i].getHand()) == 1 && players[i].getHand().rankHand() < 30) {
				if (players[i].getHand().rankHand() - 10 == 14) {
					System.out.println(players[i] + "\t" + "(Lost to the dealer with an ace high card)");
				}
				else if (players[i].getHand().rankHand() - 10 == 13) {
					System.out.println(players[i] + "\t" + " (Lost to the dealer with a king high card)");
				}
				else if (players[i].getHand().rankHand() - 10 == 12) {
					System.out.println(players[i] + "\t" + " (Lost to the dealer with a queen high card)");
				}
				else if (players[i].getHand().rankHand() - 10 == 11) {
					System.out.println(players[i] + "\t" + " (Lost to the dealer with a jack high card)");
				}
				else {
					System.out.println(players[i] + "\t" + " (Lost to the dealer with a " + (players[i].getHand().rankHand() - 10) + " high card)");
				}
			}
			else if (hand.compareTo(players[i].getHand()) == -1 && players[i].getHand().rankHand() > 150) {
				System.out.println(players[i] + "\t" + " (Beat the dealer with a straight flush)");
			}
			else if (hand.compareTo(players[i].getHand()) == -1 && players[i].getHand().rankHand() > 100 && players[i].getHand().rankHand() < 150) {
				System.out.println(players[i] + "\t" + " (Beat the dealer with 3 " + (players[i].getHand().rankHand() - 100) + "s)");
			}
			else if (hand.compareTo(players[i].getHand()) == -1 && players[i].getHand().rankHand() > 80 && players[i].getHand().rankHand() < 100) {
				System.out.println(players[i] + "\t" + " (Beat the dealer with a straight)");
			}
			else if (hand.compareTo(players[i].getHand()) == -1 && players[i].getHand().rankHand() > 60 && players[i].getHand().rankHand() < 80) {
				System.out.println(players[i] + "\t" + " (Beat the dealer with a flush)");
				
			}
			else if (hand.compareTo(players[i].getHand()) == -1 && players[i].getHand().rankHand() < 70 && players[i].getHand().rankHand() > 30) {
				if (players[i].getHand().rankHand() - 30 == 14) {
					System.out.println(players[i] + "\t" + " (Beat the dealer with a pair of aces)");
				}
				else if (players[i].getHand().rankHand() - 30 == 13) {
					System.out.println(players[i] + "\t" + " (Beat the dealer with a pair of kings)");
				}
				else if (players[i].getHand().rankHand() - 30 == 12) {
					System.out.println(players[i] + "\t" + " (Beat the dealer with a pair of queens)");
				}
				else if (players[i].getHand().rankHand() - 30 == 11) {
					System.out.println(players[i] + "\t" + " (Beat to the dealer with a pair of jacks)");
				}
				else {
					System.out.println(players[i] + "\t" + " (Beat the dealer with a pair of " + (players[i].getHand().rankHand() - 30) + "s)");
				}
				
			}
			else if (hand.compareTo(players[i].getHand()) == -1 && players[i].getHand().rankHand() < 30) {
				if (players[i].getHand().rankHand() - 10 == 14) {
					System.out.println(players[i] + "\t" + " (Beat the dealer with an ace high card)");
				}
				else if (players[i].getHand().rankHand() - 10 == 13) {
					System.out.println(players[i] + "\t" + " (Beat the dealer with a king high card)");
				}
				else if (players[i].getHand().rankHand() - 10 == 12) {
					System.out.println(players[i] + "\t" + " (Beat the dealer with a queen high card)");
				}
				else if (players[i].getHand().rankHand() - 10 == 11) {
					System.out.println(players[i] + "\t" + " (Beat the dealer with a jack high card)");
				}
				else {
					System.out.println(players[i] + "\t" + " (Beat the dealer with a " + (players[i].getHand().rankHand() - 10) + " high card)");
				}
			}
			else if (hand.compareTo(players[i].getHand()) == 0 && players[i].getHand().rankHand() > 100 && players[i].getHand().rankHand() < 150) {
				System.out.println(players[i] + "\t" + " (Push with 3 " + (players[i].getHand().rankHand() - 100) + "s)");
			}
			else if (hand.compareTo(players[i].getHand()) == 0 && players[i].getHand().rankHand() > 80 && players[i].getHand().rankHand() < 100) {
				System.out.println(players[i] + "\t" + " (Push with a straight)");
			}
			else if (hand.compareTo(players[i].getHand()) == 0 && players[i].getHand().rankHand() > 60 && players[i].getHand().rankHand() < 80) {
				System.out.println(players[i] + "\t" + " (Push with a flush)");
			}
			else if (hand.compareTo(players[i].getHand()) == 0 && players[i].getHand().rankHand() < 70 && players[i].getHand().rankHand() > 30) {
				if (players[i].getHand().rankHand() - 30 == 14) {
					System.out.println(players[i] + "\t" + " (Push with a pair of aces)");
				}
				else if (players[i].getHand().rankHand() - 30 == 13) {
					System.out.println(players[i] + "\t" + " (Push with a pair of kings)");
				}
				else if (players[i].getHand().rankHand() - 30 == 12) {
					System.out.println(players[i] + "\t" + " (Push with a pair of queens)");
				}
				else if (players[i].getHand().rankHand() - 30 == 11) {
					System.out.println(players[i] + "\t" + " (Push with a pair of jacks)");
				}
				else {
					System.out.println(players[i] + "\t" + " (Push with a " + (players[i].getHand().rankHand() - 30) + "s)");
				}
			}
			else if (hand.compareTo(players[i].getHand()) == 0 && players[i].getHand().rankHand() < 30) {
				if (players[i].getHand().rankHand() - 10 == 14) {
					System.out.println(players[i] + "\t" + " (Push with an ace high card)");
				}
				else if (players[i].getHand().rankHand() - 10 == 13) {
					System.out.println(players[i] + "\t" + " (Push with a king high card)");
				}
				else if (players[i].getHand().rankHand() - 10 == 12) {
					System.out.println(players[i] + "\t" + " (Push with a queen high card)");
				}
				else if (players[i].getHand().rankHand() - 10 == 11) {
					System.out.println(players[i] + "\t" + " (Push with a jack high card)");
				}
				else {
					System.out.println(players[i] + "\t" + " (Push with a " + (players[i].getHand().rankHand() - 10) + " high card)");
				}
			}
			else {
				System.out.println(players[i] + "\t" + " has a problem");
			}
		}
	}

}