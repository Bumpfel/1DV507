package ee222yb_assign1.xChart;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.PieChart;
import org.knowm.xchart.SwingWrapper;


public class Histogram {

	public static void main(String[] args) {
		try {
			String path = "src\\ee222yb_assign1\\Xchart\\ints.dat"; // I hard-coded this since the launch arguments have a tendency to disappear for me (I think it has something to do with file synchronization)
			File dataFile = new File(path);
			Scanner input = new Scanner(dataFile);
			
			final int NR_OF_INTERVALS = 11;
			final int INTERVAL_SPAN = 10;
			int[] intervals = new int[NR_OF_INTERVALS];
			
			while(input.hasNextInt()) {
				int curNr = input.nextInt();
				int index = (curNr - 1) / INTERVAL_SPAN; // Calculates which interval the number fits in (which array index)
				
				if(index < intervals.length && index >= 0)
					intervals[index] ++;
				else
					intervals[NR_OF_INTERVALS - 1] ++;
			}
			input.close();
			
			List<String> intervalTitles = new ArrayList<String>(); // For the histogram
			
			// Pie chart
			PieChart pieChart = new PieChart(800, 600);
			pieChart.setTitle("Histogram over file " + dataFile.getName());
					
			int lineStartInterval = 1;
			int lineEndInterval = INTERVAL_SPAN;
			for(int i = 0; i < intervals.length - 1; i ++) {
				String intervalTitle = lineStartInterval + "-" + lineEndInterval;
				
				pieChart.addSeries(intervalTitle, intervals[i]);
				intervalTitles.add(intervalTitle);
				
				lineStartInterval = lineEndInterval + 1;
				lineEndInterval = lineStartInterval - 1 + INTERVAL_SPAN;
			}
			intervalTitles.add("Others");
			pieChart.addSeries("Others", intervals[NR_OF_INTERVALS - 1]);
			
			new SwingWrapper<PieChart>(pieChart).displayChart();
			
			//  Histogram chart
			CategoryChart colChart = new CategoryChart(800, 600);
			colChart.setTitle("Histogram over " + dataFile.getName() + " in intervals of " + INTERVAL_SPAN);
			colChart.setXAxisTitle("Intervals");
			colChart.setYAxisTitle("Numbers in interval");
			
			// I know I could have just made a list to begin with instead of an array, but I couldn't be arsed to change it
			List<Integer> intervalsList = new ArrayList<>();
			for(int n : intervals)
				intervalsList.add(n);
			
			colChart.addSeries("Histogram", intervalTitles, intervalsList);
			
			new SwingWrapper<CategoryChart>(colChart).displayChart();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}

}