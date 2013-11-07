
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
	
	
	
	public boolean checkCommandTriggers(String command){
		Debug.println("Checking room command triggers");
		//Debug.println("Number of creatures in room: " + creatures.size());
		ArrayList<Trigger> all_trig = new ArrayList<Trigger>(triggers);
		for(String item : items){
			all_trig.addAll(Game.item_list.get(item).getTriggers());
		}
		for(String container : containers){
			all_trig.addAll(Game.container_list.get(container).getTriggers());
		}
		for(String creature : creatures){
			//Debug.println(creature);
			all_trig.addAll(Game.creature_list.get(creature).getTriggers());
		}
		Debug.println(all_trig.size() + " triggers found in room.");
		for(Trigger trigger : all_trig){
			if(trigger.used == false){
				if(trigger.hasCommand() && trigger.commandMatch(command)){
					if(trigger.checkConditionsTrue()){
						trigger.execute();
						trigger.used = true;
						for(Trigger trigger2 : all_trig){
							if(trigger2.permanent){
								trigger2.used = false;
							}
						}
						return true;
					}
				}
			}
		}
		for(Trigger trigger : all_trig){
			if(trigger.permanent){
				trigger.used = false;
			}
		}
		return false;

	}
	
	public void checkTriggers(){
		Debug.println("Checking room triggers");
		ArrayList<Trigger> all_trig = new ArrayList<Trigger>(triggers);
		for(String item : items){
			all_trig.addAll(Game.item_list.get(item).getTriggers());
		}
		for(String container : containers){
			all_trig.addAll(Game.container_list.get(container).getTriggers());
		}
		for(String creature : creatures){
			all_trig.addAll(Game.creature_list.get(creature).getTriggers());
		}
		for(Trigger trigger : all_trig){
			Debug.println("Trigger found: checking");
			if(trigger.used == false){// || trigger.permanent){
				if(!trigger.hasCommand()){
					if(trigger.checkConditionsTrue()){
						trigger.used = true;
						trigger.execute();
					}
				}
			}
		}
		for(Trigger trigger : all_trig){
			if(trigger.permanent){
				trigger.used = false;
			}
		}
		Debug.println("Done checking all room triggers");
	}
	
	
	
	
	
	public String getBorderRoom(String direction){
		if(borders.containsKey(direction)){
			return borders.get(direction);
		} 
		return null;
	}
	
	
	public ArrayList<String> getAvailableItems(){
		ArrayList<String> itemsAround = new ArrayList<String>(items);
		for(String container : containers){
			if(!Game.getContainer(container).getStatus().equals("locked") && !Game.getContainer(container).getStatus().equals("closed")){
				itemsAround.addAll(Game.getContainer(container).getItems());
			} else {
				Debug.println(container + " is locked");
			}
		}
		return itemsAround;
	}
	
	public void deleteBorder(String roomName){
		for(String key : borders.keySet()){
			if(borders.get(key).equals(roomName)){
				borders.remove(key);
			}
		}

	}
	
	public ArrayList<String> getCreatures(){
		return new ArrayList<String>(creatures);
	}
	
	
	public boolean hasCreature(String creature){
		return creatures.contains(creature);
	}
	
	
	
	
	public ArrayList<String> getItems(){
		return new ArrayList<String>(items);
	}
	
	
	
	public void addContainer(String container){
		containers.add(container);
	}
	
	public void deleteContainer(String container){
		containers.remove(container);
	}
	
	public void addCreature(String creature){
		creatures.add(creature);
	}
	
	public void deleteCreature(String creature){
		creatures.remove(creature);
	}
	
	public boolean hasContainer(String container){
		return containers.contains(container);
	}
	
	public ArrayList<String> getContainers(){
		return new ArrayList<String>(containers);
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
