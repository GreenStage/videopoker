package videopoker.userinterface;

import java.util.Comparator;
import java.util.List;

import videopoker.game.Game;
import videopoker.game.PowerHashMap;
import videopoker.utilities.IOHandler;


/**
 * Text User Interface
 * Uses a Input/Output Handler to read a command (string) 
 *  and write an event (string).
 */
public class TextUI implements UserInterface{
	
	/**Default bet value, for when the player wants to use the
	 * previous successful bet */
	public static final int DEFAULT_BETVALUE = 5;
	
	/** Input/Output Handler declaration*/
	private IOHandler mIOHandler;
	
	/** Game object declaration*/
	private Game mGame;
	
	/**Used to perform a bet request in case user 
	 * decides to bet the same amount as the last time*/
	private int lastBet = -1;
	
	/** Loop exiting variable */
	private boolean notExit = true;
	
	/** TextUi constructor
	 * @param game - Game instance
	 * @param ioh - Input/Output handler
	 */
	public TextUI(Game game,IOHandler ioh){
		this.mGame = game;
		this.mIOHandler = ioh;
	}
	
	
	/* main loop method */
	@Override
	public void run(){
		while(true){
			
			/* ask the IO handler for input*/
			String readStr =  mIOHandler.read();
			if(readStr == null){
				break;
			}
			
			/*parse a command in the input string*/
			String[] command = readStr.split(" ");
			
			if( command[0].equals("q") ){
				/*Quit requested, ask the game instance to terminate the game*/
				mGame.endGame(new Game.ActionListener() {
					@Override
					public void onSuccess() {
						notExit = false;	
					}
					
					@Override
					public void onFailure(String reason) {
						displayError("q: " + reason);
					}
				});
				
				if(!notExit) break;
			}
			
			if( command[0].equals("b") ){
				/*Bet action request, ask the game instance to place one*/
				
				int betValue = -1;
				
				/*Check if bet value was specified*/
				if(command.length > 1){
					if(command[1].matches("^-?\\d+$")){
						betValue = Integer.valueOf(command[1]);
					}
				}
				
				/*If no bet value found in the command, try and use the 
				 * last successful bet*/
				else if(lastBet != -1){
					betValue = lastBet;
				}
				
				/*If no previous bet was made, use the default value*/
				else betValue = DEFAULT_BETVALUE;

				final int a = betValue;
				
				/*Try to place a bet*/
				mGame.bet(betValue , new Game.ActionListener() {
					
					@Override
					public void onSuccess() {
						lastBet = a;
						displayBet(mGame.getPlayer().getBet());	
					}
					
					@Override
					public void onFailure(String reason) {
						displayError("b: " + reason);
					}
				});	
				
			}

			else if(command[0].equals("$")){
				/*Fetching request, no influence in the game state
				 *Ask for the player credit.*/
				displayCredit(mGame.getPlayer().getCredit());
			}

			/*Deal action request*/
			else if(command[0].equals("d")){
				
				/*If at least one bet was placed so far, bet with the last value*/
				if(lastBet >= 0 && mGame.getState() == Game.State.STATE_IDLE){
					mGame.bet(lastBet, new Game.ActionListener() {
						
						@Override
						public void onSuccess() {}
						
						@Override
						public void onFailure(String reason) {
							displayError("d: " + reason);
						}
					});
				}
				
				/*Ask the game to deal 5 cards*/
				mGame.deal(new Game.ActionListener() {
					
					@Override
					public void onSuccess() {
						displayHand(mGame.getPlayer().getHand().getHandStrArr());
					}
					
					@Override
					public void onFailure(String reason) {
						displayError("d: " + reason);					
					}
				});

			}
			
			else if( command[0].equals("h") ){
				/*hold action request*/
				
				boolean[] keepArray = new boolean[] {false,false,false,false,false};
				
				/*pass cards to hold from the input string to a boolean array*/
				for(int i = 1; i < command.length; i ++){
					if(command[i].matches("^-?\\d+$") ){
						int index = Integer.valueOf(command[i]);
						if(index > 0 && index < 6){
							keepArray[index - 1] = true;
						}
					}
				}
				
				/*Ask the game to hold cards*/
				mGame.keep(keepArray, new Game.ActionListener() {
					@Override
					public void onSuccess() {
						/*Display players new hand*/
						displayHand( mGame.getPlayer().getHand().getHandStrArr());	
						
						/*Display round result*/
						displayResult(mGame.getWinStatus(),
								mGame.getWinningPrizes().getHandPower(mGame.getPlayer().getHand()),
								mGame.getPlayer().getCredit());
					}
					
					@Override
					public void onFailure(String reason) {
						displayError("h: " + reason);						
					}
				});
			}
			
			
			else if(command[0].equals("a")){
				/*Advice command request, ask the game to compute the best
				  the best advice possible*/
				mGame.giveAdvice(new Game.ActionListener() {
					
					@Override
					public void onSuccess() {
						displayAdvice(mGame.getAdvice());
					}
					
					@Override
					public void onFailure(String reason) {
						displayError("a: " + reason);
					}
				});
			}
			
			/* A fetching command request, get the current game statistics*/
			else if(command[0].equals("s")){
				PowerHashMap<String, Integer> statsMap = mGame.getStatistics();
				displayStats(mGame.getPlayer().getCredit(),
						mGame.getPlayer().getGain(),statsMap);
			}
			else{
				mIOHandler.write(command[0] + ": illegal command");
			}
		}
	}
	
