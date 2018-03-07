package ee222yb_assign1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class PrintJavaMain {
	private static int indent = 0;
	private static final int INDENT_SIZE = 2;

	public static void main(String[] args) {
		try {
			printAllJavaFiles(new File("src"));
		}
		catch(FileNotFoundException ex) {
			ex.printStackTrace();
		}
	}

	public static void printAllJavaFiles(File directory) throws FileNotFoundException {

		if(directory.isDirectory()) {
			print(directory);
			indent += INDENT_SIZE;
			
			File[] dirContent = directory.listFiles();
			
			// Sorts the array by directories > files
			Arrays.sort(dirContent, (File f1, File f2) -> {
				if (f1.isDirectory() && !f2.isDirectory())
					return -1;
				else
					return 1;
			});
			
			for(File item : dirContent) {
				printAllJavaFiles(item);
				if(item.isFile() && item.getName().length() > 5) {
					String name = item.getName();
					String fileEnding = name.substring(name.length() - 5);
					if(fileEnding.equalsIgnoreCase(".java"))
						print(item);
				}
			}
			indent -= INDENT_SIZE;
		}
	
	}

	public static void print(File item) throws FileNotFoundException {
		String printString = new String();
		for(int i = 0; i < indent; i ++)
			printString += " ";

		if(item.isDirectory())
			printString += "<" + item.getName() + ">";
		else {		
			int lineCounter = 1;
			Scanner fileScanner = new Scanner(item);
			fileScanner.useDelimiter("\n");
			while(fileScanner.hasNext()) {
				fileScanner.next();
				lineCounter ++;
			}
			fileScanner.close();
			printString += item.getName() + " (" + lineCounter + " rows)"; // Sometimes lineCounter is off by 1, sometimes not. Not entirely sure why
		}
		System.out.println(printString);
	}

}
