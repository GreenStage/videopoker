package videopoker.evaluators;

import videopoker.game.Card;
import videopoker.game.Hand;
import videopoker.game.Card.Suit;
import videopoker.game.Card.Value;

/**
 * 
 * This class evaluates if the hand contains 4 cards of the same suit, with 1 high card.
 *
 */
public class ToIStraight4H1Evaluator extends ToIStraight4Evaluator implements Evaluator {
	
public boolean[] whereCards(Hand hand){
		
		boolean[] s4flush = super.whereCards(hand);
		
		if (s4flush.length == 0)
			return new boolean[0];
		
		Card[] test = new Card[4];
		
		for (int i=0, j=0; i < s4flush.length; i++ ){
			if (s4flush[i] == true){
				test[j] = hand.getCard(i); 
				j++;
			}
		}
		
		int numHighCards = this.numHighCards(test);
		
		if (this.hasAce(test) && (this.hasThree(test) || this.hasFour(test)))
			numHighCards--;
		
		
		if (numHighCards == 1)
			return s4flush;
		
		return new boolean[0];
		
	}

	public static void main(String[] args){
	
		Card c1 = new Card(Value.SIX, Suit.SPADES);
		Card c2 = new Card(Value.SEVEN, Suit.CLOVERS);
		Card c3 = new Card(Value.QUEEN, Suit.DIAMONDS);
		Card c4 = new Card(Value.TEN, Suit.HEARTS);
		Card c5 = new Card(Value.JACK, Suit.HEARTS);
	
		Hand hand = new Hand(c1,c2,c3,c4,c5);
		ToIStraight4H1Evaluator eval = new ToIStraight4H1Evaluator();
		boolean[] keep = eval.whereCards(hand);
		
		for (int i = 0; i < keep.length; i++){
			System.out.println(keep[i]);
	}
	
}


}
