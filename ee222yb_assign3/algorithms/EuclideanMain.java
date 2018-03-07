package ee222yb_assign3.algorithms;

public class EuclideanMain {

	public static void main(String[] args) {
		GCD(18,12);
		GCD(42,56);
		GCD(9,28);
		System.out.println();
		GCD(-18,-12);
		GCD(5,-15);
		GCD(-5,15);
		GCD(9,0);
		GCD(0,9);
	}
	
	public static void GCD(final int M, final int N) {
		int a = Math.abs(M);
		int b = Math.abs(N);

		while(b > 0) {
			int c;
			c = a % b;
			a = b;
			b = c;
		}
		System.out.println("GCD(" + M + "," + N + ") = " + a);
	}

}
