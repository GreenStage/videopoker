package videopoker.utilities;

import videopoker.game.Game;
import videopoker.strategy.Advisor;

/** SimulationHandler
 *	used for simulation mode
 *	 reads/simulates commands from the Game advisor
 *	 write to the console (only statistics)
 */
public class SimulationHandler implements IOHandler{
	
	/** Enumeration having each step in a simulation round*/
	private static enum Steps{BET, DEAL, ADVICE, HOLD, ENDTURN, DISPSTATS, ENDGAME};
	
	/**rounds iterator*/
	private int playsIt;
	
	/** betValue to bet each round*/
	private int betValue;
	
	/** Game instance, for advising*/
	private Game mGame;

	/**Steps instance*/
	private Steps mSteps;
	
	public SimulationHandler(Game game,int bet_value, int n_play){
		this.playsIt = n_play;
		this.betValue = bet_value;
		this.mGame = game;
		this.mSteps = Steps.BET;
	}
	
	/* Read (simulate) a command string*/
	@Override
	public String read() {
		
		switch(mSteps){
			/*Simulate bet command*/
			case BET:
				mSteps = Steps.DEAL;
				return "b " + String.valueOf(betValue);
				
			/*Simulate deal command*/	
			case DEAL:
				mSteps = Steps.ADVICE;
				return "d";
				
			/*Simulate advice command*/
			case ADVICE:
				mSteps = Steps.HOLD;				
				return "a";
				
			/*Simulate hold command*/
			case HOLD:
				/*Request advisor for an advice*/
				boolean[] advice = mGame.getAdvice();
				String toHold = "";
				
				/*Parse true in the booleans array and convert it 
				 * to an hold comman*/
				for(int i = 0; i < advice.length; i++){
					if(advice[i]==true) toHold += String.valueOf(i +1) + " ";
				}
				
				mSteps = Steps.ENDTURN;
				return "h " + toHold;
				
			/*End of a turn, check if still have rounds to play*/
			case ENDTURN:
				playsIt --;
				if(playsIt == 0){
					mSteps = Steps.DISPSTATS;
				}
				else mSteps = Steps.BET;
				return "";
			
			/*Ask to display statistics*/
			case DISPSTATS:
				mSteps = Steps.ENDGAME;
				return "s";
				
			/*End the game*/
			case ENDGAME:
				return "q";
				
			default:
				return "";
		}
		
	}

	@Override
	public void write(String message) {
		/*it should only print the statistics, and when asked for*/
		if(mSteps == Steps.ENDGAME){
			System.out.print(message);
		}
	}
	
}
