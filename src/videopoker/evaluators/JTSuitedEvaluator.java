package videopoker.evaluators;

import videopoker.game.Card;
import videopoker.game.Hand;

/**
 * 
 * This class evaluates if the hand contains a Ten and a Jack of the same suit.
 *  <p> For example: the hand [7C TS JS QH KD] is a "JT Suited" hand.
 *
 */
public class JTSuitedEvaluator extends HandEvaluator implements Evaluator {
	
	@Override
	public boolean[] whereCards(Hand hand){
		
		Card[] hand_o = hand.orderByValueSuit();
		int suit = 0;
		int card_val = 10;
		int yes_card_idx = 0;
		int[] yes_card = {0, 0};
		
		for(int i = 0; i < 5; i++){
			suit = hand_o[i].getSuitWeight();
			if(hand_o[i].getValueWeight() == card_val){
				card_val++;
				yes_card[yes_card_idx] = i;
				yes_card_idx++;
			}
			if(card_val == 12){
				break;
			}
			if(i < 4 && hand_o[i+1].getSuitWeight() != suit){
				card_val = 10;
				yes_card[0] = 0;
				yes_card[1] = 0;
				yes_card_idx = 0;
			}
		}
		
		if(card_val != 12){
			return new boolean[0];
		}
		
		boolean[] keep = {false,false,false,false,false};
		keep[hand.search(hand_o[yes_card[0]])] = true;
		keep[hand.search(hand_o[yes_card[1]])] = true;

		return keep;
	}
	
}
