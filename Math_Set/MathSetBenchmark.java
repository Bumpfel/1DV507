/*
 * File: MathSetBenchmark.java
 * Author: Jonas Lundberg
 * Date: 13 feb 2014
 */
package math_set;

import java.util.Iterator;

/**
 * @author jlnmsi
 *
 */
public class MathSetBenchmark {
	private static MathSetGenerator generator = new MathSetGenerator();
	private static Timer timer = new Timer();
	
	private static final int WORKLOAD = 100;  // An integer in range [1,100] deciding the work load
											  // WORKLOAD = 100 will be used in competition
	
	private static MathSet[] small, larger;

	public static void main(String[] args) {
		System.out.println("\nUsed Workload in Benchmark: "+ WORKLOAD);
		int[] totalTime = new int[4];
		
		// Benchmark Miscellaneous Operations
		totalTime[0] = benchmarkMiscOps();
		
		// Clear memory
		timer.gcMem();
		
		// Create small and large sets used 
		// in the following benchmarks
		int sizeSmall = 3*WORKLOAD;
		small = new MathSet[sizeSmall+2];
		for (int i=0;i<sizeSmall;i++)
			small[i] = generator.getRandomSet(10*WORKLOAD);  
		small[sizeSmall] = generator.getEmptySet();
		small[sizeSmall+1] = generator.getSingletonSet(7);
		
		int sizeLarge = WORKLOAD/2;
		larger = new MathSet[sizeLarge+2];
		for (int i=0;i<sizeLarge;i++)
			larger[i] = generator.getRandomSet(150*WORKLOAD);
		larger[sizeLarge] = generator.getEmptySet();
		larger[sizeLarge+1] = generator.getSingletonSet(7);
		
		// Benchmark Typical MathSet Operations
		totalTime[1] = benchmarkUnion();
		totalTime[2] = benchmarkIntersection();
		totalTime[3] = benchmarkDifference();
		
		// Present final time
		int elapsedTime = 0;
		for (int t : totalTime)
			elapsedTime += t;
		System.out.println("\nTotal time: "+ ((double)elapsedTime)/1000+" seconds");
	}
	
	private static int benchmarkMiscOps() {
		System.out.println("\n\tBenchmarking Standard Operations");
		
		/* Set Creation */
		timer.tic();
		for (int i=0;i<5000;i++) {
			MathSet ms = generator.getRangeSet(0,100*WORKLOAD);
		}
		int t1 = timer.toc();
		System.out.println("\t\tSet Creation: "+ t1);

		/* Equals */
		MathSet[] sets = new MathSet[100];
		for (int i=0;i<sets.length;i++)
			sets[i] = generator.getRandomSet(100*WORKLOAD);
		timer.tic();
		for (MathSet ms1 : sets) {
			for (MathSet ms2 : sets) {
				boolean b = ms1.equals(ms2);
			}
		}
		int t2 = timer.toc();
		System.out.println("\t\tEquals: "+ t2);
		
		/* HashCode */
		MathSet[] sets2 = new MathSet[3*WORKLOAD];
		for (int i=0;i<sets2.length;i++)
			sets2[i] = generator.getRandomSet(100*WORKLOAD);
		timer.tic();
		for (MathSet ms1 : sets2) {
			for (int i=0;i<100;i++) {  // Just to increase workload
				int h = ms1.hashCode();
			}
		}

		int t3 = timer.toc();
		System.out.println("\t\tHashCode: "+ t3);
		
		/* Iterator + Contains */
		MathSet[] sets3 = new MathSet[1000];
		for (int i=0;i<sets3.length;i++)
			sets3[i] = generator.getRandomSet(50*WORKLOAD);
		timer.tic();
		for (MathSet ms1 : sets3) {
			Iterator<Object> it = ms1.iterator();
			while (it.hasNext()) {
				Object o = it.next();
				boolean b = ms1.contains(o);
			}
		}
		int t4 = timer.toc();
		System.out.println("\t\tIterator+Contains: "+ t4);
		
		/* ToString */
		MathSet[] sets4 = new MathSet[1*WORKLOAD];
		for (int i=0;i<sets4.length;i++)
			sets4[i] = generator.getRandomSet(50*WORKLOAD);
		timer.tic();
		for (MathSet ms1 : sets4) {
			for (int i=0;i<10;i++) {  // Just to increase workload
				String s = ms1.toString();
			}
		}
		int t5 = timer.toc();
		System.out.println("\t\tToString: "+ t5);

		int total = t1+t2+t3+t4+t5;
		System.out.println("\tStandard Operations total: "+ total+"ms, Memory: "+timer.currentMem());
		return total;
	}
	
	private static int benchmarkUnion() {
		System.out.println("\n\tBenchmarking Union");
		
		// Many small sets		
		timer.tic();
		for (MathSet ms1 : small) {
			for (MathSet ms2 : small) {
				MathSet ms = ms1.union(ms2);
				Iterator<Object> it = ms.iterator();   // Iterator to prevent lazy evaluation
				while (it.hasNext()) {
					Object obj = it.next(); 
				}
					
			}
		}
		int t1 = timer.toc();
		System.out.println("\t\tMany small sets: "+ t1);
		
		// A few larger sets
		timer.tic();
		for (MathSet ms1 : larger) {
			for (MathSet ms2 : larger) {
				MathSet ms = ms1.union(ms2);
				Iterator<Object> it = ms.iterator();   // Iterator to prevent laze evaluation
				while (it.hasNext()) {
					Object obj = it.next(); 
				}
			}
		}
		int t2 = timer.toc();
		System.out.println("\t\tA few larger sets: "+ t2);
		System.out.println("\tUnion total: "+(t1+t2)+"ms, Memory: "+timer.currentMem());
		return t1+t2;
	}
	
	private static int benchmarkIntersection() {
		System.out.println("\n\tBenchmarking Intersection");
		
		// Many small sets
		timer.tic();
		for (MathSet ms1 : small) {
			for (MathSet ms2 : small) {
				MathSet ms = ms1.intersection(ms2);
				int sz = ms.size();               // Size() to prevent from Lazy Evaluation
			}
		}
		int t1 = timer.toc();
		System.out.println("\t\tMany small sets: "+ t1);
		
		// A few larger sets
		timer.tic();
		for (MathSet ms1 : larger) {
			for (MathSet ms2 : larger) {
				MathSet ms = ms1.intersection(ms2);
				int sz = ms.size();
			}
		}
		int t2 = timer.toc();
		System.out.println("\t\tA few larger sets: "+ t2);
		System.out.println("\tIntersection total: "+(t1+t2)+"ms, Memory: "+timer.currentMem());
		return t1+t2;
	}
	
	private static int benchmarkDifference() {
		System.out.println("\n\tBenchmarking Difference");
		
		// Many small sets
		timer.tic();
		for (MathSet ms1 : small) {
			for (MathSet ms2 : small) {
				MathSet ms = ms1.difference(ms2);
				boolean b = ms.isEmpty();               // isEmpty() to prevent from Lazy Evaluation
			}
		}
		int t1 = timer.toc();
		System.out.println("\t\tMany small sets: "+ t1);
		
		// A few larger sets
		timer.tic();
		for (MathSet ms1 : larger) {
			for (MathSet ms2 : larger) {
				MathSet ms = ms1.difference(ms2);
				boolean b = ms.isEmpty();               // isEmpty() to prevent from Lazy Evaluation
			}
		}
		int t2 = timer.toc();
		System.out.println("\t\tA few larger sets: "+ t2);
		System.out.println("\tDifference total: "+(t1+t2)+"ms, Memory: "+timer.currentMem());
		return t1+t2;
	}

}
