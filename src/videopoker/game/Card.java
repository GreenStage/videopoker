package videopoker.game; //UML DONE

/**  
* A class to instantiate a card of the type traditionally used in poker games.  
* @version 1.0 
*/
public class Card {
	/**
	 * Store the enum type that specifies the card value
	 */
	private Value value;
	
	/**
	 * Store the enum type that specifies the card suit
	 */
	private Suit suit;
	
	/**
	* Enumeration of all possible card values
	*/
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
        
        /**
        * This method returns a char representing the card value. 
        * <ul>
        * <li>Ace : 'A'</li>
        * <li>Two : '2'</li>
        * <li>Three : '3' </li>
        * <li>Four : '4' </li>
        * <li>Five : '5' </li>
        * <li>Six : '6' </li>
        * <li>Seven : '7' </li>
        * <li>Eight : '8' </li>
        * <li>Nine : '9' </li>
        * <li>Ten : 'T' </li>
        * <li>Jack : 'J' </li>
        * <li>Queen : 'Q' </li>
        * <li>King : 'K' </li>
        * </ul>
        * @return character representing the card value
        */
        public char getValue(){
        	return this.value;
        };
        
        /**
        * This method returns a number that represents the weight of the card's value.
        * <ul>
        * <li>Two : 2</li>
        * <li>Three : 3 </li>
        * <li>Four : 4 </li>
        * <li>Five : 5 </li>
        * <li>Six : 6 </li>
        * <li>Seven : 7 </li>
        * <li>Eight : 8 </li>
        * <li>Nine : 9 </li>
        * <li>Ten : 10 </li>
        * <li>Jack : 11 </li>
        * <li>Queen : 12 </li>
        * <li>King : 13 </li>
        * <li>Ace : 14 </li>
        * </ul>
        * 
        * Ace can have two possible weights, 14 or 1, depending on the game played and the combination
        * of cards in the player's hand. In this specific circumstance determining the value of the Ace
        * is a responsability of the developer.
        * 
        * 
        * @return integer representing the card weight
        */
        
        public int getWeight(){
        	return this.weight;
        };
	};  
	
	/**
	* Enumeration of possible card suits
	*/
	public enum Suit {
		DIAMONDS('D', 0), SPADES('S', 15), CLOVERS('C', 30), HEARTS('H', 45);
		
        private char suit;
        private int weight;

        private Suit(char suit, int weight) {
                this.suit = suit;
                this.weight = weight;
        }
        
        /**
        * This method returns a char representing the card suit. 
        * <ul>
        * <li>Clovers : 'C'</li>
        * <li>Diamonds : 'D'</li>
        * <li>Spades : 'S' </li>
        * <li>Hearts : 'H' </li>
        * </ul>
        * @return character representing the card value
        */
        public char getSuit(){
        	return this.suit;
        };
        
        /**
        * This method returns an integer representing the card's suit weight. 
        * <ul>
        * <li>Diamonds : 0 </li>
        * <li>Spades : 15</li>
        * <li>Clovers : 30 </li>
        * <li>Hearts : 45 </li>
        * </ul>
        * The assigned weights are arbitrary. These values are used to order the cards by suit. 
        * 
        * @return integer representing the suit's weight
        */
        public int getWeight(){
        	return this.weight;
        };
	}
	
	/**
	 * Creates a card with the specified value and suit.
	 * 
	 * @param value : enum type with the value of the card
	 * @param suit : enum type with the suit of the card
	 * @exception IllegalArgumentException if one of the arguments is NULL. 
	 */
	public Card(Value value, Suit suit){
		
		if (value == null || suit == null)
			throw new IllegalArgumentException("Value or suit are null");
		
		this.value = value;
		this.suit = suit;
	}
	
	/**
	 * Creates a card with the value and suit specified in a string.
	 * <p> The string has to be two characters long. The first character specifies the value of the card
	 * , the second the suit. If the format is not respected an exception is raised. </p>
	 *
	 * @param card : string of two characters that specifies the value and suit of the card.
	 * @exception IllegalArgumentException if string format is not respected or invalid characters
	 * are passed. 
	 */
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
	/**
	 * Return the suit of the card.
	 * @return character representing the suit of the card
	 */
	public char getSuit(){
		return this.suit.getSuit();
	}
	
	/**
	 * Return the value of the card
	 * @return character representing the value of the card
	 */
	public char getValue(){
		return this.value.getValue();
	}
	
    /**
     * Return the weight of the card value.
     * @return integer that represents the weight of the card value
     */
	public int getValueWeight(){
		return this.value.getWeight();
	}
	
    /**
     * Return the weight of the card suit.
     * @return integer that represents the weight of the card suit
     */
	public int getSuitWeight(){
		return this.suit.getWeight();
	}
	
    /**
     * Return the total weight of the card.
     * <p> The total weight is the sum of the value's weight and the suit's weight.
     * @return integer that represents the total weight of the card.
     */
	public int getTotalWeight(){
		return this.suit.getWeight() + this.value.getWeight();
	}
	
	/**
	 * Determines if the card is high or low.
	 * <p> High cards are Ace, King, Jack and Queen. The rest are low cards </p>
	 * @return true if the card is a high card.
	 */
	public boolean isHighCard(){
		if (this.getValue() == 'J' || this.getValue() == 'Q' || this.getValue() == 'K' || this.getValue() == 'A')
			return true;
		
		return false;
	}


	@Override
	/**
	 * Textual description of the card
	 */
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
}
