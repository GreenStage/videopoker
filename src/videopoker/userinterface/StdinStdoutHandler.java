package videopoker.userinterface;

import java.io.FileReader;
import java.util.Scanner;

public class StdinStdoutHandler extends IOHandler{
	Scanner mScanner;
	
	public StdinStdoutHandler(){
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

	@Override
	public void write(String message) {
		System.out.println(message);
		
	}
}
