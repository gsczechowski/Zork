public class TriggerCondition{

	private boolean type_command;
	private String command = null;
	private String object;
	private String owner;
	
	
	public void checkCondition(String command){
		if(type_command){
			if(this.command.equals(command)){
				return true;
			} else {
				return false;
			}
		} else {
			
		}
	}
	
	public void checkCondition(){
		checkCondition(null);
	}
	
}