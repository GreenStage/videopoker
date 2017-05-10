package videopoker.evaluators;

import videopoker.game.Card;
import videopoker.game.Hand;
import videopoker.game.Card.Suit;
import videopoker.game.Card.Value;

/**
 * 
 * This class evaluates if the hand contains a Straight, which means having all the 5 cards consecutive, no matter the suit.
 *  <p> For example: the hand [4S 5C 6S 7S 8C] is a "Flush" hand.
 *
 */
public class StraightEvaluator extends HandEvaluator implements MainHandEvaluator {

	public final static String handPower = "STRAIGHT";

	
	@Override
	public boolean hasHandPower(Hand hand) {
		
		Card[] hand_o = hand.orderByValue();
		if (!(this.inOrder(hand_o)))
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
		
		Card c1 = new Card(Value.ACE, Suit.DIAMONDS);
		Card c2 = new Card(Value.TWO, Suit.HEARTS);
		Card c3 = new Card(Value.THREE, Suit.HEARTS);
		Card c4 = new Card(Value.FOUR, Suit.HEARTS);
		Card c5 = new Card(Value.FIVE, Suit.HEARTS);

		Hand hand = new Hand(c2,c5,c1,c4,c3);
		StraightEvaluator eval = new StraightEvaluator();
		boolean[] keep = eval.whereCards(hand);
		
		for (int i = 0; i < keep.length; i++){
			System.out.println(keep[i]);
		}
		
	}

}
