package ee222yb_assign4.binHeap;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class BinaryHeapQueue implements PriorityQueue, Cloneable {

	private Task[] heap = new Task[8];
	private int size;

	public void insert(Task t) {
		//resize if needed
		if(size + 1 == heap.length)
			heap = Arrays.copyOf(heap, heap.length * 2);

		//add to last position
		heap[++ size] = t;

		//place it on the right position
		int i = size;
		while((i / 2) > 0 && heap[i / 2].PRIO < t.PRIO) {
			heap[i] = heap[i / 2];
			heap[i / 2] = t;
			i /= 2;
		}
	}

	public Task pullHighest() {
		if(size == 0)
			throw new NoSuchElementException();
		Task root = heap[1]; // store the root node to return later
		heap[1] = heap[size --]; // Sets the last added Task as root and reduces size

		fixHeap();

		return root;

	}

	public boolean contains(Task t) {
		for(Task i : heap) {
			if(i == t) {
				return true;
			}
		}
		return false;
	}
	
	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public Task peakHighest() {
		return heap[1];
	}

	private void fixHeap() {
		int i = 1;
		boolean done = false;
		while(!done) {
			Task parent = heap[i];
			int l = 2 * i; // index of left child

			if(l <= size) { // if left child exists
				Task leftChild = heap[l];
				Task prioChild = leftChild; 
				int p = l; // index of highest prio child

				int r = l + 1; // index of right child
				if(r <= size) { // if right child exists (can only exist if there is a left child)
					Task rightChild = heap[r];

					// Determine highest prio child
					if(rightChild.PRIO > leftChild.PRIO) {
						prioChild = rightChild;
						p = r;
					}
				}
				// Swap positions if child has higher prio than parent 
				if(prioChild.PRIO > parent.PRIO) {
					heap[i] = prioChild;
					heap[p] = parent;
					i = p;
				}
				else // parent is bigger than child
					done = true;
			}
			else // no left child (and no right child)
				done = true;
		}
	}
	
	@Override
	public String toString() {
		String str = "[";
		for(int i = 1; i <= size; i ++ ) {
			if(i == 1)
				str += heap[i];
			else
				str += ", " + heap[i];
		}
		str += "]";
		return str;
	}

}

