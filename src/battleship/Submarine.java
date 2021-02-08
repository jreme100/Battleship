/**
 * # John Remensperger
 * Penn ID: 34687492
 * I worked alone on this project and coded from scratch.
 */

package battleship;

/*
 * Describes a ship of length 1
 */
public class Submarine extends Ship {
	Submarine() {
		this.length = 1;
		this.hit = new boolean[]{false, false, false, false}; // initialize the "hit array" 
		
	}

	@Override
	String getShipType() {
		return "submarine";
	}

	@Override
	int getLength() {
		return 1;
	}
}
