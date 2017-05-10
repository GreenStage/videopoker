package videopoker.game;

import videopoker.strategy.Advisor;
import videopoker.strategy.TraditionalStrategy;
import videopoker.utilities.PowerHashMap;

public class Game {
	//Codes
	public static int NOT_ENOUGH_CARDS = 0x1;
	public static int PLAYER_HAS_NO_HAND = 0x2;
	
	private Player mPlayer;
	private Deck mDeck;
	private WinningPrizes mWinningPrizes;

	private Advisor mAdvisor;
	private boolean[] mAdvice;	
	private String handPower = ""; //Should this should be in Hand class?
	private PowerHashMap<String, Integer> winningStats = new PowerHashMap<String,Integer>();
	private boolean wins = false;
	private int lastBet = -1;
	private boolean shuffleDeck; 
	
	public static enum State{
		STATE_IDLE,
		STATE_BET,
		STATE_DEAL
	};
	State mState;
	
	public interface ActionListener{
		public void onSuccess();
		public void onFailure(String reason);
	}
	
	public Game(int credit){
		this.mWinningPrizes = new WinningPrizes();
		for(String s: this.mWinningPrizes.getWinningHands()){
			winningStats.put(s, 0);
		}
		winningStats.put(WinningPrizes.HAND_NONE,0);
		
		this.mPlayer = new Player(credit);
		this.mDeck = new Deck();
		shuffleDeck = true;
		mAdvisor = new Advisor(new TraditionalStrategy());
		
		prepareGame();

	}
	
	public Game(int credit, String[] cards){
		this(credit);
		this.mDeck = new Deck(cards);
		shuffleDeck = false;
		prepareGame();
	}	
	
	private void prepareGame(){
		mState = State.STATE_IDLE;
		this.mPlayer.setHand(null);

		if(shuffleDeck){
			this.mDeck = new Deck();
			mDeck.shuffle();
		}	
	}
	
	public void endGame(ActionListener listener){
		if(mState == State.STATE_IDLE){
			listener.onSuccess();
		}
		else 
			listener.onFailure("cant quit after betting");
	}
	
	public State getState(){
		return this.mState;
	}
	
	public void setCredit(int credit){
		mPlayer.setCredit(credit);
	}
	
	public int getCredit(){
		return mPlayer.getCredit();
	}
	
	public int getBet(){
		return mPlayer.getBet();
	}
	
	public void bet(int value, ActionListener listener){
		if( mState != State.STATE_IDLE){
			listener.onFailure("illegal command");
		}
		else if (mPlayer.getCredit() - value < 0){
			lastBet = -1;
			listener.onFailure("not enough credit");
		}
		else if(value > 5 ) 
			listener.onFailure("illegal amount");
		else if(mPlayer.getCredit() < ( (value == 0)? lastBet : value ) )
			listener.onFailure("");
		else{
			lastBet = value;
			mState = State.STATE_BET;
			mPlayer.bet(value);
			listener.onSuccess();
		}
	}
	
	public Player getPlayer(){
		return this.mPlayer;
	}
	
	public boolean [] getAdvice(){
		return mAdvice;
	}
	
	public void giveAdvice(Game.ActionListener listener){
		if(!mPlayer.hasHand()){
			listener.onFailure("illegal command");
		}
		else{
			this.mAdvice = mAdvisor.giveAdvice(mPlayer.getHand());
			listener.onSuccess();
		}
	}
	
	public void evaluateHand(){
		handPower = mWinningPrizes.getHandPower(mPlayer.getHand());
		int prize = mWinningPrizes.getPrize(handPower,this.mPlayer.getBet());
		
		if(prize == 0){
			onLose();
		}
		else{
			onWin(prize);
		} 
	} 
	
	public boolean getWinStatus(){ return this.wins; }
	
	public boolean[] getAdvice(ActionListener listener){
		if(mState != State.STATE_DEAL){
			listener.onFailure("illegal command");
			return null;
		}
		else{
			listener.onSuccess();
			return mAdvisor.giveAdvice(mPlayer.getHand());
		}
	}
	
	
	public void deal(ActionListener listener){
		if(mDeck.getAmountCards() < 1 || mState == State.STATE_DEAL || lastBet < 0) {
			listener.onFailure("illegal command");
		}
		else{
			try{
				Hand newHand = new Hand(mDeck.popCard(),mDeck.popCard(),
						   	mDeck.popCard(),mDeck.popCard(),mDeck.popCard());
				mPlayer.setHand(newHand);
				mState = State.STATE_DEAL;
				listener.onSuccess();
			}catch(Deck.EmptyDeckException e){
				listener.onFailure("Deck does not have 5 cards to deal");
			}
		}
	}
	
	public void keep(boolean[] keep, ActionListener listener){
		if(mDeck.getAmountCards() < 1 || ! ( mState == State.STATE_DEAL ) ){
			listener.onFailure("illegal command");	
			return;
		}
		else{
			Hand newHand = mPlayer.getHand();
			for(int i = 0; i < 5; i ++){
				try{
					if(keep[i] == false) newHand.setCard(i, mDeck.popCard());
				}
				catch(Deck.EmptyDeckException e){
					listener.onFailure("deck does not have 5 cards to deal");
				}
			}		
		}			

		evaluateHand();
		listener.onSuccess();
		mState = State.STATE_IDLE;
		prepareGame();
	}
	
	public PowerHashMap<String,Integer> getStatistics(){
		return this.winningStats;
	}
	
	private void onLose(){
		wins = false;
		winningStats.put(WinningPrizes.HAND_NONE, 
				winningStats.get(WinningPrizes.HAND_NONE) + 1);
		
	}
	
	private void onWin(int prize){
		this.mPlayer.setCredit(this.mPlayer.getCredit() + prize);
		wins = true;
		if(winningStats.containsKey(handPower)){
			winningStats.put(handPower, winningStats.get(handPower) + 1);
		}
	}
	
	
	public WinningPrizes getWinningPrizes(){
		return this.mWinningPrizes;
	}
	
}
