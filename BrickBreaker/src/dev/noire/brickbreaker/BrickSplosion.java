package dev.noire.brickbreaker;

import java.awt.Graphics2D;
import java.util.ArrayList;

public class BrickSplosion {

	private ArrayList<BrickPiece>pieces;
	
	private Map theMap;
	private int x, y;
	
	private boolean isActive;
	private long timer;
	
	public BrickSplosion(int x, int y, Map theMap) {
		this.theMap = theMap;
		this.x = x;
		this.y = y;
		isActive = true;
		timer = System.nanoTime();
		pieces = new ArrayList<BrickPiece>();
		init();
	}
	
	private void init() {
		int r = (int)(Math.random()*50)+20;
		for(int i=0; i<r; i++)
			pieces.add(new BrickPiece(x, y, theMap));
	}
	
	public void update() {
		
		if((System.nanoTime()-timer)/1000000 > 2000 && isActive)
			isActive = false;
		
		for(BrickPiece bp : pieces)
			bp.update();
	}
	
	public void draw(Graphics2D g) {
		for(BrickPiece bp : pieces)
			bp.draw(g);
	}
	
	public boolean getIsActive() {return isActive;}
	
}
