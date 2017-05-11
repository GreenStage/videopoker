package videopoker.evaluators;

import videopoker.game.Card;
import videopoker.game.Hand;
import videopoker.game.Card.Suit;
import videopoker.game.Card.Value;

/**
 * This class is part
 * of a group of classes that implements the interface {@link Evaluator} and 
 * scans the hand looking for a 
 * specific card combination.
 * More specifically, this class searches for a hand with a Four of kind, i.e. a hand in which four
 * cards with the same value are present.
 * <p> For example: the hand [6S 6C 6H 6D KD] is an "Four of a kind" hand. </p>
 */

public class FourOfAKindEvaluator extends HandEvaluator implements Evaluator {
	
	@Override
	public boolean[] whereCards(Hand hand){
		
		Card[] hand_o = hand.orderByValue();

		int[] aux = equality(hand_o);
		
		if(aux[0] != 4){
			return new boolean[0];
		}
		
		boolean[] keep = {false, false, false, false, false};
		for(int i = aux[1]; i < aux[1] + aux[0]; i++){
			keep[hand.search(hand_o[i])] = true;
		}

		return keep;
	}
	
	public static void main(String[] args){
		
		Card c1 = new Card(Value.TEN, Suit.HEARTS);
		Card c2 = new Card(Value.SIX, Suit.DIAMONDS);
		Card c3 = new Card(Value.SIX, Suit.CLOVERS);
		Card c4 = new Card(Value.SIX, Suit.SPADES);
		Card c5 = new Card(Value.SIX, Suit.HEARTS);

		Hand hand = new Hand(c1,c2,c3,c4,c5);
		FourOfAKindEvaluator eval = new FourOfAKindEvaluator();
		boolean[] keep = eval.whereCards(hand);
		
		for (int i = 0; i < keep.length; i++){
			System.out.println(keep[i]);
		}
		
	}
}
