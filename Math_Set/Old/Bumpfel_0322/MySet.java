package math_set.Bumpfel_0322;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import math_set.MathSet;

public class MySet implements MathSet {
	private Set<Object> values;
	private ArrayList<Object> valuesList; // an ArrayList is slightly quicker to traverse than a hash set. May contain duplicates.
			
	public MySet() {
		values = new HashSet<>();
		valuesList = new ArrayList<>();
	}
	
	public MySet(Collection<Object> col) {
		values = new HashSet<>(col);
		valuesList = new ArrayList<>(col);
	}
	
	public MySet(Object... elements) {
		valuesList = (ArrayList<Object>) Arrays.asList(elements);
		values = new HashSet<>(valuesList);
	}

	@Override
	public int size() {
		return values.size();
	}

	@Override
	public boolean isEmpty() {
		return values.size() == 0;
	}

	@Override
	public String toString() {
		return valuesList.toString();
	}
	

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof MySet) {
			MySet other = (MySet) obj;
			
			if(size() != other.size())
				return false;
			else if(values.containsAll(other.valuesList))
				return true;
			
			return false;
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		int hashSum = 0;
		for(int i = 0; i < valuesList.size(); i ++)
			hashSum += valuesList.get(i).hashCode();
		return hashSum;
	}
	
	@Override
	public boolean contains(Object element) {
		return values.contains(element);
	}

	@Override
	public Iterator<Object> iterator() {
		return valuesList.iterator();
	}


	@Override
	public MathSet union(MathSet ms) {
		MySet other = (MySet) ms;
		ArrayList<Object> tmp = new ArrayList<>(valuesList);
		tmp.addAll(other.valuesList);
		return new MySet(tmp);
	}

	@Override
	public MathSet intersection(MathSet ms) {
		MySet other = (MySet) ms;
		ArrayList<Object> tmp = new ArrayList<>(valuesList);
		tmp.retainAll(other.values);
		return new MySet(tmp);
	}

	@Override
	public MathSet difference(MathSet ms) {
		MySet other = (MySet) ms;
		ArrayList<Object> tmp = new ArrayList<>(valuesList);
		tmp.removeAll(other.values);
		return new MySet(tmp);
	}

	@Override
	public MathSet copy() {
		return new MySet(valuesList);
	}

}
