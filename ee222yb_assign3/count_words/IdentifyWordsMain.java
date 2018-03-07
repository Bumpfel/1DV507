package ee222yb_assign3.count_words;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class IdentifyWordsMain {

	public static void main(String[] args) {
		File inputFile = new File("src\\ee222yb_assign3\\count_words\\HistoryOfProgramming.txt");
		File outputFile = new File("src\\ee222yb_assign3\\count_words\\words.txt");
		
		try(Scanner reader = new Scanner(inputFile)) {
			reader.useDelimiter("");
			String filteredText = new String();
			while(reader.hasNext()) {
				char next = reader.next().charAt(0);
				if(Character.isLetter(next) || Character.isWhitespace(next))
					filteredText += next;
			}
			PrintWriter writer = new PrintWriter(outputFile);
			writer.print(filteredText);
			System.out.println("Successfully wrote to file " + outputFile.getName());
			writer.close();
		}
		catch(FileNotFoundException ex) {
			System.out.println(ex.getMessage());
		}
	}

}
