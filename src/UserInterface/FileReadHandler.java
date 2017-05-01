package UserInterface;

import java.io.FileReader;
import java.util.Scanner;

public class FileReadHandler extends ReadHandler{
	String mLine;
	Scanner mScanner;
	
	public FileReadHandler(FileReader fr){
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
				retval += String.valueOf( mScanner.nextInt() );
			}
			return retval;
		}	
		else return null;
	}
	
	public String getLine(){
		return mLine;
	}
}
