package ee222yb_assign4.binHeap;

public class WorkMain {

	public static void main(String[] args) throws CloneNotSupportedException {
		
		BinaryHeapQueue q = new BinaryHeapQueue();
		WorkTask wtask1 = new WorkTask(10, "Finish the java assignment");
		WorkTask wtask2 = new WorkTask(10, "Start with the boring course assignment");
		WorkTask wtask3 = new WorkTask(8, "Study for the test");
		WorkTask wtask4 = new WorkTask(6, "Something");
		WorkTask wtask5 = new WorkTask(5, "Something else");
		WorkTask wtask6 = new WorkTask(4, "Sleep");
		
		q.insert(wtask2);
		q.insert(wtask1);
		q.insert(wtask6);
		q.insert(wtask3);
		q.insert(wtask4);
		q.insert(wtask5);
				
		System.out.println("Heap contains work task 3: " + q.contains(wtask4));

		System.out.println("\nAll tasks sorted by priority");
		while(!q.isEmpty()) {
			Task t = q.pullHighest();
			System.out.println(t.PRIO + ". " + t.toString());
		}
		
	}
	
}
