package videopoker.game;

public class Card {
	
	private Value value;
	private Suit suit;
	
	public enum Value {
        TWO('2', 2), THREE('3', 3), FOUR('4', 4), FIVE('5', 5),
        SIX('6', 6), SEVEN('7', 7),EIGHT('8', 8), NINE('9', 9),
        TEN('T', 10), JACK('J', 11), QUEEN('Q', 12), KING('K', 13),
        ACE('A', 14);
		
        private char value;
        private int weight;
        

        private Value(char value, int weight) {
                this.value = value;
                this.weight = weight;
        }
        
        public char getValue(){
        	return this.value;
        };
        
        public int getWeight(){
        	return this.weight;
        };
	};  
	
	public enum Suit {
		DIAMONDS('D', 0), SPADES('S', 15), CLOVERS('C', 30), HEARTS('H', 45);
		
        private char suit;
        private int weight;

        private Suit(char suit, int weight) {
                this.suit = suit;
                this.weight = weight;
        }
        
        public char getSuit(){
        	return this.suit;
        };
        
        public int getWeight(){
        	return this.weight;
        };
	}
	
	public Card(Value value, Suit suit){
		this.value = value;
		this.suit = suit;
	}
	
	public Card(String card){
		char valueC = card.charAt(0);
		char suitC = card.charAt(1);
		boolean valueSet = false;
		boolean suitSet = false;
		
		for ( Value v : Card.Value.values() ){
			if ( v.getValue() == valueC){
				this.value = v;
				valueSet = true;
			}
		}
		if(valueSet == false){
			 throw new IllegalArgumentException("Invalid card Value: " + valueC);
		}	
		
		for( Suit s : Card.Suit.values() ){
			if ( s.getSuit() == suitC){
				this.suit = s;
				suitSet = true;
			}	
		}
		if(suitSet == false){
			 throw new IllegalArgumentException("Invalid card Suit: " + suitC);
		}
		
	}
	
	public char getSuit(){
		return this.suit.getSuit();
	}
	
	public char getValue(){
		return this.value.getValue();
	}
	
	//Maybe the methods below should be defined protected
	
	public int getValueWeight(){
		return this.value.getWeight();
	}
	
	public int getSuitWeight(){
		return this.suit.getWeight();
	}
	
	public int getTotalWeight(){
		return this.suit.getWeight() + this.value.getWeight();
	}
	
	public boolean isHighCard(){
		if (this.getValue() == 'J' || this.getValue() == 'Q' || this.getValue() == 'K' || this.getValue() == 'A')
			return true;
		
		return false;
	}

	@Override
	public String toString() {
		return value.getValue() + "" + suit.getSuit();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((suit == null) ? 0 : suit.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Card other = (Card) obj;
		if (suit.getSuit() != other.getSuit())
			return false;
		if (value.getValue() != other.getValue())
			return false;
		return true;
	}
	
	
	
	
	
	/*
	public static void main(String[] args){
		Card c =  new Card(Value.NINE, Suit.HEARTS);
		
		System.out.println(c.getSuit() +" " + c.getValue());
	}
	
	*/

}
