public class TriggerCondition{

	private boolean status_type;
	private boolean has;
	private String object;
	private String owner;
	private String status;
	
	public TriggerCondition(){
	}
	
	public boolean checkConditionTrue(){
		Debug.println("Checking trigger condition");
		TriggerableObject obj;
		if(status != null){//if(status_type){
			if(Game.room_list.containsKey(object)){
				obj = Game.room_list.get(object);
				if(obj.getStatus() != null && obj.getStatus().equals(status))
					return true;
			} else if (Game.creature_list.containsKey(object)){
				obj = Game.creature_list.get(object);
				if(obj.getStatus() != null && obj.getStatus().equals(status))
					return true;
			} else if (Game.container_list.containsKey(object)){
				obj = Game.container_list.get(object);
				if(obj.getStatus() != null && obj.getStatus().equals(status))
					return true;
			} else if (Game.item_list.containsKey(object)){
				obj = Game.item_list.get(object);
				if(obj.getStatus() != null && obj.getStatus().equals(status))
					return true;
			} else {
				System.out.println("Object not found in trigger.\n");
				Game.Error();
			}
		} else {
			if(owner.equals("inventory")){
				Debug.println("Checking if inventory contains: " + object);
				if(Game.inventory.contains(object) == has){
					Debug.println("It does!");
					return true;
				}
				return false;
			} else {
				if(Game.room_list.containsKey(owner)){
					Room room = Game.room_list.get(owner);
					return room.hasItem(object) == has;
				} else if (Game.container_list.containsKey(owner)){
					Container container = Game.container_list.get(owner);
					return container.hasItem(object) == has;
				} else {
					System.out.println("Object not found in trigger.\n");
					Game.Error();
				}
			}
		}
		return false;
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
	
	public void setOwner(String posessor){
		//System.out.println("Owner has been set!: " + posessor);
		this.owner = posessor;
	}
	
	public void setStatus(String status){
		this.status = status;
	}
	

	
}
