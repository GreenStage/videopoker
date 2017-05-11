package videopoker.evaluators;

import videopoker.game.Card;
import videopoker.game.Hand;

/**
 * 
 * This class is part
 * of a group of classes that implements the interface {@link Evaluator} and 
 * scans the hand looking for a 
 * specific card combination.
 * More specifically, this class searches for a hand with a Three to a Flush with no high cards, 
 * i.e. a hand in which three cards have the same suit and none of them is a high card.
 * <p> For example: the hand [2H 3H 5H 2C KS] is a "Three to a flush with no high cards " hand. </p>
 */
public class ToFlush3NHEvaluator extends ToFlush3Evaluator implements Evaluator {

	@Override
	public boolean[] whereCards(Hand hand){
		
		boolean[] s3flush = super.whereCards(hand);
		
		if (s3flush.length == 0)
			return new boolean[0];
		
		Card[] test = new Card[3];
		
		for (int i=0, j=0; i < s3flush.length; i++ ){
			if (s3flush[i] == true){
				test[j] = hand.getCard(i); 
				j++;
			}
		}
		
		int num_high = numHighCards(test);
		
		if (num_high == 0){
			return s3flush;
		}
		
		return new boolean[0];
	}
}
