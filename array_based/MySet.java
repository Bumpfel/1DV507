/*
 * File: MySet.java
 * Author: Jonas Lundberg
 * Date: 12 feb 2014
 */
package math_set.array_based;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import math_set.MathSet;

/**
 * A very simple (stupid?) MathSet implementation based on ArrayList.
 * 
 * @author jlnmsi
 *
 */
public class MySet implements MathSet {
	private ArrayList<Object> values;
	
	public MySet() { 
		values = new ArrayList<Object>();
	}
	
	public MySet(Collection<Object> col) {
		// Remove duplicates
		HashSet<Object> tmp = new HashSet<Object>(col);
		
		values = new ArrayList<Object>();
		values.addAll(tmp);
	}
	
	public MySet(Object ... elements) {
		// Remove duplicates
		List<Object> list = Arrays.asList(elements);
		HashSet<Object> tmp = new HashSet<Object>(list);
		
		values = new ArrayList<Object>();
		values.addAll(tmp);
	}
	
	@Override
	public String toString() {
		StringBuilder buf = new StringBuilder();
		buf.append("[ ");
		for (Object o: values) {
			buf.append(o.toString()+" ");
		}
		buf.append("]");
		return buf.toString();
	}

	@Override
	public int hashCode() {		
		int hash = 0;
		for (Object obj : values)
			hash += obj.hashCode();
		return hash;
	}
	
	
	@Override
	public int size() {		
		return values.size();
	}


	@Override
	public boolean isEmpty() {
		return size() == 0;
	}
	
	@Override
	public boolean equals(Object obj) {		
		if (obj instanceof MySet) {
			MySet ms = (MySet) obj;
			if (values.size() != ms.values.size())
				return false;
			else {
				for (Object o : ms.values) {
					if (!values.contains(o))
						return false;
				}
				return true;
			}				
		}
		return false;
	}


	@Override
	public boolean contains(Object element) {
		return values.contains(element);
	}


	@Override
	public Iterator<Object> iterator() {
		return values.iterator();
	}


	@Override
	public MathSet intersection(MathSet ms) {
		MySet other = (MySet) ms;
		ArrayList<Object> result = new ArrayList<Object>();
		for (int i=0; i<values.size();i++) {
			Object o = values.get(i);
			if (other.values.contains(o))
				result.add(o);
		}
		return new MySet(result);
	}

	@Override
	public MathSet union(MathSet ms) {
		MySet other = (MySet) ms;
		ArrayList<Object> result = new ArrayList<Object>();
		result.addAll(other.values);
		
		for (int i=0; i<values.size();i++) {
			Object o = values.get(i);
			if (!result.contains(o))
				result.add(o);
		}
		return new MySet(result);
		
	}

	@Override
	public MathSet difference(MathSet ms) {
		MySet other = (MySet) ms;
		ArrayList<Object> result = new ArrayList<Object>();
		for (int i=0; i<values.size();i++) {
			Object o = values.get(i);
			if (!other.values.contains(o))
				result.add(o);
		}
		return new MySet(result);
	}

	@Override
	public MathSet copy() {
		ArrayList<Object> result = new ArrayList<Object>();
		result.addAll(values);
		return new MySet(result);
	}

	/*
	 * Private Help methods
	 */
	private MySet(ArrayList<Object> list) {
		values = list;
	}

}
