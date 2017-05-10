package videopoker.evaluators;

import videopoker.game.Hand;

/**
 * 
 * This class evaluates if the hand contains a flush, which means that all the cards have the same suit.
 *  <p> For example: the hand [7C JC QC KC AC] is a "Flush" hand.
 *
 */
public class FlushEvaluator extends HandEvaluator implements MainHandEvaluator {
	
	public final static String handPower = "FLUSH";
	
	@Override
	public boolean hasHandPower(Hand hand) {
		
		if (!(this.hasSameSuit(hand.getCardList())))
			return false;
		
		return true;
	}
	
	@Override
	public boolean[] whereCards(Hand hand) {
		
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
