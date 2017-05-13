package videopoker;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import videopoker.game.Game;
import videopoker.userinterface.GraphicUI;
import videopoker.userinterface.TextUI;
import videopoker.userinterface.UserInterface;
import videopoker.utilities.DebugHandler;
import videopoker.utilities.IOHandler;
import videopoker.utilities.InteractiveHandler;
import videopoker.utilities.SimulationHandler;


/**
 * MainActivity, where the program calls main method,
 * used to validate arguments, instantiate the game object,
 * the UI and the in/out handler accordingly.
 */
public class MainActivity {
	//Error codes
	public static final int ERR_MISSING_ARGS = 1;
	public static final int ERR_INVALID_MODE = 2;
	public static final int ERR_INVALID_CREDIT = 3;
	public static final int ERR_INVALID_FILENAME = 4;
	public static final int ERR_FILE_NOT_FOUND = 5;
	public static final int INVALID_INTEGER = 6;
	public static final int INVALID_DEAL_NUMBER = 7;
	public static final int INVALID_BET_NUMBER = 8;
	
	/**
	 * User interface (can be graphical or textual)
	 */
	private static UserInterface mUI;
	
	/**
	 * Game declaration
	 */
	private static Game mGame;
	
	/**
	 * Main method
	 * @param args program arguments
	 * Instantiate a game , and
	 * starts the UI and the in/out handler 
	 * accordingly to the mode chosen.
	 */
	public static void main(String[] args){
		int credit = 0;
		IOHandler rh = null;
		
		if(args.length < 1){
			System.exit(ERR_MISSING_ARGS);
		}
		
		//Interactive mode
		if(args[0].equals("-i")){
			if(args.length < 2 ){
				System.exit(ERR_MISSING_ARGS);
			}
			else{
				try{
					credit = Integer.parseInt(args[1]);
				}catch(NumberFormatException e){
					System.out.println(e.getMessage());
					System.exit(INVALID_INTEGER);
				}
			} 
			
			mGame = new Game(credit);
			rh = new InteractiveHandler();
			mUI = new TextUI(mGame,rh);
		}
		
		/*Debug mode
		 * 	-Uses Text UI and a DebugHandler to read commands from a file
		 *		and write to the console
		 */
		else if( args[0].equals("-d") ) {
			if(args.length < 4){
				System.exit(ERR_MISSING_ARGS);
			}

			try{
				credit = Integer.parseInt(args[1]);
				
				/*We need to read a line from the cardsfile to set our
				 *starting deck */
				FileReader cardReader = new FileReader(args[3]);
				DebugHandler cardRH = new DebugHandler(cardReader);
				String line = cardRH.getLine();
				cardReader.close();

				FileReader fr =  new FileReader(args[2]);
				rh = new DebugHandler(fr);
				/*We only need a line from the commandFile, 
				 * we can close it after DebugHandler fetches it.*/
				fr.close();
				
				mGame = new Game(credit,line.split(" "));
				mUI = new TextUI(mGame,rh);
				
			}catch(NumberFormatException e){
				System.out.println(e.getMessage());
				System.exit(INVALID_INTEGER);
			}
			catch(FileNotFoundException e){
				System.exit(ERR_FILE_NOT_FOUND);
			}
			catch(IOException e){
				e.printStackTrace();
			}
		}
		
		/*Simulation mode
		 * 	-Uses Text UI and a SimulationHandler to read commands 
		 *   from the game advisor and write the statistics to the
		 *   console.
		 */
		else if( args[0].equals("-s") ){
			int betValue = 0, nDeals = 0;
			
			if (args.length < 4){
				System.exit(ERR_MISSING_ARGS);
			} 

			try{
				credit = Integer.parseInt(args[1]);
				betValue = Integer.parseInt(args[2]);
				nDeals = Integer.parseInt(args[3]);
			}
			catch(NumberFormatException e){
				System.out.println(e.getMessage());
				System.exit(INVALID_INTEGER);
			}
			
			if(betValue > Game.MAX_BET_VALUE || betValue < Game.MIN_BET_VALUE){
				System.exit(INVALID_BET_NUMBER);
			}
			
			if(nDeals == 0){
				System.exit(INVALID_DEAL_NUMBER);
			}
			mGame = new Game( credit);
			rh = new SimulationHandler(mGame,betValue,nDeals);
			mUI = new TextUI(mGame, rh);
		}
		
		/*Graphic mode
		 * 	-Uses a Swing Graphic UI to read actions and display
		 *   events on the screen
		 */
		else if( args[0].equals("-g") ){
			/*Starting credit will be changed after the user
			 * defines it in the UI. */
			mGame = new Game(10); 
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
