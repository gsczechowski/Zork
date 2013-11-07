import java.util.ArrayList;



public class Attack {
	
	private ArrayList<TriggerCondition> conditions = new ArrayList<TriggerCondition>();
	private ArrayList<String> prints = new ArrayList<String>();
	private ArrayList<String> actions = new ArrayList<String>();
	
	
	public void attack(String attack_msg){
		Debug.println("Entered attack.attack()");
		if(conditions ==null){
			Debug.println("Conditions is null");
		}
		Debug.println("Number of conditions = " + conditions.size());
		if(conditions.size() != 0){
			Debug.println("Size != 0");
			boolean check = true;
			for(TriggerCondition TC : conditions){
				check = check && TC.checkConditionTrue();
			}
			if(check){
				System.out.println(attack_msg);
				for(String print : prints){
					System.out.println(print);
				}
				for(String action : actions){
					Game.handleCommand(action);
				}
			} else {
				Game.Error();
			}
		} else {
			System.out.println(attack_msg);
			Debug.println("Size = 0");
			for(String print : prints){
				System.out.println(print);
			}
			for(String action : actions){
				Game.handleCommand(action);
			}
		}
	}
	
	public void addPrint(String print){
		prints.add(print);
	}
	
	public void addAction(String action){
		actions.add(action);
	}
	
	public void setCondition(TriggerCondition TC){
		conditions.add(TC);
	}
	

}
