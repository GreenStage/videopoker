package videopoker.evaluators;

import videopoker.game.Card;
import videopoker.game.Hand;

/**
 * 
 * This class is part
 * of a group of classes that implements the interface {@link Evaluator} and 
 * scans the hand looking for a 
 * specific card combination.
 * More specifically, this class searches for a hand with a Four to an Outside Straight, 
 * i.e. a hand with an open ended straight that can be completed at either end, such as
 * the cards 789T.
 * <p> For example: the hand [7H 8D 9H TC KS] is a "Four to an Outside Straight" hand. </p>
 */
public class ToOStraight4Evaluator extends HandEvaluator implements Evaluator{

	public boolean[] whereCards(Hand hand){
		
		Card[] hand_o  = hand.orderByValue();
		
		Card[] test1 = {hand_o[0], hand_o[1], hand_o[2], hand_o[3]};
		Card[] test2 = {hand_o[1], hand_o[2], hand_o[3], hand_o[4]};
		
		boolean test1_ss = inOrder(test1) && !hasAce(test1);
		boolean test2_ss = inOrder(test2) && !hasAce(test2);
		
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
			
			if (inOrder(test4) && !hasAce(test4)){
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
	
}
