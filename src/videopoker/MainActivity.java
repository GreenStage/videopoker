package videopoker;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import UserInterface.Action;
import UserInterface.ConsoleReadHandler;
import UserInterface.FileReadHandler;
import UserInterface.ReadHandler;
import UserInterface.TextUI;

public class MainActivity {
	//Action signals
	public static final int ERR_INVALID_MODE = 0x01;
	public static final int ERR_INVALID_CREDIT = 0x02;
	public static final int ERR_INVALID_FILENAME = 0x04;
	public static final int ERR_FILE_NOT_FOUND = 0x08;
	private static TextUI mUI;
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
				mGame = new Game(credit,line.split(" "));

				FileReader fr =  new FileReader(args[2]);
				rh = new FileReadHandler(fr);
				fr.close();
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
			mUI = null;
		}
		else{
			mUI = null;
			System.out.println("Invalid game mode");
			System.exit(ERR_INVALID_MODE);
		}
		
		mUI = new TextUI(rh,System.out);
		if( mGame != null){
			Action a;
			while(( a = mUI.readCommand() ) != null){
				
				switch(a.getType()){
					case Action.ACTION_NONE:
						break;
						
					//Fetching Requests	
					case Action.ACTION_CREDIT:
						mUI.displayCredit(mGame.getCredit());
						break;
						
					case Action.ACTION_STATS:
						// TODO
						//mUI.displayStats(mGame.getStats());
						break;
					
					//Updating requests	
					case Action.ACTION_BET:
						mGame.bet( (a.hasExtra() )?  a.getExtra() : 0, new Game.ActionListener() {
							
							@Override
							public void onSuccess() {
								// TODO Auto-generated method stub
								mUI.displayBet(mGame.getBet());
							}
							
							@Override
							public void onFailure(String reason) {
								mUI.displayError(reason);	
							}
						});
						break;
						
					case Action.ACTION_DEAL:
						mGame.deal(new Game.ActionListener() {		
							@Override
							public void onSuccess() {
								mUI.displayHand( mGame.getPlayer().getHand().getHandStrArr());
							}
							
							@Override
							public void onFailure(String reason) {
								mUI.displayError(reason);
							}
						});
						break;
						
					case Action.ACTION_HOLD:
						boolean[] keepArray = {false,false,false,false,false};
						while(a.hasExtra()){
							keepArray[a.getExtra() - 1] = true;
						}
						mGame.deal(keepArray, new Game.ActionListener() {
							
							@Override
							public void onSuccess() {
								mUI.displayHand( mGame.getPlayer().getHand().getHandStrArr());	
								mUI.displayWin(mGame.getWinStatus(),mGame.getPlayer().getHand().getHandStrArr(),mGame.getCredit());
							}
							
							@Override
							public void onFailure(String reason) {
								mUI.displayError(reason);
							}
						});
						break;
					
					case Action.ACTION_ADVICE:
						mGame.giveAdvice(new Game.ActionListener() {
							@Override
							public void onSuccess() {
								// TODO Auto-generated method stub
								mUI.displayAdvice(mGame.getPlayer().getHand().getHandStrArr(),mGame.getAdvice());
							}
							
							@Override
							public void onFailure(String reason) {
								// TODO Auto-generated method stub
								mUI.displayError(reason);
							}
						});
						
						break;
					default:
						break;
				}
			}
		}

	}
}
