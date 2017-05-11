package videopoker.evaluators;

import videopoker.game.Card;
import videopoker.game.Hand;
import videopoker.game.Card.Suit;
import videopoker.game.Card.Value;

/**
 * 
 * This class is part
 * of a group of classes that implements the interface {@link Evaluator} and 
 * scans the hand looking for a 
 * specific card combination.
 * More specifically, this class searches for a hand with a Three to a Straight Flush (Type 3), 
 * i.e. a hand in which there are three cards of the same suit with two gaps and no high cards.
 * <p> For example: the hand [2H 5H 6H 2C KS] is a "Three to a Straight flush (Type 3)" hand. </p>
 */
public class ToSFlush3Type3Evaluator extends ToSFlush3Evaluator implements Evaluator{

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
			numHighCards--;
		
		if (numGaps == 2 && numHighCards == 0)
			return s3flush;
		
		return new boolean[0];
		
	}
	
	
	public static void main(String[] args){
		
		Card c1 = new Card(Value.ACE, Suit.HEARTS);
		Card c2 = new Card(Value.FIVE, Suit.HEARTS);
		Card c3 = new Card(Value.FOUR, Suit.HEARTS);
		Card c4 = new Card(Value.JACK, Suit.SPADES);
		Card c5 = new Card(Value.QUEEN, Suit.SPADES);

		Hand hand = new Hand(c5,c1,c3,c4,c2);
		ToSFlush3Type3Evaluator eval = new ToSFlush3Type3Evaluator();
		boolean[] keep = eval.whereCards(hand);
	
		for (int i = 0; i < keep.length; i++){
			System.out.println(keep[i]);
		}
	
	}

}
