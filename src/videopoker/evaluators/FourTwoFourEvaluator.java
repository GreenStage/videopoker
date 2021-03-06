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
 * More specifically, this class searches for a hand with Four 2-4, i.e. a hand in which four cards
 * are 2, 3 or 4. 
 * <p> For example: the hand [4H 4S 4D 4C KS] is a "Four 2-4" hand. </p>
 *
 * The hand name is : <b>FOUR 2-4</b> (case sensitive)
 */
public class FourTwoFourEvaluator extends FourOfAKindEvaluator implements MainHandEvaluator {

	/**
	 * The name of the hand evaluated by the class.
	 * The name of the hand is FOUR 2-4.
	 */
	public static final String handPower = "FOUR 2-4";
	
	@Override
	public boolean hasHandPower(Hand hand) {
		
		boolean[] four = super.whereCards(hand);
		boolean test1 = true;
		boolean test2 = true;
		boolean test3 = true;
		boolean test4 = true;

		
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
			if (test[i].getValue() != '2'){
				test1 = false;
				break;
			}
		}
		
		for (int i = 0; i < test.length; i++){
			if (test[i].getValue() != '3'){
				test2 = false;
				break;
			}
		}
		
		for (int i = 0; i < test.length; i++){
			if (test[i].getValue() != '3'){
				test3 = false;
				break;
			}
		}
		
		for (int i = 0; i < test.length; i++){
			if (test[i].getValue() != '4'){
				test4 = false;
				break;
			}
		}
		
		
		return test1 || test2 || test3 || test4;

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
		Card c2 = new Card(Value.FOUR, Suit.DIAMONDS);
		Card c3 = new Card(Value.FOUR, Suit.CLOVERS);
		Card c4 = new Card(Value.FOUR, Suit.SPADES);
		Card c5 = new Card(Value.FOUR, Suit.HEARTS);

		Hand hand = new Hand(c1,c2,c3,c4,c5);
		FourTwoFourEvaluator eval = new FourTwoFourEvaluator();
		boolean[] keep = eval.whereCards(hand);
		
		for (int i = 0; i < keep.length; i++){
			System.out.println(keep[i]);
		}
		
	}

}
