package videopoker;

import videopoker.Card.Suit;
import videopoker.Card.Value;

public class ToRFlush3Evaluator extends HandEvaluator implements Evaluator{

	public boolean[] whereCards(Hand hand){
	
	    Card[] hand_o  = hand.orderByValueSuit();
	
		Card[] test1 = {hand_o[0], hand_o[1], hand_o[2]};
		Card[] test2 = {hand_o[1], hand_o[2], hand_o[3]};
		Card[] test3 = {hand_o[2], hand_o[3], hand_o[4]};
	
		boolean test1_ss = hasSameSuit(test1);
		boolean test2_ss = hasSameSuit(test2);
		boolean test3_ss = hasSameSuit(test3);
		
		if (test1_ss){
			int numRoyalCards = numRoyalCards(test1);
			if (numRoyalCards == 3){
				boolean[] keep = {false, false, false, false, false};
				keep[hand.search(test1[0])] = true;
				keep[hand.search(test1[1])] = true;
				keep[hand.search(test1[2])] = true;
				
				return keep;
			}	
		}
		
		if (test2_ss){
			int numRoyalCards = numRoyalCards(test2);
			if (numRoyalCards == 3){
				boolean[] keep = {false, false, false, false, false};
				keep[hand.search(test2[0])] = true;
				keep[hand.search(test2[1])] = true;
				keep[hand.search(test2[2])] = true;
				
				return keep;
			}	
		}
		
		if (test3_ss){
			int numRoyalCards = numRoyalCards(test3);
			if (numRoyalCards == 3){
				boolean[] keep = {false, false, false, false, false};
				keep[hand.search(test3[0])] = true;
				keep[hand.search(test3[1])] = true;
				keep[hand.search(test3[2])] = true;
				
				return keep;
			}	
		}
		
		return new boolean[0];
		
		
	}
	
	
	public static void main(String[] args){
		
		Card c1 = new Card(Value.TEN, Suit.HEARTS);
		Card c2 = new Card(Value.JACK, Suit.HEARTS);
		Card c3 = new Card(Value.QUEEN, Suit.HEARTS);
		Card c4 = new Card(Value.ACE, Suit.HEARTS);
		Card c5 = new Card(Value.EIGHT, Suit.HEARTS);

		Hand hand = new Hand(c2,c5,c1,c4,c3);
		ToRFlush3Evaluator eval = new ToRFlush3Evaluator();
		boolean[] keep = eval.whereCards(hand);
		
		for (int i = 0; i < keep.length; i++){
			System.out.println(keep[i]);
		}
		
	}
	
	
	
}
