package videopoker.game;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import videopoker.userinterface.ConsoleReadHandler;
import videopoker.userinterface.FileReadHandler;
import videopoker.userinterface.GraphicUI;
import videopoker.userinterface.ReadHandler;
import videopoker.userinterface.TextUI;
import videopoker.userinterface.UserInterface;


public class MainActivity {
	//Action signals
	public static final int ERR_INVALID_MODE = 0x01;
	public static final int ERR_INVALID_CREDIT = 0x02;
	public static final int ERR_INVALID_FILENAME = 0x04;
	public static final int ERR_FILE_NOT_FOUND = 0x08;
	private static UserInterface mUI;
	private static Game mGame;
	
	public static void main(String[] args){
		int credit = 0;
		ReadHandler rh = null;
		if(args.length < 2 || Integer.parseInt(args[1])  <= 0){
			System.exit(ERR_INVALID_CREDIT);
		}
		else credit = Integer.parseInt(args[1]);
		
		//Interactive mode
		if(args[0].equals("-i")){
			mGame = new Game(credit);
			rh = new ConsoleReadHandler();
			mUI = new TextUI(mGame,rh,System.out);
		}
		
		//Debug mode
		else if( args[0].equals("-d") ) {
			if(args.length < 4){
				System.exit(ERR_INVALID_FILENAME);
				//TODO : probably should be smth like "Missing files"
			}
			try{
				FileReader cardReader = new FileReader(args[3]);
				FileReadHandler cardRH = new FileReadHandler(cardReader);
				String line = cardRH.getLine();
				cardReader.close();

				FileReader fr =  new FileReader(args[2]);
				rh = new FileReadHandler(fr);
				fr.close();
				
				mGame = new Game(credit,line.split(" "));
				mUI = new TextUI(mGame,rh,System.out);
				
			}
			catch(FileNotFoundException e){
				System.exit(ERR_FILE_NOT_FOUND);
			}
			catch(IOException e){
				e.printStackTrace();
			}
		}
		else if( args[0].equals("-s") ){
			mUI = null;
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
		
		mUI.run();

	}
}
