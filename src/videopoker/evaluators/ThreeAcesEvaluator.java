package videopoker.evaluators;

import videopoker.game.Card;
import videopoker.game.Hand;
import videopoker.game.Card.Suit;
import videopoker.game.Card.Value;

public class ThreeAcesEvaluator extends ThreeOfAKindEvaluator implements Evaluator {
	
	@Override
	public boolean[] whereCards(Hand hand){
		
		boolean[] three = super.whereCards(hand);
		
		if (three.length == 0)
			return new boolean[0];
		
		Card[] test = new Card[3];
		
		for (int i=0, j=0; i < three.length; i++ ){
			if (three[i] == true){
				test[j] = hand.getCard(i); 
				j++;
			}
		}
		
		for (int i=0; i < test.length; i++){
			if (test[i].getValue() != 'A')
				return new boolean[0];
		}
		
		return three;
		
	}
	
	public static void main(String[] args){
		
		Card c1 = new Card(Value.TEN, Suit.HEARTS);
		Card c2 = new Card(Value.TEN, Suit.DIAMONDS);
		Card c3 = new Card(Value.TEN, Suit.CLOVERS);
		Card c4 = new Card(Value.ACE, Suit.SPADES);
		Card c5 = new Card(Value.FIVE, Suit.HEARTS);

		Hand hand = new Hand(c1,c2,c3,c4,c5);
		ThreeAcesEvaluator eval = new ThreeAcesEvaluator();
		boolean[] keep = eval.whereCards(hand);
		
		for (int i = 0; i < keep.length; i++){
			System.out.println(keep[i]);
		}
		
	}
	

}
