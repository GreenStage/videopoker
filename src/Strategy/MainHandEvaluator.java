package Strategy;

import videopoker.Hand;

public interface MainHandEvaluator extends Evaluator {
	
	public boolean hasHandPower(Hand hand);
}
