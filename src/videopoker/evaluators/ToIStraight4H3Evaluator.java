package videopoker.evaluators;

import videopoker.game.Card;
import videopoker.game.Hand;

/**
 * 
 * This class evaluates if the hand contains 4 cards of the same suit, with 3 high cards.
 *
 */
public class ToIStraight4H3Evaluator extends ToIStraight4Evaluator implements Evaluator {
	
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
		
		
		if (numHighCards == 3)
			return s4flush;
		
		return new boolean[0];
		
	}



}
