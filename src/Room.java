
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Iterator;


public class Room extends TriggerableObject{

	private String name;
	private String status;
	private String type = "regular";
	private Map<String, String> borders = new HashMap<String, String>();
	private Set<String> containers = new HashSet<String>();
	private Set<String> items = new HashSet<String>();
	private Set<String> creatues = new HashSet<String>();
	//private Set<Trigger> triggers = new HashSet<Trigger>();
	
	/*public ArrayList<String> checkTriggersCommand(String command){
		ArrayList<String> actions = new ArrayList<String>();
		if(triggers.isEmpty()){
			return null;
		}
		for(Trigger trigger : triggers){
			if(trigger.hasCommand() && trigger.getCommand().equals(command)){
				actions.addAll(trigger.getActions());
				if(!trigger.isPermanent())
					triggers.remove(trigger);
			}
		}
		actions.addAll(checkTriggers());
		return actions;
	}
	
	public ArrayList<String> checkTriggers(){
		ArrayList<String> actions = new ArrayList<String>();
		if(triggers.isEmpty()){
			return null;
		}
		for(Trigger trigger : triggers){
			if(!trigger.hasCommand()){
				actions.addAll(trigger.getActions());
				if(!trigger.isPermanent())
					triggers.remove(trigger);
			}
		}
		return actions;
	}*/
	
	public boolean hasBorder(String direction){
		return borders.containsKey(direction);
	}
	
	public void setBorder(String direction, String room){
		if(direction.length() != 1){
			System.out.println("Error! Bad direction given in setBorder(): not 1 char");
			return;
		}
		borders.put(direction, room);
	}
	
	public String getBorder(String name){
		return borders.get(name);
	}
	
	public String getName(){
		return name;
	}
	
	public boolean hasItem(String item){
		return items.contains(item);
	}
	
	public void removeItem(String item){
		items.remove(item);
	}
	
	public void giveItem(String item){
		items.add(item);
	}
	
	
	public String getType(){
		return type;
	}
	
	public void setType(String type){
		this.type = type;
	}
}
