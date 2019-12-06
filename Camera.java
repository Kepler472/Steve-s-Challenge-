/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package steve.s.challenge.master;


import exiled.mortals.tile.Tile;

/**
 *
 * @author Brandon Evans 964175
 */
public class Camera {
    // Declaring varabiles and creating new objects//
        private float xOffset, yOffset;
    
    public Camera(float xOffset, float yOffset){
        
        this.xOffset =xOffset;
        this.yOffset = yOffset;
    }
    
    // finds blank space and adjusts camera by offsetting 
    // This also sets the variables needed to allow the user to centre on the player itself
    public void checkBlankSpace(){
        if(xOffset <0){
            xOffset= 0;
        }else if(xOffset > getWorld().getWidth()* Tile.TILEWIDTH - getWidth()){
            xOffset = getWorld().getWidth()* Tile.TILEWIDTH- getWidth();
        }
        if(yOffset<0){
            yOffset=0;
        }else if(yOffset > getWorld().getHeight()* Tile.TILEHEIGHT -getHeight()){
           yOffset = getWorld().getHeight()* Tile.TILEHEIGHT- getHeight();
        }
    }
    //Center on Entity method, sets the Offset x and y variables and conducts calcualtions to direct the camera to centre on the player's character 
    public void centerOnEntity(Entity e){
        xOffset = e.getX() - getWidth() /2 + e.getWidth() /2 ; 
        yOffset = e.getY() - getHeight() /2+ e.getHeight() /2; //Have to divide by 2 to center//
        checkBlankSpace();
        
    }
    
    // This allows the camera to move itself, while checking for blank space//
    public void move( float xAmt,float yAmt){
        xOffset += xAmt;
        yOffset += yAmt;
        checkBlankSpace();
        
    }

    
    //GETTERS//
    public float getxOffset() {
        return xOffset;
    }

    public void setxOffset(float xOffset) {
        this.xOffset = xOffset;
    }

    public float getyOffset() {
        return yOffset;
    }

    public void setyOffset(float yOffset) {
        this.yOffset = yOffset;
    }
    
}
