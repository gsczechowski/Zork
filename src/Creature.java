
import java.util.Set;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Map;


public class Creature extends TriggerableObject{

	private Attack attack;
	private Set<String> vulnerabilities = new HashSet<String>();
	
	public void addVulnerability(String vul){
		vulnerabilities.add(vul);
	}
	
	public void setAttack(Attack attack){
		this.attack = attack;
	}
	
}
