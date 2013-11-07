import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.io.*;


public class Game {

	static public Map<String, Room> room_list = new HashMap<String, Room>();
	static public Map<String, Item> item_list = new HashMap<String, Item>();
	static public Map<String, Creature> creature_list = new HashMap<String, Creature>();
	static public Map<String, Container> container_list = new HashMap<String, Container>();
	//static private Set<String> inventory = new HashSet<String>();
	static public ArrayList<String> inventory= new ArrayList<String>();
	static private Room currentRoom = null;
	static private boolean trig_enable = true;

	
	
	public static void handleCommand(String command){
		String ret_str;
		if(!checkCommandTriggers(command)){
			Debug.println("No command trigger executed");
			//directional check
			if(command.equals("n") || command.equals("s") || command.equals("e") || command.equals("w")){
				Debug.println("Directional command: " + command);
				if(currentRoom.hasBorder(command)){
					ret_str = currentRoom.getBorder(command);
					currentRoom = room_list.get(ret_str);
					System.out.println(currentRoom.getDescription());
					//checkContextualTriggers();
				} else {
					System.out.println("Cannot go that way.");
				}
				checkContextualTriggers();

			}

			//print inventory
			else if(command.equals("i")){
				Debug.println("Show Inventory coomand");
				System.out.print("Inventory: ");
				String outstring = "";
				if(inventory.isEmpty()){
					System.out.println("empty");
				} else {
					for(String item: inventory){
						outstring = outstring + item + ", ";
					}
					System.out.println(outstring.substring(0, outstring.length() - 2));
				}
			}

			//take item
			else if(command.contains("take")){
				String item = command.substring(5);
				Debug.println("Taking: " + item);
				ArrayList<String> availableItems = currentRoom.getAvailableItems();
				if(availableItems.contains(item)){
					if(currentRoom.hasItem(item)){
						currentRoom.removeItem(item);
						inventory.add(item);
						System.out.println("Item " + item + " added to inventory.");
					} else {
						for(String container : currentRoom.getContainers()){
							if(container_list.get(container).getItems().contains(item) && !container_list.get(container).getStatus().equals("locked")){
								container_list.get(container).removeItem(item);
								//System.out.println("Taking item:" + item);
								inventory.add(item);
								System.out.println("Item " + item + " added to inventory.");
								//checkContextualTriggers();
							} else if (container_list.get(container).getItems().contains(item) && container_list.get(container).accepts(item)){
								container_list.get(container).removeItem(item);
								//System.out.println("Taking item:" + item);
								inventory.add(item);
								System.out.println("Item " + item + " added to inventory.");
								//checkContextualTriggers();
							}
						}
					}
				} else {
					Debug.println("Item not available.");
					Error(); ////////////////////////////SOURCE OF ATTACK DRAGON ERROR MSG
				}
				checkContextualTriggers();
			}

			//open exit
			else if(command.equals("open exit")){
				if(currentRoom.getType().equals("exit")){
					System.out.println("Game Over");
					System.exit(0);
				} else {
					Error();
				}
			}

			//open container
			else if(command.contains("open")){
				String container = command.substring(5);
				if(container_list.containsKey(container)){ 
					if(!container_list.get(container).getStatus().equals("locked")){
						container_list.get(container).setStatus("open");
						if(container_list.get(container).getItems().size() != 0){
							System.out.print(container + " contains ");
							for(String item : container_list.get(container).getItems()){
								System.out.print(item + " ");
							}
							System.out.println("");
						} else {
							System.out.println(container + " is empty.");
						}
					} else {
						Error();
					}
				} else {
					Error();
				}
			}

			//read item
			else if(command.contains("read")){
				String item = command.substring(5);
				if(inventory.contains(item)){
					System.out.println(item_list.get(item).getWriting());
				} else {
					Error();
				}
				checkContextualTriggers();
			}

			//drop item
			else if(command.contains("drop")){
				String item = command.substring(5);
				if(inventory.contains(item)){
					inventory.remove(item);
					currentRoom.addItem(item);
					System.out.println(item + " dropped");
					//checkContextualTriggers();
				} else {
					Error();
				}
			}

			//put item
			else if(command.contains("put")){
				if(command.contains(" in ")){
					String item = command.substring(4, command.indexOf("in") - 1);
					String container = command.substring(command.indexOf("in ") + 3);
					//System.out.println("Item:" + item);
					//System.out.println("Container:" + container);
					if(inventory.contains(item)){
						if(currentRoom.hasContainer(container)){
							if(container_list.get(container).getStatus().equals("open")){
								inventory.remove(item);
								container_list.get(container).addItem(item);
								System.out.println("Item " + item + " added to " + container);
								//checkContextualTriggers();
							} else if(container_list.get(container).accepts(item)){
								inventory.remove(item);
								container_list.get(container).addItem(item);
								System.out.println("Item " + item + " added to " + container);
							} else {
								Error();
							}
						} else {
							Error();
						}
					} else {
						Error();
					}
				} else {
					Debug.println(" in  was not found in put ommand.");
					Error();
				}
				checkContextualTriggers();
			}

			//turn on
			else if(command.contains("turn on")){
				String item = command.substring(command.indexOf(" on") + 4, command.length());
				if(inventory.contains(item)){
					item_list.get(item).turnOn();
				} else {
					Error();
				}
				checkContextualTriggers();
			}

			//attack
			else if(command.contains("attack")){
				String creature = command.substring(7, command.indexOf(" with"));
				String weapon = command.substring(command.indexOf("with ") + 5, command.length());
				Debug.println("Attcking: " + creature);
				Debug.println("with: " + weapon);
				if(currentRoom.hasCreature(creature) && inventory.contains(weapon)){
					String attack_msg = "You assault the " + creature + " with the " + weapon + ".";
					creature_list.get(creature).attack(weapon, attack_msg);
				} else {
					Debug.println("Error in attack command");
					Error();
				}
				checkContextualTriggers();
			}

			//look
			else if(command.equals("look")){
				for(String item : currentRoom.getItems()){
					System.out.println(item + ": " + item_list.get(item).getDescription());
				}
				for(String container : currentRoom.getContainers()){
					System.out.println(container + ": " + container_list.get(container).getDescription());
				}
				for(String creature : currentRoom.getCreatures()){
					System.out.println(creature + ": " + creature_list.get(creature).getDescription());
				}

			}
			
			//===================SPECIAL COMMANDS==================
			//add object
			else if(command.contains("Add")){
				String item = command.substring(4, command.indexOf(" to"));
				String object = command.substring(command.indexOf(" to") + 4, command.length());
				//System.out.println("Adding Item: " + item + ".");
				//System.out.println("to: " + object + ".");
				if(item_list.containsKey(item)){
					if(room_list.containsKey(object)){
						room_list.get(object).addItem(item);
					} else if(container_list.containsKey(object)){
						container_list.get(object).addItem(item);
					} else {
						System.out.println("Error: bad object.");
					}
					
				} else if(creature_list.containsKey(item)){
					if(room_list.containsKey(object)){
						room_list.get(object).addCreature(item);
					} else {
						System.out.println("Error: bad object");
					}
				}else {
					System.out.println("Error: bad item.");
				}
				checkContextualTriggers();
			}

			//delete object
			else if(command.contains("Delete")){
				String object = command.substring(7);
				if(room_list.containsKey(object)){
					for(String roomName : room_list.keySet()){
						room_list.get(roomName).deleteBorder(object);
					}
				} else if (item_list.containsKey(object)){
					for(String roomName : room_list.keySet()){
						if(room_list.get(roomName).hasItem(object)){
							room_list.get(roomName).removeItem(object);
						}
					}
				} else if (container_list.containsKey(object)){
					for(String roomName : room_list.keySet()){
						if(room_list.get(roomName).hasContainer(object)){
							room_list.get(roomName).deleteContainer(object);
						}
					}
				} else if (creature_list.containsKey(object)){
					for(String roomName : room_list.keySet()){
						if(room_list.get(roomName).hasCreature(object)){
							room_list.get(roomName).deleteCreature(object);
						}
					}
				} else {
					System.out.println("Bad delete command.");
				}
				checkContextualTriggers();
			}

			//update
			else if(command.contains("Update")){
				String object = command.substring(7, command.indexOf(" to "));
				String status = command.substring(command.indexOf(" to ") + 4, command.length());
				Debug.println("Setting status of " + object + " to " + status);
				if(room_list.containsKey(object)){
					room_list.get(object).setStatus(status);
				} else if (item_list.containsKey(object)){
					item_list.get(object).setStatus(status);
				} else if (container_list.containsKey(object)){
					container_list.get(object).setStatus(status);
				} else if (creature_list.containsKey(object)){
					creature_list.get(object).setStatus(status);
				} else {
					System.out.println("Bad update command");
				}
				checkContextualTriggers();
			}

			//game over
			else if(command.equals("Game Over")){
				System.out.println("Victory!");
				System.exit(0);
			}

			//================= BAD COMMAND ======================
			else{
				//System.out.println("Bad command");
				Error();
			}
		}
	}
	
	
	public static void Play(){
		Scanner in = new Scanner(System.in);
		String command;
		while(true){
			//get input
			try{
				System.out.print(">");
				command = in.nextLine();
				handleCommand(command);
			} catch (ArrayIndexOutOfBoundsException e){
				Debug.println("Bad command caught");
				System.out.println(e);
				Error();
			}
		} 
	}
	



