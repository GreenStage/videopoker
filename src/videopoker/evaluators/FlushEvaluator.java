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
 * More specifically, this class searches for a hand with a Flush, i.e. a hand in which all cards
 * have the same suit. 
 * <p> For example: the hand [JS 7S QS AS KS] is an "Flush" hand. </p>
 * 
 * The hand name is : <b>FLUSH</b> (case sensitive)
 *
 */
public class FlushEvaluator extends HandEvaluator implements MainHandEvaluator {
	
	/**
	 * The name of the hand evaluated by the class.
	 * The name of the hand is FLUSH.
	 */
	public final static String handPower = "FLUSH";
	
	@Override
	public boolean hasHandPower(Hand hand) {
		
		if (!(this.hasSameSuit(hand.getCardList())))
			return false;
		
		return true;
	}
	
	@Override
	public boolean[] whereCards(Hand hand) {
		
		if (!(hasHandPower(hand)))
			return new boolean[0];
		
		boolean[] keep = {true, true, true, true, true};
		
		return keep;
	}

	
	@Override
	public String getHandPower(Hand hand){
		if (this.hasHandPower(hand))
			return handPower;
		
		return super.getHandPower(hand);
	}
	
public static void main(String[] args){
		
		Card c1 = new Card(Value.TWO, Suit.DIAMONDS);
		Card c2 = new Card(Value.SIX, Suit.DIAMONDS);
		Card c3 = new Card(Value.THREE, Suit.DIAMONDS);
		Card c4 = new Card(Value.TEN, Suit.DIAMONDS);
		Card c5 = new Card(Value.FOUR, Suit.DIAMONDS);
	
		Hand hand = new Hand(c1,c2,c3,c4,c5);
		FlushEvaluator eval = new FlushEvaluator();
		boolean[] keep = eval.whereCards(hand);
		String h = eval.getHandPower(hand);
		
		System.out.println(h);
		
		for (int i = 0; i < keep.length; i++){
			System.out.println(keep[i]);
	}
}
}
