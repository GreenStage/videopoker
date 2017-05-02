package videopoker.userinterface;

import java.io.PrintStream;

public class TextUI {

	PrintStream mOutputStream;
	ReadHandler mInputHandler;
	
	public TextUI(ReadHandler inputHandler, PrintStream outStream){
		mInputHandler = inputHandler;
		this.mOutputStream = outStream;
	}

	public Action readCommand(){
		String readStr =  mInputHandler.read();
		if(readStr == null){
			return null;
		}
		String[] command = readStr.split(" ");
		
		if( command[0].equals("b") ){
			Action retval = new Action(Action.ACTION_BET);
			for(int i = 1; i < command.length; i ++){
				if(command[i].matches("^-?\\d+$")){
					int extra = Integer.parseInt(command[i]);
					retval.putExtra(extra);
				}
			}
			return retval;
		}
		else if(command[0].equals("$")){
			return new Action(Action.ACTION_CREDIT);
		}
		else if(command[0].equals("d")){
			return new Action(Action.ACTION_DEAL);
		}
		else if( command[0].equals("h") ){
			Action retval = new Action(Action.ACTION_HOLD);
			for(int i = 1; i < command.length; i ++){
				if(command[i].matches("^-?\\d+$")){
					int extra = Integer.parseInt(command[i]);
					retval.putExtra(extra);
				}
			}
			return retval;
		}
		else if(command[0].equals("a")){
			return new Action(Action.ACTION_ADVICE);
		}
		else if(command[0].equals("s")){
			return new Action(Action.ACTION_STATS);
		}
		else return new Action(Action.ACTION_NONE);
	}
	
	
	public void displayCredit(int credit){
		mOutputStream.print("player's credit is " + String.valueOf(credit) + "\n");
	}
	
	public void displayBet(int bet){
		mOutputStream.print("player is betting " + String.valueOf(bet) + "\n");
	}
	
	public void displayHand(String[] hand){
		mOutputStream.print("player's hand " + String.join(" ", hand) + "\n");
	}

	public void displayAdvice(String[] hand,boolean[] advice){
		String toHold = "";
		for(int i = 0; i < advice.length; i++){
			if(advice[i]==true) toHold += String.valueOf(i +1) + " ";
		}
		mOutputStream.print("player should hold cards " + toHold + "\n");
	}
	
	public void displayWin(boolean wins,String handPower, int credit){
		if(wins){
			mOutputStream.print("players wins with " + handPower + 
								" and his credit is " + String.valueOf(credit) + "\n");
		}
		else{
			mOutputStream.print("players loses and his credit is "
								+ String.valueOf(credit) + "\n");
		}
	}

	public void displayError(String reason){
		mOutputStream.print(reason + "\n");
	}
}
