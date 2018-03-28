package ee222yb_assign4.time;

public class StringConcatenations {

	private static final int MEASURING_TIME = 1000, ITERATIONS = 5, ALLOWED_TIME_DISCREPANCY = 100;
	
	public static void main(String[] args) {

		// Setup
		String shortString = "a";
		String longString = new String();
		for(int i = 0; i < 80; i ++)
			longString += "a";
		
		System.out.println("Time discrepancy allowance is set to +- " + ALLOWED_TIME_DISCREPANCY + " for StringBuilder\n");
		
		runAverageStringConcat(shortString);
		runAverageStringBuilderConcat(shortString);
		runAverageStringConcat(longString);
		runAverageStringBuilderConcat(longString);
	}
	
	public static void runAverageStringConcat(String str) {
		System.out.println("String concatenation of string length: " + str.length());
		long stringLength = 0;
		for(int i = 0; i < ITERATIONS; i ++) {
			String appendString = new String();
			long timestamp = System.currentTimeMillis();
			while(timestamp + MEASURING_TIME > System.currentTimeMillis())
				appendString += str;

			System.out.println((i + 1) + ": length: " + appendString.length() + ", concats: " + (appendString.length() / str.length()));
			stringLength += appendString.length();
//			Runtime.getRuntime().gc(); // This was on during the tests in the report
		}
		long avg = stringLength / ITERATIONS;
		System.out.println("-Averages-");
		System.out.println("Length: " + avg);
		System.out.println("Number of concatenations: " + avg / str.length());
		System.out.println();
	}
	
	public static void runAverageStringBuilderConcat(String str) {
		System.out.println("StringBuilder concatenation of string length: " + str.length());
		long totalStringLength = 0;
		int totalTime = 0;
		for(int i = 0; i < ITERATIONS; i ++) {
			
			boolean done = false;
			int timeFrame = MEASURING_TIME;
			while(!done) {
				StringBuilder appendStrB = new StringBuilder(0);
				
				// Main time taken measurement
				long startTime = System.currentTimeMillis();
				while(System.currentTimeMillis() - startTime < timeFrame)
					appendStrB.append(str);
				long endTime = System.currentTimeMillis();
				int runTime = (int) (endTime - startTime);
				
				Runtime.getRuntime().gc(); // garbage collecting to make sure every iteration gets the same preconditions.
				
				// Measure toString() time
				startTime = System.currentTimeMillis();
				String builderToString = appendStrB.toString();
				endTime = System.currentTimeMillis();
				int toStringTime = (int) (endTime - startTime);
				
				Runtime.getRuntime().gc();
				
				// Calculates how the allowed time frame should be alterered (if any) for the consecutive runs
				int timeTaken = runTime + toStringTime;
				final double MOD = 0.2;
				if(timeTaken > MEASURING_TIME + ALLOWED_TIME_DISCREPANCY) { 	// took too long - decrease timeToRun
					timeFrame -= MOD * (timeTaken - MEASURING_TIME);
//					System.out.println("\tHigh. Total time: " + (runTime + toStringTime) + ". Setting timeToRun to " + timeToRun + " ms"); // DEBUG ------------------
				}
				else if(timeTaken < MEASURING_TIME - ALLOWED_TIME_DISCREPANCY) { // was too quick - increase timeToRun
					timeFrame += MOD * Math.abs(timeTaken - MEASURING_TIME);
//					System.out.println("\tLow. Total time: " + (runTime + toStringTime) + ". Setting timeToRun to " + timeToRun + " ms"); // DEBUG -------------------
				}
			else { 																// ran within allowed time frame
					done = true;
					int stringLength = builderToString.length();
					totalStringLength += stringLength;
					totalTime += runTime + toStringTime;
					System.out.println((i + 1) + ": length: " + stringLength + ", concats: " + stringLength / str.length() + ", time: " + (runTime + toStringTime) + " ms");
				}
			}
		}
		
		// Presenting averages
		long avg = totalStringLength / ITERATIONS;
		System.out.println("-Averages-");
		System.out.println("Length: " + avg);
		System.out.println("Number of concatenations: " + avg / str.length());
		System.out.println("Time: " + totalTime / ITERATIONS + " ms");
		System.out.println();
	}
	
	// Debug method
	public static void printUsedMem() {
		Runtime r = Runtime.getRuntime();
		System.out.println("Used memory " + (r.totalMemory() - r.freeMemory()) / 1024 / 1024 + " mb");
	}
}
