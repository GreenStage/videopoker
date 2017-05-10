package videopoker.utilities;

public class SimulationHandler extends IOHandler{
	private int playsIt;
	private int betValue;
	private static enum Steps{BET, DEAL,ADVICE, HOLD};
	String holdValues;
	private Steps mSteps;
	private int nPlays;
	
	public SimulationHandler(int bet_value, int n_play){
		this.nPlays = n_play;
		this.playsIt = n_play;
		this.betValue = bet_value;
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
				mSteps = Steps.BET;
				return "h " + holdValues;
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
