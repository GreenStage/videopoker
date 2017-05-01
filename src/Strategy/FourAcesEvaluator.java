package Strategy;

import videopoker.Card;
import videopoker.Hand;
import videopoker.Card.Suit;
import videopoker.Card.Value;

public class FourAcesEvaluator extends FourOfAKindEvaluator implements MainHandEvaluator {
	
	private static final String handPower = "FOUR ACES";
	
	@Override
	public boolean hasHandPower(Hand hand) {
		
		boolean[] four = super.whereCards(hand);
		
		if (four.length == 0)
			return false;
		
		Card[] test = new Card[4];
		
		for (int i=0, j=0; i < four.length; i++ ){
			if (four[i] == true){
				test[j] = hand.getCard(i); 
				j++;
			}
		}
		
		for (int i = 0; i < test.length; i++){
			if (test[i].getValue() != 'A')
				return false;
		}
		
		
		return true;
	}
	
	@Override
	public boolean[] whereCards(Hand hand){
		
		if (!(hasHandPower(hand)))
			return new boolean[0];
			
		return super.whereCards(hand);
		
	}
	
	@Override
	public String getHandPower(){
		return handPower;
	}
	
	
	public static void main(String[] args){
		
		Card c1 = new Card(Value.TEN, Suit.HEARTS);
		Card c2 = new Card(Value.ACE, Suit.DIAMONDS);
		Card c3 = new Card(Value.ACE, Suit.CLOVERS);
		Card c4 = new Card(Value.ACE, Suit.SPADES);
		Card c5 = new Card(Value.ACE, Suit.HEARTS);

		Hand hand = new Hand(c1,c2,c3,c4,c5);
		FourAcesEvaluator eval = new FourAcesEvaluator();
		boolean[] keep = eval.whereCards(hand);
		
		for (int i = 0; i < keep.length; i++){
			System.out.println(eval.getHandPower());
		}
		
	}
	
}
