package videopoker.userinterface;

import videopoker.game.Game;

public class SimulationHandler extends IOHandler{
	private int nPlays;
	private int playsIt;
	private int betValue;
	private static enum Steps{BET, DEAL,ADVICE, HOLD};
	String holdValues;
	private Steps mSteps;
	
	public SimulationHandler(int bet_value, int n_play){
		this.nPlays = n_play;
		this.playsIt = n_play;
		this.betValue = bet_value;
		this.mSteps = Steps.BET;
	}
	
	@Override
	public String read() {
		if(playsIt == 0){
			playsIt--;
			return "s";
		}
		else if(playsIt < 0){
			return null;
		}
		
		switch(mSteps){
			case BET:
				mSteps = Steps.DEAL;
				return "b " + String.valueOf(betValue);
			case DEAL:
				mSteps = Steps.ADVICE;
				return "d";
			case ADVICE:
				return "a";
			case HOLD:
				mSteps = Steps.BET;
				playsIt --;
				return "h " + holdValues;
			default:
				return "";
		}
		
	}

	@Override
	public void write(String message) {
		int a = 0;
		if(playsIt < 0){
			System.out.print(message);
		}
		
		else if(mSteps == Steps.ADVICE && message.length() > 24){
			String messageContent = message.substring(0,25);
			if(messageContent.equals("player should hold cards ")){
				String toHold;
				if(message.length() > 25 )
					holdValues = message.substring(25);
				else holdValues = "";
				mSteps = Steps.HOLD;
			};
		}
	}
	
}
