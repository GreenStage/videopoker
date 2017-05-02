package videopoker.evaluators;

import videopoker.game.Card;
import videopoker.game.Hand;
import videopoker.game.Card.Suit;
import videopoker.game.Card.Value;

public class ThreeOfAKindEvaluator extends HandEvaluator implements MainHandEvaluator{
	
	private static final String handPower = "THREE OF A KIND";
	
	@Override
	public boolean hasHandPower(Hand hand) {

		Card[] hand_o = hand.orderByValue();
		int[] aux;
		aux = equality(hand_o);
		
		if(!(aux[0] >= 3 || aux[2] >= 3)){
			return false;
		}
		
		return true;
	}
	
	@Override
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
		
		boolean[] keep = {false, false, false, false, false};
		for(int i = aux[idx+1]; i < aux[idx+1] + 3; i++){
			keep[hand.search(hand_o[i])] = true;
		}

		return keep;
	}
	
	@Override
	public String getHandPower(Hand hand){
		if (this.hasHandPower(hand))
			return handPower;
		
		return super.getHandPower(hand);
	}
	
	public static void main(String[] args){
		
		Card c1 = new Card(Value.TEN, Suit.HEARTS);
		Card c2 = new Card(Value.ACE, Suit.DIAMONDS);
		Card c3 = new Card(Value.ACE, Suit.CLOVERS);
		Card c4 = new Card(Value.ACE, Suit.SPADES);
		Card c5 = new Card(Value.FIVE, Suit.HEARTS);

		Hand hand = new Hand(c1,c2,c3,c4,c5);
		ThreeOfAKindEvaluator eval = new ThreeOfAKindEvaluator();
		boolean[] keep = eval.whereCards(hand);
		
		for (int i = 0; i < keep.length; i++){
			System.out.println(keep[i]);
		}
		
	}


	
	
}
