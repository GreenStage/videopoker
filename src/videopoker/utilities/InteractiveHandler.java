package videopoker.utilities;

import java.util.Scanner;

public class InteractiveHandler extends IOHandler{
	Scanner mScanner;
	
	public InteractiveHandler(){
		this.mScanner = new Scanner(System.in);
	}
	
	@Override
	public String read(){
		return  mScanner.nextLine();
	}

	@Override
	public void write(String message) {
		System.out.println(message);
		
	}
}
