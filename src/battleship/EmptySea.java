/**
 * # John Remensperger
 * Penn ID: 34687492
 * I worked alone on this project and coded from scratch.
 */

package battleship;

/*
 * Describes a part of the ocean that doesn't have a ship in it. 
 */
public class EmptySea extends Ship {

	EmptySea(){
		this.length = 1;
		this.hit = new boolean[]{false, false, false, false};// initialize the "hit array" 
		}
	
    @Override
    boolean shootAt(int row, int column) {
    		this.hit[0] = true;
    		return false;
    		// This method overrides shootAt(int row, int column) that is inherited from Ship, and always returns false to indicate that nothing was hit.
    }
    
    @Override
    boolean isSunk() {
    		return false;
    		// This method overrides isSunk() that is inherited from Ship, and always returns false to indicate that you didn't sink anything.

    }
    @Override
    public String toString() {
    	
    		return "-";
    		// Returns a single-character String "-" to use in the Ocean's print method 
    }

	@Override // added by Eclipse
	int getLength() {
		return 1;
	}

	@Override // added by Eclipse
	String getShipType() {
		return "EmptySea";
	}
	
}

