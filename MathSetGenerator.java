/*
 * File: MathSetGenerator.java
 * Author: Jonas Lundberg
 * Date: 12 feb 2014
 */
package math_set;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

import math_set.Bumpfel.*;
//import math_set.array_based.*;




/**
 * A class that generates different types of MathSets. The elements are always 
 * of type Integer in the range [0,MAX]. Furthermore, we assume that:
 * <ol>
 * <li> The MathSet implementation is always named MySet
 * <li> MySet comes with a public default constructor MySet() that creates 
 * an empty set, and a constructor MySet(Collection<Object> col) that
 * creates a new MathSet containing the element in Collection col, and  
 * variable argument constructor MySet(Object ... elements) that can handle an 
 * arbitrary number of input data.
 * </ol>
 * 
 * 
 * @author jlnmsi
 *
 */
public class MathSetGenerator {
	private final int MAX = 20000;
	private Integer[] values = new Integer[MAX];
	
	public MathSetGenerator() {
		for (int i=0; i<values.length; i++) {
			values[i] = new Integer(i);
		}
		MySet ms = new MySet();
		System.out.println("Sets used in MathSetGenerator: "+ms.getClass().getName());
	}
	
	public MathSet getEmptySet() {
		return new MySet();
	}
	
	public MathSet getSingletonSet(int n) {
		ArrayList<Object> list = new ArrayList<Object>();
		list.add( values[n] );
		return new MySet(list);
	}
	
	public MathSet getRangeSet(int from, int to) {
		ArrayList<Object> list = new ArrayList<Object>();
		for (int i=from; i<=to;i++)
			list.add( values[i] );
		return new MySet(list);
	}
	
	public MathSet getSpecificSet(int ... numbers) {
		ArrayList<Object> list = new ArrayList<Object>();
		for (int i : numbers)
			list.add( values[i] );
		return new MySet(list);
	}
	
	// Can be used for any data type
	public MathSet getObjectSet(Object ... elements) {
		ArrayList<Object> list = new ArrayList<Object>();
		for (Object i : elements)
			list.add( i );
		return new MySet(list);
	}
	
	public MathSet getRandomSet(int size) {
		HashSet<Object> set = new HashSet<Object>();
		Random random = new Random();
		
		while (set.size() < size) {
			int n = random.nextInt(MAX);
			set.add( values[n] );
		}		
		return new MySet(set);
	}
	

	/**
	 * Demonstrating how to use the generator methods
	 */
	public static void main(String[] args) {
		MathSetGenerator generator = new MathSetGenerator();
		
		System.out.println( generator.getEmptySet() );
		System.out.println( generator.getSingletonSet(7) );
		System.out.println( generator.getRangeSet(7,14) );
		System.out.println( generator.getRandomSet(10) );
		System.out.println( generator.getSpecificSet(2,4,6,8,10,2,2,2) );
		System.out.println( generator.getObjectSet(2,"Hello",6,"World!",10,"Hello",6,2) );

	}

}
