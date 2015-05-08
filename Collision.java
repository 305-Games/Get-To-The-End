package game;

import java.util.ArrayList;

import game.Main;
import game.Robot;
import game.Asteroid;

public class Collision {
	
private static ArrayList<Asteroid> a = Main.getA();
private int enemyX /*= a(1).getEnemyX()*/, enemyY /*= Enemies.getEnemyY()*/;
private int robotX /*= Robot.getCenterX()*/, robotY /*= Robot.getCenterY()*/;
private int EcenterX = enemyX + 29, EcenterY = enemyY + 31;
private int RcenterX = robotX + 58, RcenterY = robotY + 58;
private double distance;
private final int RADIUSSUM = 75;

	public Collision() {
		
	}
	
	public boolean check(int i){
		enemyX = a.get(i).getEnemyX()-20; enemyY = a.get(i).getEnemyY();
		robotX = Robot.getCenterX(); robotY = Robot.getCenterY();
		EcenterX = enemyX + 29; EcenterY = enemyY + 31;
		RcenterX = robotX + 58; RcenterY = robotY + 58;

		distance = Math.sqrt(Math.pow((EcenterX-RcenterX),2)+Math.pow((EcenterY-RcenterY), 2));
		System.out.println(distance);
		System.out.println("Enemy: ("+enemyX+","+enemyY+")");
		System.out.println("Robot: ("+robotX+","+robotY+")");
		boolean collision = false;
		if(distance <= RADIUSSUM){
			collision = true;
			System.out.println("Collision!");
		}
		return collision;
	}

	public int getEnemyX() {
		return enemyX;
	}

	public int getEnemyY() {
		return enemyY;
	}

	public int getRobotX() {
		return robotX;
	}

	public int getRobotY() {
		return robotY;
	}
	
}
