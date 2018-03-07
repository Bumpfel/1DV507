package ee222yb_assign3.algorithms;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@TestInstance(Lifecycle.PER_CLASS)
class SortingAlgorithmsTest {
	private final int QUANTITY = 1000;
	private final int MAX_NR = 100;
	
	private int[] randIntArr = new int[QUANTITY];
	private int[] intArrCopy;
	private int[] sortedIntArrCopy;
	
	private String[] randStrArr = new String[QUANTITY];
	private String[] strArrCopy;
	private String[] sortedStrArrCopy;
	private Comparator<String> comp = new SortingAlgorithms.StringComparator();

	@BeforeAll
	void setUp() {
		Random rand = new Random();
		for(int i = 0; i < QUANTITY; i++) {
			int randInt = rand.nextInt(MAX_NR);
			randIntArr[i] = randInt;
			randStrArr[i] = "" + randInt;
		}
		intArrCopy = Arrays.copyOf(randIntArr, randIntArr.length);
		sortedIntArrCopy = Arrays.copyOf(randIntArr, randIntArr.length);
		Arrays.sort(sortedIntArrCopy);
		
		strArrCopy = Arrays.copyOf(randStrArr, randStrArr.length);
		sortedStrArrCopy = Arrays.copyOf(randStrArr, randStrArr.length);
		Arrays.sort(sortedStrArrCopy);
	}
	
	@Test
	void testInsertionSortInt() {
		intHelper(SortingAlgorithms.insertionSort(randIntArr));
	}

	@Test
	void testMergeSortInt() {
		intHelper(SortingAlgorithms.mergeSort(randIntArr));
	}
	
	// Instead of writing the same code twice
	void intHelper(int[] sortedIntArr) {
		for(int i = 0; i < QUANTITY; i ++) {
			assertEquals(sortedIntArrCopy [i], sortedIntArr[i]); // Testing that the sorting method sorts the array the same way as Arrays.sort()
			assertEquals(intArrCopy [i], randIntArr[i]); // Testing that the original hasn't changed
		}
	}
	
	
	@Test
	void testInsertionSortString() {
		strHelper(SortingAlgorithms.insertionSort(randStrArr, comp));
	}

	@Test
	void testMergeSortString() {
		strHelper(SortingAlgorithms.mergeSort(randStrArr, comp));
	}
	
	void strHelper(String[] sortedStrArr) {
		for(int i = 0; i < QUANTITY; i ++) {
			assertEquals(sortedStrArrCopy[i], sortedStrArr[i]);
			assertEquals(strArrCopy[i], randStrArr[i]); 
		}
		
	}

}
