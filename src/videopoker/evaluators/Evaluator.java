package videopoker.evaluators;

import videopoker.game.Hand;

public interface Evaluator {
	
	public boolean[] whereCards(Hand hand);
}
