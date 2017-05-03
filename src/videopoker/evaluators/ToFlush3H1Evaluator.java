package videopoker.evaluators;

import videopoker.game.Card;
import videopoker.game.Hand;

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
