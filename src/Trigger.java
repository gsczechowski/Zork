import java.util.ArrayList;

public class Trigger {

	public boolean permanent = false; //!type
	public boolean used = false;
	private String command;
	private ArrayList<TriggerCondition> conditions = new ArrayList<TriggerCondition>();
	private ArrayList<String> actions = new ArrayList<String>();
	private ArrayList<String> prints = new ArrayList<String>();
	
	
	public boolean checkConditionsTrue(){
		boolean check = true;
		for(TriggerCondition condition : conditions){
			check = check && condition.checkConditionTrue();
		}
		if(check == true){
			Debug.println("All conditions met!");
		}
		return check;
	}
	
	
	public boolean commandMatch(String cmd){
		Debug.println("Checking command match: " + cmd + "-" + command);
		if(command != null && command.equals(cmd)){
			Debug.println("Command matched\n");
			return true;
		}
		return false;
	}
	
	public boolean hasCommand(){
		Debug.print("Checking if trigger has command: ");
		if(command != null){
			Debug.println("yes.");
			return true;
		}
		Debug.println("no.");
		return false;
	}
	
	
	public void execute(){
		Debug.println("EXECUTING TRIGGER COMMANDS");
		Game.disableTrig();
		for(String print : prints){
			System.out.println(print);
		}
		for(String action : actions){
			Game.handleCommand(action);
		}
		Game.enableTrig();
		Debug.println("DONE EXECUTING TRIGGER COMMANDS");
		
	}
	
	public String getCommand(){
		return command;
	}
	
	
	public void addAction(String action){
		actions.add(action);
	}
	
	
	public void addPrint(String print){
		prints.add(print);
	}
	
	public void addCondition(TriggerCondition TC){
		conditions.add(TC);
	}
	
	public void setCommand(String command){
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
