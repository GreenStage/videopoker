package videopoker.game;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import videopoker.userinterface.GraphicUI;
import videopoker.userinterface.TextUI;
import videopoker.userinterface.UserInterface;
import videopoker.utilities.DebugHandler;
import videopoker.utilities.IOHandler;
import videopoker.utilities.InteractiveHandler;
import videopoker.utilities.SimulationHandler;


public class MainActivity {
	//Action signals
	public static final int ERR_INVALID_MODE = 0x01;
	public static final int ERR_INVALID_CREDIT = 0x02;
	public static final int ERR_INVALID_FILENAME = 0x04;
	public static final int ERR_FILE_NOT_FOUND = 0x08;
	public static final int INVALID_INTEGER = 0x10;
	public static final int INVALID_DEAL_NUMBER = 0x12;
	
	private static UserInterface mUI;
	private static Game mGame;
	
	public static void main(String[] args){
		int credit = 0;
		IOHandler rh = null;
		if(args.length < 2 || Integer.parseInt(args[1])  <= 0){
			System.exit(ERR_INVALID_CREDIT);
		}
		else{
			try{
				credit = Integer.parseInt(args[1]);
			}catch(NumberFormatException e){
				System.out.println(e.getMessage());
				System.exit(INVALID_INTEGER);
			}
		} 
		
		//Interactive mode
		if(args[0].equals("-i")){
			mGame = new Game(credit);
			rh = new InteractiveHandler();
			mUI = new TextUI(mGame,rh);
		}

		else if( args[0].equals("-d") ) {
			if(args.length < 4){
				System.exit(ERR_INVALID_FILENAME);
				//TODO : probably should be smth like "Missing files"
			}
			try{
				FileReader cardReader = new FileReader(args[3]);
				DebugHandler cardRH = new DebugHandler(cardReader);
				String line = cardRH.getLine();
				cardReader.close();

				FileReader fr =  new FileReader(args[2]);
				rh = new DebugHandler(fr);
				fr.close();
				
				mGame = new Game(credit,line.split(" "));
				mUI = new TextUI(mGame,rh);
				
			}
			catch(FileNotFoundException e){
				System.exit(ERR_FILE_NOT_FOUND);
			}
			catch(IOException e){
				e.printStackTrace();
			}
		}
		
		else if( args[0].equals("-s") ){
			
			if (args.length < 4){
				System.exit(INVALID_DEAL_NUMBER);
			}
			
			int betValue = 0, nDeals = 0;
			try{
				betValue = Integer.parseInt(args[2]);
				nDeals = Integer.parseInt(args[3]);
			}catch(NumberFormatException e){
				System.out.println(e.getMessage());
				System.exit(INVALID_INTEGER);
			}
			rh = new SimulationHandler(betValue,nDeals);
			
			mGame = new Game( credit);
			mUI = new TextUI(mGame, rh);
		}
		
		else if( args[0].equals("-g") ){
			mGame = new Game(1000); //TODO
			mUI = new GraphicUI(mGame);
		}
		
		else{
			mUI = null;
			System.out.println("Invalid game mode");
			System.exit(ERR_INVALID_MODE);
		}
		
		if(mUI != null)
			mUI.run();

	}
}
