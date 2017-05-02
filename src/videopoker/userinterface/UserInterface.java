//TODO, MABBYE USEFUL AS AN ABTRACT CLASS / IntERFACE when GUI implemented

package videopoker.userinterface;

public interface  UserInterface extends Runnable{

	public abstract void displayCredit(int credit);
	public abstract void displayBet(int bet);
	public abstract void displayHand(String[] hand);
	public abstract void displayAdvice(String[] hand,boolean[] advice);
	public abstract void displayWin(boolean wins,String handPower, int credit);
	public abstract void displayError(String reason);
}