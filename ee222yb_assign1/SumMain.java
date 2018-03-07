package ee222yb_assign1;

public class SumMain {
	
	public static void main(String[] args) {
		int N = 20;
		System.out.println("sumOf(" + N + ")");
		System.out.println("Expected output: " + refsumOf(N));
		System.out.println("Output: " + sumOf(N));
		
		System.out.println("\nTo answer the question asked: No, it's retarded. It's over-complicating things, making it really hard to understand.");
		System.out.println("The other method in this class is a much better way to do it.");
	}
	
	/**
	 * Public method interface
	 * @param N - Integer
	 * @return sum of integers from 1 to N
	 */
	public static int sumOf(int N) {
		if(N <= 0)
			return 0;
		return sumOfFirstHalf(N, N / 2) + sumOfSecondHalf(N, N / 2 + 1);
	}
	
	/**
	 * Helper 1
	 */
	private static int sumOfFirstHalf(final int N, int a) {
		if(a <= 1)
			return a;
		return sumOfFirstHalf(N, a - 1) + a;
	}
	
	/**
	 * Helper 2
	 */
	private static int sumOfSecondHalf(final int N, int a) {
		if(a == N)
			return N;
		return a + sumOfSecondHalf(N, a + 1);
	}
	
	/**
	 * A better method
	 */
	public static int refsumOf(int N) {
		if(N <= 0)
			return 0;
		return refsumOf(N - 1) + N;
		
	}
	
	
}