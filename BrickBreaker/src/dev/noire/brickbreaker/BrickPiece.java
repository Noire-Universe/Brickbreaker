package dev.noire.brickbreaker;

import java.awt.Color;
import java.awt.Graphics2D;

public class BrickPiece {

	private double dx, dy, gravity;
	private int x, y, size;
	private Map theMap;
	private Color color;
	
	public BrickPiece(int x, int y, Map theMap) {
		this.theMap = theMap;
		this.x = x +(theMap.getBrickWidth()/2);
		this.y = y +(theMap.getBrickHeight()/2);
		
		dx = (Math.random()*30)-15;
		dy = (Math.random()*30)-15;
		
		gravity = 0.8;
		
		size = (int)(Math.random()*15)+2;
		
		color = new Color(200, 0, 0);
		
	}
	
	public void update() {
		x += dx;
		y += dy;
		dy += gravity;
	}
	
	public void draw(Graphics2D g) {
		g.setColor(color);
		g.fillRect(x, y, size, size);

	}
	
	
	
}
