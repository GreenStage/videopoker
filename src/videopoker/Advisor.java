package videopoker;

import java.util.ArrayList;

import Strategy.Evaluator;
import Strategy.Strategy;
import Strategy.TraditionalStrategy;
import videopoker.Card.Suit;
import videopoker.Card.Value;

public class Advisor {
	
	private Strategy s;
	
	public Advisor(Strategy s){
		this.s = s;
	}
	
	public void setStrategy(Strategy s){
		this.s = s;
	}
	
	public boolean[] giveAdvice(Hand hand){
		
		ArrayList<Evaluator> list = s.getStrategy();
		
		for (Evaluator eval : list){
			if (eval == null)
				continue;
			
			boolean[] advice = eval.whereCards(hand);
			if (advice.length > 0)
				return advice;
		}
		
		boolean[] advice = {false, false, false, false, false};
		
		return advice;
		
		
	}
	
	
	public static void main(String[] args){
		
		Card c1 = new Card(Value.NINE, Suit.CLOVERS);
		Card c2 = new Card(Value.TEN, Suit.HEARTS);
		Card c3 = new Card(Value.SIX, Suit.DIAMONDS);
		Card c4 = new Card(Value.SIX, Suit.HEARTS);
		Card c5 = new Card(Value.THREE, Suit.DIAMONDS);

		Hand hand = new Hand(c1,c2,c3,c4,c5);
		Advisor adv = new Advisor(new TraditionalStrategy());
		boolean[] keep = adv.giveAdvice(hand);
		
		for (int i = 0; i < keep.length; i++){
			System.out.println(keep[i]);
		}
		
	}
	
	
	
}
