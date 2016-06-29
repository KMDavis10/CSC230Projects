public class Player implements Comparable <Player> {
	protected String name;
	protected Hand hand;
	public void setName(String eName) {
		name = eName;
	}
	public void setHand(Hand eHand) {
		hand = eHand;
	}
	public int compareTo(Player play) {
		return 0;
	}
	public Hand getHand() {
		return hand;
	}
	public String toString() {
		return ("Player " + "\t" + name + ":" + "\t\t" + hand);
	}
}