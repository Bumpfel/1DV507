package ee222yb_assign4.binHeap;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class BinaryIntHeap {
	private int[] heap = new int[8];
	private int size;
	
	/*public static void main(String[] args) {
		
		BinaryIntHeap b = new BinaryIntHeap();
	
		b.insert(-1);
		b.insert(6);
		b.insert(-4);
		b.insert(9);
		b.insert(8);
		
		System.out.println(b.toString());
		System.out.println(b.size + ", " + b.heap.length);
		System.out.println(Arrays.toString(b.heap));
		
		System.out.println("Pulled: " + b.pullHighest());

		System.out.println(b.toString());
		
		System.out.println("Pulled: " + b.pullHighest());
		
		System.out.println(b.toString());
	}*/
		
	public BinaryIntHeap() {
	}

	public void insert(int n) {
		if(size + 1 == heap.length)
			heap = Arrays.copyOf(heap, heap.length * 2);

		heap[++ size] = n;
		
		int i = size;
		while(heap[i / 2] < n && (i / 2) > 0) {
			heap[i] = heap[i / 2];
			heap[i / 2] = n;
			i /= 2;
		}
	}

	public int pullHighest() {
		if(size <= 0)
			throw new NoSuchElementException();
		int ret = heap[1];
		heap[1] = heap[size --];
		
		fixHeap();
		
		return ret;
	}
	
	// Inspired by the course book
	private void fixHeap() {
		int i = 1;
		boolean done = false;
		while(!done) {
			int parent = heap[i];
			int l = 2 * i; // index of left child
			
			if(l <= size) { // if left child exists
				int leftChild = heap[l];
				int biggestChild = leftChild, b = l; // b index of biggest child
				int r = l + 1; // index of right child
				if(r <= size) { // if right child exists (can only exist if there is a left child)
					int rightChild = heap[r];

					// Determine biggest child
					if(rightChild > leftChild) {
						biggestChild = rightChild;
						b = r;
					}
				}
				// Swap positions if child is bigger than parent
				if(biggestChild > parent) {
						heap[i] = biggestChild;
						heap[b] = parent;
						i = b;
				}
				else // parent is bigger than child
					done = true;
			}
			else // no left child
				done = true;
		}
	}
	
	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}
	
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
