package videopoker.game;

import java.util.Arrays;
import java.util.Comparator;

import videopoker.game.Card.Suit;
import videopoker.game.Card.Value;


public class Hand {
	private Card[] hand = new Card[5];
	//TODO: Hand cannot have duplicate cards...
	
	public Hand(Card c0, Card c1, Card c2, Card c3, Card c4){
		
		/*
		if (c0.equals(c1) || c0.equals(c2) || c0.equals(c3) || c0.equals(c4) || c1.equals(c2) ||
				c1.equals(c3) || c1.equals(c4) || c2.equals(c3) || c2.equals(c4) || c3.equals(c4))
			throw new DuplicateCardException("One or more equal cards in the hand");
		*/
		hand[0] = c0;
		hand[1] = c1;
		hand[2] = c2;
		hand[3] = c3;
		hand[4] = c4;
	}
	
	public Card getCard(int pos){
		return hand[pos];
	}
	
	public String[] getHandStrArr(){
		String[] handStr = new String[5];
		for(int i = 0; i < 5; i ++){
			if(this.getCard(i) != null)
				handStr[i] = this.getCard(i).toString();
		}
		return handStr;
	}
	
	public void setCard(int index, Card card){
		this.hand[index] = card;
	}
	
	public Card[] getCardList(){
		
		Card[] new_card = {hand[0], hand[1], hand[2], hand[3], hand[4]};
		
		return new_card;
	}
	
	public Card[] orderByValue(){
		
		Card[] new_card = {hand[0], hand[1], hand[2], hand[3], hand[4]};
		Arrays.sort(new_card, new Comparator<Card>() {
			
		    public int compare(Card c1, Card c2) {
		        return c1.getValueWeight()-c2.getValueWeight();
		    }
		});
		
		return new_card;
		
	}
	
	public Card[] orderByValueSuit(){
		
		Card[] new_card = {hand[0], hand[1], hand[2], hand[3], hand[4]};
		Arrays.sort(new_card, new Comparator<Card>() {
			
		    public int compare(Card c1, Card c2) {
		        return c1.getTotalWeight()-c2.getTotalWeight();
		    }
		});
		
		return new_card;
		
	}
	
	public int search(Card card){
		for (int i = 0; i < hand.length; i++){
			if (hand[i].equals(card))
				return i;
		}
		
		return -1;
	}
	
	// TEST ORDERING FUNCTIONS
	public static void main(String[] args){
		Card c1 = new Card(Value.TWO, Suit.HEARTS);
		Card c2 = new Card(Value.QUEEN, Suit.SPADES);
		Card c3 = new Card(Value.JACK, Suit.SPADES);
		Card c4 = new Card(Value.NINE, Suit.DIAMONDS);
		Card c5 = new Card(Value.EIGHT, Suit.SPADES);

		Hand hand = new Hand(c1,c2,c3,c4,c5);
		
		Card[] cards_value = hand.orderByValue();
		Card[] cards_both = hand.orderByValueSuit();
		
		System.out.println("By Value:");
		for (int i = 0; i < cards_value.length; i++){
			System.out.println(cards_value[i]);
		}
		
		System.out.println("By both value and suit:");
		for (int i = 0; i < cards_both.length; i++){
			System.out.println(cards_both[i]);
		}
		
		
	}
	
	
}
