package videopoker.evaluators;

import videopoker.game.Hand;

/**
 * This interface must be implemented by all the classes that need to be used to evaluate
 * the presence of winning hand combinations in the video poker typology that is being played.
 * Moreover, since this interface extends the {@link Evaluator} interface, all the classes
 * implementing the interface can be used as part of a strategy.
 * 
 *@see videopoker.strategy.Strategy
 *@see Evaluator
 */
public interface MainHandEvaluator extends Evaluator {
	
	/**
	 * Checks if the passed hand contains the combination of cards specified by the class implementing
	 * this method.
	 * @param hand : hand to evaluate
	 * @return true if the combination defined in the class has been found.
	 */
	public boolean hasHandPower(Hand hand); 
	
	/**
	 * Returns a textual description with the name of the hand, if the specific combination of cards 
	 * defined in the class is found. See the description of the class for the hand name.
	 * @param hand : hand to evaluate.
	 * @return name of the hand if the combination is found, HAND_NONE otherwise.
	 */
	public String getHandPower(Hand hand);
}
