package UserInterface;

import java.io.FileReader;
import java.util.Scanner;

public class FileReadHandler extends ReadHandler{
	String mString;
	Scanner mScanner;
	
	public FileReadHandler(FileReader fr){
		Scanner temp = new Scanner(fr);
		this.mString = temp.nextLine();
		temp.close();
		this.mScanner = new Scanner(mString);
	}
	
	@Override
	public String read(){
		
		if(mScanner.hasNext()){
			String retval = mScanner.next();
			while(mScanner.hasNextInt()){
				retval += String.valueOf( mScanner.nextInt() );
			}
			return retval;
		}	
		else return null;
	}
}
