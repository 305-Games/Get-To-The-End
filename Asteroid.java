package game;

import java.util.Random;

public class Asteroid extends Enemies {

	private static int randomX, randomY;
	
	public Asteroid(int centerX, int centerY) {
		Random r = new Random();
		randomX = r.nextInt(1000)+500;
		randomY = r.nextInt(360);
		setCenterX(randomX);
		setCenterY(randomY);
	}
	
	public static Asteroid Add(){
		Random r = new Random();
		return new Asteroid(900, randomY = r.nextInt(360));
	}
	
}
