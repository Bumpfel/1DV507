package ee222yb_assign1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

public class WarAndPeace {

	public static void main(String[] args) {
		try {
			String path = "src\\ee222yb_assign1\\WarAndPeace.txt";
			
			String text = readText(path);
			String[] words = text.split(" |\n");
			System.out.println("Initial word count: " + words.length);
						
			Stream<String> stream = Arrays.stream(words);

			long wordCount = stream.map(w -> w.replaceAll("[^a-zA-Z-']", "")).filter(w -> w.length() > 0).map(w -> w.toLowerCase()).distinct().count();
			System.out.println("Unique words: " + wordCount);
		}
		catch(IOException ex) {
			ex.printStackTrace();
		}
	}
	
	private static String readText(String text) throws IOException {
		return Files.readAllLines(Paths.get(text)).toString();
	}

}
