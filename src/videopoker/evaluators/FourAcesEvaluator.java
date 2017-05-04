package videopoker.evaluators;

import videopoker.game.Card;
import videopoker.game.Hand;

public class FourAcesEvaluator extends FourOfAKindEvaluator implements MainHandEvaluator {
	
	private static final String handPower = "FOUR ACES";
	
	@Override
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
