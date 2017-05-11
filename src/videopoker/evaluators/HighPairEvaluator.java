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
 * More specifically, this class searches for a hand with a "Jack or Better (High Pair)",
 *  i.e. a hand in which
 * there is a pair of Jacks, Queens, Kings or Aces. 
 * <p> For example: the hand [3H 2S 6D KC KS] is a "Jacks or better (High pair)" hand. </p>
 *
 * The hand name is : <b>JACKS OR BETTER</b> (case sensitive)
 */
public class HighPairEvaluator extends HandEvaluator implements MainHandEvaluator{
	/**
	 * The name of the hand evaluated by the class.
	 * The name of the hand is JACKS OR BETTER.
	 */
	public final static String handPower = "JACKS OR BETTER";

	
	@Override
	public boolean hasHandPower(Hand hand) {
		
		Card[] hand_o = hand.orderByValue();
		int[] aux;
		int size;
		aux = equality(hand_o);
		
		if(aux[0] == 0){
			return false;
		} else if(aux[2] != 0){
			size = 1;
		} else{
			size = 0;
		}

		if(size == 0){
			if(hand_o[aux[1]].getValueWeight() < 11){
				return false;
			}
		}else{
			if(hand_o[aux[1]].getValueWeight() < 11 && hand_o[aux[3]].getValueWeight() < 11){
				return false;
			}
		}
		
		return true;
	}
	
	@Override
	public boolean[] whereCards(Hand hand){
		Card[] hand_o = hand.orderByValue();
		int[] aux;
		int size;
		int idx = 0;
		
		aux = equality(hand_o);
		
		if(aux[0] == 0){
			return new boolean[0];
		} else if(aux[2] != 0){
			size = 1;
		} else{
			size = 0;
		}

		if(size == 0){
			if(hand_o[aux[1]].getValueWeight() < 11){
				return new boolean[0];
			}
		}else{
			if(hand_o[aux[1]].getValueWeight() < 11 && hand_o[aux[3]].getValueWeight() < 11){
				return new boolean[0];
			}else if(hand_o[aux[1]].getValueWeight() > 10){
				idx = 0;
			}else{
				idx = 2;
			}
		}
		
		
		boolean[] keep = {false, false, false, false, false};
		for(int i = aux[idx + 1]; i < aux[idx + 1] + aux[idx]; i++){
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
		
		Card c1 = new Card(Value.QUEEN, Suit.HEARTS);
		Card c2 = new Card(Value.QUEEN, Suit.SPADES);
		Card c3 = new Card(Value.TWO, Suit.CLOVERS);
		Card c4 = new Card(Value.JACK, Suit.DIAMONDS);
		Card c5 = new Card(Value.JACK, Suit.CLOVERS);
	
		Hand hand = new Hand(c1,c2,c3,c4,c5);
		HighPairEvaluator eval = new HighPairEvaluator();
		boolean[] keep = eval.whereCards(hand);
		
		for (int i = 0; i < keep.length; i++){
			System.out.println(keep[i]);
		}
	}


}