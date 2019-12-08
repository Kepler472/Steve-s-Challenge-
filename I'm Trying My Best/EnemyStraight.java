
public class EnemyStraight extends Creature {
    private String direction;

    public EnemyStraight(int x, int y, String direction, Game game){
        super(x, y, game);
    	this.direction = direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getDirection() {
        return direction;
    }

    @Override
    public void move(){
        int i = 0;
        boolean[] adjancentWalls = getAdjancentWalls();

        while(adjancentWalls[i]){


            if(direction.equals("DOWN")){
                setY(y+1);
            } else if(direction.equals( "UP")) {
                setY(y-1);
            } else if(direction.equals( "RIGHT")){
                setX(x-1);
            } else if(direction.equals( "LEFT")){
                setX(x+1);
            }
            i++;
        }

        if(direction.equals( "DOWN")){
            setDirection("UP");
        } else if(direction.equals( "UP")){
            setDirection("DOWN");
        } else if (direction.equals( "LEFT")){
            setDirection("RIGHT");
        }else if(direction.equals( "RIGHT")){
            setDirection("LEFT");
        }
    }

}
