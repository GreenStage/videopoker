package videopoker;

import java.util.ArrayList;

public class Deck {
	private String[] savedDeck = null;
	private ArrayList<Card> cards = new ArrayList<Card>();

	public Deck(){
		for( Card.Suit st : Card.Suit.values() ){
			for( Card.Value vl : Card.Value.values() ){
				this.cards.add( new Card( vl, st) );			
			}
		}
	}
	
	public String[] getSavedDeck(){
		return this.savedDeck;
	}
	
	public Deck(String[] cardArr){
		this.savedDeck = cardArr;
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
	
	public Card popCard(){
		if(cards.size() == 0){
			return null;
		}
		int rand = (int) ((Math.random() * cards.size()));
		return cards.remove(rand);
	}
	
	public Card popCard(int index){
		return cards.remove(index);
	}
	
	public int getAmountCards(){
		return cards.size();
	}
	
}
