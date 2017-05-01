package videopoker;
import java.util.HashMap;
import java.util.Map;

import Strategy.TraditionalStrategy;

public class Game {
	//Codes
	public static int NOT_ENOUGH_CARDS = 0x1;
	public static int PLAYER_HAS_NO_HAND = 0x2;
	
	private Map<String,int[]>  winningPrizes = new HashMap<String,int[]>();
	private Player mPlayer;
	private Deck mDeck;
	private boolean[] mAdvice;
	private Advisor mAdvisor;
	private String handPower = ""; //Should this should be in Hand class?
	private boolean wins = false;
	private boolean betted = false; 
	public interface ActionListener{
		public void onSuccess();
		public void onFailure(String reason);
	}
	
	public Game(int credit){
		this.mPlayer = new Player(credit);
		this.mDeck = new Deck();
		mAdvisor = new Advisor(new TraditionalStrategy());
		
		this.winningPrizes.put("ROYAL FLUSH",	 new int[] {250,500,750,1000,4000});
		this.winningPrizes.put("FOUR ACES",		 new int[] {160,320,480,640,800});
		this.winningPrizes.put("FOUR 2-4",		 new int[] {80,160,240,320,400});
		this.winningPrizes.put("FOUR 5-K",		 new int[] {50,100,150,200,250});
		this.winningPrizes.put("STRAIGHT FLUSH", new int[] {50,100,150,200,250});
		this.winningPrizes.put("FULL HOUSE",	 new int[] {10,20,30,40,50});
		this.winningPrizes.put("FLUSH",			 new int[] {7,14,21,28,35});
		this.winningPrizes.put("STRAIGHT",		 new int[] {5,10,15,20,25});
		this.winningPrizes.put("THREE OF A KIND",new int[] {3,6,9,12,15});
		this.winningPrizes.put("TWO PAIR",		 new int[] {1,2,3,4,5});
		this.winningPrizes.put("JACKS +",		 new int[] {1,2,3,4,5});
	}
	
	public Game(int credit, String[] cards){
		this.mPlayer = new Player(credit);
		this.mDeck = new Deck(cards);
		mAdvisor = new Advisor(new TraditionalStrategy());
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
		if(mPlayer.getHand() != null || value > 5 || betted){
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
		if(mPlayer.getHand() == null){
			listener.onFailure("a: illegal command");
		}
		else{
			this.mAdvice = mAdvisor.giveAdvice(mPlayer.getHand());
			listener.onSuccess();
		}
	}
	
	// TODO 3( here is where its needed the hands value as a string )
	public void evaluateHand(){
		//TODO	handPower = getHandValue(mPlayer.getHand()); // Here pls welp
		handPower = "STRAIGHT FLUSH"; // just to debug
		if(this.winningPrizes.containsKey(handPower)){
			this.wins = true;
			int winPrize = winningPrizes.get(handPower)[this.mPlayer.getBet() - 1];
			this.mPlayer.setCredit(this.mPlayer.getCredit() + winPrize);
		}
		else{
			this.wins = false;
		}
	} 
	
	public boolean getWinStatus(){ return this.wins; }
	
	public boolean[] getAdvice(ActionListener listener){
		if(mPlayer.getHand() == null){
			listener.onFailure("a: illegal command");
			return null;
		}
		else{
			listener.onSuccess();
			return mAdvisor.giveAdvice(mPlayer.getHand());
		}
	}
	
	
	public void deal(ActionListener listener){
		if(mDeck.getAmountCards() < 1 || !betted || mPlayer.getHand() != null){
			listener.onFailure("d: illegal command");
		}
		else{
			Hand newHand = new Hand(mDeck.popCard(),mDeck.popCard(),
						   mDeck.popCard(),mDeck.popCard(),mDeck.popCard());
			mPlayer.setHand(newHand);
			listener.onSuccess();
		}
	}
	
	public void deal(boolean[] keep, ActionListener listener){
		if(mDeck.getAmountCards() < 1){
			evaluateHand();
			listener.onFailure("h: illegal command");
		}
		else{
			Hand newHand = mPlayer.getHand();
			for(int i = 0; i < 5; i ++){
				if(keep[i] == false) newHand.setCard(i, mDeck.popCard());
			}	
			evaluateHand();
			listener.onSuccess();
			
			//Reset status
			this.betted = false;
			this.wins = false;
			this.mPlayer.setHand(null);
			if(mDeck.getSavedDeck() != null)
				this.mDeck = new Deck(this.mDeck.getSavedDeck());
			else this.mDeck = new Deck();
			

		}
	}
	
	
}
