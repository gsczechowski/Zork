import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;


public class TriggerableObject {
	
	
	protected String description, name, status;
	protected Set<Trigger> triggers = new HashSet<Trigger>();
	
	
	
	public void setStatus(String status){
		this.status = status;
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
