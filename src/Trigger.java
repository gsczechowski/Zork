import java.util.ArrayList;

public class Trigger {

	private boolean permanent; //!type
	private boolean used = false;
	private String command;
	private ArrayList<TriggerCondition> conditions = new ArrayList<TriggerCondition>();
	private ArrayList<String> actions = new ArrayList<String>();
	private ArrayList<String> prints = new ArrayList<String>();
	
	
	public void addAction(String action){
		actions.add(action);
	}
	
	
	public void addPrint(String print){
		prints.add(print);
	}
	
	public void addCondition(TriggerCondition TC){
		conditions.add(TC);
	}
	
	public void setCommand(String commnad){
		this.command = command;
	}
	
	public void setPermanent(boolean permanent){
		this.permanent = permanent;
	}
	
	public void setUsed(boolean used){
		this.used = used;
	}
	
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
