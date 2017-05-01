package Strategy;

import videopoker.Card;
import videopoker.Hand;
import videopoker.Card.Suit;
import videopoker.Card.Value;

public class TwoPairEvaluator extends HandEvaluator implements MainHandEvaluator{
	
	private final static String handPower = "TWO PAIR";
	
	@Override
	public boolean hasHandPower(Hand hand) {
		
		Card[] hand_o = hand.orderByValue();
		int[] aux;
		
		aux = equality(hand_o);
		
		if(!(aux[0] == 2 && aux[2] == 2)){
			return false;
		}
		
		return true;
	}
	
	@Override
	public boolean[] whereCards(Hand hand){
		
		Card[] hand_o = hand.orderByValue();
		int[] aux;
		
		aux = equality(hand_o);
		
		if(!(aux[0] == 2 && aux[2] == 2)){
			return new boolean[0];
		}
		
		boolean[] keep = {false, false, false, false, false};
		
		for(int i = aux[1]; i < aux[1] + aux[0]; i++){
			keep[hand.search(hand_o[i])] = true;
		}
		for(int i = aux[3]; i < aux[3] + aux[2]; i++){
			keep[hand.search(hand_o[i])] = true;
		}

		return keep;
	}
	
	@Override
	public String getHandPower(){
		return handPower;
	}
	
	public static void main(String[] args){
		
		Card c1 = new Card(Value.TEN, Suit.HEARTS);
		Card c2 = new Card(Value.TEN, Suit.SPADES);
		Card c3 = new Card(Value.TWO, Suit.CLOVERS);
		Card c4 = new Card(Value.JACK, Suit.DIAMONDS);
		Card c5 = new Card(Value.JACK, Suit.CLOVERS);
	
		Hand hand = new Hand(c1,c2,c3,c4,c5);
		TwoPairEvaluator eval = new TwoPairEvaluator();
		boolean[] keep = eval.whereCards(hand);
		
		for (int i = 0; i < keep.length; i++){
			System.out.println(keep[i]);
	}
	
}



}
