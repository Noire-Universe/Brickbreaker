package dev.noire.brickbreaker;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class GamePanel extends JPanel {

	//fields
	private boolean running;
	
	private MyMouseMotionListener theMouseListener;
	private int xMouse;
	
	private BufferedImage image;
	private Graphics2D g;
	
	//entities
	private HUD theHud;
	private Ball theBall;
	private Paddle thePaddle;
	
	//constructor
	public GamePanel() {
		//entities
		theHud = new HUD();
		theBall = new Ball();
		thePaddle = new Paddle();
		
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
		
		//BALL VS PADDLE:
		if(ballRect.intersects(paddleREct)) {
			theBall.setY(Paddle.YPOS-theBall.getBallSize());
			theBall.setDy(-theBall.getDy());
			if(theBall.getX() +(theBall.getBallSize()/2) < xMouse+(thePaddle.getWidth()/4))
				theBall.setDx(theBall.getDx()-.5);
			if(theBall.getX() +(theBall.getBallSize()/2) > xMouse+((thePaddle.getWidth()/4)*3))
				theBall.setDx(theBall.getDx() +.5);
		}
	}
	
	public void update() {
		checkCollision();
		theBall.update();
		thePaddle.update();
		
	}
	
	public void draw() {
		g.setColor(Color.DARK_GRAY);
		g.fillRect(0, 0, BBMain.WIDTH, BBMain.HEIGHT);
		theHud.draw(g);
		theBall.draw(g);
		thePaddle.draw(g);
		
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
	
}
