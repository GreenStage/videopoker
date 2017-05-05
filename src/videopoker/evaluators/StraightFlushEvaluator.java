package videopoker.evaluators;

import videopoker.game.Card;
import videopoker.game.Hand;
import videopoker.game.Card.Suit;
import videopoker.game.Card.Value;

public class StraightFlushEvaluator extends HandEvaluator implements MainHandEvaluator {

	public static final String handPower = "STRAIGHT FLUSH";
	
	@Override
	public boolean hasHandPower(Hand hand){
		Card[] hand_o  = hand.orderByValueSuit();
		
		if(!inOrder(hand_o)){
			return false;
		}
		
		if(!hasSameSuit(hand_o)){
			return false;
		}
		
		return true;
	}
	
	@Override
	public boolean[] whereCards(Hand hand){
				
		if (!(this.hasHandPower(hand)))
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
		
		Card c1 = new Card(Value.NINE, Suit.SPADES);
		Card c2 = new Card(Value.TEN, Suit.SPADES);
		Card c3 = new Card(Value.JACK, Suit.SPADES);
		Card c4 = new Card(Value.QUEEN, Suit.SPADES);
		Card c5 = new Card(Value.KING, Suit.SPADES);

		Hand hand = new Hand(c2,c5,c1,c4,c3);
		StraightFlushEvaluator eval = new StraightFlushEvaluator();
		boolean[] keep = eval.whereCards(hand);
		
		for (int i = 0; i < keep.length; i++){
			System.out.println(keep[i]);
		}
		
	}
	
}