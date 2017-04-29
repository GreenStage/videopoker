package videopoker;

import videopoker.Card.Suit;
import videopoker.Card.Value;

public class ToSFlush3Evaluator extends HandEvaluator implements Evaluator{
	
	public boolean[] whereCards(Hand hand){
		
		Card[] hand_o  = hand.orderByValueSuit();
		
		Card[] test1 = {hand_o[0], hand_o[1], hand_o[2]};
		Card[] test2 = {hand_o[1], hand_o[2], hand_o[3]};
		Card[] test3 = {hand_o[2], hand_o[3], hand_o[4]};
		Card[] test4 = {hand_o[4], hand_o[0], hand_o[2]};
		Card[] test5 = {hand_o[4], hand_o[1], hand_o[2]};
		
		boolean test1_ss = hasSameSuit(test1);
		boolean test2_ss = hasSameSuit(test2);
		boolean test3_ss = hasSameSuit(test3);
		boolean test4_ss = hasSameSuit(test4);
		boolean test5_ss = hasSameSuit(test5);
		
		if (test1_ss){
			int numGaps = this.numGaps(test1);
			if (numGaps < 3){
				boolean[] keep = {false, false, false, false, false};
				keep[hand.search(test1[0])] = true;
				keep[hand.search(test1[1])] = true;
				keep[hand.search(test1[2])] = true;
				
				return keep;
			}
		}
		
		if (test2_ss){
			int numGaps = this.numGaps(test2);
			if (numGaps < 3){
				boolean[] keep = {false, false, false, false, false};
				keep[hand.search(test2[0])] = true;
				keep[hand.search(test2[1])] = true;
				keep[hand.search(test2[2])] = true;
				
				return keep;
			}
		}
		
		if (test3_ss){
			int numGaps = this.numGaps(test3);
			if (numGaps < 3){
				boolean[] keep = {false, false, false, false, false};
				keep[hand.search(test3[0])] = true;
				keep[hand.search(test3[1])] = true;
				keep[hand.search(test3[2])] = true;
				
				return keep;
			}
		}
		
		if (test4_ss){
			int numGaps = this.numGaps(test1);
			if (numGaps < 3){
				boolean[] keep = {false, false, false, false, false};
				keep[hand.search(test4[0])] = true;
				keep[hand.search(test4[1])] = true;
				keep[hand.search(test4[2])] = true;
				
				return keep;
			}
		}
		
		if (test5_ss){
			int numGaps = this.numGaps(test5);
			if (numGaps < 3){
				boolean[] keep = {false, false, false, false, false};
				keep[hand.search(test5[0])] = true;
				keep[hand.search(test5[1])] = true;
				keep[hand.search(test5[2])] = true;
				
				return keep;
			}
		}
		
		return new boolean[0];

    }
	
	
		
	public static void main(String[] args){
		
		Card c1 = new Card(Value.TWO, Suit.HEARTS);
		Card c2 = new Card(Value.FOUR, Suit.HEARTS);
		Card c3 = new Card(Value.JACK, Suit.HEARTS);
		Card c4 = new Card(Value.ACE, Suit.HEARTS);
		Card c5 = new Card(Value.QUEEN, Suit.SPADES);

		Hand hand = new Hand(c1,c2,c3,c4,c5);
		ToSFlush3Evaluator eval = new ToSFlush3Evaluator();
		boolean[] keep = eval.whereCards(hand);
		
		for (int i = 0; i < keep.length; i++){
			System.out.println(keep[i]);
		}
		
	}
}
