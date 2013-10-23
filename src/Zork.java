import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.Scanner;
import java.util.ArrayList;

public class Zork {

	private Map<String, Room> room_list = new HashMap<String, Room>();
	private Map<String, Item> item_list = new HashMap<String, Item>();
	private Map<String, Creature> creature_list = new HashMap<String, Creature>();
	private Map<String, Container> container_list = new HashMap<String, Container>();
	private Set<String> inventory = new HashSet<String>();	
	private Room currentRoom;
	
	
	public Zork(String Filename){
		
	}
	
	private void Play(){
		Scanner in = new Scanner(System.in);
		String command;
		while(true){
			//get input
			System.out.print(">");
			command = in.nextLine();
			
			//directional check
			if(command.equals("n") || command.equals("s") || command.equals("e") || command.equals("w")){
				Debug.debugPrint("Directional command: " + command);
				String nextRoom = currentRoom.getBorder(command);
				if(nextRoom == null){
					System.out.println("Can't go that way");
				} else {
					//update contents of room
					room_list.put(currentRoom.getName(), currentRoom);
					currentRoom = getRoom(nextRoom);
				}
			}
			
			//print inventory
			else if(command.equals("i")){
				Debug.debugPrint("Show Inventory coomand");
				ArrayList<Item> inventory = new ArrayList<Item>(item_list.values());
				System.out.print("Inventory: ");
				for(int i = 0; i < inventory.size() - 1; i++){
					System.out.print(inventory.get(i).getName() + ",");
				}
				System.out.print(inventory.get(inventory.size() - 1).getName() + "\n");
			}
			
			//take item
			else if(command.contains("take")){
				String item = command.substring(5);
				Debug.debugPrint("Taking: " + item);
				if(currentRoom.hasItem(item)){
					currentRoom.removeItem(item);
					inventory.add(item);
				} else {
					Error();
				}
			}
			
			//open exit
			else if(command.equals("open exit")){
				if(currentRoom.getType().equals("exit"))
					break;
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
				break;
			}
			
			//================= BAD COMMAND ======================
			else{
				Error();
			}
		}
	}
	
	
	public boolean hasRoom(String name){
		return room_list.containsKey(name);
	}
	
	public void addRoom(String name, Room room){
		room_list.put(name, room);
	}
	
	public void removeRoom(String name){
		room_list.remove(name);
	}
	
	public Room getRoom(String name){
		return room_list.get(name);
	}
	
	public boolean hasItem(String name){
		return item_list.containsKey(name);
	}
	
	public void addItem(String name, Item item){
		item_list.put(name, item);
	}
	
	public void removeItem(String name){
		item_list.remove(name);
	}
	
	public Item getItem(String name){
		return item_list.get(name);
	}
	
	public boolean hasCreature(String name){
		return creature_list.containsKey(name);
	}

	public void addCreature(String name, Creature creature){
		creature_list.put(name, creature);
	}
	
	public void removeCreature(String name){
		creature_list.remove(name);
	}
	
	public Creature getCreature(String name){
		return creature_list.get(name);
	}
	
	public boolean hasContainer(String name){
		return container_list.containsKey(name);
	}
	
	public void addContainer(String name, Container container){
		container_list.put(name, container);
	}
	
	public void removeContainer(String name){
		container_list.remove(name);
	}
	
	public Container getContainer(String name){
		return container_list.get(name);
	}
	
	private void Error(){
		System.out.println("Error");
	}
	
}

