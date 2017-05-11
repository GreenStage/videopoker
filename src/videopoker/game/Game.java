package videopoker.game;

import videopoker.strategy.Advisor;
import videopoker.strategy.TraditionalStrategy;
import videopoker.utilities.PowerHashMap;

/**
 * Game core class, for each received command, performs one or more actions. 
 */
public class Game {
	
	public static final int MIN_BET_VALUE = 1;
	public static final int MAX_BET_VALUE = 5;
	
	/**
	 * Interface for callback on some game methods.
	 * 	used to reply to parent caller if its command was successfully executed.
	 * 	or not.
	 */
	public static interface ActionListener{
		public void onSuccess();
		public void onFailure(String reason);
	}
	
	
	/**
	 * State enumeration, a game can be at only one state at a time
	 */
	public static enum State{
		STATE_IDLE,
		STATE_BET,
		STATE_DEAL
	};
	
	/**
	 * Game current state, for keeping track on the last performed action.
	 */
	private State mState;
	
	
	/**
	 * Player declaration
	 */
	private Player mPlayer;
	
	/**
	 * Deck declaration
	 */
	private Deck mDeck;
	private boolean shuffleDeck; 
	
	/**
	 * Table of winnings in function of a players bet and hand
	 * contains the winning hands and corresponding prizes for each bet
	 */
	private WinningPrizes mWinningPrizes;

	
	/**
	 * Advisor, witch takes care of all the strategy
	 */
	private Advisor mAdvisor;
	private boolean[] mAdvice;	
	
	
	/**
	 * Winning statistics table
	 */
	private PowerHashMap<String, Integer> winningStats = new PowerHashMap<String,Integer>();
	
	private boolean wins = false;

	
	/**
	 * Game constructor for a normal deck
	 * 
	 * @param credit 
	 * 		Players starting credit
	 * 
	 * @throws IllegalArgumentException
	 * 		if credit is not a positive number
	 */
	public Game(int credit) throws IllegalArgumentException{
		
		if(credit < 1){
			throw new IllegalArgumentException("credit is not a positive number");
		}
		else{
			this.mWinningPrizes = new WinningPrizes();
			for(String s: this.mWinningPrizes.getWinningHands()){
				winningStats.put(s, 0);
			}
			winningStats.put(WinningPrizes.HAND_NONE,0);
			
			this.mPlayer = new Player(credit);

			shuffleDeck = true;
			
			mAdvisor = new Advisor(new TraditionalStrategy());
			
			prepareRound();		
		}	
	}
	
	/**
	 * Game constructor for predefined deck
	 * 
	 * @param credit 
	 * 		Players starting credit
	 * 
	 * @param cards
	 * 		String array containing the deck cards literal description
	 * 
	 * @throws IllegalArgumentException
	 * 		if credit is not a positive number
	 * 		if there are not at least 5 cards to create a deck
	 */
	public Game(int credit, String[] cards) throws IllegalArgumentException{
		
		if(credit < 1){
			throw new IllegalArgumentException("credit is not a positive number");
		}
		
		if(cards == null || cards.length < 5){
			throw new IllegalArgumentException("cant create a game without at least 5 cards");
		}
		
		this.mWinningPrizes = new WinningPrizes();
		for(String s: this.mWinningPrizes.getWinningHands()){
			winningStats.put(s, 0);
		}
		winningStats.put(WinningPrizes.HAND_NONE,0);
		
		this.mPlayer = new Player(credit);
		
		mAdvisor = new Advisor(new TraditionalStrategy());
		
		this.mDeck = new Deck(cards);
		shuffleDeck = false;
		
		prepareRound();
	}	
	
	/*********************************
	 * Operating methods
	 ********************************/
	
	/**
	 * Prepares a new round, reseting the games current state, clearing players and
	 * creating a new deck and shuffling it if required.
	 */
	private void prepareRound(){
		mState = State.STATE_IDLE;
		this.mPlayer.setHand(null);

		if(shuffleDeck){
			this.mDeck = new Deck();
			mDeck.shuffle();
		}	
	}
	
	
	/**
	 * Attempts to end the game
	 * @param listener - ActionListener instance
	 * 	-{@link ActionListener#onSuccess()} called if no bet is in place -
	 *  	therefore the user has permission to quit.
	 * 
	 * 	-{@link ActionListener#onFailure(String)} called if there is a bet in place and the  player cannot quit.
	 */
	public void endGame(ActionListener listener){
		if(mState == State.STATE_IDLE){
			listener.onSuccess();
		}
		else 
			listener.onFailure("cant quit after betting");
	}
	
