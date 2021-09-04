package dev.noire.brickbreaker;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JPanel;

public class GamePanel extends JPanel {

	//fields
	private boolean running;
	
	private MyMouseMotionListener theMouseListener;
	private int xMouse;
	
	private BufferedImage image;
	private Graphics2D g;
	
	//entities
	private Map theMap;
	private HUD theHud;
	private Ball theBall;
	private Paddle thePaddle;
	
	private ArrayList<PowerUp> powerUps;
	
	//constructor
	public GamePanel() {
		//entities
		theMap = new Map(6, 10);
		theHud = new HUD();
		theBall = new Ball();
		thePaddle = new Paddle();
		
		powerUps = new ArrayList<PowerUp>();
		
		//fields
		running = true;
		
		theMouseListener = new MyMouseMotionListener();
		this.addMouseMotionListener(theMouseListener);
		xMouse = 0;
		
		image = new BufferedImage(BBMain.WIDTH, BBMain.HEIGHT, BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D)image.getGraphics();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
	}
	
	//game loop
	private void checkCollision() {
		Rectangle ballRect = theBall.getRect();
		Rectangle paddleREct = thePaddle.getRect();
		
		//DEALING WITH POWERUPS:
		for(int i=0; i<powerUps.size(); i++) {
			if(powerUps.get(i).getType() == PowerUp.WIDEPADDLE) {
				if(powerUps.get(i).getRect().intersects(paddleREct) && !powerUps.get(i).getWasUsed()) {
					powerUps.get(i).setWasUsed(true);
					thePaddle.setWidth(thePaddle.getWidth()*2);
				}
			}
		}
		
		//BALL VS PADDLE:
		if(ballRect.intersects(paddleREct)) {
			theBall.setY(Paddle.YPOS-theBall.getBallSize());
			theBall.setDy(-theBall.getDy());
			if(theBall.getX() +(theBall.getBallSize()/2) < xMouse+(thePaddle.getWidth()/4))
				theBall.setDx(theBall.getDx()-.5);
			if(theBall.getX() +(theBall.getBallSize()/2) > xMouse+((thePaddle.getWidth()/4)*3))
				theBall.setDx(theBall.getDx() +.5);
		}
		
		//BALL VS BRICKS:
		A: for(int row=0; row<theMap.getMapArray().length; row++) {
			for(int col=0; col<theMap.getMapArray()[0].length; col++) {
				if(theMap.getMapArray()[row][col] > 0) {
					
					int brickx = col*theMap.getBrickWidth()+Map.HOR_PAD;
					int bricky = row*theMap.getBrickHeight()+Map.VERT_PAD;
					int brickWidth = theMap.getBrickWidth();
					int brickHeight = theMap.getBrickHeight();
					
					Rectangle brickRect = new Rectangle(brickx, bricky, brickWidth, brickHeight);
					
					if(ballRect.intersects(brickRect)) {
						
						if(theMap.getMapArray()[row][col] > 4) {
							powerUps.add(new PowerUp(brickx, bricky, theMap.getMapArray()[row][col], brickWidth, brickHeight));
							theMap.getMapArray()[row][col] = 0;
						}
						
						theMap.hitBrick(row, col);
						theBall.setDy(-theBall.getDy());
						theHud.addToScore(5);
						break A;
					}
					
				}
			}
		}
		
	}
	
	public void update() {
		checkCollision();
		theBall.update();
		thePaddle.update();
		
		for(PowerUp pu : powerUps)
			pu.update();
		
	}
	
	public void draw() {
		g.setColor(Color.DARK_GRAY);
		g.fillRect(0, 0, BBMain.WIDTH, BBMain.HEIGHT);
		theHud.draw(g);
		theBall.draw(g);
		thePaddle.draw(g);
		theMap.draw(g);
		
		for(PowerUp pu : powerUps)
			pu.draw(g);
		
		if(theMap.isThereAWinner()) {
			drawWinner();
			running = false;
		}
		
		/*if(theBall.isThereALoser()) {
			drawLoser();
			running = false;
		}*/
	}
	
	public void paintComponent(Graphics g) {
		int xPos = 0;
		int yPos = 0;
		
		Graphics2D g2 = (Graphics2D)g;
		g2.drawImage(image, xPos, yPos, BBMain.WIDTH, BBMain.HEIGHT, null);
		g2.dispose();
	}
	
	public void playGame() {
		while(running) {
			update();
			draw();
			repaint();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	//misc
	private class MyMouseMotionListener implements MouseMotionListener{
		public void mouseDragged(MouseEvent e) {}
		public void mouseMoved(MouseEvent e) {
			thePaddle.mouseMoved(e.getX() -(thePaddle.getWidth()/2));
			xMouse = e.getX() -(thePaddle.getWidth()/2);
		}
	}
	
	public void drawWinner() {
		g.setColor(Color.GREEN);
		g.setFont(new Font("Comic Sans MS", Font.PLAIN, 150));
		g.drawString("WINNER!!!", 150, 300);
	}
	
	public void drawLoser() {
		g.setColor(Color.BLACK);
		g.setFont(new Font("Comic Sans MS", Font.PLAIN, 150));
		g.drawString("LOSER!!!!!", 150, 300);
	}
	
}
