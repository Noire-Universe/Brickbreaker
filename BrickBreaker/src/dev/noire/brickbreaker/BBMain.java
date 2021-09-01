package dev.noire.brickbreaker;

import javax.swing.JFrame;

public class BBMain {

	public static final int WIDTH = 1024, HEIGHT = 768;
	
	public static void main(String[] args) {
		JFrame theFrame = new JFrame("BrickBreaker Demo");
		
		
		theFrame.setSize(WIDTH, HEIGHT);
		theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		theFrame.setResizable(false);
		
		theFrame.setLocationRelativeTo(null);
		theFrame.setVisible(true);
		
		
	}
	
}
