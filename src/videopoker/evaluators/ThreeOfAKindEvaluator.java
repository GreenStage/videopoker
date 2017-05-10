package videopoker.evaluators;

import videopoker.game.Card;
import videopoker.game.Hand;

/**
 * 
 * This class evaluates if the hand contains Three cards of the same kind.
 *  <p> For example: the hand [6S TS QD QH QS] is a "Three Of A Kind" hand, with 3 Queens.
 *
 */
public class ThreeOfAKindEvaluator extends HandEvaluator implements MainHandEvaluator{
	
	public static final String handPower = "THREE OF A KIND";
	
	@Override
	public boolean hasHandPower(Hand hand) {

		Card[] hand_o = hand.orderByValue();
		int[] aux;
		aux = equality(hand_o);
		
		if(!(aux[0] >= 3 || aux[2] >= 3)){
			return false;
		}
		
		return true;
	}
	
	@Override
	public boolean[] whereCards(Hand hand){
		Card[] hand_o = hand.orderByValue();
		int[] aux;
		int idx;
		
		aux = equality(hand_o);
		
		if(!(aux[0] >= 3 || aux[2] >= 3)){
			return new boolean[0];
		}
		if(aux[0] >= 3){
			idx = 0;
		}else{
			idx = 2;
		}
		
		boolean[] keep = {false, false, false, false, false};
		for(int i = aux[idx+1]; i < aux[idx+1] + 3; i++){
			keep[hand.search(hand_o[i])] = true;
		}

		return keep;
	}
	
	@Override
	public String getHandPower(Hand hand){
		if (this.hasHandPower(hand))
			return handPower;
		
		return super.getHandPower(hand);
	}

	
	
}
