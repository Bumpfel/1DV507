package ee222yb_assign2.queue;

import java.util.Iterator;

public interface Queue {
	
	/**
	 * @return Current queue size
	 */
	public int size();
	
	/**
	 * @return True if empty, false if not
	 */
	public boolean isEmpty();
	
	/**
	 * Add an element at the end of the queue
	 * @param element The object to add
	 */
	public void enqueue(Object element);
	
	/**
	 * Removes the first object in the queue
	 * @return The removed object
	 */
	public Object dequeue();
	
	/**
	 * Returns (without removing) the first object
	 * @return The first object
	 */
	public Object first();
	
	/**
	 * Returns (without removing) the last element 
	 * @return The last element
	 */
	public Object last();
	
	/**
	 * Returns a string representation of the queue content
	 * @return Queue content as String
	 */
	public String toString();
	
	/**
	 * Creates an iterator that can iterate over the queued objects
	 * @return An object iterator
	 */
	public Iterator<Object> iterator();
}
