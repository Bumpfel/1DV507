package ee222yb_assign2.queue;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayQueue implements Queue {
	// the field last points 1 position ahead of the last index. 0 as default
	private int first, last, size;
	private Object[] arr = new Object[10];

	/**
	 * Resizes and rearranges the array so that first starts at 0 again
	 */
	private void resize() {
		Object[] temp = new Object[2 * arr.length];
		for(int i = 0; i < arr.length; i ++) {
			temp[i] = arr[(first + i) % arr.length];
		}
		arr = temp;
		first = 0;
		last = size;
	}
	
	private void checkQueue() {
		if(size == 0)
			throw new NoSuchElementException("Queue is empty");
	}
	
	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public void enqueue(Object element) {
		if(size == arr.length)
			resize();
		arr[last] = element;
		last = (last + 1) % arr.length;
		size ++;
	}
	
	public Object dequeue() {
		checkQueue();
		size --;
		Object removedObj = arr[first];
		arr[first] = null; // not really necessary. cannot be accessed anyway, but makes debugging easier
		first = (first + 1) % arr.length;
		return removedObj;
	}

	public Object first() {
		checkQueue();
		return arr[first];
	}

	public Object last() {
		checkQueue();
		int index = last - 1;
		if(last == 0 && size > 0)
			index = size - 1; // If the queue has looped around, set index to the last array index
		return arr[index];
	}
	
	@Override
	public String toString() {
		String str = new String();
		if(size > 0) {
			Iterator<Object> it = new ArrayQueueIterator();
			while(it.hasNext())
				str += it.next() + ", ";
			str = str.substring(0, str.length() - 2);
		}
		else
			str = "None";
		return str;
	}

	public Iterator<Object> iterator() {
		return new ArrayQueueIterator();
	}

	private class ArrayQueueIterator implements Iterator<Object> {
		private int position = first;
		private int indexesTraversed;
		
		public boolean hasNext() {
			return indexesTraversed < size;
		}

		public Object next() {
			if(!hasNext())
				throw new NoSuchElementException("Empty queue");
			Object nextObj = arr[position];
			position = (position + 1) % arr.length;
			indexesTraversed ++;
			return nextObj;
		}

	}

}
