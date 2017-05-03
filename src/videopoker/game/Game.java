package videopoker.game;

import java.util.HashMap;
import java.util.Map;

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
	private boolean betted = false; 
	
	public interface ActionListener{
		public void onSuccess();
		public void onFailure(String reason);
	}
	
	public Game(int credit){
		this.mWinningPrizes = new WinningPrizes();
		for(String s: this.mWinningPrizes.getWinningHands()){
			winningStats.put(s, 0);
		}
		this.mPlayer = new Player(credit);
		this.mDeck = new Deck();
		mAdvisor = new Advisor(new TraditionalStrategy());

	}
	
	public Game(int credit, String[] cards){
		this(credit);
		this.mDeck = new Deck(cards);
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
	
	public void bet(int value, Game.ActionListener listener){
		if(mPlayer.hasHand() || value > 5 || betted){
			listener.onFailure("b: illegal command");
		}
		else{
			betted = true;
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
			listener.onFailure("a: illegal command");
		}
		else{
			this.mAdvice = mAdvisor.giveAdvice(mPlayer.getHand());
			listener.onSuccess();
		}
	}
	
	public void evaluateHand(){
		int prize = mWinningPrizes.getPrize( mWinningPrizes.getHandPower(mPlayer.getHand()),
				this.mPlayer.getBet());
		if(prize == 0){
			onLose();
		}
		else{
			onWin(prize);
		} 
	} 
	
	public boolean getWinStatus(){ return this.wins; }
	
	public boolean[] getAdvice(ActionListener listener){
		if(!mPlayer.hasHand()){
			listener.onFailure("a: illegal command");
			return null;
		}
		else{
			listener.onSuccess();
			return mAdvisor.giveAdvice(mPlayer.getHand());
		}
	}
	
	
	public void deal(ActionListener listener){
		if(mDeck.getAmountCards() < 1 || !betted || mPlayer.hasHand()){
			listener.onFailure("d: illegal command");
		}
		else{
			Hand newHand = new Hand(mDeck.popCard(),mDeck.popCard(),
						   mDeck.popCard(),mDeck.popCard(),mDeck.popCard());
			mPlayer.setHand(newHand);
			listener.onSuccess();
		}
	}
	
	public void keep(boolean[] keep, ActionListener listener){
		if(!mPlayer.hasHand() || !betted){
			listener.onFailure("h: illegal command");	
			return;
		}
		if(mDeck.getAmountCards() < 1){
			listener.onFailure("h: illegal command");	
		}
		else{
			Hand newHand = mPlayer.getHand();
			for(int i = 0; i < 5; i ++){
				if(keep[i] == false) newHand.setCard(i, mDeck.popCard());
			}		
		}			

		evaluateHand();
		listener.onSuccess();
		resetGame();
	}
	
	private void onLose(){
		wins = false;
		
	}
	
	private void onWin(int prize){
		this.mPlayer.setCredit(this.mPlayer.getCredit() + prize);
		wins = true;
		if(winningStats.containsKey(handPower)){
			winningStats.put(handPower, winningStats.get(handPower) + 1);
		}
	}
	
	private void resetGame(){
		this.betted = false;
		this.mPlayer.setHand(null);
		
		if(mDeck.getSavedDeck() != null)
			this.mDeck = new Deck(this.mDeck.getSavedDeck());
		else this.mDeck = new Deck();
	}
	
	public WinningPrizes getWinningPrizes(){
		return this.mWinningPrizes;
	}
	
}
