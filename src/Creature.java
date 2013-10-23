
import java.util.Set;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Map;

public class Creature {

	private String name, status, description, attack;
	private Set<String> vulnerabilities = new HashSet<String>();
	private Map<String, Trigger> triggers = new HashMap<String, Trigger>();
}
