package dev.noire.brickbreaker;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class Map {

	private int brickWidth, brickHeight;
	private int[][] theMap;
	
	public static final int HOR_PAD = 80, VERT_PAD = 50;
	
	public Map(int row, int col) {
		brickWidth = (BBMain.WIDTH -(2*HOR_PAD))/col;
		brickHeight = ((BBMain.HEIGHT/2)-(2*VERT_PAD))/row;
		
		initMap(row, col);
	}
	
	private void initMap(int row, int col) {
		theMap = new int[row][col];
		for(int i=0; i<theMap.length; i++) {
			for(int j=0; j<theMap[0].length; j++) {
				int r = (int)(Math.random()*4)+1;
				theMap[i][j] = r;
			}
		}
	}
	
	public void draw(Graphics2D g) {
		for(int row=0; row<theMap.length; row++) {
			for(int col=0; col<theMap[0].length; col++) {
				if(theMap[row][col] > 0) {
					
					if(theMap[row][col] == 1)
						g.setColor(new Color(200, 0, 0));
					
					if(theMap[row][col] == 2)
						g.setColor(new Color(150, 0, 0));
					
					if(theMap[row][col] == 3)
						g.setColor(new Color(100, 0, 0));
					
					if(theMap[row][col] == 4)
						g.setColor(new Color(50, 0, 0));
					
					g.fillRect(col*brickWidth+HOR_PAD, row*brickHeight+VERT_PAD, brickWidth, brickHeight);
					g.setColor(Color.DARK_GRAY);
					g.setStroke(new BasicStroke(2));
					g.drawRect(col*brickWidth+HOR_PAD, row*brickHeight+VERT_PAD, brickWidth, brickHeight);
				}
			}
		}
	}
	
	public int getBrickWidth() {return brickWidth;}
	public int getBrickHeight() {return brickHeight;}
	public int[][] getMapArray(){return theMap;}
	public void hitBrick(int row, int col) {
		theMap[row][col] -= 1;
		if(theMap[row][col] <=0)
			theMap[row][col] = 0;
	}
	public boolean isThereAWinner() {
		int remainingBrick = 0;
		for(int row=0; row<theMap.length; row++) {
			for(int col=0; col<theMap[0].length; col++) {
				remainingBrick += theMap[row][col];
			}
		}
		boolean winner = (remainingBrick==0)? true : false;
		return winner;
	}
}
