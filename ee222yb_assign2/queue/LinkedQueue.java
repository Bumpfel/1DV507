package ee222yb_assign2.queue;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A class using a FIFO (first in-first out) data structure
 * @author Eric Enoksson
 * @since 2018-01-29
  */
public class LinkedQueue implements Queue {
	private int size;
	private Node firstNode, lastNode;
		
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
		Node newNode = new Node(element);
		if(firstNode == null)
			firstNode = newNode;
		lastNode = newNode;
		size++;
	}
	
	public Object dequeue() {
		checkQueue();
		Object obj = firstNode.value;
		firstNode = firstNode.next;
		size --;
		return obj;
	}

	public Object first() {
		checkQueue();
		return firstNode.value;
	}

	public Object last() {
		checkQueue();
		return lastNode.value;
	}
	
	public Iterator<Object> iterator() {
		return new QueueIterator();
	}
	
	@Override
	public String toString() {
		String str = new String();
		if(size > 0) {
			Iterator<Object> it = iterator();
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
		private Object value;
		
		public Node(Object obj) {
			value = obj;
			if(lastNode != null)
				lastNode.next = this;
		}
	}

	private class QueueIterator implements Iterator<Object> {
		private Node position = null;
		
		public boolean hasNext() {
			if(position == null)
				return firstNode != null;
			else
				return position.next != null;
		}

		public Object next() {
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
