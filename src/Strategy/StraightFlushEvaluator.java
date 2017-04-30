package Strategy;

import videopoker.Card;
import videopoker.Hand;
import videopoker.Card.Suit;
import videopoker.Card.Value;

public class StraightFlushEvaluator extends HandEvaluator implements Evaluator {

	public boolean[] whereCards(Hand hand){
		
		Card[] hand_o  = hand.orderByValue();
		
		if(!inOrder(hand_o)){
			return new boolean[0];
		}
		if(!hasSameSuit(hand_o)){
			return new boolean[0];
		}
		
		boolean[] keep = {true, true, true, true, true};

		return keep;
	}
	
	
	public static void main(String[] args){
		
		Card c1 = new Card(Value.TEN, Suit.HEARTS);
		Card c2 = new Card(Value.JACK, Suit.HEARTS);
		Card c3 = new Card(Value.QUEEN, Suit.HEARTS);
		Card c4 = new Card(Value.KING, Suit.HEARTS);
		Card c5 = new Card(Value.ACE, Suit.HEARTS);

		Hand hand = new Hand(c2,c5,c1,c4,c3);
		StraightFlushEvaluator eval = new StraightFlushEvaluator();
		boolean[] keep = eval.whereCards(hand);
		
		for (int i = 0; i < keep.length; i++){
			System.out.println(keep[i]);
		}
		
	}
	
}