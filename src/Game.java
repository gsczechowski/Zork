import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;


public class Game {

	static private Map<String, Room> room_list = new HashMap<String, Room>();
	static private Map<String, Item> item_list = new HashMap<String, Item>();
	static private Map<String, Creature> creature_list = new HashMap<String, Creature>();
	static private Map<String, Container> container_list = new HashMap<String, Container>();
	static private Set<String> inventory = new HashSet<String>();
	static private Room currentRoom;

	public static void Play(){
		Scanner in = new Scanner(System.in);
		String command;
		//ArrayList<String> actions;
		while(true){
			//get input
			System.out.print(">");
			command = in.nextLine();
			
			//directional check
			if(command.equals("n") || command.equals("s") || command.equals("e") || command.equals("w")){
				Debug.println("Directional command: " + command);
				/*String nextRoom = currentRoom.getBorder(command);
				if(nextRoom == null){
					System.out.println("Can't go that way");
				} else {
					/*if((actions = getRoom(nextRoom).checkTriggersCommand(command)) == NULL){
						//update contents of room
						room_list.put(currentRoom.getName(), currentRoom);
						currentRoom = getRoom(nextRoom);
					} else {
						//found triggers
						
					}
				}*/
			}
			
			//print inventory
			else if(command.equals("i")){
				Debug.println("Show Inventory coomand");
			}
			
			//take item
			else if(command.contains("take")){
				String item = command.substring(5);
				Debug.println("Taking: " + item);
			}
			
			//open exit
			else if(command.equals("open exit")){
			}
			
			//open container
			else if(command.contains("open")){
				
			}
			
			//read item
			else if(command.contains("read")){
				
			}
			
			//drop item
			else if(command.contains("drop")){
				
			}
			
			//put item
			else if(command.contains("put")){
				
			}
			
			//turn on
			else if(command.contains("turn on")){
				
			}
			
			//attack
			else if(command.contains("attack")){
				
			}
			
			//look
			else if(command.equals("look")){
				
			}
			
			//===================SPECIAL COMMANDS==================
			//add object
			else if(command.contains("Add")){
				
			}
			
			//delete object
			else if(command.contains("Delete")){
				
			}
			
			//update
			else if(command.contains("Update")){
				
			}
			
			//game over
			else if(command.equals("Game Over")){
				System.out.println("Victory!");
				System.exit(0);
			}
			
			//================= BAD COMMAND ======================
			else{
				Error();
			}
		}
	}
	
	public void takeActions(ArrayList<String> commands){
		for(String command : commands){
			if(command.contains("Add")){
				
			}
			
			//delete object
			else if(command.contains("Delete")){
				
			}
			
			//update
			else if(command.contains("Update")){
				
			}
			
			//game over
			else if(command.equals("Game Over")){
				System.out.println("Victory!");
				System.exit(0);
			}
			
			//================= BAD COMMAND ======================
			else{
				Error();
			}
		}
	}
	
	static public boolean hasRoom(String name){
		return room_list.containsKey(name);
	}
	
	static public void addRoom(String name, Room room){
		room_list.put(name, room);
	}
	
	static public void removeRoom(String name){
		room_list.remove(name);
	}
	
	static public Room getRoom(String name){
		return room_list.get(name);
	}
	
	static public boolean hasItem(String name){
		return item_list.containsKey(name);
	}
	
	static public void addItem(String name, Item item){
		item_list.put(name, item);
	}
	
	static public void removeItem(String name){
		item_list.remove(name);
	}
	
	static public Item getItem(String name){
		return item_list.get(name);
	}
	
	static public boolean hasCreature(String name){
		return creature_list.containsKey(name);
	}

	static public void addCreature(String name, Creature creature){
		creature_list.put(name, creature);
	}
	
	static public void removeCreature(String name){
		creature_list.remove(name);
	}
	
	static public Creature getCreature(String name){
		return creature_list.get(name);
	}
	
	static public boolean hasContainer(String name){
		return container_list.containsKey(name);
	}
	
	static public void addContainer(String name, Container container){
		container_list.put(name, container);
	}
	
	static public void removeContainer(String name){
		container_list.remove(name);
	}
	
	static public Container getContainer(String name){
		return container_list.get(name);
	}
	
	static private void Error(){
		System.out.println("Error");
	}
}
