package videopoker.evaluators;

import java.util.Arrays;
import java.util.Comparator;

import videopoker.game.Card;
import videopoker.game.Hand;

public abstract class HandEvaluator {
	
	private final static String handPower ="HAND_NONE";
		
	public boolean hasSameSuit(Card[] set){
		int suit = set[0].getSuitWeight();
		
		for (int i=0; i<set.length; i++){
			if (set[i].getSuitWeight() != suit)
				return false;
		}
		
		return true;
	}
	
	public String getHandPower(Hand hand){
		return handPower;
	}
	
	public int numRoyalCards(Card[] set){
		
		int num = 0;
		for (int i=0; i<set.length; i++){
			char cur_val = set[i].getValue();
			if ((cur_val == 'T') || (cur_val == 'J') || (cur_val == 'Q') || (cur_val == 'K') || (cur_val == 'A')){
				num++;
			}
		}
		
		return num;
	}
	
	public int numGaps(Card[] set){		

		if (set.length < 2)
			return 0;
		
		Arrays.sort(set, new Comparator<Card>() {
			
		    public int compare(Card c1, Card c2) {
		        return c1.getValueWeight()-c2.getValueWeight();
		    }
		});
		
		int num_gaps1 = 0;
		int num_gaps2 = 50;
		int cur;
		Card[] set2;
	
		
		if (set[set.length-1].getValue() == 'A'){
			int temp_gaps = 0;
			set2 = new Card[set.length];
			set2[0] = set[set.length-1];
			for (int i = 1; i < set.length; i++){
				set2[i] = set[i-1];
			}
			
			cur = 1;
			int i = 1;
			while (i < set2.length){
				if (set2[i].getValueWeight() != cur+1){
					temp_gaps++;
					cur++;
				}else if (i+1 < set2.length){
					cur = set2[i].getValueWeight();
					i++;
				}else {
					i++;
				}
			}
			
			num_gaps2 = temp_gaps;
		}
		
		cur = set[0].getValueWeight();
		
		int i = 1;
		while (i < set.length){
			if (set[i].getValueWeight() != cur+1){
				num_gaps1++;
				cur++;
			}else if (i+1 < set.length){
				cur = set[i].getValueWeight();
				i++;
			}else {
				i++;
			}
		}
		
		
		return Math.min(num_gaps1, num_gaps2);
	}
	
	public boolean inOrder(Card[] set){
		int nxt_val;
		int cur_val;
		Card[] set2;
		boolean test1 = false;
		boolean test2 = false;
		
		if (set[set.length-1].getValue() == 'A'){
			set2 = new Card[set.length];
			set2[0] = set[set.length-1];
			for (int i = 1; i < set.length; i++){
				set2[i] = set[i-1];
			}
			
			cur_val = 1;
			for (int i=0; i< set2.length - 1; i++){
				nxt_val = set2[i+1].getValueWeight();
				if (nxt_val - cur_val != 1){
					test2 = false;
					break;
				}
				cur_val = nxt_val;
				test2 = true;
			}	
		}
		
		cur_val = set[0].getValueWeight();

		
		for (int i=0; i< set.length - 1; i++){
			nxt_val = set[i+1].getValueWeight();
			if (nxt_val - cur_val != 1){
				test1 = false;
				break;
			}
			cur_val = nxt_val;
			test1 = true;
		}
		
		
		
		return test1 || test2;
	}
	
	public boolean hasAce(Card[] set){
		
		for (int i = 0; i < set.length; i++){
			if (set[i].getValue() == 'A')
				return true;
		}
		
		return false;
	}
	
	public boolean hasTwo(Card[] set){
		
		for (int i = 0; i < set.length; i++){
			if (set[i].getValue() == '2')
				return true;
		}
		
		return false;
	}
	
	public boolean hasThree(Card[] set){
		
		for (int i = 0; i < set.length; i++){
			if (set[i].getValue() == '3')
				return true;
		}
		
		return false;
	}
	
	public boolean hasFour(Card[] set){
		
		for (int i = 0; i < set.length; i++){
			if (set[i].getValue() == '4')
				return true;
		}
		
		return false;
	}
	
	public boolean hasKing(Card[] set){
		
		for (int i = 0; i < set.length; i++){
			if (set[i].getValue() == 'K')
				return true;
		}
		
		return false;
	}
	
	public int[] equality(Card[] set){
		
		int[] eq = new int[4];
		int size_eq = 0;
		
		int cur_val = set[0].getValueWeight();
		int nxt_val;
		int cnt = 1;

		for (int i=0; i < set.length - 1; i++){
			nxt_val = set[i + 1].getValueWeight();
			if(nxt_val == cur_val){
				cnt++;
				if(i == set.length - 2){
					eq[size_eq] = cnt;
					eq[size_eq+1] = i - cnt + 2;
					size_eq += 2;
					cnt = 1;
				}
			}else{
				if(cnt > 1){
					eq[size_eq] = cnt;
					eq[size_eq+1] = i - cnt + 1;
					size_eq += 2;
					cnt = 1;
				}
				cur_val = nxt_val;
			}
		}
		
		return eq;
	}
	
	public int numHighCards(Card[] set){
		
		int num_cards = 0;
		
		for (int i=0; i<set.length; i++){
			if (set[i].isHighCard())
				num_cards++;
		}
		
		return num_cards;
	}
	
	public boolean hasDuplicates(Card[] set){
		
		Arrays.sort(set, new Comparator<Card>() {
			
		    public int compare(Card c1, Card c2) {
		        return c1.getValueWeight()-c2.getValueWeight();
		    }
		});
		
		int cur = set[0].getValueWeight();
		int i = 1;
		
		while (i < set.length){
			if (set[i].getValueWeight() == cur){
				return true;
			}else if (i+1 < set.length){
				cur = set[i].getValueWeight();
				i++;
			}else {
				i++;
			}
		}
		
		return false;
		
	}	
}
