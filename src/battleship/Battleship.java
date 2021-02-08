/**
 * # John Remensperger
 * Penn ID: 34687492
 * I worked alone on this project and coded from scratch.
 */

package battleship;
/*
 * Describes a ship of length 4
 */
public class Battleship extends Ship {

	Battleship() {
		this.length = 4;
		this.hit = new boolean[]{false, false, false, false}; // initialize the "hit array" 
		
	}

	@Override
	String getShipType() {
		return "battleship";
	}

	@Override
	int getLength() {
		return 4;
	}

}
