package ee222yb_assign4.binHeap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.NoSuchElementException;
import java.util.Random;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class BinaryIntHeapTest {
	private final static int TEST_SIZE = 10;

	private static BinaryIntHeap emptyHeap = new BinaryIntHeap();
	private static BinaryIntHeap testHeap = new BinaryIntHeap();

	private static Random rand = new Random();

	@BeforeAll
	static void setup() {
		restoreTestHeap();
	}

	static void restoreTestHeap() {
		BinaryIntHeap tmp = new BinaryIntHeap();
		for(int i = 0; i < TEST_SIZE; i ++)
			tmp.insert(rand.nextInt(TEST_SIZE));
		testHeap = tmp;
	}

	@Test
	void testIsEmpty() {
		assertTrue(emptyHeap.isEmpty());
		assertFalse(testHeap.isEmpty());
	}
	
	@Test
	void testSize() {
		restoreTestHeap();
		
		assertEquals(0, emptyHeap.size());
		assertEquals(TEST_SIZE, testHeap.size());
	}

	@Test
	void testPullHighest() {
		int initialSize = testHeap.size();
		int high = testHeap.pullHighest();
		assertEquals(initialSize - 1, testHeap.size()); // Tests that the method reduces heap size
		for(int i = 0; i < testHeap.size(); i ++) {
			int low = testHeap.pullHighest();
			assertTrue(high >= low); // Tests that the method always pulls the highest value
			high = low;
		}
		assertThrows(NoSuchElementException.class, () ->  emptyHeap.pullHighest()); // tests that an exception is thrown when attempting to run the method on an empty heap
		assertEquals(0, emptyHeap.size()); // pullHighest() shouldn't have reduced size below 0
	}
	
	@Test
	void testInsert() {
		/*
		 * Testing that the method inserts data in the correct order
		 */
		testHeap = new BinaryIntHeap(); // clears the test heap

		testHeap.insert(-1);
		testHeap.insert(6);
		testHeap.insert(-4);
		testHeap.insert(9);
		testHeap.insert(8);
				
		String expectedToString = "[9, 8, -4, -1, 6]";
	
		// The toString() method prints the array like it usually does, but skipping index 0
		assertEquals(expectedToString, testHeap.toString()); // Tests that the numbers are sorted in expected order when inserted

		int initialSize = testHeap.size();
		testHeap.insert(6);
		assertEquals(initialSize + 1, testHeap.size()); // Tests that the method increases heap size		
		
	}

}
