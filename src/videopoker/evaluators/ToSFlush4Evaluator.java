package videopoker.evaluators;

import videopoker.game.Card;
import videopoker.game.Hand;
import videopoker.game.Card.Suit;
import videopoker.game.Card.Value;

public class ToSFlush4Evaluator extends HandEvaluator implements Evaluator{
	
	public boolean[] whereCards(Hand hand){
		
		Card[] hand_o  = hand.orderByValueSuit();
		
		Card[] test1 = {hand_o[0], hand_o[1], hand_o[2], hand_o[3]};
		Card[] test2 = {hand_o[1], hand_o[2], hand_o[3], hand_o[4]};
		Card[] test3 = {hand_o[4], hand_o[0], hand_o[1], hand_o[2]};
		
		boolean test1_ss = hasSameSuit(test1);
		boolean test2_ss = hasSameSuit(test2);
		boolean test3_ss = hasSameSuit(test3);
		
		if (test1_ss){
			int numGaps = numGaps(test1);
			if (numGaps == 0 || numGaps == 1){
				boolean[] keep = {false, false, false, false, false};
				keep[hand.search(test1[0])] = true;
				keep[hand.search(test1[1])] = true;
				keep[hand.search(test1[2])] = true;
				keep[hand.search(test1[3])] = true;
				
				return keep;
			}	
		}
		
		if (test2_ss){
			int numGaps = numGaps(test2);
			if (numGaps == 0 || numGaps == 1){
				boolean[] keep = {false, false, false, false, false};
				keep[hand.search(test2[0])] = true;
				keep[hand.search(test2[1])] = true;
				keep[hand.search(test2[2])] = true;
				keep[hand.search(test2[3])] = true;
				
				return keep;
			}	
		}
		
		if (test3_ss){
			int numGaps = numGaps(test3);
			if (numGaps == 0 || numGaps == 1){
				boolean[] keep = {false, false, false, false, false};
				keep[hand.search(test3[0])] = true;
				keep[hand.search(test3[1])] = true;
				keep[hand.search(test3[2])] = true;
				keep[hand.search(test3[3])] = true;
				
				return keep;
			}	
		}
		

		
		return new boolean[0];	
		
	}
	
	
	public static void main(String[] args){
		
		Card c1 = new Card(Value.EIGHT, Suit.CLOVERS);
		Card c2 = new Card(Value.TWO, Suit.CLOVERS);
		Card c3 = new Card(Value.THREE, Suit.CLOVERS);
		Card c4 = new Card(Value.FOUR, Suit.CLOVERS);
		Card c5 = new Card(Value.FIVE, Suit.CLOVERS);

		Hand hand = new Hand(c1,c2,c3,c4,c5);
		ToSFlush4Evaluator eval = new ToSFlush4Evaluator();
		boolean[] keep = eval.whereCards(hand);
		
		for (int i = 0; i < keep.length; i++){
			System.out.println(keep[i]);
		}
		
	}
	
}
