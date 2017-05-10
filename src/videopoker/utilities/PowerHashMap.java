package videopoker.utilities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;


/**
 * PowerHashMap - HashMap extension with order method
 *
 * @param <K> - Key variable type
 * @param <V> - Value variable types
 */
public class PowerHashMap<K,V> extends HashMap<K,V>{

	public List<K> getOrderedKeys(Comparator<K> comp){
		List<K> retval = new ArrayList<K>(this.keySet());
		Collections.sort(retval,comp);
		return retval;	
	}
}
