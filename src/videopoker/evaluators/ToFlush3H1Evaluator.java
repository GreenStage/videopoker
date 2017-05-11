package videopoker.evaluators;

import videopoker.game.Card;
import videopoker.game.Hand;

/**
 * 
 * This class is part
 * of a group of classes that implements the interface {@link Evaluator} and 
 * scans the hand looking for a 
 * specific card combination.
 * More specifically, this class searches for a hand with a Three to a Flush with one high card, 
 * i.e. a hand in which three cards have the same suit and only one of these cards is a high card.
 * <p> For example: the hand [2H 3H KH 2C KS] is a "Three to a flush with one high card " hand. </p>
 */
public class ToFlush3H1Evaluator extends ToFlush3Evaluator implements Evaluator {

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
		
		if (num_high == 1){
			return s3flush;
		}
		
		return new boolean[0];
	}
}
