package videopoker.evaluators;

import videopoker.game.Card;
import videopoker.game.Hand;

/**
 * 
 * This class evaluates if the hand contains a Queen and a King or a Jack and a King of any suit.
 *  <p> For example: the hand [2S 7C TS QH KD] is a "KQOrKJ Unsuited" hand, with a Queen and a King.
 *  <p> Another example: the hand [7C 8H TS JS KD] is a "KQOrKJ Unsuited" hand, with a Jack and a King.
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
