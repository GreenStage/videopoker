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
 * More specifically, this class searches for a hand with a Three of A Kind, i.e. a hand in 
 * which there are three cards of any suit with the same value. 
 * <p> For example: the hand [2S 2D 2H 7H KH] is an "Three of a kind" hand. </p>
 * 
 * The hand name is : <b>THREE OF A KIND</b> (case sensitive)
 *
 */
public class ThreeOfAKindEvaluator extends HandEvaluator implements MainHandEvaluator{
	
	/**
	 * The name of the hand evaluated by the class.
	 * The name of the hand is THREE OF A KIND.
	 */
	public static final String handPower = "THREE OF A KIND";
	
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
		
		Card c1 = new Card(Value.ACE, Suit.HEARTS);
		Card c2 = new Card(Value.ACE, Suit.SPADES);
		Card c3 = new Card(Value.ACE, Suit.DIAMONDS);
		Card c4 = new Card(Value.TEN, Suit.CLOVERS);
		Card c5 = new Card(Value.TEN, Suit.HEARTS);

		Hand hand = new Hand(c1,c2,c3,c4,c5);
		ThreeOfAKindEvaluator eval = new ThreeOfAKindEvaluator();
		boolean[] keep = eval.whereCards(hand);
		
		for (int i = 0; i < keep.length; i++){
			System.out.println(keep[i]);
		}
		
	}


	
	
}
