package videopoker.evaluators;

import videopoker.game.Card;
import videopoker.game.Hand;

/**
 * 
 * This class evaluates if the hand contains a Full House, that means having 3 cards of a kind and 2 of another kind.
 *  <p> For example: the hand [JS JC JH AH AD] is a "Full House" hand.
 *
 */
public class FullHouseEvaluator extends HandEvaluator implements MainHandEvaluator {
	
	public static final String handPower = "FULL HOUSE";
	
	@Override
	public boolean hasHandPower(Hand hand) {
		
		Card[] hand_o  = hand.orderByValue();
		int[] aux;
		
		aux = equality(hand_o);
		
		if(!((aux[0] == 3 && aux[2] == 2)||(aux[0] == 2 && aux[2] == 3))){
			return false;
		}
		
		return true;
	}
	
	@Override
	public boolean[] whereCards(Hand hand){
		
		if (!(hasHandPower(hand)))
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
