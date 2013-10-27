
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Iterator;


public class Room extends TriggerableObject{


	private String type = "regular";
	private Map<String, String> borders = new HashMap<String, String>();
	private Set<String> containers = new HashSet<String>();
	private Set<String> items = new HashSet<String>();
	private Set<String> creatures = new HashSet<String>();
	
	
	
	public void addContainer(String container){
		containers.add(container);
	}
	
	public void addCreature(String creature){
		creatures.add(creature);
	}
	
	
	
	public boolean hasBorder(String direction){
		return borders.containsKey(direction);
	}
	
	public void addBorder(String direction, String room){
		if(direction.length() != 1){
			System.out.println("Error! Bad direction given in setBorder(): not 1 char");
			return;
		}
		borders.put(direction, room);
	}
	
	public String getBorder(String name){
		return borders.get(name);
	}
	
	public boolean hasItem(String item){
		return items.contains(item);
	}
	
	public void removeItem(String item){
		items.remove(item);
	}
	
	public void addItem(String item){
		items.add(item);
	}
	
	
	public String getType(){
		return type;
	}
	
	public void setType(String type){
		this.type = type;
	}
	
}
