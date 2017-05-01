package Strategy;

import videopoker.Card;
import videopoker.Hand;
import videopoker.Card.Suit;
import videopoker.Card.Value;

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
	public String getHandPower(){
		return handPower;
	}
	
	
	public static void main(String[] args){
		
		Card c1 = new Card(Value.ACE, Suit.HEARTS);
		Card c2 = new Card(Value.TWO, Suit.HEARTS);
		Card c3 = new Card(Value.THREE, Suit.HEARTS);
		Card c4 = new Card(Value.FOUR, Suit.HEARTS);
		Card c5 = new Card(Value.FIVE, Suit.HEARTS);

		Hand hand = new Hand(c2,c5,c1,c4,c3);
		StraightFlushEvaluator eval = new StraightFlushEvaluator();
		boolean[] keep = eval.whereCards(hand);
		
		for (int i = 0; i < keep.length; i++){
			System.out.println(keep[i]);
		}
		
	}
	
}