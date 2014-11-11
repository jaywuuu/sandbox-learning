
public class Card {
	public enum Suit{
		DIAMOND(1), CLUBS(2), HEARTS(3), SPADE(4);
		int value;
		private Suit(int val) {
			value = val;
		}
	}
	
	private int value;
	private Suit suit;
	
	public Card(int val, Suit s) {
		value = val;
		suit = s;
	}
	
	public int getValue() {
		return value;
	}
	
	public Suit getSuit() {
		return suit;
	}
}
