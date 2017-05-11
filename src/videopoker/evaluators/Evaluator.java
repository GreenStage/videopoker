package videopoker.evaluators;

import videopoker.game.Hand;

/**
 * This interface must be implemented by all the classes that need to be used as part of a
 * strategy.
 * 
 *@see videopoker.strategy.Strategy
 */

public interface Evaluator {
	
	/**
	 * The method whereCards evaluates the given hand and looks for the specific combination
	 * of cards defined in the class implementing this method. If the combination is found, the 
	 * elements in the returned array corresponding to the position of the cards in the hand  
	 * are marked with the value <i>true</i>. 
	 * @param hand : hand to evaluate
	 * @return array of booleans in which the positions of cards that are part of the combination are marked with the value <i>true</i>
	 */
	public boolean[] whereCards(Hand hand);
}
