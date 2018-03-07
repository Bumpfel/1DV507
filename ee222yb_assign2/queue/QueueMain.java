package ee222yb_assign2.queue;

import java.util.NoSuchElementException;

public class QueueMain {

	public static void main(String[] args) {
		try {
			LinkedQueue queue = new LinkedQueue();

			queue.enqueue("Lisa");
			queue.enqueue("Pelle");
			queue.enqueue("Bjorn");
			queue.enqueue("Sofie");
			queue.enqueue("Anna");

			System.out.println("Removing " + queue.dequeue() + " and " + queue.dequeue() + " from the queue");
			System.out.println();
			
			if(!queue.isEmpty()) {
				System.out.println("First in queue is: " + queue.first());
				System.out.println("Last in queue is: " + queue.last());
			}
			System.out.println("Queue size is: " + queue.size());
			System.out.println("People left in the queue: " + queue.toString()); // toString() uses the iterator
		}
		catch(NoSuchElementException e) {
			e.printStackTrace();
		}
	}

}
