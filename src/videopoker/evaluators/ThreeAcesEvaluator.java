package videopoker.evaluators;

import videopoker.game.Card;
import videopoker.game.Hand;

/**
 * 
 * This class is part
 * of a group of classes that implements the interface {@link Evaluator} and 
 * scans the hand looking for a 
 * specific card combination.
 * More specifically, this class searches for a hand with Three Aces, i.e. a hand in which three cards
 * are Aces. 
 * <p> For example: the hand [AH AS AD 2C KS] is a "Three Aces" hand. </p>
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
