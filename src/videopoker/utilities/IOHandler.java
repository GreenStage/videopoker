package videopoker.utilities;


/**
 * IOHandler - An input/output handler interface
 *  used to read and write strings
 */
public interface IOHandler {
	
	/**Declaration of reading method
	 * used to read a command string from an input source
	 * @return read command string.*/
	public String read();
	
	/** Declaration of writing method
	 * used to write  string to an output source
	 */
	public void write(String message);
}
