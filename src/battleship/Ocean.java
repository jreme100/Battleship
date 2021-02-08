/**
 * # John Remensperger
 * Penn ID: 34687492
 * I worked alone on this project and coded from scratch.
 */

package battleship;

import java.util.*;

/**
 * This class is for the "Ocean" object which acts as the playing field for the Battleship game.
 * It contains game-wide instance variables for the 10x10 playing field, the # of shots fired, and 
 * the total number of times the user has hit a ship.  
 *
 * @author John Remensperger
 *
 */
public class Ocean {

	private Ship[][] ships = new Ship[10][10]; // Creates an array of 100 "ships" (or references to ship types) Used to quickly determine which ship is in any given location
	private int shotsFired; // the total number of shots fired by the user
	private int hitCount; // the number of times a shot hit a ship (any ship) - hitting the same spot is useless but still counts 
	
	/*
	 * Constructor creates the 10x10 board of "ships" and loops through all 100
	 * spaces to set them as the "EmptySea" ship type. 
	 * 
	 */
	Ocean() {
		for (int i = 0; i < ships.length; i++) {
		    for (int j = 0; j < ships[i].length; j++) {
		        ships[i][j] = new EmptySea();
		    }
		}
	}
	
	/*
	 * Initializes all ten ships for the game and calls methods to place them on the board randomly. 
	 */
	void placeAllShipsRandomly() {
		
		Ship[] battleship = new Battleship[] {new Battleship()}; // initializes one battleship and calls the constructor;
		Ship[] cruiser = new Cruiser[] {new Cruiser(), new Cruiser()}; // initializes two cruisers
		Ship[] destroyer = new Destroyer[] {new Destroyer(), new Destroyer(), new Destroyer()}; // initializes three destroyers
		Ship[] submarine = new Submarine[] {new Submarine(), new Submarine(), new Submarine(), new Submarine()}; // initializes four submarines 
		
		placeShipArrayRandomly(battleship);
		placeShipArrayRandomly(cruiser);
		placeShipArrayRandomly(destroyer);
		placeShipArrayRandomly(submarine);

	}
	
	/*
	 * Takes an array of ships of a same type (battleship, destroyer, cruiser, or submarine) and calls 
	 * a method to set them individually on the board. 
	 */
	private void placeShipArrayRandomly(Ship[] shipArray) {

		for (int i = 0; i <= shipArray.length-1; i++) {
				placeShipRandomly(shipArray[i]);
		
		}

	}
	
	/*
	 * Places a ship randomly and completely on the board and calls methods from the 
	 * Ship class to make sure it is not being placed on top of or adjacent to another ship. 
	 */
	private void placeShipRandomly(Ship singleShip) {
		boolean placed = false;
		/*
		 * The while loop makes it continue to try to place the ship after a randomly
		 * generated bow location does not result in legal ship placement. 
		 */
		while (placed == false) {
			int shiprow = randomPosition();
			int shipcolumn = randomPosition();
			boolean shiphorizontal = randomHorizontal();
			if (singleShip.okToPlaceShipAt(shiprow, shipcolumn, shiphorizontal, this)) {
				singleShip.placeShipAt(shiprow, shipcolumn, shiphorizontal, this);
				placed = true;
			}
		}

	}
	
	/*
	 * Randomly generates an integer 0-9 to be used for picking a ship's position on either the horizontal or the vertical axis 
	 */
	static int randomPosition() {
		return  0 + (int)(Math.random() * 10);
	}
	
	private static boolean randomHorizontal() {
		Random randomnumber = new Random();
		boolean randomboolean = randomnumber.nextBoolean();
		return randomboolean;
	}
	
	/*
	 * Checks if a given place on the board is occupied by a ship. 
	 */
	boolean isOccupied(int row, int column) {
		if (getShipArray()[row][column].toString().equals("-")) {  
			return false;
		}
		else {
			return true;
		}
	}

