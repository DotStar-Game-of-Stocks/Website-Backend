/***
 * @author YuWu
 * last update:2015/4/9 23:09
 */
public class Operation {                     //extends from User class
	private String action="";
	private String condition=""; 
	//private Stock targetStock;
	
	//constructor #1
	public Operation(String newAction, String newCondition){
		this.action=newAction;
		this.condition=newCondition;
	}
	//constructor#2
	public Operation(Operation old){
		this.action=old.getAction();
		this.condition=old.getCondition();
	}
	
	//getter
	public String getAction(){return this.action;}
	public String getCondition(){return this.condition;}
	
	//setter
	public void modifyCondition(String newCondition){
		this.condition=newCondition;
	}

	//public void evokeOperation(){ if,else if - structure   effect on User(class)
	
}

