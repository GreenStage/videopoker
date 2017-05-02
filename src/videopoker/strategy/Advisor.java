package videopoker.strategy;

import java.util.ArrayList;

import videopoker.evaluators.Evaluator;
import videopoker.game.Card;
import videopoker.game.Hand;
import videopoker.game.Card.Suit;
import videopoker.game.Card.Value;

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
		
		Card c1 = new Card(Value.QUEEN, Suit.SPADES);
		Card c2 = new Card(Value.JACK, Suit.DIAMONDS);
		Card c3 = new Card(Value.FIVE, Suit.HEARTS);
		Card c4 = new Card(Value.SIX, Suit.HEARTS);
		Card c5 = new Card(Value.NINE, Suit.HEARTS);

		Hand hand = new Hand(c1,c2,c3,c4,c5);
		Advisor adv = new Advisor(new TraditionalStrategy());
		boolean[] keep = adv.giveAdvice(hand);
		
		for (int i = 0; i < keep.length; i++){
			System.out.println(keep[i]);
		}
		
	}
	
	
	
}
