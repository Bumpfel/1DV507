package ee222yb_assign4.time;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class SortingTime {

	private static final int MEASURING_TIME = 1000, ALLOWED_TIME_DISCREPANCY = 50, INIT_SIZE = 1000;
	private enum Method { INSERTION_SORT, MERGE_SORT };
	
	public static void main(String[] bla) {
		
		System.out.println("Time discrepancy allowance is set to +- " + ALLOWED_TIME_DISCREPANCY);
		
		System.out.println("\nStarting insertionSort int array");
		runIntegerSort(Method.INSERTION_SORT);
		
		System.out.println("\nStarting mergeSort int array");
		runIntegerSort(Method.MERGE_SORT);
		
		System.out.println("\nStarting insertionSort String array");
		runStringSort(Method.INSERTION_SORT);
		
		System.out.println("\nStarting mergeSort String array");
		runStringSort(Method.MERGE_SORT);
	}
	
	private static int[] randIntArray(final int SIZE) {
		int[] arr = new int[SIZE];
		int range = SIZE * 2;
		Arrays.fill(arr, new Random().nextInt(range));
		return arr;
	}
	
	private static String[] randStrArray(final int SIZE) {
		Random rand = new Random();
		String[] arr = new String[SIZE];
		
		// standard ASCII range
		int start = 32;
		int range = 127;
		
		for(int i = 0; i < SIZE; i ++) {
			String str = new String();
			for(int j = 0; j < 10; j ++) {
				str += (char) (rand.nextInt(range - start) + start);
			}
			arr[i] = str;
		}
		return arr;
	}
	
	public static void runIntegerSort(Method method) {
		int[] arr = randIntArray(INIT_SIZE);
		long timeTaken = 0;
		boolean done = false;
		while(!done) {
			long startTime = System.currentTimeMillis();
			int mod = 1;
			if(method == Method.INSERTION_SORT) {
				insertionSort(arr);
				mod = 30;
			}
			else if(method == Method.MERGE_SORT) {
				mergeSort(arr);
				mod = 3000;
			}
			long endTime = System.currentTimeMillis();
			timeTaken = endTime - startTime;
			System.out.print(arr.length + " took " + timeTaken + " ms. "); // PRINT ========================================================
			
			if(timeTaken < MEASURING_TIME - ALLOWED_TIME_DISCREPANCY) {  // did it too fast - Increase array size
					int calc = (int) ((MEASURING_TIME - timeTaken) * mod);
					arr = randIntArray(arr.length + calc);
					System.out.println("Increasing by " + calc); // PRINT ==================================================================
			}
			else if(timeTaken > MEASURING_TIME + ALLOWED_TIME_DISCREPANCY) { // took too long - Decrease array size
					int calc = (int) ((timeTaken - MEASURING_TIME) * (mod / 2));
					arr = randIntArray(arr.length - calc);
					System.out.println("Decreasing by " + calc); // PRINT ==================================================================
			}
			else
				done = true;
		}
		System.out.println("Sorted " + arr.length + " objects in " + timeTaken + " ms.");
	}

	public static void runStringSort(Method method) {
		StringComparator comp = new StringComparator(); 
		String[] arr = randStrArray(INIT_SIZE);
		long timeTaken = 0;
		boolean done = false;
		while(!done) {
			long startTime = System.currentTimeMillis();
			int constant = 1;
			if(method == Method.INSERTION_SORT) {
				insertionSort(arr, comp);
				constant = 10;
			}
			else if(method == Method.MERGE_SORT) {
				mergeSort(arr, comp);
				constant = 1000;
			}
			long endTime = System.currentTimeMillis();
			timeTaken = endTime - startTime;
			System.out.print(arr.length + " took " + timeTaken + " ms. "); // PRINT ========================================================
			if(timeTaken < MEASURING_TIME - ALLOWED_TIME_DISCREPANCY) {  // did it too fast - Increase array size
					int calc = (int) ((MEASURING_TIME - timeTaken) * constant);
					arr = randStrArray(arr.length + calc);
					System.out.println("Increasing by " + calc); // PRINT ==================================================================
			}
			else if(timeTaken > MEASURING_TIME + ALLOWED_TIME_DISCREPANCY) {  // took too long - Decrease array size
					int calc = (int) ((timeTaken - MEASURING_TIME) * (constant / 2));
					arr = randStrArray(arr.length - calc);
					System.out.println("Decreasing by " + calc); // PRINT ==================================================================
			}
			else
				done = true;
		}
		System.out.println("Sorted " + arr.length + " objects in " + timeTaken + " ms.");
	}
	
		
	//--------------------//
	// Sorting algorithms //
	//--------------------//
	public static int[] insertionSort(int[] in) {
		
		int[] sorted = new int[in.length];
		int sortedSz = 0; // companion for sorted array
		sorted[0] = in[0];

		for(int i = 1; i < in.length; i ++) {
			int j = sortedSz;
			boolean placed = false;
			while(j >= 0 && !placed) { // iterates from size to 0 while element isn't placed
				
				// Places element from the in-array if it's larger than the compared element in the sorted-array
				if(in[i] > sorted[j]) {
					sorted[j + 1] = in[i];
					placed = true; // sentinel. instead of break
				}
				else {
					// moves the compared element in the sorted-array one step forward
					sorted[j + 1] = sorted[j]; 
					if(j == 0)
						sorted[j] = in[i]; // places the element since it is the smallest in the array
				}
				j --;
			}
			sortedSz ++;
		}
		return sorted;
	}

	public static String[] insertionSort(String[] in, Comparator<String> c) {
		String[] sorted = new String[in.length];
		int sortedSz = 0;
		sorted[0] = in[0];

		for(int i = 1; i < in.length; i ++) {
			
			int j = sortedSz;
			boolean placed = false;
			while(j >= 0 && !placed){
				
				if(c.compare(in[i], sorted[j]) > 0) {
					sorted[j + 1] = in[i];
					placed = true;
				}
				else {
					sorted[j + 1] = sorted[j];
					if(j == 0)
						sorted[j] = in[i];
				}
				j --;
			}
			sortedSz ++;
		}
		return sorted;
	}

	// This is largely taken from the book Big java: late objects, but modified a bit
	public static int[] mergeSort(int[] in) {
		
		//Splitting the array in two parts. first will be equal or 1 unit smaller than second
		int[] sorted = new int[in.length];
		int[] first = new int[in.length / 2];
		int[] second = new int[in.length - first.length];

		for(int i = 0; i < first.length; i ++)
			first[i] = in[i];
		for(int i = 0; i < second.length; i ++)
			second[i] = in[first.length + i];

		// Calls itself recursively if the array is at least 2 units long, making sure the array is split down to the smallest fragment before starting to sort
		if(first.length > 1)
			first = mergeSort(first);
		if(second.length > 1)
			second = mergeSort(second);

		// Sorting
		int f = 0, s = 0;
		while(f < first.length || s < second.length) {
			if(f == first.length) 				// If first array is depleted, add all elements from second
				sorted[f + s] = second[s ++];
			else if(s == second.length) 		// Vice versa
				sorted[f + s] = first[f ++];
			else if(first[f] < second[s]) 		// If element from first is smaller than element from second, add element from first and increase first compare index
				sorted[f + s] = first[f ++];
			else 								// Vice versa
				sorted[f + s] = second[s ++];
		}
		
		return sorted;
	}

	public static String[] mergeSort(String[] in, Comparator<String> c) {
		String[] sorted = new String[in.length];
		String[] first = new String[in.length / 2];
		String[] second = new String[in.length - first.length];

		// Splitting
		for(int i = 0; i < first.length; i ++)
			first[i] = in[i];
		for(int i = 0; i < second.length; i ++)
			second[i] = in[first.length + i];

		// Recursive calls
		if(first.length > 1)
			first = mergeSort(first, new StringComparator());
		if(second.length > 1)
			second = mergeSort(second, new StringComparator());
		
		// Sorting
		int f = 0, s = 0;
		while(f < first.length || s < second.length) {
			if(f == first.length)
				sorted[f + s] = second[s ++];
			else if(s == second.length)
				sorted[f + s] = first[f ++];
			else if(c.compare(first[f], second[s]) < 0)
				sorted[f + s] = first[f ++];
			else
				sorted[f + s] = second[s ++];
		}
		return sorted;
	}
	
	static class StringComparator implements Comparator<String> {
		
		// Sorts like Arrays.sort
		// -> A, B, a, b
		public int compare(String a, String b) {
			return a.compareTo(b);
		}
				
		// Alternative sort method (not used)
		// -> A, a, B, b
		public int compareAlt(String a, String b) {
			if(a.compareToIgnoreCase(b) == 0) // If same letter regardless of case...
				return a.compareTo(b); // ...sort with capitals first
			return a.compareToIgnoreCase(b); // otherwise sort by letter (a, B)
		}
	}
}
