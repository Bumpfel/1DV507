package ee222yb_assign4.binHeap;

public abstract class Task {

	public final int PRIO;
	private String data;
	
	public Task(int newPrio, String newData) {
		if(newPrio < 0)
			throw new IllegalArgumentException("Priority must be a positive value");
		PRIO = newPrio;
		data = newData;
	}
	
	@Override
	public String toString() {
		return data;
	}
}
