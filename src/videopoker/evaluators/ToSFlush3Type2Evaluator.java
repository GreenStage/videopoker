package videopoker.evaluators;

import videopoker.game.Card;
import videopoker.game.Hand;
import videopoker.game.Card.Suit;
import videopoker.game.Card.Value;

public class ToSFlush3Type2Evaluator extends ToSFlush3Evaluator implements Evaluator{
	
	public boolean[] whereCards(Hand hand){
		
		boolean[] s3flush = super.whereCards(hand);
		
		if (s3flush.length == 0)
			return new boolean[0];
		
		Card[] test = new Card[3];
		
		for (int i=0, j=0; i < s3flush.length; i++ ){
			if (s3flush[i] == true){
				test[j] = hand.getCard(i); 
				j++;
			}
		}
		
		int numGaps = this.numGaps(test);
		int numHighCards = this.numHighCards(test);
		
		if (this.hasAce(test) && (this.hasThree(test) || this.hasFour(test)))
			return s3flush;
		
		if (numGaps == 1)
			return s3flush;
		
		if (numGaps == 2 && numHighCards == 1)
			return s3flush;
		
		if (this.hasSameSuit(test) && this.hasTwo(test) && this.hasThree(test) && this.hasFour(test))
			return s3flush;
		
		return new boolean[0];
		
	}
	
	
	public static void main(String[] args){
		
		Card c1 = new Card(Value.TEN, Suit.HEARTS);
		Card c2 = new Card(Value.JACK, Suit.HEARTS);
		Card c3 = new Card(Value.KING, Suit.HEARTS);
		Card c4 = new Card(Value.QUEEN, Suit.SPADES);
		Card c5 = new Card(Value.NINE, Suit.SPADES);

		Hand hand = new Hand(c1,c2,c3,c4,c5);
		ToSFlush3Type2Evaluator eval = new ToSFlush3Type2Evaluator();
		boolean[] keep = eval.whereCards(hand);
	
		for (int i = 0; i < keep.length; i++){
			System.out.println(keep[i]);
		}
	
	}


}
