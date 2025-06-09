package bcc.javaJostle;

import java.util.ArrayList;
import java.awt.image.BufferedImage;

public class MyRobot extends Robot{
    //retreatRange is distance from enemy robot that you start retreating from it
    //play around with the retreatRange number against Rando enemy to find optimal value for variable
    int counter = 1;
    int prevx = 0;
    int prevy = 0;
    //rockCharge is distance where you should move towards rock
    int rockCharge = 5;
    int Lefttimer = 0;
    int Righttimer = 0;
    int Uptimer = 0;
    int Downtimer = 0;
    public MyRobot(int x, int y){
        super(x, y, 1, 1, 4, 4,"terminator", "terminator.png", "defaultProjectileImage.png");
        // Health: 3, Speed: 3, Attack Speed: 2, Projectile Strength: 2
        // Total = 10
        // Image name is "myRobotImage.png"
        BufferedImage myImage = Utilities.loadImage("terminator.png");
    }

    public void think(ArrayList<Robot> robots, ArrayList<Projectile> projectiles, Map map, ArrayList<PowerUp> powerups) {
        /* Implement your robot's logic here
         For example, you can move towards the nearest robot or shoot at a target
         to move, choose a direciton to go
         to move left - use xMovement = -1
         to move right - use xMovement = 1
         to move up - use yMovement = -1
         to move down - use yMovement = 1
         You can ONLY move in one direction at a time, if your output doesn't match the above you will get an error

         to shoot, use shootAtLocation(x, y) where x and y are the coordinates of the target
         only shoot when canAttack() is true!
        */  
        //ONLY FOR BEATING ROCK  
        for(Robot basicrobot : robots){
            if(basicrobot != this && basicrobot.getName() != "Rock"){
                if(counter == 1){
                    System.out.println("running counter is 1" + prevy + " " + this.getY());
                    if(prevy != this.getY()){
                        System.out.println("Moving up");
                        xMovement = 0;
                        yMovement = -1;
                    }
                    else{
                        System.out.println("counter becoming 2");
                        counter = 2;
                        xMovement = 1;
                        yMovement = 0;
                    }
                }
                else if(counter == 2){
                    if(prevx != this.getX()){
                        xMovement = 1;
                        yMovement = 0;
                    }
                    else{
                        counter = 3;
                        xMovement = 0;
                        yMovement = 1;
                    }
                }
                else if(counter == 3){
                    if(prevy != this.getY()){
                        xMovement = 0;
                        yMovement = 1;
                    }
                    else{
                        counter = 4;
                        xMovement = -1;
                        yMovement = 0;
                    }
                }
                else if(counter == 4){
                    if(prevx != this.getX()){
                        xMovement = -1;
                        yMovement = 0;
                    }
                    else{
                        counter = 1;
                        xMovement = 0;
                        yMovement = -1;
                    }
                }
                if(canAttack() == true){
                    shootAtLocation(basicrobot.getX() + Utilities.ROBOT_SIZE/2, basicrobot.getY() + Utilities.ROBOT_SIZE/2);
                }
                prevx = this.getX();
                prevy = this.getY();
            
                
                continue;
            }
            
            if(basicrobot.getName() == "Rock"){
                System.out.println("Counter: " + counter + " xMove: " + xMovement + " yMove: " + yMovement);
                System.out.println("prevx: " + prevx + " prevy: " + prevy + " x: " + this.getX() + " y: " + this.getY());
                if(counter == 1){
                    System.out.println("running counter is 1" + prevy + " " + this.getY());
                    if(prevy != this.getY()){
                        System.out.println("Moving up");
                        xMovement = 0;
                        yMovement = -1;
                    }
                    else{
                        System.out.println("counter becoming 2");
                        counter = 2;
                        xMovement = 1;
                        yMovement = 0;
                    }
                }
                else if(counter == 2){
                    if(prevx != this.getX()){
                        xMovement = 1;
                        yMovement = 0;
                    }
                    else{
                        counter = 3;
                        xMovement = 0;
                        yMovement = 1;
                    }
                }
                else if(counter == 3){
                    if(prevy != this.getY()){
                        xMovement = 0;
                        yMovement = 1;
                    }
                    else{
                        counter = 4;
                        xMovement = -1;
                        yMovement = 0;
                    }
                }
                else if(counter == 4){
                    if(prevx != this.getX()){
                        xMovement = -1;
                        yMovement = 0;
                    }
                    else{
                        counter = 1;
                        xMovement = 0;
                        yMovement = -1;
                    }
                }
                if(canAttack() == true){
                    shootAtLocation(basicrobot.getX() + Utilities.ROBOT_SIZE/2, basicrobot.getY() + Utilities.ROBOT_SIZE/2);
                }
                prevx = this.getX();
                prevy = this.getY();
            }
                continue;
        }
    }
       // System.out.println("Thinking...");