		static public boolean checkCommandTriggers(String command){
			if(!trig_enable){
				return false;
			}
			Debug.println("Checking command triggers");
			Debug.println("Checking current room command triggers: ");
			boolean ret = currentRoom.checkCommandTriggers(command);
			Debug.println("Done!\n Checking inventory command triggers:");
			ret = ret || checkInventoryCommandTriggers(command);
			Debug.println("Done!");
			return ret;

		}

		static public void checkContextualTriggers(){
			if(!trig_enable){
				return;
			}
			Debug.println("Checking contextual triggers");
			currentRoom.checkTriggers();
			checkInventoryTriggers();
		}

		static private boolean checkInventoryCommandTriggers(String command){
			if(!trig_enable){
				return false;
			}
			boolean check = false;
			for(String item : inventory){
				check = check || item_list.get(item).checkCommandTriggers(command);
			}
			return check;
		}

		static private void checkInventoryTriggers(){
			if(!trig_enable){
				return;
			}
			Debug.println("Checking inventory triggers");
			for(String item : inventory){
				item_list.get(item).checkTriggers();
			}
		}

		
		static public void disableTrig(){
			trig_enable = false;
		}
		
		static public void enableTrig(){
			trig_enable = true;
		}

		static public Room getCurrentRoom(){
			return currentRoom;
		}

		static public void  setCurrentRoom(String room){
			currentRoom = room_list.get(room);
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

		static public void Error(){
			System.out.println("Error");
		}
	}
