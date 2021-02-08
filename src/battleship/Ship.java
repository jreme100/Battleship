/**
 * # John Remensperger
 * Penn ID: 34687492
 * I worked alone on this project and coded from scratch.
 */

package battleship;

/**
 * This abstract class provides instance variables, methods and getters and setters for the 
 * four types of ships (battleship, cruiser, destroyer, and submarine) as well as for 
 * areas of the board containing "Empty Sea". Empty Seas are technically a ship object 
 * though they do not represent actual ships as far as the game is concerned. 
 *
 * @author John Remensperger
 *
 */
public abstract class Ship {
	protected int bowRow; // the row (0 to 9) which contains the bow (front) of the ship.
	protected int bowColumn; // the column (0 to 9) which contains the bow (front) of the ship.
	protected int length; // the number of squares occupied by the ship. An "empty sea" location has length 1.
	protected boolean horizontal; // true if the ship occupies a single row, false otherwise. 
	boolean [] hit = new boolean[4]; // an array of booleans telling whether that part of the ship has been hit. Only battleships use all four locations; cruisers use the first three; destroyers 2; submarines 1; and "empty sea" either one or none.
	
	
	abstract int getLength(); 

	// Getters:
	int getBowRow() {
		return this.bowRow;
	}
	
	int getBowColumn() {
		return this.bowColumn; 
	}
	
	boolean isHorizontal() {
		return this.horizontal;
	}

	// Setters: 
	
	void setBowRow(int row) {
		this.bowRow = row;
	}
	
	void setBowColumn(int column) {
		this.bowColumn = column;
	}

	void setHorizontal(boolean horizontal) {
		this.horizontal = horizontal; 
	}

	// ------ 
	
	/*
	 * returns the type of ship from the subclasses 
	 */
    abstract String getShipType();
	
    /*
     * Determines whether or not a ship can be placed in the Ocean with its bow in the given
     * row and column and in the given direction. Checks if the area where the ship will go
     * is occupied as well as the area adjacent to the ship, including directly diagonal. 
     */
    boolean okToPlaceShipAt(int row, int column, boolean horizontal, Ocean ocean) {
	    int boardrange = ocean.getShipArray()[0].length; //width and height of board
    		if (horizontal) {
				if (column >= 0 && (column + this.length - 1) <= boardrange-1 && row >= 0 && row <= boardrange-1){ //checks to make sure the ship itself won't leave the board
					for (int rowcheck = row-1; rowcheck <= row+1; rowcheck++){
						for (int columncheck = column-1; columncheck <= column + this.length; columncheck++){
							if(rowcheck >= 0 && rowcheck <= boardrange-1 && columncheck >= 0 && columncheck <= boardrange-1){ // keeps the method from checking adjacent areas outside the board and going out of range 
								if(ocean.isOccupied(rowcheck, columncheck))
								{
									return false;
								}
							}
						}
					}
	
					return true;
				}
			}
			else {
				if (row >= 0 && (row + this.length - 1) <= boardrange-1 && column >= 0 && column <= boardrange-1){ //checks to make sure the ship itself won't leave the board
					for (int rowcheck = row - 1; rowcheck <= row + this.length; rowcheck++){
						for (int columncheck = column - 1; columncheck <= column + 1; columncheck++){
							if (rowcheck >= 0 && rowcheck <= boardrange-1 && columncheck >= 0 && columncheck <= boardrange-1){ // keeps the method from checking adjacent areas outside the board and going out of range
								if (ocean.isOccupied(rowcheck, columncheck))
								{
									return false;
								}
							}
	
						}
					}
					return true;
				}
			}
			return false;

	}

    /*
     * "Puts" the ship in the ocean by giving values to the bowRow, 
     * bowColumn, and horizontal instance variables in the ship, and putting 
     * a reference to the ship in each of 1 or more locations 
     * (depending on the ship length) in the ships array in the Ocean object. 
     */
    void placeShipAt(int row, int column, boolean horizontal, Ocean ocean) {
    		this.bowRow = row;
    		this.bowColumn = column;
    		this.horizontal = horizontal;
    		if (!horizontal) {
    			for (int i = row; i<=row + this.length - 1; i++) {
    				ocean.getShipArray()[i][column] = this;
    			}
    		}
    		else {
    			for (int i = column; i <=column + this.length - 1; i++) {
    				ocean.getShipArray()[row][i] = this;
    			}
    		}

    }
    
    /*
     * When a user shoots at a space where an unsunk ship is (as determined by the shootat() method in Ocean),
     * this method updates the ships hit array in the appropriate position. Returns true if the user got a hit. 
     */
    boolean shootAt(int row, int column) {
    		if (!this.isSunk()) {
			if (horizontal) {
				this.hit[column - this.bowColumn] = true; // finds the appropriate place in the ships hit array for horizontal ships
				return true;
			}
			else {
				this.hit[row - this.bowRow] = true; // finds the appropriate place in the ships hit array for vertical ships 
				return true;
			}

    		}
    		return false;

    }
    
    /*
     * Checks if a given ship is sunk by comparing the number of hits in the hit array to the 
     * length of the ship. If they are equal, the ship is sunk. 
     */
    boolean isSunk() {
    		int hitcount = 0; // variable used for counting the number of hits in the hit array
    		
    		for (int i = 0; i <= this.length-1; i++) {
    			if (this.hit[i] == true) {
    				hitcount++;
    			} 
    		}
    		if (hitcount == this.length) { //compares the counted # of hits to see if it matches the length of the ship in question
    			return true;
    		}
    		else {
    			return false; 
    		}

    }
    
    /*
     * Overrides the toString method for the print() method in the Ocean class. Returns "x" for 
     * sunk ships and "S" for ships that have been hit. 
     */
	@Override
		public String toString() {
	    		if (this.isSunk()) {
	    			return "x";
	    		}
	    		
	    		else {
	    			return "S";
	    		}
	    		
	    }

}
