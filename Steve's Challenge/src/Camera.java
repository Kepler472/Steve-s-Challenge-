/**
 * Camera class that will focus on the player and follow 
 * the player around the map 
 * @author Brandon Evans 964175
 */
public class Camera {
        private float xOffset, yOffset;    
    /**
     * Stores the ampunt of tiles shifted in either Axis
     * @param xOffset
     * @param yOffset
     */
    public Camera(float xOffset, float yOffset){
        this.xOffset =xOffset;
        this.yOffset = yOffset;
    }
    /**
     * CentreCam method will force the camera to lock to the entity
     * Restricts free camera movement
     * @param e Can be any entity doesn't have to a player
     */
    public void centreCam(Entity e){
        xOffset = e.getX() - CANVAS_WIDTH() /2 + e.getWidth() /2 ; 
        yOffset = e.getY() - CANVAS_HEIGHT() /2+ e.getHeight() /2; //Have to divide by 2 to center//
        
        
    }
    /**
     * Increments offset values based on player movement
     * @param xValue
     * @param yValue
     */
    public void move(float xValue,float yValue){
        xOffset += xValue;
        yOffset += yValue;
        
        
    }

    
    //GETTERS//

    /**
     * 
     * @return returns the current xOffset value in case of event
     */
    public float getxOffset() {
        return xOffset;
    }

    /**
     *
     * @param xOffset Sets xOffset value
     */
    public void setxOffset(float xOffset) {
        this.xOffset = xOffset;
    }

    /**
     *
     * @return returns the current xOffset value in case of event
     */
    public float getyOffset() {
        return yOffset;
    }

    /**
     *
     * @param yOffset sets the yOffset value
     */
    public void setyOffset(float yOffset) {
        this.yOffset = yOffset;
    }
    
}
