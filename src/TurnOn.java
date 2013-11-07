
public class TurnOn {
	
	private String print;
	private String action;
	
	
	
	public void execute(){
		System.out.println(print);
		Game.handleCommand(action);
	}
	
	public void setPrint(String print){
		this.print = print;
	}
	
	public void setAction(String action){
		this.action = action;
	}
	



}
