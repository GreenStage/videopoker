package videopoker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Strategy.FlushEvaluator;
import Strategy.FourAcesEvaluator;
import Strategy.FourFiveKingEvaluator;
import Strategy.FourTwoFourEvaluator;
import Strategy.FullHouseEvaluator;
import Strategy.HandEvaluator;
import Strategy.HighPairEvaluator;
import Strategy.RoyalFlushEvaluator;
import Strategy.StraightEvaluator;
import Strategy.StraightFlushEvaluator;
import Strategy.ThreeOfAKindEvaluator;
import Strategy.TwoPairEvaluator;
import Strategy.HandEvaluator;


public class WinningPrizes {
	private Map<String,int[]>  winnings = new HashMap<String,int[]>();

	private List<HandEvaluator> evaluators= new ArrayList<HandEvaluator>();
	
	public WinningPrizes(){
		this.winnings.put("ROYAL FLUSH",new int[] {250,500,750,1000,4000});
		evaluators.add(new RoyalFlushEvaluator() );
		
		this.winnings.put("FOUR ACES",new int[] {160,320,480,640,800});
		evaluators.add(new FourAcesEvaluator() );
		
		this.winnings.put("FOUR 2-4",new int[] {80,160,240,320,400});
		evaluators.add(new FourTwoFourEvaluator() );
		
		this.winnings.put("FOUR 5-K",new int[] {50,100,150,200,250});
		evaluators.add(new FourFiveKingEvaluator() );
		
		this.winnings.put("STRAIGHT FLUSH",new int[] {50,100,150,200,250});
		evaluators.add(new StraightFlushEvaluator() );
		
		this.winnings.put("FULL HOUSE",new int[] {10,20,30,40,50});
		evaluators.add(new FullHouseEvaluator() );
		
		this.winnings.put("FLUSH",new int[] {7,14,21,28,35});
		evaluators.add(new FlushEvaluator() );
		
		this.winnings.put("STRAIGHT",new int[] {5,10,15,20,25});
		evaluators.add(new StraightEvaluator() );
		
		this.winnings.put("THREE OF A KIND",new int[] {3,6,9,12,15});
		evaluators.add(new ThreeOfAKindEvaluator() );
		
		this.winnings.put("TWO PAIR",new int[] {1,2,3,4,5});
		evaluators.add(new TwoPairEvaluator() );
		
		this.winnings.put("JACKS +",new int[] {1,2,3,4,5});
		evaluators.add(new HighPairEvaluator() );	
	}
	
	public int getPrize(String handPower, int bet){
		if(bet <= 6 && this.winnings.containsKey(handPower)){
			return this.winnings.get(handPower)[bet - 1];
		}
		else return 0;
	}
	
	public String getHandPower(Hand hand){
		for(HandEvaluator h : evaluators){
			if(!h.getHandPower(hand).equals("HAND_NONE")){
				return h.getHandPower(hand);
			}
		}
		return "NONE";
	}
}
