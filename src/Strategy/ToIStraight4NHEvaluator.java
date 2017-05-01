package Strategy;

import videopoker.Card;
import videopoker.Hand;
import videopoker.Card.Suit;
import videopoker.Card.Value;

public class ToIStraight4NHEvaluator extends ToIStraight4Evaluator implements Evaluator {
	
public boolean[] whereCards(Hand hand){
		
		boolean[] s4flush = super.whereCards(hand);
		
		if (s4flush.length == 0)
			return new boolean[0];
		
		Card[] test = new Card[4];
		
		for (int i=0, j=0; i < s4flush.length; i++ ){
			if (s4flush[i] == true){
				test[j] = hand.getCard(i); 
				j++;
			}
		}
		
		int numHighCards = this.numHighCards(test);
		
		if (this.hasAce(test) && (this.hasThree(test) || this.hasFour(test)))
			numHighCards--;
		
		
		if (numHighCards == 0)
			return s4flush;
		
		return new boolean[0];
		
	}

	public static void main(String[] args){
	
		Card c1 = new Card(Value.TEN, Suit.HEARTS);
		Card c2 = new Card(Value.JACK, Suit.SPADES);
		Card c3 = new Card(Value.QUEEN, Suit.CLOVERS);
		Card c4 = new Card(Value.ACE, Suit.DIAMONDS);
		Card c5 = new Card(Value.ACE, Suit.SPADES);
	
		Hand hand = new Hand(c1,c2,c3,c4,c5);
		ToIStraight4NHEvaluator eval = new ToIStraight4NHEvaluator();
		boolean[] keep = eval.whereCards(hand);
		
		for (int i = 0; i < keep.length; i++){
			System.out.println(keep[i]);
	}
	
}


}