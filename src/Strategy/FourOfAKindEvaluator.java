package Strategy;

import videopoker.Card;
import videopoker.Hand;
import videopoker.Card.Suit;
import videopoker.Card.Value;

public class FourOfAKindEvaluator extends HandEvaluator implements Evaluator {
	
	public boolean[] whereCards(Hand hand){
		
		Card[] hand_o = hand.orderByValue();

		int[] aux = equality(hand_o);
		
		if(aux[0] != 2){
			return new boolean[0];
		}
		
		boolean[] keep = {false, false, false, false, false};
		for(int i = aux[1]; i < aux[1] + aux[0]; i++){
			keep[i] = true;
		}

		return keep;
	}
	
	public static void main(String[] args){
		
		Card c1 = new Card(Value.TEN, Suit.HEARTS);
		Card c2 = new Card(Value.TEN, Suit.DIAMONDS);
		Card c3 = new Card(Value.TEN, Suit.CLOVERS);
		Card c4 = new Card(Value.TEN, Suit.SPADES);
		Card c5 = new Card(Value.SIX, Suit.SPADES);

		Hand hand = new Hand(c2,c5,c1,c4,c3);
		FourOfAKindEvaluator eval = new FourOfAKindEvaluator();
		boolean[] keep = eval.whereCards(hand);
		
		for (int i = 0; i < keep.length; i++){
			System.out.println(keep[i]);
		}
		
	}
}
