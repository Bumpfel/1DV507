package ee222yb_assign1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class FunctionPointers {

	public static void main(String[] args) {
		System.out.println("Part 1: Apply predicates");
		List<Integer> list = Arrays.asList(45, 3, 24, 16, 1, 1, 3, 8, 7, 6, 10, 12, 17, 22, 30);

		System.out.print("Print all numbers: ");
		Predicate<Integer> all = n -> true;
		selectAndPrint(list, all);

		System.out.print("\nPrint all odd numbers: ");
		Predicate<Integer> odd = n -> n % 2 == 1;
		selectAndPrint(list, odd);

		System.out.print("\nPrint all numbers greater than 10: ");
		Predicate<Integer> aboveTen = n -> n > 10;
		selectAndPrint(list, aboveTen);

		System.out.println("\n\nPart 2: Apply functions");
		List<Double> numbers = Arrays.asList(1.0, 16.0, 25.0, 81.0);
		System.out.println("Original: " + numbers);
		System.out.println("Square root: " + applyFunction(numbers, Math::sqrt));
		System.out.println("Power of two: " + applyFunction(numbers, FunctionPointers::powerOfTwo));
	}


	// Prints all elements in the list where predicate evaluates to true
	public static void selectAndPrint(List<Integer> list, Predicate<Integer> predicate) {
		for(Integer n : list) {
			if(predicate.test(n))
				System.out.print(n + " ");
		}
	}

	// Returns a new list containing the numbers resulting from applying fx 
	// on the input list numbers
	private static List<Double> applyFunction(List<Double> numbers, Function<Double, Double> fx) {
		List<Double> list = new ArrayList<>();
		for(Double d : numbers)
			list.add(fx.apply(d));
		return list;
	}

	private static Double powerOfTwo(Double d) {
		return d * d;
	}
}
