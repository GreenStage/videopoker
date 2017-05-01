package Strategy;

import videopoker.Card;
import videopoker.Hand;
import videopoker.Card.Suit;
import videopoker.Card.Value;

public class ToFlush4Evaluator extends HandEvaluator implements Evaluator{
	
	public boolean[] whereCards(Hand hand){
		
		Card[] hand_o  = hand.orderByValueSuit();
		
		Card[] test1 = {hand_o[0], hand_o[1], hand_o[2], hand_o[3]};
		Card[] test2 = {hand_o[1], hand_o[2], hand_o[3], hand_o[4]};
		
		boolean test1_ss = hasSameSuit(test1);
		boolean test2_ss = hasSameSuit(test2);
		
	
		if (test1_ss){
			boolean[] keep = {false, false, false, false, false};
			keep[hand.search(test1[0])] = true;
			keep[hand.search(test1[1])] = true;
			keep[hand.search(test1[2])] = true;
			keep[hand.search(test1[3])] = true;
			
			return keep;
		}
		
		if (test2_ss){
			boolean[] keep = {false, false, false, false, false};
			keep[hand.search(test2[0])] = true;
			keep[hand.search(test2[1])] = true;
			keep[hand.search(test2[2])] = true;
			keep[hand.search(test2[3])] = true;
			
			return keep;
		}
		
		return new boolean[0];	
		
	}
	
	public static void main(String[] args){
		
		Card c1 = new Card(Value.TEN, Suit.HEARTS);
		Card c2 = new Card(Value.JACK, Suit.HEARTS);
		Card c3 = new Card(Value.QUEEN, Suit.HEARTS);
		Card c4 = new Card(Value.EIGHT, Suit.HEARTS);
		Card c5 = new Card(Value.EIGHT, Suit.CLOVERS);

		Hand hand = new Hand(c1,c2,c3,c4,c5);
		ToFlush4Evaluator eval = new ToFlush4Evaluator();
		boolean[] keep = eval.whereCards(hand);
		
		for (int i = 0; i < keep.length; i++){
			System.out.println(keep[i]);
		}
		
	}
	
	
}
