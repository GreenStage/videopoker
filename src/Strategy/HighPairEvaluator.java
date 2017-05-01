package Strategy;

import videopoker.Card;
import videopoker.Hand;


public class HighPairEvaluator extends HandEvaluator implements Evaluator{
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
			if(hand_o[aux[1]].getValueWeight() < 11){
				return new boolean[0];
			}
		}else{
			if(hand_o[aux[1]].getValueWeight() < 11 && hand_o[aux[3]].getValueWeight() < 11){
				return new boolean[0];
			}else if(hand_o[aux[1]].getValueWeight() > 10){
				idx = 0;
			}else{
				idx = 2;
			}
		}
		
		if(aux[idx] != 2){
			return new boolean[0];
		}
		
		boolean[] keep = {false, false, false, false, false};
		for(int i = aux[idx + 1]; i < aux[idx + 1] + aux[idx]; i++){
			keep[i] = true;
		}

		return keep;
	}

}