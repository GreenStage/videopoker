package videopoker.evaluators;

import videopoker.game.Card;
import videopoker.game.Hand;

/**
 * This class is part
 * of a group of classes that implements the interface {@link Evaluator} and 
 * scans the hand looking for a 
 * specific card combination.
 * More specifically, this class searches for a hand with a
 * Jack and a King or a King and a Queen with different suits.
 *  <p> For example: the hand [2S 7C QH TS KD] is a "KQOrKJ Unsuited" hand, with a Queen and a King.
 *  <p> Another example: the hand [JS 7C 8H TS KD] is a "KQOrKJ Unsuited" hand, with a Jack and a King.
 *
 */
public class KQOrKJUnsuitedEvaluator  extends HandEvaluator implements Evaluator {
	
	@Override
	public boolean[] whereCards(Hand hand){
		
		boolean testKQ = false;
		boolean testKJ = false;		
		
		Card[] hand_o = hand.orderByValue();
		
		// KQ
		int card_val = 12;
		int yes_card_idx = 0;
		int[] yes_card = {0, 0};
		
		for(int i = 0; i < 5; i++){
			if(hand_o[i].getValueWeight() == card_val){
				card_val++;
				yes_card[yes_card_idx] = i;
				yes_card_idx++;
			}
			if(card_val == 14){
				testKQ = true;
				break;
			}
		}
		
		// KJ
		if(testKQ == false){
			card_val = 11;
			yes_card_idx = 0;
			for(int i = 0; i < 5; i++){
				if(hand_o[i].getValueWeight() == card_val){
					card_val += 2;
					yes_card[yes_card_idx] = i;
					yes_card_idx ++;
				}
				if(card_val == 15){
					testKJ = true;
					break;
				}
			}
		}
		
		if(!(testKQ || testKJ)){
			return new boolean[0];
		}
		
		boolean[] keep = {false,false,false,false,false};
		keep[hand.search(hand_o[yes_card[0]])] = true;
		keep[hand.search(hand_o[yes_card[1]])] = true;

		return keep;
	}

}
