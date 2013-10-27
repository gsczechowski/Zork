
import java.util.Set;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Map;

public class Container extends TriggerableObject{

	private Set<String> accepts = new HashSet<String>();
	private Set<String> items = new HashSet<String>();
	

	
	public void addAccept(String accept){
		accepts.add(accept);
	}
	

	
	
	
}
