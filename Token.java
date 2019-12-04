
public class Token extends Entity{
	private int tokenNumber;
	
	public Token(int xPos, int yPos){
		this.xPos = xPos;
		this.yPos = yPos;
	}
	
	public int getTokenNumber(){
		return tokenNumber;
	}
}