	/*Display the game statistics, using the output handler*/
	@Override
	public void displayStats(int credit,float gain,PowerHashMap<String, Integer> statsMap){
		int total = 0;
		
		/*Order the winning hands from most valuable to least valuable*/
		List<String> PowerHands = statsMap.getOrderedKeys(
				new Comparator<String>(){
					@Override
					public int compare(String o1, String o2) {
						return mGame.getWinningPrizes().getPrize(o2,1) - 
								mGame.getWinningPrizes().getPrize(o1,1);
					}
				});
		
		/*Pass it to the output handler to write the table*/
		mIOHandler.write("Hand\tNb\n");
		for(String s: PowerHands){
			int c = statsMap.get(s);
			total += c;
			mIOHandler.write(s + "\t" + String.valueOf(c) + "\n");
		}
		mIOHandler.write("----------------------\n");
		mIOHandler.write("Total\t" + String.valueOf(total) + "\n");
		mIOHandler.write("----------------------\n");
		mIOHandler.write("Credit\t" + String.valueOf(credit) + " ("+ String.valueOf(gain) 
							+"%)\n" );
	}
	
	/*Display current players credit, using the output handler*/
	public void displayCredit(int credit){
		mIOHandler.write("player's credit is " + String.valueOf(credit) );
	}

	/*Display a bet value, using the output handler*/
	public void displayBet(int bet){
		mIOHandler.write("player is betting " + String.valueOf(bet) );
	}
	
	/*Display an hand, using the output handler*/
	public void displayHand(String[] hand){
		mIOHandler.write("player's hand " + String.join(" ", hand) );
	}

	/*Display an advice, using the output handler*/
	public void displayAdvice(boolean[] advice){
		String toHold = "";
		for(int i = 0; i < advice.length; i++){
			if(advice[i]==true) toHold += String.valueOf(i +1) + " ";
		}
		mIOHandler.write("player should hold cards " + toHold );
	}
	
	/*Display a result and players credit, using the output handler*/
	public void displayResult(boolean wins,String handPower, int credit){
		if(wins){
			mIOHandler.write("players wins with " + handPower + 
								" and his credit is " + String.valueOf(credit) );
		}
		else{
			mIOHandler.write("players loses and his credit is "
								+ String.valueOf(credit) );
		}
	}

	/*Display an error message, using the output handler*/
	public void displayError(String reason){
		mIOHandler.write(reason );
	}
}
