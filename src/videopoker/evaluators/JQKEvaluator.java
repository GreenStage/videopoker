package videopoker.evaluators;

import videopoker.game.Card;
import videopoker.game.Hand;

public class JQKEvaluator extends HandEvaluator implements Evaluator {

	@Override
	public boolean[] whereCards(Hand hand) {
		
		Card[] test = hand.getCardList();
		
		for (int i = 0; i < test.length; i++){
			if (test[i].getValue() == 'J' || test[i].getValue() == 'Q' || test[i].getValue() == 'K'){
				boolean[] keep = {false, false,false,false, false};
				keep[hand.search(test[i])] = true;
				return keep;
			}
		}
		
		return new boolean[0];
	}

}
