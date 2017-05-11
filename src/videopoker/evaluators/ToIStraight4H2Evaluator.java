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
 * More specifically, this class searches for a hand with a Four to an Inside Straight with two high
 * cards, 
 * i.e. a hand with a straight with a missing inside card and two high cards.
 * <p> For example: the hand [9H TD JH KC 2S] is a "Four to an Inside Straight with two high cards" hand. </p>
 */
public class ToIStraight4H2Evaluator extends ToIStraight4Evaluator implements Evaluator {
	
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
		
		
		if (numHighCards == 2)
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
		ToIStraight4H2Evaluator eval = new ToIStraight4H2Evaluator();
		boolean[] keep = eval.whereCards(hand);
		
		for (int i = 0; i < keep.length; i++){
			System.out.println(keep[i]);
	}
	
}


}
