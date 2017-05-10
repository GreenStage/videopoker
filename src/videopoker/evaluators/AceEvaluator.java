package videopoker.evaluators;

import videopoker.game.Card;
import videopoker.game.Hand;

/**
 * 
 * This class evaluates if the hand contains an Ace.
 *  <p> For example: the hand [7C JS QH KD AH] is an "Ace" hand.
 *
 */
public class AceEvaluator extends HandEvaluator implements Evaluator {

	@Override
	public boolean[] whereCards(Hand hand) {
		
		Card[] hand_o = hand.orderByValue();
		
		boolean[] keep = {false, false,false, false, false};
		
		if (hand_o[4].getValue() == 'A'){
			keep[hand.search(hand_o[4])] = true;
			return keep;
		}
		
		return new boolean[0];
	}

}
