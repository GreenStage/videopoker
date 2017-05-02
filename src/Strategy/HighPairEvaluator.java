package Strategy;

import videopoker.Card;
import videopoker.Hand;
import videopoker.Card.Suit;
import videopoker.Card.Value;


public class HighPairEvaluator extends HandEvaluator implements MainHandEvaluator{
	
	private final static String handPower = "JACKS OR BETTER";

	
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