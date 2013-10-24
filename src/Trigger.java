import java.util.ArrayList;

public class Trigger {

	private boolean permanent; //!type
	private ArrayList<TriggerCondition> conditions = new ArrayList<TriggerCondition>();
	private ArrayList<String> actions = new ArrayList<String>();
	private ArrayList<String> prints = new ArrayList<String>();
	
	
	public ArrayList<String> getActions(){
		return actions;
	}
	
	public boolean isPermanent(){
		return permanent;
	}
	
	public ArrayList<String> getPrints(){
		return prints;
	}
}
