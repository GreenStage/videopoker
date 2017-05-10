package videopoker.evaluators;

import videopoker.game.Card;
import videopoker.game.Hand;

/**
 * 
 * This class evaluates if the hand contains a Royal Flush, that means having the cards Ten, Jack, Queen, King and Ace, with the same suit.
 *  <p> For example: the hand [TS JS QS KS AS] is a "Royal Flush" hand.
 *
 */
public class RoyalFlushEvaluator extends HandEvaluator implements MainHandEvaluator {
	
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
