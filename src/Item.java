import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.ArrayList;

public class Item extends TriggerableObject{

	private String name = null, status = null, description = null, writing = "Nothing written.";
	private String turn_on = null;
	//private Map<String, Trigger> triggers = new HashMap<String, Trigger>();
	
	public Item(){
		
	}
	
	public ArrayList<Trigger> getTriggers(){
		return new ArrayList<Trigger>(triggers.values());
	}
	
	public void addTrigger(String name, Trigger trigger){
		triggers.put(name, trigger);
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
