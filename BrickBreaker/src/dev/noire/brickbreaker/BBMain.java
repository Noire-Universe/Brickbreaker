package dev.noire.brickbreaker;

import javax.swing.JFrame;

public class BBMain {

	public static final int WIDTH = 1024, HEIGHT = 768;
	
	public static void main(String[] args) {
		JFrame theFrame = new JFrame("BrickBreaker Demo");
		GamePanel thePanel = new GamePanel(); // <-game engine object
		
		theFrame.setSize(WIDTH, HEIGHT);
		theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		theFrame.setResizable(false);
		theFrame.add(thePanel); // <-- adding the game engine to the program
		theFrame.setLocationRelativeTo(null);
		theFrame.setVisible(true);
		thePanel.playGame(); // <- starting the game
		
	}
	
}
