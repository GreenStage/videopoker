package videopoker.evaluators;

import videopoker.game.Card;
import videopoker.game.Hand;

/**
* This class is part
 * of a group of classes that implements the interface {@link Evaluator} and 
 * scans the hand looking for a 
 * specific card combination.
 * More specifically, this class searches for a hand with a Queen and a Jack with the same suit. 
 *  <p> For example: the hand [4S 7C 6H JD QD] is a "QJ Suited" hand.
 *
 */
public class QJSuitedEvaluator extends HandEvaluator implements Evaluator {
	
	@Override
	public boolean[] whereCards(Hand hand){
		
		Card[] hand_o = hand.orderByValueSuit();
		int suit = 0;
		int card_val = 11;
		int yes_card_idx = 0;
		int[] yes_card = {0, 0};
		
		for(int i = 0; i < 5; i++){
			suit = hand_o[i].getSuitWeight();
			if(hand_o[i].getValueWeight() == card_val){
				card_val++;
				yes_card[yes_card_idx] = i;
				yes_card_idx++;
			}
			if(card_val == 13){
				break;
			}
			if(i < 4 && hand_o[i+1].getSuitWeight() != suit){
				card_val = 11;
				yes_card[0] = 0;
				yes_card[1] = 0;
				yes_card_idx = 0;
			}
		}
		
		if(card_val != 13){
			return new boolean[0];
		}
		
		boolean[] keep = {false,false,false,false,false};
		keep[hand.search(hand_o[yes_card[0]])] = true;
		keep[hand.search(hand_o[yes_card[1]])] = true;

		return keep;
	}

}
