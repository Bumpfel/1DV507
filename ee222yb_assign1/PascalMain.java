package ee222yb_assign1;

import java.util.Arrays;

public class PascalMain {
	
	public static void main(String[] args) {
		System.out.println(Arrays.toString(pascalRow(6)));
	}

	public static int[] pascalRow(int n) {
		if(n > 33  || n < 0)
			throw new IllegalArgumentException("Method can only handle arguments between 0 and 33"); // int can't handle row higher than 33 (numbers turn negative. not entirely sure why) and negative rows makes no sense
		int[] row = new int[n + 1];
		
		row[0] = 1;
		if(n > 0) {
			int[] prevRow = pascalRow(n - 1);
			
			for(int i = 1; i < n ; i ++)
				row[i] = prevRow[i - 1] + prevRow[i];
			row[n] = 1;
		}
		return row;
	}

	
}