package Strategy;

import videopoker.Card;
import videopoker.Hand;
import videopoker.Card.Suit;
import videopoker.Card.Value;

public class ToIStraight4Evaluator extends HandEvaluator implements Evaluator{

	@Override
	public boolean[] whereCards(Hand hand) {
		
		Card[] hand_o  = hand.orderByValue();
		
		Card[] test1 = {hand_o[0], hand_o[1], hand_o[2], hand_o[3]};
		Card[] test2 = {hand_o[1], hand_o[2], hand_o[3], hand_o[4]};
		Card[] test3 = {hand_o[4], hand_o[0], hand_o[1], hand_o[2]};
		
		if (!this.hasDuplicates(test1)){
			int numGaps = this.numGaps(test1);
			if (numGaps == 1 || (numGaps == 0 && this.hasAce(test1) && (this.hasTwo(test1) || this.hasKing(test1)))){
				boolean[] keep = {false, false, false, false, false};
				keep[hand.search(test1[0])] = true;
				keep[hand.search(test1[1])] = true;
				keep[hand.search(test1[2])] = true;
				keep[hand.search(test1[3])] = true;
				
				return keep;
			}
		}
		
		if (!this.hasDuplicates(test2)){
			int numGaps = this.numGaps(test2);
			if (numGaps == 1 || (numGaps == 0 && this.hasAce(test2) && (this.hasTwo(test2) || this.hasKing(test2)))){
				boolean[] keep = {false, false, false, false, false};
				keep[hand.search(test2[0])] = true;
				keep[hand.search(test2[1])] = true;
				keep[hand.search(test2[2])] = true;
				keep[hand.search(test2[3])] = true;
				
				return keep;
			}
		}
		
		if (!this.hasDuplicates(test3)){
			int numGaps = this.numGaps(test3);
			if (numGaps == 1 || (numGaps == 0 && this.hasAce(test3) && (this.hasTwo(test3) || this.hasKing(test3)))){
				boolean[] keep = {false, false, false, false, false};
				keep[hand.search(test3[0])] = true;
				keep[hand.search(test3[1])] = true;
				keep[hand.search(test3[2])] = true;
				keep[hand.search(test3[3])] = true;
				
				return keep;
			}
		}
		
		int[] eq = this.equality(hand_o);
		if ((eq[0] > 2 || eq[2] > 2) || (eq[0]==2 && eq[2] == 2))
			return new boolean[0];
		
		if (eq[0] == 2 || eq[2] == 2){
			Card[] test4 = new Card[4];
			int k = 0;
			for (int i = 0; i < hand_o.length-1; i++){
				if (hand_o[i].getValueWeight() != hand_o[i+1].getValueWeight()){
					test4[k] = hand_o[i];
					k++;
				}
			}
			
			test4[3] = hand_o[4];
			
			int numGaps = this.numGaps(test4);
			if (numGaps == 1 || (numGaps == 0 && this.hasAce(test4) && (this.hasTwo(test4) || this.hasKing(test4)))){
				boolean[] keep = {false, false, false, false, false};
				keep[hand.search(test4[0])] = true;
				keep[hand.search(test4[1])] = true;
				keep[hand.search(test4[2])] = true;
				keep[hand.search(test4[3])] = true;
				
				return keep;
			}
			
		}
			

		return new boolean[0];
		
		
	}
	
	
	public static void main(String[] args){
		
		Card c1 = new Card(Value.TEN, Suit.HEARTS);
		Card c2 = new Card(Value.JACK, Suit.SPADES);
		Card c3 = new Card(Value.QUEEN, Suit.CLOVERS);
		Card c4 = new Card(Value.ACE, Suit.DIAMONDS);
		Card c5 = new Card(Value.ACE, Suit.SPADES);

		Hand hand = new Hand(c1,c2,c3,c4,c5);
		ToIStraight4Evaluator eval = new ToIStraight4Evaluator();
		boolean[] keep = eval.whereCards(hand);
		
		for (int i = 0; i < keep.length; i++){
			System.out.println(keep[i]);
		}
		
	}
}
