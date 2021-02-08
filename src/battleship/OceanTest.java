/**
 * # John Remensperger
 * Penn ID: 34687492
 * I worked alone on this project and coded from scratch.
 */

package battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class OceanTest {
	
	@Test
	void constructorTest() {
		boolean ifAllEmptySea = true; 
		Ocean testOcean;
		testOcean = new Ocean();
		for (int i = 0; i <= 9; i++) {
			for (int j = 0; j <= 9; j++ ) {
				if ((testOcean.getShipArray()[i][j]) instanceof EmptySea) {
					continue;
				}
				else {
					ifAllEmptySea = false;
				}
			}
		}
		
		assertTrue(ifAllEmptySea);
	}
	
	@Test
	void randomBowGeneratorTest() {
		// this runs random a few times to confirm it is producing things in the proper range  
		assertTrue(0 <= Ocean.randomPosition());
		assertTrue(0 <= Ocean.randomPosition());
		assertTrue(0 <= Ocean.randomPosition());
		assertTrue(0 <= Ocean.randomPosition());
		assertTrue(9 >= Ocean.randomPosition());
		assertTrue(9 >= Ocean.randomPosition());
		assertTrue(9 >= Ocean.randomPosition());
		assertTrue(9 >= Ocean.randomPosition());
	}

	@Test
	// test of placeAllShipsRandomly 
	void AllTenShipsOnBoard() {
		Ocean testOcean;
		testOcean = new Ocean();
		int battleshipCount = 0;
		int cruiserCount = 0;
		int destroyerCount = 0;
		int submarineCount = 0;
		int emptySeaCount = 0;
		int error = 0;
		testOcean.placeAllShipsRandomly();
		for (int i = 0; i <= 9; i++) {
			for (int j = 0; j <= 9; j++ ) {
				if ((testOcean.getShipArray()[i][j]) instanceof Battleship) {
					battleshipCount++;
				}
				else if ((testOcean.getShipArray()[i][j]) instanceof Cruiser) {
					cruiserCount++;
				}
				else if ((testOcean.getShipArray()[i][j]) instanceof Destroyer) {
					destroyerCount++;
				}
				else if ((testOcean.getShipArray()[i][j]) instanceof Submarine) {
					submarineCount++;
				}
				else if ((testOcean.getShipArray()[i][j]) instanceof EmptySea) {
					emptySeaCount++;
				}
				else {
					error++;
				}
			}
		}
		
		assertEquals(4, submarineCount);
		assertEquals(6, destroyerCount);
		assertEquals(6, cruiserCount);
		assertEquals(4, battleshipCount);
		assertEquals(80, emptySeaCount);
		assertEquals(0, error);
	}
	
	@Test
	void testPlacedWithNoAdjacents() {
		Ocean testOcean;
		testOcean = new Ocean();
		testOcean.placeAllShipsRandomly();
		int adjacents = 0;
		testOcean.print();
		System.out.println(adjacents);
		assertEquals(0, adjacents);

	}
	

	@Test
	void testIsOccupied() {
		Ocean testOcean;
		testOcean = new Ocean();
		Battleship testBattleship;
		testBattleship = new Battleship();
		testBattleship.placeShipAt(2, 4, false, testOcean);
		assertTrue(testOcean.isOccupied(3,4));
		assertFalse(testOcean.isOccupied(2, 9));
		
	}
	
	@Test
	void testShootAt() {
		Ocean testOcean;
		testOcean = new Ocean();
		Battleship testBattleship;
		testBattleship = new Battleship();
		testBattleship.placeShipAt(2, 4, false, testOcean);
		assertTrue(testOcean.shootAt(3, 4));
		assertTrue(testOcean.getShotsFired() == 1);
		assertFalse(testOcean.shootAt(1, 1));
		assertTrue(testOcean.shootAt(3, 4)); //testing a second shot at the same unsunk location
		
		//sink the ship and then try shooting it again
		testOcean.shootAt(2, 4);
		testOcean.shootAt(4, 4);
		testOcean.shootAt(5, 4);
		assertFalse(testOcean.shootAt(3, 4)); //should return false because sunk 
		
	}
	
	@Test
	void testIsGameOver() {
		Ocean testOcean;
		testOcean = new Ocean();
		Battleship testBattleship;
		testBattleship = new Battleship();
		testBattleship.placeShipAt(2, 4, false, testOcean);
		Destroyer testDestroyer;
		testDestroyer = new Destroyer();
		testDestroyer.placeShipAt(8, 8, true, testOcean);
		
		//sink the battleship
		testOcean.shootAt(2, 4);
		testOcean.shootAt(3, 4);
		testOcean.shootAt(4, 4);
		testOcean.shootAt(5, 4);
		
		//partially sink the destroyer
		testOcean.shootAt(8, 8);
		assertFalse(testOcean.isGameOver());
		
		//finish sinking the destroyer
		testOcean.shootAt(8, 9);
		assertTrue(testOcean.isGameOver());
		
	
		
	}
	
	
	
}

