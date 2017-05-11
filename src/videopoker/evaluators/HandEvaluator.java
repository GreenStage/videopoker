package videopoker.evaluators;

import java.util.Arrays;
import java.util.Comparator;

import videopoker.game.Card;
import videopoker.game.Hand;
import videopoker.game.WinningPrizes;

/**
 * This class provides some utility methods that helps in building classes that implement
 * the {@link Evaluator} interface.
 * 
 * @see videopoker.evaluators.Evaluator
 */

public abstract class HandEvaluator {
	
	/**
	 * Constant string representing the name of the hand. The
	 * default value is HAND_NONE. This value should be overridden by 
	 * subclasses if needed (for example in evaluators
	 * that implement the {@link videopoker.evaluators.MainHandEvaluator} interface.
	 */
	public final static String handPower = WinningPrizes.HAND_NONE;
	
	/**
	 * Checks if the the cards in the array passed as argument have the same suit.
	 * @param set : array of cards to evaluate.
	 * @return true if the cards have all the same suit.
	 */
	protected boolean hasSameSuit(Card[] set){
		int suit = set[0].getSuitWeight();
		
		for (int i=0; i<set.length; i++){
			if (set[i].getSuitWeight() != suit)
				return false;
		}
		
		return true;
	}
	
	/**
	 * Return the typer of the hand as a string. This defaults to HAND_NONE if subclasses do not
	 * override this method.
	 * @param hand : hand to evaluate
	 * @return string with the type of hand.
	 */
	public String getHandPower(Hand hand){
		return handPower;
	}
	
	/**
	 * Counts the number of royal cards in the array of cards passed as argument.
	 * <p> A royal card is a card that can be used to get a Royal Flush, namely a 10, a Jack,
	 * a Queen, a King or an Ace. </p>
	 * @param set : cards to evaluate
	 * @return : number of royal cards in the array
	 */
	protected int numRoyalCards(Card[] set){
		
		int num = 0;
		for (int i=0; i<set.length; i++){
			char cur_val = set[i].getValue();
			if ((cur_val == 'T') || (cur_val == 'J') || (cur_val == 'Q') || (cur_val == 'K') || (cur_val == 'A')){
				num++;
			}
		}
		
		return num;
	}
	
	/**
	 * Counts the number of gaps in a sorted array of cards.
	 * <p> If the array is not sorted, it is sorted by value, then the number of gaps between 
	 * consecutive cards is returned. </p>
	 * @param set : cards to evaluate
	 * @return number of gaps in the passed card set
	 */
	protected int numGaps(Card[] set){		

		if (set.length < 2)
			return 0;
		
		Arrays.sort(set, new Comparator<Card>() {
			
		    public int compare(Card c1, Card c2) {
		        return c1.getValueWeight()-c2.getValueWeight();
		    }
		});
		
		int num_gaps1 = 0;
		int num_gaps2 = 50;
		int cur;
		Card[] set2;
	
		
		if (set[set.length-1].getValue() == 'A'){
			int temp_gaps = 0;
			set2 = new Card[set.length];
			set2[0] = set[set.length-1];
			for (int i = 1; i < set.length; i++){
				set2[i] = set[i-1];
			}
			
			cur = 1;
			int i = 1;
			while (i < set2.length){
				if (set2[i].getValueWeight() != cur+1){
					temp_gaps++;
					cur++;
				}else if (i+1 < set2.length){
					cur = set2[i].getValueWeight();
					i++;
				}else {
					i++;
				}
			}
			
			num_gaps2 = temp_gaps;
		}
		
		cur = set[0].getValueWeight();
		
		int i = 1;
		while (i < set.length){
			if (set[i].getValueWeight() != cur+1){
				num_gaps1++;
				cur++;
			}else if (i+1 < set.length){
				cur = set[i].getValueWeight();
				i++;
			}else {
				i++;
			}
		}
		
		
		return Math.min(num_gaps1, num_gaps2);
	}
	
	/**
	 * Checks if the cards are in perfect sequence. 
	 * <p>Cards are considered to be in perfect sequence 
	 * if, after being sorted by value, two consecutive
	 * cards differ by just one. If cards with the same value are present, the method returns
	 * false in any case.</p>
	 * @param set : cards to evaluate
	 * @return true if the cards are in perfect sequence
	 */
	protected boolean inOrder(Card[] set){
		int nxt_val;
		int cur_val;
		Card[] set2;
		boolean test1 = false;
		boolean test2 = false;
		
		if (set[set.length-1].getValue() == 'A'){
			set2 = new Card[set.length];
			set2[0] = set[set.length-1];
			for (int i = 1; i < set.length; i++){
				set2[i] = set[i-1];
			}
			
			cur_val = 1;
			for (int i=0; i< set2.length - 1; i++){
				nxt_val = set2[i+1].getValueWeight();
				if (nxt_val - cur_val != 1){
					test2 = false;
					break;
				}
				cur_val = nxt_val;
				test2 = true;
			}	
		}
		
		cur_val = set[0].getValueWeight();

		
		for (int i=0; i< set.length - 1; i++){
			nxt_val = set[i+1].getValueWeight();
			if (nxt_val - cur_val != 1){
				test1 = false;
				break;
			}
			cur_val = nxt_val;
			test1 = true;
		}
		
		
		
		return test1 || test2;
	}
	
