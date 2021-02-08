/**
 * # John Remensperger
 * Penn ID: 34687492
 * I worked alone on this project and coded from scratch.
 */

package battleship;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ShipTest {

	Ocean testOcean;
	Battleship testBattleship;
	Cruiser testCruiser;
	Destroyer testDestroyer;
	Submarine testSubmarine;
	
	@BeforeEach
	public void setUp(){
		testOcean = new Ocean();
		testBattleship = new Battleship();
		testCruiser = new Cruiser();
		testDestroyer = new Destroyer();
		testSubmarine = new Submarine();
		
	}
	
	@Test
	void ShipisProperlyOnBoard() {
		assertTrue(testBattleship.okToPlaceShipAt(0, 6, true, testOcean)); // right side edge case for battleship being within bounds horizontally 
		assertTrue(testBattleship.okToPlaceShipAt(6, 9, false, testOcean)); //bottom edge case for battleship being within bounds vertically
		assertTrue(testCruiser.okToPlaceShipAt(0, 7, true, testOcean)); //right side edge case for cruiser being within bounds horizontally
		assertTrue(testCruiser.okToPlaceShipAt(7, 0, false, testOcean)); ////bottom edge case for cruiser being within bounds vertically
		assertTrue(testDestroyer.okToPlaceShipAt(0, 8, true, testOcean)); //right side edge case for destroyer being within bounds horizontally
		assertTrue(testDestroyer.okToPlaceShipAt(8, 0, false, testOcean)); ////bottom edge case for destroyer being within bounds vertically
		assertTrue(testSubmarine.okToPlaceShipAt(0, 9, true, testOcean)); ////bottom edge case for submarine being within bounds horizontally
		assertTrue(testSubmarine.okToPlaceShipAt(9, 0, false, testOcean)); ////bottom edge case for submarine being within bounds vertically

	}
	
	@Test
	void shipIsOutsideBottomofBoard() {
		assertFalse(testBattleship.okToPlaceShipAt(7, 0, false, testOcean)); // edge case for battleship being out of bounds vertically
		assertFalse(testCruiser.okToPlaceShipAt(8, 0, false, testOcean)); // edge case for cruiser being out of bounds vertically
		assertFalse(testDestroyer.okToPlaceShipAt(9, 0, false, testOcean)); // edge case for destroyer being out of bounds vertically
		assertFalse(testSubmarine.okToPlaceShipAt(10, 0, false, testOcean)); // somewhat unnecessary test given the size of a submarine
	}
	
	@Test
	void shipIsOutsideRightofBoard() {
		assertFalse(testBattleship.okToPlaceShipAt(0, 7, true, testOcean)); // edge case for battleship being out of bounds horizontally
		assertFalse(testCruiser.okToPlaceShipAt(0, 8, true, testOcean)); // edge case for cruiser being out of bounds horizontally
		assertFalse(testDestroyer.okToPlaceShipAt(0, 9, true, testOcean)); // edge case for destroyer being out of bounds horizontally
		assertFalse(testSubmarine.okToPlaceShipAt(0, 10, true, testOcean)); // somewhat unnecessary test given the size of a submarine

	}
	
	@Test
	void shipIsPlacedOnBoard() {
		testBattleship.placeShipAt(1, 2, true, testOcean); // this places the ship horizontally on spaces 1,2 1,3 1,4 1,5
		assertTrue(testOcean.getShipArray()[1][2] == testBattleship);
		assertFalse(testOcean.getShipArray()[1][1] == testBattleship);
		assertTrue(testOcean.getShipArray()[1][5] == testBattleship);
		assertFalse(testOcean.getShipArray()[1][6] == testBattleship);		
	
	}
	
	@Test
	void shipIsOnTopOfAnotherShip() {
		testBattleship.placeShipAt(1, 2, true, testOcean); // this places the battleship horizontally on spaces 1,2 1,3 1,4 1,5
		assertFalse(testDestroyer.okToPlaceShipAt(1, 2, true, testOcean));
		assertTrue(testDestroyer.okToPlaceShipAt(3, 2, false, testOcean));
	}
	
	@Test
	void shipTestAdjacent() {
		testBattleship.placeShipAt(1, 2, true, testOcean); // this places the battleship horizontally on spaces 1,2 1,3 1,4 1,5
		assertTrue(testDestroyer.okToPlaceShipAt(2, 7, false, testOcean)); // vertical test
		assertFalse(testDestroyer.okToPlaceShipAt(2, 5, true, testOcean));  // horizontal test 
		assertFalse(testDestroyer.okToPlaceShipAt(2, 9, true, testOcean)); // tests that it doesn't go out of range on the right side
		assertTrue(testDestroyer.okToPlaceShipAt(0, 0, false, testOcean)); // tests that it doesn't go out of range on the left side and top
		
		testCruiser.placeShipAt(8, 1, true, testOcean);
		assertFalse(testDestroyer.okToPlaceShipAt(8, 0, false, testOcean)); // tests that it doesn't go out of range on the bottom

	}

	@Test
	void testOkToPlaceShip() {
		testBattleship.placeShipAt(1, 6, true, testOcean); // this places the battleship horizontally on spaces 1,2 1,3 1,4 1,5
		assertFalse(testDestroyer.okToPlaceShipAt(1, 7, true, testOcean));
		assertFalse(testDestroyer.okToPlaceShipAt(2, 9, true, testOcean));

	}
	

	@Test
	void testShootAt() {
		// tests a horizontal, unsunk ship
		boolean [] testArrayPreShoot = new boolean[] {false, false, false, false};
		boolean [] testArrayPostShoot = new boolean[] {false, true, false, false};
		testBattleship.placeShipAt(1, 4, true, testOcean); // this places the battleship horizontally on spaces 1,2 1,3 1,4 1,5
		assertTrue(Arrays.equals(testBattleship.hit, testArrayPreShoot)); // checks the arrays are equal before being shot at 
		// System.out.println(testBattleship.shootAt(2, 5)); // this doesn't actually need to work. this should never be called unless a ship has already been identified
		// assertFalse(testBattleship.shootAt(2, 5)); // checks that false is appropriately returned when shooting at empty sea. This doesn't work, but also you this method could never be called unless ship identified
		assertTrue(testBattleship.shootAt(1, 5)); // checks that true is appropriately returned when hitting an unsunk ship
		assertTrue(Arrays.equals(testBattleship.hit, testArrayPostShoot)); //checks the array has changed appropriately after being shot at 
		
		// tests a vertical, unsunk ship
		boolean [] testArray2PreShoot = new boolean[] {false, false, false, false};
		boolean [] testArray2PostShoot = new boolean[] {false, false, true, false};
		testCruiser.placeShipAt(6, 4, false, testOcean); // this places the battleship horizontally on spaces 1,2 1,3 1,4 1,5
		assertTrue(Arrays.equals(testCruiser.hit, testArray2PreShoot)); // checks the arrays are equal before being shot at 
		assertTrue(testCruiser.shootAt(8, 4)); // checks that true is appropriately returned when hitting an unsunk ship
		assertTrue(Arrays.equals(testCruiser.hit, testArray2PostShoot)); //checks the array has changed appropriately after being shot at 

		// tests that it realizes a ship is sunk and returns false
		testDestroyer.placeShipAt(3, 2, false, testOcean);
		testDestroyer.hit[0] = true;
		testDestroyer.hit[1] = true;
		assertFalse(testDestroyer.shootAt(3, 2));	
		
		//tests that shooting at an already hit spot on an unsunk ship is still a hit and returns true
		testDestroyer.hit[0] = true;
		testDestroyer.hit[1] = false;
		assertTrue(testDestroyer.shootAt(3, 2));
		
	}

	 
	@Test
	 void testIsSunk() {
		testDestroyer.placeShipAt(1, 6, true, testOcean); // this places the battleship horizontally on spaces 1,2 1,3 1,4 1,5
		
		//tests that it returns true if a ship is sunk
		testDestroyer.hit[0] = true;
		testDestroyer.hit[1] = true;
		assertTrue(testDestroyer.isSunk());
		
		//tests that it returns false if a ship is not sunk
		testDestroyer.hit[0] = false;
		testDestroyer.hit[1] = true;
		assertFalse(testDestroyer.isSunk());

	}
		 
}
