package videopoker.game;


/**
 * Player class
 * Takes care of players credit, bets and hands
 */
public class Player {
	/**
	 * Credit of the player
	 */
	private int mCredit;
	
	/**
	 * Initial credit of the player
	 */
	private int startCredit;
	
	/**
	 * Current player hand;
	 */
	private Hand mHand;
	
	/**
	 * Initial bet of the player;
	 */
	private int bet = 5;
	
	/** Players constructor
	 * @param credit - players starting credit
	 */
	public Player(int credit){
		this.mCredit = credit;
		this.startCredit = credit;
	}
	
	/** Sets player hand
	 * @param hand - an Hand instance
	 */
	public void setHand(Hand hand){
		this.mHand = hand;
	}
	
	/** Evaluates if player has an hand
	 * @return boolean , true if player has an hand, false otherwise
	 */
	public boolean hasHand(){
		return this.mHand != null;
	}
	
	/** Fetches players hand
	 * @return Hand instance, players current hand
	 */
	public Hand getHand(){
		return this.mHand;
	}
	
	/** Sets player credit
	 * @param credit - new players credit
	 */
	public void setCredit(int credit){
		this.mCredit = credit;
	}
	
	/** Fetches players current credit
	 * @return integer, players current credit
	 */
	public int getCredit(){
		return this.mCredit;
	}
	
	/** Fetches players current gain
	 * @return float, gain = percentage between players current and starting credit
	 */
	public float getGain(){
		return (100 * (float) mCredit / (float) startCredit) ;
	}
	
	/** Fetches players current bet
	 * @return integer, value of current bet
	 */
	public int getBet(){
		return this.bet;
	}
	
	/** Places a new bet, updating the players current credit aswell
	 * @param bet - bet to place
	 */
	public void bet(int bet){
		if( bet > 0 ) this.bet = bet;
		this.mCredit -= this.bet;
	}
}
