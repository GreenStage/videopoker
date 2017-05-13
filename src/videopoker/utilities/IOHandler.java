package videopoker.utilities;


/**
 * IOHandler - An input/output handler interface
 *  used to read and write strings
 */
public interface IOHandler {
	
	/**Declaration of reading method
	 * used to read a command string from an input source
	 * @return command string.*/
	String read();
	
	/** Declaration of writing method
	 * used to write string to an output source
	 * @param message - string to write
	 */
	void write(String message);
}
