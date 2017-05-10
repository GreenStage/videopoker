package videopoker.utilities;

import java.io.FileReader;
import java.util.Scanner;

public class DebugHandler extends IOHandler{
	String mLine;
	Scanner mScanner;
	
	public DebugHandler(FileReader fr){
		Scanner temp = new Scanner(fr);
		this.mLine = temp.nextLine();
		temp.close();
		this.mScanner = new Scanner(mLine);
	}
	
	@Override
	public String read(){
		
		if(mScanner.hasNext()){
			String retval = mScanner.next();
			while(mScanner.hasNextInt()){
				retval += " " + String.valueOf( mScanner.nextInt() );
			}
			write("");
			write("-cmd " + retval);
			return retval;
		}	
		else return null;
	}
	
	public String getLine(){
		return mLine;
	}

	@Override
	public void write(String message) {
		System.out.println(message);
		
	}
}
