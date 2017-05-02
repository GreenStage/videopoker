package videopoker.evaluators;

import videopoker.game.Hand;

public interface MainHandEvaluator extends Evaluator {
	
	public boolean hasHandPower(Hand hand); //Maybe not necessary
	public String getHandPower(Hand hand);
}
