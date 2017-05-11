package videopoker.evaluators;

import videopoker.game.Card;
import videopoker.game.Hand;

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

}