	/**
	 * Checks if among the cards in the array there's an Ace.
	 * @param set : cards to evaluate.
	 * @return true if an Ace is present
	 */
	protected boolean hasAce(Card[] set){
		
		for (int i = 0; i < set.length; i++){
			if (set[i].getValue() == 'A')
				return true;
		}
		
		return false;
	}
	
	/**
	 * Checks if among the cards in the array there's a Two.
	 * @param set : cards to evaluate.
	 * @return true if a Two is present
	 */
	protected boolean hasTwo(Card[] set){
		
		for (int i = 0; i < set.length; i++){
			if (set[i].getValue() == '2')
				return true;
		}
		
		return false;
	}
	
	/**
	 * Checks if among the cards in the array there's a Three.
	 * @param set : cards to evaluate.
	 * @return true if a Three is present
	 */
	protected boolean hasThree(Card[] set){
		
		for (int i = 0; i < set.length; i++){
			if (set[i].getValue() == '3')
				return true;
		}
		
		return false;
	}
	
	/**
	 * Checks if among the cards in the array there's a Four.
	 * @param set : cards to evaluate.
	 * @return true if a Four is present
	 */
	protected boolean hasFour(Card[] set){
		
		for (int i = 0; i < set.length; i++){
			if (set[i].getValue() == '4')
				return true;
		}
		
		return false;
	}
	
	/**
	 * Checks if among the cards in the array there's a King.
	 * @param set : cards to evaluate.
	 * @return true if a King is present.
	 */
	protected boolean hasKing(Card[] set){
		
		for (int i = 0; i < set.length; i++){
			if (set[i].getValue() == 'K')
				return true;
		}
		
		return false;
	}
	
	/**
	 * This function tells the number of cards of the same kind, and also the first position in the hand of that kind.
	 * <p> It can also account in case there is a second kind, with 2 or more cards. And will tell once again the number of that kind and its first position in the hand.
	 * <p> For example: the hand [3S 3C 6S 9H 9C] has 2 kinds with more than 1 card of the same kind: 3 and 9.
	 * <p> It returns an array with the following structure: [n1 p1 n2 p2].
	 * <ul>
     * <li>n1 - number of cards of the same kind, of the first kind</li>
     * <li>p1 - position of the first card, of the first kind</li>
     * <li>n2 - number of cards of the same kind, of the second kind</li>
     * <li>p2 - position of the first card, of the second kind</li>    
     * </ul>  
     * If it exists only one kind with more than 1 card, the last two parameter, of the return, will be set zero.
     * If it doesn't exist any kind with more than 1 card, then the return matrix will be [0 0 0 0].
     * <p> Examples: </p>
     * <ul>
     * <li>1 - with the hand [3C 3S 5S 9H TC], this function returns [2 1 0 0]. Since there's only one kind with more than 1 card, the last two elements are set to zero.</li>
     * <li>2 - with the hand [3C 3S 9S 9H 9C], this function returns [2 0 3 2].
     * <li>3 - with the hand [3C 3S 3H 9H 9C], this function returns [3 0 2 3].
     * <li>3 - with the hand [2C 3S 3H 9H 9C], this function returns [2 1 2 3].     
     * </ul>
	 * @param set : sorted hand
	 * @return an array that indicates the number of cards of the same kind, and its starting positions.
	 */
	protected int[] equality(Card[] set){
		
		int[] eq = new int[4];
		int size_eq = 0;
		
		int cur_val = set[0].getValueWeight();
		int nxt_val;
		int cnt = 1;

		for (int i=0; i < set.length - 1; i++){
			nxt_val = set[i + 1].getValueWeight();
			if(nxt_val == cur_val){
				cnt++;
				if(i == set.length - 2){
					eq[size_eq] = cnt;
					eq[size_eq+1] = i - cnt + 2;
					size_eq += 2;
					cnt = 1;
				}
			}else{
				if(cnt > 1){
					eq[size_eq] = cnt;
					eq[size_eq+1] = i - cnt + 1;
					size_eq += 2;
					cnt = 1;
				}
				cur_val = nxt_val;
			}
		}
		
		return eq;
	}
	
	/**
	 * Counts the number of high cards in the array of cards passed as argument.
	 * <p>An high card is one of the followings: Jack, Queen, King, Ace</p>.
	 * @param set : cards to evaluate.
	 * @return number of high cards in the array.
	 */
	protected int numHighCards(Card[] set){
		
		int num_cards = 0;
		
		for (int i=0; i<set.length; i++){
			if (set[i].isHighCard())
				num_cards++;
		}
		
		return num_cards;
	}
	
	/**
	 * Checks if there are cards with the same value in the array passed as argument.
	 * @param set : cards to evaluate.
	 * @return true if at least two cards with the same value are present in the array
	 */
	protected boolean hasDuplicates(Card[] set){
		
		Arrays.sort(set, new Comparator<Card>() {
			
		    public int compare(Card c1, Card c2) {
		        return c1.getValueWeight()-c2.getValueWeight();
		    }
		});
		
		int cur = set[0].getValueWeight();
		int i = 1;
		
		while (i < set.length){
			if (set[i].getValueWeight() == cur){
				return true;
			}else if (i+1 < set.length){
				cur = set[i].getValueWeight();
				i++;
			}else {
				i++;
			}
		}
		
		return false;
		
	}	
}
