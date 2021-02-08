/**
 * # John Remensperger
 * Penn ID: 34687492
 * I worked alone on this project and coded from scratch.
 */

package battleship;

import java.util.Scanner;

/*
 * This class:
 * 1) sets up the game
 * 2) accepts shots from the user
 * 3) displays the results between each turn
 * 4) prints final scores
 * 5) ask the user if she wants to play again
 */
public class BattleshipGame {

	static void startGame(Ocean ocean) {
		ocean.placeAllShipsRandomly();
		System.out.println("Welcome to Battleship!");
		ocean.print();
	}
	
	static void printBoardAndReturnScores(Ocean ocean) {
        ocean.print(); 
        System.out.println("You have shot " + ocean.getShotsFired() + " times");
        System.out.println("You have " + ocean.getHitCount() + " hits");
	}
	
	public static void main(String[] args) {
		boolean playing = true; 
		while (playing) { // loop around entire game in case user wants to play again
			Ocean ocean = new Ocean();
			startGame(ocean);
			Scanner scanner = new Scanner(System.in);
			while (!ocean.isGameOver()) {
				System.out.println("Enter an row and column number separated by a comma with no spaces. Example: 4,7");
	            try {

	            		String input = scanner.nextLine();
	                String[] usertext = input.split(","); // splits the user entered string of two numbers separated by a comma into an array of strings.
	                int [] userints = new int[usertext.length];
	                for (int i = 0; i < userints.length; i++) {
	                		userints[i] = Integer.parseInt(usertext[i]); // converts the array of strings to an array of integers 
	                }
	                ocean.shootAt(userints[0], userints[1]); // shoots at the two coordinates provided by the user 

	            }
	            catch (Exception e) {
				System.out.println("Invalid input. Please be sure to enter a row (0-9) and a column number (0-9) separated by a comma with no spaces.");
				
	            }
	            
	            /*
	             * After each turn, prints: 
	             */
	            printBoardAndReturnScores(ocean);
				
			} // end of code loop for the single game 
			
		System.out.println("You've sunk the fleet using " + ocean.getShotsFired() + " shots!");
			
			/*
			 * Checks to see if user wants to play again.
			 */
			boolean playAgainAnswered = false;
			while (!playAgainAnswered) {
				System.out.println("Play again? (y/n)");
				String yesNo = scanner.nextLine();
				if (yesNo.equals("n") || yesNo.equals("N")) {
					playing = false;
					playAgainAnswered = true;
					System.out.println("See you next battle, Captain!");
					scanner.close();
				}
				else if (yesNo.equals("y") || yesNo.equals("Y")) {
					playing = true;
					playAgainAnswered = true;
				}
				else {
					System.out.println("Please answer using 'y' or 'n'");
				}
			}

		}

	}

}
