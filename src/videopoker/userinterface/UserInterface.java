//TODO, MABBYE USEFUL AS AN ABTRACT CLASS / IntERFACE when GUI implemented


package videopoker.userinterface;

public abstract class UserInterface {
	public abstract void displayCredit(int credit);
	public abstract void displayBet(int bet);
	public abstract void displayHand(String[] hand);
	public abstract void displayAdvice(String[] hand,boolean[] advice);
	
	public abstract void displayError(String reason);
}