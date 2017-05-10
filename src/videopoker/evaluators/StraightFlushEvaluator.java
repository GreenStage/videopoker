package videopoker.evaluators;

import videopoker.game.Card;
import videopoker.game.Hand;

/**
 * 
 * This class evaluates if the hand contains a Straight Flush, that means having the cards Ten, Jack, Queen, King and Ace, of any suit.
 *  <p> For example: the hand [8S 9S TS JS QS] is a "Straight Flush" hand.
 *
 */
public class StraightFlushEvaluator extends HandEvaluator implements MainHandEvaluator {

	public static final String handPower = "STRAIGHT FLUSH";
	
	@Override
	public boolean hasHandPower(Hand hand){
		Card[] hand_o  = hand.orderByValueSuit();
		
		if(!inOrder(hand_o)){
			return false;
		}
		
		if(!hasSameSuit(hand_o)){
			return false;
		}
		
		return true;
	}
	
	@Override
	public boolean[] whereCards(Hand hand){
				
		if (!(this.hasHandPower(hand)))
			return new boolean[0];
		
		boolean[] keep = {true, true, true, true, true};

		return keep;
	}
	
	@Override
	public String getHandPower(Hand hand){
		if (this.hasHandPower(hand))
			return handPower;
		
		return super.getHandPower(hand);
	}
	
}