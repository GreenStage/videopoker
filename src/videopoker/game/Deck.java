package videopoker.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Deck class
 * handles the game deck
 */
public class Deck {
	
	/** EmptyDeckException
	 *  Many operations require the deck to not be empty,
	 *  this exception alerts for an empty deck
	 */
	class EmptyDeckException extends RuntimeException{
		 public EmptyDeckException(String msg){
		      super(msg);
		   }
	};
	
	/** List with current cards in deck */
	private List<Card> cards = new ArrayList<Card>();
	
	/**
	 * Contains the popped cards after a deal and a hold. All the cards will be inserted in the deck at the end of the turn.
	 */
	private List<Card> poppedCards = new ArrayList<Card>();
	
	/** Deck default constructor
	 * 	use to create a deck with 52 different cards
	 */
	public Deck(){
		for( Card.Suit st : Card.Suit.values() ){
			for( Card.Value vl : Card.Value.values() ){
				this.cards.add( new Card( vl, st) );			
			}
		}
	}

	/**
	 * @param cardArr Array with cards to fill the deck
	 * 	each card must be a string and take the format 'VS'
	 * 	where V = value and S = suit
	 */
	public Deck(String[] cardArr){
		for(String c : cardArr){
			try{
				Card newCard = new Card(c);
				this.cards.add(newCard);
			}
			catch(IllegalArgumentException e){
				System.out.println(e.getMessage());
			}
			
		}
	}
	
	/** Shuffle operator
	 *  shuffles the deck
	 */
	public void shuffle(){
		cards.addAll(poppedCards);
		poppedCards.clear();
		Collections.shuffle(cards);
	}
	
	/** Pop a card from the deck, removing it from the list
	 * @return Card instance, popped card
	 * @throws EmptyDeckException - in case the deck is empty
	 */
	public Card popCard() throws EmptyDeckException{
		if(cards.isEmpty()){
			throw new EmptyDeckException("Deck is empty");
		}
		else{
			Card rm = cards.remove(0);
			poppedCards.add(rm);
			return rm;
		} 
	}
	
	/** Fetches the amount of cards in deck
	 * @return integer , amount of cards in deck
	 */
	public int getAmountCards(){
		return cards.size();
	}
	
}
