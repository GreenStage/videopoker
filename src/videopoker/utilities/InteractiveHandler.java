package videopoker.utilities;

import java.util.Scanner;


/** InteractiveHandler - implementation of IOHandler for interactive mode
 * 	reads from the console
 * 	writes to the console
 */
public class InteractiveHandler implements IOHandler{
	
	/**
	 * Instance od a scanner to read from standard input
	 */
	private Scanner mScanner;
	
	/**
	 * Initialise the interactive handler. The content is read from a the console.
	 */
	public InteractiveHandler(){
		this.mScanner = new Scanner(System.in);
	}
	
	@Override
	public String read(){
		/*Blocks until user input*/
		return  mScanner.nextLine();
	}

	@Override
	public void write(String message) {
		System.out.println(message);
	}
}
