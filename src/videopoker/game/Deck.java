package videopoker.game;

import java.util.ArrayList;

public class Deck {
	private boolean removeRandom = true;
	private ArrayList<Card> cards = new ArrayList<Card>();

	public Deck(){
		for( Card.Suit st : Card.Suit.values() ){
			for( Card.Value vl : Card.Value.values() ){
				this.cards.add( new Card( vl, st) );			
			}
		}
	}
	
	public void setRemoveRandom(boolean f){
		removeRandom = f;
	}
	
	public boolean getRemoveRandom(){
		return removeRandom;
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
	
	public Card popCard(){
		if(cards.size() == 0){
			return null;
		}
		else if(!removeRandom)
			return cards.remove(0);
		else{
			int rand = (int) ((Math.random() * cards.size()));
			return cards.remove(rand);		
		}

	}
	
	
	public int getAmountCards(){
		return cards.size();
	}
	
}
