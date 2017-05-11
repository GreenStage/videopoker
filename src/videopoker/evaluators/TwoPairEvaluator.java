package videopoker.evaluators;

import videopoker.game.Card;
import videopoker.game.Hand;
import videopoker.game.Card.Suit;
import videopoker.game.Card.Value;

/**
 * 
 * This class is part
 * of a group of classes that implements the interface {@link MainHandEvaluator} and 
 * scans the hand looking for a 
 * specific card combination.
 * More specifically, this class searches for a hand with a Two Pair, i.e. a hand in which
 * two pairs with different values are present.
 * <p> For example: the hand [JS JH QS QH 2S] is a "Two Pair" hand. </p>
 * 
 * The hand name is : <b>TWO PAIR</b> (case sensitive)
 *
 */
public class TwoPairEvaluator extends HandEvaluator implements MainHandEvaluator{
	
	/**
	 * The name of the hand evaluated by the class.
	 * The name of the hand is TWO PAIR.
	 */
	public final static String handPower = "TWO PAIR";
	
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
	public String getHandPower(Hand hand){
		if (this.hasHandPower(hand))
			return handPower;
		
		return super.getHandPower(hand);
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
