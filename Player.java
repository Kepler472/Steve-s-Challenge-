import java.util.ArrayList;

public class Player extends Entity{
	private ArrayList<Object> itemList=new ArrayList<Object>();
	private boolean isAlive = true;
	private int score = 0;
	private int curentLevel=0;
	
	public Player(int xPos, int yPos){
		this.xPos = xPos;
		this.yPos = yPos;
		this.itemList = itemList;
		this.isAlive = isAlive;
		this.score = score;
		this.curentLevel = curentLevel;
	}
	
	public ArrayList<Object> getInv(){
		return(itemList);
	}
	
	public void addInv(Object item){
		itemList.add(item);
	}
	
	public void removeInvItem(Object item){
		itemList.remove(item);
	}
	
	public void removeAllInv(){
		itemList.clear();
	}
	
	public void rendering(){
		
	}
	
	public void changeWorld(){
		
	}
	public void getInput(){
		
	}
}