	/*
	 * Performs actions when a user inputs a specific location to be shot at. Within the 
	 * Ocean class, it checks if the space is occupied and unsunk and lets the user no if they got a hit. 
	 * It also updates the shots fired and hit count appropriately as well as calling the shootAt method to 
	 * update the individual ship appropriately. Allows repeat hits if a user fires repeatedly at the same 
	 * spot on an unsunk ship.
	 */
	boolean shootAt(int row, int column) {
		this.shotsFired++;
			//if there is a ship there 
			if (isOccupied(row,column)) {
				// and it is not sunk
				if (!this.getShipArray()[row][column].isSunk()) {
					this.hitCount++; //update the hit count 
					System.out.println(".");
					System.out.println(".");
					System.out.println(".");
					System.out.println(".");
					System.out.println("HIT!!");
					System.out.println("");
					System.out.println("");
					
					// performs the below actions if the above hit sinks the ship
					this.getShipArray()[row][column].shootAt(row, column);
					if (this.getShipArray()[row][column].isSunk()) {
						System.out.println("BOOOOM!");
						System.out.println("You just sunk a " + getShipArray()[row][column].getShipType() +"!");
						System.out.println("");
						System.out.println("");


					}
					return true;
				}
				else {
					System.out.println("You've already sunk that ship!");
					return false;
				}
			}
		this.getShipArray()[row][column].shootAt(row, column);
		System.out.println("Splash! You missed!");
		return false;
	}
		
	int getShotsFired() {
		return this.shotsFired;
		// 	Returns the number of shots fired (in this game).
	}
	
	int getHitCount() {
		return this.hitCount;
		// Returns the number of hits recorded (in this game). All hits are counted, 
		// not just the first time, a given square is hit. 
	}

	boolean isGameOver() {
		/*
		 * Checks if any unsunk ships remain in the Ocean and returns false if so. 
		 */
		for (int i = 0; i < ships.length; i++) {
			for (int j = 0; j < ships[i].length; j++) {
				if (!(ships[i][j].isSunk() || ships[i][j] instanceof EmptySea)) {
					return false;
				}
	       
			}
		}
		return true;

		// 	Returns true if all ships have been sunk, otherwise false.
	}

	Ship[][] getShipArray() {
		return ships; 
		/* 	Returns the actual 10x10 array of ships, not a copy. 
		 */

	}

	/*
	 * Prints the ocean. Row numbers (0-9) are displayed along the left 
	 * edge of the array, and column numbers (0-9) are displayed along the top. 
	 * 'S' indicates a location that the user has fired upon and hit a (real) ship, '-' 
	 * indicates a location that has been fired upon with nothing there, 'x' 
	 * indicates a sunken ship, and '.' indicates a location that 
	 * has yet to be fired upon. 
	 */
	void print() {
		System.out.print("  ");
		// prints a row of column headers
		for (int columnheader = 0; columnheader < 10; columnheader ++) {
			System.out.print(columnheader + " ");
		}
		
		int rowheader = 0;
		for (int row = 0; row < ships.length; row++) {
			System.out.println("");
			System.out.print(rowheader + " "); // prints leading rowheaders on each row
			rowheader = rowheader + 1;
			
		    for (int column = 0; column < ships[row].length; column++) {	
		    		// if a shot has been taken and revealed an empty sea
		    		if (ships[row][column] instanceof EmptySea && ships[row][column].hit[0]) {
		    			System.out.print(ships[row][column].toString() + " ");
		    		}
		    		
		    		// if a shot has been taken at a boat that isn't a submarine
		    		else if (!(ships[row][column] instanceof EmptySea && !(ships[row][column] instanceof Submarine))) {
		    			// get the hit array 
		    			boolean[] hitarray = ships[row][column].hit.clone(); 
		    				
		    				if (ships[row][column].isHorizontal()) {
		    					if (hitarray[column - ships[row][column].getBowColumn()]) { // checks the appropriate spot in the hit array for the given column 
		    						System.out.print(ships[row][column].toString() + " "); // prints either S or x depending if its been sunk
		    					}
		    					else {
		    						System.out.print(". "); // prints a period when a ship is there but hasn't been shot at at that location. 
		    					}
		    				}
		    				else {
		    					if (hitarray[row - ships[row][column].getBowRow()]) { // checks the appropriate spot in the hit array for a given row 
		    						System.out.print(ships[row][column].toString() + " "); // prints either S or x depending if its been sunk
		    					}
		    					else {
		    						System.out.print(". "); // prints a period when a ship is there but hasn't been shot at at that location.
		    					}
		    				}
		    			
		    		}
		    		
		    		else {
		    			System.out.print("."+" "); // prints periods in EmptySeas that haven't been shot at yet. 
		    		}
	
		    }

		}		    
		System.out.println("");
		
	}
}

