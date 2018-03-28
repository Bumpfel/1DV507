package math_set.purehash;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import math_set.MathSet;

public class MySet implements MathSet {
	HashSet<Object> values;
			
	public MySet() {
		values = new HashSet<>();
	}
	
	public MySet(Collection<Object> col) {
		values = new HashSet<>(col);
	}
	
	public MySet(Collection<Object> col1, Collection<Object> col2) {
		values = new HashSet<>();
		
		values.addAll(col1);
		values.addAll(col2);
	}
	
	public MySet(Object... elements) {
		for(Object obj : elements)
			values.add(obj);
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
		for(Object o : values)
			str.append(o.toString() + " ");
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
				for(Object o : other.values) {
					if(!values.contains(o))
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
		for(Object o : values)
			hashSum += o.hashCode();
			
		return hashSum;
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
		List<Object> list = new ArrayList<>();
		
		for(Object o : values) {
			if(ms.contains(o))
				list.add(o);
		}
		return new MySet(list);
	}

	@Override
	public MathSet union(MathSet ms) {
		MySet other = (MySet) ms;

//		List<Object> list = new ArrayList<>(values);
//		list.addAll(values);
//		list.addAll(other.values);
//		
//		return new MySet(list);
		return new MySet(values, other.values);
	}

	@Override
	public MathSet difference(MathSet ms) {
		List<Object> list = new ArrayList<>();
		for(Object o : values) {
			if(!ms.contains(o))
				list.add(o);
		}
		return new MySet(list);
	}

	@Override
	public MathSet copy() {
		return new MySet(values);
	}

}
