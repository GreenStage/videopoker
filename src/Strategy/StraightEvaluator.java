package Strategy;

import videopoker.Hand;

public class StraightEvaluator extends HandEvaluator implements MainHandEvaluator {

	private final static String handPower = "STRAIGHT";

	
	@Override
	public boolean hasHandPower(Hand hand) {
		
		if (!(this.inOrder(hand.getCardList())))
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