    private boolean canMoveRight(Map map){
        System.out.println("speed " + getSpeed() + " xpos " + getX() + " ypos " + getY());
        int rightSide = (getX() + Utilities.ROBOT_SIZE + getSpeed()) / Utilities.TILE_SIZE;
        int topSide = (getY() - Utilities.ROBOT_SIZE + getSpeed()) / Utilities.TILE_SIZE;
        int lowerSide = (getY() + Utilities.ROBOT_SIZE + getSpeed()) / Utilities.TILE_SIZE;
        System.out.println("Right Side: " + rightSide + " Top Side: " + topSide + " Lower Side: " + lowerSide);
        System.out.println("Rows: " + map.getTiles().length + " Cols: " + map.getTiles()[0].length);

        if(map.getTiles()[topSide][rightSide] == Utilities.WALL ||
        map.getTiles()[lowerSide][rightSide] == Utilities.WALL ){
            System.out.println("Cannot move right");
            return false;
        }
        else{
            return true;
        }
    }
    private boolean canMoveLeft(Map map){
        int leftSide = (getX() - Utilities.ROBOT_SIZE + getSpeed()) / Utilities.TILE_SIZE;
        int topSide = (getY() - Utilities.ROBOT_SIZE + getSpeed()) / Utilities.TILE_SIZE;
        int lowerSide = (getY() + Utilities.ROBOT_SIZE + getSpeed()) / Utilities.TILE_SIZE;
        System.out.println("Left Side: " + leftSide + " Top Side: " + topSide + " Lower Side: " + lowerSide);
        if(map.getTiles()[topSide][leftSide] == Utilities.WALL ||
        map.getTiles()[lowerSide][leftSide] == Utilities.WALL){
            System.out.println("Cannot move left");
            return false;
        }
        else{
            return true;
        }
    }
    private boolean canMoveUp(Map map){
        int topSide = (getY() - Utilities.ROBOT_SIZE + getSpeed()) / Utilities.TILE_SIZE;
        int leftSide = (getX() - Utilities.ROBOT_SIZE + getSpeed()) / Utilities.TILE_SIZE;
        int rightSide = (getX() + Utilities.ROBOT_SIZE + getSpeed()) / Utilities.TILE_SIZE;
        if(map.getTiles()[topSide][leftSide] == Utilities.WALL ||
        map.getTiles()[topSide][rightSide] == Utilities.WALL){
            System.out.println("Cannot move up");
            return false;
        }
        else{
            return true;
        }
    }
    private boolean canMoveDown(Map map){
        int lowerSide = (getY() + Utilities.ROBOT_SIZE + getSpeed()) / Utilities.TILE_SIZE;
        int leftSide = (getX() - Utilities.ROBOT_SIZE + getSpeed()) / Utilities.TILE_SIZE;
        int rightSide = (getX() + Utilities.ROBOT_SIZE + getSpeed()) / Utilities.TILE_SIZE;
        if(map.getTiles()[lowerSide][leftSide] == Utilities.WALL ||
        map.getTiles()[lowerSide][rightSide] == Utilities.WALL){
            System.out.println("Cannot move down");
            return false;
        }
        else{
            return true;
        }
    }
}