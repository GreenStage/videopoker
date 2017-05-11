package videopoker.evaluators;

import videopoker.game.Card;
import videopoker.game.Hand;
import videopoker.game.Card.Suit;
import videopoker.game.Card.Value;

/**
 * 
 * This class is part
 * of a group of classes that implements the interface {@link Evaluator} and 
 * scans the hand looking for a 
 * specific card combination.
 * More specifically, this class searches for a hand with a suited pair of high cards (Jack, Queen,
 * King, Ace). 
 * <p> For example: the hand [JS QC 4H 2H 7D] is an high suited hand. </p>
 *
 */
public class HighSuitedEvaluator extends HandEvaluator implements Evaluator {

	@Override
	public boolean[] whereCards(Hand hand) {

		Card[] hand_o = hand.orderByValueSuit();
		
		int numHighCards = numHighCards(hand_o);
		
		if (numHighCards < 2)
			return new boolean[0];
		
		Card[] test = new Card[numHighCards];
		
		int k = 0;
		for (int i = 0; i < hand_o.length; i++){
			char curValue = hand_o[i].getValue();
			if (curValue == 'K' || curValue == 'A' || curValue == 'Q' || curValue == 'J' ){
				test[k] = hand_o[i];
				k++;
			}
		}
		
		for (int i=0; i < test.length-1; i++){
			if (test[i].getSuit() == test[i+1].getSuit()){
				boolean[] keep = {false, false, false, false, false};
				keep[hand.search(test[i])] = true;
				keep[hand.search(test[i+1])] = true;
				return keep;
			}
		}
		
		
		return new boolean[0];
	}
	
	public static void main(String[] args){
		
		Card c1 = new Card(Value.JACK, Suit.SPADES);
		Card c2 = new Card(Value.QUEEN, Suit.SPADES);
		Card c3 = new Card(Value.FIVE, Suit.CLOVERS);
		Card c4 = new Card(Value.KING, Suit.SPADES);
		Card c5 = new Card(Value.FIVE, Suit.HEARTS);

		Hand hand = new Hand(c1,c2,c3,c4,c5);
		HighSuitedEvaluator eval = new HighSuitedEvaluator();
		boolean[] keep = eval.whereCards(hand);
		
		for (int i = 0; i < keep.length; i++){
			System.out.println(keep[i]);
		}
		
	}

}
