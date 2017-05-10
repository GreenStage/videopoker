package videopoker.evaluators;

import videopoker.game.Card;
import videopoker.game.Hand;

/**
 * 
 * This class evaluates if the hand contains two different pairs.
 *  <p> For example: the hand [6S 6C 7S QD QH] is a "Two Pair" hand, with 2 Six and 2 Queen.
 *
 */
public class TwoPairEvaluator extends HandEvaluator implements MainHandEvaluator{
	
	public final static String handPower = "TWO PAIR";
	
	@Override
	public boolean hasHandPower(Hand hand) {
		
		Card[] hand_o = hand.orderByValue();
		int[] aux;
		
		aux = equality(hand_o);
		
		if(!(aux[0] == 2 && aux[2] == 2)){
			return false;
		}
		
		return true;
	}
	
	@Override
	public boolean[] whereCards(Hand hand){
		
		Card[] hand_o = hand.orderByValue();
		int[] aux;
		
		aux = equality(hand_o);
		
		if(!(aux[0] == 2 && aux[2] == 2)){
			return new boolean[0];
		}
		
		boolean[] keep = {false, false, false, false, false};
		
		for(int i = aux[1]; i < aux[1] + aux[0]; i++){
			keep[hand.search(hand_o[i])] = true;
		}
		for(int i = aux[3]; i < aux[3] + aux[2]; i++){
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
