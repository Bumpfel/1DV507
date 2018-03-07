package ee222yb_assign1.intCollection;

public class ArrayIntStack extends AbstractIntCollection implements IntStack {

	public void push(int n) {
		if(size == values.length)
			resize();
		values[size++] = n;
	}
	
	public int pop() throws IndexOutOfBoundsException {
		if(size == 0)
			throw new IndexOutOfBoundsException("Stack empty, cannot pop.");
		return values[--size];
	}

	public int peek() throws IndexOutOfBoundsException {
		if(size == 0)
			throw new IndexOutOfBoundsException("Stack empty, cannot peek.");
		return values[size - 1];
	}

}
