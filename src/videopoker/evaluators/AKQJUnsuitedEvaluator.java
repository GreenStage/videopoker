package videopoker.evaluators;

import videopoker.game.Card;
import videopoker.game.Hand;

/**
 * 
 * This class evaluates if the hand contains the Ace, King, Queen and Jack of any suit.
 *  <p> For example: the hand [7C JS QH KD AH] is an "AKQJ Unsuited" hand.
 *
 */
public class AKQJUnsuitedEvaluator extends HandEvaluator implements Evaluator {

	
	@Override
	public boolean[] whereCards(Hand hand){
		
		Card[] hand_o = hand.orderByValue();
		int card_val = 11;
		int not_card = 1;
		
		for(int i = 0; i < 5; i++){
			if(hand_o[i].getValueWeight() == card_val){
				card_val++;
			}else{
				not_card = i;
			}
		}
		
		if(card_val != 15){
			return new boolean[0];
		}
		
		boolean[] keep = {true, true, true, true, true};
		keep[hand.search(hand_o[not_card])] = false;

		return keep;
	}
}
