package Strategy;

import videopoker.Card.Suit;

import videopoker.Card.Value;
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
		
		boolean[] keep = {false, false, false, false, false};
		for(int i = aux[idx + 1]; i < aux[idx + 1] + aux[idx]; i++){
			keep[i] = true;
		}

		return keep;
	}
	
public static void main(String[] args){
		
		Card c1 = new Card(Value.SIX, Suit.HEARTS);
		Card c2 = new Card(Value.SIX, Suit.SPADES);
		Card c3 = new Card(Value.KING, Suit.CLOVERS);
		Card c4 = new Card(Value.ACE, Suit.HEARTS);
		Card c5 = new Card(Value.KING, Suit.HEARTS);

		Hand hand = new Hand(c1,c2,c3,c4,c5);
		HighPairEvaluator eval = new HighPairEvaluator();
		boolean[] keep = eval.whereCards(hand);
		
		for (int i = 0; i < keep.length; i++){
			System.out.println(keep[i]);
		}
		
	}

}