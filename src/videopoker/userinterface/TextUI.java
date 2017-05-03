package videopoker.userinterface;

import java.io.PrintStream;

import videopoker.game.Game;


public class TextUI implements UserInterface{

	IOHandler mIOHandler;
	Game mGame;
	
	public TextUI(Game game,IOHandler ioh){
		this.mGame = game;
		this.mIOHandler = ioh;
	}
	
	@Override
	public void run(){
		boolean quit  = false;
		while(!quit){
			String readStr =  mIOHandler.read();
			if(readStr == null){
				quit = true;
			}
			
			String[] command = readStr.split(" ");
			
			if( command[0].equals("b") ){
				int betValue = -1;
				if(command.length > 1){
					if(command[1].matches("^-?\\d+$")){
						betValue = Integer.valueOf(command[1]);
					}
				}
				else{
					betValue = 0;
				}
				if(betValue >= 0 ){
					mGame.bet(betValue , new Game.ActionListener() {
						
						@Override
						public void onSuccess() {
							displayCredit(mGame.getCredit());	
						}
						
						@Override
						public void onFailure(String reason) {
							displayError(reason);
						}
					});	
				}
			}
			
			else if(command[0].equals("$")){
				displayCredit(mGame.getCredit());
			}
			
			else if(command[0].equals("d")){
				mGame.deal(new Game.ActionListener() {
					@Override
					public void onSuccess() {
						displayHand(mGame.getPlayer().getHand().getHandStrArr());
					}
					
					@Override
					public void onFailure(String reason) {
						displayError(reason);						
					}
				});
			}
			
			else if( command[0].equals("h") ){
				boolean[] keepArray = new boolean[] {false,false,false,false,false};
				for(int i = 1; i < command.length; i ++){
					if(command[i].matches("^-?\\d+$") ){
						int index = Integer.valueOf(command[i]);
						if(index > 0 && index < 6){
							keepArray[index - 1] = true;
						}
					}
				}
				mGame.keep(keepArray, new Game.ActionListener() {
					@Override
					public void onSuccess() {
						displayHand( mGame.getPlayer().getHand().getHandStrArr());	
						displayWin(mGame.getWinStatus(),
								mGame.getWinningPrizes().getHandPower(mGame.getPlayer().getHand()),
								mGame.getCredit());
					}
					
					@Override
					public void onFailure(String reason) {
						displayError(reason);						
					}
				});
			}
			else if(command[0].equals("a")){
				mGame.giveAdvice(new Game.ActionListener() {
					@Override
					public void onSuccess() {
						// TODO Auto-generated method stub
						displayAdvice(mGame.getPlayer().getHand().getHandStrArr(),mGame.getAdvice());
					}
					
					@Override
					public void onFailure(String reason) {
						// TODO Auto-generated method stub
						displayError(reason);
					}
				});
			}
			else if(command[0].equals("s")){
				//TODO
			}
		}
	}
	
	public void displayCredit(int credit){
		mIOHandler.write("player's credit is " + String.valueOf(credit) + "\n");
	}
	
	public void displayBet(int bet){
		mIOHandler.write("player is betting " + String.valueOf(bet) + "\n");
	}
	
	public void displayHand(String[] hand){
		mIOHandler.write("player's hand " + String.join(" ", hand) + "\n");
	}

	public void displayAdvice(String[] hand,boolean[] advice){
		String toHold = "";
		for(int i = 0; i < advice.length; i++){
			if(advice[i]==true) toHold += String.valueOf(i +1) + " ";
		}
		mIOHandler.write("player should hold cards " + toHold + "\n");
	}
	
	public void displayWin(boolean wins,String handPower, int credit){
		if(wins){
			mIOHandler.write("players wins with " + handPower + 
								" and his credit is " + String.valueOf(credit) + "\n");
		}
		else{
			mIOHandler.write("players loses and his credit is "
								+ String.valueOf(credit) + "\n");
		}
	}

	public void displayError(String reason){
		mIOHandler.write(reason + "\n");
	}
}
