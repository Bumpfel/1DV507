package ee222yb_assign3.count_words;

import java.util.Iterator;

public class TreeWordSet implements WordSet {
	private int size = 0;
	private Node root;
	
	public TreeWordSet() {
	}
	
	public TreeWordSet(Word... words) {
		for(Word word : words)
			add(word);
	}

	@Override
	public boolean contains(Word w) {
		Node currentNode = root;
		while(currentNode != null) {
			int comp = w.compareTo(currentNode.word);
			if(comp == 0)
				return true;
			else if(comp < 0)
				currentNode = currentNode.left;
			else 
				currentNode = currentNode.right;
		}
		return false;
	}

	@Override
	public void add(Word word) {
		Node newNode = new Node(word);
		if(root == null) {
			root = newNode;
			size ++;
		}
		else if(!contains(word)) {
			root.addNode(newNode);
			size ++;
		}
	}

	@Override
	public int size() {
		return size;
	}
	
	class Node {
		private Word word;
		private Node left, right;
		
		public Node(Word w) {
			word = w;
		}
		
		public void addNode(Node newNode) {
			int comparison = newNode.word.compareTo(word);
			if(comparison < 0) {
				if(left == null)
					left = newNode;
				else
					left.addNode(newNode);
			}
			else if(comparison > 0) {
				if(right == null)
					right = newNode;
				else
					right.addNode(newNode);
			}
		}
	
	}
	
	@Override
	public Iterator<Word> iterator() {
		return new TreeWordIterator();
	}
	
	@Override
	public String toString() {
		Iterator<Word> it = iterator();
		String str = new String();
		while(it.hasNext())
			str += it.next().toString() + ", ";
		if(str.isEmpty())
			return "TreeSet is empty";
		else
			return str.substring(0, str.length() - 2);
	}
	
	private class TreeWordIterator implements Iterator<Word> {
		private Node[] nodes = new Node[size];
		private int i;
		
		public TreeWordIterator() {
			Node n = nodes[i] = root;
			while(n.left != null) {
				nodes[++ i] = n.left;
				n = n.left;
			}
		}
		
		public boolean hasNext() {
			return i >= 0;
		}
		
		public Word next() {
			Node ret = nodes[i --];
			if(ret.right != null) {
				Node n = ret.right;
				nodes[++ i] = n;
				while(n.left != null) {
					nodes[++ i] = n.left;
					n = n.left;
				}
			}
			return ret.word;
		}
	}
	
}
