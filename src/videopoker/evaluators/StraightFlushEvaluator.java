package videopoker.evaluators;

import videopoker.game.Card;
import videopoker.game.Hand;

/**
 * 
 * This class is part
 * of a group of classes that implements the interface {@link MainHandEvaluator} and 
 * scans the hand looking for a 
 * specific card combination.
 * More specifically, this class searches for a hand with a Straight Flush, i.e. a hand in 
 * which 5 consecutive cards of the same suit are present. 
 * <p> For example: the hand [AS 2S 3S 4S 5S] is an "Straight Flush" hand. </p>
 * 
 * The hand name is : <b>STRAIGHT FLUSH</b> (case sensitive)
 *
 */
public class StraightFlushEvaluator extends HandEvaluator implements MainHandEvaluator {

	/**
	 * The name of the hand evaluated by the class.
	 * The name of the hand is STRAIGHT FLUSH.
	 */
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