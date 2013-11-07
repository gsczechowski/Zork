
import java.util.Set;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

public class Container extends TriggerableObject{

	private Set<String> accepts = new HashSet<String>();
	private Set<String> items = new HashSet<String>();
	
	public Container(){
		status = "closed";
	}
	
	public void addItem(String item){
		items.add(item);
	}
	
	public boolean accepts(String item){
		if(accepts.contains(item)){
			return true;
		}
		return false;
	}
	
	public boolean hasItem(String item){
		return items.contains(item);
	}
	
	
	public void listAccepts(){
		System.out.println(accepts);
	}
	

	public void removeItem(String item){
		items.remove(item);
	}
	
	public void addAccept(String accept){
		accepts.add(accept);
	}
	
	public ArrayList<String> getItems(){
		return new ArrayList<String>(items);
	}
	

	
	
	
}