	/**
	 * Attempts to place a bet
	 * @param value bets value to place
	 * @param listener - ActionListener instance
	 *	-{@link ActionListener#onSuccess()} called if the game state does not allows placing bets.
	 * 
	 * 	-{@link ActionListener#onFailure(String)} called if the game state does not allows placing bets.
	 * 			   or if the player tries to bet more than 5 credits or less than 1
	 * 			   or if the player has less credit than he wants to bet
	 */
	public void bet(int value, ActionListener listener){
		
		if( mState != State.STATE_IDLE){
			listener.onFailure("illegal command");
		}
		else if(value > MAX_BET_VALUE  || value < MIN_BET_VALUE){ 
			listener.onFailure("illegal amount");
		}
		else if(mPlayer.getCredit() < value ){
			listener.onFailure("not enough credit to bet " + String.valueOf(value));
		}
		else{
			mState = State.STATE_BET;
			mPlayer.bet(value);
			listener.onSuccess();
		}
	}

	
	/**
	 * Asks the advisor for an advice, giving the current players hand.
	 * @param listener - ActionListener instance
	 * 	-{@link ActionListener#onSuccess()} called if the advice was successfully asked for.
	 * 
	 * 	-{@link ActionListener#onFailure(String)} called if the game state is not at dealing stage.
	 */
	public void giveAdvice(Game.ActionListener listener){
		if(mState != State.STATE_DEAL){
			listener.onFailure("illegal command");
		}
		else{
			this.mAdvice = mAdvisor.giveAdvice(mPlayer.getHand());
			listener.onSuccess();
		}
	}
	
	/**
	 * Evaluates players hand, taking into account the set winnings hands
	 * 			 crosses the betting value with the winning hand prizes array
	 * 			 to get the players prize.
	 */
	public void evaluateHand(){
		mPlayer.getHand().setHandPower(mWinningPrizes.getHandPower(mPlayer.getHand())); 
		int prize = mWinningPrizes.getPrize(mPlayer.getHand().getHandPower(),
				this.mPlayer.getBet());
		
		if(prize == 0){
			onLose();
		}
		else{
			onWin(prize);
		} 
	} 
	
	
	/**
	 * Attempts to deal 5 cards to the player.
	 * @param listener - ActionListener instance
	 * 	-{@link ActionListener#onSuccess()} called if dealing was successful.
	 * 
	 * 	-{@link ActionListener#onFailure(String)}  called if the cards were already dealt.
	 * 			   or if the player did not bet yet in the game
	 * 			   or if the deck does not have 5 cards to pop
	 */
	public void deal(ActionListener listener){
		if(mState != State.STATE_BET) {
			listener.onFailure("illegal command");
		}
		else{
			try{
				Hand newHand = new Hand(mDeck.popCard(),mDeck.popCard(),
						   	mDeck.popCard(),mDeck.popCard(),mDeck.popCard());
				mPlayer.setHand(newHand);
				mState = State.STATE_DEAL;
				listener.onSuccess();
			}
			catch(Deck.EmptyDeckException e){
				listener.onFailure("Deck does not have 5 cards to deal");
			}
			catch(Hand.DuplicateCardException e){
				listener.onFailure(e.getMessage());
			}
		}
	}
	
	/**
	 * Attempts to draw cards, holding those defined in @keep 
	 * @param keep - cards to hold
	 * @param listener - {@link ActionListener} instance
	 * 	-{@link ActionListener#onSuccess()} called if draw was successful.
	 * 
	 * 	-{@link ActionListener#onFailure(String)} called if the cards weren't dealt yet.
	 * 			   or if the deck does not have 5 cards to pop
	 */
	public void keep(boolean[] keep, ActionListener listener){
		if( mState != State.STATE_DEAL  ){
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

		prepareRound();
	}
	
	/**
	 * Called when player loses, updates statistics and sets wins status to false
	 */
	private void onLose(){
		wins = false;
		winningStats.put(WinningPrizes.HAND_NONE, 
				winningStats.get(WinningPrizes.HAND_NONE) + 1);
		
	}
	
	
	/**
	 * Called when player wins, updates statistics and sets wins status to true
	 * @param prize
	 */
	private void onWin(int prize){
		this.mPlayer.setCredit(this.mPlayer.getCredit() + prize);
		wins = true;
		String hP = mPlayer.getHand().getHandPower();
		if(winningStats.containsKey(hP)){
			winningStats.put(hP,
			winningStats.get(hP) + 1);
		}
	}
	
	
	/*********************************
	 * Fetching methods
	 ********************************/

	/** Fetches games current state
	 * @return game state
	 */
	public State getState(){
		return this.mState;
	}
	
	/** Fetches a player instance
	 * @return player object
	 */
	public Player getPlayer(){
		return this.mPlayer;
	}
	
	/** Evaluates if player won current round
	 * @return true if player won this round, false if not
	 */
	public boolean getWinStatus(){
		return this.wins; 
	}
	
	
	/** Fetches current advice
	 * @return boolean array with cards to hold
	 */
	public boolean[] getAdvice(){
		return mAdvice;
	}
	
	/** Fetches current statistic table
	 * @return Statistics table , HandPowerN - Nº occurencesN
	 */
	public PowerHashMap<String,Integer> getStatistics(){
		return this.winningStats;
	}
	
	/** Fetches Winnings prizes tabe
	 * @return winning prizes table , HandPowerN - prize1N,prize2N...
	 */
	public WinningPrizes getWinningPrizes(){
		return this.mWinningPrizes;
	}
	
}
