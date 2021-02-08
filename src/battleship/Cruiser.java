/**
 * # John Remensperger
 * Penn ID: 34687492
 * I worked alone on this project and coded from scratch.
 */

package battleship;

/*
 * Describes a ship of length 3
 */
public class Cruiser extends Ship {
	Cruiser() {
		this.length = 3;
		this.hit = new boolean[]{false, false, false, false}; // initialize the "hit array" 
		
	}

	@Override
	String getShipType() {
		return "cruiser";
	}

	@Override
	int getLength() {
		return 3;
	}
}
