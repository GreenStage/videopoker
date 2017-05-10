package videopoker.userinterface;

import java.util.Comparator;
import java.util.List;

import videopoker.game.Game;
import videopoker.utilities.PowerHashMap;


public class TextUI implements UserInterface{

	private IOHandler mIOHandler;
	private Game mGame;
	private int lastBet = -1;
	private boolean notExit = true;
	
	public TextUI(Game game,IOHandler ioh){
		this.mGame = game;
		this.mIOHandler = ioh;
	}
	
	@Override
	public void run(){
		while(true){
			String readStr =  mIOHandler.read();
			if(readStr == null){
				break;
			}
			
			String[] command = readStr.split(" ");
			
			if( command[0].equals("q") ){
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
				int betValue = -1;
				if(command.length > 1){
					if(command[1].matches("^-?\\d+$")){
						betValue = Integer.valueOf(command[1]);
					}
				}
				else if(lastBet != -1){
					betValue = lastBet;
				}
				else betValue = 5;
				
				if(betValue >= 0 ){
					final int a = betValue;
					mGame.bet(betValue , new Game.ActionListener() {
						
						@Override
						public void onSuccess() {
							lastBet = a;
							displayBet(mGame.getBet());	
						}
						
						@Override
						public void onFailure(String reason) {
							displayError("b: " + reason);
						}
					});	
				}
			}
			
			else if(command[0].equals("$")){
				displayCredit(mGame.getCredit());
			}
			
			else if(command[0].equals("d")){
				if(lastBet >= 0 && mGame.getState() == Game.State.STATE_IDLE){
					mGame.bet(lastBet, new Game.ActionListener() {
						
						@Override
						public void onSuccess() {
							// TODO Auto-generated method stub
							
						}
						
						@Override
						public void onFailure(String reason) {
							displayError("d: " + reason);
						}
					});
				}
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
						displayError("h: " + reason);						
					}
				});
			}
			
			else if(command[0].equals("a")){
				mGame.giveAdvice(new Game.ActionListener() {
					@Override
					public void onSuccess() {
						// TODO Auto-generated method stub
						displayAdvice(mGame.getAdvice());
					}
					
					@Override
					public void onFailure(String reason) {
						// TODO Auto-generated method stub
						displayError("a: " + reason);
					}
				});
			}
			
			else if(command[0].equals("s")){
				PowerHashMap<String, Integer> statsMap = mGame.getStatistics();
				displayStats(mGame.getCredit(),mGame.getPlayer().getGain(),statsMap);
			}
		}
	}
	
	@Override
	public void displayStats(int credit,float gain,PowerHashMap<String, Integer> statsMap){
		int total = 0;
		List<String> PowerHands = statsMap.getOrderedKeys(
				new Comparator<String>(){
					@Override
					public int compare(String o1, String o2) {
						return mGame.getWinningPrizes().getPrize(o2,1) - 
								mGame.getWinningPrizes().getPrize(o1,1);
					}
				});
		
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
	
	public void displayCredit(int credit){
		mIOHandler.write("player's credit is " + String.valueOf(credit) );
	}
	
	public void displayBet(int bet){
		mIOHandler.write("player is betting " + String.valueOf(bet) );
	}
	
	public void displayHand(String[] hand){
		mIOHandler.write("player's hand " + String.join(" ", hand) );
	}

	public void displayAdvice(boolean[] advice){
		String toHold = "";
		for(int i = 0; i < advice.length; i++){
			if(advice[i]==true) toHold += String.valueOf(i +1) + " ";
		}
		mIOHandler.write("player should hold cards " + toHold );
	}
	
	public void displayWin(boolean wins,String handPower, int credit){
		if(wins){
			mIOHandler.write("players wins with " + handPower + 
								" and his credit is " + String.valueOf(credit) );
		}
		else{
			mIOHandler.write("players loses and his credit is "
								+ String.valueOf(credit) );
		}
	}

	public void displayError(String reason){
		mIOHandler.write(reason );
	}
}
