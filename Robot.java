package game;

import java.awt.Graphics;

public class Robot {

    // Constants are Here
    final int JUMPSPEED = -15;
    final int MOVESPEED = 5;
    final static int GROUND = 350;
   
    private static int robotX = 100;
    private static int robotY = GROUND;
    private boolean jumped = false;
    private boolean movingDown = false;
    private boolean movingUp = false;

        private static Background bg1 = Main.getBg1();                
        private static Background bg2 = Main.getBg2();

    private int speedX = 0;
    private int speedY = 0;

    public void update() {

        // Moves Character 
        robotY += speedY;
        
        
        //Background scrolling
        System.out.println("MOVESPEED: "+MOVESPEED);
        bg1.setSpeedX(-MOVESPEED);
        bg2.setSpeedX(-MOVESPEED);

        // Updates Y Position
        if (robotY + speedY >= GROUND) {
        	robotY = GROUND;
        }
        if (robotY + speedY <= 0){
        	robotY = 0;
        }

        // Handles Jumping
        /*if (jumped == true) {
            speedY += 1;

            if (robotY + speedY >= GROUND) {
            	robotY = GROUND;
                speedY = 0;
                jumped = false;
            }

        }*/

        // Prevents going beyond X coordinate of 0
        if (robotX + speedX <= 60) {
        	robotX = 61;
        }
    }

    public void moveDown() {
            speedY = MOVESPEED;       
    }

    public void moveUp() {
            speedY = -MOVESPEED;
    }

    public void stopDown() {
        setMovingDown(false);
        stop();
    }

    public void stopUp() {
        setMovingUp(false);
        stop();
    }

    public void stop() {
        if (isMovingDown() == false && isMovingUp() == false) {
            speedY = 0;
            System.out.println("here");
        }

        if (isMovingDown() == false && isMovingUp() == true) {
            moveUp();
        }

        if (isMovingDown() == true && isMovingUp() == false) {
            moveDown();
        }

    }

    public static int getCenterX() {
        return robotX;
    }

    public static int getCenterY() {
        return robotY;
    }

    public int getSpeedX() {
        return speedX;
    }

    public int getSpeedY() {
        return speedY;
    }

    public void setCenterX(int centerX) {
        this.robotX = centerX;
    }

    public void setCenterY(int centerY) {
        this.robotY = centerY;
    }

    public void setSpeedX(int speedX) {
        this.speedX = speedX;
    }

    public void setSpeedY(int speedY) {
        this.speedY = speedY;
    }

    public boolean isMovingDown() {
        return movingDown;
    }

    public void setMovingDown(boolean movingDown) {
        this.movingDown = movingDown;
    }

    public boolean isMovingUp() {
        return movingUp;
    }

    public void setMovingUp(boolean movingUp) {
        this.movingUp = movingUp;
    }
    
}
