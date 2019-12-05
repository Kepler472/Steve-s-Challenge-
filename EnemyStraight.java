import javafx.scene.image.Image;


public class EnemyStraight extends Creature {
    private Image image;
    private int [] position = getPosition();
    private String direction;

    public EnemyStraight(int [] position,char[][] map,Image image, String direction){
        super(position, map);
        this.position = getPosition();
        this.direction = direction;
        this.image=image;

    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getDirection() {
        return direction;
    }


    public void move(int[] position, char[][] map){
        int i = 0;
        boolean[] adjancentWalls = getAdjancentWalls();

        while(adjancentWalls[i]){


            if(direction.equals("DOWN")){
                setY(y+1);
            } else if(direction.equals( "UP")) {
                setY(y-1);
            } else if(direction.equals( "LEFT")){
                setX(x-1);
            } else if(direction.equals( "RIGHT")){
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
    public void draw() {

    }
}
