package videopoker.evaluators;

import videopoker.game.Card;
import videopoker.game.Hand;
import videopoker.game.Card.Suit;
import videopoker.game.Card.Value;

/**
 * 
 * This class evaluates if the hand contains a Full House, that means having 3 cards of a kind and 2 of another kind.
 *  <p> For example: the hand [JS JC JH AH AD] is a "Full House" hand.
 *
 */
public class FullHouseEvaluator extends HandEvaluator implements MainHandEvaluator {
	
	public static final String handPower = "FULL HOUSE";
	
	@Override
	public boolean hasHandPower(Hand hand) {
		
		Card[] hand_o  = hand.orderByValue();
		int[] aux;
		
		aux = equality(hand_o);
		
		if(!((aux[0] == 3 && aux[2] == 2)||(aux[0] == 2 && aux[2] == 3))){
			return false;
		}
		
		return true;
	}
	
	@Override
	public boolean[] whereCards(Hand hand){
		
		if (!(hasHandPower(hand)))
			return new boolean[0];
		
		boolean[] keep = {true, true, true, true, true};

		return keep;
	}
	
	@Override
	public String getHandPower(Hand hand){
		if (this.hasHandPower(hand))
			return handPower;
		
		return super.getHandPower(hand);
	}
	
	public static void main(String[] args){
		
		Card c1 = new Card(Value.SIX, Suit.HEARTS);
		Card c2 = new Card(Value.SIX, Suit.DIAMONDS);
		Card c3 = new Card(Value.SIX, Suit.CLOVERS);
		Card c4 = new Card(Value.TEN, Suit.SPADES);
		Card c5 = new Card(Value.TEN, Suit.CLOVERS);

		Hand hand = new Hand(c1,c2,c3,c4,c5);
		FullHouseEvaluator eval = new FullHouseEvaluator();
		boolean[] keep = eval.whereCards(hand);
		
		for (int i = 0; i < keep.length; i++){
			System.out.println(keep[i]);
		}
		
	}


	
}
