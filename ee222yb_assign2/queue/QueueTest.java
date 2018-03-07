package ee222yb_assign2.queue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@TestInstance(Lifecycle.PER_CLASS)
class QueueTest{
	
	LinkedQueue emptyQueue = new LinkedQueue();
	LinkedQueue testQueue = new LinkedQueue();
	LinkedQueue populatedQueue = new LinkedQueue();
	
//	ArrayQueue emptyQueue = new ArrayQueue();
//	ArrayQueue testQueue = new ArrayQueue();
//	ArrayQueue populatedQueue = new ArrayQueue();
	
	private final int QUEUE_SIZE = 12;
	
	@BeforeAll 
	public void setUp() {
		for(int i = 1; i <= QUEUE_SIZE; i ++)
			populatedQueue.enqueue(i);
		
		testQueue.enqueue(1); // Making sure it's not empty
		testQueue.enqueue(2);
	}
	
	@Test
	void testSize() {
		assertEquals(0, emptyQueue.size());
		assertEquals(QUEUE_SIZE, populatedQueue.size());
	}

	@Test
	void testIsEmpty() {
		assertTrue(emptyQueue.isEmpty());
		assertFalse(populatedQueue.isEmpty());
	}

	@Test
	void testEnqueue() {
		final int START = 1;
		final int END = 100000;
		for(int i = START; i < END; i ++) // Queuing a lot of objects just to see if anything breaks
			testQueue.enqueue(i);

		int size = testQueue.size();

		String testStr = "test";
		testQueue.enqueue(testStr);
				
		assertEquals(size + 1, testQueue.size()); // Testing that enqueue() increases queue size
		assertSame(START, testQueue.first()); // Testing that the first element is what was added first
		assertSame(testStr, testQueue.last()); // Testing that the last element is what was added last
	}
	
	@Test
	void testDequeue() {
		Object first = testQueue.first();
		int size = testQueue.size();
		Object removed = testQueue.dequeue();
		Object newFirst = testQueue.first();
				
		assertEquals(size - 1, testQueue.size()); // Testing that dequeue decreases queue size
		assertNotSame(first, newFirst); // Testing that the first value isn't the same after de-queueing
		assertSame(first, removed); // Testing that the first value (pre-dequeue) is the same as the dequeued value
		
		assertThrows(NoSuchElementException.class, () -> emptyQueue.dequeue()); // Test that exception is thrown when trying to dequeue an empty queue
	}
	
	@Test
	void testFirst() {
		String str = "random";
		emptyQueue.enqueue(str);
		assertSame(str, emptyQueue.first());
		assertSame(1, testQueue.first()); // 1 was added first in the setup
		emptyQueue.dequeue(); // As the variable name suggests, I want this queue to be empty after the test
		
		assertThrows(NoSuchElementException.class, () -> emptyQueue.first());
	}
	
	@Test
	void testLast() {
		String str = "random";
		testQueue.enqueue(str);
		emptyQueue.enqueue(str);
		assertSame(str, testQueue.last());
		assertSame(str, emptyQueue.last());
		emptyQueue.dequeue();
		
		assertThrows(NoSuchElementException.class, () -> emptyQueue.last());
	}

	@Test
	void testIterator() {
		Iterator<Object> itEmpty = emptyQueue.iterator();
		assertThrows(NoSuchElementException.class, () -> itEmpty.next());
		
		Iterator<Object> it = populatedQueue.iterator();
		Object prev = null;
		Object next = null;
		Object last = populatedQueue.last();
		assertSame(populatedQueue.first(), it.next()); // Testing that the iterator starts from the beginning
		int count = 0;
		while(it.hasNext()) {
			next = it.next();
			assertNotSame(prev, next); // Testing that the iterator is advancing
			prev = next;
			count ++;
		}
		assertEquals(populatedQueue.size() - 1, count); // Testing that it iterates through all objects in the queue
		assertSame(last, next); // Making sure the iterator doesn't stop until it's gotten to the end
	}
	
	@Test
	void testToString() {
		final int OBJECTS = 6;
		String testString = "1, 2, 3, 4, 5";

		assertEquals("None", emptyQueue.toString());
		
		for(int i = 1; i < OBJECTS; i ++)
			emptyQueue.enqueue(i);
		
		assertEquals(testString, emptyQueue.toString());
		
		for(int i = 1; i < OBJECTS; i ++)
			emptyQueue.dequeue();
	}

}
