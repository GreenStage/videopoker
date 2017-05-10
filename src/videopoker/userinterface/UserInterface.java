//TODO, MABBYE USEFUL AS AN ABTRACT CLASS / IntERFACE when GUI implemented

package videopoker.userinterface;

import videopoker.utilities.PowerHashMap;

/**
 * UserInterface - UI interface, extending a runnable
 *
 */
public interface  UserInterface extends Runnable{

	/**Display the game statistics, using the output handler*/
	public void displayStats(int credit,float gain,PowerHashMap<String, Integer> statsMap);
	
	/**Display current players credit, using the output handler*/
	public abstract void displayCredit(int credit);
	
	/**Display a bet value, using the output handler*/
	public abstract void displayBet(int bet);
	
	/**Display an hand, using the output handler*/
	public abstract void displayHand(String[] hand);
	
	/**Display an advice, using the output handler*/
	public abstract void displayAdvice(boolean[] advice);
	
	/**Display a result and players credit, using the output handler*/
	public abstract void displayResult(boolean wins,String handPower, int credit);
	
	/**Display an error message, using the output handler*/
	public abstract void displayError(String reason);
}