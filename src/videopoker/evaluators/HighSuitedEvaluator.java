package videopoker.evaluators;

import videopoker.game.Card;
import videopoker.game.Hand;

public class HighSuitedEvaluator extends HandEvaluator implements Evaluator {

	@Override
	public boolean[] whereCards(Hand hand) {

		Card[] hand_o = hand.orderByValueSuit();
		
		int numHighCards = numHighCards(hand_o);
		
		if (numHighCards < 2)
			return new boolean[0];
		
		Card[] test = new Card[numHighCards];
		
		int k = 0;
		for (int i = 0; i < hand_o.length; i++){
			char curValue = hand_o[i].getValue();
			if (curValue == 'K' || curValue == 'A' || curValue == 'Q' || curValue == 'J' ){
				test[k] = hand_o[i];
				k++;
			}
		}
		
		for (int i=0; i < test.length-1; i++){
			if (test[i].getSuit() == test[i+1].getSuit()){
				boolean[] keep = {false, false, false, false, false};
				keep[hand.search(test[i])] = true;
				keep[hand.search(test[i+1])] = true;
				return keep;
			}
		}
		
		
		return new boolean[0];
	}
	

}
