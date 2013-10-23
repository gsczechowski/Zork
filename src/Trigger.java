import java.util.ArrayList;

public class Trigger {

	private boolean permanent; //!type
	private String command = null;
	private ArrayList<String> actions = new ArrayList<String>();
	private ArrayList<String> prints = new ArrayList<String>();
	
	public boolean hasCommand(){
		return command != null;
	}
	
	public String getCommand(){
		return command;
	}
	
	public ArrayList<String> getActions(){
		return actions;
	}
	
	public boolean isPermanent(){
		return permanent;
	}
	
	public ArrayList<String> getPrints(){
		return prints;
	}
}
