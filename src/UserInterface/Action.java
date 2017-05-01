package UserInterface;

import java.util.ArrayList;

public class Action {
	public static final int ACTION_NONE		 = 0x00;
	public static final int ACTION_BET		 = 0x01;
	public static final int ACTION_CREDIT	 = 0x02;
	public static final int ACTION_DEAL		 = 0x04;
	public static final int ACTION_HOLD	 	 = 0x08;
	public static final int ACTION_ADVICE	 = 0x10;
	public static final int ACTION_STATS	 = 0x20;
	
	private int mActionType;
	private ArrayList<Integer> mActionsExtras = new ArrayList<Integer>();
	
	public Action(int type){
		this.mActionType = type;
	}
	
	public void putExtra(int value){
		mActionsExtras.add(value);
	}
	
	public int getType(){
		return this.mActionType;
	}
	public boolean hasExtra(){
		if(mActionsExtras.size() > 0){
			return true;
		}
		return false;
	}
	public int getExtra(){
		if(mActionsExtras.size() == 0){
			return -1;
		}
		else{
			return mActionsExtras.remove(0);
		}
	}
	
}
