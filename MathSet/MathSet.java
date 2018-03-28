/*
 * File: MathSet.java
 * Author: Jonas Lundberg
 * Date: 4 feb 2014
 */
package math_set;

import java.util.Iterator;

/**
 * @author jonasl
 *
 */
public interface MathSet extends  Iterable<Object> {
	/**
	 * Computes the theoretic, value-based equality of the set elements
	 * <code>this</code> and <code>ls</code>.
	 *
	 * @param    ms, a MathSet
	 *
	 * @return   a boolean
	 *
	 */
	public boolean equals(Object obj);
	
	/**
	 * Returns the hash code for the math set <code>this</code>
	 * When applied on two elements with the same theoretic value 
	 * (see equals(MathSet ms)), it must result in the same hash value. 
	 * 
	 * @return	an int
	 */
	public int hashCode();
	
	/**
	 * Used for diagnostics. Returns an informative text suitable 
	 * for print outs.
	 * 
	 * @return	a string describing the value
	 */
	public String toString();

	
	/**
	 * Method size computes the set-theoretic cardinality of the set <code>this</code>
	 *
	 * @return   an int
	 *
	 */
	public int size();
	
	/**
	 * Method isEmptySet returns <code>true</code> iff <code>this</code> is an empty set
	 * and <code>false</code>, otherwise.
	 *
	 * @return   a boolean
	 *
	 */
	public boolean isEmpty();
	
	/**
	 * Method contains returns <code>true</code> iff <code>this</code> contains the 
	 * object element and <code>false</code>, otherwise.
	 *
	 * @return   a boolean
	 *
	 */
	public boolean contains(Object element);

	/**
	 * Method iterator returns an <code>Object</code> iterator over <code>this</code>
	 *
	 * @return   an Iterator
	 *
	 */
	public Iterator<Object> iterator();
	

	
	/**
	 * Method intersection computes (and returns) the set-theoretic intersection of 
	 * the sets <code>this</code> and <code>s</code>. The returned set
	 * is a new set and neither <code>this</code> nor <code>ms</code> is
	 * affected by the operation.
	 *
	 * @param    ms, a MathSet
	 *
	 * @return   a new MathSet
	 */
	public  MathSet intersection( MathSet ms);
	
	/**
	 * Method union computes (and returns) the set-theoretic union of 
	 * the sets <code>this</code> and <code>ms</code>. The returned set
	 * is a new set and neither <code>this</code> nor <code>ms</code> is
	 * affected by the operation.
	 *
	 * @param    ms                   a MathSet
	 *
	 * @return   a new MathSet
	 */
	public  MathSet union( MathSet ms);
	
	/**
	 * Method difference computes (and returns) the set-theoretic set difference of 
	 * the sets <code>this</code> and <code>ms</code>. The returned set
	 * is a new set and neither <code>this</code> nor <code>ms</code> is
	 * affected by the operation.
	 *
	 * @param    ms                   a MathSet
	 *
	 * @return   a new MathSet
	 */
	public  MathSet difference( MathSet ms);
	
	

	/**
	 * Method copy creates a new set conatining the same elements (object identity)
	 * as the <code>this</code> set. That is, a <em>shallow copy </em>.
	 *
	 *
	 * @return   a new MathSet
	 */
	public MathSet copy();
	
	
}
