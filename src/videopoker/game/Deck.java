package videopoker.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
	
	class EmptyDeckException extends RuntimeException{
		 public EmptyDeckException(String msg){
		      super(msg);
		   }
	};
	
	private List<Card> cards = new ArrayList<Card>();

	
	public Deck(){
		for( Card.Suit st : Card.Suit.values() ){
			for( Card.Value vl : Card.Value.values() ){
				this.cards.add( new Card( vl, st) );			
			}
		}
	}

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
	
	public void shuffle(){
		Collections.shuffle(cards);
	}
	
	public Card popCard() throws EmptyDeckException{
		if(cards.isEmpty()){
			throw new EmptyDeckException("Deck is empty");
		}
		else return cards.remove(0);
	}
	
	
	public int getAmountCards(){
		return cards.size();
	}
	
}
