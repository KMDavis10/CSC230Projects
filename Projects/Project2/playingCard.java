public class playingCard {
	private int rank;
	private String suit;
	public playingCard(int eRank, String eSuit){
		rank = eRank;
		suit = eSuit;
	}
	public int getRank() {
		return rank;
	}
	public String getSuit() {
		return suit;
	}
	public String toString(){
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
		else { 
			return "ehh2";
		}
	}
}