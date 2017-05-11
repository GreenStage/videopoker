package videopoker.evaluators;

import videopoker.game.Card;
import videopoker.game.Hand;
import videopoker.game.Card.Suit;
import videopoker.game.Card.Value;
import videopoker.strategy.Advisor;
import videopoker.strategy.TraditionalStrategy;

/**
 * 
 * This class is part
 * of a group of classes that implements the interface {@link Evaluator} and 
 * scans the hand looking for a 
 * specific card combination.
 * More specifically, this class searches for a hand with Three Aces, i.e. a hand in which three cards
 * are Aces. 
 * <p> For example: the hand [AH AS AD 2C KS] is a "Three Aces" hand. </p>
 */
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
		
		Card c1 = new Card(Value.ACE, Suit.HEARTS);
		Card c2 = new Card(Value.ACE, Suit.HEARTS);
		Card c3 = new Card(Value.ACE, Suit.DIAMONDS);
		Card c4 = new Card(Value.TEN, Suit.CLOVERS);
		Card c5 = new Card(Value.TEN, Suit.HEARTS);

		Hand hand = new Hand(c1,c2,c3,c4,c5);
		Advisor adv = new Advisor(new TraditionalStrategy());
		boolean[] keep = adv.giveAdvice(hand);
		
		for (int i = 0; i < keep.length; i++){
			System.out.println(keep[i]);
		}
		
	}
	

}
