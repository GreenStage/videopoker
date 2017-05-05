package videopoker.game;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import videopoker.evaluators.FlushEvaluator;
import videopoker.evaluators.FourAcesEvaluator;
import videopoker.evaluators.FourFiveKingEvaluator;
import videopoker.evaluators.FourTwoFourEvaluator;
import videopoker.evaluators.FullHouseEvaluator;
import videopoker.evaluators.HandEvaluator;
import videopoker.evaluators.HighPairEvaluator;
import videopoker.evaluators.RoyalFlushEvaluator;
import videopoker.evaluators.StraightEvaluator;
import videopoker.evaluators.StraightFlushEvaluator;
import videopoker.evaluators.ThreeOfAKindEvaluator;
import videopoker.evaluators.TwoPairEvaluator;
import videopoker.utilities.PowerHashMap;



public class WinningPrizes {
	public static String HAND_NONE ="Other";
	private PowerHashMap<String,int[]>  winnings = new PowerHashMap<String,int[]>();

	private List<HandEvaluator> evaluators= new ArrayList<HandEvaluator>();
	
	public WinningPrizes(){
		this.winnings.put(RoyalFlushEvaluator.handPower,new int[] {250,500,750,1000,4000});
		evaluators.add( new RoyalFlushEvaluator() );
		
		this.winnings.put(FourAcesEvaluator.handPower,new int[] {160,320,480,640,800});
		evaluators.add( new FourAcesEvaluator() );
		
		this.winnings.put(FourTwoFourEvaluator.handPower,new int[] {80,160,240,320,400});
		evaluators.add( new FourTwoFourEvaluator() );
		
		this.winnings.put(FourFiveKingEvaluator.handPower,new int[] {50,100,150,200,250});
		evaluators.add( new FourFiveKingEvaluator() );
		
		this.winnings.put(StraightFlushEvaluator.handPower,new int[] {50,100,150,200,250});
		evaluators.add( new StraightFlushEvaluator() );
		
		this.winnings.put(FullHouseEvaluator.handPower,new int[] {10,20,30,40,50});
		evaluators.add( new FullHouseEvaluator() );
		
		this.winnings.put(FlushEvaluator.handPower,new int[] {7,14,21,28,35});
		evaluators.add( new FlushEvaluator() );
		
		this.winnings.put(StraightEvaluator.handPower,new int[] {5,10,15,20,25});
		evaluators.add( new StraightEvaluator() );
		
		this.winnings.put(ThreeOfAKindEvaluator.handPower,new int[] {3,6,9,12,15});
		evaluators.add( new ThreeOfAKindEvaluator() );
		
		this.winnings.put(TwoPairEvaluator.handPower,new int[] {1,2,3,4,5});
		evaluators.add( new TwoPairEvaluator() );
		
		this.winnings.put(HighPairEvaluator.handPower,new int[] {1,2,3,4,5});
		evaluators.add( new HighPairEvaluator() );	
	}
	
	public List<String> getWinningHands(){
		return winnings.getOrderedKeys(new Comparator<String>(){
			@Override
			public int compare(String o1, String o2) {
				return  winnings.get(o2)[0] - winnings.get(o1)[0];
			}
		});
	}
	
	public int getPrize(String handPower, int bet){
		if(bet <= 6 && this.winnings.containsKey(handPower)){
			return this.winnings.get(handPower)[bet - 1];
		}
		else return 0;
	}
	
	public int[] getPrizeArray(String handPower){
		if(winnings.containsKey(handPower))
			return winnings.get(handPower);
		else return new int[0];
	}

	public String getHandPower(Hand hand){
		for(HandEvaluator h : evaluators){
			if(!h.getHandPower(hand).equals(HAND_NONE)){
				return h.getHandPower(hand);
			}
		}
		return "NONE";
	}
}
