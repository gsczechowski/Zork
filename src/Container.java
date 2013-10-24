
import java.util.Set;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Map;

public class Container extends TriggerableObject{

	private String name, description, status;
	private Set<String> accepts = new HashSet<String>();
	private Set<String> items = new HashSet<String>();
	
	public void addAccept(String accept){
		accepts.add(accept);
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
