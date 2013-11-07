import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

public class Item extends TriggerableObject{

	private String writing = "Nothing written.";
	private String turn_on = null;
	private Set<Trigger> triggers = new HashSet<Trigger>();
	private TurnOn turnOn;
	
	public Item(){
		
	}
	
	public void setTurnOn(TurnOn TO){
		this.turnOn = TO;
	}
	
	
	public void addTrigger(Trigger trigger){
		triggers.add(trigger);
	}
	
	
	public void turnOn(){
		if(turnOn != null){
			System.out.println("You active the " + name + ".");
			turnOn.execute();
		} else {
			Game.Error();
		}
	}
	
	
	public void setWriting(String writing){
		this.writing = writing;
	}
	
	public String getWriting(){
		return writing;
	}
	
}
