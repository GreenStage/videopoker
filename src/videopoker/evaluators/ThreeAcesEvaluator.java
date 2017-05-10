package videopoker.evaluators;

import videopoker.game.Card;
import videopoker.game.Hand;

/**
 * 
 * This class evaluates if the hand contains 3 Aces.
 *  <p> For example: the hand [TS JS AH AC AS] is a "Three Aces" hand.
 *
 */
public class ThreeAcesEvaluator extends ThreeOfAKindEvaluator implements Evaluator {
	
	@Override
	public boolean[] whereCards(Hand hand){
		
		boolean[] three = super.whereCards(hand);
		
		if (three.length == 0)
			return new boolean[0];
		
		Card[] test = new Card[3];
		
		for (int i=0, j=0; i < three.length; i++ ){
			if (three[i] == true){
				test[j] = hand.getCard(i); 
				j++;
			}
		}
		
		for (int i=0; i < test.length; i++){
			if (test[i].getValue() != 'A')
				return new boolean[0];
		}
		
		return three;
		
	}
	

}
