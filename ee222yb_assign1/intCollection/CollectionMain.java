package ee222yb_assign1.intCollection;

import java.util.Iterator;

public class CollectionMain {

	public static void main(String[] args) {
		
		//IntList
		ArrayIntList list = new ArrayIntList();
		
		for(int i = 0; i < 8; i ++)
			list.add(i);
		
		list.addAt(-1, 8);
		list.remove(5);
		
		System.out.println("List empty: " + list.isEmpty());
		System.out.println("get(5): " + list.get(5));
		System.out.println("first found indexOf(9): " + list.indexOf(9));
		System.out.println("size(): " + list.size());
		
		Iterator<Integer> it = list.iterator();
		System.out.print("[ ");
		while(it.hasNext())
			System.out.print(it.next() + " ");
		System.out.println("]");
		
		System.out.println();
		
		
		//IntStack 
		ArrayIntStack stack = new ArrayIntStack();
		
		for(int i = 0; i < 9; i ++)
			stack.push(i);
		
		System.out.println("Popping stack. Top element is: " + stack.pop());
		System.out.println("Peeking at stack. Top element is: " + stack.peek());
		
		System.out.println(stack.toString());
	}

}
