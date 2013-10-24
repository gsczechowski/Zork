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
				Attack currentAttack;
				Trigger currentTrigger;
				Room currentRoom; 
				Creature currentCreature;
				Container currentContainer;
				TriggerCondition currentCondition;
				Item currentItem;
				String value, direction, name;
				
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
						//TODO
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
						//TODO
						break;
						
					case "has":
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
						//TODO
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
						//TODO
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
						} else {
							//TODO
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
						//TODO
						break;
						
					case "trigger":
						if(inRoom){
							Debug.println("Adding trigger to room");
							currentRoom.addTrigger(currentTrigger);
							currentTrigger = null;
							inTrigger = false;
						} else {
							
						}
						//TODO
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
								//TODO
							} else {
								Debug.println("Adding type to room: " + value);
								currentRoom.setType(value);
							}
						}
						//TODO
						getValue = false;
						break;
					
					case "command":
						//TODO
						getValue = false;
						break;
						
					case "print":
						//TODO
						getValue = false;
						break;
						
					case "condition":
						//TODO
						break;
						
					case "has":
						//TODO
						if(value.equals("yes")){
							//TODO
						} else if(value.equals("no")){
							//TODO
						} else {
							System.out.println("Bad has.");
						}
						getValue = false;
						break;
						
					case "object":
						//TODO
						getValue = false;
						break;
						
					case "owner":
						//TODO
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
								
							} else {
								Debug.println("Setting room status to: " + value);
								currentRoom.setStatus(value);
							}
						} else if(inItem){
							if(inTrigger){
								
							} else {
								Debug.println("Setting item status to: " + value);
								currentItem.setStatus(value);
							}
						} else if(inContainer){
							if(inTrigger){
								
							} else {
								Debug.println("Setting container status to: " + value);
								currentContainer.setStatus(value);
							}
						} else if(inCreature){
							if(inTrigger){
								
							} else {
								Debug.println("Setting creature status to: " + value);
								currentCreature.setStatus(value);
							}
						} else {
							System.out.println("HANGING STATUS");
						}
						//TODO
						getValue = false;
						break;
						
					case "turnon":
						//TODO
						getValue = false;
						break;
						
					case "action":
						//TODO
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
						//TODO
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
						//TODO
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
