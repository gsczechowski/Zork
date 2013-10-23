
import java.util.Set;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Map;

public class Container extends TriggerableObject{

	private String name, description;
	private Set<String> accepts = new HashSet<String>();
	private Set<String> items = new HashSet<String>();
	//private Map<String, Trigger> triggers = new HashMap<String, Trigger>();
	
}
