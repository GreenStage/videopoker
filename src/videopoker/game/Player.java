package videopoker.game;

public class Player {
	private int mCredit;
	private int startCredit;
	private Hand mHand;
	private int bet = 5;
	
	public Player(int credit){
		this.mCredit = credit;
		this.startCredit = credit;
	}
	
	public void setHand(Hand hand){
		this.mHand = hand;
	}
	
	public boolean hasHand(){
		return this.mHand != null;
	}
	
	public Hand getHand(){
		return this.mHand;
	}
	

	
	public void setCredit(int credit){
		this.mCredit = credit;
	}
	
	public int getCredit(){
		return this.mCredit;
	}
	public float getGain(){
		return (100 * (float) mCredit / (float) startCredit) ;
	}
	
	public int getBet(){
		return this.bet;
	}
	public void bet(int bet){
		if( bet > 0 ) this.bet = bet;
		this.mCredit -= this.bet;
	}
}
