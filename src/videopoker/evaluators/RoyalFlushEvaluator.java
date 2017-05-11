package videopoker.evaluators;

import videopoker.game.Card;
import videopoker.game.Hand;

/**
 * 
 * This class is part
 * of a group of classes that implements the interface {@link MainHandEvaluator} and 
 * scans the hand looking for a 
 * specific card combination.
 * More specifically, this class searches for a hand with a Royal Flush, i.e. a hand in 
 * which a Ten, a Jack, a Queen, a King and an Ace of the same suit are present. 
 * <p> For example: the hand [TS JS QS KS AS] is an "Royal Flush" hand. </p>
 * 
 * The hand name is : <b>ROYAL FLUSH</b> (case sensitive)
 *
 */
public class RoyalFlushEvaluator extends HandEvaluator implements MainHandEvaluator {
	
	/**
	 * The name of the hand evaluated by the class.
	 * The name of the hand is ROYAL FLUSH.
	 */
	public static String handPower = "ROYAL FLUSH";
	
	@Override
	public boolean hasHandPower(Hand hand){
		Card[] hand_o  = hand.orderByValueSuit();
		
		if(numRoyalCards(hand_o) < 5){
			return false;
		}
		if(!hasSameSuit(hand_o)){
			return false;
		}
		
		return true;
	}
	
	@Override
	public boolean[] whereCards(Hand hand){
		if(hasHandPower(hand) ){
			return new boolean[] {true, true, true, true, true};
		}
		else return new boolean[0];
	}
	
	@Override
	public String getHandPower(Hand hand){
		if (this.hasHandPower(hand))
			return handPower;
		
		return super.getHandPower(hand);
	}
	
	
}
