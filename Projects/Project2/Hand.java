import java.util.*;
public class Hand implements Comparable <Hand> {
	private playingCard card1;
	private playingCard card2;
	private playingCard card3;
	public Hand (playingCard eCard1, playingCard eCard2, playingCard eCard3) {
		card1 = eCard1;
		card2 = eCard2;
		card3 = eCard3;
		int [] temp = new int[3];
		temp[0] = card1.getRank();
		temp[1] = card2.getRank();
		temp[2] = card3.getRank();
		Arrays.sort(temp);
		if (temp[0] == card1.getRank()){
			if (temp[1] == card2.getRank()) {
					
			}
			else {
				card2 = eCard3;
				card3 = eCard2;
			}
		}
		else if (temp[0] == card2.getRank()) {
			if (temp[1] == card1.getRank()) {
				card1 = eCard2;
				card2 = eCard1;
			}
			else {
				card1 = eCard2;
				card2 = eCard3;
				card3 = eCard1;
			}
		}
		else {
			if (temp[1] == card1.getRank()) {
				card1 = eCard3;
				card2 = eCard1;
				card3 = eCard2;
			}
			else {
				card1 = eCard3;
				card3 = eCard1;
			}
		}
	}
	
	public int rankHand() {
		if (card2.getRank() == card1.getRank()+1 && card3.getRank() == card1.getRank()+2) {
			if (card1.getSuit() == card2.getSuit() && card1.getSuit() == card3.getSuit()) {
				return 100;
			}
			else {
				return 80;
			}
		}
		if (card1.getRank() == card2.getRank() && card1.getRank() == card3.getRank()){
			return 90;
		}
		if (card1.getSuit() == card2.getSuit() && card1.getSuit() == card3.getSuit()) {
			return 70;
		}
		if (card1.getRank() == card2.getRank() && card1.getRank() != card3.getRank()) {
			return card1.getRank() + 30;
		}
		if (card1.getRank() == card3.getRank() && card1.getRank() != card2.getRank()) {
			return card3.getRank() + 30;
		}
		if (card2.getRank() == card3.getRank() && card2.getRank() != card1.getRank()) {
			return card3.getRank() + 30;
		}
		if (card1.getRank() != card2.getRank() && card1.getRank() != card3.getRank() && card2.getRank() != card3.getRank()) {
			return card3.getRank() + 10;
		}
		else {
			return 0;
		}
	}
	
	public int compareTo(Hand playerHand) {
		if (this.rankHand() > playerHand.rankHand()) {
			return 1;
		}
		if (this.rankHand() < playerHand.rankHand()) {
			return -1;
		}
		if (this.rankHand() == playerHand.rankHand()){
			return 0;
		}
		else {
			return 0;
		}
	}
	public String toString () {
		return (card1 + " " + card2 + " " + card3);
	}
}