package videopoker.evaluators;

import videopoker.game.Card;
import videopoker.game.Hand;

/**
 * This class is part
 * of a group of classes that implements the interface {@link Evaluator} and 
 * scans the hand looking for a 
 * specific card combination.
 * More specifically, this class searches for a hand with an ACE. If multiple Aces are present only
 * one is reported.
 * <p> For example: the hand [JS 7C QH AH KD] is an "Ace" hand. </p>
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
