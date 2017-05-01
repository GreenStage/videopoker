package videopoker;

public class Player {
	private int mCredit;
	private Hand mHand;
	private int bet = 5;
	
	public Player(int credit){
		this.mCredit = credit;
	}
	
	public void setHand(Hand hand){
		this.mHand = hand;
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
	
	public int getBet(){
		return this.bet;
	}
	public void bet(int bet){
		if( bet > 0 ) this.bet = bet;
		this.mCredit -= this.bet;
	}
}
