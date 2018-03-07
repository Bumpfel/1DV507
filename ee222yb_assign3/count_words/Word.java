package ee222yb_assign3.count_words;

public class Word implements Comparable<Word> {
	private String data;
	
	public Word(String str) { 
		data = str;
	}
	
	public String toString() {
		return data;
	}

	@Override
	public int hashCode() {
		return data.hashCode();
	}
	
	@Override
	public boolean equals(Object other) {
		if(other instanceof Word) {
			Word otherWord = (Word) other;
			return otherWord.data.compareTo(data) == 0;
		}
		return false;
	}
	
	public int compareTo(Word w) {
		return data.compareToIgnoreCase(w.data); //using String compareTo method
	}
}
