package Strategy;

public class TraditionalStrategy extends Strategy{
	
	public TraditionalStrategy(){
		super();
		super.addEvaluator(new RoyalFlushEvaluator());
		super.addEvaluator(new StraightFlushEvaluator());
		super.addEvaluator(new FourOfAKindEvaluator());
		super.addEvaluator(new ToRFlush4Evaluator());
		super.addEvaluator(new ToSFlush4Evaluator());
		super.addEvaluator(new ToFlush4Evaluator());
		super.addEvaluator(new ToRFlush3Evaluator());
		super.addEvaluator(new ToOStraight4Evaluator());
		super.addEvaluator(new ToSFlush3Type1Evaluator());
		super.addEvaluator(new ToSFlush3Type2Evaluator());
		super.addEvaluator(new ToSFlush3Type3Evaluator());
	}
}
