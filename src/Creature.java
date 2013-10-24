
import java.util.Set;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Map;


public class Creature extends TriggerableObject{

	private String name, status, description, attack;
	private Set<String> vulnerabilities = new HashSet<String>();
	
	public void addVulnerability(String vul){
		vulnerabilities.add(vul);
	}
	
	public String getStatus(){
		return status;
	}
	
	public void setStatus(String status){
		this.status = status;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
}
