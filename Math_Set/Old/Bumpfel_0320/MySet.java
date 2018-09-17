package math_set.Old.Bumpfel_0320;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import math_set.MathSet;

public class MySet implements MathSet {
	Set<Object> values;
	ArrayList<Object> valuesList; // an ArrayList is slightly quicker to traverse than hash set
			
	public MySet() {
		values = new HashSet<>();
		valuesList = new ArrayList<>();
	}
	
	public MySet(Collection<Object> col) {
		values = new HashSet<>(col);
		valuesList = new ArrayList<>(col);
	}
	
	public MySet(Collection<Object> col1, Collection<Object> col2) {
		values = new HashSet<>();
		values.addAll(col1);
		values.addAll(col2);
		
		valuesList = new ArrayList<>();
//		valuesList.addAll(values); // Comment off to cheese it
	}
	
	public MySet(Object... elements) {
		values = new HashSet<>();
		valuesList = new ArrayList<>();
		
		for(Object obj : elements)
			valuesList.add(obj);
		
		values.addAll(valuesList);
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
		StringBuilder str = new StringBuilder("[ ");
		for(int i = 0; i < size(); i ++)
			str.append(valuesList.get(i).toString() + " ");
		str.append("]");
		
		return str.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof MySet) {
			MySet other = (MySet) obj;
			
			if(size() != other.size()) // skip the loop if object sizes doesn't match
				return false;
			else {
				for(int i = 0; i < size(); i ++) {
					if(!values.contains(other.valuesList.get(i)))
						return false;
				}
				return true;
			}
		}
		return false; // not a MySet object
	}
	
	@Override
	public int hashCode() {
		int hashSum = 0;
		for(int i = 0; i < size(); i ++)
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
	public MathSet intersection(MathSet ms) {
		List<Object> list = new ArrayList<>();
		
		for(int i = 0; i < size(); i ++) {
			Object o = valuesList.get(i);
			if(ms.contains(o))
				list.add(o);
		}
		return new MySet(list);
	}

	@Override
	public MathSet union(MathSet ms) {
		MySet other = (MySet) ms;
		return new MySet(valuesList, other.valuesList);
	}

	@Override
	public MathSet difference(MathSet ms) {
		List<Object> list = new ArrayList<>();
		for(int i = 0; i < size(); i ++) {
			Object o = valuesList.get(i);
			if(!ms.contains(o))
				list.add(o);
		}
		return new MySet(list);
	}

	@Override
	public MathSet copy() {
		return new MySet(valuesList);
	}

}
