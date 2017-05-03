package videopoker.userinterface;

import videopoker.game.Game;

public class AdviceinStdoutHandler extends IOHandler{
	private Game mGame;
	private int nPlays;
	private int playsIt;
	private int betValue;
	private static enum Steps{BET, DEAL, HOLD};
	private Steps mSteps;
	
	public AdviceinStdoutHandler(Game game, int n_play, int bet_value){
		this.mGame = game;
		this.nPlays = n_play;
		this.playsIt = n_play;
		this.betValue = bet_value;
		this.mSteps = Steps.BET;
	}
	@Override
	public String read() {
		if(playsIt <= 0){
			return null;
		}
		playsIt --;
		switch(mSteps){
			case BET:
				break;
			case DEAL:
				break;
			case HOLD:
				break;
		}
		
	}

	@Override
	public void write(String message) {
		// TODO Auto-generated method stub
		
	}
	
}
