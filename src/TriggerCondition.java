public class TriggerCondition{

	private String command = null;
	private boolean status_type;
	private boolean has;
	private String object;
	private String owner;
	private String status;
	
	public TriggerCondition(){
	}
	
	public void setHas(boolean has){
		this.has = has;
		this.status_type = false;
	}
	
	public void setStatusType(boolean status_type){
		this.status_type = status_type;
	}
	
	public void setObject(String object){
		this.object = object;
	}
	
	public void setOwner(String owner){
		this.owner = owner;
	}
	
	public void setStatus(String status){
		this.status = status;
	}
	

	
}
