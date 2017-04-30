package Strategy;

import java.util.ArrayList;

public class Strategy {
	
	private int size = 10;
	private ArrayList<Evaluator> list;
	
	public Strategy(){
		list = new ArrayList<Evaluator>();
	}
	
	public Strategy(int size){
		list = new ArrayList<Evaluator>(size);
		this.size = size;
	}
	
	public void addEvaluator(Evaluator eval){
		list.add(eval);
	}
	
	//TODO: Think about this method
	public void addEvaluator(Evaluator eval, int pos){
		if (pos > size-1){
			
		}
	}
	
	public ArrayList<Evaluator> getStrategy(){
		return list;
	}
	
}
