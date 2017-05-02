package videopoker.userinterface;

import java.util.Scanner;

public class ConsoleReadHandler extends ReadHandler{
	
	public ConsoleReadHandler(){
		this.mScanner = new Scanner(System.in);
	}
	
	@Override
	public String read(){
		String retval = mScanner.nextLine();
		if(retval.equals("q")){
			return null;
		}
		else return retval;
	}
}