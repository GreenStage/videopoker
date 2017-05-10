package videopoker.evaluators;

import videopoker.game.Card;
import videopoker.game.Hand;
import videopoker.game.Card.Suit;
import videopoker.game.Card.Value;

/**
 * 
 * This class evaluates if the hand only needs 2 cards for Straight Flush, of type 1.
 *
 */
public class ToSFlush3Type1Evaluator extends ToSFlush3Evaluator implements Evaluator{
	
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
			return new boolean[0];
		
		if (this.hasSameSuit(test) && (this.hasTwo(test) && this.hasThree(test) && this.hasFour(test)))
			return new boolean[0];
		
		if (numHighCards != 0 && numHighCards >= numGaps)
			return s3flush;
		
		return new boolean[0];
		
	}
	
	
	public static void main(String[] args){
		
		Card c1 = new Card(Value.TWO, Suit.HEARTS);
		Card c2 = new Card(Value.THREE, Suit.HEARTS);
		Card c3 = new Card(Value.ACE, Suit.SPADES);
		Card c4 = new Card(Value.QUEEN, Suit.SPADES);
		Card c5 = new Card(Value.KING, Suit.SPADES);

		Hand hand = new Hand(c1,c2,c3,c4,c5);
		ToSFlush3Type1Evaluator eval = new ToSFlush3Type1Evaluator();
		
		boolean[] keep = eval.whereCards(hand);
	
		for (int i = 0; i < keep.length; i++){
			System.out.println(keep[i]);
		}
	
	}

}
