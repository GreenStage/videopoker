package videopoker.strategy;

import java.util.ArrayList;

import videopoker.evaluators.Evaluator;
import videopoker.game.Hand;

/**
 * This class instantiates an advisor entity. The main purpose of this class is to suggest to the
 * player of a video poker game a strategy to follow (which cards to hold and which cards to discard).
 * In order to accomplish this, an instance of a {@link videopoker.strategy.Strategy} class should be given. Based on this strategy
 * a suggestion is given to the player. This class is very versatile and can receive as
 * input different strategies elaborated by the user.
 * 
 * @see videopoker.strategy.Strategy
 *
 */
public class Advisor {
	
	/**
	 * Current strategy employed by the advisor
	 */
	private Strategy s;
	
	/**
	 * Creates a new advisor that gives suggestions according to the provided strategy.
	 * @param s : strategy the advisor has to follow.
	 */
	public Advisor(Strategy s){
		this.s = s;
	}
	
	/**
	 * Set a new strategy for the advisor.
	 * @param s : new strategy to follow.
	 */
	public void setStrategy(Strategy s){
		this.s = s;
	}
	
	/**
	 * This method receives a hand to evaluate and suggests which cards to hold according to the
	 * chosen strategy.
	 * <p>The suggestion is given using an array of booleans. The player should hold the cards
	 * in the position in which a <i>true</i> is present and discard all the others. 
	 * For example the hand [JS QS KS 4H 6C] can produce the result [true, true, true, false, false].
	 * In this case the player should hold JS, QS and KS.</p>
	 * @param hand : hand to evaluate
	 * @return an array of booleans in which the positions marked with <i>true</i> correspond to the positions of the cards to hold in the given hand.
	 */
	public boolean[] giveAdvice(Hand hand){
		
		ArrayList<Evaluator> list = s.getStrategy();
		
		for (Evaluator eval : list){
			if (eval == null)
				continue;
			
			boolean[] advice = eval.whereCards(hand);
			if (advice.length > 0)
				return advice;
		}
		
		boolean[] advice = {false, false, false, false, false};
		
		return advice;
		
		
	}
}
