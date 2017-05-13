package videopoker.utilities;

import java.io.FileReader;
import java.util.Scanner;


/** DebugHandler - implementation of IOHandler for the debug mode.
 * 	Reads from a file and writes to the console
 */
public class DebugHandler implements IOHandler{
	/**
	 * Current line
	 */
	private String mLine;
	
	/**
	 * Instance of scanner to read the file.
	 */
	private Scanner mScanner;
	
	/**
	 * Initialise the debug handler, reads the content of the file and save it as a string.
	 * @param fr - file reader instance
	 */
	public DebugHandler(FileReader fr){
		Scanner sc = new Scanner(fr);
		this.mLine = "";
		while(sc.hasNextLine()){
			this.mLine += sc.nextLine();
		}

		/*We only need a string, we can now close the scanner*/
		sc.close();
		this.mScanner = new Scanner(this.mLine);
	}
	

	@Override
	public String read(){
		/* read next command if exists */
		if(mScanner.hasNext()){
			String retval = mScanner.next();
			
			/*Check for integers extras in the String*/
			while(mScanner.hasNextInt()){
				retval += " " + String.valueOf( mScanner.nextInt() );
			}
			
			/*Write found command*/
			write("");
			write("-cmd " + retval);
			return retval;
		}	
		else return null;
	}
	/**
	 * 	Returns the current input String
	 */
	public String getLine(){
		return mLine;
	}
	
	@Override
	public void write(String message) {
		System.out.println(message);
		
	}
}
