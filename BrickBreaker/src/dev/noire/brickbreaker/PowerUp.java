package dev.noire.brickbreaker;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class PowerUp {

	private int x, y, dy, type, width, height;
	private boolean isOnScreen, wasUsed;
	private Color color;
	
	public static final int WIDEPADDLE = 5;
	public static final int FASTBALL = 6;
	public static final Color WIDECOLOR = new Color(255, 255, 255);
	public static final Color FASTCOLOR = new Color(0, 0, 0);
	
	public PowerUp(int x, int y, int type, int width, int height) {
		this.x = x;
		this.y = y;
		this.type = type;
		this.width = width;
		this.height = height;
		dy = (int)(Math.random()*6)+2;
		
		isOnScreen = true;
		wasUsed = false;
		
		if(type < WIDEPADDLE)type = WIDEPADDLE;
		if(type > FASTBALL)type = FASTBALL;
		if(type == WIDEPADDLE)color = WIDECOLOR;
		if(type == FASTBALL)color = FASTCOLOR;
	}
	
	public void update() {
		y += dy;
		
		if(y>BBMain.HEIGHT)
			isOnScreen = false;
	}
	
	public void draw(Graphics2D g) {
		g.setColor(color);
		g.fillRect(x, y, width, height);
	}
	
	public Rectangle getRect() {return new Rectangle(x, y, width, height);}
	public int getType() {return type;}
	public boolean getWasUsed() {return wasUsed;}
	public void setWasUsed(boolean used) {wasUsed = used;}
	
}
