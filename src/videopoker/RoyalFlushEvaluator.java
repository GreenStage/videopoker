package videopoker;

import videopoker.Card.Suit;
import videopoker.Card.Value;

public class RoyalFlushEvaluator extends HandEvaluator implements Evaluator {

	public boolean[] whereCards(Hand hand){
		
		Card[] hand_o  = hand.orderByValueSuit();
		
		if(numRoyalCards(hand_o) < 5){
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
		Card c4 = new Card(Value.ACE, Suit.HEARTS);
		Card c5 = new Card(Value.TWO, Suit.HEARTS);

		Hand hand = new Hand(c2,c5,c1,c4,c3);
		RoyalFlushEvaluator eval = new RoyalFlushEvaluator();
		boolean[] keep = eval.whereCards(hand);
		
		for (int i = 0; i < keep.length; i++){
			System.out.println(keep[i]);
		}
		
	}
	
}
