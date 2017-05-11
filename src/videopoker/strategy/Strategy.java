package videopoker.strategy;

import java.util.ArrayList;

import videopoker.evaluators.Evaluator;

/**
 * The Strategy class provides an abstraction for a strategy to follow in a video poker game.
 * A strategy in just an ordered collection of objects
 * implementing the interface {@link videopoker.evaluators.Evaluator}. The evaluators are inserted in
 * a list. The first evaluator in the list represents the best hand to play, while the last 
 * represents the worst hand to play. Using this class a
 * strategy can be composed by the user by placing the evaluators in the
 * desired position. The {@link videopoker.strategy.Advisor} class should be used to
 * evaluate a hand against a strategy.
 * 
 *@see Advisor
 */
public class Strategy {
	
	/**
	 * List containing the evaluators in ascending order of importance.
	 */
	private ArrayList<Evaluator> list;
	
	/**
	 * Instantiate a new empty strategy.
	 */
	public Strategy(){
		list = new ArrayList<Evaluator>();
	}
	
	/**
	 * Add a new evaluator to the strategy. 
	 * <p> The newly inserted evaluator will be added at the end of the list representing 
	 * the strategy and therefore will evaluate a less convenient hand in the current strategy.</p>
	 * 
	 * @param eval : hand evaluator to insert at the end of the strategy.
	 */
	public void addEvaluator(Evaluator eval){
		list.add(eval);
	}
	
	/**
	 * Initialise the strategy with the provided ArrayList of evaluators.
	 * @param newList : list of evaluators placed in the list in order of importance.
	 */
	public void addEvaluator(ArrayList<Evaluator> newList){
		list = newList;
	}
	
	/**
	 * Return the current strategy.
	 * @return list of evaluators used as current strategy.
	 */
	public ArrayList<Evaluator> getStrategy(){
		return list;
	}
	
}
