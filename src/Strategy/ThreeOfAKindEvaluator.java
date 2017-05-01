package Strategy;

import videopoker.Card;
import videopoker.Hand;
import videopoker.Card.Suit;
import videopoker.Card.Value;

public class ThreeOfAKindEvaluator extends HandEvaluator implements Evaluator{
	public boolean[] whereCards(Hand hand){
		Card[] hand_o = hand.orderByValue();
		int[] aux;
		int idx;
		
		aux = equality(hand_o);
		
		if(!(aux[0] >= 3 || aux[2] >= 3)){
			return new boolean[0];
		}
		if(aux[0] >= 3){
			idx = 0;
		}else{
			idx = 2;
		}
		
		if(hand_o[aux[idx]].getValue() == 'A'){
			return new boolean[0];
		}
		
		boolean[] keep = {false, false, false, false, false};
		for(int i = aux[idx+1]; i < aux[idx+1] + 3; i++){
			keep[hand.search(hand_o[i])] = true;
		}

		return keep;
	}
	
	public static void main(String[] args){
		
		Card c1 = new Card(Value.TEN, Suit.HEARTS);
		Card c2 = new Card(Value.SIX, Suit.DIAMONDS);
		Card c3 = new Card(Value.SIX, Suit.CLOVERS);
		Card c4 = new Card(Value.SIX, Suit.SPADES);
		Card c5 = new Card(Value.FIVE, Suit.HEARTS);

		Hand hand = new Hand(c1,c2,c3,c4,c5);
		ThreeOfAKindEvaluator eval = new ThreeOfAKindEvaluator();
		boolean[] keep = eval.whereCards(hand);
		
		for (int i = 0; i < keep.length; i++){
			System.out.println(keep[i]);
		}
		
	}
	
	
}
