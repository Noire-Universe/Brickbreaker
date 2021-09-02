package dev.noire.brickbreaker;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class HUD {

	private int score;
	
	public HUD() {
		score = 0;
	}
	
	public void draw(Graphics2D g) {
		g.setColor(Color.RED);
		g.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		g.drawString("Score: " + score, 20, 20);
	}
	
	public void addToScore(int token) {
		score += token;
	}
	
}
