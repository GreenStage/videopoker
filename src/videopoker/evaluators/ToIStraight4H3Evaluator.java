package videopoker.evaluators;

import videopoker.game.Card;
import videopoker.game.Hand;

/**
 * 
 * This class is part
 * of a group of classes that implements the interface {@link Evaluator} and 
 * scans the hand looking for a 
 * specific card combination.
 * More specifically, this class searches for a hand with a Four to an Inside Straight with three high
 * cards, 
 * i.e. a hand with a straight with a missing inside card and three high cards.
 * <p> For example: the hand [TH JD QH AC 2S] is a "Four to an Inside Straight with three high cards" hand. </p>
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
