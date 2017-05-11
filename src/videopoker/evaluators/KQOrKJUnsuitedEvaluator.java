package videopoker.evaluators;

import videopoker.game.Card;
import videopoker.game.Hand;
import videopoker.game.Card.Suit;
import videopoker.game.Card.Value;

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
	
	public static void main(String[] args){
		
		Card c1 = new Card(Value.KING, Suit.HEARTS);
		Card c2 = new Card(Value.ACE, Suit.DIAMONDS);
		Card c3 = new Card(Value.ACE, Suit.CLOVERS);
		Card c4 = new Card(Value.ACE, Suit.SPADES);
		Card c5 = new Card(Value.JACK, Suit.HEARTS);

		Hand hand = new Hand(c1,c2,c3,c4,c5);
		KQOrKJUnsuitedEvaluator eval = new KQOrKJUnsuitedEvaluator();
		boolean[] keep = eval.whereCards(hand);
		
		for (int i = 0; i < keep.length; i++){
			System.out.println(keep[i]);
		}
		
	}
}
