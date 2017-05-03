package videopoker.strategy;

import videopoker.evaluators.*;


public class TraditionalStrategy extends Strategy{
	
	public TraditionalStrategy(){
		super();
		//Royal Flush
		super.addEvaluator(new RoyalFlushEvaluator());
		
		//Straight Flush
		super.addEvaluator(new StraightFlushEvaluator());
		
		//Four of a kind
		super.addEvaluator(new FourOfAKindEvaluator());
		
		//4 to a Royal Flush
		super.addEvaluator(new ToRFlush4Evaluator());
		
		//Three Aces
		super.addEvaluator(new ThreeAcesEvaluator());
		
		//Straight
		super.addEvaluator(new StraightEvaluator());
		
		//Flush
		super.addEvaluator(new FlushEvaluator());
		
		//Full House
		super.addEvaluator(new FullHouseEvaluator());
		
		//Three of a kind 
		super.addEvaluator(new ThreeOfAKindEvaluator());
		
		//Four to a straight flush
		super.addEvaluator(new ToSFlush4Evaluator());
		
		//Two Pairs
		super.addEvaluator(new TwoPairEvaluator());
		
		//High Pair
		super.addEvaluator(new HighPairEvaluator());
		
		//Four to a flush
		super.addEvaluator(new ToFlush4Evaluator());
		
		//Three to a royal flush
		super.addEvaluator(new ToRFlush3Evaluator());
		
		//Four to an outside straight
		super.addEvaluator(new ToOStraight4Evaluator());
		
		//Low pair
		super.addEvaluator(new LowPairEvaluator());
		
		//AKQJ Unsuited
		super.addEvaluator(new AKQJUnsuitedEvaluator());
		
		//Three to a straight flush (Type 1)
		super.addEvaluator(new ToSFlush3Type1Evaluator());
		
		//Four to an inside straight with 3 high cards
		super.addEvaluator(new ToIStraight4H3Evaluator());
		
		//QJ Suited
		super.addEvaluator(new QJSuitedEvaluator());
		
		//Three to a flush with 2 high cards
		super.addEvaluator(new ToFlush3H2Evaluator());
		
		//Two suited high cards
		super.addEvaluator(new HighSuitedEvaluator());
		
		//Four to an inside straight with 2 High cards
		super.addEvaluator(new ToIStraight4H2Evaluator());
		
		//Three to a straight flush (Type 2)
		super.addEvaluator(new ToSFlush3Type2Evaluator());
		
		//Four to an inside straight with 1 high card
		super.addEvaluator(new ToIStraight4H1Evaluator());
		
		//KQJ Unsuited
		super.addEvaluator(new KQJUnsuitedEvaluator());
		
		//JT Suited
		super.addEvaluator(new JTSuitedEvaluator());
		
		//QJ Unsuited
		super.addEvaluator(new QJUnsuitedEvaluator());
		
		//Three to a flush with 1 high cards
		super.addEvaluator(new ToFlush3H1Evaluator());
		
		//QT Suited
		super.addEvaluator(new QTSuitedEvaluator());
		
		//Three to a straight flush (Type 3)
		super.addEvaluator(new ToSFlush3Type3Evaluator());
		
		//KQ, KJ Unsuited
		super.addEvaluator(new KQOrKJUnsuitedEvaluator());
		
		//Ace
		super.addEvaluator(new AceEvaluator());
		
		//KT Suited
		super.addEvaluator(new KTSuitedEvaluator());
		
		//J, Q, K
		super.addEvaluator(new JQKEvaluator());
		
		//Four to an inside straight with no high cards
		super.addEvaluator(new ToIStraight4NHEvaluator());
		
		//Three to a flush with no high cards
		super.addEvaluator(new ToFlush3NHEvaluator());
	}
}
