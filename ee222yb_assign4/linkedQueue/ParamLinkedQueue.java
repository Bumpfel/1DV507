package ee222yb_assign4.linkedQueue;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ParamLinkedQueue<T> implements ParamQueue<T> {
	private Node firstNode, lastNode;
	private int size;
	
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
	
	public void enqueue(T element) {
		Node newNode = new Node(element);
		if(firstNode == null)
			firstNode = newNode;
		lastNode = newNode;
		size++;
	}
	
	public T dequeue() {
		checkQueue();
		T obj = firstNode.value;
		firstNode = firstNode.next;
		size --;
		return obj;
	}

	public T first() {
		checkQueue();
		return firstNode.value;
	}

	public T last() {
		checkQueue();
		return lastNode.value;
	}
	
	public Iterator<T> iterator() {
		return new QueueIterator();
	}
	
	@Override
	public String toString() {
		String str = new String();
		if(size > 0) {
			Iterator<T> it = iterator();
			while(it.hasNext())
				str += it.next() + ", ";
			str = str.substring(0, str.length() - 2); // cuts the last comma
		}
		else
			str = "None";
		return str;
	}
	
	
	private class Node {
		private Node next;
		private T value;
		
		public Node(T obj) {
			value = obj;
			if(lastNode != null)
				lastNode.next = this;
		}
	}

	private class QueueIterator implements Iterator<T> {
		private Node position = null;
		
		public boolean hasNext() {
			if(position == null)
				return firstNode != null;
			else
				return position.next != null;
		}

		public T next() {
			if(!hasNext())
				throw new NoSuchElementException("Empty queue");
			
			if(position == null)
				position = firstNode;
			else
				position = position.next;
			return position.value;
		}
		
	}
}
