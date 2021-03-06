package videopoker.evaluators;

import videopoker.game.Card;
import videopoker.game.Hand;
import videopoker.game.Card.Suit;
import videopoker.game.Card.Value;
/**
 * 
 * This class is part
 * of a group of classes that implements the interface {@link MainHandEvaluator} and 
 * scans the hand looking for a 
 * specific card combination.
 * More specifically, this class searches for a hand with Four 5-K, i.e. a hand in which four cards
 * are 5, 6, 7, 8, 9, 10, Jack, Queen or King. 
 * <p> For example: the hand [6H 6S 6D 6C KS] is an "Four 5-K" hand. </p>
 *
 * The hand name is : <b>FOUR 5-K</b> (case sensitive)
 */
public class FourFiveKingEvaluator extends FourOfAKindEvaluator implements MainHandEvaluator {
	
	/**
	 * The name of the hand evaluated by the class.
	 * The name of the hand is FOUR 5-K.
	 */
	public static final String handPower = "FOUR 5-K";
	
	@Override
	public boolean hasHandPower(Hand hand) {
		
		boolean[] four = super.whereCards(hand);
		boolean test1 = true;
		boolean test2 = true;
		boolean test3 = true;
		boolean test4 = true;
		boolean test5 = true;
		boolean test6 = true;
		boolean test7 = true;
		boolean test8 = true;
		boolean test9 = true;
		
		if (four.length == 0)
			return false;
		
		Card[] test = new Card[4];
		
		for (int i=0, j=0; i < four.length; i++ ){
			if (four[i] == true){
				test[j] = hand.getCard(i); 
				j++;
			}
		}
		
		for (int i = 0; i < test.length; i++){
			if (test[i].getValue() != '5'){
				test1 = false;
				break;
			}
		}
		
		for (int i = 0; i < test.length; i++){
			if (test[i].getValue() != '6'){
				test2 = false;
				break;
			}
		}
		
		for (int i = 0; i < test.length; i++){
			if (test[i].getValue() != '7'){
				test3 = false;
				break;
			}
		}
		
		for (int i = 0; i < test.length; i++){
			if (test[i].getValue() != '8'){
				test4 = false;
				break;
			}
		}
		
		for (int i = 0; i < test.length; i++){
			if (test[i].getValue() != '9'){
				test5 = false;
				break;
			}
		}
		
		for (int i = 0; i < test.length; i++){
			if (test[i].getValue() != 'T'){
				test6 = false;
				break;
			}
		}
		
		for (int i = 0; i < test.length; i++){
			if (test[i].getValue() != 'J'){
				test7 = false;
				break;
			}
		}
		
		for (int i = 0; i < test.length; i++){
			if (test[i].getValue() != 'Q'){
				test8 = false;
				break;
			}
		}
		
		for (int i = 0; i < test.length; i++){
			if (test[i].getValue() != 'K'){
				test9 = false;
				break;
			}
		}
		
		
		return test1 || test2 || test3 || test4 || test5 || test6 || test7 || test8 || test9;

	}
	
	@Override
	public boolean[] whereCards(Hand hand) {
		
		if (!(hasHandPower(hand)))
			return new boolean[0];
			
		return super.whereCards(hand);
	}
	
	@Override
	public String getHandPower(Hand hand){
		if (this.hasHandPower(hand))
			return handPower;
		
		return super.getHandPower(hand);
	}
	
	public static void main(String[] args){
		
		Card c1 = new Card(Value.TEN, Suit.HEARTS);
		Card c2 = new Card(Value.KING, Suit.DIAMONDS);
		Card c3 = new Card(Value.KING, Suit.CLOVERS);
		Card c4 = new Card(Value.KING, Suit.SPADES);
		Card c5 = new Card(Value.KING, Suit.HEARTS);

		Hand hand = new Hand(c1,c2,c3,c4,c5);
		FourFiveKingEvaluator eval = new FourFiveKingEvaluator();
		boolean[] keep = eval.whereCards(hand);
		
		for (int i = 0; i < keep.length; i++){
			System.out.println(keep[i]);
		}
		
	}

}
