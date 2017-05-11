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
 * More specifically, this class searches for a hand with a Four to a Royal Flush, 
 * i.e. a hand in which four royal cards (T, J, Q, K) of the same suit are present. 
 * <p> For example: the hand [TH JH QH KH 2S] is a "Four to a Royal flush" hand. </p>
 */
public class ToRFlush4Evaluator extends HandEvaluator implements Evaluator {
	
	
	public boolean[] whereCards(Hand hand){
		
		Card[] hand_o  = hand.orderByValueSuit();
		
		Card[] test1 = {hand_o[0], hand_o[1], hand_o[2], hand_o[3]};
		Card[] test2 = {hand_o[1], hand_o[2], hand_o[3], hand_o[4]};
		
		boolean test1_ss = hasSameSuit(test1);
		boolean test2_ss = hasSameSuit(test2);
		
		if (!test1_ss && !test2_ss)
			return new boolean[0];
		
		if (test1_ss){
			int numRoyalCards = numRoyalCards(test1);
			if (numRoyalCards == 4){
				boolean[] keep = {false, false, false, false, false};
				keep[hand.search(test1[0])] = true;
				keep[hand.search(test1[1])] = true;
				keep[hand.search(test1[2])] = true;
				keep[hand.search(test1[3])] = true;
				
				return keep;
			}	
		}
		
		int numRoyalCards = numRoyalCards(test2);
		if (numRoyalCards == 4){
			boolean[] keep = {false, false, false, false, false};
			keep[hand.search(test2[0])] = true;
			keep[hand.search(test2[1])] = true;
			keep[hand.search(test2[2])] = true;
			keep[hand.search(test2[3])] = true;
			
			return keep;
		}	
		
		return new boolean[0];	
	}
	
	
	public static void main(String[] args){
		
		Card c1 = new Card(Value.TEN, Suit.HEARTS);
		Card c2 = new Card(Value.JACK, Suit.HEARTS);
		Card c3 = new Card(Value.QUEEN, Suit.HEARTS);
		Card c4 = new Card(Value.ACE, Suit.HEARTS);
		Card c5 = new Card(Value.EIGHT, Suit.HEARTS);

		Hand hand = new Hand(c2,c5,c1,c4,c3);
		ToRFlush4Evaluator eval = new ToRFlush4Evaluator();
		boolean[] keep = eval.whereCards(hand);
		
		for (int i = 0; i < keep.length; i++){
			System.out.println(keep[i]);
		}
		
	}
	
	
	
}
