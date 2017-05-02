package Strategy;

import videopoker.Hand;

public interface Evaluator {
	
	public boolean[] whereCards(Hand hand);
}
