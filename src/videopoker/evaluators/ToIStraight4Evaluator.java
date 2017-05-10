package videopoker.evaluators;

import videopoker.game.Card;
import videopoker.game.Hand;

/**
 * 
 * This class evaluates if the hand contains 4 consecutive cards.
 * <p> For example: the hand [6S 7S 8C 9H AS] is a "Four To Straight" hand.
 *
 */
public class ToIStraight4Evaluator extends HandEvaluator implements Evaluator{

	@Override
	public boolean[] whereCards(Hand hand) {
		
		Card[] hand_o  = hand.orderByValue();
		
		Card[] test1 = {hand_o[0], hand_o[1], hand_o[2], hand_o[3]};
		Card[] test2 = {hand_o[1], hand_o[2], hand_o[3], hand_o[4]};
		Card[] test3 = {hand_o[4], hand_o[0], hand_o[1], hand_o[2]};
		boolean[] keep1 = {false, false, false, false, false};
		boolean[] keep2 = {false, false, false, false, false};
		boolean[] keep3 = {false, false, false, false, false};
		boolean enter1 = false;
		boolean enter2 = false;
		boolean enter3 = false;
		
		if (!this.hasDuplicates(test1)){
			int numGaps = this.numGaps(test1);
			if (numGaps == 1 || (numGaps == 0 && this.hasAce(test1) && (this.hasTwo(test1) || this.hasKing(test1)))){
				keep1[hand.search(test1[0])] = true;
				keep1[hand.search(test1[1])] = true;
				keep1[hand.search(test1[2])] = true;
				keep1[hand.search(test1[3])] = true;
				enter1 = true;
			}
		}
		
		if (!this.hasDuplicates(test2)){
			int numGaps = this.numGaps(test2);
			if (numGaps == 1 || (numGaps == 0 && this.hasAce(test2) && (this.hasTwo(test2) || this.hasKing(test2)))){
				keep2[hand.search(test2[0])] = true;
				keep2[hand.search(test2[1])] = true;
				keep2[hand.search(test2[2])] = true;
				keep2[hand.search(test2[3])] = true;
					
				enter2 = true;
			}
		}
		
		if (!this.hasDuplicates(test3)){
			int numGaps = this.numGaps(test3);
			if (numGaps == 1 || (numGaps == 0 && this.hasAce(test3) && (this.hasTwo(test3) || this.hasKing(test3)))){
				keep3[hand.search(test3[0])] = true;
				keep3[hand.search(test3[1])] = true;
				keep3[hand.search(test3[2])] = true;
				keep3[hand.search(test3[3])] = true;
				
				enter3 = true;
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
		
		if (enter1 || enter2 || enter3){
			int numH1 = numHighCards(test1);
			int numH2 = numHighCards(test2);
			int numH3 = numHighCards(test3);
			
			if (enter1 && enter2 && enter3){
				if (numH1 >= numH2 && numH1 >= numH3)
					return keep1;
				else if (numH2 >= numH1 && numH2 >= numH3)
					return keep2;
				else
					return keep3;
			}
			else if (enter1 && enter2){
				if (numH1 >= numH2)
					return keep1;
				else
					return keep2;
			}
			else if (enter1 && enter3){
				if (numH1 >= numH3)
					return keep1;
				else
					return keep3;
			}
			else if (enter1)
				return keep1;
			else if (enter2)
				return keep2;
			else 
				return keep3;
		}		

		return new boolean[0];
		
		
	}
	
}
