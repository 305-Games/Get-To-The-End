package game;

import game.Background;
import game.Main;
import java.util.Random;

public class Enemies {

	private int speedX, enemyX, enemyY;
	private Background bg = Main.getBg1();
	Random r = new Random();
	
//BEHAVIORAL METHODS
	public void update(){
		
		enemyX += speedX;
		speedX = bg.getSpeedX();
		if(enemyX <= -50){
			enemyX = 800;
			enemyY = r.nextInt(400);
		}
	}

	public int getSpeedX() {
		return speedX;
	}

	public int getEnemyX() {
		return enemyX;
	}

	public int getEnemyY() {
		return enemyY;
	}

	public Background getBg() {
		return bg;
	}

	public void setCenterX(int centerX) {
		this.enemyX = centerX;
	}

	public void setCenterY(int centerY) {
		this.enemyY = centerY;
	}

}
