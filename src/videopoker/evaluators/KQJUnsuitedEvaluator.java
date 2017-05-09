package videopoker.evaluators;

import videopoker.game.Card;
import videopoker.game.Hand;
import videopoker.game.Card.Suit;
import videopoker.game.Card.Value;

/**
 * 
 * This class evaluates if the hand contains a Jack, Queen and King of any suit.
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
	
	public static void main(String[] args){
		
		Card c1 = new Card(Value.KING, Suit.HEARTS);
		Card c2 = new Card(Value.ACE, Suit.DIAMONDS);
		Card c3 = new Card(Value.ACE, Suit.CLOVERS);
		Card c4 = new Card(Value.QUEEN, Suit.SPADES);
		Card c5 = new Card(Value.JACK, Suit.HEARTS);

		Hand hand = new Hand(c1,c2,c3,c4,c5);
		KQJUnsuitedEvaluator eval = new KQJUnsuitedEvaluator();
		boolean[] keep = eval.whereCards(hand);
		
		for (int i = 0; i < keep.length; i++){
			System.out.println(keep[i]);
		}
		
	}
}
