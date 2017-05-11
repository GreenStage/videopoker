package videopoker.evaluators;

import videopoker.game.Card;
import videopoker.game.Hand;
import videopoker.game.Card.Suit;
import videopoker.game.Card.Value;

/**
 /**
 * 
 * This class is part
 * of a group of classes that implements the interface {@link Evaluator} and 
 * scans the hand looking for a 
 * specific card combination.
 * More specifically, this class searches for a hand with a pair of low cards (all cards excluded Jack,
 * , Queen, King, Ace). 
 * <p> For example: the hand [4S 7C 6H 6D KD] is a "Low Pair" hand.
 *
 */
public class LowPairEvaluator extends HandEvaluator implements Evaluator{
	
	@Override
	public boolean[] whereCards(Hand hand){
		Card[] hand_o = hand.orderByValue();
		int[] aux;
		int size;
		int idx = 0;
		
		aux = equality(hand_o);
		
		if(aux[0] == 0){
			return new boolean[0];
		} else if(aux[2] != 0){
			size = 1;
		} else{
			size = 0;
		}

		if(size == 0){
			if(hand_o[aux[1]].getValueWeight() > 10){
				return new boolean[0];
			}
		}else{
			if(hand_o[aux[1]].getValueWeight() > 10 && hand_o[aux[3]].getValueWeight() > 10){
				return new boolean[0];
			}else if(hand_o[aux[1]].getValueWeight() < 11){
				idx = 0;
			}else{
				idx = 2;
			}
		}
		
		
		boolean[] keep = {false, false, false, false, false};
		for(int i = aux[idx + 1]; i < aux[idx + 1] + aux[idx]; i++){
			keep[hand.search(hand_o[i])] = true;
		}

		return keep;
	}
	
public static void main(String[] args){
		
		Card c1 = new Card(Value.TEN, Suit.HEARTS);
		Card c2 = new Card(Value.TEN, Suit.SPADES);
		Card c3 = new Card(Value.TWO, Suit.CLOVERS);
		Card c4 = new Card(Value.KING, Suit.DIAMONDS);
		Card c5 = new Card(Value.KING, Suit.CLOVERS);
	
		Hand hand = new Hand(c1,c2,c3,c4,c5);
		LowPairEvaluator eval = new LowPairEvaluator();
		boolean[] keep = eval.whereCards(hand);
		
		for (int i = 0; i < keep.length; i++){
			System.out.println(keep[i]);
	}
	
}

}
