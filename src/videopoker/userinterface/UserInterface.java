//TODO, MABBYE USEFUL AS AN ABTRACT CLASS / IntERFACE when GUI implemented

package videopoker.userinterface;

import videopoker.game.PowerHashMap;

/**
 * UserInterface - UI interface, extending a runnable
 *
 */
public interface  UserInterface extends Runnable{

	
	/** Display the game statistics, using the output handler
	 * @param credit - players credit
	 * @param gain	 - ratio in percentage between players current credit and starting credit
	 * @param statsMap - statistics table where rows are like 'HandPower, Occurrences'
	 */
	void displayStats(int credit,float gain,PowerHashMap<String, Integer> statsMap);
	
	/** Display current players credit, using the output handler
	 * @param credit - players credit
	 */
	void displayCredit(int credit);
	
	/** Display a bet value, using the output handler
	 * @param bet - bet value
	 */
	void displayBet(int bet);
	
	/** Display an hand, using the output handler
	 * @param hand - hand, Array of strings in each takes the format 'VS'
	 * 				where V = value, S = suit
	 */
	void displayHand(String[] hand);
	
	/** Display an advice, using the output handler
	 * @param advice - Advice, array of booleans where true means keep
	 * 				and false means discard
	 */
	void displayAdvice(boolean[] advice);
	
	
	/** Display a result and players credit, using the output handler
	 * @param wins - boolean where its defined if the player won or lost
	 * @param handPower - Hand power, hand value - ex: TWO PAIRS
	 * @param credit - Players credit after the result
	 */
	void displayResult(boolean wins,String handPower, int credit);
	
	/**Display an error message, using the output handler
	 * @param reason - String describing the error
	 */
	void displayError(String reason);
}