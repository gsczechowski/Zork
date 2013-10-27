import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XMLParser {

	//HEY TIRED PERSON:
	//nothing triggers related has been done
	//not all closing had been done
	//most opening have been done
	//gogo
	
	public void Parse(String filename){
		try {

			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();

			DefaultHandler handler = new DefaultHandler() {

				boolean bad = false;
				boolean inRoom = false;
				boolean inBorder = false;
				boolean getValue = false;
				boolean inCreature = false;
				boolean inContainer = false;
				boolean inTrigger = false;
				boolean inAttack = false;
				boolean inItem = false;
				boolean inCondition = false;
				boolean type_has = false;
				boolean inTurnOn = false;
				Attack currentAttack;
				Trigger currentTrigger;
				Room currentRoom; 
				Creature currentCreature;
				Container currentContainer;
				TriggerCondition currentCondition;
				Item currentItem;
				TurnOn currentTurnOn;
				String value, direction, name, type;
				
				public void startElement(String uri, String localName,String qName, Attributes attributes) throws SAXException {
					//System.out.println("Start Element:" + qName);
					switch (qName){
					case "map":
						Debug.println("Parsing XML");
						break;
					
					case "room":
						Debug.print("Found room:");
						inRoom = true;
						currentRoom = new Room();
						break;
					
					case "name":
						getValue = true;
						break;
						
					case "description":
						getValue = true;
						break;
					
					case "item":
						if(inRoom){
							getValue = true;
						} else if(inContainer){
							getValue = true;
						} else {
							Debug.println("Found item:");
							currentItem = new Item();
							inItem = true;
						}
						break;
						
					case "trigger":
						inTrigger = true;
						currentTrigger = new Trigger();
						break;
						
					case "border":
						inBorder = true;
						break;
						
					case "type":
						getValue = true;
						break;
					
					case "command":
						getValue = true;
						break;
						
					case "print":
						getValue = true;
						break;
						
					case "condition":
						inCondition = true;
						currentCondition = new TriggerCondition();
						break;
						
					case "has":
						type_has = true;
						getValue = true;
						break;
						
					case "object":
						getValue = true;
						break;
						
					case "owner":
						getValue = true;
						break;
						
					case "direction":
						getValue = true;
						break;
						
					case "writing":
						getValue = true;
						break;
						
					case "status":
						getValue = true;
						break;
						
					case "turnon":
						inTurnOn = true;
						currentTurnOn = new TurnOn();
						break;
						
					case "action":
						getValue = true;
						break;
						
					case "container":
						if(inRoom){
							getValue = true;
						} else {
							inContainer = true;
							currentContainer = new Container();
						}
						break;
						
					case "creature":
						if(inRoom){
							getValue = true;
						} else {
							inCreature = true;
							currentCreature = new Creature();
						}
						break;
						
					case "vulnerability":
						getValue = true;
						break;
						
					case "attack":
						currentAttack = new Attack();
						break;
						
					case "accept":
						getValue = true;
						break;
						
					default:
						System.out.println("Bad tag found: " + qName);
						bad = true;
						break;
					}
					
				}

				public void endElement(String uri, String localName, String qName) throws SAXException {
					//System.out.println("End Element :" + qName);
					switch (qName){
					case "map":
						Debug.println("Done Parsing XML");
						break;
					
					case "room":
						Debug.println("GAME: Adding room to game: " + currentRoom.getName());
						Game.addRoom(currentRoom.getName(), currentRoom);
						currentRoom = null;
						inRoom = false;
						break;
					
					case "name":
						if(inRoom){
							if(inBorder){
								name = value;
							} else {
								Debug.println("Setting name to: " + value);
								currentRoom.setName(value);
								
							}
						} else if (inItem){
							Debug.println("Setting Item name to: " + value);
							currentItem.setName(value);
						} else if (inContainer) {
							Debug.println("Setting container name to: " + value);
							currentContainer.setName(value);
						} else if (inCreature){
							Debug.println("Setting creature name to: " + value);
							currentCreature.setName(value);
						}
						getValue = false;
						break;
					
					case "description":
						if(inRoom){
							Debug.println("Setting description to: " + value);
							currentRoom.setDescription(value);
						} else if(inItem){
							Debug.println("Setting description to: " + value);
							currentItem.setDescription(value);
						} else if(inContainer){
							Debug.println("Setting description to: " + value);
							currentContainer.setDescription(value);
						} else if(inCreature){
							Debug.println("Setting description to: " + value);
							currentCreature.setDescription(value);
						} else {
							System.out.println("Bad description");
						}
						getValue = false;
						break;
					
					case "item":
						if(inRoom){
							Debug.println("Adding item: " + value);
							currentRoom.addItem(value);
							getValue = false;
						} else if(inContainer) {
							
						} else {
							Debug.println("GAME: adding item: " + currentItem.getName());
							Game.addItem(currentItem.getName(), currentItem);
							currentItem = null;
							inItem = false;
						}
						break;
						
					case "trigger":
						if(type.equals("permanent")){
							Debug.println("Trigger: setting type to: permanent");
							currentTrigger.setPermanent(true);
						} else if (type.equals("single")){
							Debug.println("Trigger: setting type to: single");
							currentTrigger.setPermanent(false);
						} else if (type == null){
							Debug.println("Trigger: setting type to: single");
							currentTrigger.setPermanent(false);
						} else {
							System.out.println("Error in trigger type");
						}
						if(inRoom){
							Debug.println("Adding trigger to room");
							currentRoom.addTrigger(currentTrigger);
							currentTrigger = null;
							inTrigger = false;
						} else {
							
						}
						type = null;
						break;
						
					case "border":
						inBorder = false;
						Debug.println("Adding border to room: " + direction + " " + name);
						switch(direction){
						case "north":
							direction = "n";
							break;
						case "south":
							direction = "s";
							break;
						case "east":
							direction = "e";
							break;
						case "west":
							direction = "w";
							break;
						}
						currentRoom.addBorder(direction, name);
						direction = null;
						name = null;
						inBorder = false;
						break;
						
					case "type":
						if(inRoom){
							if(inTrigger){
								type = value;
								//Debug.println("Setting trigger type to: " + value);
								//currentTrigger.setType(value);
							} else {
								Debug.println("Adding type to room: " + value);
								currentRoom.setType(value);
							}
						} else if (inTrigger){
							type = value;
							//Debug.println("Setting trigger type to: " + value);
							//currentTrigger.setType(value);
						}
						getValue = false;
						break;
					
					case "command":
						Debug.println("Setting command to: " + value);
						currentTrigger.setCommand(value);
						getValue = false;
						break;
						
					case "print":
						if(inAttack){
							Debug.println("Adding print to attack: " + value);
							currentAttack.addPrint(value);
						}
						if(inTurnOn){
							Debug.println("Adding print to turn on: " + value);
							currentTurnOn.setPrint(value);
						} else if(inTrigger){
							Debug.println("Adding print to trigger: " + value);
							currentTrigger.addPrint(value);
						} else {
							System.out.println("Error: bad print found.");
						}
						getValue = false;
						break;
						
					case "condition":
						if(inCondition){
							if(type_has){
								currentCondition.setStatusType(false);
							} else {
								currentCondition.setStatusType(true);
							}
							currentTrigger.addCondition(currentCondition);
							currentCondition = null;
							Debug.println("Adding condition to Trigger.");
						} else if (inAttack){
							currentAttack.setCondition(currentCondition);
						}else {
							System.out.println("Bad condition structure.");
						}
						break;
						
					case "has":
						if(value.equals("yes")){
							Debug.println("Setting has to: yes");
							currentCondition.setHas(true);
						} else if(value.equals("no")){
							Debug.println("Setting has to: no");
							currentCondition.setHas(false);
						} else {
							System.out.println("Bad has.");
						}
						getValue = false;
						break;
						
					case "object":
						Debug.println("Setting condition object to: " + value);
						currentCondition.setObject(value);
						getValue = false;
						break;
						
					case "owner":
						Debug.println("Setting condtion owner to: " + value);
						currentCondition.setOwner(value);
						getValue = false;
						break;
						
					case "direction":
						direction = value;
						getValue = false;
						break;
						
					case "writing":
						Debug.println("Setting writing to: " + value);
						currentItem.setWriting(value);
						getValue = false;
						break;
						
					case "status":
						if(inRoom) {
							if(inTrigger){
								Debug.println("Setting current condition status to: " + value);
								currentCondition.setStatus(value);
							} else {
								Debug.println("Setting room status to: " + value);
								currentRoom.setStatus(value);
							}
						} else if(inItem){
							if(inTrigger){
								Debug.println("Setting current condition status to: " + value);
								currentCondition.setStatus(value);
							} else {
								Debug.println("Setting item status to: " + value);
								currentItem.setStatus(value);
							}
						} else if(inContainer){
							if(inTrigger){
								Debug.println("Setting current condition status to: " + value);
								currentCondition.setStatus(value);
							} else {
								Debug.println("Setting container status to: " + value);
								currentContainer.setStatus(value);
							}
						} else if(inCreature){
							if(inTrigger){
								Debug.println("Setting current condition status to: " + value);
								currentCondition.setStatus(value);
							} else {
								Debug.println("Setting creature status to: " + value);
								currentCreature.setStatus(value);
							}
						} else {
							System.out.println("HANGING STATUS");
						}
						getValue = false;
						break;
						
					case "turnon":
						currentItem.setTurnOn(currentTurnOn);
						currentTurnOn = null;
						inTurnOn = false;
						getValue = false;
						break;
						
					case "action":
						if(inTurnOn){
							Debug.println("Action added to turn on: " + value);
							currentTurnOn.setAction(value);
						} else if(inTrigger){
							Debug.println("Action added to trigger: " + value);
							currentTrigger.addAction(value);
						} else {
							System.out.println("Bad action found.");
						}
						getValue = false;
						break;
						
					case "container":
						if(inRoom){
							Debug.println("Adding container to room: " + value);
							currentRoom.addContainer(value);
							getValue = false;
						} else {
							Debug.println("GAME: adding container: " + currentContainer.getName());
							Game.addContainer(currentContainer.getName(), currentContainer);
							currentContainer = null;
							inContainer = false;
						}
						break;
						
					case "creature":
						if(inRoom){
							Debug.println("Adding creature to room: " + value);
							currentRoom.addCreature(value);
							getValue = false;
						} else {
							Debug.println("GAME: adding creature: " + currentCreature.getName());
							Game.addCreature(currentCreature.getName(), currentCreature);
							currentCreature = null;
						}
						
						break;
						
					case "vulnerability":
						Debug.println("Adding vulnerability to current creature: " + value);
						currentCreature.addVulnerability(value);
						getValue = false;
						break;
						
					case "attack":
						//Debug.println("Adding attack to current creature: " + value);
						break;
						
					case "accept":
						Debug.println("Adding accept to container: " + value);
						currentContainer.addAccept(value);
						getValue = false;
						break;
						
					default:
						System.out.println("Bad tag found: " + qName);
						bad = false;
						break;
					}
					
					
					//clear value
					value = null;
				}

				public void characters(char ch[], int start, int length) throws SAXException {
					//System.out.println("Element: " + new String(ch, start, length));
					if(getValue){
						value = new String(ch, start, length);
						//Debug.println("Read in value: " + value);
					}
				}

			};

			saxParser.parse(filename, handler);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
