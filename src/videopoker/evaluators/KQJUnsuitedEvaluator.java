package videopoker.evaluators;

import videopoker.game.Card;
import videopoker.game.Hand;

/**
 * This class is part
 * of a group of classes that implements the interface {@link Evaluator} and 
 * scans the hand looking for a 
 * specific card combination.
 * More specifically, this class searches for a hand with a Jack, a Queen and a King of different suits.
 *  <p> For example: the hand [JS 7C QH TS KD] is a "KQJ Unsuited" hand.
 *
 */
public class KQJUnsuitedEvaluator extends HandEvaluator implements Evaluator {

	@Override
	public boolean[] whereCards(Hand hand){
		
		Card[] hand_o = hand.orderByValue();
		int card_val = 11;
		int yes_card_idx = 0;
		int[] yes_card = {0, 0, 0};
		
		for(int i = 0; i < 5; i++){
			if(hand_o[i].getValueWeight() == card_val){
				card_val++;
				yes_card[yes_card_idx] = i;
				yes_card_idx++;
			}
			if(card_val == 14){
				break;
			}
		}
		
		if(card_val != 14){
			return new boolean[0];
		}
		
		boolean[] keep = {false,false,false,false,false};
		keep[hand.search(hand_o[yes_card[0]])] = true;
		keep[hand.search(hand_o[yes_card[1]])] = true;
		keep[hand.search(hand_o[yes_card[2]])] = true;

		return keep;
	}

}
