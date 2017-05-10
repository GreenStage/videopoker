package videopoker.evaluators;

import videopoker.game.Card;
import videopoker.game.Hand;

/**
 * 
 * This class evaluates if the hand contains 4 cards of the same suit.
 * <p> For example: the hand [6S JS QS KC AS] is a "Four To Flush" hand.
 *
 */
public class ToFlush4Evaluator extends HandEvaluator implements Evaluator{
	
	@Override
	public boolean[] whereCards(Hand hand){
		
		Card[] hand_o  = hand.orderByValueSuit();
		
		Card[] test1 = {hand_o[0], hand_o[1], hand_o[2], hand_o[3]};
		Card[] test2 = {hand_o[1], hand_o[2], hand_o[3], hand_o[4]};
		
		boolean test1_ss = hasSameSuit(test1);
		boolean test2_ss = hasSameSuit(test2);
		
	
		if (test1_ss){
			boolean[] keep = {false, false, false, false, false};
			keep[hand.search(test1[0])] = true;
			keep[hand.search(test1[1])] = true;
			keep[hand.search(test1[2])] = true;
			keep[hand.search(test1[3])] = true;
			
			return keep;
		}
		
		if (test2_ss){
			boolean[] keep = {false, false, false, false, false};
			keep[hand.search(test2[0])] = true;
			keep[hand.search(test2[1])] = true;
			keep[hand.search(test2[2])] = true;
			keep[hand.search(test2[3])] = true;
			
			return keep;
		}
		
		return new boolean[0];	
		
	}
	
}
