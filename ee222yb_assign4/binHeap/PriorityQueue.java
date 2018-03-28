package ee222yb_assign4.binHeap;

public interface PriorityQueue {
	
	public void insert(Task t);
	public Task pullHighest();
	public boolean contains(Task t);
	public int size();
	public boolean isEmpty();
	public Task peakHighest();
	
}
