package videopoker.evaluators;

import videopoker.game.Card;
import videopoker.game.Hand;

/**
 * 
 * This class is part
 * of a group of classes that implements the interface {@link MainHandEvaluator} and 
 * scans the hand looking for a 
 * specific card combination.
 * More specifically, this class searches for a hand with Four Aces, i.e. a hand in which four cards
 * are Aces. 
 * <p> For example: the hand [AH AS AD AC KS] is an "Four Aces" hand. </p>
 * 
 * The hand name is : <b>FOUR ACES</b> (case sensitive)
 *
 */
public class FourAcesEvaluator extends FourOfAKindEvaluator implements MainHandEvaluator {
	
	public static final String handPower = "FOUR ACES";
	
	@Override
	/**
	 * The name of the hand evaluated by the class.
	 * The name of the hand is FOUR ACES.
	 */
	public boolean hasHandPower(Hand hand) {
		
		boolean[] four = super.whereCards(hand);
		
		if (four.length == 0)
			return false;
		
		Card[] test = new Card[4];
		
		for (int i=0, j=0; i < four.length; i++ ){
			if (four[i] == true){
				test[j] = hand.getCard(i); 
				j++;
			}
		}
		
		for (int i = 0; i < test.length; i++){
			if (test[i].getValue() != 'A')
				return false;
		}
		
		
		return true;
	}
	
	@Override
	public boolean[] whereCards(Hand hand){
		
		if (!(hasHandPower(hand)))
			return new boolean[0];
			
		return super.whereCards(hand);
		
	}
	
	@Override
	public String getHandPower(Hand hand){
		if (this.hasHandPower(hand))
			return handPower;
		
		return super.getHandPower(hand);
	}

}
