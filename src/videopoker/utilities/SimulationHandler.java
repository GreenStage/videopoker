package videopoker.utilities;

import videopoker.game.Game;
import videopoker.strategy.Advisor;

public class SimulationHandler extends IOHandler{
	private int playsIt;
	private int betValue;
	private Game mGame;
	private static enum Steps{BET, DEAL,ADVICE, HOLD};
	private Steps mSteps;
	private int nPlays;
	
	public SimulationHandler(Game game,int bet_value, int n_play){
		this.nPlays = n_play;
		this.playsIt = n_play;
		this.betValue = bet_value;
		this.mGame = game;
		this.mSteps = Steps.BET;
	}
	
	@Override
	public String read() {
		if(playsIt == -1){
			playsIt--;
			return "s";
		}
		else if(playsIt == -2){
			return null;
		}
		
		switch(mSteps){
			case BET:
				playsIt --;
				mSteps = Steps.DEAL;
				return "b " + String.valueOf(betValue);
			case DEAL:
				mSteps = Steps.ADVICE;
				return "d";
			case ADVICE:
				mSteps = Steps.HOLD;				
				return "a";
			case HOLD:
				boolean[] advice = mGame.getAdvice();
				String toHold = "";
				for(int i = 0; i < advice.length; i++){
					if(advice[i]==true) toHold += String.valueOf(i +1) + " ";
				}
				mSteps = Steps.BET;
				return "h " + toHold;
			default:
				return "";
		}
		
	}

	@Override
	public void write(String message) {
		if(playsIt < -1){
			System.out.print(message);
		}
	}
	
}
