import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;


public class TriggerableObject {
	
	
	protected String description, name, status;
	protected Set<Trigger> triggers = new HashSet<Trigger>();
	
	
	public boolean checkCommandTriggers(String command){
		for(Trigger trigger : triggers){
			if(trigger.used == false){
				if(trigger.hasCommand() && trigger.commandMatch(command)){
					if(trigger.checkConditionsTrue()){
						trigger.used = true;
						trigger.execute();
						for(Trigger trigger2 : triggers){
							if(trigger2.permanent){
								trigger2.used = false;
							}
						}
						return true;
					}
				}
			}
		}
		for(Trigger trigger : triggers){
			if(trigger.permanent){
				trigger.used = false;
			}
		}
		return false;
	}
	
	public void checkTriggers(){
		for(Trigger trigger : triggers){
			if(trigger.used == false){// || trigger.permanent){
				if(!trigger.hasCommand()){
					if(trigger.checkConditionsTrue()){
						trigger.used = true;
						trigger.execute();
					}
				}
			}
		}
		for(Trigger trigger : triggers){
			if(trigger.permanent){
				trigger.used = false;
			}
		}
	}
	
	public String getDescription(){
		return description;
	}
	
	
	
	public void setStatus(String status){
		this.status = status;
	}
	
	public ArrayList<Trigger> getTriggers(){
		Debug.println(name + " has " + triggers.size() + " triggers");
		return new ArrayList<Trigger>(triggers);
	}
	
	public String getStatus(){
		return status;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
	public void setDescription(String desc){
		description = desc;
	}
	
	public void addTrigger(Trigger trigger){
		triggers.add(trigger);
	}
	
}
