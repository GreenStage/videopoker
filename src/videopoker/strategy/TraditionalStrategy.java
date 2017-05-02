package videopoker.strategy;

import videopoker.evaluators.AKQJUnsuitedEvaluator;
import videopoker.evaluators.FlushEvaluator;
import videopoker.evaluators.FourOfAKindEvaluator;
import videopoker.evaluators.FullHouseEvaluator;
import videopoker.evaluators.HighPairEvaluator;
import videopoker.evaluators.JTSuitedEvaluator;
import videopoker.evaluators.KQJUnsuitedEvaluator;
import videopoker.evaluators.KQOrKJUnsuitedEvaluator;
import videopoker.evaluators.KTSuitedEvaluator;
import videopoker.evaluators.LowPairEvaluator;
import videopoker.evaluators.QJSuitedEvaluator;
import videopoker.evaluators.QJUnsuitedEvaluator;
import videopoker.evaluators.QTSuitedEvaluator;
import videopoker.evaluators.RoyalFlushEvaluator;
import videopoker.evaluators.StraightEvaluator;
import videopoker.evaluators.StraightFlushEvaluator;
import videopoker.evaluators.ThreeAcesEvaluator;
import videopoker.evaluators.ThreeOfAKindEvaluator;
import videopoker.evaluators.ToFlush4Evaluator;
import videopoker.evaluators.ToIStraight4H1Evaluator;
import videopoker.evaluators.ToIStraight4H2Evaluator;
import videopoker.evaluators.ToIStraight4H3Evaluator;
import videopoker.evaluators.ToIStraight4NHEvaluator;
import videopoker.evaluators.ToOStraight4Evaluator;
import videopoker.evaluators.ToRFlush3Evaluator;
import videopoker.evaluators.ToRFlush4Evaluator;
import videopoker.evaluators.ToSFlush3Type1Evaluator;
import videopoker.evaluators.ToSFlush3Type2Evaluator;
import videopoker.evaluators.ToSFlush3Type3Evaluator;
import videopoker.evaluators.ToSFlush4Evaluator;
import videopoker.evaluators.TwoPairEvaluator;

public class TraditionalStrategy extends Strategy{
	
	public TraditionalStrategy(){
		super();
		super.addEvaluator(new RoyalFlushEvaluator());
		super.addEvaluator(new StraightFlushEvaluator());
		super.addEvaluator(new FourOfAKindEvaluator());
		super.addEvaluator(new ToRFlush4Evaluator());
		super.addEvaluator(new ThreeAcesEvaluator());
		super.addEvaluator(new StraightEvaluator());
		super.addEvaluator(new FlushEvaluator());
		super.addEvaluator(new FullHouseEvaluator());
		super.addEvaluator(new ThreeOfAKindEvaluator());
		super.addEvaluator(new ToSFlush4Evaluator());
		super.addEvaluator(new TwoPairEvaluator());
		super.addEvaluator(new HighPairEvaluator());
		super.addEvaluator(new ToFlush4Evaluator());
		super.addEvaluator(new ToRFlush3Evaluator());
		super.addEvaluator(new ToOStraight4Evaluator());
		super.addEvaluator(new LowPairEvaluator());
		super.addEvaluator(new AKQJUnsuitedEvaluator());
		super.addEvaluator(new ToSFlush3Type1Evaluator());
		super.addEvaluator(new ToIStraight4H3Evaluator());
		super.addEvaluator(new QJSuitedEvaluator());
		super.addEvaluator(new ToIStraight4H2Evaluator());
		super.addEvaluator(new ToSFlush3Type2Evaluator());
		super.addEvaluator(new ToIStraight4H1Evaluator());
		super.addEvaluator(new KQJUnsuitedEvaluator());
		super.addEvaluator(new JTSuitedEvaluator());
		super.addEvaluator(new QJUnsuitedEvaluator());
		super.addEvaluator(new QTSuitedEvaluator());
		super.addEvaluator(new ToSFlush3Type3Evaluator());
		super.addEvaluator(new KQOrKJUnsuitedEvaluator());
		super.addEvaluator(new KTSuitedEvaluator());
		super.addEvaluator(new ToIStraight4NHEvaluator());
	}
}
