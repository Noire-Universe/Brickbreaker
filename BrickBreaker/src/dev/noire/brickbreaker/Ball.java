package dev.noire.brickbreaker;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Ball {

	private double x, y, dx, dy;
	private int ballSize;
	
	public Ball() {
		ballSize = 20;
		x = 400;
		y = 400;
		dx = 1;
		dy = 5;
	}
	
	public void update() {
		x += dx;
		y += dy;
		
		dx = (x<0||x>BBMain.WIDTH-ballSize)? -dx : dx;
		dy = (y<0||y>BBMain.HEIGHT-ballSize)? -dy : dy;
	}
	
	public void draw(Graphics2D g) {
		g.setColor(Color.BLACK);
		g.setStroke(new BasicStroke(2));
		g.drawOval((int)x, (int)y, ballSize, ballSize);
	}
	
	public Rectangle getRect() {return new Rectangle((int)x, (int)y, ballSize, ballSize);}
	public boolean isThereALoser() {
		boolean loser = (y > ((BBMain.HEIGHT-100)+(ballSize*2)))? true : false;
		return loser;
	}

	//GETTERS & SETTERS:
	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getDx() {
		return dx;
	}

	public void setDx(double dx) {
		this.dx = dx;
	}

	public double getDy() {
		return dy;
	}

	public void setDy(double dy) {
		this.dy = dy;
	}

	public int getBallSize() {
		return ballSize;
	}

	public void setBallSize(int ballSize) {
		this.ballSize = ballSize;
	}
}
