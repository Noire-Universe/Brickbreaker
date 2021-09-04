package dev.noire.brickbreaker;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Paddle {

	private double x, xtarget;
	private int startWidth, startHeight, width, height;
	private boolean altWidth;
	private long widthTimer;
	
	public static final int YPOS = BBMain.HEIGHT-100;
	
	public Paddle() {
		startWidth = 100;
		startHeight = 20;
		width = startWidth;
		height = startHeight;
		altWidth = false;
		widthTimer = System.nanoTime();
		x = (BBMain.WIDTH/2)-(width/2);
		xtarget = 0;
	}
	
	public void update() {
		
		if((System.nanoTime()-widthTimer)/1000000 > 9000 && altWidth) {
			altWidth = false;
			width = startWidth;
		}
		
		x +=(xtarget-x)*.3;
		int dif = (int)Math.abs(xtarget-x)/5;
		height = startHeight-dif;
		if(height < 18)
			height = 18;
		
	}
	
	public void draw(Graphics2D g) {
		int yDraw = YPOS +(startHeight-height)/2;
		
		g.setColor(Color.GRAY);
		g.fillRect((int)x, yDraw, width/4, height);
		g.fillRect((int)x+((width/4)*3), yDraw, width/4, height);
		g.setColor(Color.BLACK);
		g.fillRect((int)x+(width/4), yDraw, width/2, height);
		if(altWidth) {
			g.setColor(Color.WHITE);
			g.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
			g.drawString(" ..shrinkin in: " + (9-((System.nanoTime()-widthTimer)/1000000000)), (int)x, YPOS+18);
		}
	}
	
	public Rectangle getRect() {return new Rectangle((int)x, YPOS, width, height);}
	public int getWidth() {return width;}
	public void setWidth(int newWidth) {
		width = newWidth;
		altWidth = true;
		widthTimer = System.nanoTime();
	}
	
	public void mouseMoved(double mouseXPos) {
		xtarget = mouseXPos;
		
		if(xtarget<0)
			xtarget=0;
		if(xtarget>BBMain.WIDTH-width)
			xtarget=BBMain.WIDTH-width;
	}

	//GETTERS & SETTERS:
	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}
}
