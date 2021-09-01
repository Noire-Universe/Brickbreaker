package dev.noire.brickbreaker;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
	
	
	//constructor
	public GamePanel() {
		//entities
		
		
		//fields
		running = true;
		
		theMouseListener = new MyMouseMotionListener();
		xMouse = 0;
		
		image = new BufferedImage(BBMain.WIDTH, BBMain.HEIGHT, BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D)image.getGraphics();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
	}
	
	//game loop
	private void checkCollision() {
		
	}
	
	public void update() {
		checkCollision();
		
	}
	
	public void draw() {
		g.setColor(Color.DARK_GRAY);
		g.fillRect(0, 0, BBMain.WIDTH, BBMain.HEIGHT);
		
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
			
		}
	}
	
}
