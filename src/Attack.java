import java.util.ArrayList;



public class Attack {
	
	private TriggerCondition condition;
	private ArrayList<String> prints = new ArrayList<String>();
	private ArrayList<String> actions = new ArrayList<String>();
	
	public void addPrint(String print){
		prints.add(print);
	}
	
	public void addAction(String action){
		actions.add(action);
	}
	
	public void setCondition(TriggerCondition TC){
		condition = TC;
	}

}
