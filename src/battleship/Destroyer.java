/**
 * # John Remensperger
 * Penn ID: 34687492
 * I worked alone on this project and coded from scratch.
 */

package battleship;
/*
 * Describes a ship of length 2
 */
public class Destroyer extends Ship {
	Destroyer() {
		this.length = 2;
		this.hit = new boolean[]{false, false, false, false}; // initialize the "hit array" 
		
	}

	@Override
	String getShipType() {
		return "destroyer";
	}

	@Override
	int getLength() {
		return 2;
	}
}
