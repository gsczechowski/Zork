import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;


public class TriggerableObject {

	private Set<Trigger> triggers = new HashSet<Trigger>();
	
	public void addTrigger(Trigger trigger){
		triggers.add(trigger);
	}
	
}
