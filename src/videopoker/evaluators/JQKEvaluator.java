package videopoker.evaluators;

import videopoker.game.Card;
import videopoker.game.Hand;

/**
 * 
 * This class is part
 * of a group of classes that implements the interface {@link Evaluator} and 
 * scans the hand looking for a 
 * specific card combination.
 * More specifically, this class searches for a hand with a Jack, a Queen or a King of any suit. 
 * If more than one card with these values is present, only the first instance in the hand is marked.
 * <p> For example: the hand [JS 7C 5H AH 2D] is an "J,Q,K" hand. </p>
 *
 */
public class JQKEvaluator extends HandEvaluator implements Evaluator {

	@Override
	public boolean[] whereCards(Hand hand) {
		
		Card[] test = hand.getCardList();
		
		for (int i = 0; i < test.length; i++){
			if (test[i].getValue() == 'J' || test[i].getValue() == 'Q' || test[i].getValue() == 'K'){
				boolean[] keep = {false, false,false,false, false};
				keep[hand.search(test[i])] = true;
				return keep;
			}
		}
		
		return new boolean[0];
	}

}
