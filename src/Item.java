import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

public class Item extends TriggerableObject{

	private String name = null, status = null, description = null, writing = "Nothing written.";
	private String turn_on = null;
	private Set<Trigger> triggers = new HashSet<Trigger>();
	
	public Item(){
		
	}
	
	public ArrayList<Trigger> getTriggers(){
		return new ArrayList<Trigger>(triggers);
	}
	
	public void addTrigger(Trigger trigger){
		triggers.add(trigger);
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
	public void setStatus(String status){
		this.status = status;
	}
	
	public String getStatus(){
		return status;
	}
	
	public void setDescription(String descr){
		this.description = descr;
	}
	
	public String getDescription(){
		return description;
	}
	
	public void setWriting(String writing){
		this.writing = writing;
	}
	
	public String getWriting(){
		return writing;
	}
	
}
