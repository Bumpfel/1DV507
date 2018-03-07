package ee222yb_assign3.count_words;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.TreeSet;

public class WordCount2Main {

	public static void main(String[] args) {
		File file = new File("src\\ee222yb_assign3\\count_words\\words.txt");
		HashSet<Word> hSet = new HashSet<>();
		TreeSet<Word> tSet = new TreeSet<>();
		
		try(Scanner scan = new Scanner(file)) {
			while(scan.hasNext()) {
				Word w = new Word(scan.next().toLowerCase());
				hSet.add(w);
				tSet.add(w);
			}	
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		
		System.out.println("HashSet: File contains " + hSet.size() + " words");
		System.out.println("TreeSet: File contains " + tSet.size() + " words");
		
	}

}
