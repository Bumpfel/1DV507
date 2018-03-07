package ee222yb_assign1.intCollection;

public class ArrayIntList extends AbstractIntCollection implements IntList {

	public void add(int n) {
		if(size == values.length)
			resize();
		values[size++] = n;
	}

	public void addAt(int n, int index) throws IndexOutOfBoundsException {
		if(!checkIndex(index, size + 1)) // Allowing method to add element after the last one
			throw new IndexOutOfBoundsException("Cannot add item at the specified index");
		if(size == values.length)
			resize();
		
		// Shifts the elements forward if necessary, starting with the last element
		for(int i = size; i > index; i --)
			values[i] = values[i - 1];
		
		values[index] = n;
		size ++;
	}

	public void remove(int index) throws IndexOutOfBoundsException {
		if(!checkIndex(index, size))
			throw new IndexOutOfBoundsException("Cannot remove item");
		for(int i = index; i < values.length - 1; i ++)
			values[i] = values[i + 1];
		size --;
	}

	public int get(int index) throws IndexOutOfBoundsException {
		if(!checkIndex(index, size))
			throw new IndexOutOfBoundsException("Cannot get item");
		return values[index];
	}

	/**
	 * Returns the index of the first found occurrence, or -1 if not found
	 */
	public int indexOf(int n) {
		int foundAt = -1;
		for(int i = 0; i < size; i ++) {
			if(n == values[i]) {
				foundAt = i;
				break;				
			}
		}
		return foundAt;
	}

}
