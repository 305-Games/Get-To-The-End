package game;

import java.applet.Applet;
import java.awt.Button;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Panel;
import java.awt.event.*;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.Timer;



public class Main extends Applet implements Runnable, KeyListener, ActionListener {

	private Robot robot;
	private Collision collision;
	private Image image, character, background, currentSprite, asteroid, explosion, title;
	private Graphics second;
	private URL base;
	private static Background bg1, bg2;
	private static ArrayList<Asteroid> a = new ArrayList<Asteroid>();
	private Timer t = new Timer(5000, this);
	private long startTime;
	private boolean run = false;
	private Button bStart;
	
    @Override
    public void init() {
        setSize(800,480);
        setBackground(Color.BLACK);
        setFocusable(true);
        Frame frame1 = (Frame)this.getParent().getParent();
        frame1.setTitle("Q-Bot Aplha");
        addKeyListener(this);
        try{
        	base = getDocumentBase();
        }catch(Exception e){
        	
        }    	
        //bStart = new Button("START!");			-------> This stuff adds a button that should cause it to go from title screen to the game.
        //this.add(bStart);
        //bStart.addActionListener(this);
         
        
        //Image Setups
        character = getImage(base, "images/ufo.png");
        currentSprite = character;
        background = getImage(base, "images/background.png");
        asteroid = getImage(base, "images/asteroid.png");
        explosion = getImage(base, "images/explosion.png");
        title = getImage(base, "images/titlescreen.png");
    }

    @Override
    public void start() {

    	bg1 = new Background(0,0);
    	bg2 = new Background(2160,0);
    	a.add(addAsteroid());
    	robot = new Robot();
    	collision = new Collision();
    	
    	
        Thread thread = new Thread(this);
        thread.start();        
    }

    @Override
    public void stop() {
       
    }

    @Override
    public void destroy() {
        
    }
  
//RUN METHOD   (Actual commands for running the game) 
    @Override
    public void run() {
    	/*while(run==false){	//-----> tried to use this for start screen. Though currently doesn't work

    	}*/
    		int j = 0;
    		boolean play = true;
    		boolean good;
    		while(play == true){
    			good = false;
    			t.start();
    			startTime = System.nanoTime();
    			while(good == false){
    				robot.update();
    				bg1.update();
    				bg2.update();
    				for(int i = 0; i<a.size(); i++){
    					a.get(i).update();
    				}
    				while(j < a.size() && good ==  false){
    					good = collision.check(j);
    					if(good == true){
    						currentSprite = explosion;
    						t.stop();
    					}
    					j++;
    				}
    				j=0;
    			//System.out.println("After collision");
    				repaint();
    				try{
    					Thread.sleep(17);
    				}catch(InterruptedException e){
    					e.printStackTrace();
    				}
    				}
    		
    			int dialogButton = JOptionPane.showConfirmDialog (null, "Play Again?","Warning",JOptionPane.YES_NO_OPTION);             
    			if(dialogButton == JOptionPane.NO_OPTION){
    				play = false;
    				System.exit(0);
    			}
    			else if(dialogButton == JOptionPane.YES_OPTION);{
    				reset();
    			}
    		}
    	
            

    }

//KEY EVENTS SECTIONS    
	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		   case KeyEvent.VK_UP:	
			   robot.moveUp();
	           robot.setMovingUp(true);
		   break;

		   case KeyEvent.VK_DOWN:
			   robot.moveDown();
			   robot.setMovingDown(true);			   
		   break;

		   case KeyEvent.VK_LEFT:			   
		   break;

		   case KeyEvent.VK_RIGHT:			   
		   break;

		   case KeyEvent.VK_SPACE:
		   break;
		   }
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		   case KeyEvent.VK_UP:
			   robot.stopUp();
		      break;
		   case KeyEvent.VK_DOWN:
			   robot.stopDown();
		      break;
		   case KeyEvent.VK_LEFT:			   
		      break;
		   case KeyEvent.VK_RIGHT:			   
		      break;
		   case KeyEvent.VK_SPACE:
		      break;

		   }
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

//GRAPHICS SECTIONS	
	@Override
	public void update(Graphics g){
		if (image == null) {
			image = createImage(this.getWidth(), this.getHeight());
			second = image.getGraphics();
		}

		second.setColor(getBackground());
		second.fillRect(0, 0, getWidth(), getHeight());
		second.setColor(getForeground());
		paint(second);
		
		g.drawImage(image, 0, 0, this);
	}
	
	@Override
	public void paint(Graphics g){	
		/*if(run == false){					//----> Tried to use this for start screen. Though it doesn't work yet.
			g.drawImage(title, 0,0,this);
		}
		else if(run == true){*/
		g.drawImage(background, bg1.getBgX(), bg1.getBgY(), this);
		g.drawImage(background, bg2.getBgX(), bg2.getBgY(), this);
		g.drawImage(currentSprite, robot.getCenterX(), robot.getCenterY(), this);
		for(int i = 0; i<a.size(); i++){
			g.drawImage(asteroid,  a.get(i).getEnemyX(), a.get(i).getEnemyY(), this);
		}
		g.drawString("Score: " + ((System.nanoTime()-startTime)/10000000), 650, 450);
		}
	//Collision check stuff
		/*g.drawOval(a2.getEnemyX()+3, a2.getEnemyY()+5,52, 52);
		g.drawOval(Robot.getCenterX()-2, Robot.getCenterY(), 120, 120);
		g.drawOval(a2.getEnemyX(), a2.getEnemyY(),2,2);
		g.drawOval(Robot.getCenterX(), Robot.getCenterY(),2,2);
		g.drawOval(a2.getEnemyX()+28, a2.getEnemyY()+30,2, 2);
		g.drawOval(Robot.getCenterX()+58, Robot.getCenterY()+60, 2, 2);
	*/
	
	
	/*}*/								//---------> Don't forget this line
	
	public static Asteroid addAsteroid(){
		return Asteroid.Add();		
	}

	public static Background getBg1() {
		return bg1;
	}

	public static Background getBg2() {
		return bg2;
	}

	public static void setBg1(Background bg1) {
		Main.bg1 = bg1;
	}

	public static void setBg2(Background bg2) {
		Main.bg2 = bg2;
	}
	
	public static ArrayList getA(){
		return a;
	}
	
	public void actionPerformed(ActionEvent e){
		/*if(e.getSource() == bStart){			//----> Method used for button press, but messes up without title screen implemented so i took out for now.
			run = true;
			System.out.println("Button");
			start();				//if i try going to the run method from here the game screen doesn't appear. However, if i go to start method the background and ufo(robot) don't update.
		}
		else */if(e.getSource() == t){
		a.add(Asteroid.Add());
		System.out.println("Add Asteroid!");
		}
	}
	
	public void reset(){
		a.clear();				
		currentSprite = character;
    	a.add(addAsteroid());
    	robot = new Robot();
    	collision = new Collision();
		bg1.reset(0, 0);
		bg2.reset(2160, 0);
	}
	
} 



/*
 * If you put in the commented code that has arrows next to it the title screen appears and sometimes works, but never completely.
 * It depends on which method is called from the button press action. If it goes to run() then it doesn't display the game. If it 
 * goes to start() then it displays the game but only updates the asteroids. I had a similar problem when i was working on the reset 
 * button. I believe it is not passing the movement data and the variable that controls how fast the objects are moving is staying at 
 * 0 so nothing happens. All the images being used are stored in the image folder so you can add new ones if u want. However, the
 * collision detection is set up for the current images so you will have to realign those parameters. 
 */
