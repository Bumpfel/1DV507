package ee222yb_assign4.linkedQueue;

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
class ParamQueueTest {
	ParamLinkedQueue<Integer> intEmptyQueue = new ParamLinkedQueue<>();
	ParamLinkedQueue<String> strEmptyQueue = new ParamLinkedQueue<>();
	ParamLinkedQueue<Integer> intTestQueue;
	ParamLinkedQueue<String> strTestQueue;
	
	private final int QUEUE_SIZE = 12;
	
	@BeforeAll 
	public void setUp() {
		restoreTestQueues();
	}
	
	private void emptyTestQueues() {
		intTestQueue = new ParamLinkedQueue<>();
		strTestQueue = new ParamLinkedQueue<>();
	}
	
	private void restoreTestQueues() {
		intTestQueue = new ParamLinkedQueue<>();
		strTestQueue = new ParamLinkedQueue<>();
		for(int i = 1; i <= QUEUE_SIZE; i ++) {
			intTestQueue.enqueue(i);
			strTestQueue.enqueue("" + i);
		}
	}
	
	@Test
	void testSize() {
		assertEquals(0, intEmptyQueue.size());
		assertEquals(0, strEmptyQueue.size());
		
		assertEquals(QUEUE_SIZE, intTestQueue.size());
		assertEquals(QUEUE_SIZE, strTestQueue.size());
	}

	@Test
	void testIsEmpty() {
		assertTrue(intEmptyQueue.isEmpty());
		assertTrue(strEmptyQueue.isEmpty());
		
		assertFalse(intTestQueue.isEmpty());
		assertFalse(strTestQueue.isEmpty());
	}
	
	@Test
	void testFirst() {
		emptyTestQueues();
		
		Integer j = -1;
		intTestQueue.enqueue(j);
		String k = "random";
		strTestQueue.enqueue(k);
		
		intTestQueue.enqueue(1);
		strTestQueue.enqueue("" + 1);
		
		assertSame(j, intTestQueue.first());
		assertSame(k, strTestQueue.first());
		
		assertThrows(NoSuchElementException.class, () -> intEmptyQueue.first());
		assertThrows(NoSuchElementException.class, () -> strEmptyQueue.first());
		
		restoreTestQueues();
	}
	
	@Test
	void testLast() {
		int i = -1;
		String str = "random";
		intTestQueue.enqueue(i);
		strTestQueue.enqueue(str);
		assertSame(i, intTestQueue.last());
		assertSame(str, strTestQueue.last());
		
		assertThrows(NoSuchElementException.class, () -> intEmptyQueue.last());
		assertThrows(NoSuchElementException.class, () -> strEmptyQueue.last());
		
		restoreTestQueues();
	}
	
	@Test
	void testEnqueue() {
		emptyTestQueues();
		
		int firstInt = -1;
		String firstStr= "-1";
		intTestQueue.enqueue(firstInt);
		strTestQueue.enqueue(firstStr);
		
		for(int i = 0; i < 10000; i ++) { // Queuing a lot of objects just to see if anything breaks
			intTestQueue.enqueue(i);
			strTestQueue.enqueue("" + i);
		}
		int oldIntSize = intTestQueue.size();
		int oldStrSize = strTestQueue.size();
		
		int lastInt = -99;
		String lastStr = "-99";
		intTestQueue.enqueue(lastInt);
		strTestQueue.enqueue(lastStr);
		
		 // Testing that enqueue() increases queue size
		assertEquals(oldIntSize + 1, intTestQueue.size());
		assertEquals(oldStrSize + 1, strTestQueue.size());
		
		// Testing that the first element is what was added first
		assertSame(firstInt, intTestQueue.first());
		assertSame(firstStr, strTestQueue.first());
		
		// Testing that the last element is what was added last
		assertSame(lastInt, intTestQueue.last());
		assertSame(lastStr, strTestQueue.last());
		
		restoreTestQueues();
	}
	
	@Test
	void testDequeue() {
		Integer firstInt = intTestQueue.first();
		String firstStr = strTestQueue.first();
		
		int intSize = intTestQueue.size();
		int strSize = strTestQueue.size();

		Integer removedInt = intTestQueue.dequeue();
		String removedStr = strTestQueue.dequeue();
		
		Integer newFirstInt = intTestQueue.first();
		String newFirstStr = strTestQueue.first();
		
		 // Testing that dequeue decreases queue size
		assertEquals(intSize - 1, intTestQueue.size());
		assertEquals(strSize - 1, strTestQueue.size());
		
		// Testing that the first value isn't the same after de-queueing
		assertNotSame(firstInt, newFirstInt);
		assertNotSame(firstStr, newFirstStr);
		
		// Testing that the first value (pre-dequeue) is the same as the dequeued value
		assertSame(removedInt, firstInt); 
		assertSame(removedStr, firstStr);
		
		
		System.out.println(intEmptyQueue.size());
		assertThrows(NoSuchElementException.class, () -> intEmptyQueue.dequeue()); // Test that exception is thrown when trying to dequeue an empty queue
		assertThrows(NoSuchElementException.class, () -> strEmptyQueue.dequeue());
		
		restoreTestQueues();
	}

	@Test
	void testIterator() {
		Iterator<Integer> intItEmpty = intEmptyQueue.iterator();
		Iterator<String> strItEmpty = strEmptyQueue.iterator();
		assertThrows(NoSuchElementException.class, () -> intItEmpty.next());
		assertThrows(NoSuchElementException.class, () -> strItEmpty.next());
		
		
		// Integer queue tests
		Iterator<Integer> intIt = intTestQueue.iterator();
		Integer prevInt = null;
		Integer nextInt = null;
		Integer lastInt = intTestQueue.last();
		assertSame(intTestQueue.first(), intIt.next()); // Testing that the iterator starts from the beginning
		int count = 0;
		while(intIt.hasNext()) {
			nextInt = intIt.next();
			assertNotSame(prevInt, nextInt); // Testing that the iterator is advancing
			prevInt = nextInt;
			count ++;
		}
		assertEquals(intTestQueue.size() - 1, count); // Testing that it iterates through all objects in the queue
		assertSame(lastInt, nextInt); // Making sure the iterator doesn't stop until it's gotten to the end
		
		// String queue tests
		Iterator<String> strIt = strTestQueue.iterator();
		String prevStr = null;
		String nextStr = null;
		String lastStr = strTestQueue.last();
		assertSame(strTestQueue.first(), strIt.next());
		count = 0;
		while(strIt.hasNext()) {
			nextStr = strIt.next();
			assertNotSame(prevStr, nextStr);
			prevStr = nextStr;
			count ++;
		}		
		assertEquals(strTestQueue.size() - 1, count);
		assertSame(lastStr, nextStr);
	}
	
	@Test
	void testToString() {
		emptyTestQueues();
		
		assertEquals("None", intEmptyQueue.toString());
		assertEquals("None", strEmptyQueue.toString());

		final int OBJECTS = 6;
		String testString = "1, 2, 3, 4, 5";
		
		for(int i = 1; i < OBJECTS; i ++) {
			intTestQueue.enqueue(i);
			strTestQueue.enqueue("" + i);
		}
		assertEquals(testString, intTestQueue.toString());
		assertEquals(testString, strTestQueue.toString());
		
		restoreTestQueues();
	}

}
