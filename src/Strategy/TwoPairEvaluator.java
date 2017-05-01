package Strategy;

import videopoker.Card;
import videopoker.Hand;

public class TwoPairEvaluator extends HandEvaluator implements Evaluator{
	public boolean[] whereCards(Hand hand){
		Card[] hand_o = hand.orderByValue();
		int[] aux;
		
		aux = equality(hand_o);
		
		if(!(aux[0] == 2 && aux[2] == 2)){
			return new boolean[0];
		}
		
		boolean[] keep = {false, false, false, false, false};
		for(int i = aux[1]; i < aux[1] + aux[0]; i++){
			keep[i] = true;
		}
		for(int i = aux[3]; i < aux[3] + aux[2]; i++){
			keep[i] = true;
		}

		return keep;
	}

}
