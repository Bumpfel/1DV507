package ee222yb_assign3.count_words;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class HashWordSet implements WordSet {
	private static final int INIT_SIZE = 100;
	
	private Node[] buckets = new Node[INIT_SIZE];
	private int size;
	
	public HashWordSet() {
	}
	
	public HashWordSet(Word...words) {
		for(Word word : words)
			add(word);
	}
	
	private int getHashIndex(Word w) {
		int positiveHash = Math.abs(w.hashCode());
		return positiveHash % buckets.length;
	}

	@Override
	public boolean contains(Word word) {
		int hashIndex = getHashIndex(word);
		Node bucket = buckets[hashIndex];
		while(bucket != null) {
			if(word.equals(bucket.value))
				return true;
			bucket = bucket.next;
		}
		return false;
	}

	@Override
	public void add(Word word) {
		int hashIndex = getHashIndex(word);
		if(!contains(word)) {
			Node bucket = new Node(word);
			bucket.next = buckets[hashIndex]; //null if index is empty. previous bucket becomes new buckets next
			buckets[hashIndex] = bucket; // new bucket becomes the bucket in the array
			size ++;
			if(size == buckets.length)
				rehash();
		}
	}
	
	private void rehash() {
		Node[] temp = buckets;
		buckets = new Node[temp.length * 2];
		size = 0;
		for(Node n : temp) { // iterate through every node in the array
			while(n != null) { // iterate over every node in the bucket
				add(n.value);
				n = n.next;
			}
		}
	}

	@Override
	public int size() {
		return size;
	}
	
	@Override
	public String toString() {
		Iterator<Word> it = iterator();
		String str = new String();
		while(it.hasNext())
			str += it.next().toString() + ", ";
		if(str.isEmpty())
			return "HashSet is empty";
		else
			return str.substring(0, str.length() - 2);
	}
	
	/**
	 * A method that gives a nice hierarchical print (for debugging - ought not really be public)
	 */
	public void printHashSet() {
		System.out.println("Hash Set");
		for(int i = 0; i < buckets.length; i ++) {
			System.out.println(" [ bucket " + i + " ]");
			Node thisBucket = buckets[i];
			int j = 0;
			while(thisBucket != null) {
				System.out.println("  [" + (j ++) + "] " + thisBucket.toString());
				thisBucket = thisBucket.next;
			}
		}
	}
	
	@Override
	public Iterator<Word> iterator() {
		return new HashWordIterator();
	}

	class HashWordIterator implements Iterator<Word> {
		private int iterations, bucketIndex;
		private Node currentBucket;

		public HashWordIterator() {
		}

		public boolean hasNext() {
			return iterations < size;
		}

		public Word next() {
			if(currentBucket != null && currentBucket.next != null)
				currentBucket = currentBucket.next;
			else {
				do {
					if(iterations == size)
						throw new NoSuchElementException("No such element");
					currentBucket = buckets[bucketIndex ++];
				}
				while(currentBucket == null);
			}
			iterations ++;
			return currentBucket.value;
		}
	}
	
	class Node {
		private Word value;
		private Node next = null;
		
		public Node(Word word) {
			value = word;
		}
		
		public String toString() {
			return value.toString();
		}
	}

}
